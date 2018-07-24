package com.example.zacco.universitycourseregistration;

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



    public ScheduleTiming(String day, String timeSlot){
        this.day = day;
        this.timeSlot=timeSlot;

    }

    public String toString(){
        return "Day: "+day+"/nTime: "+timeSlot+"/n";
    }



}
