package com.example.patrollerapp.login.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.trackermodule.homepage.HomePage
import com.example.trackermodule.login.OtpVerifyActivity
import com.example.patrollerapp.login.pojo.LoginWithPasscode
import com.example.patrollerapp.login.pojo.NormalResponse
import com.example.patrollerapp.login.pojo.OptSendService
import com.example.patrollerapp.util.PatrollerPriference
import com.example.patrollerapp.util.Util
import com.example.trackermodule.login.sms.AppSignatureHelper
import com.example.trackermodule.server.APIClientPatroller
import com.example.trackermodule.server.APIInterface
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MainActivityViewmodel:ViewModel() {



fun loginWithPasscode(context: Context,pin:String){
    var dialog = Util.getdialouge(context)
    dialog.show()
    val retrofit: Retrofit = APIClientPatroller.getClient()
    val apiInference = retrofit.create(APIInterface::class.java)
    val call = apiInference.loginWithPassword(LoginWithPasscode(device_id = PatrollerPriference(context).getDeviceID(),
    loginpin = pin,
    number = PatrollerPriference(context).getPhonenumber()))
    call.enqueue(object :Callback<NormalResponse>{
        override fun onResponse(call: Call<NormalResponse>, response: Response<NormalResponse>) {
            val responseData = response.body()
            println("response is got")
            dialog.dismiss()
            if(response.isSuccessful) {
                if (responseData != null) {

                    if (responseData!!.Success.equals("True")) {
                        Toast.makeText(context, "Login Successful.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(context, HomePage::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(context, responseData!!.Message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Something went wrong! please try again.", Toast.LENGTH_SHORT).show()
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
            println("error is "+t.message)

        }
    })
}


    fun SendOtpService(context: Context,phonNo:String){
        val appSignatureHelper = AppSignatureHelper(context)

        var dialog = Util.getdialouge(context)
        dialog.show()
        val retrofit: Retrofit = APIClientPatroller.getClient()
        val apiInference = retrofit.create(APIInterface::class.java)
        val call = apiInference.sendOtp(
            OptSendService(device_id = PatrollerPriference(context).getDeviceID(),
            phone = phonNo,
            signature = appSignatureHelper.appSignatures[0])
        )
        call.enqueue(object :Callback<NormalResponse>{
            override fun onResponse(call: Call<NormalResponse>, response: Response<NormalResponse>) {
                val responseData = response.body()
                println("response is "+response.body())
                dialog.dismiss()
                if(response.isSuccessful) {
                    if (responseData!!.success) {
                        Toast.makeText(
                            context,
                            "Otp Send Successful, Please check Inbox",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(context, OtpVerifyActivity::class.java)
                        intent.putExtra("phoneno", phonNo)
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(context, responseData!!.Message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<NormalResponse>, t: Throwable) {
                dialog.dismiss()
                println("error is "+t.message)

            }
        })
    }


}