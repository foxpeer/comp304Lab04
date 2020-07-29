package com.comp304.Lab04.models;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Patient.class, Nurse.class, Test.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PatientDAO patientDAO();
    public abstract NurseDAO nurseDAO();

    private static volatile AppDatabase instance;

    private static final String DATABASE_NAME = "medical_db";

    //creating database
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            // Create or open a new SQLite database,
            // and return it as a Room Database instance
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()   //if exist, destroy the old database
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };



    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private PatientDAO patientDAO;

        private PopulateDbAsyncTask(AppDatabase db){
            patientDAO = db.patientDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            patientDAO.insert(new Patient(1001, "Nancy", "White", "WOMEN", 9001, "A-301" ));
            patientDAO.insert(new Patient(1002, "Lily", "Lee", "WOMEN", 9003, "B-122" ));
            patientDAO.insert(new Patient(1003, "May", "Hart", "CHILDREN", 9007, "E-722" ));
            return null;
        }
    }

}
