package com.example.sogong;

public class BoardCategory {
    private String name;
    private boolean isSelected;
    public BoardCategory(String name,boolean isSelected){
        this.name= name;
        this.isSelected=isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
