package com.example.patrollerapp.util

import android.content.Context
import android.content.SharedPreferences

class PatrollerPriference(context: Context) {
    private val sharedPreferences: SharedPreferences
    private val context: Context

    init {
        sharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        this.context = context
    }

    companion object {
        private const val SHAREPREFERENCE_NAME = "Patroller_app"
        private const val PHONE_NO = "phone_no"
        private const val DEVICE_ID = "device_id"
        private const val START_LATTITUDE = "start_lattitude"
        private const val START_LONGITUDE = "start_longitude"
        private const val PATROLING_STATUS = "patroling_status"
        private const val TIME = "time"

        private const val USERID = "user_id"
        private const val PAUSE_TIME_STAMP = "pause_time_stamp"
        private const val TOTAL_PAUSE_TIME_STAMP = "total_pause_time_stamp"

        private const val PENDING_LATLONG = "pending_latlong"
        private const val TOKEN_KEY = "tokenkey11"
        public const val APP_VERSION = "app_version"

        public const val PATROLING_STATUS_STOP = "patroling_status_stop"
        public const val PATROLING_STATUS_running = "patroling_status_running"
        public const val PATROLING_STATUS_PAUSE = "patroling_status_pause"
        public const val PATROLING_STATUS_RESTART = "patroling_status_restart"
        public const val TASK_ID = "task_id"
        public const val OWNER_NAME = "Owner_name"
    }

    fun setstartTime( start_mili: Long) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putLong("startmili", start_mili);
        println("time is " + start_mili)
        editor.apply()
    }

    fun setStopTotalTime( stop_mili: Long) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putLong("stoptime", stop_mili);
        println("time is " + stop_mili)
        editor.apply()
    }

    fun getstartTime(): Long {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val time = prefs.getLong("startmili", 0)
        println(" get time is " + time)
        return time
    }

    fun getStopTotalTime(): Long {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val time = prefs.getLong("stoptime", 0)
        println(" get time is stoptime " + time)
        return time
    }

    fun isSaveLocation(): Boolean {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val isSavelocation = prefs.getBoolean("isSavelocation", true)
        println(" is save locaiton " + isSavelocation)
        return isSavelocation
    }

    fun getPhonenumber(): String {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val phone_number = prefs.getString(PHONE_NO, "")
        return phone_number!!
    }

    fun setPhoneNumber( phoneno : String) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(PHONE_NO, phoneno);
        editor.apply()
    }

    fun getDeviceID(): String {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val deviceID = prefs.getString(DEVICE_ID , "")
        return deviceID!!
    }

    fun seDeviceID( deviceid : String) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(DEVICE_ID , deviceid);
        editor.apply()
    }


    fun getStartLattitude(): String {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val lattitude = prefs.getString(START_LATTITUDE , "NA")
        return lattitude!!
    }

    fun setStartLattitude(lattitude : String) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(START_LATTITUDE, lattitude);
        editor.apply()
    }

    fun getStartLongitude(): String {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val longitude = prefs.getString(START_LONGITUDE , "NA")
        return longitude!!
    }

    fun setStartLongitude( longitude : String) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(START_LONGITUDE , longitude);
        editor.apply()
    }
    fun getPtrollingStatus(): String {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val longitude = prefs.getString(PATROLING_STATUS , PATROLING_STATUS_STOP)
        return longitude!!
    }

    fun setPtrollingStatus( status : String) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(PATROLING_STATUS , status);
        editor.apply()
    }


    fun gettime(): String {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val time = prefs.getString(TIME ,"")
        return time!!
    }

    fun settime( time : String) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(TIME , time);
        editor.apply()
    }

    fun getUserId(): String {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val time = prefs.getString(USERID ,"NA")
        return time!!
    }

    fun setUserId( userid : String) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(USERID , userid);
        editor.apply()
    }


    fun getPauseTimestamp(): Long {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val time = prefs.getLong(PAUSE_TIME_STAMP ,0)
        return time!!
    }

    fun setPauseTimestamp( milisecond : Long) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putLong(PAUSE_TIME_STAMP , milisecond);
        editor.apply()
    }


    fun getTotalPauseTime(): Long {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val time = prefs.getLong(TOTAL_PAUSE_TIME_STAMP ,0)
        return time!!
    }

    fun setTotalPauseTime( milisecond : Long) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putLong(TOTAL_PAUSE_TIME_STAMP , milisecond);
        editor.apply()
    }



    fun getPendingLatlong(): String {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val latlongjson = prefs.getString(PENDING_LATLONG ,"")
        return latlongjson!!
    }

    fun setPendinglatlongl( latlongjson : String) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(PENDING_LATLONG , latlongjson);
        editor.apply()
    }


    fun gettokekey(): String {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val token = prefs.getString(TOKEN_KEY ,"")
        return token!!
    }

    fun settokekey( token : String) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(TOKEN_KEY , token);
        editor.apply()
    }

    fun getTaskID(): String {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val taskid = prefs.getString(TASK_ID ,"")
        return taskid!!
    }

    fun setTaskID( taskid : String) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(TASK_ID , taskid);
        editor.apply()
    }

    fun getOwnername(): String {
        val prefs: SharedPreferences = context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE)
        val ownername = prefs.getString(OWNER_NAME ,"")
        return ownername!!
    }

    fun setOwnername( ownername : String) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(TASK_ID , ownername);
        editor.apply()
    }




}