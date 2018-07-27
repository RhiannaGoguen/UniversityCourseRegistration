package com.example.zacco.universitycourseregistration;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ListActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RegisteredClasses extends ListActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_classes);
        populateRegistered();
      
    }

    public void populateRegistered() {
        auth = FirebaseAuth.getInstance();
        Log.v("Method","populateRegistered Called");
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Students/"+auth.getUid()+"/Courses");
        Log.v("Check", dbRef.getKey());
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Object> courseMap = (HashMap<String,Object>) dataSnapshot.getValue();
                Log.w("check",""+ courseMap.get("Course1"));
                getArray(courseMap);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DATASNAPSHOT", "Datasnapshot error");
            }
        });
    }

    public void getArray(Map<String, Object> map) {
        String[] currentClasses = new String[map.size()];
        int i = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            currentClasses[i] = (String) entry.getValue();
            i++;
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, currentClasses);
        setListAdapter(arrayAdapter);
    }

    public void populateRegistered(){
        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Students").child(auth.getUid()).child("Courses");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                for (DataSnapshot students : dataSnapshot.getChildren()){
                    Log.v("what",""+ students.getKey());
                    Log.v("tmz", "" + students.child("First Name").getValue());
                    String key = students.getKey();
                    if(key.equals(auth.getUid())){
                        String name = students.child("First Name").getValue().toString() + " " + students.child("Last Name").getValue().toString();
                        String uid = auth.getUid();
                        setContentView(R.layout.activity_registered_classes);
                        TextView textView = (TextView) findViewById(R.id.studentInfo);
                        textView.setText(name);
                        textView = (TextView) findViewById(R.id.studentID);
                        textView.setText(uid);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("check", "failed");
            }
        });
    }

}