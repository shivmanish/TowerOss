package com.smarthub.baseapplication.otphelper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.smarthub.baseapplication.otphelper.MySMSBroadcastReceiver.Common.OTPListener

class MySMSBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
            val extras = intent.extras
            val status = extras!![SmsRetriever.EXTRA_STATUS] as Status?
            println("MySMSBroadcastReceiver.onReceive" + status!!.statusCode)
            when (status.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    // Get SMS message contents
                    var message = extras[SmsRetriever.EXTRA_SMS_MESSAGE] as String?
                    println("MySMSBroadcastReceiver.onReceive sdfgadg gdfg dfag ds $message")
                    if (mListener != null) {
                        message = message!!.replace("[^0-9]".toRegex(), "").trim { it <= ' ' }
                        message = message!!.substring(0, 6)
                        mListener!!.onOTPReceived(message.trim { it <= ' ' })
                    }
                }
//                Please use this OTP:054635 IKm1CtvQCFv
                CommonStatusCodes.TIMEOUT ->
                    println("MySMSBroadcastReceiver.onReceive sdfgadg gdfg dfag ds TIMEOUT")
            }
        }
    }

    interface Common {
        interface OTPListener {
            fun onOTPReceived(otp: String?)
        }
    }

    companion object {
        fun bindListener(listener: OTPListener?) {
            mListener = listener
        }

        fun unbindListener() {
            mListener = null
        }

        private var mListener: OTPListener? = null
    }
}