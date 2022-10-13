package com.smarthub.baseapplication.network;

import com.smarthub.baseapplication.model.login.UserLoginPost;
import com.smarthub.baseapplication.network.pojo.RefreshToken;
import com.smarthub.baseapplication.model.profile.UserProfileGet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIClient {

    @POST(EndPoints.ACCESS_TOKEN)
    Call<RefreshToken> getLoginForAccessToken(@Body UserLoginPost data);


    @POST(EndPoints.ACCESS_TOKEN)
    Call<RefreshToken> getRegister(@Body UserLoginPost data);

    @POST(EndPoints.PROFILE)
    Call<List<ProfileDetails>> getProfile(@Body UserProfileGet data);

    @POST(EndPoints.PROFILE)
    Call<RefreshToken> updateProfile(@Body UserProfileGet data);




}

