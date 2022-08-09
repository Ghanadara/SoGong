package com.example.sogong;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class data_model {
    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;


    public String getUserId(){
        return userId;
    }

    public String getID(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }
}