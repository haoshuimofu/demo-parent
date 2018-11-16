package com.demo.domain.bean;

/**
 * Created by Wude on 2017/7/7.
 */
public class ChannelBean {

    private String channelId;
    private String name;

    public ChannelBean() {

    }

    public ChannelBean(String channelId, String name) {
        this.channelId = channelId;
        this.name = name;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
