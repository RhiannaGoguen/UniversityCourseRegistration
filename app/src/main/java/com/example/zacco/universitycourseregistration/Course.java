package com.example.zacco.universitycourseregistration;

public class Course {

    String name;
    String description;
    int capacity;
    String location;
    String prerequisites;
    String prof;
    String profEmail;
    String semester;
    String time;
    String day;

    public Course(){}

    public Course (String name, String description, int capacity, String location, String prerequisites, String prof, String profEmail, String semester, String time, String day){
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.location = location;
        this.prerequisites = prerequisites;
        this.prof = prof;
        this.profEmail = profEmail;
        this.semester = semester;
        this.time = time;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getProfEmail() {
        return profEmail;
    }

    public void setProfEmail(String profEmail) {this.profEmail = profEmail;}

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTimeSlot() {
        return time;
    }

    public void setTimeSlot(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


    public String toString(){
        String result = "Course Name: "+getName()+"\n" +
                "Description: "+getDescription()+"\n" +
                "Capacity: "+getCapacity()+"\n" +
                "Location: "+getLocation()+"\n" +
                "Prof: "+getProf()+"\n" +
                "Prof Email: "+getProfEmail()+"\n" +
                "Semester: "+getSemester()+"\n" +
                "TimeSlot: "+getTimeSlot()+"\n" +
                "Day: "+getDay()+"\n" +
                "Prerequisites: "+getPrerequisites()+"\n";
        return result;
    }

}
