package com.smarthub.baseapplication.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.fragments.login.LoginSecondStep
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.login.UserLoginPost
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.viewmodels.LoginViewModel

class SplashActivity : AppCompatActivity() {
    private lateinit var progressDialog : ProgressDialog
    private var loginViewModel : LoginViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        findViewById<View>(R.id.manage_site).setOnClickListener {
            if (!AppPreferences.getInstance().getString("userMail").isNullOrEmpty() &&
                !AppPreferences.getInstance().getString("password").isNullOrEmpty()){
//                var intent = Intent(this@SplashActivity,LoginActivity::class.java)
//                startActivity(intent)
//                finish()
                loginValidation()
            }else{
//                val intent = Intent (this@SplashActivity, DashboardActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(intent)

                var intent = Intent(this@SplashActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        }


        loginViewModel?.loginResponse?.observe(this) {
            if (it != null && it.data?.access?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS && it.data!=null) {
                    AppPreferences.getInstance().saveString("access", "${it.data?.access}")
                    Log.d("status","${it.message}")
                    if (progressDialog.isShowing)
                        progressDialog.dismiss()
                    Toast.makeText(this@SplashActivity,"LoginSuccessful", Toast.LENGTH_LONG).show()
                    val intent = Intent (this@SplashActivity, DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                    return@observe
                }else{
                    Log.d("status","${it.message}")
                    if (progressDialog.isShowing)
                        progressDialog.dismiss()
                    Toast.makeText(this@SplashActivity,"error:"+it.message, Toast.LENGTH_LONG).show()

                }
            }else{
                Log.d("status","${AppConstants.GENERIC_ERROR}")
                if (progressDialog.isShowing)
                    progressDialog.dismiss()
                Toast.makeText(this@SplashActivity, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()

            }

        }
    }

    private fun loginValidation(){
        progressDialog.show()
        loginViewModel?.getLoginToken(UserLoginPost(AppPreferences.getInstance().getString("userMail"), AppPreferences.getInstance().getString("password")))
    }
}