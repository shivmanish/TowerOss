package com.smarthub.baseapplication.network;

import com.smarthub.baseapplication.model.basicInfo.IdData;
import com.smarthub.baseapplication.model.search.SearchList;
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
import com.smarthub.baseapplication.network.pojo.RefreshToken;
import com.smarthub.baseapplication.model.profile.UserProfileGet;
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
    Call<List<ProfileDetails>> getProfile(@Body UserProfileGet data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.PROFILE)
    Call<ProfileUpdate> updateProfile(@Body UserProfileUpdate data);

    @POST(EndPoints.REGISTRATION)
    Call<RegstationResponse> registration(@Body RegisterData data);

//    Site Info Drop Down api integration
    @GET(EndPoints.SITE_INFO_DROP_DOWN)
    Call<SiteInfoDropDownData> siteInfoDropDown();

    @GET(EndPoints.SITE_INFO_MODEL)
    Call<SiteInfoModel> fetchSiteInfoData();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<SiteInfoModel> fetchSiteInfoById(@Body IdData data);

    @GET(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<SearchList> searchSiteInfoData(@Query("id") String id);

    @GET(EndPoints.SITE_INFO_SEARCH_DATA)
    Call<SearchList> searchSiteInfoData(
            @Query("id") String id,
            @Query("category") String category
    );

}

