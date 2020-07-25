package com.comp304.Lab04.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TestDAO {
    //Insert a list of Test, replacing stored tests
    //using the same name
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTests(List<Test> testList);

    //Insert one new test
    @Insert
    public void insertTest(Test test);

    @Update
    public void updateTests(List<Test> testList);
    @Update
    public void updateTest(Test test);

    @Delete
    public void deleteTest(Test test);
    @Delete
    public void deleteTwoTest(Test test1, Test test2);

    @Query("DELETE FROM test")
    public void deleteAllTests();

    @Query("SELECT * FROM test ORDER BY testId")
    public List<Test> displayAllTests();

    @Query("SELECT * FROM test WHERE testId =:testId")
    public Test findTestById(int testId);


}
