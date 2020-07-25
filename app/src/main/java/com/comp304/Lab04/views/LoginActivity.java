package com.comp304.Lab04.views;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private String nurseId;
    private String password;
    EditText etNurseId;
    EditText etPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNurseId = findViewById(R.id.tvNurseId);
        etPassword = findViewById(R.id.password);

        nurseId = etNurseId.getText().toString();
        password = etPassword.getText().toString();
    }


    public void LoginClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Welcome to Centennial Medical APP...", Toast.LENGTH_SHORT).show();

        etNurseId.setText("");
        etPassword.setText("");
    }

    public void RegisterClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"You are moving to register as a new nurse", Toast.LENGTH_SHORT).show();

        etNurseId.setText("");
        etPassword.setText("");

    }



  /*  private boolean validateLogin() {
        Log.d("NurseId:", nurseId);
        Log.d("Password:", nurseId);
        if(nurseId.equals("N900001") && password.equals("N123456") ) {
            return true;
        } else
            return false;
    }*/
}