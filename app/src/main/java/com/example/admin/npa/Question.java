package com.example.admin.npa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 3/15/2017.
 */

public class Question {


    @SerializedName("qid")
    public String qid;
    @SerializedName("qdesc")
    public String qdesc;
    @SerializedName("restype")
    public String restype;

    public Question(String qid, String qdesc, String restype) {
        this.qid = qid;
        this.qdesc = qdesc;
        this.restype = restype;
    }

    public Question() {
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getQdesc() {
        return qdesc;
    }

    public void setQdesc(String qdesc) {
        this.qdesc = qdesc;
    }

    public String getRestype() {
        return restype;
    }

    public void setRestype(String restype) {
        this.restype = restype;
    }
}
