package com.notadeveloper.app.npa;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 3/14/2017.
 */

public class PatientJ {

    @SerializedName("pid")
    private String pid;
    @SerializedName("nurseid")
    private String nurseid;

    @SerializedName("name")
    private String pname;

    @SerializedName("gender")
    private String gender;
    @SerializedName("dob")
    private String dob;
    @SerializedName("illnessID")
    private String disease;
    @SerializedName("surveyid")
    private String surveyid;

    @SerializedName("surveyname")
    private String surveyname;
    @SerializedName("surveydesc")
    private String surveydesc;
    @SerializedName("appdate")
    private String appdate;
    private String status;
    @SerializedName("repdate")
    private String repdate;
    @SerializedName("repscore")
    private String repscore;
    @SerializedName("repmaxscore")
    private String repmaxscore;

    public PatientJ(String pid, String nurseid, String pname, String gender, String dob, String disease, String surveyid, String surveyname, String surveydesc, String appdate, String status, String repdate, String repscore, String repmaxscore) {
        this.pid = pid;
        this.nurseid = nurseid;
        this.pname = pname;
        this.gender = gender;
        this.dob = dob;
        this.disease = disease;
        this.surveyid = surveyid;
        this.surveyname = surveyname;
        this.surveydesc = surveydesc;
        this.appdate = appdate;
        this.status = status;
        this.repdate = repdate;
        this.repscore = repscore;
        this.repmaxscore = repmaxscore;
    }

    public PatientJ() {
    }

    public PatientJ(String pid, String nurseid, String pname, String gender, String dob, String disease, String surveyid, String surveyname, String surveydesc, String appdate, String status) {
        this.pid = pid;
        this.nurseid = nurseid;
        this.pname = pname;
        this.gender = gender;
        this.dob = dob;
        this.disease = disease;
        this.surveyid = surveyid;
        this.surveyname = surveyname;
        this.surveydesc = surveydesc;
        this.appdate = appdate;
        this.status = status;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getSurveydesc() {
        return surveydesc;
    }

    public void setSurveydesc(String surveydesc) {
        this.surveydesc = surveydesc;
    }

    public String getSurveyname() {
        return surveyname;
    }

    public void setSurveyname(String surveyname) {
        this.surveyname = surveyname;
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
        return pname;
    }

    public void setFname(String pname) {
        this.pname = pname;
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

