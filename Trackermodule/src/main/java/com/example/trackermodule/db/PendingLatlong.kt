package com.example.patrollerapp.db

import com.example.patrollerapp.homepage.pojo.UploadLatLong

data class PendingLatlong(
    val latlnglist: ArrayList<UploadLatLong>
)