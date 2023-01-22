package com.smarthub.baseapplication.network.repo;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;
import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.helpers.SingleLiveEvent;
import com.smarthub.baseapplication.model.APIError;
import com.smarthub.baseapplication.model.basicInfo.IdData;
import com.smarthub.baseapplication.model.dropdown.newData.DropDownNew;
import com.smarthub.baseapplication.model.home.HomeResponse;
import com.smarthub.baseapplication.model.notification.newData.NotificationNew;
import com.smarthub.baseapplication.model.project.ProjectModelData;
import com.smarthub.baseapplication.model.project.TaskModelData;
import com.smarthub.baseapplication.model.search.SearchAliasNameItem;
import com.smarthub.baseapplication.model.search.SearchList;
import com.smarthub.baseapplication.model.search.SearchListItem;
import com.smarthub.baseapplication.model.search.SearchSiteOpcoName;
import com.smarthub.baseapplication.model.search.SearchSiteIdItem;
import com.smarthub.baseapplication.model.search.SearchSiteNameItem;
import com.smarthub.baseapplication.model.search.SearchSiteOpcoSiteId;
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData;
import com.smarthub.baseapplication.model.serviceRequest.log.LogSearchData;
import com.smarthub.baseapplication.model.serviceRequest.new_site.GenerateSiteIdResponse;
import com.smarthub.baseapplication.model.siteInfo.newData.SiteInfoModelNew;
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.NocAndCompModel;
import com.smarthub.baseapplication.model.siteInfo.OpcoDataList;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModelUpdate;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoParam;
import com.smarthub.baseapplication.model.siteInfo.oprationInfo.UpdateOperationInfo;
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignModel;
import com.smarthub.baseapplication.model.siteInfo.powerFuel.PowerAndFuelModel;
import com.smarthub.baseapplication.model.siteInfo.qat.QatModel;
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteAgreementModel;
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteacquisitionAgreement;
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerCivilInfraModel;
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.newData.OpcoInfoNewModel;
import com.smarthub.baseapplication.model.siteInfo.service_request.ServiceRequestModel;
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.UtilitiesEquipModel;
import com.smarthub.baseapplication.model.workflow.TaskDataList;
import com.smarthub.baseapplication.network.APIClient;
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData;
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel;
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.CreateSiteModel;
import com.smarthub.baseapplication.ui.dialog.siteinfo.repo.BasicInfoDialougeResponse;
import com.smarthub.baseapplication.utils.AppConstants;
import com.smarthub.baseapplication.utils.AppController;
import com.smarthub.baseapplication.utils.AppLogger;

import java.util.ArrayList;
import java.util.List;

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
    private SingleLiveEvent<Resource<SiteInfoModelNew>> siteInfoModelNew;
    private SingleLiveEvent<Resource<NotificationNew>> notificationNew;
    private SingleLiveEvent<Resource<SiteInfoModelUpdate>> siteInfoUpdate;
    private SingleLiveEvent<Resource<SiteInfoModel>> siteInfoResponse;
    private SingleLiveEvent<Resource<SearchList>> siteSearchResponse;
    private SingleLiveEvent<Resource<OpcoDataList>> opcoDataResponse;
    private SingleLiveEvent<Resource<ServiceRequestAllData>> serviceRequestAllData;
    private SingleLiveEvent<Resource<GenerateSiteIdResponse>> generateSiteIdResponse;
    private SingleLiveEvent<Resource<SiteInfoDropDownData>> dropDownResoonse;
    private SingleLiveEvent<Resource<DropDownNew>> dropDownResponseNew;
    private SingleLiveEvent<Resource<TaskDataList>> taskDataList;
    private SingleLiveEvent<Resource<ServiceRequestModel>> serviceRequestModel;
    private SingleLiveEvent<Resource<OpcoInfoNewModel>> opcoTenencyModel;
    private SingleLiveEvent<Resource<NocAndCompModel>> noCandCompModel;
    private SingleLiveEvent<Resource<TowerCivilInfraModel>> towerAndCivilInfraModel;
    private SingleLiveEvent<Resource<PowerAndFuelModel>> powerFuelModel;
    private SingleLiveEvent<Resource<SiteAgreementModel>> siteAgreementModel;
    private SingleLiveEvent<Resource<PlanAndDesignModel>> planAndDesignModel;
    private SingleLiveEvent<Resource<QatModel>> qatModelResponse;
    private SingleLiveEvent<Resource<UtilitiesEquipModel>> utilityEquipModel;
    private SingleLiveEvent<Resource<LogSearchData>> loglivedata;
    private SingleLiveEvent<Resource<SiteacquisitionAgreement>> updateAgreementInfo;

    public static HomeRepo getInstance(APIClient apiClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new HomeRepo(apiClient);
            }
        }
        return sInstance;
    }

    public SingleLiveEvent<Resource<LogSearchData>> getloglivedata() {
        return loglivedata;
    }
    public SingleLiveEvent<Resource<SiteInfoModelNew>> getSiteInfoModelNew() {
        return siteInfoModelNew;
    }

    public SingleLiveEvent<Resource<NotificationNew>> getNotificationNew() {
        return notificationNew;
    }

    public SingleLiveEvent<Resource<SearchList>> getSiteSearchResponseData() {
        return siteSearchResponse;
    }

    public SingleLiveEvent<Resource<SiteInfoDropDownData>> getDropDownResoonse() {
        return dropDownResoonse;
    }

    public SingleLiveEvent<Resource<DropDownNew>> getDropDownResponseNew() {
        return dropDownResponseNew;
    }

    public SingleLiveEvent<Resource<SiteAgreementModel>> getSiteAgreementModel() {
        return siteAgreementModel;
    }

    public SingleLiveEvent<Resource<ServiceRequestModel>> getServiceRequestModel() {
        return serviceRequestModel;
    }
    public SingleLiveEvent<Resource<OpcoInfoNewModel>> getOpcoTenencyModel() {
        return opcoTenencyModel;
    }
    public SingleLiveEvent<Resource<NocAndCompModel>> getNOCandCompModel() {
        return noCandCompModel;
    }
    public SingleLiveEvent<Resource<UtilitiesEquipModel>> getUtilityEquipModel() {
        return utilityEquipModel;
    }
    public SingleLiveEvent<Resource<TowerCivilInfraModel>> getTowerAndCivilInfraModel() {
        return towerAndCivilInfraModel;
    }
    public SingleLiveEvent<Resource<PlanAndDesignModel>> getPlanAndDesignModel() {
        return planAndDesignModel;
    }
    public SingleLiveEvent<Resource<QatModel>> getQatModelResponse() {
        return qatModelResponse;
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
        opcoTenencyModel=new SingleLiveEvent<>();
        noCandCompModel=new SingleLiveEvent<>();
        towerAndCivilInfraModel=new SingleLiveEvent<>();
        powerFuelModel = new SingleLiveEvent<>();
        planAndDesignModel=new SingleLiveEvent<>();
        utilityEquipModel=new SingleLiveEvent<>();
        siteInfoUpdate = new SingleLiveEvent<>();
        loglivedata = new SingleLiveEvent<>();
        siteAgreementModel = new SingleLiveEvent<>();
        dropDownResponseNew = new SingleLiveEvent<>();
        siteInfoModelNew = new SingleLiveEvent<>();
        notificationNew = new SingleLiveEvent<>();
        qatModelResponse = new SingleLiveEvent<>();
    }

    public SingleLiveEvent<Resource<HomeResponse>> getHomeResponse() {
        return homeResponse;
    }
    public SingleLiveEvent<Resource<TaskDataList>> getTaskDataList() {
        return taskDataList;
    }
    public SingleLiveEvent<Resource<SiteInfoModelUpdate>> getSiteInfoUpdateData() {
        return siteInfoUpdate;
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

    public SingleLiveEvent<Resource<PowerAndFuelModel>> getPowerFuelModel() {
        return powerFuelModel;
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

    public void updateOperationInfo(UpdateOperationInfo basicinfoModel) {
//        SiteInfoModelUpdate d = (basicInfoUpdate.getValue()!=null) ? siteInfoUpdate.getValue().data : null;
//        siteInfoUpdate.postValue(Resource.loading(d, 200));
        apiClient.updateOperationalInfo(basicinfoModel).enqueue(new Callback<SiteInfoModelUpdate>() {
            @Override
            public void onResponse(@NonNull Call<SiteInfoModelUpdate> call, Response<SiteInfoModelUpdate> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<SiteInfoModelUpdate> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SiteInfoModelUpdate> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.toString());
                    siteInfoUpdate.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    siteInfoUpdate.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    siteInfoUpdate.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    siteInfoUpdate.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
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
    public void updateAgreementSiteInfo(SiteacquisitionAgreement siteacquisitionAgreement) {
        BasicInfoDialougeResponse d = (basicInfoUpdate.getValue()!=null) ? basicInfoUpdate.getValue().data : null;
        basicInfoUpdate.postValue(Resource.loading(d, 200));
        apiClient.updateAgreementSiteInfo(siteacquisitionAgreement).enqueue(new Callback<SiteacquisitionAgreement>() {
            @Override
            public void onResponse(@NonNull Call<SiteacquisitionAgreement> call, Response<SiteacquisitionAgreement> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SiteacquisitionAgreement> call, @NonNull Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SiteacquisitionAgreement> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.toString());
                    updateAgreementInfo.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    updateAgreementInfo.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    updateAgreementInfo.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void createSite(CreateSiteModel basicinfoModel) {
        apiClient.createSite(basicinfoModel).enqueue(new Callback<SiteInfoModelNew>() {
            @Override
            public void onResponse(@NonNull Call<SiteInfoModelNew> call, Response<SiteInfoModelNew> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SiteInfoModelNew> call, @NonNull Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SiteInfoModelNew> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.toString());
                    siteInfoModelNew.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    siteInfoModelNew.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    siteInfoModelNew.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void getAllNotification() {
        apiClient.getNotification().enqueue(new Callback<NotificationNew>() {
            @Override
            public void onResponse(@NonNull Call<NotificationNew> call, Response<NotificationNew> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<NotificationNew> call, @NonNull Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<NotificationNew> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.toString());
                    notificationNew.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    notificationNew.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    notificationNew.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void fetchHomeData() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ownername",AppController.getInstance().ownerName);
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
        jsonObject.addProperty("ownername",AppController.getInstance().ownerName);
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
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
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
        jsonObject.addProperty("ownername",AppController.getInstance().ownerName);
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
        jsonObject.addProperty("ownername",AppController.getInstance().ownerName);
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
        opcoDataResponse.postValue(Resource.loading((opcoDataResponse.getValue()!=null)?opcoDataResponse.getValue().data:null, 200));
        siteInfoResponse.postValue(Resource.loading((siteInfoResponse.getValue()!=null)?siteInfoResponse.getValue().data:null, 200));
        apiClient.fetchSiteInfoById(new IdData(id,AppController.getInstance().ownerName)).enqueue(new Callback<SiteInfoModel>() {
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

    public void serviceRequestAll(String id) {
        ArrayList<String> list = new ArrayList<>();
        list.add("ServiceRequestMain");
        SiteInfoParam siteInfoParam = new SiteInfoParam(list,Integer.parseInt(id),AppController.getInstance().ownerName);
        ServiceRequestModel srModel = (serviceRequestModel!=null && serviceRequestModel.getValue()!=null)?serviceRequestModel.getValue().data:null;
        serviceRequestModel.postValue(Resource.loading(srModel, 200));
        apiClient.fetchServiceRequest(siteInfoParam).enqueue(new Callback<ServiceRequestModel>() {
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

    public void opcoRequestAll(String id) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Operator");
        SiteInfoParam siteInfoParam = new SiteInfoParam(list,Integer.parseInt(id),AppController.getInstance().ownerName);
        apiClient.fetchOpcoInfoRequest(siteInfoParam).enqueue(new Callback<OpcoInfoNewModel>() {
            @Override
            public void onResponse(Call<OpcoInfoNewModel> call, Response<OpcoInfoNewModel> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<OpcoInfoNewModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<OpcoInfoNewModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
                    opcoTenencyModel.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    opcoTenencyModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    opcoTenencyModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void planDesignRequestAll(String id) {
        ArrayList<String> list = new ArrayList<>();
        list.add("PlanningAndDesign");
        SiteInfoParam siteInfoParam = new SiteInfoParam(list,Integer.parseInt(id),AppController.getInstance().ownerName);
        apiClient.fetchPlanDesignRequest(siteInfoParam).enqueue(new Callback<PlanAndDesignModel>() {
            @Override
            public void onResponse(Call<PlanAndDesignModel> call, Response<PlanAndDesignModel> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<PlanAndDesignModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<PlanAndDesignModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
                    planAndDesignModel.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    planAndDesignModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    planAndDesignModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }
    public void qatRequestAll(String id) {
        ArrayList<String> list = new ArrayList<>();
        list.add("QATTemplateMain");
        SiteInfoParam siteInfoParam = new SiteInfoParam(list,Integer.parseInt(id),AppController.getInstance().ownerName);
        apiClient.fetchQatRequest(siteInfoParam).enqueue(new Callback<QatModel>() {
            @Override
            public void onResponse(Call<QatModel> call, Response<QatModel> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<QatModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<QatModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
                    qatModelResponse.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    qatModelResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    qatModelResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void siteAgreementRequestAll(String id) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Siteacquisition");
        SiteInfoParam siteInfoParam = new SiteInfoParam(list,Integer.parseInt(id),AppController.getInstance().ownerName);
        apiClient.fetchSiteAgreementModelRequest(siteInfoParam).enqueue(new Callback<SiteAgreementModel>() {
            @Override
            public void onResponse(Call<SiteAgreementModel> call, Response<SiteAgreementModel> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<SiteAgreementModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SiteAgreementModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
                    siteAgreementModel.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    siteAgreementModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    siteAgreementModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void utilitiEquipRequestAll(String id) {
        ArrayList<String> list = new ArrayList<>();
        list.add("utilities");
        AppLogger.INSTANCE.log("id :"+id);
        SiteInfoParam siteInfoParam = new SiteInfoParam(list,Integer.parseInt(id),AppController.getInstance().ownerName);
        apiClient.fetchUtilitiesEquipRequest(siteInfoParam).enqueue(new Callback<UtilitiesEquipModel>() {
            @Override
            public void onResponse(Call<UtilitiesEquipModel> call, Response<UtilitiesEquipModel> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<UtilitiesEquipModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<UtilitiesEquipModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
                    utilityEquipModel.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    utilityEquipModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    utilityEquipModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void NocAndCompRequestAll(String id) {
        ArrayList<String> list = new ArrayList<>();
        list.add("NOCCompliance");
        SiteInfoParam siteInfoParam = new SiteInfoParam(list,Integer.parseInt(id),AppController.getInstance().ownerName);
        apiClient.fetchNocAndCompRequest(siteInfoParam).enqueue(new Callback<NocAndCompModel>() {
            @Override
            public void onResponse(Call<NocAndCompModel> call, Response<NocAndCompModel> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<NocAndCompModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<NocAndCompModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
                    noCandCompModel.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    noCandCompModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    noCandCompModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void TowerCivilInfraRequestAll(String id) {
        ArrayList<String> list = new ArrayList<>();
        list.add("TowerAndCivilInfra");
        SiteInfoParam siteInfoParam = new SiteInfoParam(list,Integer.parseInt(id),AppController.getInstance().ownerName);
        apiClient.fetchTowerCivilInfraRequest(siteInfoParam).enqueue(new Callback<TowerCivilInfraModel>() {
            @Override
            public void onResponse(Call<TowerCivilInfraModel> call, Response<TowerCivilInfraModel> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<TowerCivilInfraModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<TowerCivilInfraModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
                    towerAndCivilInfraModel.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    towerAndCivilInfraModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    towerAndCivilInfraModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void powerAndFuelRequestAll(String id) {
        ArrayList<String> list = new ArrayList<>();
        list.add("PowerAndFuel");
        SiteInfoParam siteInfoParam = new SiteInfoParam(list,Integer.parseInt(id),AppController.getInstance().ownerName);
        apiClient.fetchPowerFuelRequest(siteInfoParam).enqueue(new Callback<PowerAndFuelModel>() {
            @Override
            public void onResponse(Call<PowerAndFuelModel> call, Response<PowerAndFuelModel> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<PowerAndFuelModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<PowerAndFuelModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
                    powerFuelModel.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    powerFuelModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    powerFuelModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void chamgeLogAll(String id) {
        ArrayList<String> list = new ArrayList<>();
        list.add("ChangeLog");
        SiteInfoParam siteInfoParam = new SiteInfoParam(list,Integer.parseInt(id),AppController.getInstance().ownerName);
        apiClient.fetchLogData(siteInfoParam).enqueue(new Callback<LogSearchData>() {
            @Override
            public void onResponse(Call<LogSearchData> call, Response<LogSearchData> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<LogSearchData> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<LogSearchData> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response);
                    loglivedata.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    loglivedata.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    loglivedata.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void searchSiteAll(String category,String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(category,id);
        jsonObject.addProperty("ownername",AppController.getInstance().ownerName);
        if (category.equalsIgnoreCase("name"))
            apiClient.searchSiteByName(jsonObject).enqueue(new Callback<List<SearchListItem>>() {
                @Override
                public void onResponse(Call<List<SearchListItem>> call, Response<List<SearchListItem>> response) {
                    if (response.isSuccessful()){
                        reportSuccessResponse(response);
                    }else {
                        AppLogger.INSTANCE.log("error :"+response);
                    }
                }

                @Override
                public void onFailure(Call<List<SearchListItem>> call, Throwable t) {
                    reportErrorResponse(null, t.getLocalizedMessage());
                }

                private void reportSuccessResponse(Response<List<SearchListItem>> response) {
                    if (response != null && response.body()!=null) {
                        SearchList searchList = new SearchList();
                        searchList.addAll(response.body());
                        siteSearchResponse.postValue(Resource.success(searchList, 200));
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
        else if (category.equalsIgnoreCase("siteID"))
            apiClient.searchSiteBySiteId(jsonObject).enqueue(new Callback<List<SearchSiteIdItem>>() {
                @Override
                public void onResponse(Call<List<SearchSiteIdItem>> call, Response<List<SearchSiteIdItem>> response) {
                    if (response.isSuccessful()){
                        reportSuccessResponse(response);
                    }else {
                        AppLogger.INSTANCE.log("error :"+response);
                    }
                }

                @Override
                public void onFailure(Call<List<SearchSiteIdItem>> call, Throwable t) {
                    reportErrorResponse(null, t.getLocalizedMessage());
                }

                private void reportSuccessResponse(Response<List<SearchSiteIdItem>> response) {
                    if (response != null && response.body()!=null) {
                        SearchList searchList = new SearchList();
                        searchList.addAll(response.body());
                        siteSearchResponse.postValue(Resource.success(searchList, 200));
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
        else if (category.equalsIgnoreCase("siteName"))
            apiClient.searchSiteBySiteName(jsonObject).enqueue(new Callback<List<SearchSiteNameItem>>() {
                @Override
                public void onResponse(Call<List<SearchSiteNameItem>> call, Response<List<SearchSiteNameItem>> response) {
                    if (response.isSuccessful()){
                        reportSuccessResponse(response);
                    }else {
                        AppLogger.INSTANCE.log("error :"+response);
                    }
                }

                @Override
                public void onFailure(Call<List<SearchSiteNameItem>> call, Throwable t) {
                    reportErrorResponse(null, t.getLocalizedMessage());
                }

                private void reportSuccessResponse(Response<List<SearchSiteNameItem>> response) {
                    if (response != null && response.body()!=null) {
                        SearchList searchList = new SearchList();
                        searchList.addAll(response.body());
                        siteSearchResponse.postValue(Resource.success(searchList, 200));
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
        else if (category.equalsIgnoreCase("aliasName"))
            apiClient.searchSiteByAliasName(jsonObject).enqueue(new Callback<List<SearchAliasNameItem>>() {
                @Override
                public void onResponse(Call<List<SearchAliasNameItem>> call, Response<List<SearchAliasNameItem>> response) {
                    if (response.isSuccessful()){
                        reportSuccessResponse(response);
                    }else {
                        AppLogger.INSTANCE.log("error :"+response);
                    }
                }

                @Override
                public void onFailure(Call<List<SearchAliasNameItem>> call, Throwable t) {
                    reportErrorResponse(null, t.getLocalizedMessage());
                }

                private void reportSuccessResponse(Response<List<SearchAliasNameItem>> response) {
                    if (response != null && response.body()!=null) {
                        SearchList searchList = new SearchList();
                        searchList.addAll(response.body());
                        siteSearchResponse.postValue(Resource.success(searchList, 200));
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
        if (category.equalsIgnoreCase("OpcoName"))
            apiClient.searchSiteByOpcoName(jsonObject).enqueue(new Callback<List<SearchSiteOpcoName>>() {
                @Override
                public void onResponse(Call<List<SearchSiteOpcoName>> call, Response<List<SearchSiteOpcoName>> response) {
                    if (response.isSuccessful()){
                        reportSuccessResponse(response);
                    }else {
                        AppLogger.INSTANCE.log("error :"+response);
                    }
                }

                @Override
                public void onFailure(Call<List<SearchSiteOpcoName>> call, Throwable t) {
                    reportErrorResponse(null, t.getLocalizedMessage());
                }

                private void reportSuccessResponse(Response<List<SearchSiteOpcoName>> response) {
                    if (response != null && response.body()!=null) {
                        SearchList searchList = new SearchList();
                        searchList.addAll(response.body());
                        siteSearchResponse.postValue(Resource.success(searchList, 200));
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
        if (category.equalsIgnoreCase("SearchSiteOpcoSiteId"))
            apiClient.searchSiteByOpcoSiteId(jsonObject).enqueue(new Callback<List<SearchSiteOpcoSiteId>>() {
                @Override
                public void onResponse(Call<List<SearchSiteOpcoSiteId>> call, Response<List<SearchSiteOpcoSiteId>> response) {
                    if (response.isSuccessful()){
                        reportSuccessResponse(response);
                    }else {
                        AppLogger.INSTANCE.log("error :"+response);
                    }
                }

                @Override
                public void onFailure(Call<List<SearchSiteOpcoSiteId>> call, Throwable t) {
                    reportErrorResponse(null, t.getLocalizedMessage());
                }

                private void reportSuccessResponse(Response<List<SearchSiteOpcoSiteId>> response) {
                    if (response != null && response.body()!=null) {
                        SearchList searchList = new SearchList();
                        searchList.addAll(response.body());
                        siteSearchResponse.postValue(Resource.success(searchList, 200));
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

    public void siteInfoDropDownNew() {

        apiClient.siteInfoDropDownNew().enqueue(new Callback<DropDownNew>() {
            @Override
            public void onResponse(Call<DropDownNew> call, Response<DropDownNew> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<DropDownNew> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<DropDownNew> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
                    dropDownResponseNew.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    dropDownResponseNew.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    dropDownResponseNew.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    dropDownResponseNew.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void getTaskById(String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("gettaskdata",id);
        jsonObject.addProperty("ownername",AppController.getInstance().ownerName);
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
