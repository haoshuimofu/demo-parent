package com.demo.web.controller;

import com.demo.dao.UserDao;
import com.demo.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User控制器
 *
 * @Author Wude
 * @Create 2017-05-12 21:25
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "list")
    public String list() {
        return "user/users";
    }

    @GetMapping(value = "get/{id}")
    @ResponseBody
    public User list(@PathVariable int id) {
        return userDao.selectById(id);
    }
}