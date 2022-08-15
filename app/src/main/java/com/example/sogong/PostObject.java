package com.example.sogong;

import com.google.gson.annotations.SerializedName;

public class PostObject {
    @SerializedName("title")
    private String title;
    @SerializedName("complete")
    private boolean complete;
    @SerializedName("important")
    private boolean important;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public PostObject(String title, boolean complete, boolean important){
        this.title = title;
        this.complete = complete;
        this.important = important;
    }
    @Override
    public String toString() {
        return "PostResult{" +
                ", title='" + title + '\'' +
                ", complete='" + complete + '\'' +
                ", important='" + important + '\'' +
                '}';
    }

}