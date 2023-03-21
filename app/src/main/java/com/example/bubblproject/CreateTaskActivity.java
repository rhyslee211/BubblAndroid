package com.example.bubblproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class CreateTaskActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText dateText;
    private EditText locationText;
    private Button button;

    private String nameToSend;
    private String dateToSend;
    private String locToSend;

    public static final String TEXT_NAME = "com.example.bubblproject.TEXT_NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        button = (Button) findViewById(R.id.button);

        nameText = findViewById(R.id.editName);
        dateText = findViewById(R.id.editDate);
        locationText = findViewById(R.id.editLocation);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                nameToSend = nameText.getText().toString();
                dateToSend = dateText.getText().toString();
                locToSend = locationText.getText().toString();
                goToMainActivity();
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(TEXT_NAME, nameToSend);
        startActivity(intent);
    }
}