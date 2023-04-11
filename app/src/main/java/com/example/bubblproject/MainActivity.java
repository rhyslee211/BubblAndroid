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
    private ImageButton addbutton;

    private ListView myListView;
    private ArrayAdapter TaskArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//sets layout to main

        Intent intent =  getIntent();//creates an Intent

        TaskItem task = (TaskItem) intent.getSerializableExtra("Hello");//searches for the Create Task intent
        //TextView textView = findViewById(R.id.taskText);//finds the textview text box:
        try {
            if (task.getName() != null && task.getName().equals("") == false) {
                MyTaskList.TaskList.add(task);
            }
        }
        catch(NullPointerException e){}
        //TODO: Call the sorting algorithm function here

        myListView = findViewById(R.id.list_view);

        TaskArrayAdapter = new ArrayAdapter(this, R.layout.mytextview, MyTaskList.TaskList);
        myListView.setAdapter(TaskArrayAdapter);
        myListOnClickListener();

        addbutton = (ImageButton) findViewById(R.id.addbutton);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateActivity();
            }
        });

    }

    private void myListOnClickListener() {
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l){
                MyTaskList.TaskList.remove(i);
                TaskArrayAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void openCreateActivity(){
        Intent intent = new Intent(this, CreateTaskActivity.class);
        startActivity(intent);
    }
}