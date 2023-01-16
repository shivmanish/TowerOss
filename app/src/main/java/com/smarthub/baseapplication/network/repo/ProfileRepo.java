package com.smarthub.baseapplication.network.repo;

import com.google.gson.JsonObject;
import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.helpers.SingleLiveEvent;
import com.smarthub.baseapplication.model.APIError;
import com.smarthub.baseapplication.model.profile.UserProfileUpdate;
import com.smarthub.baseapplication.model.profile.viewProfile.newData.ProfileData;
import com.smarthub.baseapplication.network.APIClient;
import com.smarthub.baseapplication.model.profile.viewProfile.ProfileDetails;
import com.smarthub.baseapplication.network.ProfileUpdate;
import com.smarthub.baseapplication.utils.AppConstants;
import com.smarthub.baseapplication.utils.AppLogger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepo {

    private final APIClient apiClient;
    private static ProfileRepo sInstance;
    private static final Object LOCK = new Object();
    private SingleLiveEvent<Resource<List<ProfileData>>> profileResponse;
    private SingleLiveEvent<Resource<ProfileUpdate>> profileUpdate;

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
        profileUpdate = new SingleLiveEvent<>();
    }

    public SingleLiveEvent<Resource<List<ProfileData>>> getProfileResponse() {
        return profileResponse;
    }

    public SingleLiveEvent<Resource<ProfileUpdate>> getProfileUpdate() {
        return profileUpdate;
    }

    public void getProfileData() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("get","");
        apiClient.getProfile(jsonObject).enqueue(new Callback<List<ProfileData>>() {
            @Override
            public void onResponse(Call<List<ProfileData>> call, Response<List<ProfileData>> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else if (response!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else AppLogger.INSTANCE.log("getProfileData response is null");
            }

            @Override
            public void onFailure(Call<List<ProfileData>> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<List<ProfileData>> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
//                    Logger.getLogger("ProfileRepo").warning(response.toString());
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

    public void updateProfileData(UserProfileUpdate data) {
        AppLogger.INSTANCE.log("status","Update Profile Data in Profile Repo");
        apiClient.updateProfile(data).enqueue(new Callback<ProfileUpdate>() {
            @Override
            public void onResponse(Call<ProfileUpdate> call, Response<ProfileUpdate> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else if (response!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else AppLogger.INSTANCE.log("getProfileData response is null");
            }

            @Override
            public void onFailure(Call<ProfileUpdate> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<ProfileUpdate> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
//                    Logger.getLogger("ProfileRepo").warning(response.toString());
                    profileUpdate.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    profileUpdate.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    profileUpdate.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    profileUpdate.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

}
