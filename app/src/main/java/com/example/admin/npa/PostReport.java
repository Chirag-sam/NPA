package com.example.admin.npa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by krsnv on 30-Mar-17.
 */

public class PostReport {
    @SerializedName("Patient")
    List<PatientJ> mPatientJs;
    @SerializedName("Questions")
    List<Question>response;

    public PostReport(List<PatientJ> patientJs, List<Question> response) {
        mPatientJs = patientJs;
        this.response = response;
    }

    public PostReport() {
    }

    public List<PatientJ> getPatientJs() {
        return mPatientJs;
    }

    public void setPatientJs(List<PatientJ> patientJs) {
        mPatientJs = patientJs;
    }

    public List<Question> getResponse() {
        return response;
    }

    public void setResponse(List<Question> response) {
        this.response = response;
    }
}
