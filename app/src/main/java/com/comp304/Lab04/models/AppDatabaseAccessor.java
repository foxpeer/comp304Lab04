package com.comp304.Lab04.models;

import android.content.Context;


import androidx.room.Insert;
import androidx.room.Room;

public class AppDatabaseAccessor {
    private static AppDatabase medicalDatabase;
    private static final String APP_DB_NAME = "medical_db";

    //constructor
    private AppDatabaseAccessor(){}

    public static AppDatabase getInstance(Context context){
        if (medicalDatabase == null){
            // Create or open a new SQLite database,
            // and return it as a Room Database instance
            medicalDatabase = Room.databaseBuilder(context,
                    AppDatabase.class, APP_DB_NAME ).build();
        }
        return medicalDatabase;
    }


}
