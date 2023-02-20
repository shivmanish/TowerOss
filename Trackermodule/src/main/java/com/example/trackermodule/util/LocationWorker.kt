/*
package com.example.patrollerapp.util

import android.Manifest
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.patrollerapp.db.CourseRepository
import com.example.patrollerapp.db.LatlongData
import com.example.patrollerapp.db.LatlongInternData
import com.example.patrollerapp.homepage.pojo.UploadLatLong
import com.example.patrollerapp.homepage.pojo.response.UserDataResponse
import com.example.patrollerapp.server.APIClientPatroller
import com.example.patrollerapp.server.APIInterface
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*


class LocationWorker( @NonNull val mContext:Context , @NonNull workerParams: WorkerParameters) : Worker(mContext,workerParams) {

    companion object {
        var is_canceled_by_me = false
    }
    var latlongarray = ArrayList<LatLng>()
    var counter = 0
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    private val TAG = "LocationService"
    private var repository: CourseRepository? = null
    override fun doWork(): Result {
        repository = CourseRepository(mContext)

        requestLocationUpdates()





    }

    private fun createNotificationChanel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "Patroller"
            val description: String = "Location Fetching Service Running."
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel("Patroller", name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager: NotificationManager =
                mContext.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(mContext,"Patroller")
                .setSmallIcon(android.R.drawable.ic_menu_mylocation)
                .setContentTitle("New Location Update")
                .setContentText(
                    "Your location update " + getcurrentDatetime()
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(
                    NotificationCompat.BigTextStyle().bigText(
                        "Your location update" + getcurrentDatetime()
                    )
                )

        val notificationManager = NotificationManagerCompat.from(mContext)

        // notificationId is a unique int for each notification that you must define

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1001, builder.build())

    }

  */
/*  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        startTimer()
        return START_STICKY
    }*//*


   */
/* override fun onDestroy() {
        super.onDestroy()
        stoptimertask()
        val broadcastIntent = Intent()
        broadcastIntent.action = "restartservice"
        broadcastIntent.setClass(this, RestartBackgroundService::class.java)
        this.sendBroadcast(broadcastIntent)
    }*//*


    fun  getcurrentDatetime():String{
        val sdf = SimpleDateFormat("MM-dd hh:mm:ss a")
        val currentDateandTime: String = sdf.format(Date())
        return currentDateandTime
    }
  */
/*  private var timer: Timer? = null
    private var timerTask: TimerTask? = null
    fun startTimer() {
        timer = Timer()
        timerTask = object : TimerTask() {
            override fun run() {
                *//*
*/
/* try {
                     println("this is called and lat long ")
                     getExtradata(latlongarray.get(counter).latitude,latlongarray.get(counter).longitude)
                 }catch (e:Exception){
                     e.printStackTrace()
                 }*//*
*/
/*
                var count = counter++

                if (latitude != 0.0 && longitude != 0.0) {
                    Log.d(
                        "Location::",
                        latitude.toString() + ":::" + longitude.toString() + "Count" + count.toString()
                    )
                }
            }
        }
        timer!!.schedule(
            timerTask, 0, 10000
        ) //1 * 60 * 1000 1 minute
    }*//*




    val UPDATE_INTERVAL_IN_MILLISECONDS = (1000 * 30 //30 secs
            ).toLong()
    val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2
    val HORIZONTAL_ACCURACY_IN_METERS = 100

    private fun requestLocationUpdates() {
        val request = LocationRequest.create().apply {
            interval = UPDATE_INTERVAL_IN_MILLISECONDS
            fastestInterval = FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
            smallestDisplacement = 15f
            priority = Priority.PRIORITY_HIGH_ACCURACY
        }
        val client: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(mContext)

        val permission = ContextCompat.checkSelfPermission(
            mContext, Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (permission == PackageManager.PERMISSION_GRANTED) { // Request location updates and when an update is
            // received, store the location in Firebase
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
                        createNotificationChanel()
                        if ((PatrollerPriference(mContext).getPtrollingStatus()).equals(
                                PatrollerPriference.PATROLING_STATUS_running, ignoreCase = true
                            )
                        ) {
                            getExtradata(location.latitude, location.longitude)
                        }
                    }
                }
            }, null)
        }
    }

    fun getExtradata(lattitude: Double, longitude: Double) {
        if (PatrollerPriference(mContext).getStartLattitude().equals("NA", ignoreCase = true)) {
            Util.setStartLatlong(mContext, lattitude, longitude)
        } else {
            val latlongdata = LatlongData()
            latlongdata.start_lattitiude = PatrollerPriference(mContext).getStartLattitude().toDouble()
            latlongdata.start_longitude = PatrollerPriference(mContext).getStartLongitude().toDouble()
            latlongdata.end_lattitiude = lattitude
            latlongdata.end_longitude = longitude
            Util.setStartLatlong(mContext, lattitude, longitude)
            getData(latlongdata)
        }
    }

    @Synchronized
    fun getData(latlongdata: LatlongData) {
        var fromPosition = LatLng(latlongdata.start_lattitiude, latlongdata.start_longitude)
        var toPosition = LatLng(latlongdata.end_lattitiude, latlongdata.end_longitude)


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
        updateLocation(mContext, latlongdata)

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
                        mContext, uploadLatLong = UploadLatLong(
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
                    mContext, uploadLatLong = UploadLatLong(
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



}*/
