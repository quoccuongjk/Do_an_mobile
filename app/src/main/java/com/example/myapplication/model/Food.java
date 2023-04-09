package com.example.myapplication.model;

import java.io.Serializable;

public class Food implements Serializable {
    private int id;
    private String name;
    private String image;
    private String describe;
    private int price;
    private int id_food_type;


    public Food(int id, String name, String image, String describe, int price, int id_food_type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.describe = describe;
        this.price = price;
        this.id_food_type = id_food_type;
    }

    public Food() {
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId_food_type() {
        return id_food_type;
    }

    public void setId_food_type(int id_food_type) {
        this.id_food_type = id_food_type;
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


    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
