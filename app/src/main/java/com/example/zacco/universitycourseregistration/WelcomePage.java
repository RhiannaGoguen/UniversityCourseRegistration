package com.example.zacco.universitycourseregistration;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class WelcomePage extends AppCompatActivity {
    private TextView welcomeText;
    private FirebaseAuth auth;
    private DatabaseReference dbRef;
    private Button logoutBtn;
    private Button activityRegistered;
    private String fName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("Students/"+auth.getUid());
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                Map<String,Object> studentMap = (HashMap<String,Object>) dataSnapshot.getValue();
                fName = (String) studentMap.get("First Name");
                welcomeText = (TextView) findViewById(R.id.Welcome);
                welcomeText.setText("Welcome " +fName+ " to The University of Maximegalon Course Registration!");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("check", "failed");
            }
        });

        logoutBtn = (Button) findViewById(R.id.logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        activityRegistered = (Button) findViewById(R.id.registeredclasses);
        activityRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisteredClasses();
            }
        });
    }
    /** Called when the user taps the button */
    public void goToRegisteredClasses() {
        // Do something in response to button
        Intent startNewActivity = new Intent(this, RegisteredClasses.class);
        startActivity(startNewActivity);
    }
    public void goToAcademicTimetable(View view) {
        // Do something in response to button
        Intent startNewActivity = new Intent(this, AcademicTimetable.class);
        startActivity(startNewActivity);
    }
    public void goToSchedule(View view) {
        // Do something in response to button
    }
}

