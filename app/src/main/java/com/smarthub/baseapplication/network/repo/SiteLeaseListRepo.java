package com.smarthub.baseapplication.network.repo;

import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.helpers.SingleLiveEvent;
import com.smarthub.baseapplication.model.APIError;
import com.smarthub.baseapplication.network.APIClient;
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData;
import com.smarthub.baseapplication.utils.AppConstants;
import com.smarthub.baseapplication.utils.AppLogger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiteLeaseListRepo {
    private final APIClient apiClient;
    private static SiteLeaseListRepo sInstance;
    private static final Object LOCK = new Object();
    private SingleLiveEvent<Resource<SiteInfoDropDownData>> dropDownResoonse;
    public static SiteLeaseListRepo getInstance(APIClient apiClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new SiteLeaseListRepo(apiClient);
            }
        }
        return sInstance;
    }

    public SiteLeaseListRepo(APIClient apiClient) {
        this.apiClient = apiClient;
        dropDownResoonse = new SingleLiveEvent<>();
    }

    public SingleLiveEvent<Resource<SiteInfoDropDownData>> getDropDownResoonse() {
        return dropDownResoonse;
    }

    public void siteInfoDropDown() {

        apiClient.siteInfoDropDown().enqueue(new Callback<SiteInfoDropDownData>() {
            @Override
            public void onResponse(Call<SiteInfoDropDownData> call, Response<SiteInfoDropDownData> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else if (response!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else AppLogger.INSTANCE.log("getProfileData response is null");
            }

            @Override
            public void onFailure(Call<SiteInfoDropDownData> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SiteInfoDropDownData> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
//                    Logger.getLogger("ProfileRepo").warning(response.toString());
                    dropDownResoonse.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    dropDownResoonse.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    dropDownResoonse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    dropDownResoonse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

}
