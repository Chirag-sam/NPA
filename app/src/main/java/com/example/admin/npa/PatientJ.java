package com.example.admin.npa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 3/14/2017.
 */

public class PatientJ {

    @SerializedName("pid")
    public String pid;
    @SerializedName("nurseid")
    public String nurseid;

    @SerializedName("fname")
    public String fname;
    @SerializedName("lname")
    public String lname;
    @SerializedName("gender")
    public String gender;
    @SerializedName("dob")
    public String dob;
    @SerializedName("diseaseid")
    public String disease;
    @SerializedName("surveyid")
    public String surveyid;
    @SerializedName("appdate")
    public String appdate;
    public String status;
    @SerializedName("repdate")
    public String repdate;
    @SerializedName("repscore")
    public String repscore;
    @SerializedName("repmaxscore")
    public String repmaxscore;

    public PatientJ(String pid, String nurseid, String fname, String lname, String gender, String dob, String disease, String surveyid, String appdate, String status, String repdate, String repscore, String repmaxscore) {
        this.pid = pid;
        this.nurseid = nurseid;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.dob = dob;
        this.disease = disease;
        this.surveyid = surveyid;
        this.appdate = appdate;
        this.status = status;
        this.repdate = repdate;
        this.repscore = repscore;
        this.repmaxscore = repmaxscore;
    }

    public String getRepmaxscore() {
        return repmaxscore;
    }

    public void setRepmaxscore(String repmaxscore) {
        this.repmaxscore = repmaxscore;
    }

    public String getRepdate() {
        return repdate;
    }

    public void setRepdate(String repdate) {
        this.repdate = repdate;
    }

    public String getRepscore() {
        return repscore;
    }

    public void setRepscore(String repscore) {
        this.repscore = repscore;
    }

    public String getSurveyid() {
        return surveyid;
    }

    public void setSurveyid(String surveyid) {
        this.surveyid = surveyid;
    }


    public PatientJ() {
    }



    public PatientJ(String pid, String nurseid, String fname, String lname, String gender, String dob, String disease, String surveyid, String appdate, String status) {
        this.pid = pid;
        this.nurseid = nurseid;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.dob = dob;
        this.disease = disease;
        this.surveyid = surveyid;
        this.appdate = appdate;
        this.status = status;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNurseid() {
        return nurseid;
    }

    public void setNurseid(String nurseid) {
        this.nurseid = nurseid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getAppdate() {
        return appdate;
    }

    public void setAppdate(String appdate) {
        this.appdate = appdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

