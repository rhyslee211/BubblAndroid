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
        setContentView(R.layout.activity_main);

        Intent intent =  getIntent();

        String taskName = intent.getStringExtra(CreateTaskActivity.TEXT_NAME);
        TextView textView = findViewById(R.id.taskText);
        textView.setText(taskName);

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