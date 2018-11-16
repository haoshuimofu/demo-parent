package com.demo.web.shiro.filter;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义Permission访问控制过滤器
 *
 * @Author wude
 * @Create 2017-07-05 18:03
 */
public class PermissionCheckFilter extends PermissionsAuthorizationFilter {

    /**
     * 判断是否允许访问
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws IOException
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        String uri = WebUtils.toHttp(request).getRequestURI();

        Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;
        boolean isPermitted = true;
        try {
            subject.checkPermission(uri);
        } catch (AuthorizationException e) {
            isPermitted = false;
        }
        return isPermitted;

    }

}
