package com.example.myapplication.model;

public class Details {
    private String Gmail_User ;
    private  int Id_Food;
    private int Cunt_Food;

    public Details(String gmail_User, int id_Food, int cunt_Food) {
        Gmail_User = gmail_User;
        Id_Food = id_Food;
        Cunt_Food = cunt_Food;
    }

    public String getGmail_User() {
        return Gmail_User;
    }

    public void setGmail_User(String gmail_User) {
        Gmail_User = gmail_User;
    }

    public int getId_Food() {
        return Id_Food;
    }

    public void setId_Food(int id_Food) {
        Id_Food = id_Food;
    }

    public int getCunt_Food() {
        return Cunt_Food;
    }

    public void setCunt_Food(int cunt_Food) {
        Cunt_Food = cunt_Food;
    }
}
