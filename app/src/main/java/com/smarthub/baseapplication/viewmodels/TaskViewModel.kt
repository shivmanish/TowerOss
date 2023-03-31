package com.smarthub.baseapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.workflow.TaskDataList
import com.smarthub.baseapplication.model.workflow.TaskDataUpdateModel
import com.smarthub.baseapplication.model.workflow.UpdatedTaskResponseModel
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.repo.HomeRepo
import com.smarthub.baseapplication.network.repo.TaskActivityRepo

class TaskViewModel : ViewModel() {

    var homeRepo: HomeRepo?=null
    var taskActivityRepo:TaskActivityRepo?=null
    var updateTaskDataResponse: SingleLiveEvent<Resource<UpdatedTaskResponseModel>>?=null
    var taskDataList: SingleLiveEvent<Resource<TaskDataList?>>? = null

    init {
        taskActivityRepo= TaskActivityRepo(APIInterceptor.get())
        homeRepo = HomeRepo(APIInterceptor.get())
        taskDataList = homeRepo?.taskDataList
        updateTaskDataResponse=taskActivityRepo?.updatedTaskResponse

    }

    fun fetchTaskDetails(id:String?) {
        homeRepo?.getTaskById(id)
    }
    fun updateTaskDataWithDataId(data: TaskDataUpdateModel,taskId:String){
        taskActivityRepo?.updateTaskData(data,taskId)
    }
}