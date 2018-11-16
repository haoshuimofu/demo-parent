package com.demo.web.controller;

import com.demo.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Index控制器
 *
 * @Author Wude
 * @Create 2017-05-12 22:08
 */
@Controller
@RequestMapping(value = "index")
public class IndexController extends BaseController {

    @RequestMapping(value = "view")
    public String indexPage() {
        return "index";
    }


    @RequestMapping(value = "init")
    @ResponseBody
    public String init() {
        return "haha";
    }


}