package com.comp304.Lab04.views;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.comp304.Lab04.models.AppDatabase;
import com.comp304.Lab04.models.Nurse;
import com.comp304.Lab04.models.NurseDAO;
import com.comp304.Lab04.models.Patient;
import com.comp304.Lab04.models.PatientDAO;
import com.comp304.Lab04.viewmodels.NurseViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private int nurseId;
    private String password;
    EditText tvNurseId;
    EditText tvPassword;
    TextView tvMsg;
    NurseViewModel nurseViewModel;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvNurseId = findViewById(R.id.tvNurseId);
        tvPassword = findViewById(R.id.tvPassword);
        tvMsg = findViewById(R.id.tvMsg);

        //initialize nurseViewModel
        nurseViewModel = ViewModelProviders.of(this).get(NurseViewModel.class);


    }
    //method validate input
    private boolean validateInput(){
        if(TextUtils.isEmpty(tvNurseId.getText().toString()) || TextUtils.isEmpty(tvPassword.getText().toString())) {
            return false;
        } else {
            return true;
        }
    }


    public void LoginClick(View view) {
        tvMsg.setText("");
        try{
            nurseId = Integer.parseInt(tvNurseId.getText().toString());
        } catch(Exception ex){
            ex.printStackTrace();
            tvMsg.setText("Please fill the nurse Id");
            return;
        }

        password = tvPassword.getText().toString();
        if(validateInput()){
            nurseViewModel.login(nurseId, password, this);
        } else {
            Toast.makeText(LoginActivity.this, "Empty fields", Toast.LENGTH_SHORT).show();
        }
    }

    public void loginSucceed(Nurse nurse){
        Log.d("LoginActivity::loginSucceed", "login Succeed: " + nurse.toString());
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("NurseId",  nurse.getNurseId());
        intent.putExtra("FirstName",  nurse.getFirstName());
        intent.putExtra("LastName",  nurse.getLastName());
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Welcome to Centennial Medical APP...", Toast.LENGTH_SHORT).show();

    }

    public void loginFailed(String errorMsg) {
        Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        tvMsg.setText(errorMsg);
    }

    public void RegisterClick(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "You are moving to register as a new nurse", Toast.LENGTH_SHORT).show();

    }
}






