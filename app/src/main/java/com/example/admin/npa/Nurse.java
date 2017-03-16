package com.example.admin.npa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by krsnv on 13-Mar-17.
 */

public class Nurse {
        @SerializedName("nid")
        public String nid;
         @SerializedName("uname")
        public String uname;
         @SerializedName("password")
        public String password;
        @SerializedName("name")
        public String name;
        @SerializedName("lastsync")
        public String lastsync;

        public Nurse() {
        }

    public Nurse(String nid, String uname, String password, String name, String lastsync) {
        this.nid = nid;
        this.uname = uname;
        this.password = password;
        this.name = name;
        this.lastsync = lastsync;
    }

    public String getNid() {
        return nid;
    }

    public String getUname() {
        return uname;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastsync() {
        return lastsync;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastsync(String lastsync) {
        this.lastsync = lastsync;
    }
}
