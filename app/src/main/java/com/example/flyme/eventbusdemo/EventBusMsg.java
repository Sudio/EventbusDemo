package com.example.flyme.eventbusdemo;

/**
 * Created by Flyme on 2016/1/18.
 */
public class EventBusMsg {
    private String method;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EventBusMsg(String method, String content) {
        this.method = method;
        this.content = content;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
