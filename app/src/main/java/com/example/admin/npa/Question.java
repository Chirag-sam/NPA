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
    @SerializedName("diseasetype")
    public String diseasetype;
    public String answer;
    public String score;
    public String maxscore;
    public String getAnswer() {
        return answer;
    }

    public Question(String qid, String qdesc, String answer) {
        this.qid = qid;
        this.qdesc = qdesc;
        this.answer = answer;
    }

    public String getMaxscore() {
        return maxscore;
    }

    public void setMaxscore(String maxscore) {
        this.maxscore = maxscore;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question(String qid, String qdesc, String restype, String diseasetype) {
        this.qid = qid;
        this.qdesc = qdesc;
        this.restype = restype;
        this.diseasetype = diseasetype;
    }

    public String getDiseasetype() {
        return diseasetype;
    }

    public void setDiseasetype(String diseasetype) {
        this.diseasetype = diseasetype;
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
