package com.example.admin.npa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 3/14/2017.
 */

public class PatientJ {

    @SerializedName("name")
    public String name;
    @SerializedName("pid")
    public String pid;
    @SerializedName("repdate")
    public String repdate;
    @SerializedName("gender")
    public String gender;
    @SerializedName("age")
    public String age;

    public PatientJ(String name, String pid, String repdate, String gender, String age) {
        this.name = name;
        this.pid = pid;
        this.repdate = repdate;
        this.gender = gender;
        this.age = age;
    }

    public PatientJ() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRepdate() {
        return repdate;
    }

    public void setRepdate(String repdate) {
        this.repdate = repdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

