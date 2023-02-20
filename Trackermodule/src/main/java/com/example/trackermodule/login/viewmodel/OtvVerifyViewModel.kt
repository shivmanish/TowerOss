package com.example.patrollerapp.login.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.patrollerapp.login.SetPinActivity
import com.example.patrollerapp.login.pojo.NormalResponse
import com.example.patrollerapp.login.pojo.OtpVerifyService
import com.example.patrollerapp.util.PatrollerPriference
import com.example.patrollerapp.util.Util
import com.example.trackermodule.server.APIClientPatroller
import com.example.trackermodule.server.APIInterface
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class OtvVerifyViewModel : ViewModel() {
    fun otpVerifyService(context: Context, phonNo: String, otp: String) {
        var dialog = Util.getdialouge(context)
        dialog.show()
        val retrofit: Retrofit = APIClientPatroller.getClient()
        val apiInference = retrofit.create(APIInterface::class.java)
        val call = apiInference.verifyOtp(
            OtpVerifyService(
                device_id = PatrollerPriference(context).getDeviceID(),
                phone = phonNo, otp = otp
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
                    if (responseData!!.Message.equals("True")) {
                        Toast.makeText(context, "Otp Verify Successfully! ", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(context, SetPinActivity::class.java)
                        intent.putExtra("phoneno", phonNo)
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(
                            context,
                            "This Otp is not valid, Please Enter a valid Otp. ",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }else{
                    if (response.errorBody() != null) {
                        try {
                            val jObjError = JSONObject(response.errorBody()!!.string())
                            println("do do "+jObjError.toString())
                            Toast.makeText(
                                context,
                                jObjError.getString("Message"),
                                Toast.LENGTH_LONG
                            ).show()
                        } catch (e: Exception) {
                            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                        }
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