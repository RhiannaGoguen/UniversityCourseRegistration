package com.example.zacco.universitycourseregistration;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class Course_Test {

    Course testCourse= new Course("n","d",0,"l","pr","p","pe","se","1","M");

    /**
     * This test asserts that the creation method of the Course class assigns all the variables correctly.
     */
    @Test
    public void courseCreateSuccess(){
        Course c = new Course("name","desc",1,"loc","prereq","prof","email","semester","time","day");
        assertEquals(c.getName(), "name");
        assertEquals(c.getDescription(), "desc");
        assertEquals(c.getCapacity(), 1);
        assertEquals(c.getLocation(), "loc");
        assertEquals(c.getPrerequisites(), "prereq");
        assertEquals(c.getProf(), "prof");
        assertEquals(c.getProfEmail(), "email");
        assertEquals(c.getSemester(), "semester");
        assertEquals(c.getTimeSlot(), "time");
        assertEquals(c.getDay(), "day");

    }

    /**
     * This test checks that parsing a course is functional
     */
    @Test
    public void parseSuccess(){
         Map<String, Object> map = new HashMap<String, Object>() {{
            put("Course Name", "n");
            put("Description", "d");
            put("Capacity", 0);
            put("Location", "l");
            put("Prerequisites", "pr");
            put("Prof", "p");
            put("Prof Email", "pe");
            put("Semester", "se");
            put("TimeSlot", (long) 1);
            put("Day", "M");
         }};

         Course c = Course.parse(map);

         assertTrue(c.equals(testCourse));
    }

    /**
     * This test checks that the equals method will fail when it should
     */
    @Test
    public void equalsFail(){
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("Course Name", "name"); //is different than testCourse's
            put("Description", "d");
            put("Capacity", 0);
            put("Location", "l");
            put("Prerequisites", "pr");
            put("Prof", "p");
            put("Prof Email", "pe");
            put("Semester", "se");
            put("TimeSlot", (long) 1);
            put("Day", "M");
        }};

        Course c = Course.parse(map);

        assertFalse(c.equals(testCourse));
    }
}
