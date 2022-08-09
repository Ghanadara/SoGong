package com.example.sogong;

import com.google.gson.annotations.SerializedName;

// DTO 모델 - PostResult Class 선언
public class PostResult {


    @SerializedName("id")
    private int id;
    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함
    @SerializedName("title")
    private String title;
    // @SerializedName()로 변수명을 입치시켜주면 클래스 변수명이 달라도 알아서 매핑시켜줌

    @SerializedName("description")
    private String description;

    @SerializedName("created")
    private String created;

    @SerializedName("complete")
    private Boolean complete;

    @SerializedName("important")
    private Boolean important;


    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "PostResult{" +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", created='" + created + '\'' +
                ", complete='" + complete + '\'' +
                ", important='" + important + '\'' +
                '}';
    }
}