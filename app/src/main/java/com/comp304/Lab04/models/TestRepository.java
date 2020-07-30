package com.comp304.Lab04.models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.comp304.Lab04.views.TestUpdateActivity;

import java.util.List;

public class TestRepository {
    //variables
    private final TestDAO testDAO;   
    private LiveData<List<Test>> testsLiveData;

    //constructor
    public TestRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        testDAO = db.testDAO();
        testsLiveData =  testDAO.selectAll();
    }

    public void insert(Test test){
        new TestRepository.InsertTestAsyncTask(testDAO).execute(test);
    }

    public void update(Test test){
        new TestRepository.UpdateTestAsyncTask(testDAO).execute(test);
    }

    public void delete(Test test){
        new TestRepository.DeleteTestAsyncTask(testDAO).execute(test);
    }

    public void deleteAllTests(){
        new TestRepository.DeleteAllTestAsyncTask(testDAO).execute();
    }

    public LiveData<List<Test>> getAllTests(){
        return testsLiveData;
    }

    public void loadTest(int testId, TestUpdateActivity updateActivity) {
        new TestRepository.GetTestAsyncTask(testDAO, updateActivity).execute(testId);
    }

    private static class InsertTestAsyncTask
            extends AsyncTask<Test, Void, Void>{
        private TestDAO testDAO;

        //constructor of InsertTestAsyncTask class
        private InsertTestAsyncTask(TestDAO testDAO){
            this.testDAO = testDAO;
        }
        @Override
        protected Void doInBackground(Test... tests){
            testDAO.insert(tests[0]);
            return null;
        }
    } //end of InsertTestAsyncTask

    private static class UpdateTestAsyncTask
            extends AsyncTask<Test, Void, Void>{
        private TestDAO testDAO;

        //constructor of UpdateTestAsyncTask class
        private UpdateTestAsyncTask(TestDAO testDAO){
            this.testDAO = testDAO;
        }
        @Override
        protected Void doInBackground(Test... tests){
            testDAO.update(tests[0]);
            return null;
        }
    } //end of UpdateTestAsyncTask

    private static class DeleteTestAsyncTask
            extends AsyncTask<Test, Void, Void>{
        private TestDAO testDAO;

        //constructor of DeleteTestAsyncTask class
        private DeleteTestAsyncTask(TestDAO testDAO){
            this.testDAO = testDAO;
        }
        @Override
        protected Void doInBackground(Test... tests){
            testDAO.delete(tests[0]);
            return null;
        }
    } //end of DeleteTestAsyncTask

    private static class DeleteAllTestAsyncTask
            extends AsyncTask<Void, Void, Void>{
        private TestDAO testDAO;

        //constructor of DeleteAllTestAsyncTask class
        private DeleteAllTestAsyncTask(TestDAO testDAO){
            this.testDAO = testDAO;
        }
        @Override
        protected Void doInBackground(Void... voids){
            testDAO.deleteAll();
            return null;
        }
    } //end of DeleteAllTestAsyncTask


    private static class GetTestAsyncTask
            extends AsyncTask<Integer, Void, Test>{
        private TestDAO testDAO;
        private TestUpdateActivity updateActivity;

        //constructor of GetTestAsyncTask class
        private GetTestAsyncTask(TestDAO testDAO, TestUpdateActivity updateActivity){
            this.testDAO = testDAO;
            this.updateActivity = updateActivity;
        }

        @Override
        protected Test doInBackground(Integer... testId){
            return testDAO.select(testId[0]);
        }
        @Override
        protected void onPostExecute(Test test) {
            // call the callback function here
            this.updateActivity.loadInputText(test);
        }
    } //end of GetTestAsyncTask class
    
    
}
