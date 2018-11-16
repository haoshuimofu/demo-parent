package com.demo.web.controller;

import com.demo.service.IUserService;
import com.demo.web.command.UserCommand;
import com.demo.web.controller.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 *
 * @Author Wude
 * @Create 2017-05-11 22:23
 */

@Controller
public class LoginController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request) {
        return "login";
    }

    /**
     * 登录
     *
     * @param userCommand 登录请求RequestBody
     * @param request     Http请求
     * @return 登录信息
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody UserCommand userCommand, HttpServletRequest request) {

        String username = userCommand.getUsername();
        String password = userCommand.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(false);

        boolean loginSuccess = false;
        String errorMessage = "";

        // 获取当前Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            errorMessage = "未知账户";
        } catch (IncorrectCredentialsException ice) {
            errorMessage = "密码不正确";
        } catch (LockedAccountException lae) {
            errorMessage = "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            errorMessage = "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            errorMessage = "用户名或密码不正确";
            ae.printStackTrace();
        }

        if (currentUser.isAuthenticated()) {
            loginSuccess = true;
            System.out.println(String.format("[%s]用户名密码验证通过，登录成功...", username));
        } else {
            token.clear();
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("loginSuccess", loginSuccess);
        resultMap.put("errorMessage", errorMessage);
        return toJsonResult(resultMap);
    }

    /**
     * 退出登录
     *
     * @param request 请求
     * @return 登录页Url
     */
    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }

}