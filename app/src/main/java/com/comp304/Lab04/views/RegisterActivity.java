package com.comp304.Lab04.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.comp304.Lab04.models.AppDatabase;
import com.comp304.Lab04.models.Nurse;
import com.comp304.Lab04.models.NurseDAO;
import com.comp304.Lab04.viewmodels.NurseViewModel;
import com.comp304.Lab04.viewmodels.PatientViewModel;

public class RegisterActivity extends AppCompatActivity {
    private EditText tvNurseId;
    private EditText  tvFirstName;
    private EditText tvLastName;
    private EditText tvDepartment;
    private EditText tvPassword;

    int nurseId;
    String firstName;
    String lastName;
    String department;
    String password;
    NurseViewModel nurseViewModel;


    private NurseDAO nurseDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvNurseId = findViewById(R.id.tvNurseId);
        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvDepartment = findViewById(R.id.tvDepartment);
        tvPassword = findViewById(R.id.tvPassword);
        setTitle("Register Nurse");

        //initialize nurseViewModel
        nurseViewModel = ViewModelProviders.of(this).get(NurseViewModel.class);
    }

    public void RegisterClick(View view) {
        nurseId = Integer.parseInt(tvNurseId.getText().toString());
        firstName = tvFirstName.getText().toString() ;
        lastName =  tvLastName.getText().toString();
        department = tvDepartment.getText().toString();
        password = tvPassword.getText().toString();


        if(!isEmpty()){
            Nurse nurse = new Nurse(nurseId, firstName, lastName, department,password);
            nurseViewModel.insert(nurse);
            Log.d("RegisterActivity::RegisterClick ", "RegisterClick: "+ nurse.toString());
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Thanks for your registration, you are good to login now", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Empty Fields", Toast.LENGTH_SHORT).show();
        }
       }

    public void LoginClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"You already have an account, go to Login directly", Toast.LENGTH_SHORT).show();
    }

    private boolean isEmpty() {
        if (TextUtils.isEmpty(tvNurseId.getText().toString()) ||
                TextUtils.isEmpty(tvFirstName.getText().toString()) ||
                TextUtils.isEmpty(tvLastName.getText().toString()) ||
                TextUtils.isEmpty(tvDepartment.getText().toString()) ||
                TextUtils.isEmpty(tvPassword.getText().toString())
        ) {
            return true;
        } else {
            return false;
        }
    }
}