package com.demo.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 系统环境变量Bean
 *
 * @Author wude
 * @Create 2017-06-02 16:46
 */
@Component
public class SystemEnvBean {

    @Value("#{envConfig['env']}")
    private String env;

    @PostConstruct
    private void init() {
        System.out.println("当前环境: " + env);
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}
