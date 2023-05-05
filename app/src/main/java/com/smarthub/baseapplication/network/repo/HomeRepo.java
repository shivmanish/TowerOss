package com.smarthub.baseapplication.network.repo;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.smarthub.baseapplication.helpers.AppPreferences;
import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.helpers.SingleLiveEvent;
import com.smarthub.baseapplication.model.APIError;
import com.smarthub.baseapplication.model.basicInfo.IdData;
import com.smarthub.baseapplication.model.dropdown.newData.DropDownNew;
import com.smarthub.baseapplication.model.dropdown.newData.DropDownNewItem;
import com.smarthub.baseapplication.model.home.HomeResponse;
import com.smarthub.baseapplication.model.logs.LogsDataModel;
import com.smarthub.baseapplication.model.logs.PostLogData;
import com.smarthub.baseapplication.model.notification.newData.AddNotificationModel;
import com.smarthub.baseapplication.model.notification.newData.AddNotificationResponse;
import com.smarthub.baseapplication.model.notification.newData.NotificationNew;
import com.smarthub.baseapplication.model.project.ProjectModelData;
import com.smarthub.baseapplication.model.project.TaskModelData;
import com.smarthub.baseapplication.model.qatcheck.QalLaunchModel;
import com.smarthub.baseapplication.model.qatcheck.punch_point.QatPunchPointModel;
import com.smarthub.baseapplication.model.qatcheck.update.QatUpdateModel;
import com.smarthub.baseapplication.model.search.SearchAliasNameItem;
import com.smarthub.baseapplication.model.search.SearchList;
import com.smarthub.baseapplication.model.search.SearchListItem;
import com.smarthub.baseapplication.model.search.SearchSiteIdItem;
import com.smarthub.baseapplication.model.search.SearchSiteNameItem;
import com.smarthub.baseapplication.model.search.SearchSiteOpcoName;
import com.smarthub.baseapplication.model.search.SearchSiteOpcoSiteId;
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData;
import com.smarthub.baseapplication.model.serviceRequest.acquisitionSurvey.AcquisitionSurveyAllDataItem;
import com.smarthub.baseapplication.model.serviceRequest.acquisitionSurvey.AcquisitionSurveyModel;
import com.smarthub.baseapplication.model.serviceRequest.new_site.GenerateSiteIdResponse;
import com.smarthub.baseapplication.model.siteIBoard.AttachmentConditionsDataModel;
import com.smarthub.baseapplication.model.siteIBoard.AttachmentsConditions;
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllDataModel;
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllDataModel;
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelAllDataModel;
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SiteAcquisitionAllDataModel;
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.AllsiteInfoDataModel;
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TowerCivilAllDataModel;
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAllDataModel;
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllDataModel;
import com.smarthub.baseapplication.model.siteInfo.OpcoDataList;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModelUpdate;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoParam;
import com.smarthub.baseapplication.model.siteInfo.newData.SiteInfoModelNew;
import com.smarthub.baseapplication.model.siteInfo.oprationInfo.UpdateOperationInfo;
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignModel;
import com.smarthub.baseapplication.model.siteInfo.qat.QatModel;
import com.smarthub.baseapplication.model.siteInfo.qat.SaveCheckpointModel;
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.QatMainModel;
import com.smarthub.baseapplication.model.siteInfo.service_request.ServiceRequestModel;
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteacquisitionAgreement;
import com.smarthub.baseapplication.model.taskModel.department.DepartmentDataModel;
import com.smarthub.baseapplication.model.taskModel.update.CloseTaskModel;
import com.smarthub.baseapplication.model.workflow.TaskDataList;
import com.smarthub.baseapplication.network.APIClient;
import com.smarthub.baseapplication.network.APIInterceptor;
import com.smarthub.baseapplication.network.EndPoints;
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData;
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponse;
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.AddAttachmentModel;
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel;
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.CreateSiteModel;
import com.smarthub.baseapplication.ui.dialog.siteinfo.repo.BasicInfoDialougeResponse;
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfMainResponse;
import com.smarthub.baseapplication.utils.AppConstants;
import com.smarthub.baseapplication.utils.AppController;
import com.smarthub.baseapplication.utils.AppLogger;
import com.smarthub.baseapplication.utils.DropDowns;
import com.smarthub.baseapplication.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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
    private SingleLiveEvent<Resource<CloseTaskModel>> closeTaskModel;
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
    private SingleLiveEvent<Resource<DepartmentDataModel>> departmentDataModel;
    private SingleLiveEvent<Resource<ServiceRequestModel>> serviceRequestModel;
    private SingleLiveEvent<Resource<AcquisitionSurveyModel>> acquisitionSurveyAllDataItem;
    private SingleLiveEvent<Resource<OpcoTenencyAllDataModel>> opcoTenencyModel;
    private SingleLiveEvent<Resource<NocCompAllDataModel>> noCandCompModel;
    private SingleLiveEvent<Resource<TowerCivilAllDataModel>> towerAndCivilInfraModel;
    private SingleLiveEvent<Resource<PowerFuelAllDataModel>> powerFuelModel;
    private SingleLiveEvent<Resource<SiteAcquisitionAllDataModel>> siteAgreementModel;
    private SingleLiveEvent<Resource<SstSbcAllDataModel>> sstSbcModel;
    private SingleLiveEvent<Resource<RfMainResponse>> rfResponesLivedata;
    private SingleLiveEvent<Resource<PlanAndDesignModel>> planAndDesignModel;
    private SingleLiveEvent<Resource<QatModel>> qatModelResponse;
    private SingleLiveEvent<Resource<QatMainModel>> qatMainModelResponse;
    private SingleLiveEvent<Resource<QatUpdateModel>> qatUpdateModel;
    private SingleLiveEvent<Resource<UtilityEquipmentAllDataModel>> utilityEquipModel;
    private SingleLiveEvent<Resource<LogsDataModel>> loglivedata;
    private SingleLiveEvent<Resource<SiteacquisitionAgreement>> updateAgreementInfo;
    private SingleLiveEvent<Resource<AddAttachmentModel>> addAttachmentModel;
    private SingleLiveEvent<Resource<UserDataResponse>> userDataResponse;
    private SingleLiveEvent<Resource<AddNotificationResponse>> addNotificationResponse;
    private SingleLiveEvent<Resource<AllsiteInfoDataModel>> siteInfoDataModel;
    private SingleLiveEvent<Resource<AttachmentConditionsDataModel>> attachmentConsitionsModel;

    public static HomeRepo getInstance(APIClient apiClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new HomeRepo(apiClient);
            }
        }
        return sInstance;
    }

    public SingleLiveEvent<Resource<LogsDataModel>> getloglivedata() {
        return loglivedata;
    }

    public SingleLiveEvent<Resource<SiteInfoModelNew>> getSiteInfoModelNew() {
        return siteInfoModelNew;
    }

    public SingleLiveEvent<Resource<AttachmentConditionsDataModel>> getAttachmentConsitionsModel() {
        return attachmentConsitionsModel;
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

    public SingleLiveEvent<Resource<SiteAcquisitionAllDataModel>> getSiteAgreementModel() {
        return siteAgreementModel;
    }

    public SingleLiveEvent<Resource<SstSbcAllDataModel>> getSstSbcModel() {
            return sstSbcModel;
        }
    public SingleLiveEvent<Resource<RfMainResponse>> getRfSurveyModel() {
        return rfResponesLivedata;
    }

    public SingleLiveEvent<Resource<ServiceRequestModel>> getServiceRequestModel() {
        return serviceRequestModel;
    }

    public SingleLiveEvent<Resource<AcquisitionSurveyModel>> getAcquisitionSurveyAllDataItem() {
        return acquisitionSurveyAllDataItem;
    }

    public SingleLiveEvent<Resource<OpcoTenencyAllDataModel>> getOpcoTenencyModel() {
        return opcoTenencyModel;
    }

    public SingleLiveEvent<Resource<NocCompAllDataModel>> getNOCandCompModel() {
        return noCandCompModel;
    }

    public SingleLiveEvent<Resource<UtilityEquipmentAllDataModel>> getUtilityEquipModel() {
        return utilityEquipModel;
    }

    public SingleLiveEvent<Resource<TowerCivilAllDataModel>> getTowerAndCivilInfraModel() {
        return towerAndCivilInfraModel;
    }

    public SingleLiveEvent<Resource<PlanAndDesignModel>> getPlanAndDesignModel() {
        return planAndDesignModel;
    }

    public SingleLiveEvent<Resource<QatModel>> getQatModelResponse() {
        return qatModelResponse;
    }

    public SingleLiveEvent<Resource<QatMainModel>> getQatMainModelResponse() {
        return qatMainModelResponse;
    }

    public SingleLiveEvent<Resource<QatUpdateModel>> getQatUpdateModel() {
        return qatUpdateModel;
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

    public SingleLiveEvent<Resource<CloseTaskModel>> getCloseTaskModel() {
        return closeTaskModel;
    }

    public SingleLiveEvent<Resource<UserDataResponse>> getUserDataResponse() {
        return userDataResponse;
    }

    public HomeRepo(APIClient apiClient) {
        this.apiClient = apiClient;
        homeResponse = new SingleLiveEvent<>();
        projectResponse = new SingleLiveEvent<>();
        taskResponse = new SingleLiveEvent<>();
        serRequestData = new SingleLiveEvent<>();
        basicInfoUpdate = new SingleLiveEvent<>();
        closeTaskModel = new SingleLiveEvent<>();
        siteInfoResponse = new SingleLiveEvent<>();
        siteSearchResponse = new SingleLiveEvent<>();
        dropDownResoonse = new SingleLiveEvent<>();
        opcoDataResponse = new SingleLiveEvent<>();
        serviceRequestAllData = new SingleLiveEvent<>();
        generateSiteIdResponse = new SingleLiveEvent<>();
        taskDataList = new SingleLiveEvent<>();
        serviceRequestModel = new SingleLiveEvent<>();
        opcoTenencyModel = new SingleLiveEvent<>();
        noCandCompModel = new SingleLiveEvent<>();
        towerAndCivilInfraModel = new SingleLiveEvent<>();
        powerFuelModel = new SingleLiveEvent<>();
        planAndDesignModel = new SingleLiveEvent<>();
        utilityEquipModel = new SingleLiveEvent<>();
        siteInfoUpdate = new SingleLiveEvent<>();
        loglivedata = new SingleLiveEvent<>();
        rfResponesLivedata = new SingleLiveEvent<>();
        siteAgreementModel = new SingleLiveEvent<>();
        sstSbcModel = new SingleLiveEvent<>();
        dropDownResponseNew = new SingleLiveEvent<>();
        siteInfoModelNew = new SingleLiveEvent<>();
        notificationNew = new SingleLiveEvent<>();
        qatModelResponse = new SingleLiveEvent<>();
        qatUpdateModel = new SingleLiveEvent<>();
        qatMainModelResponse = new SingleLiveEvent<>();
        userDataResponse = new SingleLiveEvent<>();
        addNotificationResponse = new SingleLiveEvent<>();
        siteInfoDataModel = new SingleLiveEvent<>();
        acquisitionSurveyAllDataItem = new SingleLiveEvent<>();
        addAttachmentModel = new SingleLiveEvent<>();
        departmentDataModel = new SingleLiveEvent<>();
        attachmentConsitionsModel = new SingleLiveEvent<>();
    }

    public SingleLiveEvent<Resource<HomeResponse>> getHomeResponse() {
        return homeResponse;
    }

    public SingleLiveEvent<Resource<AddNotificationResponse>> getAddNotificationResponse() {
        return addNotificationResponse;
    }

    public SingleLiveEvent<Resource<AllsiteInfoDataModel>> getSiteInfoDataModel() {
        return siteInfoDataModel;
    }

    public SingleLiveEvent<Resource<DepartmentDataModel>> getDepartmentDataModel() {
        return departmentDataModel;
    }

    public SingleLiveEvent<Resource<AddAttachmentModel>> getAddAttachmentModel() {
        return addAttachmentModel;
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

    public SingleLiveEvent<Resource<PowerFuelAllDataModel>> getPowerFuelModel() {
        return powerFuelModel;
    }


    public SingleLiveEvent<Resource<TaskModelData>> getTaskResponse() {
        return taskResponse;
    }

    public SingleLiveEvent<Resource<ServiceRequestAllData>> getServiceRequest() {
        return serRequestData;
    }

    public void updateData(BasicinfoModel basicinfoModel) {
        BasicInfoDialougeResponse d = (basicInfoUpdate.getValue() != null) ? basicInfoUpdate.getValue().data : null;
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

    public void addAttachmentData(AddAttachmentModel model) {

//        MultipartBody.Builder builder = new MultipartBody.Builder()
//                .addFormDataPart("sourceSchemaName",model.getSourceSchemaName())
//                .addFormDataPart("detail",model.getDetail())
//                .addFormDataPart("title",model.getTitle());
//        if (model.getId()!=null) {
//            builder.addFormDataPart("id",model.getId());
//        }
//        RequestBody filePart = RequestBody.create(MediaType.parse("image/*"), model.getFile());
//        MultipartBody.Part file = MultipartBody.Part.createFormData("file", model.getFile().getName(), filePart);

        File imageFile = new File(model.getFile());
        final Map<String, RequestBody> map = new HashMap<>();
        try {
            RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
            map.put("file\"; filename=\"" + imageFile.getName() + "\"", fileBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("sourceSchemaName", RequestBody.create(MediaType.parse("multipart/form-data"), model.getSourceSchemaName()));
        map.put("sourceSchemaId", RequestBody.create(MediaType.parse("multipart/form-data"), model.getSourceSchemaId()));
        map.put("detail", RequestBody.create(MediaType.parse("multipart/form-data"), model.getDetail()));
        map.put("title", RequestBody.create(MediaType.parse("multipart/form-data"), model.getTitle()));
        map.put("type", RequestBody.create(MediaType.parse("multipart/form-data"), model.getType()));
        map.put("category", RequestBody.create(MediaType.parse("multipart/form-data"), model.getCategory().toString()));
        map.put("siteId", RequestBody.create(MediaType.parse("multipart/form-data"), model.getSiteId().toString()));
        map.put("locLatitude", RequestBody.create(MediaType.parse("multipart/form-data"), model.getLocLatitude()));
        map.put("locLongitude", RequestBody.create(MediaType.parse("multipart/form-data"), model.getLocLongitude()));
        map.put("place", RequestBody.create(MediaType.parse("multipart/form-data"), model.getPlace()));
        AppLogger.INSTANCE.log("attachment model data=====>"+map);
        apiClient.addAttachmentData("http://49.50.77.81:8126/fms/createupdate", map).enqueue(new Callback<List<AddAttachmentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<AddAttachmentModel>> call, Response<List<AddAttachmentModel>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    reportSuccessResponse(response.body().get(0));
                    AppLogger.INSTANCE.log("addAttachmentData:" + response.body().size());
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<AddAttachmentModel>> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
                AppLogger.INSTANCE.log("addAttachmentData onFailure:" + t.getLocalizedMessage());
            }

            private void reportSuccessResponse(AddAttachmentModel response) {

                if (response != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
                    addAttachmentModel.postValue(Resource.success(response, 200));
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
        GenerateSiteIdResponse d = (generateSiteIdResponse.getValue() != null) ? generateSiteIdResponse.getValue().data : null;
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

    public void closeTask(String taskId,String remark) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Approve",taskId);
        jsonObject.addProperty("status","Closed");
        jsonObject.addProperty("remark",remark);
        jsonObject.addProperty("ownername",AppController.getInstance().ownerName);
        if (!Utils.INSTANCE.isNetworkConnected()){
            try {
                AppPreferences.getInstance().saveTaskOfflineApi(new Gson().toJson(jsonObject), APIInterceptor.DYNAMIC_BASE_URL+ EndPoints.WORKFLOW_DATA_URL,"closeTask"+taskId);
                CloseTaskModel model = new CloseTaskModel("","Data updated");
                closeTaskModel.postValue(Resource.success(model, 200));
            }catch (Exception e){
                e.printStackTrace();
                AppLogger.INSTANCE.log("e:"+e.getLocalizedMessage());
            }
            return;
        }

        apiClient.closeTask(jsonObject).enqueue(new Callback<CloseTaskModel>() {
            @Override
            public void onResponse(@NonNull Call<CloseTaskModel> call, Response<CloseTaskModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CloseTaskModel> call, @NonNull Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<CloseTaskModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.toString());
                    closeTaskModel.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    closeTaskModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    closeTaskModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }
    public void reopenTask(String taskId,String remark) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Approve",taskId);
        jsonObject.addProperty("status","Idle");
        jsonObject.addProperty("remark",remark);
        jsonObject.addProperty("ownername",AppController.getInstance().ownerName);
        if (!Utils.INSTANCE.isNetworkConnected()){
            try {
                AppPreferences.getInstance().saveTaskOfflineApi(new Gson().toJson(jsonObject), APIInterceptor.DYNAMIC_BASE_URL+ EndPoints.WORKFLOW_DATA_URL,"reOpenTask"+taskId);
                CloseTaskModel model = new CloseTaskModel("","Data updated");
                closeTaskModel.postValue(Resource.success(model, 200));
            }catch (Exception e){
                e.printStackTrace();
                AppLogger.INSTANCE.log("e:"+e.getLocalizedMessage());
            }
            return;
        }
        apiClient.closeTask(jsonObject).enqueue(new Callback<CloseTaskModel>() {
            @Override
            public void onResponse(@NonNull Call<CloseTaskModel> call, Response<CloseTaskModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CloseTaskModel> call, @NonNull Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<CloseTaskModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.toString());
                    closeTaskModel.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    closeTaskModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    closeTaskModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }
    public void updateSiteInfo(BasicinfoModel basicinfoModel) {
        BasicInfoDialougeResponse d = (basicInfoUpdate.getValue() != null) ? basicInfoUpdate.getValue().data : null;
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
        BasicInfoDialougeResponse d = (basicInfoUpdate.getValue() != null) ? basicInfoUpdate.getValue().data : null;
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
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Get", "all");
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        apiClient.getNotification(jsonObject).enqueue(new Callback<NotificationNew>() {
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

    public void getUserData() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("getuser", "All");
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        apiClient.getUsersData(jsonObject).enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserDataResponse> call, Response<UserDataResponse> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserDataResponse> call, @NonNull Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<UserDataResponse> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.body().toString());
                    userDataResponse.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    userDataResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    userDataResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void addNotification(AddNotificationModel data) {
        AppLogger.INSTANCE.log("add notification data===>" + data);
        apiClient.addNotification(data).enqueue(new Callback<AddNotificationResponse>() {
            @Override
            public void onResponse(@NonNull Call<AddNotificationResponse> call, Response<AddNotificationResponse> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AddNotificationResponse> call, @NonNull Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<AddNotificationResponse> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.body().toString());
                    addNotificationResponse.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    addNotificationResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    addNotificationResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void fetchHomeData() {
        if (!Utils.INSTANCE.isNetworkConnected()){
            try {
                String data = AppPreferences.getInstance().getString("fetchHomeData");
                if (data!=null && !data.isEmpty()) {
                    HomeResponse model = new Gson().fromJson(data, HomeResponse.class);
                    if (model != null)
                        homeResponse.postValue(Resource.success(model, 200));
                }
            }catch (Exception e){
                e.printStackTrace();
                AppLogger.INSTANCE.log("e:"+e.getLocalizedMessage());
            }
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        jsonObject.addProperty("homepage", "");
        if (AppController.getInstance().profileData!=null){
            jsonObject.addProperty("National", AppController.getInstance().profileData.getNational());

            if (AppController.getInstance().profileData.getRegion()!=null && !AppController.getInstance().profileData.getRegion().isEmpty())
                jsonObject.addProperty("Region", AppController.getInstance().profileData.getRegion().get(0));
            else jsonObject.addProperty("Region", "");

            if (AppController.getInstance().profileData.getState()!=null && !AppController.getInstance().profileData.getState().isEmpty())
                jsonObject.addProperty("State", AppController.getInstance().profileData.getState().get(0));
            else jsonObject.addProperty("State", "");
            jsonObject.addProperty("Cluster", "");
            if (AppController.getInstance().profileData.getMaintenancepoint()!=null && !AppController.getInstance().profileData.getMaintenancepoint().isEmpty())
                jsonObject.addProperty("MaintenancePoint", AppController.getInstance().profileData.getMaintenancepoint().get(0));
            else jsonObject.addProperty("MaintenancePoint", "");
            jsonObject.addProperty("Area", "");
            jsonObject.addProperty("Objectname", "");
            jsonObject.addProperty("geolevel", "national");
        }else{
            jsonObject.addProperty("National", "INDIA");
            jsonObject.addProperty("Region", "");
            jsonObject.addProperty("State", "");
            jsonObject.addProperty("MaintenancePoint", "");
            jsonObject.addProperty("Area", "");
            jsonObject.addProperty("Objectname", "");
            jsonObject.addProperty("geolevel", "national");
        }

        apiClient.fetchHomeData(jsonObject).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<HomeResponse> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
                    homeResponse.postValue(Resource.success(response.body(), 200));
                    String json = new Gson().toJson(response.body());
                    AppPreferences.getInstance().saveString("fetchHomeData",json);
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
        jsonObject.addProperty("Getprocess", "all");
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        apiClient.fetchProjectData(jsonObject).enqueue(new Callback<ProjectModelData>() {
            @Override
            public void onResponse(Call<ProjectModelData> call, Response<ProjectModelData> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<ProjectModelData> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<ProjectModelData> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
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
        jsonObject.addProperty("Gettemplate", templateName);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        if (!Utils.INSTANCE.isNetworkConnected()){
            try {
                String data = AppPreferences.getInstance().getString("fetchTaskData"+templateName);
                if (data!=null && !data.isEmpty()) {
                    TaskModelData model = new Gson().fromJson(data, TaskModelData.class);
                    if (model != null)
                        taskResponse.postValue(Resource.success(model, 200));
                }
            }catch (Exception e){
                e.printStackTrace();
                AppLogger.INSTANCE.log("e:"+e.getLocalizedMessage());
            }
            return;
        }
        apiClient.fetchTaskData(jsonObject).enqueue(new Callback<TaskModelData>() {
            @Override
            public void onResponse(@NonNull Call<TaskModelData> call, @NonNull Response<TaskModelData> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TaskModelData> call, @NonNull Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<TaskModelData> response) {

                if (response.body() != null) {
                    AppPreferences.getInstance().saveString("fetchTaskData"+templateName,new Gson().toJson(response.body()));
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
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
        if (!Utils.INSTANCE.isNetworkConnected()){
            try {
                String data = AppPreferences.getInstance().getString("fetchServiceRequestData"+id);
                if (data!=null && !data.isEmpty()) {
                    ServiceRequestAllData model = new Gson().fromJson(data, ServiceRequestAllData.class);
                    if (model != null)
                        serRequestData.postValue(Resource.success(model, 200));
                }
            }catch (Exception e){
                e.printStackTrace();
                AppLogger.INSTANCE.log("e:"+e.getLocalizedMessage());
            }
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        Call<ServiceRequestAllData> request = apiClient.fetchsServiceRequestData(jsonObject);
        request.enqueue(new Callback<ServiceRequestAllData>() {
            @Override
            public void onResponse(@NonNull Call<ServiceRequestAllData> call, @NonNull Response<ServiceRequestAllData> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServiceRequestAllData> call, @NonNull Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<ServiceRequestAllData> response) {

                if (response.body() != null) {
                    AppPreferences.getInstance().saveString("fetchServiceRequestData"+id,new Gson().toJson(response.body()));

                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
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
        opcoDataResponse.postValue(Resource.loading((opcoDataResponse.getValue() != null) ? opcoDataResponse.getValue().data : null, 200));
        siteInfoResponse.postValue(Resource.loading((siteInfoResponse.getValue() != null) ? siteInfoResponse.getValue().data : null, 200));
        if (!Utils.INSTANCE.isNetworkConnected()){
            try {
                String data = AppPreferences.getInstance().getString("siteInfoById"+id);
                if (data!=null && !data.isEmpty()) {
                    SiteInfoModel model = new Gson().fromJson(data, SiteInfoModel.class);
                    if (model != null)
                        siteInfoResponse.postValue(Resource.success(model, 200));
                }
            }catch (Exception e){
                e.printStackTrace();
                AppLogger.INSTANCE.log("e:"+e.getLocalizedMessage());
            }
            return;
        }
        apiClient.fetchSiteInfoById(new IdData(id, AppController.getInstance().ownerName)).enqueue(new Callback<SiteInfoModel>() {
            @Override
            public void onResponse(Call<SiteInfoModel> call, Response<SiteInfoModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<SiteInfoModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SiteInfoModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
                    SiteInfoModel data = response.body();
                    AppPreferences.getInstance().saveString("siteInfoById"+id,new Gson().toJson(response.body()));
                    siteInfoResponse.postValue(Resource.success(response.body(), 200));
//                   data update
                    if (data.getItem() != null && !data.getItem().isEmpty()) {
                        opcoDataResponse.postValue(Resource.success(new OpcoDataList(data.getItem().get(0).getOperator()), 200));
                        serviceRequestAllData.postValue(Resource.success(data.getItem().get(0).getServiceRequestMain(), 200));
                        AppLogger.INSTANCE.log("Service request Fragment data error :" + data.getItem().get(0).getServiceRequestMain());

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

    public void SiteInfoRequestAll(String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        jsonObject.add("Basicinfo", new JsonObject());
        jsonObject.add("OperationalInfo", new JsonObject());
        jsonObject.add("GeoCondition", new JsonObject());
        jsonObject.add("SafetyAndAccess", new JsonObject());
        jsonObject.add("Siteaddress", new JsonObject());
        AllsiteInfoDataModel siteModel = (siteInfoDataModel != null && siteInfoDataModel.getValue() != null) ? siteInfoDataModel.getValue().data : null;
        siteInfoDataModel.postValue(Resource.loading(siteModel, 200));
        apiClient.fetchSiteInfo(jsonObject).enqueue(new Callback<AllsiteInfoDataModel>() {
            @Override
            public void onResponse(Call<AllsiteInfoDataModel> call, Response<AllsiteInfoDataModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("SiteInfoRequestAll error :" + response.errorBody());
                } else {
                    AppLogger.INSTANCE.log("SiteInfoRequestAll error :" + response);
                }
            }

            @Override
            public void onFailure(Call<AllsiteInfoDataModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<AllsiteInfoDataModel> response) {

                if (response.body() != null) {
                    AllsiteInfoDataModel data = response.body();
                    AppController.getInstance().newSiteInfoModel = data;
                    AppLogger.INSTANCE.log("SiteInfoRequestAll reportSuccessResponse :" + response);
                    siteInfoDataModel.postValue(Resource.success(response.body(), 200));
                }
                else
                    AppLogger.INSTANCE.log("SiteInfoRequestAll reportSuccessResponse : null");

            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                AppLogger.INSTANCE.log("SiteInfoRequestAll reportSuccessResponse error :" + iThrowableLocalMessage);
                if (iThrowableLocalMessage != null)
                    siteInfoDataModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    siteInfoDataModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void AttachmentsConditionsRequestAll() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Attachment", new JsonObject());
        apiClient.fetchAttachmentConditions(jsonObject).enqueue(new Callback<AttachmentConditionsDataModel>() {
            @Override
            public void onResponse(Call<AttachmentConditionsDataModel> call, Response<AttachmentConditionsDataModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("AttachmentsConditionsRequestAll error :" + response.errorBody());
                } else {
                    AppLogger.INSTANCE.log("AttachmentsConditionsRequestAll error :" + response);
                }
            }

            @Override
            public void onFailure(Call<AttachmentConditionsDataModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<AttachmentConditionsDataModel> response) {

                if (response.body() != null) {
                    AppController.getInstance().attachmentsConditionsList=response.body();
                    AppLogger.INSTANCE.log("AttachmentsConditionsRequestAll reportSuccessResponse :" + response);
                    attachmentConsitionsModel.postValue(Resource.success(response.body(), 200));
                }
                else
                    AppLogger.INSTANCE.log("AttachmentsConditionsRequestAll reportSuccessResponse : null");

            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                AppLogger.INSTANCE.log("AttachmentsConditionsRequestAll reportSuccessResponse error :" + iThrowableLocalMessage);
                if (iThrowableLocalMessage != null)
                    attachmentConsitionsModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    attachmentConsitionsModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void siteAcquisitionSurveyById(String id) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        jsonObject.add("SAcqSiteAcquisition", jsonArray);
        apiClient.fetchAcquisitionSurveyAllDataItemRequest(jsonObject).enqueue(new Callback<AcquisitionSurveyAllDataItem>() {
            @Override
            public void onResponse(Call<AcquisitionSurveyAllDataItem> call, Response<AcquisitionSurveyAllDataItem> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getSAcqSiteAcquisition() != null &&
                        !response.body().getSAcqSiteAcquisition().isEmpty()) {
                    reportSuccessResponse(response.body().getSAcqSiteAcquisition().get(0));
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<AcquisitionSurveyAllDataItem> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(AcquisitionSurveyModel response) {

                if (response != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
                    acquisitionSurveyAllDataItem.postValue(Resource.success(response, 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    acquisitionSurveyAllDataItem.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    acquisitionSurveyAllDataItem.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void serviceRequestAll(String id) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        jsonObject.add("ServiceRequestMain", jsonArray);
        AppLogger.INSTANCE.log("serviceRequestAll===>"+jsonObject);
        apiClient.fetchServiceRequest(jsonObject).enqueue(new Callback<ServiceRequestModel>() {
            @Override
            public void onResponse(Call<ServiceRequestModel> call, Response<ServiceRequestModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<ServiceRequestModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<ServiceRequestModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
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
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        jsonObject.add("Operator", new JsonObject());
        apiClient.fetchOpcoInfoRequest(jsonObject).enqueue(new Callback<OpcoTenencyAllDataModel>() {
            @Override
            public void onResponse(Call<OpcoTenencyAllDataModel> call, Response<OpcoTenencyAllDataModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<OpcoTenencyAllDataModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<OpcoTenencyAllDataModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
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
//        ArrayList<String> list = new ArrayList<>();
//        list.add("PlanningAndDesign");
//        SiteInfoParam siteInfoParam = new SiteInfoParam(list,Integer.parseInt(id),AppController.getInstance().ownerName);
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        jsonObject.add("PlanningAndDesign", jsonArray);

        apiClient.fetchPlanDesignRequest(jsonObject).enqueue(new Callback<PlanAndDesignModel>() {
            @Override
            public void onResponse(Call<PlanAndDesignModel> call, Response<PlanAndDesignModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<PlanAndDesignModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<PlanAndDesignModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
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
        SiteInfoParam siteInfoParam = new SiteInfoParam(list, Integer.parseInt(id), AppController.getInstance().ownerName);
        apiClient.fetchQatRequest(siteInfoParam).enqueue(new Callback<QatModel>() {
            @Override
            public void onResponse(Call<QatModel> call, Response<QatModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<QatModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<QatModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse on qatRequestAll :" + response + "===>:" + response.body());
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

    public void qatMainRequestAll(String id) {
        if (!Utils.INSTANCE.isNetworkConnected(AppController.getInstance())) {
            String cache_data = AppPreferences.getInstance().getString("qatMainRequestAll" + id);
            if (cache_data != null && !cache_data.isEmpty()) {
                try {
                    QatMainModel cacheobject = new Gson().fromJson(cache_data, QatMainModel.class);
                    if (cacheobject != null) {
                        qatMainModelResponse.postValue(Resource.success(cacheobject, 200));
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
            qatMainModelResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            return;
        }

        ArrayList<String> list = new ArrayList<>();
        list.add("QATMainLaunch");
        SiteInfoParam siteInfoParam = new SiteInfoParam(list, Integer.parseInt(id), AppController.getInstance().ownerName);
        apiClient.fetchQatMainRequest(siteInfoParam).enqueue(new Callback<QatMainModel>() {
            @Override
            public void onResponse(Call<QatMainModel> call, Response<QatMainModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<QatMainModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<QatMainModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
                    qatMainModelResponse.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    qatMainModelResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    qatMainModelResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void qatMainRequestAll(QalLaunchModel data) {
        apiClient.fetchQatMainRequest(data).enqueue(new Callback<QatUpdateModel>() {
            @Override
            public void onResponse(Call<QatUpdateModel> call, Response<QatUpdateModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<QatUpdateModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<QatUpdateModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
                    qatUpdateModel.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    qatUpdateModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    qatUpdateModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void qatMainRequestAll(QatPunchPointModel data) {
        apiClient.fetchQatMainRequest(data).enqueue(new Callback<QatMainModel>() {
            @Override
            public void onResponse(Call<QatMainModel> call, Response<QatMainModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<QatMainModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<QatMainModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
                    qatMainModelResponse.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    qatMainModelResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    qatMainModelResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void saveQatPunchPointRequestAll(SaveCheckpointModel data) {
        apiClient.saveQatPunchPointRequest(data).enqueue(new Callback<QatMainModel>() {
            @Override
            public void onResponse(Call<QatMainModel> call, Response<QatMainModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<QatMainModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<QatMainModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
                    qatMainModelResponse.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    qatMainModelResponse.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    qatMainModelResponse.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void siteAgreementRequestAll(String id) {
        if (!Utils.INSTANCE.isNetworkConnected(AppController.getInstance())) {
            String cache_data = AppPreferences.getInstance().getString("siteAgreementRequestAll" + id);
            if (cache_data != null && !cache_data.isEmpty()) {
                try {
                    SiteAcquisitionAllDataModel cacheobject = new Gson().fromJson(cache_data, SiteAcquisitionAllDataModel.class);
                    if (cacheobject != null) {
                        siteAgreementModel.postValue(Resource.success(cacheobject, 200));
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
            siteAgreementModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        jsonObject.add("SAcqSiteAcquisition", new JsonArray());
        apiClient.fetchSiteAgreementModelRequest(jsonObject).enqueue(new Callback<SiteAcquisitionAllDataModel>() {
            @Override
            public void onResponse(Call<SiteAcquisitionAllDataModel> call, Response<SiteAcquisitionAllDataModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<SiteAcquisitionAllDataModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());


            }

            private void reportSuccessResponse(Response<SiteAcquisitionAllDataModel> response) {

                if (response.body() != null) {
                    String data_json = new Gson().toJson(response.body());
                    AppPreferences.getInstance().saveString("siteAgreementRequestAll" + id, data_json);

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

    public void sstSbcRequestAll(String id) {
        if (!Utils.INSTANCE.isNetworkConnected(AppController.getInstance())) {
            String cache_data = AppPreferences.getInstance().getString("sstSbcRequestAll" + id);
            if (cache_data != null && !cache_data.isEmpty()) {
                try {
                    SiteAcquisitionAllDataModel cacheobject = new Gson().fromJson(cache_data, SiteAcquisitionAllDataModel.class);
                    if (cacheobject != null) {
                        siteAgreementModel.postValue(Resource.success(cacheobject, 200));
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
            siteAgreementModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        jsonObject.add("SstSbc", new JsonArray());
        apiClient.fetchSstSbcModelRequest(jsonObject).enqueue(new Callback<SstSbcAllDataModel>() {
            @Override
            public void onResponse(Call<SstSbcAllDataModel> call, Response<SstSbcAllDataModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<SstSbcAllDataModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());


            }

            private void reportSuccessResponse(Response<SstSbcAllDataModel> response) {

                if (response.body() != null) {
                    String data_json = new Gson().toJson(response.body());
                    AppPreferences.getInstance().saveString("siteAgreementRequestAll" + id, data_json);

                    sstSbcModel.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    sstSbcModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    sstSbcModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });


    }
    public void RfRequestAll(String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        jsonObject.add("RfSurvey", new JsonArray());
        apiClient.fetchRfSurveyRequest(jsonObject).enqueue(new Callback<RfMainResponse>() {
            @Override
            public void onResponse(Call<RfMainResponse> call, Response<RfMainResponse> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<RfMainResponse> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());


            }

            private void reportSuccessResponse(Response<RfMainResponse> response) {

                if (response.body() != null) {
                    String data_json = new Gson().toJson(response.body());
                    AppPreferences.getInstance().saveString("siteAgreementRequestAll" + id, data_json);

                    rfResponesLivedata.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(String iThrowableLocalMessage) {
                if (iThrowableLocalMessage != null)
                    rfResponesLivedata.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    rfResponesLivedata.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });


    }

    public void utilitiEquipRequestAll(String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        jsonObject.add("UtilityEquipment", new JsonArray());
        AppLogger.INSTANCE.log("id :" + id);
        apiClient.fetchUtilitiesEquipRequest(jsonObject).enqueue(new Callback<UtilityEquipmentAllDataModel>() {
            @Override
            public void onResponse(Call<UtilityEquipmentAllDataModel> call, Response<UtilityEquipmentAllDataModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<UtilityEquipmentAllDataModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<UtilityEquipmentAllDataModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
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

        if (!Utils.INSTANCE.isNetworkConnected()){
            String value = AppPreferences.getInstance().getString("NocAndCompRequestAll"+id);
            AppLogger.INSTANCE.log("NocComp api data in offline mode===>:"+value);

            if (value!=null && !value.isEmpty()){
                try {
                    NocCompAllDataModel alldata= new Gson().fromJson(value,NocCompAllDataModel.class);
                    if (alldata!=null){
                        noCandCompModel.postValue(Resource.success(alldata, 200));
                    }
                }catch (Exception e){
                    AppLogger.INSTANCE.log(e.getLocalizedMessage());
                }
            }
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        jsonObject.add("NOCCompliance", new JsonArray());
        apiClient.fetchNocAndCompRequest(jsonObject).enqueue(new Callback<NocCompAllDataModel>() {
            @Override
            public void onResponse(Call<NocCompAllDataModel> call, Response<NocCompAllDataModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<NocCompAllDataModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<NocCompAllDataModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
                    noCandCompModel.postValue(Resource.success(response.body(), 200));
                    String jsonStringData = new Gson().toJson(response.body());
                    AppPreferences.getInstance().saveString("NocAndCompRequestAll"+id, jsonStringData);

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
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        jsonObject.add("TowerAndCivilInfra", new JsonArray());
        apiClient.fetchTowerCivilInfraRequest(jsonObject).enqueue(new Callback<TowerCivilAllDataModel>() {
            @Override
            public void onResponse(Call<TowerCivilAllDataModel> call, Response<TowerCivilAllDataModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<TowerCivilAllDataModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<TowerCivilAllDataModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
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
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        jsonObject.add("PowerAndFuel", new JsonArray());

        apiClient.fetchPowerFuelRequest(jsonObject).enqueue(new Callback<PowerFuelAllDataModel>() {
            @Override
            public void onResponse(Call<PowerFuelAllDataModel> call, Response<PowerFuelAllDataModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<PowerFuelAllDataModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<PowerFuelAllDataModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response);
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
        SiteInfoParam siteInfoParam = new SiteInfoParam(list, Integer.parseInt(id), AppController.getInstance().ownerName);
        apiClient.fetchLogData(siteInfoParam).enqueue(new Callback<LogsDataModel>() {
            @Override
            public void onResponse(Call<LogsDataModel> call, Response<LogsDataModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<LogsDataModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<LogsDataModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse on get logs :" + response);
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

    public void UpdateLogData(PostLogData data) {
        apiClient.postLogData(data).enqueue(new Callback<LogsDataModel>() {
            @Override
            public void onResponse(Call<LogsDataModel> call, Response<LogsDataModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<LogsDataModel> call, Throwable t) {
                reportErrorResponse(t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<LogsDataModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse on post logs :" + response);
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

    public void searchSiteAll(String category, String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(category, id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        if (category.equalsIgnoreCase("name"))
            apiClient.searchSiteByName(jsonObject).enqueue(new Callback<List<SearchListItem>>() {
                @Override
                public void onResponse(Call<List<SearchListItem>> call, Response<List<SearchListItem>> response) {
                    if (response.isSuccessful()) {
                        reportSuccessResponse(response);
                    } else {
                        AppLogger.INSTANCE.log("error :" + response);
                    }
                }

                @Override
                public void onFailure(Call<List<SearchListItem>> call, Throwable t) {
                    reportErrorResponse(null, t.getLocalizedMessage());
                }

                private void reportSuccessResponse(Response<List<SearchListItem>> response) {
                    if (response != null && response.body() != null) {
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
                    if (response.isSuccessful()) {
                        reportSuccessResponse(response);
                    } else {
                        AppLogger.INSTANCE.log("error :" + response);
                    }
                }

                @Override
                public void onFailure(Call<List<SearchSiteIdItem>> call, Throwable t) {
                    reportErrorResponse(null, t.getLocalizedMessage());
                }

                private void reportSuccessResponse(Response<List<SearchSiteIdItem>> response) {
                    if (response != null && response.body() != null) {
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
                    if (response.isSuccessful()) {
                        reportSuccessResponse(response);
                    } else {
                        AppLogger.INSTANCE.log("error :" + response);
                    }
                }

                @Override
                public void onFailure(Call<List<SearchSiteNameItem>> call, Throwable t) {
                    reportErrorResponse(null, t.getLocalizedMessage());
                }

                private void reportSuccessResponse(Response<List<SearchSiteNameItem>> response) {
                    if (response != null && response.body() != null) {
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
                    if (response.isSuccessful()) {
                        reportSuccessResponse(response);
                    } else {
                        AppLogger.INSTANCE.log("error :" + response);
                    }
                }

                @Override
                public void onFailure(Call<List<SearchAliasNameItem>> call, Throwable t) {
                    reportErrorResponse(null, t.getLocalizedMessage());
                }

                private void reportSuccessResponse(Response<List<SearchAliasNameItem>> response) {
                    if (response != null && response.body() != null) {
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
                    if (response.isSuccessful()) {
                        reportSuccessResponse(response);
                    } else {
                        AppLogger.INSTANCE.log("error :" + response);
                    }
                }

                @Override
                public void onFailure(Call<List<SearchSiteOpcoName>> call, Throwable t) {
                    reportErrorResponse(null, t.getLocalizedMessage());
                }

                private void reportSuccessResponse(Response<List<SearchSiteOpcoName>> response) {
                    if (response != null && response.body() != null) {
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
                    if (response.isSuccessful()) {
                        reportSuccessResponse(response);
                    } else {
                        AppLogger.INSTANCE.log("error :" + response);
                    }
                }

                @Override
                public void onFailure(Call<List<SearchSiteOpcoSiteId>> call, Throwable t) {
                    reportErrorResponse(null, t.getLocalizedMessage());
                }

                private void reportSuccessResponse(Response<List<SearchSiteOpcoSiteId>> response) {
                    if (response != null && response.body() != null) {
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
        AppLogger.INSTANCE.log("Dropdown Api called");
        apiClient.siteInfoDropDown().enqueue(new Callback<SiteInfoDropDownData>() {
            @Override
            public void onResponse(Call<SiteInfoDropDownData> call, Response<SiteInfoDropDownData> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<SiteInfoDropDownData> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SiteInfoDropDownData> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.toString());
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
//        getDepartmentRequest();
        AttachmentsConditionsRequestAll();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        apiClient.siteInfoDropDownNew(jsonObject).enqueue(new Callback<DropDownNew>() {
            @Override
            public void onResponse(Call<DropDownNew> call, Response<DropDownNew> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<DropDownNew> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<DropDownNew> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.toString());
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
        jsonObject.addProperty("gettaskdata", id);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        if (!Utils.INSTANCE.isNetworkConnected()){
            String value = AppPreferences.getInstance().getString("TaskDetailsData"+id);
            AppLogger.INSTANCE.log("task details in offline mode===>:"+value);
            if (value!=null && !value.isEmpty()){
                try {
                    TaskDataList alldata= new Gson().fromJson(value,TaskDataList.class);
                    if (alldata!=null){
                        taskDataList.postValue(Resource.success(alldata, 200));
                    }
                }catch (Exception e){
                    AppLogger.INSTANCE.log(e.getLocalizedMessage());
                }
            }
            return;
        }
        apiClient.getTaskDataById(jsonObject).enqueue(new Callback<TaskDataList>() {
            @Override
            public void onResponse(Call<TaskDataList> call, Response<TaskDataList> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<TaskDataList> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<TaskDataList> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :" + response.toString());
//                    Logger.getLogger("ProfileRepo").warning(response.toString());
                    String jssonData=new Gson().toJson(response.body());
                    AppPreferences.getInstance().saveString("TaskDetailsData"+id,jssonData);
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

    public void getDepartmentRequest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Dropdown", true);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        apiClient.getDepartmentWithId(jsonObject).enqueue(new Callback<DepartmentDataModel>() {
            @Override
            public void onResponse(Call<DepartmentDataModel> call, Response<DepartmentDataModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<DepartmentDataModel> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<DepartmentDataModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("getDepartmentRequest reportSuccessResponse :" + response.toString());
                    DropDownNewItem data=new DropDownNewItem(response.body().getDepartment().getData(), DropDowns.Deaprtments.name(),true);
                    AppPreferences.getInstance().saveDropDownData(data);
                    AppLogger.INSTANCE.log("DepartmentDataSaved :" + new Gson().toJson(AppPreferences.getInstance().getDropDown(DropDowns.Deaprtments.name())));
                    departmentDataModel.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    departmentDataModel.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    departmentDataModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    departmentDataModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void getDepartmentWithGeographyRequest(String data) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray=new JsonArray();
        jsonArray.add(data);
        jsonObject.addProperty("Dropdownfilter", true);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        jsonObject.add("metadata",jsonArray);
        apiClient.getDepartmentWithId(jsonObject).enqueue(new Callback<DepartmentDataModel>() {
            @Override
            public void onResponse(Call<DepartmentDataModel> call, Response<DepartmentDataModel> response) {
                if (response.isSuccessful()) {
                    reportSuccessResponse(response);
                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<DepartmentDataModel> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<DepartmentDataModel> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("getDepartmentRequest reportSuccessResponse :" + response.toString());
                    DropDownNewItem data=new DropDownNewItem(response.body().getDepartment().getData(), DropDowns.Deaprtments.name(),true);
                    AppPreferences.getInstance().saveDropDownData(data);
                    AppLogger.INSTANCE.log("DepartmentDataSaved :" + new Gson().toJson(AppPreferences.getInstance().getDropDown(DropDowns.Deaprtments.name())));
                    departmentDataModel.postValue(Resource.success(response.body(), 200));

                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    departmentDataModel.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    departmentDataModel.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    departmentDataModel.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }
}
