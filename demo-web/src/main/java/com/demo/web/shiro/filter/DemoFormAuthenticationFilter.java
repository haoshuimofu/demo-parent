package com.demo.web.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.demo.web.shiro.utils.CustomWebUtil;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录认证过滤器
 *
 * @Author wude
 * @Create 2017-07-05 18:33
 */
public class DemoFormAuthenticationFilter extends FormAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(DemoFormAuthenticationFilter.class);

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        return super.onLoginSuccess(token, subject, request, response);
    }

    /**
     * 当访问被拒绝：Subject未登录时访问了需要authc的资源
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        logger.info("用户未登录请求资源[{}]被拒...", httpServletRequest.getRequestURI());

        if (this.isLoginRequest(request, response)) {
            if (this.isLoginSubmission(request, response)) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login submission detected.  Attempting to execute login.");
                }

                return this.executeLogin(request, response);
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login page view.");
                }

                return true;
            }
        } else {
            if (logger.isTraceEnabled()) {
                logger.trace("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [" + this.getLoginUrl() + "]");
            }
            // 如果是异步请求, 直接返回内容
            if (CustomWebUtil.isAjaxRequest(httpServletRequest)) {
                saveRequest(request);
                // response.setContentType();
                PrintWriter writer = response.getWriter();
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("errorCode", "-9999");
                dataMap.put("message", "未登录或登录超时, 请重新登录!");
                writer.write(JSON.toJSONString(dataMap));
            } else {
                this.saveRequestAndRedirectToLogin(request, response);
            }

            return false;
        }
    }

}
