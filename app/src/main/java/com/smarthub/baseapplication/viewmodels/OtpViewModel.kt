package com.smarthub.baseapplication.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.model.otp.SendOtpResponse
import com.smarthub.baseapplication.model.otp.SendOtpService
import com.smarthub.baseapplication.network.APIClient
import com.smarthub.baseapplication.network.APIInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtpViewModel : ViewModel() {
    var otpResponse = MutableLiveData<SendOtpResponse>()

    fun sendOtp(sendOtpService: SendOtpService) {

        var apiClient: APIClient = APIInterceptor.get()
        apiClient.sendOtp(sendOtpService).enqueue(object : Callback<SendOtpResponse> {
            override fun onResponse(
                call: Call<SendOtpResponse>, response: Response<SendOtpResponse>
            ) {
                otpResponse.postValue(response.body())
                println("this is called 2")
            }

            override fun onFailure(call: Call<SendOtpResponse>, t: Throwable) {
                println("this is called 1")
            }
        })
        println("this is called ")
    }

    fun showToast(context: Context?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}