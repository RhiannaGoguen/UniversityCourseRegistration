package com.example.zacco.universitycourseregistration;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/*public class CourseAdapter extends RecyclerView.Adapter<CourseHolder> {
   private List<Course> courses;
    protected Context context;
    public CourseAdapter(Context context, List<Course> course) {
        this.courses = course;
        this.context = context;
    }
    @Override
    public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CourseHolder viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list, parent, false);
        viewHolder = new CourseHolder(layoutView, courses);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(CourseHolder holder, int position) {
        holder.courseContent.setText(courses.get(position).getName());
    }
    @Override
    public int getItemCount() {
        return this.courses.size();
    }
}*/