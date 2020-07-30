package com.comp304.Lab04.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    int nurseId;
    String firstName;
    String lastName;
    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvWelcome = findViewById(R.id.tvWelcome);
        nurseId = getIntent().getIntExtra("NurseId", 0 );
        firstName = getIntent().getStringExtra("FirstName");
        lastName = getIntent().getStringExtra("LastName");
        tvWelcome.setText("Welcome "+ firstName + ", " +lastName+ " !");


    }

    //to add a new Patient
    public void AddPatientClick(View view) {
        Intent i = new Intent(this, PatientActivity.class);
        i.putExtra("NurseId",  nurseId);
        startActivity(i);
        Toast.makeText(getApplicationContext(),"You are going to add a new patient", Toast.LENGTH_SHORT).show();
        //Log.d(TAG, "You are moving to add a new patient ");
    }

    //to display all the patients
    public void DisplayPatientClick(View view) {
        Intent i = new Intent(this, PatientListActivity.class);
        i.putExtra("NurseId",  nurseId);
        startActivity(i);
        Toast.makeText(getApplicationContext(),"You are going to View All Patients", Toast.LENGTH_SHORT).show();
    }
/*
    public void UpdatePatientClick (View view) {
        Intent i = new Intent(this, PatientUpdateActivity.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(),"You are going to Update a Patient info", Toast.LENGTH_SHORT).show();
        //Log.d(TAG, "You are moving to add a new patient ");
    }*/

    //to add a new Test
    public void AddNewTestClick(View view) {
        Intent testIntent = new Intent(this, TestAddActivity.class);
        startActivity(testIntent);
        Toast.makeText(getApplicationContext(),"You are going to add a new test", Toast.LENGTH_SHORT).show();
    }

    //to view a Test Results
    public void ViewTestClick(View view) {
        Intent testIntent = new Intent(this, TestListActivity.class);
        startActivity(testIntent);
        Toast.makeText(getApplicationContext(),"You are going to add a new test", Toast.LENGTH_SHORT).show();
    }




}