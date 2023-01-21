package com.smarthub.baseapplication.ui.mapui.viewmodel;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;
import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.helpers.SingleLiveEvent;
import com.smarthub.baseapplication.model.APIError;
import com.smarthub.baseapplication.network.APIClient;
import com.smarthub.baseapplication.ui.mapui.pojo.MapMarkerService;
import com.smarthub.baseapplication.ui.mapui.pojo.MarkerResponse;
import com.smarthub.baseapplication.utils.AppConstants;
import com.smarthub.baseapplication.utils.AppLogger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapRepo {

    private final APIClient apiClient;
    private static MapRepo sInstance;
    private static final Object LOCK = new Object();
    private SingleLiveEvent<Resource<MarkerResponse>> mapmarketLivedata;


    public static MapRepo getInstance(APIClient apiClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new MapRepo(apiClient);
            }
        }
        return sInstance;
    }

    public MapRepo(APIClient apiClient) {
        this.apiClient = apiClient;
        mapmarketLivedata = new SingleLiveEvent<>();

    }

    public SingleLiveEvent<Resource<MarkerResponse>> getMarker() {
        return mapmarketLivedata;
    }


    public void getmarkedata(MapMarkerService mapMarkerService) {
        apiClient.getmaplatlong(mapMarkerService).enqueue(new Callback<MarkerResponse>() {
            @Override
            public void onResponse(@NonNull Call<MarkerResponse> call, Response<MarkerResponse> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<MarkerResponse> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<MarkerResponse> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.toString());
                    mapmarketLivedata.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    mapmarketLivedata.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    mapmarketLivedata.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    mapmarketLivedata.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }


}
