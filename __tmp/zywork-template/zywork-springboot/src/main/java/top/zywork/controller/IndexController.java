package top.zywork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页控制器类<br />
 * 创建于2017-08-31
 * @author 王振宇
 * @version 1.0
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public String index() {
        return "index";
    }

}
