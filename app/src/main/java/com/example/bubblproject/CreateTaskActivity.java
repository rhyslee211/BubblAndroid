package com.example.bubblproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class CreateTaskActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText dateText;
    private EditText locationText;

    private EditText priorityText;
    private Button button;

    private TaskItem task = new TaskItem();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        button = (Button) findViewById(R.id.button);

        nameText = findViewById(R.id.editName);
        dateText = findViewById(R.id.editDate);
        locationText = findViewById(R.id.editLocation);
        priorityText = findViewById(R.id.editPriority);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if(nameText.getText().toString() != "") {
                    task.setName(nameText.getText().toString());
                }
                if(dateText.getText().toString() != "") {
                    task.setDate(dateText.getText().toString());
                }
                if(locationText.getText().toString() != "") {
                    task.setLoc(locationText.getText().toString());
                }
                if(priorityText.getText().toString() != "") {
                    try {
                        task.setPriority(Integer.parseInt(priorityText.getText().toString()));
                    }
                    catch (NumberFormatException e){}
                }
                if(task.getName() == null || task.getName().equals("")) {
                    goToMainActivity();
                }
                goToMainActivityWithTask();
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToMainActivityWithTask() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Hello", task);
        startActivity(intent);
    }
}