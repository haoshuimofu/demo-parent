package com.demo.web.shiro.session;

import org.apache.shiro.SecurityUtils;

/**
 * Session操作类
 *
 * @author wude
 * @version 1.0.0
 * @create 2018-05-25 13:21
 */
public class SessionHolder {

    public static SessionUser getSessionUser() {
        SessionUser sessionUser = (SessionUser) SecurityUtils.getSubject().getSession().getAttribute(SessionConstants.SESSION_USER_ATTR_KEY);
        if (sessionUser == null) {
            sessionUser = new SessionUser();
        }
        return sessionUser;
    }

    public static void setSessionUser(SessionUser sessionUser) {
        SecurityUtils.getSubject().getSession().setAttribute(SessionConstants.SESSION_USER_ATTR_KEY, sessionUser);
    }

    public static void setAttribute(Object key, Object value) {
        SecurityUtils.getSubject().getSession().setAttribute(key, value);
    }

    public static Object getAttribute(Object key) {
        return SecurityUtils.getSubject().getSession().getAttribute(key);

    }

}