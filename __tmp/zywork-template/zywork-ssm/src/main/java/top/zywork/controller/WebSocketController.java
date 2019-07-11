package top.zywork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * WebSocket测试用控制器<br />
 * 创建于2017-09-21
 *
 * @author 王振宇
 * @version 1.0
 */
@Controller
@RequestMapping("/websocket")
public class WebSocketController extends BaseController {

    /**
     * 显示socket测试页
     * @return socket测试页
     */
    @RequestMapping("socket_page")
    public String socketPage() {
        return "websocket/socket";
    }
}
