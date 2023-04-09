package com.example.myapplication.model;

import androidx.annotation.NonNull;

public class FoodType {
    private int id;
    private String img;
    private String name;


    public FoodType(int id, String name, String img) {
        this.id = id;
        this.img = img;
        this.name = name;
    }

    public FoodType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
