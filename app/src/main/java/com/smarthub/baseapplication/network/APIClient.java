package com.smarthub.baseapplication.network;

import com.smarthub.baseapplication.model.login.UserLoginPost;
import com.smarthub.baseapplication.model.otp.GetOtpResponse;
import com.smarthub.baseapplication.model.otp.UserOTPGet;
import com.smarthub.baseapplication.model.otp.UserOTPVerify;
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

public interface APIClient {

    @POST(EndPoints.ACCESS_TOKEN)
    Call<RefreshToken> getLoginForAccessToken(@Body UserLoginPost data);

    @POST(EndPoints.GET_OTP)
    Call<RefreshToken> getLoginWithOtpToken(@Body UserOTPVerify data);

    @POST(EndPoints.GET_OTP)
    Call<GetOtpResponse> getOTP(@Body UserOTPGet data);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.PROFILE)
    Call<List<ProfileDetails>> getProfile(@Body UserProfileGet data,@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(EndPoints.PROFILE)
    Call<ProfileUpdate> updateProfile(@Body UserProfileUpdate data, @Header("Authorization") String auth);

    @POST(EndPoints.REGISTRATION)
    Call<RegstationResponse> registration(@Body RegisterData data);

    @GET(EndPoints.SITE_INFO_DROP_DOWN)
    Call<SiteInfoDropDownData> siteInfoDropDown();



}

