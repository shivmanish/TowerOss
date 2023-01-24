package com.smarthub.baseapplication.ui.fragments.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.CommonResponse
import com.smarthub.baseapplication.model.taskModel.*
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.repo.TaskActivityRepo
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class TaskViewModel : ViewModel() {
    var createTaskResponse: MutableLiveData<CreateNewTaskResponse>?=null
    var taskActivityRepo:TaskActivityRepo?=null
    var processTemplatemanual :Processtemplatecallmanual
    var geoGraphyLevelDataResponse: SingleLiveEvent<Resource<GeoGraphyLevelData>>?=null
    var taskAssignResponse: MutableLiveData<CreateNewTaskResponse>?=null

init {
    processTemplatemanual = Utils.getCreateNewTaskDummyData()
    taskActivityRepo= TaskActivityRepo(APIInterceptor.get())
    createTaskResponse=taskActivityRepo?.createNewTaskResponse
    geoGraphyLevelDataResponse=taskActivityRepo?.geoGraphyDropDownDataResponse
    taskAssignResponse=taskActivityRepo?.assignTaskResponse
}

    fun updateProcessTempletManual(item:Processtemplatecallmanual){
        processTemplatemanual=item
    }

    fun createNewTask(){
        var list=ArrayList<Processtemplatecallmanual>()
        list.add(processTemplatemanual)
        var createNewTaskModel = CreateNewTaskModel(list)
        AppLogger.log("Create new Task Json: ${Gson().toJson(createNewTaskModel)}")
        taskActivityRepo?.createNewTask(createNewTaskModel)
    }
    fun updateSiteId(id:String){
        processTemplatemanual.siteid=id
    }
    fun getCreateNewTask(): MutableLiveData<CreateNewTaskResponse>?{
        return createTaskResponse
    }


    fun getGeoGraphyData(data:GeoGraphyLevelPostData){
        taskActivityRepo?.getGeoGraphylevelDropdownData(data)
    }
    fun taskAssign(data:Updateprocesstask){
        var item = TaskAssignModel(data)
        taskActivityRepo?.assignTask(item)
    }
}