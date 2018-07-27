package com.example.zacco.universitycourseregistration;

public class Student {
    public String courseId;
    public String studentId;

    //constructor
    public Student(){

    }
    public Student(String courseId, String studentId){
        this.courseId= courseId;
        this.studentId=studentId;
    }
    // getter and setters
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }



}
