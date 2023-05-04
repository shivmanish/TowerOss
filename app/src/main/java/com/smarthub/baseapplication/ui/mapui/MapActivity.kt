package com.smarthub.baseapplication.ui.mapui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.trackermodule.homepage.BaseActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityMapBinding
import com.smarthub.baseapplication.ui.mapui.pojo.MapMarkerService
import com.smarthub.baseapplication.ui.mapui.pojo.MarkerResponseItem
import com.smarthub.baseapplication.ui.mapui.viewmodel.MapViewModel


class MapActivity : BaseActivity(), OnMapReadyCallback {
    private var mapObj: GoogleMap? = null
    var candraw: Boolean = false
    var mapFragment: SupportMapFragment? = null
    var lat:String? = null
    var long:String? = null
    var rad:String? = null
    lateinit var viewmodel:MapViewModel

    lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this).get(MapViewModel::class.java)
        setContentView(binding.root)
        lat = intent.getStringExtra("lat")
        long = intent.getStringExtra("long")
        rad = intent.getStringExtra("rad")
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        setMap()

        binding.filter.setOnClickListener {
            if (binding.filterMain.visibility == View.VISIBLE) {
                binding.filterMain.visibility = View.GONE
            } else {
                binding.filterMain.visibility = View.VISIBLE
            }
        }
        binding.apply.setOnClickListener {
            binding.filterMain.visibility = View.GONE
        }
        binding.cancel.setOnClickListener {
            binding.filterMain.visibility = View.GONE
        }
        binding.canecl.setOnClickListener {
            binding.filterMain.visibility = View.GONE
        }
        binding.circle.onItemSelectedListener = listener
        binding.cluster.onItemSelectedListener = listener
        binding.mentainancePoint.onItemSelectedListener = listener
        binding.area.onItemSelectedListener = listener
        binding.siteCategory.onItemSelectedListener = listener
        binding.siteTime.onItemSelectedListener = listener
        binding.opcoName.onItemSelectedListener = listener
        viewmodel.mapmarketLivedata!!.observe(this, Observer {
            if(it!=null && it.data!=null) {
                showpointsonmap(it.data)
            }
        })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        binding.back.setOnClickListener {
            this.onBackPressedDispatcher.onBackPressed()
        }
    }

    fun setMap() {
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment!!.getMapAsync(this)
    }


    override fun onMapReady(mMap: GoogleMap) {
        mapObj = mMap
        viewmodel.fetchData(MapMarkerService(Lat = lat!!,Long =long!!, Radius   = rad!!))
    }

    fun showpointsonmap(latlonglist : ArrayList<MarkerResponseItem>){
        if(mapObj!=null) {

            for (latlong in latlonglist!!) {


                var marker = MarkerOptions().position(LatLng(latlong.locLatitude.toDouble(), latlong.locLongitude.toDouble()))
                    .title("Site Name: " + latlong.siteName + "\nSite Id: " + latlong.siteID)
                    .icon(BitmapFromVector(getApplicationContext(), R.drawable.map_marker))
                mapObj!!.addMarker(marker)!!.isVisible = true
                val cameraPosition = CameraPosition.Builder()
                    .target(
                        LatLng(
                            latlong.locLatitude.toDouble(),
                            latlong.locLongitude.toDouble()
                        )
                    ) // Sets the center of the map to location user
                    .zoom(15f) // Sets the zoom
                    .bearing(90f) // Sets the orientation of the camera to east
                    .tilt(40f) // Sets the tilt of the camera to 30 degrees
                    .build() // Creates a CameraPosition from the builder
                mapObj!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


            }
        }
    }

    private fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
        // below line is use to generate a drawable
        var vectorDrawable: Drawable? = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable!!.getIntrinsicWidth(),
            vectorDrawable!!.getIntrinsicHeight()
        );

        // below line is use to create a bitmap for our
        // drawable which we have added.
        var bitmap: Bitmap = Bitmap.createBitmap(
            vectorDrawable!!.getIntrinsicWidth(),
            vectorDrawable!!.getIntrinsicHeight(),
            Bitmap.Config.ARGB_8888
        );

        // below line is use to add bitmap in our canvas.
        var canvas: Canvas = Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable!!.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private val listener: AdapterView.OnItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                (parent.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.grey))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


}