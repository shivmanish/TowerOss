package com.smarthub.baseapplication.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.smarthub.baseapplication.R

import com.smarthub.baseapplication.databinding.ActivityForgotPasswordBinding
import com.smarthub.baseapplication.databinding.ActivitySettingBinding

class ForgotPasswordActivity : AppCompatActivity() {

   private  var binding: ActivityForgotPasswordBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
         binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
         setContentView(binding?.root)
         initViews();
    }

    private fun initViews() {
        findViewById<View>(R.id.img_back).setOnClickListener {
            onBackPressed()
        }
        binding?.btnNext?.setOnClickListener {
            var intent = Intent(this@ForgotPasswordActivity, LanguageActivity::class.java)
            startActivity(intent)
        }
    }


}