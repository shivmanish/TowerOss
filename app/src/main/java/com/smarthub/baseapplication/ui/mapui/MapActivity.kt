package com.smarthub.baseapplication.ui.mapui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityMapBinding


class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    var mapFragment: SupportMapFragment? = null
    var latlonglist: ArrayList<LatlongValue>? = null
    lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        latlonglist = ArrayList<LatlongValue>()
        latlonglist!!.add(LatlongValue(20.906166, 85.616768, "Site one", "01"))
        latlonglist!!.add(LatlongValue(20.894459, 85.595809, "Site two", "02"))
        latlonglist!!.add(LatlongValue(20.887563, 85.629649, "Site three", "03"))
        latlonglist!!.add(LatlongValue(20.893016, 85.614353, "Site four", "04"))
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

    }

    fun setMap() {
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment!!.getMapAsync(this)
    }


    override fun onMapReady(mMap: GoogleMap) {
        for (latlong in latlonglist!!) {


            var marker = MarkerOptions().position(LatLng(latlong.lattitue, latlong.longitude))
                .title("Site Name: " + latlong.sitename+"\nSite Id: " + latlong.site_Id)
                .icon(BitmapFromVector(getApplicationContext(), R.drawable.map_marker))
            mMap.addMarker(marker)!!.isVisible = true
            val cameraPosition = CameraPosition.Builder()
                .target(
                    LatLng(
                        latlong.lattitue,
                        latlong.longitude
                    )
                ) // Sets the center of the map to location user
                .zoom(17f) // Sets the zoom
                .bearing(90f) // Sets the orientation of the camera to east
                .tilt(40f) // Sets the tilt of the camera to 30 degrees
                .build() // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


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