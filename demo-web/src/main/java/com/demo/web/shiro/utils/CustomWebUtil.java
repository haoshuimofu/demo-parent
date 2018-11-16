package com.demo.web.shiro.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义Web工具类
 *
 * @author wude
 * @version 1.0.0
 * @create 2018-05-17 14:29
 */
public class CustomWebUtil {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

}