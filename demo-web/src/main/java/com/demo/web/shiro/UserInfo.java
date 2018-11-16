package com.demo.web.shiro;

import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;

/**
 * @author dell
 * @version 1.0.0
 * @create 2018-05-17 17:03
 */
public class UserInfo implements Serializable, AuthCachePrincipal {

    private String username;

    private Integer age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String getAuthCacheKey() {
        return getUsername();
    }
}