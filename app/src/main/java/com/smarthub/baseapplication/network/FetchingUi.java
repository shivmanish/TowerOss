package com.smarthub.baseapplication.network;

import com.google.gson.JsonObject;
import com.smarthub.baseapplication.model.login.TokenResponse;
import com.smarthub.baseapplication.model.login.UserLoginPost;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FetchingUi {
    @POST("token/")
    Single<JsonObject> applyAIEffect(@Body UserLoginPost data);
}

