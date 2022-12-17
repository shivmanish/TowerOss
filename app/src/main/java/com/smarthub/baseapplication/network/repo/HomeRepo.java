package com.smarthub.baseapplication.network.repo;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;
import com.smarthub.baseapplication.helpers.AppPreferences;
import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.helpers.SingleLiveEvent;
import com.smarthub.baseapplication.model.APIError;
import com.smarthub.baseapplication.model.home.HomeResponse;
import com.smarthub.baseapplication.model.project.ProjectModelData;
import com.smarthub.baseapplication.model.project.TaskModelData;
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData;
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem;
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
    private SingleLiveEvent<Resource<ProjectModelData>> projectResponse;
    private SingleLiveEvent<Resource<TaskModelData>> taskResponse;
    private SingleLiveEvent<Resource<ServiceRequestAllData>> serRequestData;

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
        projectResponse = new SingleLiveEvent<>();
        taskResponse = new SingleLiveEvent<>();
    }

    public SingleLiveEvent<Resource<HomeResponse>> getHomeResponse() {
        return homeResponse;
    }

    public SingleLiveEvent<Resource<ProjectModelData>> getProjectResponse() {
        return projectResponse;
    }

    public SingleLiveEvent<Resource<TaskModelData>> getTaskResponse() {
        return taskResponse;
    }
   public SingleLiveEvent<Resource<ServiceRequestAllData>> getServiceRequest() {
        return serRequestData;
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

    public void fetchProjectData() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Gettemplate","all");
        apiClient.fetchProjectData(jsonObject).enqueue(new Callback<ProjectModelData>() {
            @Override
            public void onResponse(Call<ProjectModelData> call, Response<ProjectModelData> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<ProjectModelData> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<ProjectModelData> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
                    projectResponse.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    projectResponse.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    projectResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    projectResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void fetchTaskData(String templateName) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Gettemplate",templateName);
        apiClient.fetchTaskData(jsonObject).enqueue(new Callback<TaskModelData>() {
            @Override
            public void onResponse(@NonNull Call<TaskModelData> call, @NonNull Response<TaskModelData> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TaskModelData> call, @NonNull Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<TaskModelData> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
                    taskResponse.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    taskResponse.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    taskResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    taskResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void fetchServiceRequestData(String templateName) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Gettemplate",templateName);
        apiClient.fetchsServiceRequestData(jsonObject).enqueue(new Callback<ServiceRequestAllData>() {
            @Override
            public void onResponse(@NonNull Call<ServiceRequestAllData> call, @NonNull Response<ServiceRequestAllData> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServiceRequestAllData> call, @NonNull Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<ServiceRequestAllData> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
                    serRequestData.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    serRequestData.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    serRequestData.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    serRequestData.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

}
