package com.example.admin.npa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 3/14/2017.
 */

public class PatientJ {

    @SerializedName("pid")
    public String pid;
    @SerializedName("name")
    public String name;
    @SerializedName("gender")
    public String gender;
    @SerializedName("age")
    public String age;
    @SerializedName("repdate")
    public String repdate;
    @SerializedName("disease")
    public String disease;
    @SerializedName("nid")
    public String nid;
    @SerializedName("status")
    public String status;


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

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PatientJ(String pid, String name, String gender, String age, String repdate, String disease, String nid, String status) {
        this.pid = pid;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.repdate = repdate;
        this.disease = disease;
        this.nid = nid;
        this.status = status;
    }
}

