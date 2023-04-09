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

import com.example.bubblproject.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<TaskItem> TaskList = new ArrayList<TaskItem>();
    private ImageButton addbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//sets layout to main

        Intent intent =  getIntent();//creates an Intent

        TaskItem task = (TaskItem) intent.getSerializableExtra("Hello");//searches for the Create Task intent
        TextView textView = findViewById(R.id.taskText);//finds the textview text box:
        //TODO: Turn this textview into a list that iterates through a tasklist
        try {
            textView.setText(task.getName());
        }
        catch(NullPointerException e){//if the task is null, doesnt crash the program

        }

        addbutton = (ImageButton) findViewById(R.id.addbutton);
        addbutton.setOnClickListener(new View.OnClickListener() {
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