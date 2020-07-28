package com.comp304.Lab04.models;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.comp304.Lab04.views.PatientActivity;
import com.comp304.Lab04.views.PatientUpdateActivity;

import java.util.List;

public class PatientRepository {
    //variables
    private final PatientDAO patientDao;
    private LiveData<List<Patient>> patientsLiveData;

    //constructor
    public PatientRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        patientDao = db.patientDAO();
        patientsLiveData =  patientDao.selectAll();
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
        return patientsLiveData;
    }

    public void loadPatient(int patientId, PatientUpdateActivity updateActivity) {
        new GetPatientAsyncTask(patientDao, updateActivity).execute(patientId);
    }

    private static class InsertPatientAsyncTask
            extends AsyncTask<Patient, Void, Void>{
        private PatientDAO patientDAO;

        //constructor of InsertPatientAsyncTask class
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

        //constructor of UpdatePatientAsyncTask class
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

        //constructor of DeletePatientAsyncTask class
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

        //constructor of DeleteAllPatientAsyncTask class
        private DeleteAllPatientAsyncTask(PatientDAO patientDAO){
            this.patientDAO = patientDAO;
        }
        @Override
        protected Void doInBackground(Void... voids){
            patientDAO.deleteAll();
            return null;
        }
    } //end of DeleteAllPatientAsyncTask


    private static class GetPatientAsyncTask
            extends AsyncTask<Integer, Void, Patient>{
        private PatientDAO patientDAO;
        private PatientUpdateActivity updateActivity;

        //constructor of GetPatientAsyncTask class
        private GetPatientAsyncTask(PatientDAO patientDAO, PatientUpdateActivity updateActivity){
            this.patientDAO = patientDAO;
            this.updateActivity = updateActivity;
        }

        @Override
        protected Patient doInBackground(Integer... patientId){
            return patientDAO.select(patientId[0]);
        }
        @Override
        protected void onPostExecute(Patient patient) {
        // call the callback function here
            this.updateActivity.loadInputText(patient);
        }
    } //end of GetPatientAsyncTask class




}
