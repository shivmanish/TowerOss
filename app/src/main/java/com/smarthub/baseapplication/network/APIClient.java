package com.smarthub.baseapplication.network;

import com.smarthub.baseapplication.model.login.UserLoginPost;
import com.smarthub.baseapplication.network.pojo.RefreshToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIClient {

    @POST(EndPoints.ACCESS_TOKEN)
    Call<RefreshToken> getLoginForAccessToken(@Body UserLoginPost data);


    @POST(EndPoints.ACCESS_TOKEN)
    Call<RefreshToken> getRegister(@Body UserLoginPost data);

}

