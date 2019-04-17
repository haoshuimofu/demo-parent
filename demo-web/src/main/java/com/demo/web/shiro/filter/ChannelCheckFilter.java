package com.demo.web.shiro.filter;

import com.demo.web.shiro.session.SessionConstants;
import com.demo.web.shiro.session.SessionUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义channel访问控制过滤器
 *
 * @Author wude
 * @Create 2017-07-05 18:03
 */
public class ChannelCheckFilter extends AccessControlFilter {

    private static final Logger logger = LoggerFactory.getLogger(ChannelCheckFilter.class);

    /**
     * 重定向URL,如果channel check不通过时跳转的URL
     */
    private String redirectUrl;

    /**
     * 判断是否允许访问
     *
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        logger.info("");
        Session session = this.getSubject(servletRequest, servletResponse).getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute(SessionConstants.SESSION_USER_ATTR_KEY);
        return sessionUser != null && sessionUser.getChannel() != null;
    }

    /**
     * 访问被拒时处理逻辑,跳转到redirectUrl
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = this.getSubject(servletRequest, servletResponse);
        System.out.println(String.format("[%s]访问[%s]被拒: 登录后未选择Channel.", subject.getPrincipal(), WebUtils.toHttp(servletRequest).getRequestURI()));
        saveRequest(servletRequest);
        if (redirectUrl == null)
            redirectUrl = getLoginUrl();
        WebUtils.issueRedirect(servletRequest, servletResponse, redirectUrl);
        return false;
    }


    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
