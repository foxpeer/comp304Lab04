package com.comp304.Lab04.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.comp304.Lab04.models.Nurse;

import java.util.List;

@Dao
public interface NurseDAO {
    @Query("SELECT * FROM tb_nurse where nurse_id= :nurseId and password= :password")
    Nurse getNurse(int nurseId, String password);

    @Insert
    void insert(Nurse nurse);

    @Update
    void update(Nurse nurse);

    @Delete
    void delete(Nurse nurse);

    @Query("SELECT * FROM tb_nurse ORDER BY nurse_id")
    LiveData<List<Nurse>> selectAll();

    @Query("DELETE FROM tb_nurse")
    void deleteAll();

    @Query("SELECT * FROM tb_nurse WHERE nurse_id = :nurseId  LIMIT 1")
    Nurse select(int nurseId);
}
