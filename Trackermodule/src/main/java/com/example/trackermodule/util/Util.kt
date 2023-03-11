package com.example.patrollerapp.util

import android.app.Activity
import android.app.ActivityManager
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.LocationManager
import android.net.ConnectivityManager
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.patrollerapp.db.PendingLatlong
import com.example.patrollerapp.homepage.pojo.UploadLatLong
import com.example.patrollerapp.homepage.pojo.response.UserDataResponse
import com.example.trackermodule.R
import com.example.trackermodule.homepage.pojo.UpDateLatlongRequest
import com.example.trackermodule.server.APIClientPatroller
import com.example.trackermodule.server.APIInterface
import com.example.trackermodule.util.MyApplication
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*


object Util {


    fun isMyServiceRunning(serviceClass: Class<*>, mActivity: Activity): Boolean {
        val manager: ActivityManager =
            mActivity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.getClassName()) {
                Log.i("Service status", "Running")
                return true
            }
        }
        Log.i("Service status", "Not running")
        return false
    }

    fun isLocationEnabledOrNot(context: Context): Boolean {
        var locationManager: LocationManager? = null
        locationManager =
            context!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        return locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager!!.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    fun showAlertLocation(context: Context, title: String, message: String, btnText: String) {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setButton(btnText) { dialog, which ->
            dialog.dismiss()
            context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
        alertDialog.show()
    }

    private fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    fun internetIsConnected(): Boolean {
        return try {
            val command = "ping -c 1 google.com"
            Runtime.getRuntime().exec(command).waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }

    fun setStartLatlong(context: Context, lattitude: Double, longitude: Double) {
        PatrollerPriference(context).setStartLattitude(lattitude.toString())
        PatrollerPriference(context).setStartLongitude(longitude.toString())
    }

    fun getdialouge(context: Context): Dialog {
        val customdialog = Dialog(context, R.style.NewDialog)
        customdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customdialog.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent);
        customdialog.setCancelable(false)
        customdialog.setContentView(R.layout.custome_loading_view)
        val window = customdialog.window
        window!!.setLayout(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        window.setGravity(Gravity.CENTER)

        var image: ImageView = customdialog.findViewById(R.id.imageview)
        Glide.with(context).load(R.drawable.phone).into(image)

        return customdialog
    }

    fun getStartTimeString(context: Context): String {
        var value = PatrollerPriference(context).getstartTime()
        if (!value.equals(0.0)) {
            val date = Date(value)
            val df2 = SimpleDateFormat("dd MMM , hh:mm a")
            val dateText: String = df2.format(date)
            println(dateText)
            return dateText
        } else {
            return "-----"
        }
    }

    fun getCurrentTimeString(context: Context): String {
        var value = System.currentTimeMillis()
        val date = Date(value)
        val df2 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dateText: String = df2.format(date)
        println(dateText)
        return dateText
    }

    fun addToPreference(context: Context, uploadLatLong: UploadLatLong) {
        println("addToPreference this is called ")
        var gson = Gson()
        var priferencejson: String = PatrollerPriference(context).getPendingLatlong()
        if (!priferencejson.isEmpty()) {

            var pendingLatlong = gson.fromJson(priferencejson, PendingLatlong::class.java)
            if (pendingLatlong != null) {
                pendingLatlong.latlnglist.add(uploadLatLong)
                var jsondata:String = gson.toJson(pendingLatlong)
//                Toast.makeText(context,"addToPreference saved to priference 1 "+jsondata,Toast.LENGTH_SHORT).show()
                PatrollerPriference(context).setPendinglatlongl(jsondata)
            }else{
                pendingLatlong = PendingLatlong(ArrayList())
                pendingLatlong.latlnglist.add(uploadLatLong)
                var jsondata:String = gson.toJson(pendingLatlong)
//                Toast.makeText(context,"addToPreference saved to priference 2 "+jsondata,Toast.LENGTH_SHORT).show()
                PatrollerPriference(context).setPendinglatlongl(jsondata)
            }
        }else{
            var pendingLatlong = PendingLatlong(ArrayList())
            pendingLatlong.latlnglist.add(uploadLatLong)
            var jsondata:String = gson.toJson(pendingLatlong)
//            Toast.makeText(context,"addToPreference saved to priference 2 "+jsondata,Toast.LENGTH_SHORT).show()
            PatrollerPriference(context).setPendinglatlongl(jsondata)
        }
    }


    fun updateLocation(context: Context) {
        var priferencejson: String = PatrollerPriference(context).getPendingLatlong();
        println("priference String is "+priferencejson)
        if (!priferencejson.isEmpty()) {
            var gson = Gson()
            var pendingLatlong = gson.fromJson(priferencejson, PendingLatlong::class.java)
            if (pendingLatlong != null) {
                val retrofit: Retrofit = APIClientPatroller.getClient()
                val apiInference = retrofit.create(APIInterface::class.java)
                val call = apiInference.updateLatlong(
                    UpDateLatlongRequest(ownername = PatrollerPriference(context).getOwnername(), tracking = PatrollerPriference(context).getTaskID(),data = pendingLatlong.latlnglist)
                            , PatrollerPriference(context).gettokekey()
                )
                call.enqueue(object : Callback<UserDataResponse> {
                    override fun onResponse(
                        call: Call<UserDataResponse>,
                        response: Response<UserDataResponse>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(context, "pending data uploaded", Toast.LENGTH_SHORT)
                                .show()
                            println("response is " + response.body())
                            PatrollerPriference(context).setPendinglatlongl("")
                        }
                    }

                    override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                        println("error is " + t.message)
//                Toast.makeText(context,"data upload not ok",Toast.LENGTH_SHORT).show()

                    }
                })
            }else{
                println("json  is null")

            }
        }else{
            println("priference json is empty")
        }
    }
    fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        // below line is use to generate a drawable.
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

        // below line is use to set bounds to our vector drawable.
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )

        // below line is use to create a bitmap for our
        // drawable which we have added.
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        // below line is use to add bitmap in our canvas.
        val canvas = Canvas(bitmap)

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas)

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

}
