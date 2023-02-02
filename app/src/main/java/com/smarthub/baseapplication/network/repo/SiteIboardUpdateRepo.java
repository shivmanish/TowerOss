package com.smarthub.baseapplication.network.repo;

import androidx.annotation.NonNull;

import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.helpers.SingleLiveEvent;
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency.OpcoInfoUpdateResponse;
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency.UpdateOpcoTenencyModel;
import com.smarthub.baseapplication.network.APIClient;
import com.smarthub.baseapplication.utils.AppConstants;
import com.smarthub.baseapplication.utils.AppLogger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiteIboardUpdateRepo {

    private final APIClient apiClient;
    private static SiteIboardUpdateRepo sInstance;
    private static final Object LOCK = new Object();

    private SingleLiveEvent<Resource<OpcoInfoUpdateResponse>> opcoTenencyUpdateResponse;


    public static SiteIboardUpdateRepo getInstance(APIClient apiClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new SiteIboardUpdateRepo(apiClient);
            }
        }
        return sInstance;
    }



    public SingleLiveEvent<Resource<OpcoInfoUpdateResponse>> getOpcoTenencyUpdateResponse() {
        return opcoTenencyUpdateResponse;
    }

    public SiteIboardUpdateRepo(APIClient apiClient) {
        this.apiClient = apiClient;
        opcoTenencyUpdateResponse = new SingleLiveEvent<>();

    }






    public void updateOpcoTeency(UpdateOpcoTenencyModel opcoTenencyModel) {
        AppLogger.INSTANCE.log("opcoInfo data for update on repo:"+ opcoTenencyModel);
        OpcoInfoUpdateResponse d = (opcoTenencyUpdateResponse.getValue()!=null) ? opcoTenencyUpdateResponse.getValue().data: null;
        opcoTenencyUpdateResponse.postValue(Resource.loading(d, 200));
        apiClient.updateOpcoTenency(opcoTenencyModel).enqueue(new Callback<OpcoInfoUpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<OpcoInfoUpdateResponse> call, Response<OpcoInfoUpdateResponse> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<OpcoInfoUpdateResponse> call, @NonNull Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<OpcoInfoUpdateResponse> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse on opco Tennecy update:" + response.toString());
                    opcoTenencyUpdateResponse.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    opcoTenencyUpdateResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    opcoTenencyUpdateResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

}
