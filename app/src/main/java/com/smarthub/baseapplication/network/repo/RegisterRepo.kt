package com.smarthub.baseapplication.network.repo

import androidx.lifecycle.MutableLiveData
import com.smarthub.baseapplication.model.APIError
import com.smarthub.baseapplication.model.register.RegisterData
import com.smarthub.baseapplication.model.register.RegstationResponse
import com.smarthub.baseapplication.network.APIClient
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterRepo(private val apiClient: APIClient) {
    val TAG = "RegisterRepo"
    val regstationResponse: MutableLiveData<RegstationResponse>

    init {
        regstationResponse = MutableLiveData()
    }

    fun registerUser(data: RegisterData?) {
        apiClient.registration(data!!).enqueue(object : Callback<RegstationResponse?> {
            override fun onResponse(
                call: Call<RegstationResponse?>,
                response: Response<RegstationResponse?>
            ) {
                AppLogger.log(TAG + " onResponse get response " + response)
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<RegstationResponse?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<RegstationResponse?>) {
                if (response.body() != null) {
                    regstationResponse.postValue(response.body())
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    regstationResponse.postValue(RegstationResponse("Failed", response.message))
                } else if (iThrowableLocalMessage != null) regstationResponse.postValue(
                    RegstationResponse("Failed", iThrowableLocalMessage)
                ) else regstationResponse.postValue(
                    RegstationResponse(
                        "Failed",
                        AppConstants.GENERIC_ERROR
                    )
                )
            }
        })
    }

    companion object {
        private var sInstance: RegisterRepo? = null
        private val LOCK = Any()
        fun getInstance(apiClient: APIClient): RegisterRepo? {
            if (sInstance == null) {
                synchronized(LOCK) { sInstance = RegisterRepo(apiClient) }
            }
            return sInstance
        }
    }
}