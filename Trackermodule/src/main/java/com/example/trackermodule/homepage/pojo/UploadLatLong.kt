package com.example.patrollerapp.homepage.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UploadLatLong(
    val startlat: String,
    val startlong: String,
    val starttime: String,
    val distance: Double
)