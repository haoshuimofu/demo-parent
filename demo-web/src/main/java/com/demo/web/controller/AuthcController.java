package com.demo.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.demo.service.ICommonAuthService;
import com.demo.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Wude on 2017/7/9.
 */
@Controller
@RequestMapping(value = "/authc")
public class AuthcController extends BaseController {

    @Autowired
    private ICommonAuthService commonAuthService;

    @RequestMapping(value = "/initMenuTree")
    @ResponseBody
    public JSONArray initMenuTree() {
        return commonAuthService.initMenuTree(getSessionUser().getUserId());
    }

}
