package com.smarthub.baseapplication.network.repo;

import com.google.gson.JsonObject;
import com.smarthub.baseapplication.helpers.AppPreferences;
import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.helpers.SingleLiveEvent;
import com.smarthub.baseapplication.model.APIError;
import com.smarthub.baseapplication.model.home.HomeResponse;
import com.smarthub.baseapplication.network.APIClient;
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData;
import com.smarthub.baseapplication.utils.AppConstants;
import com.smarthub.baseapplication.utils.AppLogger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepo {

    private final APIClient apiClient;
    private static HomeRepo sInstance;
    private static final Object LOCK = new Object();
    private SingleLiveEvent<Resource<HomeResponse>> homeResponse;

    public static HomeRepo getInstance(APIClient apiClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new HomeRepo(apiClient);
            }
        }
        return sInstance;
    }

    public HomeRepo(APIClient apiClient) {
        this.apiClient = apiClient;
        homeResponse = new SingleLiveEvent<>();
    }

    public SingleLiveEvent<Resource<HomeResponse>> getHomeResponse() {
        return homeResponse;
    }

    public void fetchHomeData() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("homepage","");
        apiClient.fetchHomeData(jsonObject).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<HomeResponse> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
                    homeResponse.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    homeResponse.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    homeResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    homeResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

}
