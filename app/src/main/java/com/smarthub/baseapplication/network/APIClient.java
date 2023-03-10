package com.smarthub.baseapplication.network;

import com.google.gson.JsonObject;
import com.smarthub.baseapplication.model.CommonResponse;
import com.smarthub.baseapplication.model.basicInfo.IdData;
import com.smarthub.baseapplication.model.dropdown.DropDownList;
import com.smarthub.baseapplication.model.dropdown.newData.DropDownNew;
import com.smarthub.baseapplication.model.home.HomeResponse;
import com.smarthub.baseapplication.model.login.UserLoginPost;
import com.smarthub.baseapplication.model.logs.LogsDataModel;
import com.smarthub.baseapplication.model.logs.PostLogData;
import com.smarthub.baseapplication.model.notification.newData.AddNotificationModel;
import com.smarthub.baseapplication.model.notification.newData.AddNotificationResponse;
import com.smarthub.baseapplication.model.notification.newData.NotificationNew;
import com.smarthub.baseapplication.model.otp.GetOtpResponse;
import com.smarthub.baseapplication.model.otp.GetRegisterOtpResponse;
import com.smarthub.baseapplication.model.otp.GetSuccessResponse;
import com.smarthub.baseapplication.model.otp.UserOTPGet;
import com.smarthub.baseapplication.model.otp.UserOTPVerify;
import com.smarthub.baseapplication.model.otp.UserPasswordGet;
import com.smarthub.baseapplication.model.profile.UserProfileUpdate;
import com.smarthub.baseapplication.model.profile.viewProfile.newData.ProfileData;
import com.smarthub.baseapplication.model.project.ProjectModelData;
import com.smarthub.baseapplication.model.project.TaskModelData;
import com.smarthub.baseapplication.model.qatcheck.QalLaunchModel;
import com.smarthub.baseapplication.model.qatcheck.punch_point.QatPunchPointModel;
import com.smarthub.baseapplication.model.register.RegisterData;
import com.smarthub.baseapplication.model.register.RegstationResponse;
import com.smarthub.baseapplication.model.register.dropdown.DepartmentDropdown;
import com.smarthub.baseapplication.model.register.dropdown.DropdownParam;
import com.smarthub.baseapplication.model.search.SearchAliasNameItem;
import com.smarthub.baseapplication.model.search.SearchListItem;
import com.smarthub.baseapplication.model.search.SearchSiteIdItem;
import com.smarthub.baseapplication.model.search.SearchSiteNameItem;
import com.smarthub.baseapplication.model.search.SearchSiteOpcoName;
import com.smarthub.baseapplication.model.search.SearchSiteOpcoSiteId;
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData;
import com.smarthub.baseapplication.model.serviceRequest.acquisitionSurvey.AcquisitionSurveyAllDataItem;
import com.smarthub.baseapplication.model.serviceRequest.new_site.GenerateSiteIdResponse;
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllDataModel;
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllDataModel;
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelAllDataModel;
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SiteAcquisitionAllDataModel;
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.AllsiteInfoDataModel;
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TowerCivilAllDataModel;
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAllDataModel;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModelUpdate;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoParam;
import com.smarthub.baseapplication.model.siteInfo.newData.SiteInfoModelNew;
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency.OpcoInfoUpdateResponse;
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency.UpdateOpcoTenencyModel;
import com.smarthub.baseapplication.model.siteInfo.oprationInfo.UpdateOperationInfo;
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignModel;
import com.smarthub.baseapplication.model.siteInfo.qat.QatModel;
import com.smarthub.baseapplication.model.siteInfo.qat.SaveCheckpointModel;
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.QatMainModel;
import com.smarthub.baseapplication.model.siteInfo.service_request.ServiceRequestModel;
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteacquisitionAgreement;
import com.smarthub.baseapplication.model.taskModel.CreateNewTaskModel;
import com.smarthub.baseapplication.model.taskModel.CreateNewTaskResponse;
import com.smarthub.baseapplication.model.taskModel.GeoGraphyLevelData;
import com.smarthub.baseapplication.model.taskModel.GeoGraphyLevelPostData;
import com.smarthub.baseapplication.model.taskModel.GetTaskInfoPostData;
import com.smarthub.baseapplication.model.taskModel.TaskAssignModel;
import com.smarthub.baseapplication.model.taskModel.TaskInfo;
import com.smarthub.baseapplication.model.taskModel.dropdown.GetTaskDataModel;
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel;
import com.smarthub.baseapplication.model.taskModel.dropdown.UpdateTaskDataModel;
import com.smarthub.baseapplication.model.workflow.TaskDataList;
import com.smarthub.baseapplication.network.pojo.RefreshToken;
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData;
import com.smarthub.baseapplication.ui.alert.model.UpdateAlertData;
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList;
import com.smarthub.baseapplication.ui.alert.model.request.SendAlertModel;
import com.smarthub.baseapplication.ui.alert.model.response.SendAlertResponse;
import com.smarthub.baseapplication.ui.alert.model.response.SendAlertResponseNew;
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponse;
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel;
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.CreateSiteModel;
import com.smarthub.baseapplication.ui.dialog.siteinfo.repo.BasicInfoDialougeResponse;
import com.smarthub.baseapplication.ui.mapui.pojo.MapMarkerService;
import com.smarthub.baseapplication.ui.mapui.pojo.MarkerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIClient {

    @POST(EndPoints.ACCESS_TOKEN)
    Call<RefreshToken> getLoginForAccessToken(@Body UserLoginPost data);

    @POST(EndPoints.GET_OTP)
    Call<RefreshToken> getLoginWithOtpToken(@Body UserOTPVerify data);

    @POST(EndPoints.GET_OTP)
    Call<GetOtpResponse> getOTP(@Body UserOTPGet data);

    @POST(EndPoints.PASSWORD_RESET)
    Call<GetSuccessResponse> changePass(@Body UserPasswordGet data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.PROFILE)
    Call<List<ProfileData>> getProfile(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.PROFILE)
    Call<ProfileUpdate> updateProfile(@Body UserProfileUpdate data);

    @POST(EndPoints.REGISTRATION)
    Call<RegstationResponse> registration(@Body RegisterData data);

    @POST(EndPoints.REGISTRATION)
    Call<DropDownList> companyDropDown(@Body JsonObject data);

    @POST(EndPoints.REGISTRATION)
    Call<CommonResponse> commonResponse(@Body JsonObject data);

    @POST(EndPoints.REGISTRATION)
    Call<GetRegisterOtpResponse> commonRegisterOTPResponse(@Body JsonObject data);

//    Site Info Drop Down api integration
    @GET(EndPoints.SITE_INFO_DROP_DOWN)
    Call<SiteInfoDropDownData> siteInfoDropDown();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.MONGO_TASK_API_GET)
    Call<GetTaskDataModel> dynamicTaskUiModel(@Body JsonObject data);

    @GET(EndPoints.MONGO_TASK_API_UPDATE)
    Call<UpdateTaskDataModel> dynamicTaskUiModelUpdate(@Body TaskDropDownModel data);

    @POST(EndPoints.SITE_INFO_DROP_DOWN_NEW)
    Call<DropDownNew> siteInfoDropDownNew();

    @GET(EndPoints.SITE_INFO_MODEL)
    Call<SiteInfoModel> fetchSiteInfoData();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<SiteInfoModel> fetchSiteInfoById(@Body IdData data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_IBOARD_DATA_URL)
    Call<AllsiteInfoDataModel> fetchSiteInfo(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_IBOARD_DATA_URL)
    Call<ServiceRequestModel> fetchServiceRequest(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_IBOARD_DATA_URL)
    Call<AcquisitionSurveyAllDataItem> fetchAcquisitionSurveyAllDataItemRequest(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<LogsDataModel> fetchLogData(@Body SiteInfoParam data);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_IBOARD_DATA_URL)
    Call<OpcoTenencyAllDataModel> fetchOpcoInfoRequest(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<PlanAndDesignModel> fetchPlanDesignRequest(@Body SiteInfoParam data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<QatModel> fetchQatRequest(@Body SiteInfoParam data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<QatMainModel> fetchQatMainRequest(@Body SiteInfoParam data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_DATA)
    Call<QatMainModel> fetchQatMainRequest(@Body QalLaunchModel data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_DATA)
    Call<QatMainModel> fetchQatMainRequest(@Body QatPunchPointModel data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_IBOARD_DATA_URL)
    Call<UtilityEquipmentAllDataModel> fetchUtilitiesEquipRequest(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_IBOARD_DATA_URL)
    Call<SiteAcquisitionAllDataModel> fetchSiteAgreementModelRequest(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_IBOARD_DATA_URL)
    Call<NocCompAllDataModel> fetchNocAndCompRequest(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_IBOARD_DATA_URL)
    Call<TowerCivilAllDataModel> fetchTowerCivilInfraRequest(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_IBOARD_DATA_URL)
    Call<PowerFuelAllDataModel> fetchPowerFuelRequest(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA_URL)
    Call<List<SearchListItem>> searchSiteByName(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA_URL)
    Call<List<SearchSiteIdItem>> searchSiteBySiteId(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA_URL)
    Call<List<SearchSiteNameItem>> searchSiteBySiteName(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA_URL)
    Call<List<SearchAliasNameItem>> searchSiteByAliasName(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA_URL)
    Call<List<SearchSiteOpcoName>> searchSiteByOpcoName(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA_URL)
    Call<List<SearchSiteOpcoSiteId>> searchSiteByOpcoSiteId(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SERVICE_REQUEST_DATA_URL)
    Call<BasicInfoDialougeResponse> updateBasicInfo(@Body BasicinfoModel basicinfoModel);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_DATA)
    Call<SiteInfoModelUpdate> updateOperationalInfo(@Body UpdateOperationInfo basicinfoModel);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SERVICE_REQUEST_GENERATE_ITEM)
    Call<GenerateSiteIdResponse> generateSiteId(@Body GenerateSiteIdResponse basicinfoModel);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_DATA)
    Call<BasicInfoDialougeResponse> updateSiteInfo(@Body BasicinfoModel basicinfoModel);
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_DATA)
    Call<SiteacquisitionAgreement> updateAgreementSiteInfo(@Body SiteacquisitionAgreement siteacquisitionAgreement);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_DATA)
    Call<SiteInfoModelNew> createSite(@Body CreateSiteModel basicinfoModel);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.NOTIFICATION_DATA)
    Call<NotificationNew> getNotification(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.DASHBOARD_DATA_URL)
    Call<HomeResponse> fetchHomeData(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.PROJECTS_DATA_URL)
    Call<ProjectModelData> fetchProjectData(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.PROJECTS_DATA_URL)
    Call<TaskModelData> fetchTaskData(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SERVICE_REQUEST_DATA_URL)
    Call<ServiceRequestAllData> fetchsServiceRequestData(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.WORKFLOW_DATA_URL)
    Call<TaskDataList> getTaskDataById(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SERVICE_REQUEST_SEND_ALERT)
    Call<SendAlertResponse> sendAlert(@Body SendAlertModel data);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SERVICE_REQUEST_SEND_ALERT)
    Call<SendAlertResponseNew> sendAlertNew(@Body SendAlertModel data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SERVICE_REQUEST_SEND_ALERT)
    Call<SendAlertResponse> sendAlert(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SERVICE_REQUEST_SEND_ALERT)
    Call<SendAlertResponse> updateAlert(@Body UpdateAlertData data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SERVICE_REQUEST_SEND_ALERT)
    Call<UserDataResponse> getuserlist(@Body GetUserList data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA_NEW)
    Call<MarkerResponse> getmaplatlong(@Body MapMarkerService data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.DEPARTMENT_DROPDOWNS)
    Call<DepartmentDropdown> getDepartmentList(@Body DropdownParam data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.WORKFLOW_DATA_URL)
    Call<CreateNewTaskResponse> createNewTask(@Body CreateNewTaskModel data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA_URL)
    Call<GeoGraphyLevelData> getGeoGraphyLevel(@Body GeoGraphyLevelPostData data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.WORKFLOW_DATA_URL)
    Call<CreateNewTaskResponse> AssignTask(@Body TaskAssignModel data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.WORKFLOW_DATA_URL)
    Call<TaskInfo> getTaskInfo(@Body GetTaskInfoPostData data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_DATA)
    Call<OpcoInfoUpdateResponse> updateOpcoTenency(@Body UpdateOpcoTenencyModel data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.PROFILE)
    Call<UserDataResponse> getUsersData(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.NOTIFICATION_DATA)
    Call<AddNotificationResponse> addNotification(@Body AddNotificationModel data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_DATA)
    Call<LogsDataModel> postLogData(@Body PostLogData data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_DATA)
    Call<QatMainModel> saveQatPunchPointRequest(@Body SaveCheckpointModel data);
}

