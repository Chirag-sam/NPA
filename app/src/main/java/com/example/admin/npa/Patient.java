package com.example.admin.npa;

/**
 * Created by Admin on 3/6/2017.
 */

public class Patient {
    int id;
    String name;
    boolean gender;
    String date;
    String address;
    String illness;
    String phone;

    public Patient(int id, String name, boolean gender, String date, String address, String illness, String phone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.date = date;
        this.address = address;
        this.illness = illness;
        this.phone = phone;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
