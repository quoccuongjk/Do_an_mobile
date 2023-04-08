package com.example.myapplication.model;

public class User {
    private String email;
    private String sdt;
    private String dchi;
    private String name;

    public User(String email, String sdt, String dchi, String name) {
        this.email = email;
        this.sdt = sdt;
        this.dchi = dchi;
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setDchi(String dchi) {
        this.dchi = dchi;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getSdt() {
        return sdt;
    }

    public String getDchi() {
        return dchi;
    }

    public String getName() {
        return name;
    }
}
