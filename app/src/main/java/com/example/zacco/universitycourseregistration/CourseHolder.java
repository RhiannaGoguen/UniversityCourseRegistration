    package com.example.zacco.universitycourseregistration;

    //https://inducesmile.com/android/a-simple-android-todo-list-app-with-recyclerview-and-firebase-real-time-database/

    import android.support.v7.widget.RecyclerView;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;
    import android.widget.Toast;


    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.auth.FirebaseUser;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
            import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;

    import java.util.HashMap;
    import java.util.Map;

    public class CourseHolder extends RecyclerView.ViewHolder{
        //declaring the variables here
        public Button registerButton;
        public TextView courseContent;
        public Button delete;
        String currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser().getUid() ;
        private FirebaseAuth auth;

        // database reference
        private DatabaseReference mydb;
        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Students/").child(currentFirebaseUser).child("Courses");



        public CourseHolder(final View itemView) {
            super(itemView);
            courseContent = itemView.findViewById(R.id.courseContent);
            registerButton = itemView.findViewById(R.id.register);
            delete=itemView.findViewById(R.id.delete);
            final String[] regId = new String[1];
            // calling the db ref
            mydb=FirebaseDatabase.getInstance().getReference();
                        // setting a listener for register
                        registerButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v){
                                // creating a child in root object
                                //regId[0] =mydb.child("Students").child("JhBCzdu6MlWNal2sBkJRRvqTD7H3").child("Courses").push().getKey();
                                regId[0] =mydb.child("Students").child(String.valueOf(currentFirebaseUser)).child("Courses").push().getKey();
                                mydb.child("Students").child(String.valueOf(currentFirebaseUser)).child("Courses").child(regId[0]).setValue(getCourseName());
                                //mydb.child("Students").child("JhBCzdu6MlWNal2sBkJRRvqTD7H3").child("Courses").child(regId[0]).setValue(getCourseName());
                                checkCourse();

                            }
                        });
            // setting a listener for the delete button
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteCourse();
                }
            });
        }


        public  void checkCourse(){
            final String cName=getCourseName();
            Log.v("Check",cName);

            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Map<String,Object> courseMap = (HashMap<String,Object>) dataSnapshot.getValue();
                    if(courseMap!=null){
                        Log.w("check",""+ courseMap.get("Course1"));
                        for (Map.Entry<String, Object> entry : courseMap.entrySet()) {
                            if (entry.getValue().equals(cName)) {
                                registerButton.setVisibility(View.GONE);
                                delete.setVisibility(View.VISIBLE);
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
        public void deleteCourse(){
            final String cName=getCourseName();
            Log.v("Check",cName);

            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Map<String,Object> courseMap = (HashMap<String,Object>) dataSnapshot.getValue();
                    Log.w("check",""+ courseMap.get("Course1"));
                    for (Map.Entry<String, Object> entry : courseMap.entrySet()) {
                        if (entry.getValue().equals(cName)) {
                            dbRef.child(entry.getKey()).removeValue();
                            registerButton.setVisibility(View.VISIBLE);
                            delete.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("DATASNAPSHOT", "Datasnapshot error");
                }
            });

        }
        // method to bring return just the course name
       public String getCourseName(){
            // getting the full content
            String courseDesc = (String) courseContent.getText();
            // getting only the courseName
           String subString= courseDesc.substring(13,25);
           String result=subString.split("\n")[0];
            return result;
       }
        public void bindCourse(Course course) {

            String courseName = course.toString();

            courseContent.setText(courseName);
            checkCourse();
        }
    }



