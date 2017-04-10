package com.example.admin.npa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 3/15/2017.
 */

public class Question {
    @SerializedName("pid")
    public String pid;
    @SerializedName("qid")
    public String qid;
    @SerializedName("qdesc")
    public String qdesc;
    @SerializedName("restype")
    public String restype;
    @SerializedName("opscore")
    public String option;
    @SerializedName("surveyid")
    public String surveyid;

    @SerializedName("answer")
    public String answer;
    @SerializedName("score")
    public String score;
    @SerializedName("maxscore")
    public String maxscore;
    public String getAnswer() {
        return answer;
    }

    public Question(String pid, String qid, String qdesc, String answer, String score, String maxscore) {
        this.pid = pid;
        this.qid = qid;
        this.qdesc = qdesc;
        this.answer = answer;
        this.score = score;
        this.maxscore = maxscore;
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



    public Question(String qid, String qdesc, String restype, String option, String surveyid) {
        this.qid = qid;
        this.qdesc = qdesc;
        this.restype = restype;
        this.option = option;
        this.surveyid = surveyid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSurveyid() {
        return surveyid;
    }

    public void setSurveyid(String surveyid) {
        this.surveyid = surveyid;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getDiseasetype() {
        return surveyid;
    }

    public void setDiseasetype(String diseasetype) {
        this.surveyid = diseasetype;
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
