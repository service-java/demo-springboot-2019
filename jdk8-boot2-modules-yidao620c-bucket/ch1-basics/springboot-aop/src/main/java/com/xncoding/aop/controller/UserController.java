package com.xncoding.aop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xncoding.aop.annotation.UserAccess;

/**
 * Description:
 */
@RestController
public class UserController {
    @RequestMapping("/first")
    public Object first() {
        return "first controller";
    }

    @RequestMapping("/second")
    @UserAccess(desc = "second")
    public Object second() {
        return "second controller";
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }

}
