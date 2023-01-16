package com.smarthub.baseapplication.ui.alert;

import com.google.gson.JsonObject;
import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.helpers.SingleLiveEvent;
import com.smarthub.baseapplication.model.APIError;
import com.smarthub.baseapplication.model.search.SearchList;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel;
import com.smarthub.baseapplication.network.APIClient;
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData;
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList;
import com.smarthub.baseapplication.ui.alert.model.request.SendAlertModel;
import com.smarthub.baseapplication.ui.alert.model.response.SendAlertResponse;
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponse;
import com.smarthub.baseapplication.utils.AppConstants;
import com.smarthub.baseapplication.utils.AppLogger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlertRepo {

    private final APIClient apiClient;
    private static AlertRepo sInstance;
    private static final Object LOCK = new Object();
    private SingleLiveEvent<Resource<SendAlertResponse>> sendAlertResponseLivedata;
    private SingleLiveEvent<Resource<UserDataResponse>> userDataResponseLiveData;

    public static AlertRepo getInstance(APIClient apiClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AlertRepo(apiClient);
            }
        }
        return sInstance;
    }

    public AlertRepo(APIClient apiClient) {
        this.apiClient = apiClient;
        sendAlertResponseLivedata = new SingleLiveEvent<>();
        userDataResponseLiveData = new SingleLiveEvent<>();
    }

    public SingleLiveEvent<Resource<SendAlertResponse>> getAlertResponseLiveData() {
        return sendAlertResponseLivedata;
    }

    public SingleLiveEvent<Resource<UserDataResponse>> getUserDataReponseLiveData() {
        return userDataResponseLiveData;
    }





    public void sendAlert(SendAlertModel model) {

        apiClient.sendAlert(model).enqueue(new Callback<SendAlertResponse>() {
            @Override
            public void onResponse(Call<SendAlertResponse> call, Response<SendAlertResponse> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<SendAlertResponse> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SendAlertResponse> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
                    sendAlertResponseLivedata.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    sendAlertResponseLivedata.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    sendAlertResponseLivedata.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    sendAlertResponseLivedata.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void getuser(GetUserList model) {

        apiClient.getuserlist(model).enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<UserDataResponse> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
                    userDataResponseLiveData.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    userDataResponseLiveData.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    userDataResponseLiveData.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    userDataResponseLiveData.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }


}
