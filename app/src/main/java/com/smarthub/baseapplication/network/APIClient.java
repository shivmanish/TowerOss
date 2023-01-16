package com.smarthub.baseapplication.network;

import com.google.gson.JsonObject;
import com.smarthub.baseapplication.model.CommonResponse;
import com.smarthub.baseapplication.model.basicInfo.IdData;
import com.smarthub.baseapplication.model.dropdown.DropDownList;
import com.smarthub.baseapplication.model.dropdown.newData.DropDownNew;
import com.smarthub.baseapplication.model.home.HomeResponse;
import com.smarthub.baseapplication.model.notification.newData.NotificationNew;
import com.smarthub.baseapplication.model.otp.GetRegisterOtpResponse;
import com.smarthub.baseapplication.model.profile.viewProfile.ProfileDetails;
import com.smarthub.baseapplication.model.profile.viewProfile.newData.ProfileData;
import com.smarthub.baseapplication.model.project.ProjectModelData;
import com.smarthub.baseapplication.model.project.TaskModelData;
import com.smarthub.baseapplication.model.search.SearchAliasNameItem;
import com.smarthub.baseapplication.model.search.SearchListItem;
import com.smarthub.baseapplication.model.search.SearchSiteOpcoName;
import com.smarthub.baseapplication.model.search.SearchSiteIdItem;
import com.smarthub.baseapplication.model.search.SearchSiteNameItem;
import com.smarthub.baseapplication.model.search.SearchSiteOpcoSiteId;
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData;
import com.smarthub.baseapplication.model.serviceRequest.log.LogSearchData;
import com.smarthub.baseapplication.model.serviceRequest.new_site.GenerateSiteIdResponse;
import com.smarthub.baseapplication.model.siteInfo.newData.SiteInfoModelNew;
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.NocAndCompModel;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel;
import com.smarthub.baseapplication.model.login.UserLoginPost;
import com.smarthub.baseapplication.model.otp.GetOtpResponse;
import com.smarthub.baseapplication.model.otp.GetSuccessResponse;
import com.smarthub.baseapplication.model.otp.UserOTPGet;
import com.smarthub.baseapplication.model.otp.UserOTPVerify;
import com.smarthub.baseapplication.model.otp.UserPasswordGet;
import com.smarthub.baseapplication.model.profile.UserProfileUpdate;
import com.smarthub.baseapplication.model.register.RegisterData;
import com.smarthub.baseapplication.model.register.RegstationResponse;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModelUpdate;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoParam;
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignModel;
import com.smarthub.baseapplication.model.siteInfo.oprationInfo.UpdateOperationInfo;
import com.smarthub.baseapplication.model.siteInfo.powerFuel.PowerAndFuelModel;
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteAgreementModel;
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerCivilInfraModel;
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.newData.OpcoInfoNewModel;
import com.smarthub.baseapplication.model.siteInfo.service_request.ServiceRequestModel;
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.UtilitiesEquipModel;
import com.smarthub.baseapplication.model.workflow.TaskDataList;
import com.smarthub.baseapplication.network.pojo.RefreshToken;

import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData;
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList;
import com.smarthub.baseapplication.ui.alert.model.request.SendAlertModel;
import com.smarthub.baseapplication.ui.alert.model.response.SendAlertResponse;
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponse;
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel;
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.CreateSiteModel;
import com.smarthub.baseapplication.ui.dialog.siteinfo.repo.BasicInfoDialougeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @POST(EndPoints.SITE_INFO_DROP_DOWN_NEW)
    Call<DropDownNew> siteInfoDropDownNew();

    @GET(EndPoints.SITE_INFO_MODEL)
    Call<SiteInfoModel> fetchSiteInfoData();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<SiteInfoModel> fetchSiteInfoById(@Body IdData data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<ServiceRequestModel> fetchServiceRequest(@Body SiteInfoParam data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<LogSearchData> fetchLogData(@Body SiteInfoParam data);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<OpcoInfoNewModel> fetchOpcoInfoRequest(@Body SiteInfoParam data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<PlanAndDesignModel> fetchPlanDesignRequest(@Body SiteInfoParam data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<UtilitiesEquipModel> fetchUtilitiesEquipRequest(@Body SiteInfoParam data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<SiteAgreementModel> fetchSiteAgreementModelRequest(@Body SiteInfoParam data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<NocAndCompModel> fetchNocAndCompRequest(@Body SiteInfoParam data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<TowerCivilInfraModel> fetchTowerCivilInfraRequest(@Body SiteInfoParam data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<PowerAndFuelModel> fetchPowerFuelRequest(@Body SiteInfoParam data);

    @GET(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<List<SearchListItem>> searchSiteInfoData(@Query("id") String id);

    @POST(EndPoints.SITE_INFO_SEARCH_DATA_NEW)
    Call<List<SearchListItem>> searchSiteInfoDataNew(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA_URL)
    Call<List<SearchListItem>> searchSiteInfoData(@Body JsonObject data);

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
    Call<SiteInfoModelUpdate> updateSiteInfoData(@Body Object basicinfoModel);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_DATA)
    Call<SiteInfoModelNew> createSite(@Body CreateSiteModel basicinfoModel);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET(EndPoints.NOTIFICATION_DATA)
    Call<NotificationNew> getNotification();

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
    Call<UserDataResponse> getuserlist(@Body GetUserList data);


}

