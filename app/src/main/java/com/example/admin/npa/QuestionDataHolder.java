package com.example.admin.npa;

import com.google.gson.annotations.SerializedName;

/**
 * Created by krsnv on 15-Mar-17.
 */

public class QuestionDataHolder {

    public String qid;
    public String qdesc;
    public String restype;
    public String answer;

    public QuestionDataHolder() {
    }

    public QuestionDataHolder(String qid, String qdesc, String restype, String answer) {
        this.qid = qid;
        this.qdesc = qdesc;
        this.restype = restype;
        this.answer = answer;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
