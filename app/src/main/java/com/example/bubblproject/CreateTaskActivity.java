package com.example.bubblproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.text.format.DateFormat;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class CreateTaskActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText locationText;

    private EditText priorityText;
    private Button button;

    private static TaskItem task = new TaskItem();

    private static Date taskDate = new Date();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        button = (Button) findViewById(R.id.button);

        nameText = findViewById(R.id.editName);
        locationText = findViewById(R.id.editLocation);
        priorityText = findViewById(R.id.editPriority);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                task.setDate(taskDate);

                if(nameText.getText().toString() != "") {
                    task.setName(nameText.getText().toString());
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

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){

            final Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY) + 1;
            int minute = 0;

            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }
        public void onTimeSet(TimePicker view,int hourOfDay, int minute){
            taskDate.setHours(hourOfDay);
            taskDate.setMinutes(minute);
            taskDate.setSeconds(0);
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(requireContext(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            taskDate.setYear(year - 1900);
            taskDate.setMonth(month);
            taskDate.setDate(day);

        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}