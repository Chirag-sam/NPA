package com.example.admin.npa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 3/15/2017.
 */

public class ResponseQn {

    @SerializedName("pid")
    public String pid;
    @SerializedName("qid")
    public String qid;
    @SerializedName("answer")
    public String answer;

    public ResponseQn(String pid, String qid, String answer) {
        this.pid = pid;
        this.qid = qid;
        this.answer = answer;
    }

    public ResponseQn() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
