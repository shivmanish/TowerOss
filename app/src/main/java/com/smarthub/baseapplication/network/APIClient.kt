package com.smarthub.baseapplication.network

import retrofit2.http.POST
import com.smarthub.baseapplication.model.login.UserLoginPost
import com.smarthub.baseapplication.model.otp.SendOtpResponse
import com.smarthub.baseapplication.model.otp.SendOtpService
import com.smarthub.baseapplication.model.otp.VerifyOtpService
import com.smarthub.baseapplication.model.profile.UserProfileGet
import com.smarthub.baseapplication.model.register.RegisterData
import com.smarthub.baseapplication.model.register.RegstationResponse
import com.smarthub.baseapplication.network.pojo.RefreshToken
import retrofit2.Call
import retrofit2.http.Body

interface   APIClient {
    @POST(EndPoints.ACCESS_TOKEN)
    fun getLoginForAccessToken(@Body data: UserLoginPost): Call<RefreshToken>

    @POST(EndPoints.ACCESS_TOKEN)
    fun getRegister(@Body data: UserLoginPost): Call<RefreshToken>

    @POST(EndPoints.ACCESS_TOKEN)
    fun sendOtp(@Body data: SendOtpService): Call<SendOtpResponse>

    @POST(EndPoints.ACCESS_TOKEN)
    fun verifyOtp(@Body data: VerifyOtpService): Call<RefreshToken>

    @POST(EndPoints.ACCESS_TOKEN)
    fun getProfile(@Body data: UserProfileGet): Call<List<ProfileDetails>>

    @POST(EndPoints.REGISTRATION)
    fun registration(@Body data: RegisterData): Call<RegstationResponse>

}