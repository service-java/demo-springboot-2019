package com.xncoding.pos.controller;

import com.xncoding.pos.dao.TUserMapper;
import com.xncoding.pos.entity.TUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @Autowired
    private TUserMapper tUserMapper;

    private static final Logger _logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/insert")
    @ResponseBody
    public String insert() {

        TUser tUser = new TUser();
        tUser.setPassword("123456").setUsername("HA" + Math.random());
        tUserMapper.insert(tUser);

        _logger.info("插入 {}", tUser);

        return "ok";
    }

}
