package com.example.patrollerapp.homepage

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.app.Dialog
import android.app.PictureInPictureParams
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.patrollerapp.db.LatlongInternData
import com.example.patrollerapp.homepage.viewmodel.HomePageViewModel
import com.example.patrollerapp.login.MainActivity
import com.example.patrollerapp.util.LocationService
import com.example.patrollerapp.util.LocationService.Companion.is_canceled_by_me
import com.example.patrollerapp.util.PatrollerPriference
import com.example.patrollerapp.util.Util
import com.example.trackermodule.R
import com.example.trackermodule.databinding.ActivityHomePageBinding
import com.example.trackermodule.util.EasyLocationProvider
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.gson.Gson


class HomePage : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    var easyLocationProvider: EasyLocationProvider? = null
    lateinit var homePageBinding: ActivityHomePageBinding
    lateinit var homePageViewModel: HomePageViewModel
    lateinit var mActivity: Activity
    lateinit var mServiceIntent: Intent
    var is_first_time: Boolean = true
    var t: Thread? = null
    var mLocationService: LocationService = LocationService()
    var mapFragment: SupportMapFragment? = null

    var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePageBinding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(homePageBinding.root)
        mActivity = this@HomePage
        homePageViewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)
        homePageViewModel.init(mActivity)
        initVariable()
//        homePageViewModel.fetchUserDetails(this)
        if (!Util.isLocationEnabledOrNot(mActivity)) {
            Util.showAlertLocation(
                mActivity,
                getString(R.string.gps_enable),
                getString(R.string.please_turn_on_gps),
                getString(
                    R.string.ok
                )
            )
        }
        dialog = Util.getdialouge(this)
        dialog!!.show()
        homePageBinding.versiontext.text =
            "Version:- 2, android:- " + android.os.Build.VERSION.SDK_INT
        homePageBinding.logout.setOnClickListener {
            let {
                startActivity(Intent(this@HomePage, MainActivity::class.java))
            }
            finish()
        }
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment!!.getMapAsync(this)
        easyLocationProvider = EasyLocationProvider.Builder(this@HomePage).setInterval(5000)
            .setFastestInterval(2000) //.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setListener(object : EasyLocationProvider.EasyLocationCallback {
                override fun onGoogleAPIClient(googleApiClient: GoogleApiClient, message: String) {
                    Log.e("EasyLocationProvider", "onGoogleAPIClient: $message")
                }

                override fun onLocationUpdated(latitude: Double, longitude: Double) {
                    Log.e(
                        "EasyLocationProvider",
                        "onLocationUpdated:: Latitude: $latitude Longitude: $longitude"
                    )
                    moveToMyLocation(LatLng(latitude, longitude))
//                    lifecycle.removeObserver(easyLocationProvider!!)
//                    easyLocationProvider!!.removeUpdates()

                }

                override fun onLocationUpdateRemoved() {
                    Log.e("EasyLocationProvider", "onLocationUpdateRemoved")
                }
            }).build()

        lifecycle.addObserver(easyLocationProvider!!)
    }

    private fun initVariable() {
        mLocationService = LocationService()
        mServiceIntent = Intent(this, mLocationService.javaClass)
        homePageBinding.expandArrow.setOnClickListener {
            /* if (homePageBinding.expandView.visibility == View.VISIBLE) {
                 homePageBinding.expandView.visibility = View.GONE
                 homePageBinding.expandArrow.rotation = 0f
             } else {
                 homePageBinding.expandView.visibility = View.VISIBLE
                 homePageBinding.expandArrow.rotation = 180f
             }*/
        }
//        if (!Util.isMyServiceRunning(mLocationService.javaClass, mActivity)) {
//            homePageBinding.start.visibility = View.VISIBLE
//            homePageBinding.pause.visibility = View.GONE
//            homePageBinding.stop.visibility = View.GONE
//        } else {
//            startClock(PatrollerPriference(this@HomePage).getstartTime())
//            homePageBinding.start.visibility = View.GONE
//            homePageBinding.pause.visibility = View.VISIBLE
//            homePageBinding.stop.visibility = View.VISIBLE
//        }
        if ((PatrollerPriference(this).getPtrollingStatus()).equals(
                PatrollerPriference.PATROLING_STATUS_PAUSE, ignoreCase = true
            )
        ) {
            homePageBinding.start.visibility = View.GONE
            homePageBinding.pause.visibility = View.VISIBLE
            homePageBinding.stop.visibility = View.VISIBLE
            homePageBinding.pause.text = "Resume"

        } else if ((PatrollerPriference(this).getPtrollingStatus()).equals(
                PatrollerPriference.PATROLING_STATUS_running, ignoreCase = true
            )
        ) {

            startServiceBackground()
            startClock(PatrollerPriference(this@HomePage).getstartTime())
            homePageBinding.start.visibility = View.GONE
            homePageBinding.pause.visibility = View.VISIBLE
            homePageBinding.stop.visibility = View.VISIBLE
            homePageBinding.pause.text = "Pause"

        } else if ((PatrollerPriference(this).getPtrollingStatus()).equals(
                PatrollerPriference.PATROLING_STATUS_STOP, ignoreCase = true
            )
        ) {
            homePageBinding.start.visibility = View.VISIBLE
            homePageBinding.pause.visibility = View.GONE
            homePageBinding.stop.visibility = View.GONE
        }
        if (!PatrollerPriference(this).gettime().isEmpty()) {
            homePageBinding.duraion.text = PatrollerPriference(this).gettime()
        }

        homePageBinding.time.text = Util.getStartTimeString(this)

        homePageBinding.start.setOnClickListener {
            PatrollerPriference(this@HomePage).setstartTime(System.currentTimeMillis())
            PatrollerPriference(this@HomePage).setPauseTimestamp(0)
            PatrollerPriference(this@HomePage).setTotalPauseTime(0)
            homePageBinding.time.text = Util.getStartTimeString(this)
            startClock(PatrollerPriference(this@HomePage).getstartTime())
            homePageBinding.start.visibility = View.GONE
            homePageBinding.pause.visibility = View.VISIBLE
            homePageBinding.stop.visibility = View.VISIBLE
            PatrollerPriference(this).setPtrollingStatus(PatrollerPriference.PATROLING_STATUS_running)
            homePageViewModel.delate()
            PatrollerPriference(this).settime("")
            PatrollerPriference(this).setStartLattitude("Na")
            PatrollerPriference(this).setStartLongitude("Na")
//            PatrollerPriference(this).settime("")

            startServiceBackground()

        }
        homePageBinding.stop.setOnClickListener {
            if (mServiceIntent != null) {
//                homePageViewModel.setTime(
//                    Util.getstartTime(this@HomePage),
//                    System.currentTimeMillis(),
//                    homePageBinding.duraion
//                )
                if (t != null) {
                    t!!.interrupt()
                }
                PatrollerPriference(this).setPtrollingStatus(PatrollerPriference.PATROLING_STATUS_STOP)
                is_canceled_by_me = true
                stopService(mServiceIntent)
                PatrollerPriference(this).settime(homePageBinding.duraion.text.toString())
                homePageBinding.start.visibility = View.VISIBLE
                homePageBinding.pause.visibility = View.GONE
                homePageBinding.stop.visibility = View.GONE
            }
        }
        homePageBinding.pause.setOnClickListener {
            if ((PatrollerPriference(this).getPtrollingStatus()).equals(
                    PatrollerPriference.PATROLING_STATUS_PAUSE, ignoreCase = true
                )
            ) {
                //this is resumed is clcked
                var previouspausetme = PatrollerPriference(this).getTotalPauseTime()
                if (PatrollerPriference(this).getPauseTimestamp() > 0) {
                    val current_pause_time =
                        System.currentTimeMillis() - PatrollerPriference(this).getPauseTimestamp()
                    previouspausetme = previouspausetme + current_pause_time;
                }
                PatrollerPriference(this).setTotalPauseTime(previouspausetme)
                PatrollerPriference(this).setPauseTimestamp(0)

                homePageBinding.pause.text = "Pause"
                PatrollerPriference(this).setPtrollingStatus(PatrollerPriference.PATROLING_STATUS_running)
            } else {
                //this is paused is clicked
                PatrollerPriference(this).setPauseTimestamp(System.currentTimeMillis())
                homePageBinding.pause.text = "Resume"
                PatrollerPriference(this).setPtrollingStatus(PatrollerPriference.PATROLING_STATUS_PAUSE)

            }
        }
    }

    private fun setMapComponent() {
        homePageBinding.mapType.visibility = View.VISIBLE
        homePageBinding.mapType.setOnClickListener {
            homePageViewModel.showMapTypeSelectorDialog(this@HomePage, mMap)
        }
    }

    private fun startClock(millis: Long) {
        t = object : Thread() {
            override fun run() {
                try {
                    while (!isInterrupted) {
                        sleep(1000)
                        runOnUiThread {
                            /* val c: Calendar = Calendar.getInstance()
                             val date = Date(timeMili)
                             c.time = date
                             val hours: Int = c.get(Calendar.HOUR_OF_DAY)
                             val minutes: Int = c.get(Calendar.MINUTE)
                             val seconds: Int = c.get(Calendar.SECOND)
                             val curTime =
                                 String.format("%02d : %02d : %02d", hours, minutes, seconds)*/
                            /*  val curTime =   String.format("%d min, %d sec",
                                  TimeUnit.MILLISECONDS.toMinutes(millis),
                                  TimeUnit.MILLISECONDS.toSeconds(millis) -
                                          TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
                              );*/
                            /*  val date = Date(millis)
                              val timedata =  SimpleDateFormat("mm:ss:SSS").format(date)
                              homePageBinding.duraion.text = timedata*///change clock to your textview

                            homePageViewModel.setTime(this@HomePage, homePageBinding.duraion)
                            println("this is called")
                        }
                    }
                } catch (e: InterruptedException) {
                }
            }
        }
        t!!.start()
    }

    private fun startServiceBackground() {
        mLocationService = LocationService()
        if (!Util.isMyServiceRunning(mLocationService.javaClass, mActivity)) {
            startService(mServiceIntent)
            Toast.makeText(
                mActivity, getString(R.string.service_start_successfully), Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                mActivity, getString(R.string.service_already_running), Toast.LENGTH_SHORT
            ).show()
        }

    }

    fun drawPolyLineOnMap(list: List<LatLng?>) {
        if (list!!.size > 0) {
            addmarker(list.get(0)!!, "Start Point", R.drawable.start_marker)
            if ((PatrollerPriference(this).getPtrollingStatus()).equals(
                    PatrollerPriference.PATROLING_STATUS_running, ignoreCase = true
                )
            ) {
                addmarker(list.get(list.size - 1)!!, "End Point", R.drawable.patroller_bike_blue)
            } else {
                addmarker(list.get(list.size - 1)!!, "End Point", R.drawable.end_marker)

            }
            val polyOptions = PolylineOptions()
            polyOptions.color(Color.parseColor("#2978ED"))
            polyOptions.width(6f)
            polyOptions.addAll(list)
            mMap.addPolyline(polyOptions)
            val builder = LatLngBounds.Builder()
            for (latLng in list) {
                builder.include(latLng!!)
            }
            val bounds = builder.build()

            //BOUND_PADDING is an int to specify padding of bound.. try 100.
            val cu = CameraUpdateFactory.newLatLngBounds(bounds, 10)
//        mMap.animateCamera(cu)
        }
    }

    fun addmarker(latLng: LatLng, titel: String, resources: Int) {
        mMap.addMarker(
            MarkerOptions().position(latLng).title(titel)
                .icon(Util.BitmapFromVector(this, resources))

        )!!.showInfoWindow()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.clear()
        setMapComponent()
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mMap.isMyLocationEnabled = true
        val locationButton = (mapFragment!!.requireView()
            .findViewById<View>(Integer.parseInt("1")).parent as View).findViewById<View>(
            Integer.parseInt(
                "2"
            )
        )
        val rlp = locationButton.layoutParams as (RelativeLayout.LayoutParams)
        // position on right bottom
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0)
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
        rlp.setMargins(0, 0, 30, 30);
        dialog!!.dismiss()
        setObserver()
    }

    fun moveToMyLocation(latLng: LatLng) {
//        if (is_first_time) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15f), 2000, null);
//            is_first_time = false
//        }
    }

    fun setObserver() {
//        homePageViewModel.getAllCourses()
        homePageViewModel.getAllCourses().observe(this, Observer {
            /* println("pring size " + it.size)
             var list: ArrayList<LatLng> = ArrayList<LatLng>()
             var listpath: ArrayList<PathData> = ArrayList<PathData>()
             for (latLong in it) {
                 list.add(LatLng(latLong.lattitiude, latLong.longitude))
             }


             for (i in 0 until list.size) {
                 if (i != list.size - 1) {
                     try {
                         listpath.add(PathData(list.get(i), list.get(i + 1)))
                     } catch (e: Exception) {
                         e.printStackTrace()
                     }
                 }
             }*/

            var list: ArrayList<LatLng> = ArrayList<LatLng>()
            var total_distance = 0.0
            for (latLongdata in it) {
                var gson = Gson()
                println("database data " + latLongdata.betweenlatlongjson)
                println("database data distance " + latLongdata.root_distance)
                if (latLongdata.betweenlatlongjson != null && !latLongdata.betweenlatlongjson.isEmpty()) {
                    var latlongInternData: LatlongInternData =
                        gson.fromJson(latLongdata.betweenlatlongjson, LatlongInternData::class.java)
                    if (latlongInternData != null) {
                        list.addAll(latlongInternData.latlongList)
                    }
                }
                total_distance = total_distance + latLongdata.root_distance
            }

            homePageViewModel.route_list.postValue(list)
            homePageViewModel.setDistance(total_distance)
        })
        homePageViewModel.distance.observe(this, Observer {
            homePageBinding.distance.text = String.format("%.2f", (it / 1000)) + " km"
        })
        homePageViewModel.route_list!!.observe(this, Observer {
            println("list size id " + it.size)
            mMap.clear()
            drawPolyLineOnMap(it)
        })

        homePageViewModel.updatePendingLatlong(this)
    }


    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(
        permissions: Array<String>, requestCode: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions!!, requestCode)
        }
    }

    override fun onDestroy() {
        easyLocationProvider!!.removeUpdates()
        if (mServiceIntent != null) {
            stopService(mServiceIntent)
        }
        lifecycle.removeObserver(easyLocationProvider!!)
        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun enterPipMode() {
        /*  val d = windowManager.defaultDisplay
          val p = Point()
          d.getSize(p)
          val width: Int = p.x
          val height: Int = p.y*/

        val ratio = Rational(1, 1)
        val pip_Builder = PictureInPictureParams.Builder()
        pip_Builder.setAspectRatio(ratio).build()
        enterPictureInPictureMode(pip_Builder.build())
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onUserLeaveHint() {
        if (PatrollerPriference(this).getPtrollingStatus()
                .equals(PatrollerPriference.PATROLING_STATUS_running)
        ) {
            enterPipMode()
        } else {
            super.onUserLeaveHint()
        }
    }

    override fun onBackPressed() {
        if (PatrollerPriference(this).getPtrollingStatus()
                .equals(PatrollerPriference.PATROLING_STATUS_running)
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                enterPipMode()
            }
        } else {
            super.onBackPressed()
        }
    }

//    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
//        super.onPictureInPictureModeChanged(isInPictureInPictureMode)
//
//    }

    @SuppressLint("MissingSuperCall")
    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean, newConfig: Configuration
    ) {
        if (isInPictureInPictureMode) {
            homePageBinding.action.visibility = View.GONE
            homePageBinding.mapType.visibility = View.GONE
        } else {
            homePageBinding.action.visibility = View.VISIBLE
            homePageBinding.mapType.visibility = View.VISIBLE
        }
//        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
    }


}