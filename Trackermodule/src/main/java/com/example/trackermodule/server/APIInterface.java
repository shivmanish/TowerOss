package com.example.trackermodule.server;

import com.example.patrollerapp.db.PendingLatlong;
import com.example.patrollerapp.homepage.pojo.UploadLatLong;
import com.example.patrollerapp.homepage.pojo.UserDetailsService;
import com.example.patrollerapp.homepage.pojo.response.UserDataResponse;
import com.example.patrollerapp.login.pojo.LoginWithPasscode;
import com.example.patrollerapp.login.pojo.NormalResponse;
import com.example.patrollerapp.login.pojo.OptSendService;
import com.example.patrollerapp.login.pojo.OtpVerifyService;
import com.example.patrollerapp.login.pojo.SetPinService;
import com.example.trackermodule.homepage.pojo.UpDateLatlongRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/maps/api/directions/json")
    public Call<DirectionResults> getJson(@Query("origin") String origin, @Query("destination") String destination,@Query("key") String key);


    @POST("/Userdetails/")
    public Call<NormalResponse> loginWithPassword(@Body LoginWithPasscode loginWithPasscode);


    @POST("/Otpsend/")
    public Call<NormalResponse> sendOtp(@Body OptSendService optSendService);

    @POST("/Otpsend/")
    public Call<NormalResponse> verifyOtp(@Body OtpVerifyService optSendService);

    @POST("/Userdetails/")
    public Call<NormalResponse> setPin(@Body SetPinService optSendService);


    @POST("/Userdetails/")
    public Call<UserDataResponse> getUserDetails(@Body UserDetailsService userDetailsService);


    @POST("/Patrollerlatlngall/")
    public Call<UserDataResponse> updatePendingLatlong(@Body PendingLatlong pendingLatlong);

    @POST("/toweross/workflow/")
    public Call<UserDataResponse> updateLatlong(@Body UpDateLatlongRequest userDetailsService,@Header("authorization") String token);


}