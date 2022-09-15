package com.example.sogong;

import com.google.gson.annotations.SerializedName;

public class LoginObject {
    @SerializedName("uid")
    private String UserId;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("password")
    private String password;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginObject(String userId, String nickname, String password) {
        this.UserId = userId;
        this.nickname = nickname;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginObject{" +
                "UserId='" + UserId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
