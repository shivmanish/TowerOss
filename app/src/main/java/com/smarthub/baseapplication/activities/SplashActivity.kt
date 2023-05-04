package com.smarthub.baseapplication.activities

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.Q
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.trackermodule.locationpicker.LocationPickerActivity
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.profile.viewProfile.newData.ProfileData
import com.smarthub.baseapplication.ui.fragments.project.DemoActivity
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel

class SplashActivity : BaseActivity() {

    lateinit var loginViewModel : LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        AppPreferences.getInstance().saveStaticDropDownData(this)
        AppLogger.log("token:${AppPreferences.getInstance().token}")
        AppLogger.log("refresh:${AppPreferences.getInstance().refresh}")
        AppLogger.log("refresh:${AppPreferences.getInstance().bearerToken}")
        val loginTime = AppPreferences.getInstance().getLong("loginTime")
        val loginTimeDiff = ((System.currentTimeMillis() - loginTime)/(1000*60*60)) // second,minute,hour
        AppLogger.log("loginTimeDiff:$loginTimeDiff")
        findViewById<View>(R.id.manage_site).setOnClickListener {
            AppController.getInstance().ownerName = AppPreferences.getInstance().getString("company")
//            val intent = Intent (this@SplashActivity, DashboardActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//            return@setOnClickListener

            if (Utils.isNetworkConnected(this@SplashActivity)){
                val intent = Intent(this@SplashActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                if (AppPreferences.getInstance().token.isNullOrEmpty() || loginTimeDiff > 72){
                    val intent = Intent(this@SplashActivity,LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    AppController.getInstance().ownerName = AppPreferences.getInstance().getString("company")
                    val profileData = Gson().fromJson(AppPreferences.getInstance().getString("profileData"),ProfileData::class.java)
                    if (profileData!=null)
                        AppController.getInstance().profileData=profileData
                    val intent = Intent (this@SplashActivity, DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
//            else if (AppPreferences.getInstance().token.isNullOrEmpty() || loginTimeDiff > (30*600)){
//                val intent = Intent(this@SplashActivity,LoginActivity::class.java)
//                startActivity(intent)
//                finish()
//            }else{
//                if (isNetworkConnected) {
//                    showLoader()
//                    loginViewModel.getProfileData()
//                }
//                else{
//                    AppController.getInstance().ownerName = AppPreferences.getInstance().getString("company")
//                    val intent = Intent (this@SplashActivity, DashboardActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    startActivity(intent)
//                }
//            }
        }

        loginViewModel.loginResponse?.observe(this) {

            if (it != null && it.data?.access?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS) {
                    AppPreferences.getInstance().saveString("accessToken", it.data.access)
                    AppPreferences.getInstance().saveString("refreshToken", it.data.refresh)
                    Log.d("status","${it.message}")
                    AppPreferences.getInstance().saveLong("loginTime",System.currentTimeMillis())
//                    val intent = Intent (this@SplashActivity, DashboardActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    startActivity(intent)
//                    finish()
                    loginViewModel.getProfileData()
                    return@observe
                }else{
                    Log.d("status","${it.message}")
                    Toast.makeText(this@SplashActivity,"error:"+it.message, Toast.LENGTH_LONG).show()
                }
            }else{
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(this@SplashActivity, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()

            }
            hideLoader()
        }
        loginViewModel.profileResponse?.observe(this) {
            hideLoader()
            if (it != null && it.status==Resource.Status.SUCCESS) {
//                AppController.getInstance().ownerName = it.data?.get(0)?.ownercode
                Log.d("status", "ownerName :${AppController.getInstance().ownerName}")
                if (isNetworkConnected){
                    if (it.data?.isNotEmpty()==true) {
                        AppController.getInstance().ownerName = it.data[0].ownercode
                        AppController.getInstance().profileData = it.data[0]
                        AppPreferences.getInstance().saveString("company",AppController.getInstance().ownerName)
                        AppPreferences.getInstance().saveString("profileData",Gson().toJson(AppController.getInstance().profileData))
                    }
                    val intent = Intent (this@SplashActivity, DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }else {
                    showNetworkAlert()
                }
            }else{
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(this@SplashActivity, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showNetworkAlert() {
        val dialogBuilder = AlertDialog.Builder(this)
        val dialogView: View = layoutInflater.inflate(R.layout.network_alert, null)
        dialogBuilder.setView(dialogView)
        val b = dialogBuilder.create()
        b.setCancelable(false)
        val exitText = dialogView.findViewById<TextView>(R.id.exittext)
        val icon = dialogView.findViewById<ImageView>(R.id.icon)
        val progressBar = dialogView.findViewById<ProgressBar>(R.id.progress)
        Glide.with(this).load(R.mipmap.ic_launcher).apply(RequestOptions().centerCrop()).into(icon)
        exitText.setText(R.string.internet_alert)
        val cancel = dialogView.findViewById<Button>(R.id.try_again)
        cancel.setOnClickListener {
            cancel.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            exitText.text = "Loading..."
            Handler().postDelayed({
                if (isNetworkConnected) {
                    b.dismiss()
                    val intent = Intent (this@SplashActivity, DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                } else {
                    ContextCompat.getMainExecutor(this@SplashActivity).execute {
                        cancel.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        exitText.setText(R.string.internet_alert)
                    }
                }
            }, 1000)
        }
        b.show()
    }

    private val isNetworkConnected: Boolean
        get() {
            val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (SDK_INT >= Q)
                manager.getNetworkCapabilities(manager.activeNetwork)?.let {
                    it.hasTransport(TRANSPORT_WIFI) || it.hasTransport(TRANSPORT_CELLULAR) ||
                            it.hasTransport(TRANSPORT_BLUETOOTH) ||
                            it.hasTransport(TRANSPORT_ETHERNET) ||
                            it.hasTransport(TRANSPORT_VPN)
                } ?: false
            else
                @Suppress("DEPRECATION")
                manager.activeNetworkInfo?.isConnected == true
        }

}