package com.xncoding.pos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    private static final Logger _logger = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping("/demo")
    @ResponseBody
    public String demo() {
        return "welcome";
    }

}
