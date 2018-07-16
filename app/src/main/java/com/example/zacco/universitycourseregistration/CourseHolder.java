package com.example.zacco.universitycourseregistration;

//https://inducesmile.com/android/a-simple-android-todo-list-app-with-recyclerview-and-firebase-real-time-database/

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import com.google.firebase.database.ValueEventListener;
        import java.util.List;

public class CourseHolder extends RecyclerView.ViewHolder{
    public Button registerButton;
    public TextView courseContent;

    public CourseHolder(final View itemView) {
        super(itemView);
        courseContent = itemView.findViewById(R.id.courseContent);
        registerButton = itemView.findViewById(R.id.register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){



            }
        });
    }

    public void bindCourse(Course course) {
        courseContent = itemView.findViewById(R.id.courseContent);
        registerButton = itemView.findViewById(R.id.register);

        String courseName = course.toString();

        courseContent.setText(courseName);
    }
}

