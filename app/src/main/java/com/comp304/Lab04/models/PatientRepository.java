package com.comp304.Lab04.models;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.comp304.Lab04.views.PatientActivity;

import java.util.List;

public class PatientRepository {
    private final PatientDAO patientDao;
    private LiveData<List<Patient>> patientsLiveDate;


    public PatientRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        patientDao = db.patientDAO();
        patientsLiveDate =  patientDao.selectAll();
    }

    public void insert(Patient patient){
        new InsertPatientAsyncTask(patientDao).execute(patient);
    }

    public void update(Patient patient){
        new UpdatePatientAsyncTask(patientDao).execute(patient);
    }

    public void delete(Patient patient){
        new DeletePatientAsyncTask(patientDao).execute(patient);
    }

    public void deleteAllPatients(){
        new DeleteAllPatientAsyncTask(patientDao).execute();
    }

    public LiveData<List<Patient>> getAllPatients(){
        return patientsLiveDate;
    }

    private static class InsertPatientAsyncTask
            extends AsyncTask<Patient, Void, Void>{
        private PatientDAO patientDAO;

        //constructor of class
        private InsertPatientAsyncTask(PatientDAO patientDAO){
            this.patientDAO = patientDAO;
        }
        @Override
        protected Void doInBackground(Patient... patients){
            patientDAO.insert(patients[0]);
            return null;
        }
    } //end of InsertPatientAsyncTask

    private static class UpdatePatientAsyncTask
            extends AsyncTask<Patient, Void, Void>{
        private PatientDAO patientDAO;

        //constructor of class
        private UpdatePatientAsyncTask(PatientDAO patientDAO){
            this.patientDAO = patientDAO;
        }
        @Override
        protected Void doInBackground(Patient... patients){
            patientDAO.update(patients[0]);
            return null;
        }
    } //end of UpdatePatientAsyncTask

    private static class DeletePatientAsyncTask
            extends AsyncTask<Patient, Void, Void>{
        private PatientDAO patientDAO;

        //constructor of class
        private DeletePatientAsyncTask(PatientDAO patientDAO){
            this.patientDAO = patientDAO;
        }
        @Override
        protected Void doInBackground(Patient... patients){
            patientDAO.delete(patients[0]);
            return null;
        }
    } //end of DeletePatientAsyncTask

    private static class DeleteAllPatientAsyncTask
            extends AsyncTask<Void, Void, Void>{
        private PatientDAO patientDAO;

        //constructor of class
        private DeleteAllPatientAsyncTask(PatientDAO patientDAO){
            this.patientDAO = patientDAO;
        }
        @Override
        protected Void doInBackground(Void... voids){
            patientDAO.deleteAll();
            return null;
        }
    } //end of DeleteAllPatientAsyncTask





}
