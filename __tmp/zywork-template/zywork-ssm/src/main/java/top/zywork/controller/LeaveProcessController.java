package top.zywork.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/leave")
public class LeaveProcessController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(LeaveProcessController.class);

    private ActivitiService activitiService;

    @GetMapping("apply")
    @ResponseBody
    public ControllerStatusVO apply(LeaveApplyVO leaveApplyVO) {
        activitiService.startProcess("1", "leave_process");
        return ControllerStatusVO.okStatus(10000, "已提交请假申请");
    }

    @Autowired
    public void setActivitiService(ActivitiService activitiService) {
        this.activitiService = activitiService;
    }
}


