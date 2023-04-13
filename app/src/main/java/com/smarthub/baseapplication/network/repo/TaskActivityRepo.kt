package com.smarthub.baseapplication.network.repo

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.APIError
import com.smarthub.baseapplication.model.taskModel.*
import com.smarthub.baseapplication.model.taskModel.assignTask.AssignTaskNewModel
import com.smarthub.baseapplication.model.workflow.TaskDataList
import com.smarthub.baseapplication.model.workflow.TaskDataUpdateModel
import com.smarthub.baseapplication.model.workflow.UpdatedTaskResponseModel
import com.smarthub.baseapplication.network.APIClient
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.EndPoints
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.AppLogger.log
import com.smarthub.baseapplication.utils.Utils.isNetworkConnected
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskActivityRepo(private var apiClient: APIClient) {

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
        AppLogger.log("TaskData for create or update=====>:${Gson().toJson(data)}")
        apiClient.createNewTask(data!!).enqueue(object : Callback<CreateNewTaskResponse?> {
            override fun onResponse(
                call: Call<CreateNewTaskResponse?>,
                response: Response<CreateNewTaskResponse?>,
            ) {
                AppLogger.log("createNewTask onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<CreateNewTaskResponse?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log("createNewTask onResponse get response " + t.localizedMessage)

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
                response: Response<GeoGraphyLevelData?>,
            ) {
                AppLogger.log("getGeoGraphylevelDropdownData onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<GeoGraphyLevelData?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log( "getGeoGraphylevelDropdownData onResponse get response " + t.localizedMessage)

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

    fun assignTask(data: AssignTaskNewModel?) {
        AppLogger.log("assignTask data====>${Gson().toJson(data)}")
        apiClient.AssignTask(data!!).enqueue(object : Callback<CreateNewTaskResponse?> {
            override fun onResponse(
                call: Call<CreateNewTaskResponse?>,
                response: Response<CreateNewTaskResponse?>,
            ) {
                AppLogger.log("assignTask onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<CreateNewTaskResponse?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log( "assignTask onResponse get response " + t.localizedMessage)

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
                response: Response<TaskInfo?>,
            ) {
                AppLogger.log("getTaskInfoById onResponse get response $response")
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<TaskInfo?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                AppLogger.log("getTaskInfoById onResponse get response " + t.localizedMessage)

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

    fun updateTaskData(data: TaskDataUpdateModel?,id:String) {
        AppLogger.log("updateTaskData==> : ${Gson().toJson(data)}")
        if (!isNetworkConnected()) {
            val value = AppPreferences.getInstance().getString("TaskDetailsData$id")
            log("task details in offline mode===>:$value")
            if (value != null && !value.isEmpty()) {
                try {
                    val alldata = Gson().fromJson(value,
                        TaskDataList::class.java)
                    if (alldata != null && alldata.isNotEmpty()) {
                        val item= alldata[0]
                        item.ModuleId= data?.ModuleId.toString()
                        item.ModuleName= data?.ModuleName.toString()
                        alldata[0]=item
                        val jssonData = Gson().toJson(alldata)
                        AppPreferences.getInstance().saveString("TaskDetailsData$id", jssonData)
                    }
                    //                    Logger.getLogger("ProfileRepo").warning(response.toString());
                } catch (e: Exception) {
                    log(e.localizedMessage)
                }
            }
            updatedTaskResponse?.postValue(Resource.success(UpdatedTaskResponseModel("","Data updated"),200))
            AppPreferences.getInstance().saveTaskOfflineApi(Gson().toJson(data),"${APIInterceptor.DYNAMIC_BASE_URL}${EndPoints.WORKFLOW_DATA_URL}","updateTaskData$id")
            return
        }
        apiClient.updateTaskData(data!!).enqueue(object : Callback<UpdatedTaskResponseModel> {
            override fun onResponse(
                call: Call<UpdatedTaskResponseModel?>,
                response: Response<UpdatedTaskResponseModel?>, ) {
                log("onResponse main : $response")
                if ("$response"=="null"){
                    updatedTaskResponse?.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500))
                    return
                }
                reportSuccessResponse(response)
            }

            override fun onFailure(call: Call<UpdatedTaskResponseModel?>, t: Throwable) {
                reportErrorResponse(null, t.localizedMessage)
                log(" on failure get response " + t.localizedMessage)

            }

            private fun reportSuccessResponse(response: Response<UpdatedTaskResponseModel?>) {
                if (response.body() != null) {
                    updatedTaskResponse?.postValue(Resource.success(response.body()!!,200))
                }else if (response.errorBody() != null) {
                    updatedTaskResponse?.postValue(Resource.success(response.body()!!,200))
                }else updatedTaskResponse?.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500))
            }

            private fun reportErrorResponse(response: APIError?, iThrowableLocalMessage: String?) {
                if (response != null) {
                    updatedTaskResponse?.postValue(Resource.error("${response.message}",null,201))
                } else if (iThrowableLocalMessage != null) updatedTaskResponse?.postValue(Resource.error(iThrowableLocalMessage, null, 500))
                else updatedTaskResponse?.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500))
            }
        })
    }
    
}