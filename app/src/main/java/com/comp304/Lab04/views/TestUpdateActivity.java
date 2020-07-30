package com.comp304.Lab04.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.comp304.Lab04.models.Patient;
import com.comp304.Lab04.models.Test;
import com.comp304.Lab04.viewmodels.TestViewModel;

public class TestUpdateActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_test_update);
        setTitle("Update Patient");

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

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_TESTID)){
            //receive testId by Intent
            testId = intent.getIntExtra(EXTRA_TESTID,0);
            Log.d("TestUpdateActivity:", "TestId: "+ testId);

            //loadtest according testid
            testViewModel.loadTest(testId, this);
            Log.d("TestUpdateActivity:", "tvTestId: "+tvTestId);

            setTitle("Update Test");
        } else{
            setTitle("Add Test");
        }
    }

    public void loadInputText(Test currentTest) {
        tvTestId.setText(String.valueOf(currentTest.getTestId())); //
        tvPatientId.setText(String.valueOf(currentTest.getPatientId())); //
        tvNurseId.setText(String.valueOf(currentTest.getNurseId()));
        tvBPL.setText(String.valueOf(currentTest.getBPL()) );
        tvBPH.setText(String.valueOf(currentTest.getBPH()) );
        tvTemperature.setText(String.valueOf(currentTest.getTemperature()) );
        tvWeight.setText(String.valueOf(currentTest.getWeight()) );
        tvHeight.setText(String.valueOf(currentTest.getHeight()) );
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

    private void updateTest() {
        if(!validate()) {
            Toast.makeText(this, "Please fill all fields of the form", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("TestUpdateActivity:", "tvTestId: "+tvTestId.getText().toString());
            testId = Integer.parseInt(tvTestId.getText().toString());
            Log.d("TestUpdateActivity:", "testId: "+ testId);
            patientId = Integer.parseInt(tvPatientId.getText().toString());
            nurseId = Integer.parseInt(tvNurseId.getText().toString());
            BPL = Integer.parseInt(tvBPL.getText().toString());
            BPH = Integer.parseInt(tvBPH.getText().toString());
            temperature = Double.parseDouble(tvTemperature.getText().toString());
            weight = Double.parseDouble(tvWeight.getText().toString());
            height = Double.parseDouble(tvHeight.getText().toString());

            testViewModel.update(new Test(testId, patientId, nurseId, BPL, BPH, temperature, weight, height));
            Toast.makeText(this, "Test saved.", Toast.LENGTH_SHORT).show();

            Log.d("TestAddActivity:", "saved testId: "+ testId);
            finish(); //back to previous activity
        }

    }

    public void CancelUpdateTest(View view) {
        finish(); //back to previous activity
    }

    public void UpdateTestClick(View view){
        updateTest();
    }
}