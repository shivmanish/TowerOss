package com.smarthub.baseapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.workflow.TaskDataList
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.repo.HomeRepo

class TaskViewModel : ViewModel() {

    var homeRepo: HomeRepo?=null
    var taskDataList: SingleLiveEvent<Resource<TaskDataList?>>? = null

    init {
        homeRepo = HomeRepo(APIInterceptor.get())
        taskDataList = homeRepo?.taskDataList
    }

    fun fetchTaskDetails(id:String) {
        homeRepo?.getTaskById(id)
    }
}