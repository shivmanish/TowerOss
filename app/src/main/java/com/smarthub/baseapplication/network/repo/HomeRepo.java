package com.smarthub.baseapplication.network.repo;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;
import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.helpers.SingleLiveEvent;
import com.smarthub.baseapplication.model.APIError;
import com.smarthub.baseapplication.model.basicInfo.IdData;
import com.smarthub.baseapplication.model.home.HomeResponse;
import com.smarthub.baseapplication.model.project.ProjectModelData;
import com.smarthub.baseapplication.model.project.TaskModelData;
import com.smarthub.baseapplication.model.search.SearchList;
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData;
import com.smarthub.baseapplication.model.serviceRequest.new_site.GenerateSiteIdResponse;
import com.smarthub.baseapplication.model.siteInfo.OpcoDataList;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoParam;
import com.smarthub.baseapplication.model.siteInfo.service_request.ServiceRequestModel;
import com.smarthub.baseapplication.model.workflow.TaskDataList;
import com.smarthub.baseapplication.network.APIClient;
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData;
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel;
import com.smarthub.baseapplication.ui.dialog.siteinfo.repo.BasicInfoDialougeResponse;
import com.smarthub.baseapplication.utils.AppConstants;
import com.smarthub.baseapplication.utils.AppController;
import com.smarthub.baseapplication.utils.AppLogger;

import java.util.ArrayList;

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
    private SingleLiveEvent<Resource<BasicInfoDialougeResponse>> basicInfoUpdate;
    private SingleLiveEvent<Resource<SiteInfoModel>> siteInfoResponse;
    private SingleLiveEvent<Resource<SearchList>> siteSearchResponse;
    private SingleLiveEvent<Resource<OpcoDataList>> opcoDataResponse;
    private SingleLiveEvent<Resource<ServiceRequestAllData>> serviceRequestAllData;
    private SingleLiveEvent<Resource<GenerateSiteIdResponse>> generateSiteIdResponse;
    private SingleLiveEvent<Resource<SiteInfoDropDownData>> dropDownResoonse;
    private SingleLiveEvent<Resource<TaskDataList>> taskDataList;
    private SingleLiveEvent<Resource<ServiceRequestModel>> serviceRequestModel;

    public static HomeRepo getInstance(APIClient apiClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new HomeRepo(apiClient);
            }
        }
        return sInstance;
    }

    public SingleLiveEvent<Resource<SearchList>> getSiteSearchResponseData() {
        return siteSearchResponse;
    }

    public SingleLiveEvent<Resource<ServiceRequestModel>> getServiceRequestModel() {
        return serviceRequestModel;
    }

    public SingleLiveEvent<Resource<OpcoDataList>> getOpcoResponseData() {
        return opcoDataResponse;
    }
    public SingleLiveEvent<Resource<ServiceRequestAllData>> getServiceRequestAllData() {
        return serviceRequestAllData;
    }

    public SingleLiveEvent<Resource<GenerateSiteIdResponse>> getGenerateSiteIdResponse() {
        return generateSiteIdResponse;
    }

    public SingleLiveEvent<Resource<BasicInfoDialougeResponse>> getBasicInfoUpdate() {
        return basicInfoUpdate;
    }

    public HomeRepo(APIClient apiClient) {
        this.apiClient = apiClient;
        homeResponse = new SingleLiveEvent<>();
        projectResponse = new SingleLiveEvent<>();
        taskResponse = new SingleLiveEvent<>();
        serRequestData = new SingleLiveEvent<>();
        basicInfoUpdate = new SingleLiveEvent<>();
        siteInfoResponse = new SingleLiveEvent<>();
        siteSearchResponse = new SingleLiveEvent<>();
        dropDownResoonse = new SingleLiveEvent<>();
        opcoDataResponse = new SingleLiveEvent<>();
        serviceRequestAllData = new SingleLiveEvent<>();
        generateSiteIdResponse = new SingleLiveEvent<>();
        taskDataList = new SingleLiveEvent<>();
        serviceRequestModel = new SingleLiveEvent<>();
    }

    public SingleLiveEvent<Resource<HomeResponse>> getHomeResponse() {
        return homeResponse;
    }
    public SingleLiveEvent<Resource<TaskDataList>> getTaskDataList() {
        return taskDataList;
    }

    public SingleLiveEvent<Resource<SiteInfoDropDownData>> getSiteDropDownDataResponse() {
        return dropDownResoonse;
    }

    public SingleLiveEvent<Resource<SiteInfoModel>> getSiteInfoResponse() {
        return siteInfoResponse;
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

    public void updateData(BasicinfoModel basicinfoModel) {
        BasicInfoDialougeResponse d = (basicInfoUpdate.getValue()!=null) ? basicInfoUpdate.getValue().data : null;
        basicInfoUpdate.postValue(Resource.loading(d, 200));
        apiClient.updateBasicInfo(basicinfoModel).enqueue(new Callback<BasicInfoDialougeResponse>() {
            @Override
            public void onResponse(@NonNull Call<BasicInfoDialougeResponse> call, Response<BasicInfoDialougeResponse> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<BasicInfoDialougeResponse> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<BasicInfoDialougeResponse> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.toString());
                    basicInfoUpdate.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    basicInfoUpdate.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    basicInfoUpdate.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    basicInfoUpdate.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }
    public void generateSiteId(GenerateSiteIdResponse basicinfoModel) {
        GenerateSiteIdResponse d = (generateSiteIdResponse.getValue()!=null) ? generateSiteIdResponse.getValue().data : null;
        generateSiteIdResponse.postValue(Resource.loading(d, 200));
        apiClient.generateSiteId(basicinfoModel).enqueue(new Callback<GenerateSiteIdResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenerateSiteIdResponse> call, Response<GenerateSiteIdResponse> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<GenerateSiteIdResponse> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<GenerateSiteIdResponse> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.toString());
                    generateSiteIdResponse.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    generateSiteIdResponse.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    generateSiteIdResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    generateSiteIdResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void updateSiteInfo(BasicinfoModel basicinfoModel) {
        BasicInfoDialougeResponse d = (basicInfoUpdate.getValue()!=null) ? basicInfoUpdate.getValue().data : null;
        basicInfoUpdate.postValue(Resource.loading(d, 200));
        apiClient.updateSiteInfo(basicinfoModel).enqueue(new Callback<BasicInfoDialougeResponse>() {
            @Override
            public void onResponse(@NonNull Call<BasicInfoDialougeResponse> call, Response<BasicInfoDialougeResponse> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<BasicInfoDialougeResponse> call, @NonNull Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<BasicInfoDialougeResponse> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.toString());
                    basicInfoUpdate.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    basicInfoUpdate.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    basicInfoUpdate.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
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

    public void fetchServiceRequestData(String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",id);
        Call<ServiceRequestAllData> request = apiClient.fetchsServiceRequestData(jsonObject);
        request.enqueue(new Callback<ServiceRequestAllData>() {
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

    public void siteInfoById(String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",id);
        jsonObject.addProperty("ownername","SMRT");
        opcoDataResponse.postValue(Resource.loading((opcoDataResponse.getValue()!=null)?opcoDataResponse.getValue().data:null, 200));
        siteInfoResponse.postValue(Resource.loading((siteInfoResponse.getValue()!=null)?siteInfoResponse.getValue().data:null, 200));
        apiClient.fetchSiteInfoById(new IdData(id)).enqueue(new Callback<SiteInfoModel>() {
            @Override
            public void onResponse(Call<SiteInfoModel> call, Response<SiteInfoModel> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<SiteInfoModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SiteInfoModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
                    SiteInfoModel data = response.body();
                    AppController.getInstance().siteInfoModel = data;
                    siteInfoResponse.postValue(Resource.success(response.body(), 200));
//                   data update
                    if (data.getItem()!=null && !data.getItem().isEmpty()) {
                        opcoDataResponse.postValue(Resource.success(new OpcoDataList(data.getItem().get(0).getOperator()), 200));
                        serviceRequestAllData.postValue(Resource.success(data.getItem().get(0).getServiceRequestMain(), 200));
                        AppLogger.INSTANCE.log("Service request Fragment data error :"+ data.getItem().get(0).getServiceRequestMain());

                    }
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    siteInfoResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    siteInfoResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void serviceRequestAll(String id,String filter) {
        ArrayList<String> list = new ArrayList<>();
        list.add(filter);
        SiteInfoParam siteInfoParam = new SiteInfoParam(list,Integer.parseInt(id),"SMRT");
        apiClient.fetchSiteInfoRequest(siteInfoParam).enqueue(new Callback<ServiceRequestModel>() {
            @Override
            public void onResponse(Call<ServiceRequestModel> call, Response<ServiceRequestModel> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<ServiceRequestModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<ServiceRequestModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
                    serviceRequestModel.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    serviceRequestModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    serviceRequestModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void siteSearchData(String id) {

        apiClient.searchSiteInfoData(id).enqueue(new Callback<SearchList>() {
            @Override
            public void onResponse(Call<SearchList> call, Response<SearchList> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<SearchList> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SearchList> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
                    siteSearchResponse.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    siteSearchResponse.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    siteSearchResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    siteSearchResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void siteSearchDataNew(String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("siteID",id);
        apiClient.searchSiteInfoDataNew(jsonObject).enqueue(new Callback<SearchList>() {
            @Override
            public void onResponse(Call<SearchList> call, Response<SearchList> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<SearchList> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SearchList> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
                    siteSearchResponse.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    siteSearchResponse.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    siteSearchResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    siteSearchResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void siteSearchData(String id,String category) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(id,category);
        apiClient.searchSiteInfoData(jsonObject).enqueue(new Callback<SearchList>() {
            @Override
            public void onResponse(Call<SearchList> call, Response<SearchList> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<SearchList> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SearchList> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
                    siteSearchResponse.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    siteSearchResponse.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    siteSearchResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    siteSearchResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void siteInfoDropDown() {

        apiClient.siteInfoDropDown().enqueue(new Callback<SiteInfoDropDownData>() {
            @Override
            public void onResponse(Call<SiteInfoDropDownData> call, Response<SiteInfoDropDownData> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
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

    public void getTaskById(String id) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("gettaskdata",id);
        apiClient.getTaskDataById(jsonObject).enqueue(new Callback<TaskDataList>() {
            @Override
            public void onResponse(Call<TaskDataList> call, Response<TaskDataList> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<TaskDataList> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<TaskDataList> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
//                    Logger.getLogger("ProfileRepo").warning(response.toString());
                    taskDataList.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    taskDataList.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    taskDataList.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    taskDataList.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }
}
