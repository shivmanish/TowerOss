package com.smarthub.baseapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.login.UserLoginPost
import com.smarthub.baseapplication.model.otp.GetOtpResponse
import com.smarthub.baseapplication.model.otp.UserOTPGet
import com.smarthub.baseapplication.model.otp.UserOTPVerify
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.pojo.RefreshToken
import com.smarthub.baseapplication.network.repo.LoginRepo
import com.smarthub.baseapplication.utils.AppLogger

class LoginViewModel: ViewModel() {
    var loginRepo: LoginRepo?=null
    var userMail : String = ""
    var password : String = ""
    var loginResponse : SingleLiveEvent<Resource<RefreshToken>>?=null
    var otp : SingleLiveEvent<ArrayList<String>?>?=null
    var getOtpResponse : SingleLiveEvent<Resource<GetOtpResponse>>?=null
    init {
        loginRepo = LoginRepo(APIInterceptor.get())
        loginResponse = loginRepo?.loginResponse
        getOtpResponse = loginRepo?.otpResponse
        otp = SingleLiveEvent()
        otp?.postValue(ArrayList())
    }

    fun updateOtpValueIndex(char: String,index: Int){
        var list = otp?.value
        if (list?.size!! > index){
            list[index] = char
        }else list?.add(index,char)
        otp?.postValue(list)
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

}