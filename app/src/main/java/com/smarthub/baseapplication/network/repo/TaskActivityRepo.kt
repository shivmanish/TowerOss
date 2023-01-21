package com.smarthub.baseapplication.network.repo

import androidx.lifecycle.MutableLiveData
import com.smarthub.baseapplication.model.APIError
import com.smarthub.baseapplication.model.taskModel.CreateNewTaskModel
import com.smarthub.baseapplication.model.taskModel.CreateNewTaskResponse
import com.smarthub.baseapplication.network.APIClient
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskActivityRepo(private val apiClient: APIClient) {

    val TAG = "TaskActivityRepo"
    val createNewTaskResponse: MutableLiveData<CreateNewTaskResponse> = MutableLiveData()
    

    fun createNewTask(data: CreateNewTaskModel?) {
        apiClient.createNewTask(data!!).enqueue(object : Callback<CreateNewTaskResponse?> {
            override fun onResponse(
                call: Call<CreateNewTaskResponse?>,
                response: Response<CreateNewTaskResponse?>
            ) {
                AppLogger.log("$TAG onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<CreateNewTaskResponse?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<CreateNewTaskResponse?>) {
                if (response.body() != null) {
                    createNewTaskResponse.postValue(response.body())
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    createNewTaskResponse.postValue(CreateNewTaskResponse("Failed", response.message))
                } else if (iThrowableLocalMessage != null) createNewTaskResponse.postValue(
                    CreateNewTaskResponse("Failed", iThrowableLocalMessage)
                ) else createNewTaskResponse.postValue(
                    CreateNewTaskResponse(
                        "Failed",
                        AppConstants.GENERIC_ERROR
                    )
                )
            }
        })
    }
    
}