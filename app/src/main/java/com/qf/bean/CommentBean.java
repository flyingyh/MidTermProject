package com.qf.bean;

/**
 * Created by Administrator on 2016/11/24.
 */

public class CommentBean {
    private String username;
    private String floor;
    private String msg;

    public CommentBean(String username, String floor, String msg) {
        this.username = username;
        this.floor = floor;
        this.msg = msg;
    }

    public CommentBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CommentBean{" +
                "username='" + username + '\'' +
                ", floor='" + floor + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
