package com.recruit.entity;

import java.io.Serializable;

public class UserWithCode implements Serializable {
    private User user; // 用户
    private String code; // 激活码

    public UserWithCode() {

    }

    public UserWithCode(User user, String code) {
        this.user = user;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public User getUser() {
        return user;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
