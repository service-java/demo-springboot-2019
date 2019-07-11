package top.zywork.controller;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

/**
 * 用于显示主页的控制器<br />
 * 创建于2017-08-28
 *
 * @author 王振宇
 * @version 1.0
 */
@Controller
@ParentPackage(value = "base-package")
@Namespace(value = "/")
public class IndexController extends ActionSupport {
    private static final long serialVersionUID = -4485014037534737662L;

    @Action(value = "index", results = {@Result(name = "success", location = "/WEB-INF/views/index.jsp")})
    public String execute() {
        return SUCCESS;
    }
}
