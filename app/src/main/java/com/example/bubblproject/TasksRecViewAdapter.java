package com.example.bubblproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import java.util.ArrayList;

public class TasksRecViewAdapter extends RecyclerView.Adapter<TasksRecViewAdapter.ViewHolder> {

    private ArrayList<TaskItem> tasks = new ArrayList<>();
    private Context context;

    public TasksRecViewAdapter(ArrayList<TaskItem> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskItem task = tasks.get(position);

        holder.taskName.setText(task.getTaskName());
        holder.priorityBar.setProgress(task.getTaskPriority());
        holder.locationText.setText("Location: " + task.getTaskLocation());

        boolean isVisible = tasks.get(position).isVisible();
        holder.expandableLayout.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(ArrayList<TaskItem> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView taskName, locationText;
        private ProgressBar priorityBar;
        private LinearLayout taskItem;
        private RelativeLayout expandableLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            priorityBar = itemView.findViewById(R.id.priorityBar);
            locationText = itemView.findViewById(R.id.locationText);
            taskItem = itemView.findViewById(R.id.taskItem);
            expandableLayout = itemView.findViewById(R.id.expandableContent);

            taskItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TaskItem task = tasks.get(getAdapterPosition());
                    if(task.isVisible() == false) {
                        for (TaskItem temp : tasks) {
                            temp.setVisible(false);
                        }
                    }

                    task.setVisible(!task.isVisible());
                    notifyDataSetChanged();
                }
            });
        }
    }
}
