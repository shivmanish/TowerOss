package com.example.patrollerapp.homepage.viewmodel

import android.R
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.patrollerapp.db.CourseRepository
import com.example.patrollerapp.db.LatlongData
import com.example.patrollerapp.homepage.pojo.UserDetailsService
import com.example.patrollerapp.homepage.pojo.response.UserDataResponse
import com.example.patrollerapp.util.PatrollerPriference
import com.example.patrollerapp.util.Util
import com.example.trackermodule.server.APIClientPatroller
import com.example.trackermodule.server.APIInterface
import com.example.trackermodule.server.PathData
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*
import kotlin.math.roundToInt


class HomePageViewModel : ViewModel() {
    private var repository: CourseRepository? = null
    private val MAP_TYPE_ITEMS = arrayOf<CharSequence>("Road Map", "Hybrid", "Satellite", "Terrain")
    private var allCourses: LiveData<List<LatlongData>>? = null
    var distance = MutableLiveData<Double>()
    private var position = 0
    private var pathDataArray: ArrayList<PathData>? = null
    public var distance_value = 0.0;

    var route_list = MutableLiveData<ArrayList<LatLng>>()

    fun init(application: Context) {
        repository = CourseRepository(application)
        allCourses = repository!!.allCourses
    }

    fun getAllCourses(): LiveData<List<LatlongData>> {
        return allCourses!!
    }

    fun updatePendingLatlong(context: Context){
        Util.updateLocation(context)
    }


   /* fun setpathData(pathDataArray: ArrayList<PathData>) {
        this.pathDataArray = pathDataArray;
        println("size is " + pathDataArray.size)
        drawPath()
    }*/

/*
    fun drawPath() {
        if (position != pathDataArray!!.size) {
            if (Util.internetIsConnected()) {
                getData(
                    pathDataArray!!.get(position).start_location,
                    pathDataArray!!.get(position).end_location
                )

            } else {
                drawPathManually(
                    pathDataArray!!.get(position).start_location,
                    pathDataArray!!.get(position).end_location
                )
            }
            position++
        }

    }
*/

    fun setStopTime(context: Context) {
        val start_timrstamp = PatrollerPriference(context).getstartTime()
        val end_timrstamp = System.currentTimeMillis()
        val diffInMillisec: Long = end_timrstamp - start_timrstamp
        println("different is " + diffInMillisec)
    }

    fun setTime(context: Context, text_view: TextView) {
        if(PatrollerPriference(context).getPtrollingStatus().equals(PatrollerPriference.PATROLING_STATUS_running)) {
            val start_timrstamp = PatrollerPriference(context).getstartTime()
            val end_timrstamp = System.currentTimeMillis()
            var diffInMillisec: Long = end_timrstamp - start_timrstamp
            val pausetime = PatrollerPriference(context).getTotalPauseTime()
            diffInMillisec = diffInMillisec - pausetime
            println("different is " + diffInMillisec)

            val curTime = String.format(
                "%d min, %d sec",
                diffInMillisec / 1000 / 60,
                diffInMillisec / 1000 % 60
            )

            text_view.text = curTime
        }
    }

/*
    @Synchronized
    fun getData(fromPosition: LatLng, toPosition: LatLng) {
        val retrofit: Retrofit = APIClientGoogle.getClient()
        val apiInference = retrofit.create(APIInterface::class.java)
        val call = apiInference.getJson(
            fromPosition.latitude.toString() + "," + fromPosition.longitude.toString(),
            toPosition.latitude.toString() + "," + toPosition.longitude.toString(),
            "AIzaSyBCSDxhrQ5AZYQlFF5Q67qsN8TvumEGco8"
        )
        println("somnath usl " + call.request().url)
        call.enqueue(
            object : Callback<DirectionResults> {
                override fun onResponse(
                    call: Call<DirectionResults>,
                    response: Response<DirectionResults>
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
                    if (dis < 250) {
                        if (routelist.size > 0) {

                            route_list!!.postValue(routelist)
                            distance_value = distance_value + dis
                            distance.postValue(distance_value)
                            var update_data = LatlongInternData()
                            update_data.latlongList.addAll(routelist)
                            var final_data = LatlongData()


                            */
/*     val rectLine = PolylineOptions().width(10f).color(
                                     Color.RED
                                 )
                                 for (i in 0 until routelist.size) {
                                     rectLine.add(routelist[i])
                                 }
                                 // Adding route on the map
                                 mMap.addPolyline(rectLine)
                                 markerOptions.position(toPosition)
                                 markerOptions.draggable(true)
                                 mMap.addMarker(markerOptions)*//*

                        }
                    } else if (dis < 500) {
                        val routelist = ArrayList<LatLng>()
                        routelist.add(fromPosition)
                        routelist.add(toPosition)
                        route_list.postValue(routelist)
                        getDistanceFromLatlongmanually(fromPosition, toPosition)
                    } else {
                        //skip that
                    }
                    drawPath()

                }

                override fun onFailure(call: Call<DirectionResults>, t: Throwable) {
                    val routelist = ArrayList<LatLng>()
                    routelist.add(fromPosition)
                    routelist.add(toPosition)
                    route_list.postValue(routelist)
                    getDistanceFromLatlongmanually(fromPosition, toPosition)
                    drawPath()
                }

            }
        )

    }
*/

    fun delate() {
        repository!!.delate()
    }

/*
    fun drawPathManually(fromPosition: LatLng, toPosition: LatLng) {
        val routelist = ArrayList<LatLng>()
        routelist.add(fromPosition)
        routelist.add(toPosition)
        route_list.postValue(routelist)
        getDistanceFromLatlongmanually(fromPosition, toPosition)
        Timer().schedule(timerTask {
            drawPath()
        }, 1000)

    }
*/

    fun getDistanceFromLatlongmanually(fromPosition: LatLng, toPosition: LatLng) {
        val locationA = android.location.Location("Point A")
        locationA.setLatitude(fromPosition.latitude)
        locationA.setLongitude(fromPosition.longitude)
        val locationB = android.location.Location("point B")
        locationB.setLatitude(toPosition.latitude)
        locationB.setLongitude(toPosition.longitude)

        val distancedata: Float = locationA.distanceTo(locationB)

        distance_value = distance_value + distancedata.roundToInt()
        distance.postValue(distance_value)

        println("distance between two " + distance)
    }

    fun setDistance(distancedata:Double){
//        distance_value = distance_value + distancedata
        distance.postValue(distancedata)
    }

    fun showMapTypeSelectorDialog(context: Context, mMap: GoogleMap) {
        // Prepare the dialog by setting up a Builder.
        val fDialogTitle = "Select Map Type"
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(fDialogTitle)

        // Find the current map type to pre-check the item representing the current state.
        val checkItem: Int = mMap.getMapType() - 1

        // Add an OnClickListener to the dialog, so that the selection will be handled.
        builder.setSingleChoiceItems(
            MAP_TYPE_ITEMS,
            checkItem,
            DialogInterface.OnClickListener { dialog, item -> // Locally create a finalised object.

                // Perform an action depending on which item was selected.
                when (item) {
                    1 -> mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE)
                    2 -> mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN)
                    3 -> mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID)
                    else -> mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL)
                }
                dialog.dismiss()
            }
        )

        // Build the dialog and show it.
        val fMapTypeDialog: AlertDialog = builder.create()
        fMapTypeDialog.setCanceledOnTouchOutside(true)
        fMapTypeDialog.show()
    }


    fun fetchUserDetails(context: Context) {
//        var dialog = Util.getdialouge(context)
//        dialog.show()
        val retrofit: Retrofit = APIClientPatroller.getClient()
        val apiInference = retrofit.create(APIInterface::class.java)
        val call = apiInference.getUserDetails(
            UserDetailsService(
                device_id = PatrollerPriference(context).getDeviceID(),
                mobile = PatrollerPriference(context).getPhonenumber()
            )
        )
        call.enqueue(object : Callback<UserDataResponse> {
            override fun onResponse(
                call: Call<UserDataResponse>,
                response: Response<UserDataResponse>
            ) {
//                dialog.dismiss()
                if(response.isSuccessful){
                    val responseData = response.body()
                    println("response is " + response.body())
                    if (responseData!!.Message == null) {
                        PatrollerPriference(context).setUserId(responseData.ID)

                    } else {
                        showAllert(context as Activity)
//                    Toast.makeText(context, responseData!!.Message, Toast.LENGTH_SHORT).show()
                    }
                }else{
                    showAllert(context as Activity)

                }
            }

            override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
//                dialog.dismiss()
                showAllert(context as Activity)
                println("error is " + t.message)

            }
        })
    }



    fun showAllert(context: Activity){
    AlertDialog.Builder(context)
        .setTitle("User Details Not found!")
        .setMessage("There are some error to find User Details, Please contact Admin.") // Specifying a listener allows you to take an action before dismissing the dialog.
        // The dialog is automatically dismissed when a dialog button is clicked.
        .setPositiveButton(
            "OK"
        ) { dialog, which ->
            context.finish()
            // Continue with delete operation
        } // A null listener allows the button to dismiss the dialog and take no further action.
        .setNegativeButton(R.string.no, null)
        .setIcon(R.drawable.ic_dialog_alert)
        .show()
}

}