package com.comp304.Lab04.models;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.comp304.Lab04.views.LoginActivity;
import com.comp304.Lab04.views.PatientUpdateActivity;
import com.comp304.Lab04.views.RegisterActivity;

import java.util.List;

public class NurseRepository {
    //variables
    private final NurseDAO nurseDao;
    private LiveData<List<Nurse>> nursesLiveData;

    //constructor
    public NurseRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        nurseDao = db.nurseDAO();
        nursesLiveData =  nurseDao.selectAll();
    }


    public void insert(Nurse nurse){
        new InsertNurseAsyncTask(nurseDao).execute(nurse);
    }

    public void update(Nurse nurse){
        new UpdateNurseAsyncTask(nurseDao).execute(nurse);
    }

    public void delete(Nurse nurse){
        new DeleteNurseAsyncTask(nurseDao).execute(nurse);
    }

    public void deleteAllNurses(){
        new DeleteAllNurseAsyncTask(nurseDao).execute();
    }

    public LiveData<List<Nurse>> getAllNurses(){
        return nursesLiveData;
    }

    public void login(int nurseId, String password, LoginActivity loginActivity) {
        new LoginNurseAsyncTask(nurseDao, nurseId, password, loginActivity).execute();
    }

    private static class InsertNurseAsyncTask
            extends AsyncTask<Nurse, Void, Void>{
        private NurseDAO nurseDAO;

        //constructor of InsertNurseAsyncTask class
        private InsertNurseAsyncTask(NurseDAO nurseDAO){
            this.nurseDAO = nurseDAO;
        }
        @Override
        protected Void doInBackground(Nurse... nurses){
            nurseDAO.insert(nurses[0]);
            return null;
        }
    } //end of InsertNurseAsyncTask

    private static class UpdateNurseAsyncTask
            extends AsyncTask<Nurse, Void, Void>{
        private NurseDAO nurseDAO;

        //constructor of UpdateNurseAsyncTask class
        private UpdateNurseAsyncTask(NurseDAO nurseDAO){
            this.nurseDAO = nurseDAO;
        }
        @Override
        protected Void doInBackground(Nurse... nurses){
            nurseDAO.update(nurses[0]);
            return null;
        }
    } //end of UpdateNurseAsyncTask

    private static class DeleteNurseAsyncTask
            extends AsyncTask<Nurse, Void, Void>{
        private NurseDAO nurseDAO;

        //constructor of DeleteNurseAsyncTask class
        private DeleteNurseAsyncTask(NurseDAO nurseDAO){
            this.nurseDAO = nurseDAO;
        }
        @Override
        protected Void doInBackground(Nurse... nurses){
            nurseDAO.delete(nurses[0]);
            return null;
        }
    } //end of DeleteNurseAsyncTask

    private static class DeleteAllNurseAsyncTask
            extends AsyncTask<Void, Void, Void>{
        private NurseDAO nurseDAO;

        //constructor of DeleteAllNurseAsyncTask class
        private DeleteAllNurseAsyncTask(NurseDAO nurseDAO){
            this.nurseDAO = nurseDAO;
        }
        @Override
        protected Void doInBackground(Void... voids){
            nurseDAO.deleteAll();
            return null;
        }
    } //end of DeleteAllNurseAsyncTask


    private static class LoginNurseAsyncTask
            extends AsyncTask<Void, Void, Nurse>{
        private NurseDAO nurseDAO;
        private int nurseId;
        private String password;
        private LoginActivity loginActivity;

        //constructor of DeleteAllNurseAsyncTask class
        private LoginNurseAsyncTask(NurseDAO nurseDAO, int nurseId, String password, LoginActivity loginActivity){
            this.nurseDAO = nurseDAO;
            this.nurseId = nurseId;
            this.password = password;
            this.loginActivity = loginActivity;
        }
        @Override
        protected Nurse doInBackground(Void... voids){
            return nurseDAO.select(this.nurseId);
        }
        @Override
        protected void onPostExecute(Nurse nurse) {
            // call the callback function here
            if (nurse == null) {
                this.loginActivity.loginFailed("nurseId not found");
                return;
            }
            if (!this.password.equals(nurse.getPassword())) {
                this.loginActivity.loginFailed("wrong password");
                return;
            }
            this.loginActivity.loginSucceed(nurse);
        }

    } //end of LoginNurseAsyncTask






}
