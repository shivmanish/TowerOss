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
import com.smarthub.baseapplication.model.search.SearchSiteIdItem;
import com.smarthub.baseapplication.model.search.SearchSiteNameItem;
import com.smarthub.baseapplication.model.search.SearchSiteOpcoName;
import com.smarthub.baseapplication.model.search.SearchSiteOpcoSiteId;
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData;
import com.smarthub.baseapplication.model.serviceRequest.log.LogSearchData;
import com.smarthub.baseapplication.model.serviceRequest.new_site.GenerateSiteIdResponse;
import com.smarthub.baseapplication.model.siteInfo.OpcoDataList;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModelUpdate;
import com.smarthub.baseapplication.model.siteInfo.SiteInfoParam;
import com.smarthub.baseapplication.model.siteInfo.newData.SiteInfoModelNew;
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.NocAndCompModel;
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.newData.OpcoInfoNewModel;
import com.smarthub.baseapplication.model.siteInfo.oprationInfo.UpdateOperationInfo;
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignModel;
import com.smarthub.baseapplication.model.siteInfo.powerFuel.PowerAndFuelModel;
import com.smarthub.baseapplication.model.siteInfo.qat.QatModel;
import com.smarthub.baseapplication.model.siteInfo.service_request.ServiceRequestModel;
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteAgreementModel;
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteacquisitionAgreement;
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerCivilInfraModel;
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

public class SiteIboardUpdateRepo {

    private final APIClient apiClient;
    private static SiteIboardUpdateRepo sInstance;
    private static final Object LOCK = new Object();

    private SingleLiveEvent<Resource<BasicInfoDialougeResponse>> basicInfoUpdate;


    public static SiteIboardUpdateRepo getInstance(APIClient apiClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new SiteIboardUpdateRepo(apiClient);
            }
        }
        return sInstance;
    }



    public SingleLiveEvent<Resource<BasicInfoDialougeResponse>> getBasicInfoUpdate() {
        return basicInfoUpdate;
    }

    public SiteIboardUpdateRepo(APIClient apiClient) {
        this.apiClient = apiClient;
        basicInfoUpdate = new SingleLiveEvent<>();

    }






    public void updateOpcoTeency(BasicinfoModel basicinfoModel) {
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

}
