package com.smarthub.baseapplication.ui.mapui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
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
        latlonglist!!.add(LatlongValue(20.906166, 85.616768))
        latlonglist!!.add(LatlongValue(20.894459, 85.595809))
        latlonglist!!.add(LatlongValue(20.887563, 85.629649))
        latlonglist!!.add(LatlongValue(20.893016, 85.614353))
        setMap()

        binding.filter.setOnClickListener{
            if(binding.filterMain.visibility == View.VISIBLE){
                binding.filterMain.visibility = View.GONE
            }else{
                binding.filterMain.visibility = View.VISIBLE
            }
        }
        binding.apply.setOnClickListener{
            binding.filterMain.visibility = View.GONE
        }
        binding.cancel.setOnClickListener {
            binding.filterMain.visibility = View.GONE
        }

    }

    fun setMap() {
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment!!.getMapAsync(this)
    }


    override fun onMapReady(mMap: GoogleMap) {
        for (latlong in latlonglist!!) {

            mMap.addMarker(
                MarkerOptions().position(LatLng(latlong.lattitue, latlong.longitude))
                    .title("Marker")
                    .icon(BitmapFromVector(getApplicationContext(), R.drawable.map_marker))
            )
            mMap.animateCamera(CameraUpdateFactory.zoomTo(8.0f));

            mMap.moveCamera(
                CameraUpdateFactory.newLatLng(
                    LatLng(
                        latlong.lattitue,
                        latlong.longitude
                    )
                )
            );
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

}