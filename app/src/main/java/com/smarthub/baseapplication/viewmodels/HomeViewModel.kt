package com.smarthub.baseapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.dropdown.newData.DropDownNew
import com.smarthub.baseapplication.model.home.HomeResponse
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.model.notification.newData.NotificationNew
import com.smarthub.baseapplication.model.project.ProjectModelData
import com.smarthub.baseapplication.model.project.TaskModelData
import com.smarthub.baseapplication.model.qatcheck.QalLaunchModel
import com.smarthub.baseapplication.model.qatcheck.punch_point.QatPunchPointModel
import com.smarthub.baseapplication.model.search.SearchList
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.log.LogSearchData
import com.smarthub.baseapplication.model.serviceRequest.new_site.GenerateSiteIdResponse
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.NocAndCompModel
import com.smarthub.baseapplication.model.siteInfo.OpcoDataList
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModelUpdate
import com.smarthub.baseapplication.model.siteInfo.newData.SiteInfoModelNew
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerCivilInfraModel
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.newData.OpcoInfoNewModel
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignModel
import com.smarthub.baseapplication.model.siteInfo.oprationInfo.UpdateOperationInfo
import com.smarthub.baseapplication.model.siteInfo.powerFuel.PowerAndFuelModel
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.QatMainModel
import com.smarthub.baseapplication.model.siteInfo.service_request.ServiceRequestModel
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.UtilitiesEquipModel
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteAgreementModel
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteacquisitionAgreement
import com.smarthub.baseapplication.model.workflow.TaskDataList
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.network.repo.HomeRepo
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.CreateSiteModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.repo.BasicInfoDialougeResponse
import com.smarthub.baseapplication.utils.AppLogger

class HomeViewModel : ViewModel() {

    var homeRepo: HomeRepo?=null
    var getHomeDataResponse : SingleLiveEvent<Resource<HomeResponse>>?=null
    var getProjectDataResponse : SingleLiveEvent<Resource<ProjectModelData>>?=null
    var getTaskDataResponse : SingleLiveEvent<Resource<TaskModelData>>?=null
    var getServiceRequest : SingleLiveEvent<Resource<ServiceRequestAllData>>?=null
    var myTeamTask : SingleLiveEvent<List<MyTeamTask>?>?=null
    var myTask : SingleLiveEvent<List<MyTeamTask>?>?=null
    var siteInfoResponse : SingleLiveEvent<Resource<SiteInfoModel?>>?=null
    var serviceRequestModelResponse : SingleLiveEvent<Resource<ServiceRequestModel?>>?=null
    var loglivedata : SingleLiveEvent<Resource<LogSearchData?>>?=null
    var opcoTenancyListResponse : SingleLiveEvent<Resource<OpcoDataList?>>?=null
    var serviceRequestAllData : SingleLiveEvent<Resource<ServiceRequestAllData?>>?=null
    var siteSearchResponse : SingleLiveEvent<Resource<SearchList>>?=null
    var basicinfoModel: SingleLiveEvent<Resource<BasicInfoDialougeResponse>>? = null
    var siteDropData: SingleLiveEvent<Resource<SiteInfoDropDownData>>? = null
    var basicInfoUpdate: SingleLiveEvent<Resource<BasicInfoDialougeResponse>>? = null
    var generateSiteId: SingleLiveEvent<Resource<GenerateSiteIdResponse>>? = null
    private var taskDataList: SingleLiveEvent<Resource<TaskDataList?>>? = null
    private var siteInfoModelUpdate: SingleLiveEvent<Resource<SiteInfoModelUpdate?>>? = null
    var opcoTenencyModelResponse : SingleLiveEvent<Resource<OpcoInfoNewModel?>>?=null
    var NocAndCompModelResponse : SingleLiveEvent<Resource<NocAndCompModel?>>?=null
    var TowerCivilInfraModelResponse : SingleLiveEvent<Resource<TowerCivilInfraModel?>>?=null
    var PlanDesignModelResponse : SingleLiveEvent<Resource<PlanAndDesignModel?>>?=null
    var QatModelResponse : SingleLiveEvent<Resource<QatMainModel?>>?=null
    var dropDownResponse : SingleLiveEvent<Resource<SiteInfoDropDownData>>?=null
    var dropDownResponseNew : SingleLiveEvent<Resource<DropDownNew>>?=null
    var powerAndFuelResponse:SingleLiveEvent<Resource<PowerAndFuelModel>>? = null
    var siteAgreementModel:SingleLiveEvent<Resource<SiteAgreementModel>>? = null
    var siteInfoModelNew:SingleLiveEvent<Resource<SiteInfoModelNew>>? = null
    var utilityEquipResponse:SingleLiveEvent<Resource<UtilitiesEquipModel>>? = null
    var notificationNew:SingleLiveEvent<Resource<NotificationNew>>? = null

    init {
        homeRepo = HomeRepo(APIInterceptor.get())
        getHomeDataResponse = homeRepo?.homeResponse
        getProjectDataResponse = homeRepo?.projectResponse
        getTaskDataResponse = homeRepo?.taskResponse
        getServiceRequest = homeRepo?.serviceRequest
        myTeamTask  = SingleLiveEvent<List<MyTeamTask>?>()
        myTask  = SingleLiveEvent<List<MyTeamTask>?>()
        siteSearchResponse = homeRepo?.siteSearchResponseData
        siteInfoResponse = homeRepo?.siteInfoResponse
        siteDropData = homeRepo?.siteDropDownDataResponse
        basicInfoUpdate = homeRepo?.basicInfoUpdate
        opcoTenancyListResponse = homeRepo?.opcoResponseData
        serviceRequestAllData = homeRepo?.serviceRequestAllData
        generateSiteId = homeRepo?.generateSiteIdResponse
        taskDataList = homeRepo?.taskDataList
        serviceRequestModelResponse = homeRepo?.serviceRequestModel
        loglivedata = homeRepo?.getloglivedata()
        opcoTenencyModelResponse=homeRepo?.opcoTenencyModel
        NocAndCompModelResponse=homeRepo?.noCandCompModel
        TowerCivilInfraModelResponse=homeRepo?.towerAndCivilInfraModel
        siteInfoModelUpdate = homeRepo?.siteInfoUpdateData
        PlanDesignModelResponse=homeRepo?.planAndDesignModel
        QatModelResponse=homeRepo?.qatMainModelResponse
        powerAndFuelResponse=homeRepo?.powerFuelModel
        dropDownResponse = homeRepo?.dropDownResoonse
        dropDownResponseNew = homeRepo?.dropDownResponseNew
        utilityEquipResponse=homeRepo?.utilityEquipModel
        siteAgreementModel = homeRepo?.siteAgreementModel
        siteInfoModelNew = homeRepo?.siteInfoModelNew
        notificationNew = homeRepo?.notificationNew
    }

    fun updateData(basicinfoModel: BasicinfoModel){
        homeRepo?.updateData(basicinfoModel)
    }

    fun updateOperationInfo(basicinfoModel: UpdateOperationInfo){
        homeRepo?.updateOperationInfo(basicinfoModel)
    }

    fun generateSiteId(basicinfoModel: GenerateSiteIdResponse){
        homeRepo?.generateSiteId(basicinfoModel)
    }

    fun updateBasicInfo(basicinfoModel: BasicinfoModel){
        homeRepo?.updateSiteInfo(basicinfoModel)
    }

    fun createSite(basicinfoModel: CreateSiteModel){
        homeRepo?.createSite(basicinfoModel)
    }

    fun getNotifications(){
        homeRepo?.getAllNotification()
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
    fun fetchServiceRequestData(id : String){
        homeRepo?.fetchServiceRequestData(id)
    }

    fun fetchSiteInfoData(id : String){
        homeRepo?.siteInfoById(id)
    }

    fun serviceRequestAll(id : String){
        homeRepo?.serviceRequestAll(id)
    }

    fun opcoTenancyRequestAll(id : String){
        homeRepo?.opcoRequestAll(id)
    }
    fun planAndDesignRequestAll(id : String){
        homeRepo?.planDesignRequestAll(id)
    }

    fun qatRequestAll(id : String){
        homeRepo?.qatRequestAll(id)
    }

    fun qatMainRequestAll(id : String){
        homeRepo?.qatMainRequestAll(id)
    }

    fun qatLaunchMain(data : QalLaunchModel){
        homeRepo?.qatMainRequestAll(data)
    }

    fun qatLaunchMain(data : QatPunchPointModel){
        homeRepo?.qatMainRequestAll(data)
    }

    fun fetchSiteAgreementModelRequest(id : String){
        homeRepo?.siteAgreementRequestAll(id)
    }

    fun utilityRequestAll(id : String){
        homeRepo?.utilitiEquipRequestAll(id)
    }

    fun NocAndCompRequestAll(id : String){
        homeRepo?.NocAndCompRequestAll(id)
    }
    fun TowerAndCivilRequestAll(id : String){
        homeRepo?.TowerCivilInfraRequestAll(id)
    }

//    fun fetchSiteSearchData(id:String) {
//        homeRepo?.siteSearchData(id)
//    }
//
//    fun fetchSiteSearchDataNew(id:String) {
//        homeRepo?.siteSearchDataNew(id)
//    }

    fun fetchSiteSearchData(id:String,category :String) {
        homeRepo?.searchSiteAll(id,category)
    }

    fun fetchSiteDropDownData() {
        homeRepo?.siteInfoDropDown()
    }

    fun fetchPowerAndFuel(id:String) {
        homeRepo?.powerAndFuelRequestAll(id)
    }
    fun fetchChangeLog(id:String) {
        homeRepo?.chamgeLogAll(id)
    }

    fun fetchDropDown() {
        homeRepo?.siteInfoDropDown()
    }

    fun fetchDropDownNew() {
        homeRepo?.siteInfoDropDownNew()
    }

    fun updateSiteInfo(siteAgreementsData: SiteacquisitionAgreement) {
        homeRepo?.updateAgreementSiteInfo(siteAgreementsData)
    }
}