package com.example.zacco.universitycourseregistration;

import android.app.ListActivity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class StudentSchedule extends ListActivity {

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

    /**
     *
     * @param map
     * @return
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
                    Course c = Course.parse(scheduleMap);
                    System.out.println(c.toString());
                    System.out.println("Going to get timings...");
                    for(i =0; i<courseList.length; i++){
                        System.out.println("Looping");
                        if(c.getName().equals(courseList[i])){
                            timings.add(new ScheduleTiming(c.getDay(), c.getTimeSlot()));
                            System.out.println("Timings: "+timings.get(i).toString());
                        }
                    }

                    //TODO sort timings
                    //  - Create new array for timings that are sorted
                    //  - We gotta sort by day first and then timeslot
                    //  - We'll

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DATASNAPSHOT", "Datasnapshot error");
            }
        });


    }


    public void printTimings(String[] timings){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timings);
        setListAdapter(arrayAdapter);
    }

}



