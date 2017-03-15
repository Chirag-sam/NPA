package com.example.admin.npa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 3/15/2017.
 */

public class Result {


    @SerializedName("pid")
    public String pid;
    @SerializedName("riskscore")
    public String riskscore;
    @SerializedName("scorerange")
    public String scorerange;

    public Result() {
    }

    public Result(String pid, String riskscore, String scorerange) {
        this.pid = pid;
        this.riskscore = riskscore;
        this.scorerange = scorerange;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRiskscore() {
        return riskscore;
    }

    public void setRiskscore(String riskscore) {
        this.riskscore = riskscore;
    }

    public String getScorerange() {
        return scorerange;
    }

    public void setScorerange(String scorerange) {
        this.scorerange = scorerange;
    }
}
