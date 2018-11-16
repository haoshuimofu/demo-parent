package com.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User控制器
 *
 * @Author Wude
 * @Create 2017-05-12 21:25
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @RequestMapping(value = "list")
    public String list() {
        return "user/users";
    }
}