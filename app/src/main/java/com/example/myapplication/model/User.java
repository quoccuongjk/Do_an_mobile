package com.example.myapplication.model;

public class User {
    private int id;
    private String email;
    private String sdt;
    private String dchi;
    private String name;

    public User() {
    }

    public User(int id,String dchi, String email, String name, String sdt) {
        this.dchi = dchi;
        this.email = email;
        this.name = name;
        this.sdt = sdt;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDchi() {
        return dchi;
    }

    public void setDchi(String dchi) {
        this.dchi = dchi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    public User GetUserByEmail(String str){
//        return
//    }
}
