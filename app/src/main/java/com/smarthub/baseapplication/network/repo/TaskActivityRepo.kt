package com.smarthub.baseapplication.network.repo

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.APIError
import com.smarthub.baseapplication.model.register.dropdown.DepartmentDropdown
import com.smarthub.baseapplication.model.search.SearchList
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.updateNocComp.UpdateNocCompModel
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.updateNocComp.UpdateNocCompResponseModel
import com.smarthub.baseapplication.model.taskModel.*
import com.smarthub.baseapplication.model.workflow.TaskDataUpdateModel
import com.smarthub.baseapplication.model.workflow.UpdatedTaskResponseModel
import com.smarthub.baseapplication.network.APIClient
import com.smarthub.baseapplication.ui.alert.model.response.SendAlertResponse
import com.smarthub.baseapplication.ui.alert.model.response.SendAlertResponseNew
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponse
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskActivityRepo(private var apiClient: APIClient) {

    val TAG = "TaskActivityRepo"
    val createNewTaskResponse: MutableLiveData<CreateNewTaskResponse> = MutableLiveData()
    val assignTaskResponse: MutableLiveData<CreateNewTaskResponse> = MutableLiveData()
    var updatedTaskResponse: SingleLiveEvent<Resource<UpdatedTaskResponseModel>>? = null
    var geoGraphyDropDownDataResponse: SingleLiveEvent<Resource<GeoGraphyLevelData>>? = null
    var taskInfoResponse: SingleLiveEvent<Resource<TaskInfo>>? = null

    init {
        geoGraphyDropDownDataResponse=SingleLiveEvent<Resource<GeoGraphyLevelData>>()
        taskInfoResponse=SingleLiveEvent<Resource<TaskInfo>>()
        updatedTaskResponse=SingleLiveEvent<Resource<UpdatedTaskResponseModel>>()
    }

    

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

    fun getGeoGraphylevelDropdownData(data: GeoGraphyLevelPostData?) {
        apiClient.getGeoGraphyLevel(data!!).enqueue(object : Callback<GeoGraphyLevelData> {
            override fun onResponse(
                call: Call<GeoGraphyLevelData?>,
                response: Response<GeoGraphyLevelData?>
            ) {
                AppLogger.log("$TAG onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<GeoGraphyLevelData?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<GeoGraphyLevelData?>) {
                if (response.body() != null) {
                    geoGraphyDropDownDataResponse?.postValue(Resource.success(response.body()!!,200))
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    geoGraphyDropDownDataResponse?.postValue(Resource.error("${response.message}",null,201))
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

    fun assignTask(data: TaskAssignModel?) {
        apiClient.AssignTask(data!!).enqueue(object : Callback<CreateNewTaskResponse?> {
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
                    assignTaskResponse.postValue(response.body())
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    assignTaskResponse.postValue(CreateNewTaskResponse("Failed", response.message))
                } else if (iThrowableLocalMessage != null) assignTaskResponse.postValue(
                    CreateNewTaskResponse("Failed", iThrowableLocalMessage)
                ) else assignTaskResponse.postValue(
                    CreateNewTaskResponse("Failed", AppConstants.GENERIC_ERROR)
                )
            }
        })
    }

    fun getTaskInfoById(data: GetTaskInfoPostData?) {
        apiClient.getTaskInfo(data!!).enqueue(object : Callback<TaskInfo> {
            override fun onResponse(
                call: Call<TaskInfo?>,
                response: Response<TaskInfo?>
            ) {
                AppLogger.log("$TAG onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<TaskInfo?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<TaskInfo?>) {
                if (response.body() != null) {
                    taskInfoResponse?.postValue(Resource.success(response.body()!!,200))
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    taskInfoResponse?.postValue(Resource.error("${response.message}",null,201))
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

    fun updateTaskData(data: TaskDataUpdateModel?) {
        AppLogger.log("updateTaskData==> : ${Gson().toJson(data)}")
        apiClient.updateTaskData(data!!).enqueue(object : Callback<UpdatedTaskResponseModel> {
            override fun onResponse(
                call: Call<UpdatedTaskResponseModel?>,
                response: Response<UpdatedTaskResponseModel?>
            ) {
                AppLogger.log("$TAG onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<UpdatedTaskResponseModel?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log(TAG + " onResponse get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<UpdatedTaskResponseModel?>) {
                if (response.body() != null) {
                    updatedTaskResponse?.postValue(Resource.success(response.body()!!,200))
                }
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    updatedTaskResponse?.postValue(Resource.error("${response.message}",null,201))
                } else if (iThrowableLocalMessage != null) updatedTaskResponse?.postValue(
                    Resource.error(iThrowableLocalMessage, null, 500)
                ) else updatedTaskResponse?.postValue(
                    Resource.error(AppConstants.GENERIC_ERROR, null, 500))
            }
        })
    }
    
}