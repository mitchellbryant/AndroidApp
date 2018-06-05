package com.example.u9.myapplication;

/**
 * Created by Mitchell Bryant on 9/21/2017.
 */

public class Term {

    private long termId;
    private String termName;
    private String termStartDate;
    private String termEndDate;
    private String courses;

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public long getTermNo() {
        return termId;
    }

    public void setTermNo(long termNo) {
        this.termId = termNo;
    }

    public String getTermName() {return termName;}

    public void setTermName(String termName) {this.termName = termName;}

    public String getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(String termStartDate) {
        this.termStartDate = termStartDate;
    }

    public String getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(String termEndDate) {
        this.termEndDate = termEndDate;
    }



}
