package com.example.myapplication.model;

public class Food {
    private int id;
    private String name;
    private int image;
    private String describe;

    public Food(int id, String name, int image, String describe) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.describe = describe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
