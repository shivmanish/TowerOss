package com.example.patrollerapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lat_long_table")
data class LatlongData(
    // below line is to auto increment
    // id for each course.
    @PrimaryKey (autoGenerate = false)// variable for our id.
    var id: Long = 0,
    var start_lattitiude: Double = 0.0,
    var start_longitude: Double = 0.0,
    var end_lattitiude: Double = 0.0,
    var end_longitude: Double = 0.0,
    var root_distance: Double = 0.0,
    var betweenlatlongjson: String = "",
    var server_update: Boolean = false,

    )