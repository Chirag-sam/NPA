package com.example.admin.npa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 3/15/2017.
 */

public class Disease {

    @SerializedName("name")
    public String name;
    @SerializedName("id")
    public String id;
    @SerializedName("type")
    public String type;

    public Disease(String name, String id, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public Disease() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
