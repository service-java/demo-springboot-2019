package top.zywork.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import top.zywork.enums.CommonControllerStatusEnum;
import top.zywork.vo.AdminLoginVO;
import top.zywork.vo.ControllerStatusVO;

/**
 * 管理员控制器<br />
 * 创建于2017-09-12
 *
 * @author 王振宇
 * @version 1.0
 */
@Controller
@ParentPackage(value = "base-package")
@Namespace(value = "/admin")
public class AdminController extends BaseController {
    private static final long serialVersionUID = 569702540698440409L;

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    private AdminLoginVO loginVO;

    private ControllerStatusVO statusVO;

    /**
     * 显示登录页
     * @return 登录页面
     */
    @Action(value = "login_page", results = {@Result(name = "loginPage", location = "/WEB-INF/views/admin/login.jsp")})
    public String loginPage() {
        return "loginPage";
    }

    /**
     * 登录操作
     * @return 登录操作结果对应的json字符串
     */
    @Action(value = "login", results = {@Result(name = "login", type = "json", params = {"root", "statusVO"})})
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginVO.getAccount(), loginVO.getPassword());
        statusVO = ControllerStatusVO.okStatus(CommonControllerStatusEnum.USER_LOGIN_OK.getCode(),
                CommonControllerStatusEnum.USER_LOGIN_OK.getMessage());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            logger.info("用户登录失败，登录名或密码错误");
            statusVO = ControllerStatusVO.errorStatus(CommonControllerStatusEnum.USER_LOGIN_ERROR.getCode(),
                    CommonControllerStatusEnum.USER_LOGIN_ERROR.getMessage());
        }
        return "login";
    }

    public AdminLoginVO getLoginVO() {
        return loginVO;
    }

    public void setLoginVO(AdminLoginVO loginVO) {
        this.loginVO = loginVO;
    }

    public ControllerStatusVO getStatusVO() {
        return statusVO;
    }

}
