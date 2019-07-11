package top.zywork.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

/**
 * WebSocket测试用控制器<br />
 * 创建于2017-09-21
 *
 * @author 王振宇
 * @version 1.0
 */
@Controller
@ParentPackage(value = "base-package")
@Namespace(value = "/websocket")
public class WebSocketController extends BaseController {
    private static final long serialVersionUID = 4314529153953195464L;

    /**
     * 显示socket测试页
     * @return socket测试页
     */
    @Action(value = "socket_page", results = {@Result(name = "socketPage", location = "/WEB-INF/views/websocket/socket.jsp")})
    public String socketPage() {
        return "socketPage";
    }
}
