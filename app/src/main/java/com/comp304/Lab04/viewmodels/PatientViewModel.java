package com.comp304.Lab04.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.comp304.Lab04.models.Patient;
import com.comp304.Lab04.models.PatientRepository;
import com.comp304.Lab04.views.PatientUpdateActivity;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {
    private PatientRepository patientRepository;
    private LiveData<List<Patient>> allPatients;

    //constructor
    public PatientViewModel(@NonNull Application application) {
        super(application);
        patientRepository = new PatientRepository(application);
        allPatients = patientRepository.getAllPatients();
    }

    public void insert(Patient patient){
        patientRepository.insert(patient);
    }

    public void update(Patient patient){
        patientRepository.update(patient);
    }

    public void delete(Patient patient){
        patientRepository.delete(patient);
    }
    public void deleteAllPatients(){
        patientRepository.deleteAllPatients();
    }

    public LiveData<List<Patient>> getAllPatients(){
        addPatientSeedData();
        return allPatients;
    }
    private void addPatientSeedData(){
        //add some seed data to database
        insert(new Patient(1001, "Lily", " Lee", "OB", 9001, "A123" ));
        insert(new Patient(1002, "Cathy", " Care", "KIDS", 9002, "E123" ));
        insert(new Patient(1003, "John", " Johns", "EMT", 9003, "F123" ));

    }


    public void loadPatient(int patientId, PatientUpdateActivity updateActivity) {
        patientRepository.loadPatient(patientId, updateActivity);
    }
}
