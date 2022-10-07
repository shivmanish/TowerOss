package com.smarthub.baseapplication.network.repo;

import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.helpers.SingleLiveEvent;
import com.smarthub.baseapplication.model.APIError;
import com.smarthub.baseapplication.model.ErrorUtils;
import com.smarthub.baseapplication.model.login.UserLoginPost;
import com.smarthub.baseapplication.network.APIClient;
import com.smarthub.baseapplication.network.RefreshToken;
import com.smarthub.baseapplication.utils.AppConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepo {

    private final APIClient apiClient;
    private static LoginRepo sInstance;
    private static final Object LOCK = new Object();
    private SingleLiveEvent<Resource<RefreshToken>> logingResponse;

    public static LoginRepo getInstance(APIClient apiClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new LoginRepo(apiClient);
            }
        }
        return sInstance;
    }

    public LoginRepo(APIClient apiClient) {
        this.apiClient = apiClient;
        logingResponse = new SingleLiveEvent<>();
    }

    public SingleLiveEvent<Resource<RefreshToken>> getLoginResponse() {
        return logingResponse;
    }

    public void getLoginToken(UserLoginPost data) {
        apiClient.getLoginForAccessToken(data).enqueue(new Callback<RefreshToken>() {
            @Override
            public void onResponse(Call<RefreshToken> call, Response<RefreshToken> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else {
                    APIError error = ErrorUtils.parseError(response);
                    reportErrorResponse(error, null);
                }
            }

            @Override
            public void onFailure(Call<RefreshToken> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<RefreshToken> response) {

                if (response.body() != null) {
                    logingResponse.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    logingResponse.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    logingResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    logingResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

}
