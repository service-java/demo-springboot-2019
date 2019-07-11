package top.zywork.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import top.zywork.service.ActivitiService;
import top.zywork.vo.ControllerStatusVO;
import top.zywork.vo.LeaveApplyVO;

/**
 * 请假流程控制器类<br />
 * 创建于2017-10-17
 *
 * @author 王振宇
 * @version 1.0
 */
@Controller
@ParentPackage(value = "base-package")
@Namespace(value = "/leave")
public class LeaveProcessController extends BaseController {

    private static final long serialVersionUID = -6183593753850533980L;
    private Logger logger = LoggerFactory.getLogger(LeaveProcessController.class);

    private ActivitiService activitiService;

    private LeaveApplyVO leaveApplyVO;
    private ControllerStatusVO statusVO;

    @Action(value = "apply", results = {@Result(name = "apply", type = "json", params = {"root", "statusVO"})})
    public String apply() {
        activitiService.startProcess("1", "leave_process");
        statusVO = ControllerStatusVO.okStatus(10000, "已提交请假申请");
        return "apply";
    }

    @Autowired
    public void setActivitiService(ActivitiService activitiService) {
        this.activitiService = activitiService;
    }

    public LeaveApplyVO getLeaveApplyVO() {
        return leaveApplyVO;
    }

    public void setLeaveApplyVO(LeaveApplyVO leaveApplyVO) {
        this.leaveApplyVO = leaveApplyVO;
    }

    public ControllerStatusVO getStatusVO() {
        return statusVO;
    }
}


