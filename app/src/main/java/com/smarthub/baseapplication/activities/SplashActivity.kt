package com.smarthub.baseapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.smarthub.baseapplication.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var intent = Intent(this@SplashActivity,LoginActivity::class.java)
        findViewById<View>(R.id.manage_site).setOnClickListener {
           startActivity(intent)
            finish()
        }
    }
}