package com.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Wude on 2017/7/10.
 */
@Controller
@RequestMapping(value = "system")
public class SystemSettingController {

    @RequestMapping(value = "users")
    public String listUser() {
        return "user/users";
    }

    @RequestMapping(value = "roles")
    public String listRole() {
        return "role/roles";
    }
}
