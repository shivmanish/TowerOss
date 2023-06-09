package com.smarthub.baseapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.dropdown.newData.DropDownNew
import com.smarthub.baseapplication.model.home.HomeResponse
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.model.home.alerts.AlertAllDataResponse
import com.smarthub.baseapplication.model.logs.LogsDataModel
import com.smarthub.baseapplication.model.logs.PostLogData
import com.smarthub.baseapplication.model.notification.newData.AddNotificationModel
import com.smarthub.baseapplication.model.notification.newData.AddNotificationResponse
import com.smarthub.baseapplication.model.notification.newData.NotificationNew
import com.smarthub.baseapplication.model.project.ProjectModelData
import com.smarthub.baseapplication.model.project.TaskModelData
import com.smarthub.baseapplication.model.qatcheck.QalLaunchModel
import com.smarthub.baseapplication.model.qatcheck.punch_point.QatPunchPointModel
import com.smarthub.baseapplication.model.qatcheck.update.QatUpdateModel
import com.smarthub.baseapplication.model.search.SearchList
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.acquisitionSurvey.AcquisitionSurveyModel
import com.smarthub.baseapplication.model.serviceRequest.new_site.GenerateSiteIdResponse
import com.smarthub.baseapplication.model.siteIBoard.AttachmentConditionsDataModel
import com.smarthub.baseapplication.model.siteIBoard.AttachmentsConditions
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllDataModel
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.updateNocComp.UpdateNocCompModel
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.updateNocComp.UpdateNocCompResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllDataModel
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelAllDataModel
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.updatePowerFuel.UpdatePowerFuelResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SiteAcquisitionAllDataModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcqModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcqResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.AllsiteInfoDataModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.updateSiteInfo.UpdateSiteInfoResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.NewTowerCivilAllData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TowerCivilAllDataModel
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.updateTwrCivil.UpdateTwrCivilInfraResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAllDataModel
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentModel
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityResponseModel
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllDataModel
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.updateSstSbc.UpdateSstSbcModel
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.updateSstSbc.UpdateSstSbcResponseModel
import com.smarthub.baseapplication.model.siteInfo.OpcoDataList
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModelUpdate
import com.smarthub.baseapplication.model.siteInfo.newData.SiteInfoModelNew
import com.smarthub.baseapplication.model.siteInfo.oprationInfo.UpdateOperationInfo
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignModel
import com.smarthub.baseapplication.model.siteInfo.qat.QatModel
import com.smarthub.baseapplication.model.siteInfo.qat.SaveCheckpointModel
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.QatMainModel
import com.smarthub.baseapplication.model.siteInfo.service_request.ServiceRequestModel
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteacquisitionAgreement
import com.smarthub.baseapplication.model.taskModel.GeoGraphyLevelPostData
import com.smarthub.baseapplication.model.taskModel.department.DepartmentDataModel
import com.smarthub.baseapplication.model.taskModel.update.CloseTaskModel
import com.smarthub.baseapplication.model.workflow.TaskDataList
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.EndPoints
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.network.repo.HomeRepo
import com.smarthub.baseapplication.network.repo.TaskActivityRepo
import com.smarthub.baseapplication.network.repo.UpdateIBoardRepo
import com.smarthub.baseapplication.ui.alert.AlertRepo
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponse
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.AddAttachmentModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.CreateSiteModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.repo.BasicInfoDialougeResponse
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfBasicResponse
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfMainResponse
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.rfSurveyUpdate.UpdateRfSurveyResponseModel
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class HomeViewModel : ViewModel() {

    var homeRepo: HomeRepo? = null
    var updateIBoardRepo: UpdateIBoardRepo? = null
    var alertRepo: AlertRepo? = null
    var taskActivityRepo: TaskActivityRepo? = null
    var getHomeDataResponse: SingleLiveEvent<Resource<HomeResponse>>? = null
    var getProjectDataResponse: SingleLiveEvent<Resource<ProjectModelData>>? = null
    var getTaskDataResponse: SingleLiveEvent<Resource<TaskModelData>>? = null
    var getServiceRequest: SingleLiveEvent<Resource<ServiceRequestAllData>>? = null
    var myTeamTask: SingleLiveEvent<List<MyTeamTask>?>? = null
    var myTask: SingleLiveEvent<List<MyTeamTask>?>? = null
    var siteInfoResponse: SingleLiveEvent<Resource<SiteInfoModel?>>? = null
    var serviceRequestModelResponse: SingleLiveEvent<Resource<ServiceRequestModel?>>? = null
    var acquisitionSurveyAllDataItem: SingleLiveEvent<Resource<AcquisitionSurveyModel?>>? = null
    var loglivedata: SingleLiveEvent<Resource<LogsDataModel?>>? = null
    var opcoTenancyListResponse: SingleLiveEvent<Resource<OpcoDataList?>>? = null
    var serviceRequestAllData: SingleLiveEvent<Resource<ServiceRequestAllData?>>? = null
    var siteSearchResponse: SingleLiveEvent<Resource<SearchList>>? = null
    var basicinfoModel: SingleLiveEvent<Resource<BasicInfoDialougeResponse>>? = null
    var siteDropData: SingleLiveEvent<Resource<SiteInfoDropDownData>>? = null
    var basicInfoUpdate: SingleLiveEvent<Resource<BasicInfoDialougeResponse>>? = null
    var generateSiteId: SingleLiveEvent<Resource<GenerateSiteIdResponse>>? = null
    var closeTaskModel: SingleLiveEvent<Resource<CloseTaskModel>>? = null
    private var taskDataList: SingleLiveEvent<Resource<TaskDataList?>>? = null
    private var siteInfoModelUpdate: SingleLiveEvent<Resource<SiteInfoModelUpdate?>>? = null
    var opcoTenencyModelResponse : SingleLiveEvent<Resource<OpcoTenencyAllDataModel?>>?=null
    var NocAndCompModelResponse : SingleLiveEvent<Resource<NocCompAllDataModel?>>?=null
    var TowerCivilInfraModelResponse : SingleLiveEvent<Resource<TowerCivilAllDataModel?>>?=null
    var PlanDesignModelResponse : SingleLiveEvent<Resource<PlanAndDesignModel?>>?=null
    var QatModelResponse : SingleLiveEvent<Resource<QatMainModel?>>?=null
    var QatMainTempletResponse : SingleLiveEvent<Resource<QatModel?>>?=null
    var qatUpdateModel : SingleLiveEvent<Resource<QatUpdateModel?>>?=null
    var dropDownResponse : SingleLiveEvent<Resource<SiteInfoDropDownData>>?=null
    var dropDownResponseNew : SingleLiveEvent<Resource<DropDownNew>>?=null
    var powerAndFuelResponse:SingleLiveEvent<Resource<PowerFuelAllDataModel>>? = null
    var siteAgreementModel:SingleLiveEvent<Resource<SiteAcquisitionAllDataModel>>? = null
    var sstSbcModelResponse:SingleLiveEvent<Resource<SstSbcAllDataModel>>? = null
    var sstSbcOfflineModelResponse:SingleLiveEvent<Resource<SstSbcAllDataModel>>? = null
    var rfMainResponse:SingleLiveEvent<Resource<RfMainResponse>>? = null
    var siteInfoModelNew:SingleLiveEvent<Resource<SiteInfoModelNew>>? = null
    var utilityEquipResponse:SingleLiveEvent<Resource<UtilityEquipmentAllDataModel>>? = null
    var notificationNew:SingleLiveEvent<Resource<NotificationNew>>? = null
    var userDataListResponse:SingleLiveEvent<Resource<UserDataResponse>>? = null
    var addNotiResponse:SingleLiveEvent<Resource<AddNotificationResponse>>? = null
    var siteInfoDataResponse:SingleLiveEvent<Resource<AllsiteInfoDataModel>>? = null
    var updateSiteAcqDataResponse:SingleLiveEvent<Resource<UpdateSiteAcqResponseModel>>? = null
    var updateUtilityDataResponse:SingleLiveEvent<Resource<UpdateUtilityResponseModel>>? = null
    var updateNocCompDataResponse:SingleLiveEvent<Resource<UpdateNocCompResponseModel>>? = null
    var updateSstSbcDataResponse:SingleLiveEvent<Resource<UpdateSstSbcResponseModel>>? = null
    var updateRflivedataResponse:SingleLiveEvent<Resource<RfBasicResponse>>? = null
    var updateSiteInfoDataResponse:SingleLiveEvent<Resource<UpdateSiteInfoResponseModel>>? = null
    var updatePowerFuelDataResponse:SingleLiveEvent<Resource<UpdatePowerFuelResponseModel>>? = null
    var updateRfSurveyDataResponse:SingleLiveEvent<Resource<UpdateRfSurveyResponseModel>>? = null
    var updateTwrCivilInfraDataResponse:SingleLiveEvent<Resource<UpdateTwrCivilInfraResponseModel>>? = null
    var departmentDataDataResponse:SingleLiveEvent<Resource<DepartmentDataModel>>? = null
    var addAttachmentModel:SingleLiveEvent<Resource<AddAttachmentModel>>? = null
    var attachmentConditionModel:SingleLiveEvent<Resource<AttachmentConditionsDataModel>>? = null
    var homeAlertsDataModel:SingleLiveEvent<Resource<AlertAllDataResponse>>? = null

    init {
        homeRepo = HomeRepo(APIInterceptor.get())
        updateIBoardRepo = UpdateIBoardRepo(APIInterceptor.get())
        alertRepo = AlertRepo(APIInterceptor.get())
        alertRepo = AlertRepo(APIInterceptor.get())
        taskActivityRepo = TaskActivityRepo(APIInterceptor.get())
        getHomeDataResponse = homeRepo?.homeResponse
        getProjectDataResponse = homeRepo?.projectResponse
        getTaskDataResponse = homeRepo?.taskResponse
        getServiceRequest = homeRepo?.serviceRequest
        myTeamTask = SingleLiveEvent<List<MyTeamTask>?>()
        myTask = SingleLiveEvent<List<MyTeamTask>?>()
        siteSearchResponse = homeRepo?.siteSearchResponseData
        siteInfoResponse = homeRepo?.siteInfoResponse
        siteDropData = homeRepo?.siteDropDownDataResponse
        basicInfoUpdate = homeRepo?.basicInfoUpdate
        opcoTenancyListResponse = homeRepo?.opcoResponseData
        serviceRequestAllData = homeRepo?.serviceRequestAllData
        generateSiteId = homeRepo?.generateSiteIdResponse
        closeTaskModel = homeRepo?.closeTaskModel
        taskDataList = homeRepo?.taskDataList
        serviceRequestModelResponse = homeRepo?.serviceRequestModel
        updateUtilityDataResponse = updateIBoardRepo?.updateUtilityEquipResponse
        loglivedata = homeRepo?.getloglivedata()
        opcoTenencyModelResponse=homeRepo?.opcoTenencyModel
        NocAndCompModelResponse=homeRepo?.noCandCompModel
        TowerCivilInfraModelResponse=homeRepo?.towerAndCivilInfraModel
        siteInfoModelUpdate = homeRepo?.siteInfoUpdateData
        PlanDesignModelResponse=homeRepo?.planAndDesignModel
        QatModelResponse=homeRepo?.qatMainModelResponse
        QatMainTempletResponse=homeRepo?.qatModelResponse
        qatUpdateModel=homeRepo?.qatUpdateModel
        powerAndFuelResponse=homeRepo?.powerFuelModel
        dropDownResponse = homeRepo?.dropDownResoonse
        dropDownResponseNew = homeRepo?.dropDownResponseNew
        utilityEquipResponse=homeRepo?.utilityEquipModel
        siteAgreementModel = homeRepo?.siteAgreementModel
        sstSbcModelResponse = homeRepo?.sstSbcModel
        rfMainResponse =  homeRepo?.getRfSurveyModel()
        siteInfoModelNew = homeRepo?.siteInfoModelNew
        notificationNew = homeRepo?.notificationNew
        userDataListResponse=homeRepo?.userDataResponse
        addNotiResponse=homeRepo?.addNotificationResponse
        siteInfoDataResponse=homeRepo?.siteInfoDataModel
        acquisitionSurveyAllDataItem=homeRepo?.acquisitionSurveyAllDataItem
        updateSiteAcqDataResponse=updateIBoardRepo?.updateSiteAcqResponse
        updateUtilityDataResponse=updateIBoardRepo?.updateUtilityEquipResponse
        updateNocCompDataResponse=updateIBoardRepo?.updateNocCompResponse
        updateSstSbcDataResponse=updateIBoardRepo?.updateSstSbcResponse
        updateRflivedataResponse = updateIBoardRepo?.rfBasicResponselivedata
        updateSiteInfoDataResponse=updateIBoardRepo?.updateSiteInfoResponse
        updatePowerFuelDataResponse=updateIBoardRepo?.updatePowerFuelResponse
        updateRfSurveyDataResponse=updateIBoardRepo?.updateRfSurveyResponse
        updateTwrCivilInfraDataResponse=updateIBoardRepo?.updateTwrCivilInfraResponse
        departmentDataDataResponse=homeRepo?.departmentDataModel
        addAttachmentModel=homeRepo?.addAttachmentModel
        attachmentConditionModel=homeRepo?.attachmentConsitionsModel
        homeAlertsDataModel=alertRepo?.homeAlertResponseLivedata
        sstSbcOfflineModelResponse = SingleLiveEvent()
    }

    fun updateData(basicinfoModel: BasicinfoModel){
        homeRepo?.updateData(basicinfoModel)
    }

    fun addAttachmentData(addAttachmentModel : AddAttachmentModel){
        AppLogger.log("attachment model data=====>${Gson().toJson(addAttachmentModel)}")
        homeRepo?.addAttachmentData(addAttachmentModel)
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

    fun closeTask(taskId: String,remark : String){
        homeRepo?.closeTask(taskId,remark)
    }

    fun reopenTask(taskId: String,remark : String){
        homeRepo?.reopenTask(taskId,remark)
    }

    fun createSite(basicinfoModel: CreateSiteModel){
        homeRepo?.createSite(basicinfoModel)
    }

    fun getNotifications(){
        homeRepo?.getAllNotification()
    }

    fun getUsers(){
        homeRepo?.getUserData()
    }
    fun addNotification(data:AddNotificationModel){
        homeRepo?.addNotification(data)
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
    fun fetchHomeAlertData(){
        alertRepo?.getHomeAlertDetails()
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

    fun siteInfoRequestAll(id : String){
        homeRepo?.SiteInfoRequestAll(id)
    }
    fun attachmentConditionsRequestAll(){
        homeRepo?.AttachmentsConditionsRequestAll()
    }

    fun siteAcquisitionSurveyById(id : String){
        homeRepo?.siteAcquisitionSurveyById(id)
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

    fun saveQatPunchPoint(data : SaveCheckpointModel){
        homeRepo?.saveQatPunchPointRequestAll(data)
    }

    fun fetchSiteAgreementModelRequest(id : String){
        homeRepo?.siteAgreementRequestAll(id)
    }

    fun fetchSstSbcModelRequest(id : String){
        homeRepo?.sstSbcRequestAll(id)
    }
    fun fetchRfRequest(id : String){
        homeRepo?.RfRequestAll(id)
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

    fun postChangeLog(data:PostLogData){
        homeRepo?.UpdateLogData(data)
    }

    fun fetchDropDown() {
        homeRepo?.siteInfoDropDown()
    }

    fun fetchDropDownNew() {
        taskActivityRepo?.getGeoGraphylevelDropdownData(GeoGraphyLevelPostData(""))
        homeRepo?.siteInfoDropDownNew()
    }

    fun updateSiteInfo(siteAgreementsData: SiteacquisitionAgreement) {
        homeRepo?.updateAgreementSiteInfo(siteAgreementsData)
    }

    fun updateSiteAcq(data: UpdateSiteAcquiAllData) {
        val dataModel = UpdateSiteAcqModel()
        val tempList: ArrayList<UpdateSiteAcquiAllData> = ArrayList()
        tempList.clear()
        tempList.add(data)
        dataModel.SAcqSiteAcquisition = tempList
        if (!Utils.isNetworkConnected()) {
            val site_id = AppController.getInstance().siteid
            val value = AppPreferences.getInstance().getString("siteAgreementRequestAll${site_id}")
            var position = 0
            println("Preference id is siteAgreementRequestAll${site_id}")
            var cache_model = SiteAcquisitionAllDataModel()
            if (value != null && !value.isEmpty()) {
                //Save the Service fot later updatation when online
                try {
                    cache_model = Gson().fromJson(value, SiteAcquisitionAllDataModel::class.java)
                    for ((index, value) in cache_model.SAcqSiteAcquisition!!.withIndex()) {
                        if (value.id == data.id) {
                            position = index
                            break
                        }
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            if (cache_model.SAcqSiteAcquisition == null) {
                cache_model.SAcqSiteAcquisition = ArrayList()
            }
            if (cache_model.SAcqSiteAcquisition?.size == 0) {
                cache_model.SAcqSiteAcquisition?.add(
                    NewSiteAcquiAllData(
                        null, null, null, null, null,null,"", 0, false, ""
                    )!!
                )
            }
            if (data.SAcqAcquitionSurvey != null) {
                cache_model.SAcqSiteAcquisition?.get(position)?.SAcqAcquitionSurvey = ArrayList()
                cache_model.SAcqSiteAcquisition!!.get(position).SAcqAcquitionSurvey?.addAll(data.SAcqAcquitionSurvey!!)
            }
            if (data.SAcqAgreement != null) {
                cache_model.SAcqSiteAcquisition?.get(position)?.SAcqAgreement = ArrayList()
                cache_model.SAcqSiteAcquisition!!.get(position).SAcqAgreement?.addAll(data.SAcqAgreement!!)

            }
            if (data.SAcqAssignACQTeam != null) {
                cache_model.SAcqSiteAcquisition?.get(position)?.SAcqAssignACQTeam = ArrayList()
                cache_model.SAcqSiteAcquisition!!.get(position).SAcqAssignACQTeam?.addAll(data.SAcqAssignACQTeam!!)

            }
            if (data.SAcqSoftAcquisition != null) {
                cache_model.SAcqSiteAcquisition?.get(position)?.SAcqSoftAcquisition = ArrayList()
                cache_model.SAcqSiteAcquisition!!.get(position).SAcqSoftAcquisition?.addAll(data.SAcqSoftAcquisition!!)

            }

            val jsonStringData = Gson().toJson(cache_model)
            AppPreferences.getInstance().saveString("siteAgreementRequestAll${site_id}", jsonStringData)
            AppPreferences.getInstance().saveTaskOfflineApi(Gson().toJson(dataModel), "${APIInterceptor.DYNAMIC_BASE_URL}${EndPoints.UPDATE_SITE_IBOARD_DATA_URL}","siteAgreementRequestAll${site_id}")
            siteAgreementModel?.postValue(Resource.success(cache_model,200))
            return
        }
        updateIBoardRepo?.updateSiteAcqData(dataModel)

    }

    fun updateUtilityEquip(data: UpdateUtilityEquipmentAllData) {
        val dataModel= UpdateUtilityEquipmentModel()
        val tempList:ArrayList<UpdateUtilityEquipmentAllData> =ArrayList()
        tempList.clear()
        tempList.add(data)
        dataModel.UtilityEquipment=tempList
        updateIBoardRepo?.updateUtilityEquipData(dataModel)
    }

    fun updatePowerFuel(data: NewPowerFuelAllData) {
        val dataModel= PowerFuelAllDataModel()
        dataModel.PowerAndFuel= arrayListOf(data)
        updateIBoardRepo?.updatePowerFuelData(dataModel)
    }
    fun updateRfSurvey(data: RfSurvey) {
        val dataModel= RfMainResponse()
        dataModel.RfSurvey= arrayListOf(data)
        updateIBoardRepo?.updateRfSurveyData(dataModel)
    }

    fun updateTwrCivilInfra(data: NewTowerCivilAllData) {
        val dataModel= TowerCivilAllDataModel()
        dataModel.TowerAndCivilInfra= arrayListOf(data)
        updateIBoardRepo?.updateTwrCivilData(dataModel)
    }

    fun updateSiteInfo(data: AllsiteInfoDataModel?) {
        updateIBoardRepo?.updateSiteInfoData(data)
    }

    fun getDepartment(data:String?) {
        homeRepo?.getDepartmentWithGeographyRequest(data)
    }

    fun getDepartmentUsers(data: GetUserList) {
        alertRepo?.getuserByWorkflow(data)
    }

    fun updateNocAndComp(data: NocCompAllData) {
        AppLogger.log("data for update======>:${Gson().toJson(data)}")
        val dataModel= UpdateNocCompModel()
        dataModel.NOCCompliance= arrayListOf(data)
        if(!Utils.isNetworkConnected()){
            val site_id = AppController.getInstance().siteid
            val value = AppPreferences.getInstance().getString("NocAndCompRequestAll${site_id}")
            var position = 0
            println("Preference id is NocAndCompRequestAll${site_id}")
            var cache_model = NocCompAllDataModel()
            if (value != null && !value.isEmpty()) {
                //Save the Service fot later updatation when online
                try {
                    cache_model = Gson().fromJson(value, NocCompAllDataModel::class.java)
                    for ((index, value) in cache_model.NOCCompliance!!.withIndex()) {
                        if (value.id == data.id) {
                            position = index
                            AppLogger.log("childIndex in update function====>:$position")
                            break
                        }
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    AppLogger.log("somthing went wrong in update noc homeviewmodel ${e.localizedMessage}")
                }
            }
            if (cache_model.NOCCompliance == null) {
                cache_model.NOCCompliance = ArrayList()
            }
            if (cache_model.NOCCompliance?.size == 0) {
                cache_model.NOCCompliance?.add(
                    NocCompAllData()
                )
            }
            if (data.ApplicationInitial != null) {
                if (cache_model.NOCCompliance?.get(position)?.ApplicationInitial==null || cache_model.NOCCompliance?.get(position)?.ApplicationInitial?.isEmpty()==true){
                    cache_model.NOCCompliance?.get(position)?.ApplicationInitial = data.ApplicationInitial
                    AppLogger.log("Save offline noc data is empty===>${Gson().toJson(cache_model.NOCCompliance?.get(position)?.ApplicationInitial)}")
                }
                else{
                    for ((index, value) in cache_model.NOCCompliance?.get(position)?.ApplicationInitial!!.withIndex()) {
                        if (value.id == data.ApplicationInitial?.get(0)?.id) {
                            AppLogger.log("SubChildIndex in update function====>:$index")
                            cache_model.NOCCompliance?.get(position)?.ApplicationInitial!![index] = data.ApplicationInitial?.get(0)!!
                            AppLogger.log("Save offline noc updated data is ===>${Gson().toJson(cache_model.NOCCompliance?.get(position)?.ApplicationInitial!![index])}")
                            break
                        }
                        else if (index==cache_model.NOCCompliance?.get(position)?.ApplicationInitial?.size?.minus(1)){
                            cache_model.NOCCompliance?.get(position)?.ApplicationInitial?.add(data.ApplicationInitial?.get(0)!!)
                        }
                    }
                }
            }
            if (data.PODetail != null) {
                if (cache_model.NOCCompliance?.get(position)?.PODetail==null || cache_model.NOCCompliance?.get(position)?.PODetail?.isEmpty()==true){
                    cache_model.NOCCompliance?.get(position)?.PODetail = data.PODetail
                }
                else{
                    for ((index, value) in cache_model.NOCCompliance?.get(position)?.PODetail!!.withIndex()) {
                        if (value.id == data.ApplicationInitial?.get(0)?.id) {
                            cache_model.NOCCompliance?.get(position)?.PODetail!![index] = data.PODetail?.get(0)!!
                            break
                        }
                        else if (index==cache_model.NOCCompliance?.get(position)?.PODetail?.size?.minus(1)){
                            cache_model.NOCCompliance?.get(position)?.PODetail?.add(data.PODetail?.get(0)!!)
                        }
                    }
                }
            }
            if (data.AuthorityFeePaymentDetail != null) {
                if (cache_model.NOCCompliance?.get(position)?.AuthorityFeePaymentDetail==null || cache_model.NOCCompliance?.get(position)?.AuthorityFeePaymentDetail?.isEmpty()==true){
                    cache_model.NOCCompliance?.get(position)?.AuthorityFeePaymentDetail = data.AuthorityFeePaymentDetail
                }
                else{
                    for ((index, value) in cache_model.NOCCompliance?.get(position)?.AuthorityFeePaymentDetail!!.withIndex()) {
                        if (value.id == data.ApplicationInitial?.get(0)?.id) {
                            cache_model.NOCCompliance?.get(position)?.AuthorityFeePaymentDetail!![index] = data.AuthorityFeePaymentDetail?.get(0)!!
                            break
                        }
                        else if (index==cache_model.NOCCompliance?.get(position)?.AuthorityFeePaymentDetail?.size?.minus(1)){
                            cache_model.NOCCompliance?.get(position)?.AuthorityFeePaymentDetail?.add(data.AuthorityFeePaymentDetail?.get(0)!!)
                        }
                    }
                }
            }
            if (data.AuthorityDetail != null) {
                cache_model.NOCCompliance?.get(position)?.AuthorityDetail = data.AuthorityDetail
            }
            val jsonStringData = Gson().toJson(cache_model)
            AppPreferences.getInstance().saveString("NocAndCompRequestAll${site_id}", jsonStringData)
            if (cache_model.NOCCompliance != null)
                dataModel.NOCCompliance= arrayListOf(cache_model.NOCCompliance?.get(position)!!)
            AppPreferences.getInstance().saveTaskOfflineApi(Gson().toJson(dataModel), "${APIInterceptor.DYNAMIC_BASE_URL}${EndPoints.UPDATE_SITE_IBOARD_DATA_URL}","NocAndCompRequestAll${site_id}")
            AppLogger.log("saved data size with id:$site_id ====>: ${cache_model.NOCCompliance?.size}")
            AppLogger.log("saved data with id:$site_id ====>: ${Gson().toJson(cache_model)}")
            NocAndCompModelResponse?.postValue(Resource.success(cache_model,200))
            return
        }
        updateIBoardRepo?.updateNocCompData(dataModel)
    }

    fun updateSstSbc(data: SstSbcAllData) {
        AppLogger.log("data for update======>:${Gson().toJson(data)}")
        val dataModel= UpdateSstSbcModel()
        dataModel.SstSbc= arrayListOf(data)
        if(!Utils.isNetworkConnected()){
            val site_id = AppController.getInstance().siteid
            val value = AppPreferences.getInstance().getString("sstSbcRequestAll${site_id}")
            var position = 0
            println("Preference id is NocAndCompRequestAll${site_id}")
            var cache_model = SstSbcAllDataModel()
            if (value != null && !value.isEmpty()) {
                //Save the Service fot later updatation when online
                try {
                    cache_model = Gson().fromJson(value, SstSbcAllDataModel::class.java)
                    for ((index, value) in cache_model.SstSbc!!.withIndex()) {
                        if (value.id == data.id) {
                            position = index
                            AppLogger.log("childIndex in update function====>:$position")
                            break
                        }
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    AppLogger.log("somthing went wrong in update noc homeviewmodel ${e.localizedMessage}")
                }
            }
            if (cache_model.SstSbc == null) {
                cache_model.SstSbc = ArrayList()
            }
            if (cache_model.SstSbc?.size == 0) {
                cache_model.SstSbc?.add(
                    SstSbcAllData()
                )
            }
            if (data.SstSbcTeam != null) {
                if (cache_model.SstSbc?.get(position)?.SstSbcTeam==null || cache_model.SstSbc?.get(position)?.SstSbcTeam?.isEmpty()==true){
                    cache_model.SstSbc?.get(position)?.SstSbcTeam = data.SstSbcTeam
                    AppLogger.log("Save offline noc data is empty===>${Gson().toJson(cache_model.SstSbc?.get(position)?.SstSbcTeam)}")
                }
                else{
                    for ((index, value) in cache_model.SstSbc?.get(position)?.SstSbcTeam!!.withIndex()) {
                        if (value.id == data.SstSbcTeam?.get(0)?.id) {
                            AppLogger.log("SubChildIndex in update function====>:$index")
                            cache_model.SstSbc?.get(position)?.SstSbcTeam!![index] = data.SstSbcTeam?.get(0)!!
                            AppLogger.log("Save offline noc updated data is ===>${Gson().toJson(cache_model.SstSbc?.get(position)?.SstSbcTeam!![index])}")
                            break
                        }
                        else if (index==cache_model.SstSbc?.get(position)?.SstSbcTeam?.size?.minus(1)){
                            cache_model.SstSbc?.get(position)?.SstSbcTeam?.add(data.SstSbcTeam?.get(0)!!)
                        }
                    }
                }
            }
            if (data.SstSbcTestReport != null) {
                if (cache_model.SstSbc?.get(position)?.SstSbcTestReport==null || cache_model.SstSbc?.get(position)?.SstSbcTestReport?.isEmpty()==true){
                    cache_model.SstSbc?.get(position)?.SstSbcTestReport = data.SstSbcTestReport
                }
                else{
                    for ((index, value) in cache_model.SstSbc?.get(position)?.SstSbcTestReport!!.withIndex()) {
                        if (value.id == data.SstSbcTestReport?.get(0)?.id) {
                            cache_model.SstSbc?.get(position)?.SstSbcTestReport!![index] = data.SstSbcTestReport?.get(0)!!
                            break
                        }
                        else if (index==cache_model.SstSbc?.get(position)?.SstSbcTestReport?.size?.minus(1)){
                            cache_model.SstSbc?.get(position)?.SstSbcTestReport?.add(data.SstSbcTestReport?.get(0)!!)
                        }
                    }
                }
            }

            val jsonStringData = Gson().toJson(cache_model)
            AppPreferences.getInstance().saveString("NocAndCompRequestAll${site_id}", jsonStringData)
            if (cache_model.SstSbc != null)
                dataModel.SstSbc= arrayListOf(cache_model.SstSbc?.get(position)!!)
            AppPreferences.getInstance().saveTaskOfflineApi(Gson().toJson(dataModel), "${APIInterceptor.DYNAMIC_BASE_URL}${EndPoints.UPDATE_SITE_IBOARD_DATA_URL}","NocAndCompRequestAll${site_id}")
            AppLogger.log("saved data size with id:$site_id ====>: ${cache_model.SstSbc?.size}")
            AppLogger.log("saved data with id:$site_id ====>: ${Gson().toJson(cache_model)}")
            sstSbcOfflineModelResponse?.postValue(Resource.success(cache_model,200))
            return
        }
        updateIBoardRepo?.updateSstSbcData(dataModel)
    }

    fun updateRfSurvey(data: RfMainResponse) {
        AppLogger.log("data for update======>:${Gson().toJson(data)}")
        if(!Utils.isNetworkConnected())
            updateIBoardRepo?.updateRfData(data)
    }

}