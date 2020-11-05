package com.xncoding.pos.entity;

import lombok.Data;

/**
 * Description: 模块功能描述
 * <p>
 * User: luo0412
 * Date: 2020-04-03 13:56
 */
@Data
public class User {

    private String userName;

    private String email;

    public User() {}

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String toString() {
        return "User:{userName=" + this.userName + ",email=" + this.email + "}";
    }

}
