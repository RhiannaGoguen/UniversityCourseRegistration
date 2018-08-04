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

/**
 * Displays reqistered classes by pulling the registered class list from firebase
 */

public class RegisteredClasses extends ListActivity {

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_classes);
        populateRegistered();
    }

    /**
     * Creates a map from the firebase data entries for courses for the logged-in student
     */
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

    /**
     * Returns an array with the course names
     * @param map
     *          map from which the values are pulled from
     */
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
}
