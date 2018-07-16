package com.example.zacco.universitycourseregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentSchedule extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private String[] courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Students/"+auth.getUid()+"/Courses");//.child("Course");
        recyclerView = (RecyclerView)findViewById(R.id.scheduleView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Object> courseMap = (HashMap<String,Object>) dataSnapshot.getValue();
                Log.w("check",""+ courseMap.get("Course1"));
                courseList = getArray(courseMap);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DATASNAPSHOT", "Datasnapshot error");
            }
        });

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
}
