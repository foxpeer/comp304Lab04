package com.comp304.Lab04.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.comp304.Lab04.models.Test;
import com.comp304.Lab04.viewmodels.TestViewModel;

//nurse enter test data for a patient
public class TestAddActivity extends AppCompatActivity {
    public static final String EXTRA_TESTID =
            "com.comp304.Lab04.views.EXTRA_TESTID";
    public static final String EXTRA_PATIENTID =
            "com.comp304.Lab04.views.EXTRA_PATIENTID";
    public static final String EXTRA_NURSEID =
            "com.comp304.Lab04.views.EXTRA_NURSEID";
    public static final String EXTRA_BPL =
            "com.comp304.Lab04.views.EXTRA_BPL";
    public static final String EXTRA_BPH =
            "com.comp304.Lab04.views.EXTRA_BPH";
    public static final String EXTRA_TEMPERATURE =
            "com.comp304.Lab04.views.EXTRA_TEMPERATURE";
    public static final String EXTRA_WEIGHT =
            "com.comp304.Lab04.views.EXTRA_WEIGHT";
    public static final String EXTRA_HEIGHT =
            "com.comp304.Lab04.views.EXTRA_HEIGHT";

    private TestViewModel testViewModel;

    private EditText tvTestId;
    private EditText tvPatientId;
    private EditText tvNurseId;
    private EditText tvBPL;
    private EditText tvBPH;
    private EditText tvTemperature;
    private EditText tvWeight;
    private EditText tvHeight;

    int testId;
    int patientId;
    int nurseId;
    int BPL;
    int BPH;
    double temperature;
    double weight;
    double height;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testadd);
        setTitle("Add Test");

        tvTestId = findViewById(R.id.tvTestId);
        tvPatientId = findViewById(R.id.tvPatientId);
        tvNurseId = findViewById(R.id.tvNurseId);
        tvBPL = findViewById(R.id.tvBPL);
        tvBPH = findViewById(R.id.tvBPH);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvWeight = findViewById(R.id.tvWeight);
        tvHeight = findViewById(R.id.tvHeight);

        //initialize testViewModel
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
    }

   //method validate input
    private boolean validate(){

        if ( tvTestId.getText().toString().trim().isEmpty()
                ||tvNurseId.getText().toString().trim().isEmpty()
                ||tvPatientId.getText().toString().trim().isEmpty()
                ||tvBPL.getText().toString().trim().isEmpty()
                ||tvBPH.getText().toString().trim().isEmpty()
                ||tvTestId.getText().toString().trim().isEmpty()
                ||tvWeight.getText().toString().trim().isEmpty()
                ||tvHeight.getText().toString().trim().isEmpty()
         ) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return false; //? Stay in the activity
        } else {
            return true;
        }
    }

    //method saveTest()
    private void saveTest() {
        if(!validate()) {
            Toast.makeText(this, "Please fill all fields of the form", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("TestAddActivity::Validate", "tvPatientId: "+tvPatientId.getText().toString());
            Log.d("TestAddActivity::Validate", "patientId: "+ patientId);
            testId = Integer.parseInt(tvTestId.getText().toString());
            patientId = Integer.parseInt(tvPatientId.getText().toString());
            nurseId = Integer.parseInt(tvNurseId.getText().toString());
            BPL = Integer.parseInt(tvBPL.getText().toString());
            BPH = Integer.parseInt(tvBPH.getText().toString());
            temperature = Double.parseDouble(tvTemperature.getText().toString());
            weight = Double.parseDouble(tvWeight.getText().toString());
            height = Double.parseDouble(tvHeight.getText().toString());

            testViewModel.insert(new Test(testId, patientId, nurseId, BPL, BPH, temperature, weight, height));
            Toast.makeText(this, "Test saved.", Toast.LENGTH_SHORT).show();

            Log.d("TestAddActivity:", "saved testId: "+ testId);
            finish();
            Intent intent = new Intent(this, TestListActivity.class);
            startActivity(intent);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_test_menu, menu);
        return true;
    }

    //menu selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_test:
                saveTest();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //button add new test event handler
    public void AddTestRecord(View view) {
        saveTest();
    }

    public void CancelAddTest(View view) {
        finish(); //back to previous activity
    }
}