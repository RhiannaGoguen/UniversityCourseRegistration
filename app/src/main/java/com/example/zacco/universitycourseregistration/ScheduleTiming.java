package com.example.zacco.universitycourseregistration;

/**
 * ScheduleTiming class for containing course information relating to the timing of the course.
 * Used for the displaying the student schedule.
 */

public class ScheduleTiming {

    public String name;
    public String day;
    public String timeSlot;


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



    public ScheduleTiming(String day, String timeSlot, String name){
        this.day = day;
        this.timeSlot=timeSlot;
        this.name = name;

    }

    /**
     * The toString for the scheduleTimings.
     * It converts the time slots into understandable times.
     * @return
     */
    public String toString(){
        String time ="";
        if(timeSlot.equals("1")){
            time = "10:00AM-11:00AM";
        }
        else if(timeSlot.equals("2")){
            time = "11:00AM-12:00PM";
        }
        else if(timeSlot.equals("3")){
            time = "1:00PM-2:00PM";
        }
        else if(timeSlot.equals("4")){
            time = "2:00PM-3:00PM";
        }
        return name+"\nTime: "+time+"\n";
    }



}
