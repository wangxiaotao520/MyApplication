package com.myapplication.model;

import java.io.Serializable;

/**
 * Description: 测试eventbus的model
 * Author: wangxiaotao
 * Create: 2018/6/10 9:32
 */
public class ModelEvent implements Serializable {

    private int type;
    private String event;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
