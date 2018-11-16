package com.demo.web.controller.base;

import com.alibaba.fastjson.JSON;
import com.demo.web.shiro.session.SessionHolder;
import com.demo.web.shiro.session.SessionUser;

/**
 * 控制器基类
 *
 * @Author Wude
 * @Create 2017-05-11 22:24
 */
public class BaseController {

    public SessionUser getSessionUser() {
        return SessionHolder.getSessionUser();
    }

    public String toJsonResult(Object result) {
        return JSON.toJSONString(result);
    }
}