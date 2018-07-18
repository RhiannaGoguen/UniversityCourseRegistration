package com.example.zacco.universitycourseregistration;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentSchedule extends AppCompatActivity {

    private ListView listView;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceCourses;
    private FirebaseAuth auth;
    private String[] courseList;
    private List<String[]> scheduleList;
    private ArrayList<ScheduleTiming> timings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Students/"+auth.getUid()+"/Courses");//.child("Course");
        databaseReferenceCourses = FirebaseDatabase.getInstance().getReference("Course");
        listView = (ListView)findViewById(R.id.scheduleView);
        linearLayoutManager = new LinearLayoutManager(this);

        getUserCourses();

        getScheduleThing();

    }

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

 /*   public String[] sortArray(String[] course){
        for(Map<String,Object> child : databaseReferenceCourses.)
        databaseReferenceCourses.child("1");
        for(int i=0;i<course.length;i++){
            String c = course[i].
        }

    }
*/
    public void getUserCourses(){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Object> courseMap = (HashMap<String,Object>) dataSnapshot.getValue();

                courseList = getArray(courseMap);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DATASNAPSHOT", "Datasnapshot error");
            }
        });
    }

    public void getScheduleThing(){
        databaseReferenceCourses.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // Course c = parseSnapshot(dataSnapshot);

                for(int i = 1; i<6; i++){
                    Map<String, Object> scheduleMap = (Map<String, Object>) dataSnapshot.child(i+"").getValue();
                    Course c= parseSnapshot(scheduleMap);
                    for(i =0; i<courseList.length; i++){
                        if(c.getName()==courseList[i]){
                            timings.add(new ScheduleTiming(c.getDay(), c.getTimeSlot()));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DATASNAPSHOT", "Datasnapshot error");
            }
        });
    }

    public Course parseSnapshot(@NonNull Map<String, Object> map) {
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
        String timeSlot = time+"";
        String day = (String) map.get("Day");
        Course c = new Course(name, description, 0, location,
                prerequisites, prof, profEmail, semester, timeSlot, day);
        System.out.println(c.toString());
        return c;
    }

}


