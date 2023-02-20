package com.example.trackermodule.db;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.patrollerapp.db.LatlongData;
import com.example.patrollerapp.homepage.pojo.UploadLatLong;

import java.util.List;

import retrofit2.http.DELETE;

// Adding annotation
// to our Dao class
@androidx.room.Dao
public interface Dao {

    // below method is use to
    // add data to database.
    @Insert
    void insert(LatlongData latlongData);


    @Query("SELECT * FROM lat_long_table")
    LiveData<List<LatlongData>> getAllCourses();



    @Query("DELETE FROM lat_long_table")
    public void nukeTable();


}
