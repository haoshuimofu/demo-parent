package com.demo.web.shiro.session;

import java.io.Serializable;

/**
 * Session中用户POJO
 *
 * @Author Wude
 * @Create 2017-05-12 21:37
 */
public class SessionUser implements Serializable {
    private Integer userId;
    private String username;
    private String channel;

    public SessionUser() {
    }

    public SessionUser(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}