package com.example.myapplication.model;

public class Details {
    private int Id_User ;
    private  int Id_Food;
    private int Cunt_Food;

    public Details(int id_User, int id_Food, int cunt_Food) {
        Id_User = id_User;
        Id_Food = id_Food;
        Cunt_Food = cunt_Food;
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

    public int getCunt_Food() {
        return Cunt_Food;
    }

    public void setCunt_Food(int cunt_Food) {
        Cunt_Food = cunt_Food;
    }
}
