package com.smarthub.baseapplication.ui.fragments.task

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.CommonResponse
import com.smarthub.baseapplication.model.qatcheck.QalLaunchModel
import com.smarthub.baseapplication.model.qatcheck.update.QatUpdateModel
import com.smarthub.baseapplication.model.taskModel.*
import com.smarthub.baseapplication.model.taskModel.assignTask.AssignTaskNewModel
import com.smarthub.baseapplication.model.taskModel.assignTask.Assigntask
import com.smarthub.baseapplication.model.workflow.TaskDataUpdateModel
import com.smarthub.baseapplication.model.workflow.UpdatedTaskResponseModel
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.repo.TaskActivityRepo
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class TaskViewModel : ViewModel() {
    var updateTaskDataResponse: SingleLiveEvent<Resource<UpdatedTaskResponseModel>>?=null
    var createTaskResponse: MutableLiveData<CreateNewTaskResponse>?=null
    var taskActivityRepo:TaskActivityRepo?=null
    var processTemplatemanual :Processtemplatecallmanual
    var geoGraphyLevelDataResponse: SingleLiveEvent<Resource<GeoGraphyLevelData>>?=null
    var taskAssignResponse: MutableLiveData<CreateNewTaskResponse>?=null
    var getTaskInfoResponse: SingleLiveEvent<Resource<TaskInfo>>?=null
    var qatUpdateModel : SingleLiveEvent<Resource<QatUpdateModel?>>?=null

init {
//    processTemplatemanual = Utils.getCreateNewTaskDummyData()
    processTemplatemanual = Processtemplatecallmanual()
    taskActivityRepo= TaskActivityRepo(APIInterceptor.get())
    createTaskResponse=taskActivityRepo?.createNewTaskResponse
    geoGraphyLevelDataResponse=taskActivityRepo?.geoGraphyDropDownDataResponse
    taskAssignResponse=taskActivityRepo?.assignTaskResponse
    getTaskInfoResponse=taskActivityRepo?.taskInfoResponse
    getTaskInfoResponse=taskActivityRepo?.taskInfoResponse
    qatUpdateModel=taskActivityRepo?.qatUpdateModel
    updateTaskDataResponse=taskActivityRepo?.updatedTaskResponse
}

    fun updateTaskDataWithDataId(data: TaskDataUpdateModel, taskId:String){
        taskActivityRepo?.updateTaskData(data,taskId)
    }

    fun qatLaunchMain(data : QalLaunchModel){
        taskActivityRepo?.qatMainRequestAll(data)
    }

    fun updateProcessTempletManual(item:Processtemplatecallmanual){
        processTemplatemanual=item
    }

    fun createNewTask(data:Processtemplatecallmanual){

        val createNewTaskModel = CreateNewTaskModel(arrayListOf(data))
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
    fun taskAssign(data: Assigntask){
        val item:AssignTaskNewModel= AssignTaskNewModel()
        item.assigntask=data
        taskActivityRepo?.assignTask(item)
    }

    fun getTaskById(id: String){
        val item = GetTaskInfoPostData(id.toInt())
        taskActivityRepo?.getTaskInfoById(item)
    }
}