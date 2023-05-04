package com.example.trackermodule.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.patrollerapp.login.viewmodel.SetPinViewModel
import com.example.trackermodule.databinding.ActivitySetPinBinding
import com.example.trackermodule.homepage.BaseActivity
import com.google.android.material.snackbar.Snackbar

class SetPinActivity : BaseActivity() {

    lateinit var setPinBinding: ActivitySetPinBinding
    lateinit var setPinViewModel: SetPinViewModel
    var phoneno = ""
    var pin = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPinBinding = ActivitySetPinBinding.inflate(layoutInflater)
        setContentView(setPinBinding.root)
        setPinViewModel = ViewModelProvider(this).get(SetPinViewModel::class.java)
        phoneno = intent.getStringExtra("phoneno")!!

/*
        setPinBinding.otpView.otpListener = object : OTPListener {
            override fun onInteractionListener() {
                pin = ""
            }
            override fun onOTPComplete(otp: String?) {
                pin = otp!!
            }
        }
*/

        setPinBinding.login.setOnClickListener {
            if (!pin.isEmpty()) {
                setPinViewModel.otpVerifyService(this, phoneno, pin)
            } else {
                Snackbar.make(
                    setPinBinding.root,
                    "Please Enter a valid Pin ! ",
                    Snackbar.LENGTH_SHORT
                ).show()

            }
        }

    }
}