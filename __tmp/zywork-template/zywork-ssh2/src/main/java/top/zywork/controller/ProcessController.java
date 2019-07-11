package top.zywork.controller;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import top.zywork.common.DateUtils;
import top.zywork.common.ExceptionUtils;
import top.zywork.common.PageQueryUtils;
import top.zywork.common.WebUtils;
import top.zywork.constant.BPMNConstants;
import top.zywork.dto.ProcessDTO;
import top.zywork.enums.CommonControllerStatusEnum;
import top.zywork.exception.ServiceException;
import top.zywork.query.ProcessDeployQuery;
import top.zywork.service.ActivitiService;
import top.zywork.service.ProcessService;
import top.zywork.vo.ControllerStatusVO;
import top.zywork.vo.PagerVO;
import top.zywork.vo.ProcessVO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 流程管理控制器<br />
 * 创建于2017-10-16
 *
 * @author 王振宇
 * @version 1.0
 */
@Controller
@ParentPackage(value = "base-package")
@Namespace(value = "/process")
public class ProcessController extends BaseController {

    private static final long serialVersionUID = 7833474652291681558L;

    private Logger logger = LoggerFactory.getLogger(ProcessController.class);

    private ProcessService processService;
    private ActivitiService activitiService;

    private ProcessVO processVO;
    private File process;
    private String processFileName;
    private String processContentType;
    private ControllerStatusVO statusVO;
    private PagerVO<ProcessDTO> pagerVO;

    private String processName;

    /**
     * 上传流程zip文件
     * @return
     */
    @Action(value = "save", results = {@Result(name = "save", type = "json", params = {"root", "statusVO"})})
    public String save() {
        processVO.setName(top.zywork.common.FileUtils.getFileNameWithoutExt(processFileName));
        processVO.setPath(BPMNConstants.BPMN_DIR + "/" + processFileName);
        processVO.setUserId(1L);
        try {
            FileUtils.copyFile(process, new File(top.zywork.common.FileUtils.getBPMNDir() + processFileName));
            ProcessDTO processDTO = processService.getByName(processVO.getName());
            if (processDTO == null) {
                processService.save(getDozerMapper().map(processVO, ProcessDTO.class));
            }
        } catch (IOException | ServiceException e) {
            throw ExceptionUtils.appException(e);
        }
        statusVO = ControllerStatusVO.okStatus(CommonControllerStatusEnum.PROCESS_SAVE_OK.getCode(),
                CommonControllerStatusEnum.PROCESS_SAVE_OK.getMessage());
        return "save";
    }

    /**
     * 分页列出所有已经上传的流程定义文件
     * @return
     */
    @Action(value = "list_page", results = {@Result(name = "listPage", type = "json", params = {"root", "pagerVO"})})
    public String listPage() {
        pagerVO = getDozerMapper().map(processService.listPage(PageQueryUtils.getPageQuery(1)), PagerVO.class);
        return "listPage";
    }

    /**
     * 部署流程
     * @return
     */
    @Action(value = "deploy", results = {@Result(name = "deploy", type = "json", params = {"root", "statusVO"})})
    public String deploy() {
        activitiService.deploy(processName);
        processService.updateDeploy(new ProcessDeployQuery(null, processName, 1, DateUtils.currentDate()));
        statusVO = ControllerStatusVO.okStatus(CommonControllerStatusEnum.PROCESS_DEPLOY_OK.getCode(),
                CommonControllerStatusEnum.PROCESS_DEPLOY_OK.getMessage());
        return "deploy";
    }

    /**
     * 删除流程部署
     * @return
     */
    @Action(value = "remove", results = {@Result(name = "remove", type = "json", params = {"root", "statusVO"})})
    public String remove() {
        activitiService.remove(processName);
        processService.updateDeploy(new ProcessDeployQuery(null, processName, 0, null));
        statusVO = ControllerStatusVO.okStatus(CommonControllerStatusEnum.PROCESS_REMOVE_OK.getCode(),
                CommonControllerStatusEnum.PROCESS_REMOVE_OK.getMessage());
        return "remove";
    }

    /**
     * 列出所有流程定义
     * @return
     */
    @Action(value = "list_definitions", results = {@Result(name = "listProcessDefinitions", type = "json", params = {"root", "statusVO"})})
    public String listDefinitions() {
        List<ProcessDefinition> processDefinitionList = activitiService.listProcessDefinitions();
        for (ProcessDefinition processDefinition : processDefinitionList) {
            System.out.println(processDefinition.getId() + ", " + processDefinition.getName() + ", "
                    + processDefinition.getVersion());
        }
        return "listProcessDefinitions";
    }

    /**
     * 列出流程实例
     * @return
     */
    @Action(value = "list_instances", results = {@Result(name = "listProcessInstances", type = "json", params = {"root", "statusVO"})})
    public String listInstances() {
        List<ProcessInstance> processInstanceList = activitiService.listProcessInstances("leave_process");
        for (ProcessInstance processInstance : processInstanceList) {
            System.out.println(processInstance.getId() + ", " + processInstance.getStartUserId());
        }
        return "listProcessInstances";
    }

    /**
     * 查看原始流程图
     * @return
     */
    @Action("view_proc")
    public String viewProc() {
        InputStream in = activitiService.getDiagramPNG("leave_process");
        WebUtils.outResponse(getResponse(), "image/png", in);
        return null;
    }

    /**
     * 查看生成的流程图
     * @return
     */
    @Action("generate_proc")
    public String generateProc() {
        InputStream in = activitiService.generateDiagramPNG("12513", "leave_process");
        WebUtils.outResponse(getResponse(), "image/png", in);
        return null;
    }

    @Autowired
    public void setProcessService(ProcessService processService) {
        this.processService = processService;
    }

    @Autowired
    public void setActivitiService(ActivitiService activitiService) {
        this.activitiService = activitiService;
    }

    public ProcessVO getProcessVO() {
        return processVO;
    }

    public void setProcessVO(ProcessVO processVO) {
        this.processVO = processVO;
    }

    public File getProcess() {
        return process;
    }

    public void setProcess(File process) {
        this.process = process;
    }

    public String getProcessFileName() {
        return processFileName;
    }

    public void setProcessFileName(String processFileName) {
        this.processFileName = processFileName;
    }

    public String getProcessContentType() {
        return processContentType;
    }

    public void setProcessContentType(String processContentType) {
        this.processContentType = processContentType;
    }

    public ControllerStatusVO getStatusVO() {
        return statusVO;
    }

    public PagerVO<ProcessDTO> getPagerVO() {
        return pagerVO;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
    
}
