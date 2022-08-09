package com.example.sogong;

import com.google.gson.annotations.SerializedName;

public class PostSending {
    @SerializedName("userId")
    private String userId;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PostSending(String title, String description, String important){
        this.userId = title;
        this.password = description;
        this.email = important;
    }
}