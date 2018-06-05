package com.example.u9.myapplication;

/**
 * Created by Mitchell Bryant on 9/22/2017.
 */

public class Course {

    private long courseId;
    private String Name;
    private String Title;
    private String startDate;
    private String endDate;
    private long mentor;
    private String status;
    private String notes;
    private long oAssessment;
    private long pAssessment;
    private String oGoalDate;
    private String pGoalDate;
    private Boolean startAlert;
    private Boolean endAlert;
    private Boolean goalAlert;
    private Boolean goalAlertP;


    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getMentor() {
        return mentor;
    }

    public void setMentor(long mentor) {
        this.mentor = mentor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNotes() {return notes;}

    public void setNotes(String notes) {this.notes = notes;}

    public long getoAssessment() {
        return oAssessment;
    }

    public long getpAssessment() {
        return pAssessment;
    }
    public void setoAssessment(long oAssessment) {
        this.oAssessment = oAssessment;
    }

    public void setpAssessment(long pAssessment) {
        this.pAssessment = pAssessment;
    }

    public String getoGoalDate() {
        return oGoalDate;
    }

    public void setoGoalDate(String oGoalDate) {
        this.oGoalDate = oGoalDate;
    }

    public String getpGoalDate() {
        return pGoalDate;
    }

    public void setpGoalDate(String pGoalDate) {
        this.pGoalDate = pGoalDate;
    }

    public Boolean getStartAlert() {
        return startAlert;
    }

    public void setStartAlert(Boolean startAlert) {
        this.startAlert = startAlert;
    }

    public Boolean getEndAlert() {
        return endAlert;
    }

    public void setEndAlert(Boolean endAlert) {
        this.endAlert = endAlert;
    }

    public Boolean getGoalAlert() {
        return goalAlert;
    }

    public void setGoalAlert(Boolean goalAlert) {
        this.goalAlert = goalAlert;
    }

    public Boolean getGoalAlertP() {
        return goalAlertP;
    }

    public void setGoalAlertP(Boolean goalAlertP) {
        this.goalAlertP = goalAlertP;
    }
}
