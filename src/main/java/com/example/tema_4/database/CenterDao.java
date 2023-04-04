package com.example.tema_4.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CenterDao {
    @Insert
    long insert(Center center);
    @Query("select * from centre")
    List<Center> getAll();
}
