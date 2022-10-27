package com.smarthub.baseapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.login.UserLoginPost
import com.smarthub.baseapplication.model.otp.GetOtpResponse
import com.smarthub.baseapplication.model.otp.UserOTPGet
import com.smarthub.baseapplication.model.otp.UserOTPVerify
import com.smarthub.baseapplication.model.register.RegisterData
import com.smarthub.baseapplication.model.register.RegstationResponse
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.pojo.RefreshToken
import com.smarthub.baseapplication.network.repo.LoginRepo
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.network.repo.RegisterRepo

class LoginViewModel: ViewModel() {
    var loginRepo: LoginRepo?=null
    var loginResponse : SingleLiveEvent<Resource<RefreshToken>>?=null
    var getOtpResponse : SingleLiveEvent<Resource<GetOtpResponse>>?=null

    var regstationResponse: MutableLiveData<RegstationResponse>?=null
    var registerRepo: RegisterRepo?=null

    var registerData: RegisterData? = RegisterData()


    init {
        loginRepo = LoginRepo(APIInterceptor.get())
        loginResponse = loginRepo?.loginResponse
        getOtpResponse = loginRepo?.otpResponse
        registerRepo = RegisterRepo(APIInterceptor.get())
        regstationResponse = registerRepo?.regstationResponse

    }


    fun getLoginToken(data : UserLoginPost) {
        loginRepo?.getLoginToken(data)
    }

    fun getLoginWithOtp(otp: String) {
        AppLogger.log("s : $otp, userPhoneNumber:${userOTPGet?.userPhoneNumber}")
        var data = UserOTPVerify(userOTPGet?.userPhoneNumber,otp)
        loginRepo?.getLoginWith(data)
    }


    var userOTPGet : UserOTPGet?=null
    fun getPhoneOtp(data : UserOTPGet) {
        userOTPGet = data
        loginRepo?.getOtpOnPhone(data)
    }

    fun registerUser() {
        registerRepo!!.registerUser(registerData)
    }


}