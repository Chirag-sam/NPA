package com.example.admin.npa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by krsnv on 13-Mar-17.
 */

public class Nurse {
        @SerializedName("ID")
        public String nid;
        // @SerializedName("uname")
        public String uname;
         //@SerializedName("password")
        public String password;
        @SerializedName("name")
        public String nname;

    @SerializedName("gender")
    public String gender;
    public String lastsync;
    @SerializedName("tcs")
    public String tcs;
    @SerializedName("hosplogo")
    public String hosplogo;
    @SerializedName("hospname")
    public String hospname;
//first name
        public Nurse() {
        }

    public String getTcs() {
        return tcs;
    }

    public void setTcs(String tcs) {
        this.tcs = tcs;
    }

    public String getHosplogo() {
        return hosplogo;
    }

    public void setHosplogo(String hosplogo) {
        this.hosplogo = hosplogo;
    }

    public String getHospname() {
        return hospname;
    }

    public void setHospname(String hospname) {
        this.hospname = hospname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public Nurse(String nid, String uname, String password, String nname, String gender, String lastsync, String tcs, String hosplogo, String hospname) {
        this.nid = nid;
        this.uname = uname;
        this.password = password;
        this.nname = nname;
        this.gender = gender;
        this.lastsync = lastsync;
        this.tcs = tcs;
        this.hosplogo = hosplogo;
        this.hospname = hospname;
    }
//    public Nurse(String nid, String uname, String password, String nname, String lastname, String gender) {
//        this.nid = nid;
//        this.uname = uname;
//        this.password = password;
//        this.nname = nname;
//        this.lastname = lastname;
//        this.gender = gender;
//    }

    public String getLastsync() {
        return lastsync;
    }

    public void setLastsync(String lastsync) {
        this.lastsync = lastsync;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return nname;
    }

    public void setFirstname(String nname) {
        this.nname = nname;
    }

}
