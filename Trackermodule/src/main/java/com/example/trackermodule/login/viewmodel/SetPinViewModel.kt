package com.example.patrollerapp.login.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.patrollerapp.homepage.HomePage
import com.example.patrollerapp.login.pojo.NormalResponse
import com.example.patrollerapp.login.pojo.SetPinService
import com.example.patrollerapp.util.PatrollerPriference
import com.example.patrollerapp.util.Util
import com.example.trackermodule.server.APIClientPatroller
import com.example.trackermodule.server.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SetPinViewModel : ViewModel() {
    fun otpVerifyService(context: Context, phonNo: String, pin: String) {
        var dialog = Util.getdialouge(context)
        dialog.show()
        val retrofit: Retrofit = APIClientPatroller.getClient()
        val apiInference = retrofit.create(APIInterface::class.java)
        val call = apiInference.setPin(
            SetPinService(
                device_id = PatrollerPriference(context).getDeviceID(),
                mobile = phonNo, cpin = pin
            )
        )
        call.enqueue(object : Callback<NormalResponse> {
            override fun onResponse(
                call: Call<NormalResponse>,
                response: Response<NormalResponse>
            ) {
                dialog.dismiss()
                if(response.isSuccessful) {
                    val responseData = response.body()
                    println("response is " + response.body())
                    if (responseData!!.Success.equals("True")) {
                        PatrollerPriference(context).setPhoneNumber(phonNo)
                        Toast.makeText(context, "Pin Set Successfully! ", Toast.LENGTH_SHORT).show()
                        val intent = Intent(context, HomePage::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(context, responseData!!.Message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<NormalResponse>, t: Throwable) {
                dialog.dismiss()
                println("error is " + t.message)

            }
        })
    }

}