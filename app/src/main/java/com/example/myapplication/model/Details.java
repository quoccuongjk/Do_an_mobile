package com.example.myapplication.model;

public class Details {
    private int Id_User ;
    private  int Id_Food;
    private String Name_Food;
    private String Image_Food;
    private int Price_Food;
    private int Cunt_Food;

    public Details() {
    }

    public Details(int id_User, int id_Food, String name_Food, int price, int cunt_Food,String image_Food) {
        Id_User = id_User;
        Id_Food = id_Food;
        Name_Food = name_Food;
        Price_Food = price;
        Cunt_Food = cunt_Food;
        Image_Food=image_Food;
    }

    public String getImage_Food() {
        return Image_Food;
    }

    public void setImage_Food(String image_Food) {
        Image_Food = image_Food;
    }

    public int getPrice_Food() {
        return Price_Food;
    }

    public void setPrice_Food(int price_Food) {
        Price_Food = price_Food;
    }

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int id_User) {
        Id_User = id_User;
    }

    public int getId_Food() {
        return Id_Food;
    }

    public void setId_Food(int id_Food) {
        Id_Food = id_Food;
    }

    public String getName_Food() {
        return Name_Food;
    }

    public void setName_Food(String name_Food) {
        Name_Food = name_Food;
    }

    public int getCunt_Food() {
        return Cunt_Food;
    }

    public void setCunt_Food(int cunt_Food) {
        Cunt_Food = cunt_Food;
    }
}
