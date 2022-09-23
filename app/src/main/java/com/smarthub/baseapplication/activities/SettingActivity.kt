package com.smarthub.baseapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.smarthub.baseapplication.databinding.ActivitySettingBinding
import com.smarthub.baseapplication.ui.profile.ProfileActivity

class SettingActivity : AppCompatActivity() {

    private var dataBinding : ActivitySettingBinding ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dataBinding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(dataBinding?.root)
        initViews()
    }

    //    initialize all view
    fun initViews(){
        dataBinding?.language?.setOnClickListener {
            var intent = Intent(this@SettingActivity,LanguageActivity::class.java)
            startActivity(intent)
        }
        dataBinding?.editProfile?.setOnClickListener {
            var intent = Intent(this@SettingActivity,ProfileActivity::class.java)
            startActivity(intent)
        }

    }
}