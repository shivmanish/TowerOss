package com.example.patrollerapp.login.callback

interface OTPReceiveListener {
    fun onOTPReceived(otp: String?)
    fun onOTPTimeOut()
}