package com.smarthub.baseapplication.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityForgotPasswordBinding
import com.smarthub.baseapplication.databinding.ActivityForgotPasswordBinding.inflate

class ForgotPasswordActivity : BaseActivity() {

   private var binding: ActivityForgotPasswordBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
         binding = inflate(layoutInflater)
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