package com.comp304.Lab04.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TestDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Test test);

    @Update
    void update(Test test);

    @Delete
    void delete(Test test);

    @Query("SELECT * FROM tb_test ORDER BY test_id")
    LiveData<List<Test>> selectAll();

    @Query("DELETE FROM tb_test")
    void deleteAll();

    @Query("SELECT * FROM tb_test WHERE test_id = :testId  LIMIT 1")
    Test select(int testId);

    @Delete
    void delete(Test... test);

    @Query("DELETE FROM tb_test")
    void deleteAllTests();

    @Query("DELETE FROM tb_test WHERE test_id=:testId")
    void deleteTest(int testId);

    @Query("SELECT * FROM tb_test WHERE patient_id=:patientId ORDER BY test_id")
    LiveData<List<Test>> displayAllTests(int patientId);







}
