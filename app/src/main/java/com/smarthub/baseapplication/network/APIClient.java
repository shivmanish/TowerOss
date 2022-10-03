package com.smarthub.baseapplication.network;

import com.smarthub.baseapplication.model.login.UserLoginPost;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIClient {

    @POST(EndPoints.ACCESS_TOKEN)
    Call<RefreshToken> getLoginForAccessToken(@Body UserLoginPost data);

}

