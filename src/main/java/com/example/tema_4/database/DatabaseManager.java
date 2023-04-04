package com.example.tema_4.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Center.class},exportSchema = false,version = 1)
public abstract class DatabaseManager extends RoomDatabase {
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context){
        if(databaseManager==null){
            synchronized (DatabaseManager.class){
                if(databaseManager==null){
                    String centre_db = "centre_db";
                    databaseManager= Room.databaseBuilder(context,DatabaseManager.class, centre_db)
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return databaseManager;
    }

    public abstract CenterDao getCenterDao();
}
