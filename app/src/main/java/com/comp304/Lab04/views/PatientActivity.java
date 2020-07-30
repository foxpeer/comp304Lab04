package com.comp304.Lab04.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.comp304.Lab04.models.Patient;
import com.comp304.Lab04.viewmodels.PatientViewModel;

import java.util.List;

//allow the nurse to enter and view patient information
public class PatientActivity extends AppCompatActivity {
    public static final String EXTRA_PATIENTID =
            "com.comp304.Lab04.views.EXTRA_PATIENTID";
    public static final String EXTRA_FIRSTNAME =
            "com.comp304.Lab04.views.EXTRA_FIRSTNAME";
    public static final String EXTRA_LASTNAME =
            "com.comp304.Lab04.views.EXTRA_LASTNAME";
    public static final String EXTRA_DEPARTMENT =
            "com.comp304.Lab04.views.EXTRA_DEPARTMENT";
    public static final String EXTRA_ROOM =
            "com.comp304.Lab04.views.EXTRA_ROOM";
    public static final String EXTRA_NURSEID =
            "com.comp304.Lab04.views.EXTRA_NURSEID";

    private PatientViewModel patientViewModel;

    private EditText tvPatientId;
    private EditText tvFirstName;
    private EditText tvLastName;
    private EditText tvDepartment;
    private EditText tvRoom;
    private EditText tvNurseId;


    int patientId;
    String firstName;
    String lastName;
    String department;
    String room;
    int nurseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        tvPatientId = findViewById(R.id.tvPatientId);
        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvDepartment = findViewById(R.id.tvDepartment);
        tvRoom = findViewById(R.id.tvRoom);
        tvNurseId = findViewById(R.id.tvNurseId);

        nurseId = getIntent().getIntExtra("NurseId", 0 );
        tvNurseId.setText(String.valueOf(nurseId));

        //initialize patientViewModel
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Patient");
    }
    //method validate input
    private boolean validate(){
        Log.d("PatientActivity::Validate", "tvPatientId: "+tvPatientId.getText().toString());
        Log.d("PatientActivity::Validate", "patientId: "+ patientId);
         firstName = tvFirstName.getText().toString();
         lastName = tvLastName.getText().toString();
         department = tvDepartment.getText().toString();
         room = tvRoom.getText().toString();

        if (firstName.trim().isEmpty()
                || lastName.trim().isEmpty()
                || department.trim().isEmpty()
                || room.trim().isEmpty()
                ||tvNurseId.getText().toString().trim().isEmpty()
                ||tvPatientId.getText().toString().trim().isEmpty() ) {
            Toast.makeText(this, "Please fill all the field", Toast.LENGTH_SHORT).show();
            return false; //? Stay in the activity
        } else {
            return true;
        }

    }

    //method savePatient()
    private void savePatient() {
       if(!validate()) {
           Toast.makeText(this, "Please fill all fields of the form", Toast.LENGTH_SHORT).show();
       } else {
           Log.d("PatientActivity:", "tvPatientId: "+tvPatientId.getText().toString());
           patientId = Integer.parseInt(tvPatientId.getText().toString());
           Log.d("PatientActivity:", "patientId: "+ patientId);
           firstName = tvFirstName.getText().toString();
           lastName = tvLastName.getText().toString();
           department = tvDepartment.getText().toString();
           room = tvRoom.getText().toString();
           nurseId = Integer.parseInt(tvNurseId.getText().toString());
           patientViewModel.insert(new Patient(patientId, firstName, lastName, department, nurseId, room));
           Toast.makeText(this, "Patient saved.", Toast.LENGTH_SHORT).show();

           Log.d("PatientActivity:", "saved patientId: "+ patientId);
           finish();
           Intent intent = new Intent(this, PatientListActivity.class);
           startActivity(intent);
       }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_patient_menu, menu);
        return true;
    }

    //menu selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_patient:
                savePatient();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //button add new patient event handler
    public void SubmitNewPatient(View view) {
        savePatient();

    }

    public void CancelAddPatient(View view) {
        finish(); //back to previous activity
    }

}
