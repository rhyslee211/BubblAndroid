package com.example.bubblproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.bubblproject.Adapter.TasksRecViewAdapter;
import com.example.bubblproject.Task.TaskItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.DisplayMetrics;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

//import com.example.bubblproject.databinding.ActivityMainBinding;

import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//import kotlinx.coroutines.scheduling.Task;

public class MainActivity extends AppCompatActivity {
    private RecyclerView tasksRecView;
    private FloatingActionButton addTask;

    public static ArrayList<TaskItem> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//sets layout to main

        Intent intent = getIntent();//creates an Intent

        tasksRecView = findViewById(R.id.tasksRecView);
        addTask = findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateActivity();
            }
        });

        TaskItem task = (TaskItem) intent.getSerializableExtra("Hello");//searches for the Create Task intent
        //TextView textView = findViewById(R.id.taskText);//finds the textview text box:
        try {
            if (task.getTaskName() != null && task.getTaskName().equals("") == false) {
                tasks.add(task);
            }
        } catch (NullPointerException e) {
        }
        //TODO: Call the sorting algorithm function here

        Collections.sort(tasks, new Comparator<TaskItem>() {
            @Override
            public int compare(TaskItem o1, TaskItem o2) {
                return Integer.valueOf(o1.getTaskPriority()).compareTo(Integer.valueOf(o2.getTaskPriority()));
            }
        });

        Collections.reverse(tasks);


        TasksRecViewAdapter adapter = new TasksRecViewAdapter(tasks, getApplicationContext());
        adapter.setTasks(tasks);
        tasksRecView.setAdapter(adapter);
    }

    public void openCreateActivity() {
        Intent intent = new Intent(this, CreateTaskActivity.class);
        startActivity(intent);
    }
}