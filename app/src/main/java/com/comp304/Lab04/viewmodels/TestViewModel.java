package com.comp304.Lab04.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.comp304.Lab04.models.Test;
import com.comp304.Lab04.models.TestRepository;
import com.comp304.Lab04.views.TestUpdateActivity;

import java.util.List;

public class TestViewModel extends AndroidViewModel {
    private TestRepository testRepository;
    private LiveData<List<Test>> allTests;

    //constructor
    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = new TestRepository(application);
        allTests = testRepository.getAllTests();
    }


    public void insert(Test test){
        testRepository.insert(test);
    }

    public void update(Test test){
        testRepository.update(test);
    }

    public void delete(Test test){
        testRepository.delete(test);
    }
    public void deleteAllTests(){
        testRepository.deleteAllTests();
    }

    public LiveData<List<Test>> getAllTests(){
        addTestSeedData();
        return allTests;
    }
    private void addTestSeedData(){
        //add some seed data to database
        insert(new Test(80001, 1001, 9001, 80,  116, 36.5, 55.6, 160.5 ));
        insert(new Test(80002, 1001, 9003, 79,  120, 36.7, 56.0, 160.5 ));
        insert(new Test(80003, 1002, 9001, 85,  116, 36.5, 55.6, 180.0 ));
        insert(new Test(80004, 1002, 9002, 95,  130, 36.7, 56.0, 159.0 ));
        insert(new Test(80002, 1003, 9003, 79,  120, 36.7, 56.0, 160.5 ));
        insert(new Test(80003, 1004, 9001, 85,  116, 36.5, 55.6, 180.0 ));
        insert(new Test(80004, 1005, 9002, 95,  130, 36.7, 56.0, 159.0 ));
    }

    public void loadTest(int testId, TestUpdateActivity updateActivity) {
        testRepository.loadTest(testId, updateActivity);
    }
}
