package com.example.zacco.universitycourseregistration;

/**
 * ScheduleTiming class for containing course information relating to the timing of the course.
 * Used for the displaying the student schedule.
 */

public class ScheduleTiming {

    public String name;
    public String day;
    public String timeSlot;
    public String semester;


    public String getSemester(){ return semester;}
    public void setSemester(String s){this.semester=s;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.day = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }



    public ScheduleTiming(String day, String timeSlot, String name, String semester){
        this.day = day;
        this.timeSlot=timeSlot;
        this.name = name;
        this.semester = semester;

    }

    /**
     * The toString for the scheduleTimings.
     * @return
     *      String with converted time.
     */
    public String toString(){
        String time = Course.timeslotToTime(this.getTimeSlot());
        return name+"\nTime: "+time+"\n";
    }



}
