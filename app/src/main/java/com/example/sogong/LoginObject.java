package com.example.sogong;

import com.google.gson.annotations.SerializedName;

public class LoginObject {
    @SerializedName("uid")
    private String UserId;
    @SerializedName("password")
    private String password;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginObject(String userId, String password) {
        UserId = userId;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginObject{" +
                "UserId='" + UserId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
