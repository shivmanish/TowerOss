package com.example.patrollerapp.util

import android.Manifest
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.*
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.patrollerapp.db.CourseRepository
import com.example.patrollerapp.db.LatlongData
import com.example.patrollerapp.db.LatlongInternData
import com.example.patrollerapp.homepage.pojo.UploadLatLong
import com.example.patrollerapp.homepage.pojo.response.UserDataResponse
import com.example.trackermodule.server.APIClientPatroller
import com.example.trackermodule.server.APIInterface
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*


class LocationService : Service() {

    companion object {
        var is_canceled_by_me = false
    }

    private var handlerThread: HandlerThread? = null
    private var backgroundHandler: Handler? = null
    var latlongarray = ArrayList<LatLng>()
    var counter = 0
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    private val TAG = "LocationService"
    private var repository: CourseRepository? = null
    private val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 10000
    private val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2
    override fun onCreate() {
        super.onCreate()
        repository = CourseRepository(application)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) createNotificationChanel() else startForeground(
            1, Notification()
        )


//         latlongarray.add(LatLng(20.861668,85.674709))
//         latlongarray.add(LatLng(20.861989,85.673249))
//         latlongarray.add(LatLng(20.862310,85.672379))
//         latlongarray.add(LatLng(20.862933,85.670827))
//         latlongarray.add(LatLng(20.863475,85.669302))
//         latlongarray.add(LatLng(20.863824,85.667862))

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChanel() {
        val NOTIFICATION_CHANNEL_ID = "com.getlocationbackground"
        val channelName = "Background Service"
        val chan = NotificationChannel(
            NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE
        )
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val manager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(chan)
        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        val notification: Notification =
            notificationBuilder.setOngoing(true).setContentTitle("App is running count::" + counter)
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE).build()
        startForeground(2, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        requestLocationUpdates()
        startTimer()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stoptimertask()
        val broadcastIntent = Intent()
        broadcastIntent.action = "restartservice"
        broadcastIntent.setClass(this, RestartBackgroundService::class.java)
        this.sendBroadcast(broadcastIntent)
    }

    private var timer: Timer? = null
    private var timerTask: TimerTask? = null
    fun startTimer() {
        timer = Timer()
        timerTask = object : TimerTask() {
            override fun run() {
                var count = counter++
                print("this is called")
//                Toast.makeText(this@LocationService, "this is called", Toast.LENGTH_SHORT).show()
                if (latitude != 0.0 && longitude != 0.0) {
                    if ((PatrollerPriference(this@LocationService).getPtrollingStatus()).equals(
                            PatrollerPriference.PATROLING_STATUS_running, ignoreCase = true
                        )
                    ) {
                        getExtradata(latitude, longitude)
                    }
                    Log.d(
                        "Location::",
                        latitude.toString() + ":::" + longitude.toString() + "Count" + count.toString()
                    )
                }
            }
        }

        timer!!.scheduleAtFixedRate(
            timerTask, 0, 20000
        ) //1 * 60 * 1000 1 minute
    }

    fun stoptimertask() {
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun requestLocationUpdates() {
        val request = LocationRequest.create().apply {
            interval = UPDATE_INTERVAL_IN_MILLISECONDS
            fastestInterval = FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
            smallestDisplacement = 30f
            priority = Priority.PRIORITY_HIGH_ACCURACY
        }
        val client: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)

        val permission = ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (permission == PackageManager.PERMISSION_GRANTED) { // Request location updates and when an update is
            // received, store the location in Firebase
/*
            client.requestLocationUpdates(request, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    val location: Location? = locationResult.lastLocation
                    if (location != null) {
                        latitude = location.latitude
                        longitude = location.longitude
                        Log.d(
                            "Location somnath",
                            "location update " + location.latitude + " - " + location.longitude
                        )
                        if ((PatrollerPriference(this@LocationService).getPtrollingStatus()).equals(
                                PatrollerPriference.PATROLING_STATUS_running, ignoreCase = true
                            )
                        ) {
                            getExtradata(latitude,longitude)
                        }                    }
                }
            }, null)
*/
            var locationCallback: LocationCallback? = null
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    val location: Location? = locationResult.lastLocation
                    if (location != null) {
                        latitude = location.latitude
                        longitude = location.longitude
                        Toast.makeText(
                            this@LocationService,
                            "location update " + location.latitude + " - " + location.longitude,
                            Toast.LENGTH_SHORT
                        )
                        Log.d(
                            "Location somnath",
                            "location update " + location.latitude + " - " + location.longitude
                        )


                    }
                }
            }
            client.requestLocationUpdates(request, locationCallback, Looper.myLooper())

        }
    }

    fun getExtradata(lattitude: Double, longitude: Double) {
        if (PatrollerPriference(this).getStartLattitude().equals("NA", ignoreCase = true)) {
            Util.setStartLatlong(this, lattitude, longitude)
        } else {
            val latlongdata = LatlongData()
            latlongdata.start_lattitiude = PatrollerPriference(this).getStartLattitude().toDouble()
            latlongdata.start_longitude = PatrollerPriference(this).getStartLongitude().toDouble()
            latlongdata.end_lattitiude = lattitude
            latlongdata.end_longitude = longitude
            Util.setStartLatlong(this, lattitude, longitude)
            getData(latlongdata)
        }

//
    }


    @Synchronized
    fun getData(latlongdata: LatlongData) {

        var fromPosition = LatLng(latlongdata.start_lattitiude, latlongdata.start_longitude)
        var toPosition = LatLng(latlongdata.end_lattitiude, latlongdata.end_longitude)
        /* val retrofit: Retrofit = APIClientGoogle.getClient()
         val apiInference = retrofit.create(APIInterface::class.java)
         val call = apiInference.getJson(
             fromPosition.latitude.toString() + "," + fromPosition.longitude.toString(),
             toPosition.latitude.toString() + "," + toPosition.longitude.toString(),
             "AIzaSyBCSDxhrQ5AZYQlFF5Q67qsN8TvumEGco8"
         )
         println("somnath usl " + call.request().url)
         call.enqueue(object : Callback<DirectionResults> {
             override fun onResponse(
                 call: Call<DirectionResults>, response: Response<DirectionResults>
             ) {
                 val directionResults: DirectionResults? = response.body()
                 val routelist = ArrayList<LatLng>()
                 var dis: Double = 0.0
                 if (directionResults?.getRoutes()!!.size > 0) {
                     var decodelist: ArrayList<LatLng>?
                     val routeA: Route = directionResults.getRoutes().get(0)
                     Log.i("zacharia", "Legs length : " + routeA.getLegs().size)
                     if (routeA.getLegs().size > 0) {
                         val steps: List<Steps> = routeA.getLegs().get(0).getSteps()
                         Log.i("zacharia", "Steps size :" + steps.size)
                         var step: Steps
                         var location: com.example.patrollerapp.server.Location
                         var polyline: String?

                         for (i in steps.indices) {
                             step = steps[i]
                             location = step!!.start_location
                             println("distance is " + step.distance.value)
                             dis = dis + step.distance.value
                             routelist.add(LatLng(location.lat, location.lng))
                             Log.i(
                                 "zacharia",
                                 "Start Location :" + location.lat.toString() + ", " + location.lng
                             )
                             polyline = step.polyline.getPoints()
                             decodelist = RouteDecode.decodePoly(polyline)
                             routelist.addAll(decodelist)
                             location = step.end_location
                             routelist.add(LatLng(location.lat, location.lng))
                             Log.i(
                                 "zacharia",
                                 "End Location :" + location.lat.toString() + ", " + location.lng
                             )
                         }
                     }
                 }
                 Log.i("zacharia", "routelist size : " + routelist.size)
                 if (dis < 500) {
                     if (routelist.size > 0) {

                         var latlongInternData = LatlongInternData(latlongList = routelist)
                         val gson = Gson()
                         val jsonlatlong = gson.toJson(latlongInternData)
                         latlongdata.betweenlatlongjson = jsonlatlong
                         latlongdata.root_distance = dis

                     }
                 } else if (dis < 650) {
                     val routelist = ArrayList<LatLng>()
                     routelist.add(fromPosition)
                     routelist.add(toPosition)
                     var latlongInternData = LatlongInternData(latlongList = routelist)
                     val gson = Gson()
                     val jsonlatlong = gson.toJson(latlongInternData)
                     latlongdata.betweenlatlongjson = jsonlatlong
                     latlongdata.root_distance = dis
                 } else {
                     *//* println("latlog between distnce is exceed to 800 mtr so skip that")
                     var latlongInternData = LatlongInternData()
                     val gson = Gson()
                     val jsonlatlong = gson.toJson(latlongInternData)
                     latlongdata.betweenlatlongjson = jsonlatlong
                     latlongdata.root_distance = 0.0*//*

                    val routelist = ArrayList<LatLng>()
                    routelist.add(fromPosition)
                    routelist.add(toPosition)
                    var latlongInternData = LatlongInternData(latlongList = routelist)
                    val gson = Gson()
                    val jsonlatlong = gson.toJson(latlongInternData)
                    latlongdata.betweenlatlongjson = jsonlatlong
                    latlongdata.root_distance =
                        getDistanceFromLatlongmanually(fromPosition, toPosition)
                    println("insert is called from 4")

                }
                println("insert is called from 1")
                repository!!.insert(latlongdata)
                updateLocation(this@LocationService, latlongdata)

            }

            override fun onFailure(call: Call<DirectionResults>, t: Throwable) {
                val routelist = ArrayList<LatLng>()
                routelist.add(fromPosition)
                routelist.add(toPosition)
                var latlongInternData = LatlongInternData(latlongList = routelist)
                val gson = Gson()
                val jsonlatlong = gson.toJson(latlongInternData)
                latlongdata.betweenlatlongjson = jsonlatlong
                latlongdata.root_distance = getDistanceFromLatlongmanually(fromPosition, toPosition)
                println("insert is called from 2")
                repository!!.insert(latlongdata)
                updateLocation(this@LocationService, latlongdata)
            }
        })*/

        val routelist = ArrayList<LatLng>()
        routelist.add(fromPosition)
        routelist.add(toPosition)
        var latlongInternData = LatlongInternData(latlongList = routelist)
        val gson = Gson()
        val jsonlatlong = gson.toJson(latlongInternData)
        latlongdata.betweenlatlongjson = jsonlatlong
        latlongdata.root_distance = getDistanceFromLatlongmanually(fromPosition, toPosition)
        println("insert is called from 2")
        repository!!.insert(latlongdata)
        updateLocation(this@LocationService, latlongdata)

    }

    fun getDistanceFromLatlongmanually(fromPosition: LatLng, toPosition: LatLng): Double {
        val locationA = android.location.Location("Point A")
        locationA.setLatitude(fromPosition.latitude)
        locationA.setLongitude(fromPosition.longitude)
        val locationB = android.location.Location("point B")
        locationB.setLatitude(toPosition.latitude)
        locationB.setLongitude(toPosition.longitude)

        val distancedata: Float = locationA.distanceTo(locationB)

        return distancedata.toDouble()

    }

    fun updateLocation(context: Context, latlongData: LatlongData) {
        val retrofit: Retrofit = APIClientPatroller.getClient()
        val apiInference = retrofit.create(APIInterface::class.java)
        val call = apiInference.updateLatlong(
            UploadLatLong(
                user = PatrollerPriference(context).getUserId(),
                starttime = Util.getCurrentTimeString(context),
                startlat = latlongData.end_lattitiude.toString(),
                startlong = latlongData.end_longitude.toString(),
                distance = latlongData.root_distance
            )
        )
        call.enqueue(object : Callback<UserDataResponse> {
            override fun onResponse(
                call: Call<UserDataResponse>,
                response: Response<UserDataResponse>
            ) {
                if (response.isSuccessful) {
//                Toast.makeText(context,"data upload ok",Toast.LENGTH_SHORT).show()
                    println("response is " + response.body())
                    Util.updateLocation(context)
                } else {
//                    Toast.makeText(context,"data upload NOT ok",Toast.LENGTH_SHORT).show()
                    Util.addToPreference(
                        this@LocationService, uploadLatLong = UploadLatLong(
                            user = PatrollerPriference(context).getUserId(),
                            starttime = Util.getCurrentTimeString(context),
                            startlat = latlongData.end_lattitiude.toString(),
                            startlong = latlongData.end_longitude.toString(),
                            distance = latlongData.root_distance
                        )
                    )
                }
            }

            override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                Util.addToPreference(
                    this@LocationService, uploadLatLong = UploadLatLong(
                        user = PatrollerPriference(context).getUserId(),
                        starttime = Util.getCurrentTimeString(context),
                        startlat = latlongData.end_lattitiude.toString(),
                        startlong = latlongData.end_longitude.toString(),
                        distance = latlongData.root_distance
                    )
                )
                println("error is " + t.message)
//                Toast.makeText(context,"data upload not ok",Toast.LENGTH_SHORT).show()

            }
        })
    }
    override fun onTaskRemoved(rootIntent: Intent?) {
        initAlarm()
        super.onTaskRemoved(rootIntent)
    }

    private fun initAlarm() {
        val alarmMgr = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, LocationService::class.java)
        val alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmMgr[AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() +
                2000] = alarmIntent
    }
}