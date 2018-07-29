    package com.example.zacco.universitycourseregistration;

    //https://inducesmile.com/android/a-simple-android-todo-list-app-with-recyclerview-and-firebase-real-time-database/

    import android.support.v7.widget.RecyclerView;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;


    import com.google.android.gms.tasks.Task;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
            import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;

    public class CourseHolder extends RecyclerView.ViewHolder{
        //declaring the variables here
        public Button registerButton;
        public TextView courseContent;
        public Button delete;

        // database reference
        private DatabaseReference mydb;


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
                                regId[0] =mydb.child("Students").child("JhBCzdu6MlWNal2sBkJRRvqTD7H3").child("Courses").push().getKey();
                                mydb.child("Students").child("JhBCzdu6MlWNal2sBkJRRvqTD7H3").child("Courses").child(regId[0]).setValue(getCourseName());
                            }
                        });
            // setting a listener for the delete button
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //removing the value created
                    mydb.child("Students").child("JhBCzdu6MlWNal2sBkJRRvqTD7H3").child("Courses").child(regId[0]).removeValue();
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
            courseContent = itemView.findViewById(R.id.courseContent);
            registerButton = itemView.findViewById(R.id.register);
            delete=itemView.findViewById(R.id.delete);

            String courseName = course.toString();

            courseContent.setText(courseName);
        }
    }



