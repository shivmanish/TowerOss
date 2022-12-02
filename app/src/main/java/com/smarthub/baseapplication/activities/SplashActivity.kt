package com.smarthub.baseapplication.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.ui.fragments.customer_tab.editdialouge.OpcoInfoSiteDialouge
import com.smarthub.baseapplication.ui.fragments.customer_tab.editdialouge.RfEquipmentDialouge
import com.smarthub.baseapplication.ui.fragments.customer_tab.editdialouge.SiteInfoBasicDetailsDialouge
import com.smarthub.baseapplication.ui.project.DemoActivity
import com.smarthub.baseapplication.ui.utilites.BatteryBankDetailsActivity
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.viewmodels.LoginViewModel

class SplashActivity : BaseActivity() {

    private var loginViewModel : LoginViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        findViewById<View>(R.id.manage_site).setOnClickListener {
            if (AppPreferences.getInstance().token.isNullOrEmpty()){
                var intent = Intent(this@SplashActivity,LoginActivity::class.java)
//                var intent = Intent(this@SplashActivity, DemoActivity::class.java)

                startActivity(intent)
                finish()

//                loginValidation()
            }else{
                val intent = Intent (this@SplashActivity, DashboardActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

//                var intent = Intent(this@SplashActivity,DemoActivity::class.java)
//                startActivity(intent)
//                finish()
/*
                startActivity(
                    Intent(
                        this@SplashActivity,
                        BatteryBankDetailsActivity::class.java
                    )
                )
*/


//                val dialouge = RfEquipmentDialouge()
//                dialouge.show(getSupportFragmentManager(),"")
            }

        }


        loginViewModel?.loginResponse?.observe(this) {
            hideLoader()
            if (it != null && it.data?.access?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS && it.data!=null) {
                    AppPreferences.getInstance().saveString("accessToken", "${it.data?.access}")
                    AppPreferences.getInstance().saveString("refreshToken", "${it.data?.refresh}")
                    Log.d("status","${it.message}")
                    Toast.makeText(this@SplashActivity,"LoginSuccessful", Toast.LENGTH_LONG).show()
                    val intent = Intent (this@SplashActivity, DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                    return@observe
                }else{
                    Log.d("status","${it.message}")
                    Toast.makeText(this@SplashActivity,"error:"+it.message, Toast.LENGTH_LONG).show()

                }
            }else{
                Log.d("status","${AppConstants.GENERIC_ERROR}")
                Toast.makeText(this@SplashActivity, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()

            }

        }
    }

//    private fun loginValidation(){
//        progressDialog.show()
//        loginViewModel?.getLoginToken(UserLoginPost(AppPreferences.getInstance().getString("userMail"), AppPreferences.getInstance().getString("password")))
//    }
}