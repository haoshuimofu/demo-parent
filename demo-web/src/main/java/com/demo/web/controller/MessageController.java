package com.demo.web.controller;

import com.demo.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Wude on 2017/7/3.
 */
@Controller
@RequestMapping(value = "message")
public class MessageController extends BaseController {


    @RequestMapping(value = "noPermission")
    public String noPermission() {
        return "noPermission";
    }

}
