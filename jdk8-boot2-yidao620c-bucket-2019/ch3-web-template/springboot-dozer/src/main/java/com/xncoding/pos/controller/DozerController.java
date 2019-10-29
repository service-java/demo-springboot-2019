package com.xncoding.pos.controller;


import cn.hutool.core.lang.Console;
import com.xncoding.pos.entity.DozerContacterDO;
import com.xncoding.pos.entity.DozerContacterVO;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DozerController {

    private static final Logger _logger = LoggerFactory.getLogger(DozerController.class);

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @RequestMapping("/dozer")
    @ResponseBody
    public DozerContacterDO demo() {

        DozerContacterVO dozerContacterVO = new DozerContacterVO();
        dozerContacterVO.setAge(11).setName("peter")
                .setLocation("Hangzhou")
                .setPhone("7777").setSex("male");

        // 不是web
        DozerContacterDO contacterDO = dozerBeanMapper.map(dozerContacterVO, DozerContacterDO.class);
        Console.log(contacterDO);
        return contacterDO;
    }

}
