package com.blueice.dataserver.bean;

import java.io.Serializable;

public class WeatherResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Weather data; //天气数据
    private String status; //消息状态
    private String desc; //消息描述

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Weather getData() {
        return data;
    }

    public void setData(Weather data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
