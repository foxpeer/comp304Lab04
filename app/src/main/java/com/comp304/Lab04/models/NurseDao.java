package com.comp304.Lab04.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.comp304.Lab04.models.Nurse;

import java.util.List;

@Dao
public interface  NurseDao {
    @Query("SELECT * FROM Nurse where nurseId= :nurseId and password= :password")
    Nurse getNurse(int nurseId, String password);

    @Insert
    void insert(Nurse nurse);

    @Update
    void update(Nurse nurse);

    @Delete
    void delete(Nurse nurse);


}
