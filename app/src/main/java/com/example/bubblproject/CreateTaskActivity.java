package com.example.bubblproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.location.Address;
import android.location.Location;
import android.text.format.DateFormat;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.example.bubblproject.Task.TaskItem;
import com.google.android.gms.maps.model.LatLng;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

public class CreateTaskActivity extends AppCompatActivity {

    private EditText nameText, priorityText;
    private Button createTaskButton, LocationButton;
    private RadioButton lowPriority, mediumPriority, highPriority;
    private RadioGroup priorityLevel;
    int hour, minutes, month, day, year;
    double priority;
    private TaskItem task = new TaskItem();
    private Date taskDate = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        createTaskButton = (Button) findViewById(R.id.button);
        LocationButton = (Button) findViewById(R.id.LocationButton);
        nameText = findViewById(R.id.editName);

        lowPriority = findViewById(R.id.lowPriority);
        mediumPriority = findViewById(R.id.mediumPriority);
        highPriority = findViewById(R.id.highPriority);

        lowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lowPriority.toggle();
            }
        });

        mediumPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediumPriority.toggle();
            }
        });

        highPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highPriority.toggle();
            }
        });



        Intent intent = getIntent();

        LocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMapActivity();
            }
        });



        try {
            String address = (String) intent.getSerializableExtra("Address");
            double taskLat = (double) intent.getSerializableExtra("Latitude");
            double taskLong = (double) intent.getSerializableExtra("Longitude");
            task.setTaskLocation(address);
            task.setTaskLatitude(taskLat);
            task.setTaskLongitude(taskLong);
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }

        createTaskButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if (lowPriority.isChecked()){
                    System.out.println("0.33");
                    task.setTaskPriority(0.33);
                } else if (mediumPriority.isChecked()) {
                    System.out.println("0.66");
                    task.setTaskPriority(0.66);
                } else if (highPriority.isChecked()) {
                    System.out.println("0.99");
                    task.setTaskPriority(0.99);
                } else {
                    task.setTaskPriority(0.0);
                }

                task.setTaskDate(taskDate);

                if(nameText.getText().toString() != "") {
                    task.setTaskName(nameText.getText().toString());
                }
                if(priorityText.getText().toString() != "") {
                    try {
                        task.setTaskPriority(Integer.parseInt(priorityText.getText().toString()));
                    }
                    catch (NumberFormatException e){}
                }
                if(task.getTaskName() == null || task.getTaskName().equals("")) {
                    goToMainActivity();
                }

                task.setOverallPriority();

                System.out.println(task.getOverallPriority());

                priorityText.setText("");
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

    private void goToMapActivity(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void popTimePicker(View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(Objects.isNull(taskDate)){
                    taskDate = new Date();
                }

                taskDate.setHours(hourOfDay);
                taskDate.setMinutes(minute);
                taskDate.setSeconds(0);
            }
        };

        int style = AlertDialog.THEME_HOLO_LIGHT;

        TimePickerDialog timePickerDialog;
        if(Objects.isNull(taskDate)) {
            timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, LocalTime.now().getHour() + 1, 0, false);
        }
        else{
            timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, taskDate.getHours(), taskDate.getMinutes(), false);
        }

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }


    public void popDatePicker(View view){
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int setyear, int setmonth, int setdayOfMonth) {
                if(Objects.isNull(taskDate)){
                    taskDate = new Date();
                }
                year = setyear - 1900;
                month = setmonth;
                day = setdayOfMonth;
                taskDate.setYear(year);
                taskDate.setMonth(month);
                taskDate.setDate(day);
            }
        };

        int style = AlertDialog.THEME_HOLO_LIGHT;

        DatePickerDialog datePickerDialog;
        if(Objects.isNull(taskDate)) {
            datePickerDialog = new DatePickerDialog(this, style, onDateSetListener, LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth());
        }
        else{
            datePickerDialog = new DatePickerDialog(this, style, onDateSetListener, taskDate.getYear() + 1900, taskDate.getMonth(), taskDate.getDate());
        }

        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
    }
}