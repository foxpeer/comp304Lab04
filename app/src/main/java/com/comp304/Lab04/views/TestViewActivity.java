package com.comp304.Lab04.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

//nurse can view test information for a given patient
public class TestViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testview);
    }
}