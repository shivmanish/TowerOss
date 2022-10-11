package com.smarthub.baseapplication.network.repo;

import android.util.Log;

import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.helpers.SingleLiveEvent;
import com.smarthub.baseapplication.model.APIError;
import com.smarthub.baseapplication.model.ErrorUtils;
import com.smarthub.baseapplication.model.login.UserLoginPost;
import com.smarthub.baseapplication.model.profile.UserProfileGet;
import com.smarthub.baseapplication.network.APIClient;
import com.smarthub.baseapplication.network.ProfileDetails;
import com.smarthub.baseapplication.network.RefreshToken;
import com.smarthub.baseapplication.utils.AppConstants;

import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepo {

    private final APIClient apiClient;
    private static ProfileRepo sInstance;
    private static final Object LOCK = new Object();
    private SingleLiveEvent<Resource<ProfileDetails>> profileResponse;

    public static ProfileRepo getInstance(APIClient apiClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new ProfileRepo(apiClient);
            }
        }
        return sInstance;
    }

    public ProfileRepo(APIClient apiClient) {
        this.apiClient = apiClient;
        profileResponse = new SingleLiveEvent<>();
    }

    public SingleLiveEvent<Resource<ProfileDetails>> getProfileResponse() {
        return profileResponse;
    }

    public void getProfileData(UserProfileGet data) {
        apiClient.getProfile(data).enqueue(new Callback<ProfileDetails>() {
            @Override
            public void onResponse(Call<ProfileDetails> call, Response<ProfileDetails> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else {
                    APIError error = ErrorUtils.parseError(response);
                    reportErrorResponse(error, null);
                }
            }

            @Override
            public void onFailure(Call<ProfileDetails> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<ProfileDetails> response) {

                if (response.body() != null) {
                    Logger.getLogger("ProfileRepo").warning(response.toString());
                    profileResponse.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    profileResponse.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    profileResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    profileResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

}
