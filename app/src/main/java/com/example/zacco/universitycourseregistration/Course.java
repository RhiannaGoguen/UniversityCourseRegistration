package com.example.zacco.universitycourseregistration;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

import java.util.Map;

/**
 * Used to store course information for a particular course
 */
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
                "Time: "+timeslotToTime(getTimeSlot())+"\n" +
                "Day: "+getDay()+"\n" +
                "Prerequisites: "+getPrerequisites()+"\n";
        return result;
    }

    public static String timeslotToTime(String timeslot){
        String time ="";
        if(timeslot.equals("1")){
            time = "10:00AM-11:00AM";
        }
        else if(timeslot.equals("2")){
            time = "11:00AM-12:00PM";
        }
        else if(timeslot.equals("3")){
            time = "1:00PM-2:00PM";
        }
        else if(timeslot.equals("4")){
            time = "2:00PM-3:00PM";
        }
        return time;
    }

    /**
     * Parses a map into a Course object. Map is usually obtained from a DataSnapshot
     * @param map
     *      Map of course parameters
     * @return
     *      Parsed Course object
     */
    public static Course parse(@NonNull Map<String, Object> map) {
        String name = (String) map.get("Course Name");
        String description = (String) map.get("Description");

        //TODO what's wrong with capacity? When I try to set it, it errors.
        //Is it because it is set to 0 and so it assumes it is set to a
        // null or something like that?
        //int capacity = (int) map.get("Capacity");

        String location = (String) map.get("Location");
        String prerequisites = (String) map.get("Prerequisites");
        String prof = (String) map.get("Prof");
        String profEmail = (String) map.get("Prof Email");
        String semester = (String) map.get("Semester");
        long time = (Long) map.get("TimeSlot");
        String timeSlot = time + "";
        String day = (String) map.get("Day");
        Course c = new Course(name, description, 0, location,
                prerequisites, prof, profEmail, semester, timeSlot, day);
        //System.out.println(c.toString());
        return c;
    }

    public boolean equals(Course c){
        return this.getName().equals(c.getName())
                && this.getDay().equals(c.getDay())
                && this.getCapacity()==c.getCapacity()
                && this.getDescription().equals(c.getDescription())
                && this.getPrerequisites().equals(c.getPrerequisites())
                && this.getProfEmail().equals(c.getProfEmail())
                && this.getProf().equals(c.getProf())
                && this.getTimeSlot().equals(c.getTimeSlot())
                && this.getSemester().equals(c.getSemester())
                && this.getLocation().equals(c.getLocation());
    }

}
