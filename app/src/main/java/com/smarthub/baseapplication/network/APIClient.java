package com.smarthub.baseapplication.network;

import com.google.gson.JsonObject;
import com.smarthub.baseapplication.model.CommonResponse;
import com.smarthub.baseapplication.model.basicInfo.IdData;
import com.smarthub.baseapplication.model.dropdown.DropDownItem;
import com.smarthub.baseapplication.model.dropdown.DropDownList;
import com.smarthub.baseapplication.model.home.HomeResponse;
import com.smarthub.baseapplication.model.opco_tenancy.OpcoCardList;
import com.smarthub.baseapplication.model.otp.GetRegisterOtpResponse;
import com.smarthub.baseapplication.model.project.ProjectModelData;
import com.smarthub.baseapplication.model.project.TaskModelData;
import com.smarthub.baseapplication.model.search.SearchList;
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData;
import com.smarthub.baseapplication.model.serviceRequest.new_site.GenerateSiteIdResponse;
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
import com.smarthub.baseapplication.model.siteInfo.SiteInfoParam;
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.newData.OpcoInfoNewModel;
import com.smarthub.baseapplication.model.siteInfo.service_request.ServiceRequestModel;
import com.smarthub.baseapplication.model.workflow.TaskDataList;
import com.smarthub.baseapplication.network.pojo.RefreshToken;

import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData;
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel;
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.CreateSiteModel;
import com.smarthub.baseapplication.ui.dialog.siteinfo.repo.BasicInfoDialougeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
    Call<List<ProfileDetails>> getProfile(@Body JsonObject data);

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

    @GET(EndPoints.SITE_INFO_MODEL)
    Call<SiteInfoModel> fetchSiteInfoData();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<SiteInfoModel> fetchSiteInfoById(@Body IdData data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<ServiceRequestModel> fetchSiteInfoRequest(@Body SiteInfoParam data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<OpcoInfoNewModel> fetchOpcoInfoRequest(@Body SiteInfoParam data);

    @GET(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<SearchList> searchSiteInfoData(@Query("id") String id);

    @POST(EndPoints.SITE_INFO_SEARCH_DATA_NEW)
    Call<SearchList> searchSiteInfoDataNew(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA_URL)
    Call<SearchList> searchSiteInfoData(@Body JsonObject data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SERVICE_REQUEST_DATA_URL)
    Call<BasicInfoDialougeResponse> updateBasicInfo(@Body BasicinfoModel basicinfoModel);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SERVICE_REQUEST_GENERATE_ITEM)
    Call<GenerateSiteIdResponse> generateSiteId(@Body GenerateSiteIdResponse basicinfoModel);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_DATA)
    Call<BasicInfoDialougeResponse> updateSiteInfo(@Body BasicinfoModel basicinfoModel);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_DATA)
    Call<BasicInfoDialougeResponse> createSite(@Body CreateSiteModel basicinfoModel);


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


}

