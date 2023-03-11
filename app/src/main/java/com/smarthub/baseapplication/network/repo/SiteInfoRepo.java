package com.smarthub.baseapplication.network.repo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.smarthub.baseapplication.helpers.AppPreferences;
import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.helpers.SingleLiveEvent;
import com.smarthub.baseapplication.model.APIError;
import com.smarthub.baseapplication.model.search.SearchList;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel;
import com.smarthub.baseapplication.model.taskModel.dropdown.GetTaskDataModel;
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel;
import com.smarthub.baseapplication.model.taskModel.dropdown.UpdateTaskDataModel;
import com.smarthub.baseapplication.network.APIClient;
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData;
import com.smarthub.baseapplication.utils.AppConstants;
import com.smarthub.baseapplication.utils.AppController;
import com.smarthub.baseapplication.utils.AppLogger;
import com.smarthub.baseapplication.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiteInfoRepo {

    private final APIClient apiClient;
    private static SiteInfoRepo sInstance;
    private static final Object LOCK = new Object();
    private SingleLiveEvent<Resource<SiteInfoDropDownData>> dropDownResoonse;
    private SingleLiveEvent<Resource<GetTaskDataModel>> taskUiModelResoonse;
    private SingleLiveEvent<Resource<UpdateTaskDataModel>> updateTaskUiModelResoonse;
    private SingleLiveEvent<Resource<SiteInfoModel>> siteIndoResponse;
    private SingleLiveEvent<Resource<SearchList>> siteSearchResponse;

    public static SiteInfoRepo getInstance(APIClient apiClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new SiteInfoRepo(apiClient);
            }
        }
        return sInstance;
    }

    public SiteInfoRepo(APIClient apiClient) {
        this.apiClient = apiClient;
        dropDownResoonse = new SingleLiveEvent<>();
        siteIndoResponse = new SingleLiveEvent<>();
        siteSearchResponse = new SingleLiveEvent<>();
        taskUiModelResoonse = new SingleLiveEvent<>();
        updateTaskUiModelResoonse=new SingleLiveEvent<>();
    }

    public SingleLiveEvent<Resource<SiteInfoDropDownData>> getDropDownResoonse() {
        return dropDownResoonse;
    }

    public SingleLiveEvent<Resource<GetTaskDataModel>> getTaskUiModelResoonse() {
        return taskUiModelResoonse;
    }

    public SingleLiveEvent<Resource<UpdateTaskDataModel>> getUpdateTaskUiModelResoonse() {
        return updateTaskUiModelResoonse;
    }

    public SingleLiveEvent<Resource<SiteInfoModel>> getSiteInfoResponseData() {
        return siteIndoResponse;
    }

    public SingleLiveEvent<Resource<SearchList>> getSiteSearchResponseData() {
        return siteSearchResponse;
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

    public void siteTaskUiModel(String taskId) {
        JsonObject jsonObject = new JsonObject();
        AppLogger.INSTANCE.log("json data of siteTaskUiModel=====>: "+jsonObject);
        if(!Utils.INSTANCE.isNetworkConnected(AppController.getInstance())){
            String json = AppPreferences.getInstance().getString("siteTaskUiModel"+taskId);
            GetTaskDataModel taskModel = new Gson().fromJson(json,GetTaskDataModel.class);
            AppPreferences.getInstance().saveString("siteTaskUiModel"+taskId,json);
            taskUiModelResoonse.postValue(Resource.success(taskModel, 200));
              return;
        }
        apiClient.dynamicTaskUiModel(jsonObject).enqueue(new Callback<GetTaskDataModel>() {
            @Override
            public void onResponse(Call<GetTaskDataModel> call, Response<GetTaskDataModel> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<GetTaskDataModel> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<GetTaskDataModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
                    String json = new Gson().toJson(response.body());
                    AppPreferences.getInstance().saveString("siteTaskUiModel"+taskId,json);
                    taskUiModelResoonse.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    dropDownResoonse.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    taskUiModelResoonse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    taskUiModelResoonse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }
    public void siteTaskUiUpdateModel(TaskDropDownModel taskDropDownModel) {
        apiClient.dynamicTaskUiModelUpdate(taskDropDownModel).enqueue(new Callback<UpdateTaskDataModel>() {
            @Override
            public void onResponse(Call<UpdateTaskDataModel> call, Response<UpdateTaskDataModel> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else if (response!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else AppLogger.INSTANCE.log("getProfileData response is null");
            }

            @Override
            public void onFailure(Call<UpdateTaskDataModel> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<UpdateTaskDataModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
//                    Logger.getLogger("ProfileRepo").warning(response.toString());
                    updateTaskUiModelResoonse.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    dropDownResoonse.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    taskUiModelResoonse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    taskUiModelResoonse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

}
