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
        @SerializedName("fname")
        public String firstname;
        @SerializedName("lname")
         public String lastname;
    @SerializedName("gender")
    public String gender;
//first name
        public Nurse() {
        }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Nurse(String nid, String uname, String password, String firstname, String lastname, String gender) {
        this.nid = nid;
        this.uname = uname;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
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
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
