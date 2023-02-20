package com.example.patrollerapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

class LatlongInternData(
    var latlongList: ArrayList<LatLng> = ArrayList<LatLng>()
)