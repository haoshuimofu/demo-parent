package com.demo.web.beans;

import java.io.Serializable;

/**
 * 控制器Json格式返回结果
 *
 * @Author Wude
 * @Create 2017-05-12 21:58
 */
public class JsonResult implements Serializable {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}