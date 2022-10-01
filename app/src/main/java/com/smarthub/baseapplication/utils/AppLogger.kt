package com.smarthub.baseapplication.utils

import android.util.Log

object AppLogger {

    const val tag = "status"

    fun log(msg : String){
        Log.d(tag,"$msg")
    }
    fun log(tag : String,msg : String){
        Log.d(tag,"$msg")
    }
}