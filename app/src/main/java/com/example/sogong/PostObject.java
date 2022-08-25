package com.example.sogong;

import com.google.gson.annotations.SerializedName;

public class PostObject {
    @SerializedName("postUser")
    private int postUser;
    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private String author;
    @SerializedName("time")
    private String time;
    @SerializedName("category")
    private String category;
    @SerializedName("views")
    private String views;

    public int getPostUser() {
        return postUser;
    }

    public void setPostUser(int postUser) {
        this.postUser = postUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public PostObject(int postUser, String title, String author, String time, String category, String views) {
        this.postUser = postUser;
        this.title = title;
        this.author = author;
        this.time = time;
        this.category = category;
        this.views = views;
    }

    @Override
    public String toString() {
        return "PostObject{" +
                "postUser=" + postUser +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", time='" + time + '\'' +
                ", category='" + category + '\'' +
                ", views='" + views + '\'' +
                '}';
    }

}