package com.demo.web.shiro;

import com.demo.domain.model.Resource;
import com.demo.domain.model.Role;
import com.demo.domain.model.User;
import com.demo.service.ICommonAuthService;
import com.demo.service.IUserService;
import com.demo.web.shiro.session.SessionUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.demo.web.shiro.session.SessionConstants.SESSION_USER_ATTR_KEY;


/**
 * 自定义认证授权Realm
 *
 * @Author Wude
 * @Create 2017-07-11 21:49
 */
public class DemoRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(DemoRealm.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private ICommonAuthService commonAuthService;

    /**
     * 为当前登录的Subject授予角色和权限，验证角色或权限时调用
     *
     * @return Subject授权信息，返回null时跳转到unauthorizedUrl
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
        // (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principals);
            SecurityUtils.getSubject().logout();
            return null;
        }
        // 从Realm获取Principal, Authentication时存放的是username
        UserInfo userInfo = (UserInfo) super.getAvailablePrincipal(principals);
        String currentUsername = userInfo.getAuthCacheKey();
        if (currentUsername != null) {
            logger.info("用户({})正在授权认证...", currentUsername);
            // 用户已经登录, 直接从Session获取userId
            SessionUser sessionUser = (SessionUser) SecurityUtils.getSubject().getSession().getAttribute(SESSION_USER_ATTR_KEY);
            if (sessionUser == null || sessionUser.getUserId() == null) {
                doClearCache(principals);
                throw new AuthenticationException(String.format("用户(%s)未登录, 授权认证失败!", currentUsername));
            }
            // 角色和权限
            List<String> roleList = new ArrayList<String>();
            List<String> permissionList = new ArrayList<String>();

            List<Role> userRoleList = commonAuthService.getUserRoleList(sessionUser.getUserId());
            if (userRoleList != null && userRoleList.size() > 0) {
                for (Role role : userRoleList) {
                    roleList.add(role.getRoleName());
                }
            }

            List<Resource> userPermissionList = commonAuthService.getUserPermissionList(sessionUser.getUserId());
            if (userPermissionList != null && userPermissionList.size() > 0) {
                for (Resource permission : userPermissionList) {
                    if (permission.getResPath() != null) {
                        permissionList.add(permission.getResPath());
                    }
                }
            }

            SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
            simpleAuthorInfo.addRoles(roleList);
            simpleAuthorInfo.addStringPermissions(permissionList);
            return simpleAuthorInfo;
        }
        return null;
    }

    /**
     * Subject.login时调用,返回当前Subject的认证信息
     *
     * @param authcToken 认证令牌
     * @return 验证通过时返回当前Subject认证信息, 返回null时subject.login会抛UnknownAccountException异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        // 用户login是提交的是UsernamePasswordToken, 强制获取用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        // System.out.println(String.format("[%s]登录认证: %s", username, ReflectionToStringBuilder.toString(authcToken, ToStringStyle.MULTI_LINE_STYLE)));
        logger.info("用户({})登录, 正在进行登录认证...", username);

        // 根据用户名查询用户
        User user = userService.getByUsername(username);
        if (user != null) {
            /**
             * principal: 用户名
             * credentials: 凭证(密码)
             * realmName: currentRealName
             */
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(username);
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(userInfo, user.getPassword(), getName());

            SessionUser sessionUser = new SessionUser();
            sessionUser.setUserId(user.getId());
            sessionUser.setUsername(user.getUsername());
            SecurityUtils.getSubject().getSession().setAttribute(SESSION_USER_ATTR_KEY, sessionUser);
            return authcInfo;
        }
        return null;
    }

}