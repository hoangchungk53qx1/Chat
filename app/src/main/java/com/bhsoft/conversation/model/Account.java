package com.bhsoft.conversation.model;

import java.io.Serializable;

public class Account implements Serializable {
    private String userName;
    private String password;

    public Account() {
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
