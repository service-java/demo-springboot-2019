package com.example.demo.controller;

import com.example.demo.domain.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author MrBird
 */
@RestController
@Validated
public class TestController {

    /**
     * 直接在参数里配置
     * @param name
     * @param email
     * @return
     */
    @GetMapping("test1")
    public String test1(
            @NotBlank(message = "{required}") String name,
            @Email(message = "{invalid}") String email) {
        return "success";
    }

    @GetMapping("test2")
    public String test2(@Valid User user) {
        return "success";
    }
}

