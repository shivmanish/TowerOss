package com.smarthub.baseapplication.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.helpers.AppPreferences


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        findViewById<View>(R.id.manage_site).setOnClickListener {
            if (AppPreferences.getInstance().token.isNullOrEmpty()){
             /*   var intent = Intent(this@SplashActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()*/

            }else{
                val intent = Intent (this@SplashActivity, DashboardActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

        }
    }
}