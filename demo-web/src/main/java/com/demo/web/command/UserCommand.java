package com.demo.web.command;

/**
 * User Command
 *
 * @Author Wude
 * @Create 2017-05-12 21:30
 */
public class UserCommand {

    private String username;
    private String password;
    private String label;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}