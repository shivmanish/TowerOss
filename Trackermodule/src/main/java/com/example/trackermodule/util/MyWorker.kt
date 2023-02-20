package com.example.patrollerapp.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.location.Location
import android.os.Build
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
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
import java.text.SimpleDateFormat
import java.util.*

class MyWorker(val mContext: Context, workerParams: WorkerParameters) : Worker(
    mContext, workerParams

)
{
    private var handlerThread: HandlerThread? = null
    private var backgroundHandler: Handler? = null
    private var mLocation: Location? = null
    private var repository: CourseRepository? = null
    var latitude: Double = 0.0
    var longitude: Double = 0.0

    /**
     * Provides access to the Fused Location Provider API.
     */
    private var mFusedLocationClient: FusedLocationProviderClient? = null

    /**
     * Callback for changes in location.
     */
    private var mLocationCallback: LocationCallback? = null
    @SuppressLint("MissingPermission")
    override fun doWork(): Result {

        repository = CourseRepository(mContext)
        Toast.makeText(mContext,"work staeted ",Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onStartJob: STARTING JOB..")
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext)
            mLocationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                }
            }
            val mLocationRequest = LocationRequest()
            mLocationRequest.interval = UPDATE_INTERVAL_IN_MILLISECONDS
            mLocationRequest.fastestInterval = FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
            mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY


        return Result.success()
    }

    fun startTimer() {

        handlerThread = HandlerThread("MyLocationThread");
        handlerThread!!.setDaemon(true);
        handlerThread!!.start();
        backgroundHandler = Handler(handlerThread!!.getLooper())

        // Every other call is up to you. You can update the location,
        // do whatever you want after this part.

        // Sample code (which should call handler.postDelayed()
        // in the function as well to create the repetitive task.)
        backgroundHandler!!.postDelayed(object : Runnable {
            override fun run() {

                if ((PatrollerPriference(mContext).getPtrollingStatus()).equals(
                        PatrollerPriference.PATROLING_STATUS_running, ignoreCase = true
                    )
                ) {
                    getExtradata(latitude, longitude)
                }

            }
        }, 20000)

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
    companion object {
        private const val TAG = "MyWorker"

        /**
         * The desired interval for location updates. Inexact. Updates may be more or less frequent.
         */
        private const val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 10000

        /**
         * The fastest rate for active location updates. Updates will never be more frequent
         * than this value.
         */
        private const val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2
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
    fun  getcurrentDatetime():String{
        val sdf = SimpleDateFormat("MM-dd hh:mm:ss a")
        val currentDateandTime: String = sdf.format(Date())
        return currentDateandTime
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

}