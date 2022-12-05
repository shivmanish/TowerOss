package com.smarthub.baseapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivitySettingBinding
import com.smarthub.baseapplication.ui.fragments.profile.ProfileActivity

class SettingActivity : AppCompatActivity() {
    private var dataBinding : ActivitySettingBinding ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        dataBinding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(dataBinding?.root)
        initViews()
    }

    //    initialize all view
    private fun initViews(){
        findViewById<View>(R.id.img_back).setOnClickListener {
            onBackPressed()
        }
        dataBinding?.language?.setOnClickListener {
            var intent = Intent(this@SettingActivity,LanguageActivity::class.java)
            startActivity(intent)
        }
        dataBinding?.editProfile?.setOnClickListener {
            var intent = Intent(this@SettingActivity, ProfileActivity::class.java)
            startActivity(intent)
        }

    }
}