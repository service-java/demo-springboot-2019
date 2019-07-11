package top.zywork.controller;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/process")
public class ProcessController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ProcessController.class);

    private ProcessService processService;
    private ActivitiService activitiService;

    /**
     * 上传流程zip文件
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(ProcessVO processVO, MultipartFile process) {
        processVO.setName(top.zywork.common.FileUtils.getFileNameWithoutExt(process.getOriginalFilename()));
        processVO.setPath(BPMNConstants.BPMN_DIR + "/" + process.getOriginalFilename());
        processVO.setUserId(1L);
        try {
            process.transferTo(new File(top.zywork.common.FileUtils.getBPMNDir() + process.getOriginalFilename()));
            ProcessDTO processDTO = processService.getByName(processVO.getName());
            if (processDTO == null) {
                processService.save(getDozerMapper().map(processVO, ProcessDTO.class));
            }
        } catch (IOException | ServiceException e) {
            throw ExceptionUtils.appException(e);
        }
        return ControllerStatusVO.okStatus(CommonControllerStatusEnum.PROCESS_SAVE_OK.getCode(),
                CommonControllerStatusEnum.PROCESS_SAVE_OK.getMessage());
    }

    /**
     * 分页列出所有已经上传的流程定义文件
     * @return
     */
    @GetMapping("list_page")
    @ResponseBody
    public PagerVO<ProcessVO> listPage() {
        return getDozerMapper().map(processService.listPage(PageQueryUtils.getPageQuery(1)), PagerVO.class);
    }

    /**
     * 部署流程
     * @return
     */
    @GetMapping("deploy/{processName}")
    @ResponseBody
    public ControllerStatusVO deploy(@PathVariable("processName") String processName) {
        activitiService.deploy(processName);
        processService.updateDeploy(new ProcessDeployQuery(null, processName, 1, DateUtils.currentDate()));
        return ControllerStatusVO.okStatus(CommonControllerStatusEnum.PROCESS_DEPLOY_OK.getCode(),
                CommonControllerStatusEnum.PROCESS_DEPLOY_OK.getMessage());
    }

    /**
     * 删除流程部署
     * @return
     */
    @GetMapping("remove/{processName}")
    @ResponseBody
    public ControllerStatusVO remove(@PathVariable("processName") String processName) {
        activitiService.remove(processName);
        processService.updateDeploy(new ProcessDeployQuery(null, processName, 0, null));
        return ControllerStatusVO.okStatus(CommonControllerStatusEnum.PROCESS_REMOVE_OK.getCode(),
                CommonControllerStatusEnum.PROCESS_REMOVE_OK.getMessage());
    }

    /**
     * 列出所有流程定义
     * @return
     */
    @GetMapping("list_definitions")
    @ResponseBody
    public ControllerStatusVO listDefinitions() {
        List<ProcessDefinition> processDefinitionList = activitiService.listProcessDefinitions();
        for (ProcessDefinition processDefinition : processDefinitionList) {
            System.out.println(processDefinition.getId() + ", " + processDefinition.getName() + ", "
                    + processDefinition.getVersion());
        }
        return null;
    }

    /**
     * 列出流程实例
     * @return
     */
    @GetMapping("list_instances")
    @ResponseBody
    public ControllerStatusVO listInstances() {
        List<ProcessInstance> processInstanceList = activitiService.listProcessInstances("leave_process");
        for (ProcessInstance processInstance : processInstanceList) {
            System.out.println(processInstance.getId() + ", " + processInstance.getStartUserId());
        }
        return null;
    }

    /**
     * 查看原始流程图
     * @return
     */
    @GetMapping("view_proc")
    public String viewProc(HttpServletResponse response) {
        InputStream in = activitiService.getDiagramPNG("leave_process");
        WebUtils.outResponse(response, "image/png", in);
        return null;
    }

    /**
     * 查看生成的流程图
     * @return
     */
    @GetMapping("generate_proc")
    public String generateProc(HttpServletResponse response) {
        InputStream in = activitiService.generateDiagramPNG("12513", "leave_process");
        WebUtils.outResponse(response, "image/png", in);
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
    
}
