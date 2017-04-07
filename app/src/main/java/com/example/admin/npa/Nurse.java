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
        @SerializedName("nname")
        public String nname;

    @SerializedName("gender")
    public String gender;
    public String lastsync;
//first name
        public Nurse() {
        }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Nurse(String nid, String uname, String password, String nname, String gender, String lastsync) {
        this.nid = nid;
        this.uname = uname;
        this.password = password;
        this.nname = nname;

        this.gender = gender;
        this.lastsync = lastsync;
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
