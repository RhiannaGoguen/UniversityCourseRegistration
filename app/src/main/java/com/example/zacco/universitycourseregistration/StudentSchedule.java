package com.example.zacco.universitycourseregistration;

/**
 * This class displays the logged in student's registered classes.
 */

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentSchedule extends ListActivity {

    private DatabaseReference dbReferenceStudentCourses;
    private DatabaseReference dbReferenceAllCourses;
    private FirebaseAuth auth;
    private String[] courseList;
    private ArrayList<ScheduleTiming> timings;

    ScheduleTiming[][] mon = new ScheduleTiming[4][2];
    ScheduleTiming[][] tues = new ScheduleTiming[4][2];
    ScheduleTiming[][] wed = new ScheduleTiming[4][2];
    ScheduleTiming[][] thurs = new ScheduleTiming[4][2];
    ScheduleTiming[][] fri = new ScheduleTiming[4][2];

    String[] finalFallSchedule = new String[5];
    String[] finalWinterSchedule = new String[5];
    Button fall;
    Button winter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule);

        auth = FirebaseAuth.getInstance();
        dbReferenceStudentCourses = FirebaseDatabase.getInstance().getReference("Students/"+auth.getUid()+"/Courses");
        //dbReferenceStudentCourses = FirebaseDatabase.getInstance().getReference("Students/"+"Student1"+"/Courses");
        dbReferenceAllCourses = FirebaseDatabase.getInstance().getReference("Course");
        timings = new ArrayList<ScheduleTiming>();

        getUserCourses();

        fall = (Button) findViewById(R.id.fall);
        winter = (Button) findViewById(R.id.winter);

        fall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateListView(finalFallSchedule);
            }
        });

        winter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateListView(finalWinterSchedule);
            }
        });


    }

    /**
     * This method will take a map of Objects and convert them into a String
     * array.
     * @param map
     *      Map of objects to be converted into Strings
     * @return
     *      String array of the value of the Objects
     */
    public String[] getArray(Map<String, Object> map) {
        String[] currentClasses = new String[map.size()];
        int i = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            currentClasses[i] = (String) entry.getValue();
            Log.w("check",currentClasses[i]);
            i++;
        }
        return currentClasses;
    }

    public String[] getArray(List<Object> list) {
        String[] currentClasses = new String[list.size()];
        int i = 0;
        for(int k=0; k<list.size(); k++){
            currentClasses[i] = list.get(k).toString();
            Log.w("check",currentClasses[i]);
            i++;
        }
        return currentClasses;
    }

    /**
     * This method calls the getArray method to convert the Database snapshot of the current
     * students courses into a String array and stores it in the courseList array.
     */
    public void getUserCourses(){
        dbReferenceStudentCourses.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (dataSnapshot.getValue() instanceof List) {
                    List<Object> courses = (List<Object>) dataSnapshot.getValue();
                    courseList = getArray(courses);
                } else {
                    Map<String, Object> courses = (Map<String, Object>) dataSnapshot.getValue();
                    courseList = getArray(courses);
                }


                getSchedule();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DATASNAPSHOT", "Datasnapshot error");
            }


        });
    }

    /**
     * This method gets the contents of the student's schedule by parsing through the total
     * Course list and selecting the ones that the student is taking and gets the timings
     * of those courses and stores them in a special timings Array.
     *
     * These timings are then sorted by day, turned into Strings, then finally the ListView
     * is populated with the contents of the timings and the schedule is displayed.
     */
    public void getSchedule(){
        dbReferenceAllCourses.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(int i = 1; i<6; i++){
                    Map<String, Object> scheduleMap = (Map<String, Object>) dataSnapshot.child(i+"").getValue();
                    Course c = Course.parse(scheduleMap);
                    System.out.println(c.getName());
                    System.out.println("Going to get timings...");
                    for(int k =0; k<courseList.length; k++){
                        System.out.println("Looping");
                        if(c.getName().equals(courseList[k])){
                            timings.add(new ScheduleTiming(c.getDay(), c.getTimeSlot(), c.getName(), c.getSemester()));
                            System.out.println("Timings: "+timings.get(0).toString());
                        }
                    }


                }

                sortTimings(timings);

                makeString(finalFallSchedule, 0);
                makeString(finalWinterSchedule, 1);

                populateListView(finalFallSchedule);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DATASNAPSHOT", "Datasnapshot error");
            }
        });


    }

    /**
     * This method sorts the timings of the student's courses and inserts them in the
     * arrays corresponding to the day(s) of the week they are in.
     *
     * @param timings
     *      ArrayList of ScheduleTimings of the student's courses.
     */
    public void sortTimings(ArrayList<ScheduleTiming> timings){

        for(int i = 0; i<timings.size(); i++){
            ScheduleTiming curr = timings.get(i);
            int term = 0;
            if(curr.getSemester().contains("Winter"))
                term = 1;

            if(curr.getDay().contains("M")){
                String timeslot = curr.getTimeSlot();
                int timeSlotI= Integer.parseInt(timeslot);
                mon[timeSlotI][term] = curr;
            }
            if(curr.getDay().contains("TU")){
                String timeslot = curr.getTimeSlot();
                int timeSlotI= Integer.parseInt(timeslot);
                tues[timeSlotI][term] = curr;
            }
            if(curr.getDay().contains("W")){
                String timeslot = curr.getTimeSlot();
                int timeSlotI= Integer.parseInt(timeslot);
                wed[timeSlotI][term] = curr;

            }
            if(curr.getDay().contains("TH")){
                String timeslot = curr.getTimeSlot();
                int timeSlotI= Integer.parseInt(timeslot);
                thurs[timeSlotI][term] = curr;
            }
            if(curr.getDay().contains("F")){
                String timeslot = curr.getTimeSlot();
                int timeSlotI= Integer.parseInt(timeslot);
                fri[timeSlotI][term] = curr;

            }

        }
    }

    /**
     * This method coverts the contents of the days of the week ScheduleTiming arrays
     * into strings for the finalSchedule array. It also populates days of the week with no
     * classes with a String that indicates there aren't any courses on that day.
     *
     */
    public void makeString(String[] schedule, int term){

        schedule[0] = "Monday\n\n";
        schedule[1] = "Tuesday\n\n";
        schedule[2] = "Wednesday\n\n";
        schedule[3] = "Thursday\n\n";
        schedule[4] = "Friday\n\n";


        for(int i = 0; i<4; i++){
            if(mon[i][term] != null){
                schedule[0] = schedule[0]+mon[i][term].toString();
            }
            if(tues[i][term] != null){
                schedule[1] = schedule[1]+tues[i][term].toString();
            }
            if(wed[i][term] != null){
                schedule[2] = schedule[2]+wed[i][term].toString();
            }
            if(thurs[i][term] != null){
                schedule[3] = schedule[3]+thurs[i][term].toString();
            }
            if(fri[i][term] != null){
                schedule[4] = schedule[4]+fri[i][term].toString();
            }

        }


        if(schedule[4].equals("Friday\n\n")){
            schedule[4]=schedule[4]+"No classes on Friday!";
        }
        if(schedule[3].equals("Thursday\n\n")){
            schedule[3]=schedule[3]+"No classes on Thursday!";
        }
        if(schedule[2].equals("Wednesday\n\n")){
            schedule[2]=schedule[2]+"No classes on Wednesday!";
        }
        if(schedule[1].equals("Tuesday\n\n")){
            schedule[1]=schedule[1]+"No classes on Tuesday!";
        }
        if(schedule[0].equals("Monday\n\n")){
            schedule[0]=schedule[0]+"No classes on Monday!";
        }


    }



    /**
     * This method populates the ListView with the Course timing information stored in the
     * finalSchedule String array.
     */
    public void populateListView(String[] schedule){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schedule);
        setListAdapter(arrayAdapter);
    }

}



