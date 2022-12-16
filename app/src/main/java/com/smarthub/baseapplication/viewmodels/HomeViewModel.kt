package com.smarthub.baseapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.home.HomeResponse
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.model.project.ProjectModelData
import com.smarthub.baseapplication.model.project.TaskModelData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequest
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.repo.HomeRepo
import com.smarthub.baseapplication.utils.AppLogger

class HomeViewModel : ViewModel() {

    var homeRepo: HomeRepo?=null
    var getHomeDataResponse : SingleLiveEvent<Resource<HomeResponse>>?=null
    var getProjectDataResponse : SingleLiveEvent<Resource<ProjectModelData>>?=null
    var getTaskDataResponse : SingleLiveEvent<Resource<TaskModelData>>?=null
    var getServiceRequest : SingleLiveEvent<Resource<ServiceRequestAllData>>?=null
    var myTeamTask : SingleLiveEvent<List<MyTeamTask>?>?=null
    var myTask : SingleLiveEvent<List<MyTeamTask>?>?=null

    init {
        homeRepo = HomeRepo(APIInterceptor.get())
        getHomeDataResponse = homeRepo?.homeResponse
        getProjectDataResponse = homeRepo?.projectResponse
        getTaskDataResponse = homeRepo?.taskResponse
        getServiceRequest = homeRepo?.serviceRequest
        myTeamTask  = SingleLiveEvent<List<MyTeamTask>?>()
        myTask  = SingleLiveEvent<List<MyTeamTask>?>()
    }

    fun updateMyTeamTask(data : List<MyTeamTask>?){
        AppLogger.log("updateMyTeamTask : data ${data?.size}")
        myTeamTask?.postValue(data)
    }

    fun updateMyTask(data : List<MyTeamTask>?){
        AppLogger.log("updateMyTask : data ${data?.size}")
        myTask?.postValue(data)
    }

    fun homeData(): SingleLiveEvent<Resource<HomeResponse>>?{
        return getHomeDataResponse
    }

    fun fetchHomeData(){
        homeRepo?.fetchHomeData()
    }

    fun fetchProjectsData(){
        homeRepo?.fetchProjectData()
    }

    fun fetchTaskData(templateName : String){
        homeRepo?.fetchTaskData(templateName)
    }
    fun fetchServiceRequestData(templateName : String){
        homeRepo?.fetchServiceRequestData(templateName)
    }

}