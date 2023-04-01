package com.example.myapplication;

public class ProductType {
    private int id;
    private String title;
    private int image;

    public ProductType(int id, String title, int image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
