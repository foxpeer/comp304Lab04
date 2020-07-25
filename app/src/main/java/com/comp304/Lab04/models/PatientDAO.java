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
public interface PatientDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insert(Patient patient);

    @Update
     void update(Patient patient);

    @Delete
     void delete(Patient patient);

    @Query("SELECT * FROM tb_patient ORDER BY patient_id")
    LiveData<List<Patient>> selectAll();

    @Query("DELETE FROM tb_patient")
    void deleteAll();





}
