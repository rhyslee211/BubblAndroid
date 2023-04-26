package com.example.bubblproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.bubblproject.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//import kotlinx.coroutines.scheduling.Task;

public class MainActivity extends AppCompatActivity {
    private ImageButton addButton;

    private RecyclerView tasksRecView;
    private ArrayAdapter TaskArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//sets layout to main

        Intent intent =  getIntent();//creates an Intent

        tasksRecView = findViewById(R.id.tasksRecView);

        ArrayList<TaskItem> tasks = new ArrayList<>();

        tasks.add(new TaskItem("Gym", 3));
        tasks.add(new TaskItem("Homework", 5));
        tasks.add(new TaskItem("Soccer Practice", 3));
        tasks.add(new TaskItem("Sleep", 1));

        /*
        TaskItem task = (TaskItem) intent.getSerializableExtra("Hello");//searches for the Create Task intent
        //TextView textView = findViewById(R.id.taskText);//finds the textview text box:
        try {
            if (task.getName() != null && task.getName().equals("") == false) {
                tasks.add(task);
            }
        }
        catch(NullPointerException e){}
        //TODO: Call the sorting algorithm function here
        */

        TasksRecViewAdapter adapter = new TasksRecViewAdapter();
        adapter.setTasks(tasks);

        tasksRecView.setAdapter(adapter);
        tasksRecView.setLayoutManager(new LinearLayoutManager(this));

        ImageButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateActivity();
            }
        });

    }

    public void openCreateActivity(){
        Intent intent = new Intent(this, CreateTaskActivity.class);
        startActivity(intent);
    }
}