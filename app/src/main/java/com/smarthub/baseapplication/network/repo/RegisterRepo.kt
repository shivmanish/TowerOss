package com.smarthub.baseapplication.network.repo

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.APIError
import com.smarthub.baseapplication.model.CommonResponse
import com.smarthub.baseapplication.model.EmailVerificationResponse
import com.smarthub.baseapplication.model.dropdown.DropDownList
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
    val registrationResponse: MutableLiveData<RegstationResponse> = MutableLiveData()
    val companyDropDownList: MutableLiveData<DropDownList> = MutableLiveData()
    val commonResponse: MutableLiveData<CommonResponse> = MutableLiveData()
    var verifyOtpResponse: SingleLiveEvent<Resource<CommonResponse>> ?=null
    var verifyEmailResponse: SingleLiveEvent<Resource<EmailVerificationResponse>> ?=null

    init {
        verifyOtpResponse = SingleLiveEvent<Resource<CommonResponse>>()
        verifyEmailResponse = SingleLiveEvent<Resource<EmailVerificationResponse>>()
    }

    fun registerUser(data: RegisterData?) {
        apiClient.registration(data!!).enqueue(object : Callback<RegstationResponse?> {
            override fun onResponse(
                call: Call<RegstationResponse?>,
                response: Response<RegstationResponse?>
            ) {
                AppLogger.log("$TAG onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<RegstationResponse?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<RegstationResponse?>) {
                if (response.body() != null) {
                    registrationResponse.postValue(response.body())
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    registrationResponse.postValue(RegstationResponse("Failed", response.message))
                } else if (iThrowableLocalMessage != null) registrationResponse.postValue(
                    RegstationResponse("Failed", iThrowableLocalMessage)
                ) else registrationResponse.postValue(
                    RegstationResponse(
                        "Failed",
                        AppConstants.GENERIC_ERROR
                    )
                )
            }
        })
    }

    fun companyDropDown(key: String?) {
        var jsonObj = JsonObject()
        jsonObj.addProperty(key,true)
        apiClient.companyDropDown(jsonObj).enqueue(object : Callback<DropDownList?> {
            override fun onResponse(
                call: Call<DropDownList?>,
                response: Response<DropDownList?>
            ) {
                AppLogger.log("$TAG onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<DropDownList?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<DropDownList?>) {
                if (response.body() != null) {
                    companyDropDownList.postValue(response.body())
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    companyDropDownList.postValue(DropDownList("Failed", response.message, ArrayList()))
                } else if (iThrowableLocalMessage != null) companyDropDownList.postValue(
                    DropDownList("Failed", iThrowableLocalMessage, ArrayList())
                ) else companyDropDownList.postValue(
                    DropDownList(
                        "Failed",
                        AppConstants.GENERIC_ERROR, ArrayList()
                    )
                )
            }
        })
    }
    fun verifyDomain(ownername: String?,email: String?) {
        var jsonObj = JsonObject()
//        {
//            "checkemaildomain": "true",
//            "ownername": "smartmile",
//            "email": "test@domain1.com"
//        }
        jsonObj.addProperty("checkemaildomain",true)
        jsonObj.addProperty("ownername",ownername)
        jsonObj.addProperty("email",email)
        apiClient.commonResponse(jsonObj).enqueue(object : Callback<CommonResponse?> {
            override fun onResponse(call: Call<CommonResponse?>, response: Response<CommonResponse?>) {
                AppLogger.log("$TAG onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<CommonResponse?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<CommonResponse?>) {
                if (response.body() != null) {
                    commonResponse.postValue(response.body())
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    commonResponse.postValue(CommonResponse("Failed", response.message))
                } else if (iThrowableLocalMessage != null)
                    commonResponse.postValue(CommonResponse("Failed", iThrowableLocalMessage)
                ) else
                    commonResponse.postValue(CommonResponse("Failed", AppConstants.GENERIC_ERROR)
                )
            }
        })
    }
    fun registerOTPVerification(otp: String?,phone : String?) {
        var jsonObj = JsonObject()
        jsonObj.addProperty("verifyotp",true)
        jsonObj.addProperty("phone",phone)
        jsonObj.addProperty("otp",otp)
        apiClient.commonResponse(jsonObj).enqueue(object : Callback<CommonResponse?> {
            override fun onResponse(call: Call<CommonResponse?>, response: Response<CommonResponse?>) {
                AppLogger.log("$TAG onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<CommonResponse?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<CommonResponse?>) {
                if (response.body() != null) {
                    verifyOtpResponse?.postValue(
                        Resource.success(
                            response.body()!!, 200
                        )
                    )
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    verifyOtpResponse?.postValue(Resource.error(response.message, null, 400))
                } else if (iThrowableLocalMessage != null) verifyOtpResponse?.postValue(
                    Resource.error(iThrowableLocalMessage, null, 500)
                ) else verifyOtpResponse?.postValue(
                    Resource.error(AppConstants.GENERIC_ERROR, null, 500)
                )
            }

//            private fun reportSuccessResponse(response: Response<CommonResponse?>) {
//                if (response.body() != null) {
//                    verifyOtpResponse?.postValue(response.body())
//                }
//            }
//
//            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
//                if (response != null) {
//                    verifyOtpResponse?.postValue(CommonResponse("Failed", response.message))
//                } else if (iThrowableLocalMessage != null)
//                    verifyOtpResponse?.postValue(CommonResponse("Failed", iThrowableLocalMessage)
//                ) else
//                    verifyOtpResponse?.postValue(CommonResponse("Failed", AppConstants.GENERIC_ERROR)
//                )
//            }
        })
    }

    fun emailVerification(email: String?, ownername : String?) {
        var jsonObj = JsonObject()
        jsonObj.addProperty("checkemaildomain",true)
        jsonObj.addProperty("ownername",ownername)
        jsonObj.addProperty("email",email)
        AppLogger.log("Emailvarification data===> $jsonObj")
        apiClient.verifyEmailResponse(jsonObj).enqueue(object : Callback<EmailVerificationResponse?> {
            override fun onResponse(call: Call<EmailVerificationResponse?>, response: Response<EmailVerificationResponse?>) {
                AppLogger.log("emailVerification onResponse get response $response")
                AppLogger.log("emailVerification onResponse get responsebody ${response.body()}")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<EmailVerificationResponse?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log("emailVerification" + " onFailureResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<EmailVerificationResponse?>) {
                if (response.body() != null) {
                    AppLogger.log("varification success response===>${response.body()}")
                    verifyEmailResponse?.postValue(Resource.success(response.body()!!, 200))
                }else if (response.body() == null) {
                    verifyEmailResponse?.postValue(Resource.success(EmailVerificationResponse("","Please fill correct email domain"), 200))
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) verifyEmailResponse?.postValue(Resource.error(response.message, null, 400))
                else if (iThrowableLocalMessage != null) verifyEmailResponse?.postValue(Resource.error(iThrowableLocalMessage, null, 500))
                else verifyEmailResponse?.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500))
            }

//            private fun reportSuccessResponse(response: Response<CommonResponse?>) {
//                if (response.body() != null) {
//                    verifyOtpResponse?.postValue(response.body())
//                }
//            }
//
//            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
//                if (response != null) {
//                    verifyOtpResponse?.postValue(CommonResponse("Failed", response.message))
//                } else if (iThrowableLocalMessage != null)
//                    verifyOtpResponse?.postValue(CommonResponse("Failed", iThrowableLocalMessage)
//                ) else
//                    verifyOtpResponse?.postValue(CommonResponse("Failed", AppConstants.GENERIC_ERROR)
//                )
//            }
        })
    }
}