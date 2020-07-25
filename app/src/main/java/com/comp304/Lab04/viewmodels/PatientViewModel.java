package com.comp304.Lab04.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.comp304.Lab04.models.Patient;
import com.comp304.Lab04.models.PatientRepository;

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
        return allPatients;
    }


}
