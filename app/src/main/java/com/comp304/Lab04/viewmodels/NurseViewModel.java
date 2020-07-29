package com.comp304.Lab04.viewmodels;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Query;

import com.comp304.Lab04.models.Nurse;
import com.comp304.Lab04.models.NurseRepository;
import com.comp304.Lab04.views.LoginActivity;
import com.comp304.Lab04.views.MainActivity;

import java.util.List;

public class NurseViewModel extends AndroidViewModel {
    private NurseRepository nurseRepository;
    private LiveData<List<Nurse>> allNurses;

    //constructor
    public NurseViewModel(@NonNull Application application) {
        super(application);
        nurseRepository = new NurseRepository(application);
        allNurses = nurseRepository.getAllNurses();
    }

    public void insert(Nurse nurse){
        nurseRepository.insert(nurse);
    }

    public Nurse getNurse(int nurseId, String password){
        List<Nurse> nurses = allNurses.getValue();
        for(int i =0; i< nurses.size(); i++){
            if (nurses.get(i).getNurseId() == nurseId) {
                if(nurses.get(i).getPassword() == password){
                   return nurses.get(i);
                } else {
                   return null;
                }
            }
        }
        return  null;
    }



    public void update(Nurse nurse){
        nurseRepository.update(nurse);
    }

    public void delete(Nurse nurse){
        nurseRepository.delete(nurse);
    }
    public void deleteAllNurses(){
        nurseRepository.deleteAllNurses();
    }

   public LiveData<List<Nurse>> getAllNurses(){
        addNurseSeedData();
        return allNurses;
    }

   /* public LiveData<List<Nurse>> getAllNurses(){
        if(allNurses == null){
            allNurses = new MutableLiveData<List<Nurse>>();
            loadAllNursers();
        }
        return allNurses;
    }

    //Do an asynchronous operation to fetch nursers
    private void loadAllNursers() {
    }*/

    private void addNurseSeedData(){
        //add some seed data to database
        insert(new Nurse(9001, "Lily", " Lee", "OB", "qwertyu" ));
        insert(new Nurse(9002, "Cathy", " Care", "KIDS",  "qwertyu" ));
        insert(new Nurse(9003, "John", " Johns", "EMT","qwertyu" ));
    }

    public void login(int nurseId, String password, LoginActivity loginActivity) {
        nurseRepository.login(nurseId, password, loginActivity);
    }




}
