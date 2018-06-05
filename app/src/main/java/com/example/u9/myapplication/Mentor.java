package com.example.u9.myapplication;

/**
 * Created by Mitchell Bryant on 9/22/2017.
 */

public class Mentor {


    private long mentorId;
    private String Name;
    private String Phone;
    private String Email;
    private long courseId;



    public long getMentorId() {
        return mentorId;
    }

    public void setMentorId(long courseId) {
        this.mentorId = courseId;
    }

    public String getMentorName() {
        return Name;
    }

    public void setMentorName(String name) {
        Name = name;
    }
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }




}
