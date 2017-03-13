package com.example.admin.npa;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by krsnv on 13-Mar-17.
 */

public class Nurse {

        @SerializedName("name")
        public String name;
        @SerializedName("id")
        public String id;
        @SerializedName("avail")
        public String avail;
        @SerializedName("notasks")
        public String notasks;
        @SerializedName("taskcomp")
        public String taskcomp;
        @SerializedName("taskpend")
        public String taskpend;
        @SerializedName("reportdate")
        public String reportdate;

        public Nurse() {
        }

        public Nurse(String name, String id, String avail, String notasks, String taskcomp, String taskpend, String reportdate) {
            this.name = name;
            this.id = id;
            this.avail = avail;
            this.notasks = notasks;
            this.taskcomp = taskcomp;
            this.taskpend = taskpend;
            this.reportdate = reportdate;
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

        public String getAvail() {
            return avail;
        }

        public void setAvail(String avail) {
            this.avail = avail;
        }

        public String getNotasks() {
            return notasks;
        }

        public void setNotasks(String notasks) {
            this.notasks = notasks;
        }

        public String getTaskcomp() {
            return taskcomp;
        }

        public void setTaskcomp(String taskcomp) {
            this.taskcomp = taskcomp;
        }

        public String getTaskpend() {
            return taskpend;
        }

        public void setTaskpend(String taskpend) {
            this.taskpend = taskpend;
        }

        public String getReportdate() {
            return reportdate;
        }

        public void setReportdate(String reportdate) {
            this.reportdate = reportdate;
        }

}
