package com.example.trackermodule.login

import android.content.IntentFilter
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.patrollerapp.login.viewmodel.OtvVerifyViewModel
import com.example.trackermodule.databinding.ActivityOtpVerifyBinding
import com.example.trackermodule.homepage.BaseActivity
import com.example.trackermodule.login.sms.MySMSBroadcastReceiver
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar


class OtpVerifyActivity : BaseActivity() {
    lateinit var  mySMSBroadcastReceiver : MySMSBroadcastReceiver
    lateinit var otpVerifyBinding: ActivityOtpVerifyBinding
    lateinit var viewmodel: OtvVerifyViewModel
    lateinit var phoneno: String
    var pin = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        otpVerifyBinding = ActivityOtpVerifyBinding.inflate(layoutInflater)
        setContentView(otpVerifyBinding.root)
        otpFetchFunction()
        viewmodel = ViewModelProvider(this).get(OtvVerifyViewModel::class.java)

/*
        otpVerifyBinding.otpView.otpListener = object : OTPListener {
            override fun onInteractionListener() {
                pin = ""
            }

            override fun onOTPComplete(otp: String?) {
                pin = otp!!
            }
        }
*/
        phoneno = intent.getStringExtra("phoneno")!!
//        otpVerifyBinding.phonenoText.text = "Please enter a one time password(OTP) sent to +91 "+phoneno

        otpVerifyBinding.next.setOnClickListener {
            if (!pin.isEmpty()) {
                viewmodel.otpVerifyService(this, phoneno, pin)
            } else {
                Snackbar.make(otpVerifyBinding.root, "Please Enter a valid OTP! ",Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun startSMSRetrieverClient() {
        val client = SmsRetriever.getClient(this)
        val task: Task<Void> = client.startSmsRetriever()
        task.addOnSuccessListener { aVoid ->

        }
        task.addOnFailureListener { e ->

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        if (mySMSBroadcastReceiver != null) unregisterReceiver(mySMSBroadcastReceiver)
    }

    fun otpFetchFunction(){
        startSMSRetrieverClient() // Already implemented above.
        mySMSBroadcastReceiver = MySMSBroadcastReceiver()

        registerReceiver(
            mySMSBroadcastReceiver,
            IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        )
/*
        mySMSBroadcastReceiver.init(object : OTPReceiveListener {
            override fun onOTPReceived(otp: String?) {
                // OTP Received
                otpVerifyBinding.otpView.setOTP(otp)
                pin = otp!!
            }

            override fun onOTPTimeOut() {}
        })
*/
    }
}