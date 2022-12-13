package com.smarthub.baseapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.CommonResponse
import com.smarthub.baseapplication.model.dropdown.DropDownList
import com.smarthub.baseapplication.model.login.UserLoginPost
import com.smarthub.baseapplication.model.otp.*
import com.smarthub.baseapplication.model.register.RegstationResponse
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.pojo.RefreshToken
import com.smarthub.baseapplication.network.repo.LoginRepo
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.network.repo.RegisterRepo
import com.smarthub.baseapplication.utils.Utils

class LoginViewModel: ViewModel() {
    var loginRepo: LoginRepo?=null
    var loginResponse : SingleLiveEvent<Resource<RefreshToken>>?=null
    var getOtpResponse : SingleLiveEvent<Resource<GetOtpResponse>>?=null
    var getResendOtpResponse : SingleLiveEvent<Resource<GetOtpResponse>>?=null
    var getPassResponse : SingleLiveEvent<Resource<GetSuccessResponse>>?=null
    var regstationResponse: MutableLiveData<RegstationResponse>?=null
    var registerVerifyOtpResponse: SingleLiveEvent<Resource<CommonResponse>>?=null
    var emailVerifyOtpResponse: SingleLiveEvent<Resource<CommonResponse>>?=null
    var dropDownList: MutableLiveData<DropDownList>?=null
    var registerOtpResponse : SingleLiveEvent<Resource<GetRegisterOtpResponse>>?=null
    var registerRepo: RegisterRepo?=null

    var registerData = Utils.getRegistrationDummyData()


    init {
        loginRepo = LoginRepo(APIInterceptor.get())
        loginResponse = loginRepo?.loginResponse
        getOtpResponse = loginRepo?.otpResponse
        getResendOtpResponse = loginRepo?.resendOtpResponse
        getPassResponse = loginRepo?.passResponse
        registerRepo = RegisterRepo(APIInterceptor.get())
        regstationResponse = registerRepo?.registrationResponse
        dropDownList = registerRepo?.companyDropDownList
        registerOtpResponse = loginRepo?.registerSendOtpResponse
        registerVerifyOtpResponse = registerRepo?.verifyOtpResponse
        emailVerifyOtpResponse = registerRepo?.verifyEmailResponse

    }

    fun getRegister(): MutableLiveData<RegstationResponse>?{
        return regstationResponse
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
    fun resendPhoneOtp(data : UserOTPGet) {
        userOTPGet = data
        loginRepo?.getResendOtpOnPhone(data)
    }

    fun getRegisterOtp(phone : String?) {
        loginRepo?.getRegisterOtpOnPhone(phone)
    }

//    fun changePassword(data : String) {
//        loginRepo?.changePassword(UserPasswordGet(userOTPGet?.userPhoneNumber,data))
//    }
    fun changePassword(phone : String,data : String) {
        loginRepo?.changePassword(UserPasswordGet(phone,data))
    }

    fun registerPassword(phone : String?,newPass : String,) {
        loginRepo?.changePassword(UserPasswordGet(phone,newPass))
    }

    fun registerUser() {
        registerRepo?.registerUser(registerData)
    }

    fun fetchCompanyDropDown() {
        registerRepo?.companyDropDown("companydropdown")
    }

    fun domainVerification(ownername:String,email:String) {
        registerRepo?.verifyDomain(ownername,email)
    }

    fun registerOTPVerification(otp:String,phone:String?) {
        registerRepo?.registerOTPVerification(otp,phone)
    }

    fun emailVerification(email:String, ownername:String?) {
        Log.d("status","emailVerification:$email:$ownername")
        registerRepo?.emailVerification(email,ownername)
    }


}