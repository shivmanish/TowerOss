package com.example.trackermodule.homepage.pojo

import com.example.patrollerapp.homepage.pojo.UploadLatLong

data class UpDateLatlongRequest(
    val ownername: String,
    val tracking: String,
    val data:ArrayList<UploadLatLong>
)