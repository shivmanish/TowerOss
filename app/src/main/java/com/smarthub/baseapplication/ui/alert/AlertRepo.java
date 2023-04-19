package com.smarthub.baseapplication.ui.alert;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.smarthub.baseapplication.helpers.AppPreferences;
import com.smarthub.baseapplication.helpers.Resource;
import com.smarthub.baseapplication.helpers.SingleLiveEvent;
import com.smarthub.baseapplication.model.APIError;
import com.smarthub.baseapplication.model.register.dropdown.DepartmentDropdown;
import com.smarthub.baseapplication.model.register.dropdown.DropdownParam;
import com.smarthub.baseapplication.model.search.SearchAliasNameItem;
import com.smarthub.baseapplication.model.search.SearchList;
import com.smarthub.baseapplication.model.search.SearchListItem;
import com.smarthub.baseapplication.model.search.SearchSiteIdItem;
import com.smarthub.baseapplication.model.search.SearchSiteNameItem;
import com.smarthub.baseapplication.model.search.SearchSiteOpcoName;
import com.smarthub.baseapplication.model.search.SearchSiteOpcoSiteId;
import com.smarthub.baseapplication.network.APIClient;
import com.smarthub.baseapplication.ui.alert.model.UpdateAlertData;
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList;
import com.smarthub.baseapplication.ui.alert.model.request.SendAlertModel;
import com.smarthub.baseapplication.ui.alert.model.response.SendAlertResponse;
import com.smarthub.baseapplication.ui.alert.model.response.SendAlertResponseNew;
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponse;
import com.smarthub.baseapplication.utils.AppConstants;
import com.smarthub.baseapplication.utils.AppController;
import com.smarthub.baseapplication.utils.AppLogger;
import com.smarthub.baseapplication.utils.DropDowns;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlertRepo {

    private final APIClient apiClient;
    private static AlertRepo sInstance;
    private static final Object LOCK = new Object();
    private SingleLiveEvent<Resource<SearchList>> siteSearchResponse;
    private SingleLiveEvent<Resource<SendAlertResponse>> sendAlertResponseLivedata;
    private SingleLiveEvent<Resource<SendAlertResponseNew>> sendAlertResponseLivedataNew;
    private SingleLiveEvent<Resource<UserDataResponse>> userDataResponseLiveData;
    private SingleLiveEvent<Resource<DepartmentDropdown>> departmentDropDownData;

    public static AlertRepo getInstance(APIClient apiClient) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AlertRepo(apiClient);
            }
        }
        return sInstance;
    }

    public AlertRepo(APIClient apiClient) {
        this.apiClient = apiClient;
        sendAlertResponseLivedata = new SingleLiveEvent<>();
        userDataResponseLiveData = new SingleLiveEvent<>();
        departmentDropDownData = new SingleLiveEvent<>();
        siteSearchResponse = new SingleLiveEvent<>();
        sendAlertResponseLivedataNew = new SingleLiveEvent<>();

    }

    public SingleLiveEvent<Resource<SendAlertResponse>> getAlertResponseLiveData() {
        return sendAlertResponseLivedata;
    }

    public SingleLiveEvent<Resource<SendAlertResponseNew>> getAlertResponseLiveDataNew() {
        return sendAlertResponseLivedataNew;
    }

    public SingleLiveEvent<Resource<UserDataResponse>> getUserDataReponseLiveData() {
        return userDataResponseLiveData;
    }

    public SingleLiveEvent<Resource<DepartmentDropdown>> getDepartmentDropDownData() {
        return departmentDropDownData;
    }

    public SingleLiveEvent<Resource<SearchList>> getSiteSearchResponseData() {
        return siteSearchResponse;
    }

    public void sendAlertNew(SendAlertModel model) {

        apiClient.sendAlertNew(model).enqueue(new Callback<SendAlertResponseNew>() {
            @Override
            public void onResponse(Call<SendAlertResponseNew> call, Response<SendAlertResponseNew> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<SendAlertResponseNew> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SendAlertResponseNew> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
                    sendAlertResponseLivedataNew.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    sendAlertResponseLivedataNew.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    sendAlertResponseLivedataNew.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    sendAlertResponseLivedataNew.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }
    public void getAlertDetails(String model) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Get",model);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        apiClient.sendAlert(jsonObject).enqueue(new Callback<SendAlertResponse>() {
            @Override
            public void onResponse(Call<SendAlertResponse> call, Response<SendAlertResponse> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<SendAlertResponse> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SendAlertResponse> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+ response);
                    sendAlertResponseLivedata.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    sendAlertResponseLivedata.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    sendAlertResponseLivedata.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    sendAlertResponseLivedata.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }
    public void updateAlertDetails(UpdateAlertData model) {
        apiClient.updateAlert(model).enqueue(new Callback<SendAlertResponse>() {
            @Override
            public void onResponse(Call<SendAlertResponse> call, Response<SendAlertResponse> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<SendAlertResponse> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SendAlertResponse> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
                    sendAlertResponseLivedata.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    sendAlertResponseLivedata.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    sendAlertResponseLivedata.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    sendAlertResponseLivedata.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }
    public void sendSms(String id,String sms) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("chating",id);
        jsonObject.addProperty("message",sms);
        jsonObject.addProperty("ownername", AppController.getInstance().ownerName);
        apiClient.sendAlert(jsonObject).enqueue(new Callback<SendAlertResponse>() {
            @Override
            public void onResponse(Call<SendAlertResponse> call, Response<SendAlertResponse> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<SendAlertResponse> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<SendAlertResponse> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
                    sendAlertResponseLivedata.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    sendAlertResponseLivedata.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    sendAlertResponseLivedata.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    sendAlertResponseLivedata.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void getuser(GetUserList model) {

        apiClient.getuserlist(model).enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<UserDataResponse> response) {
                if (response.body() != null) {
                    AppLogger.INSTANCE.log("getuser with department reportSuccessResponse :"+response.toString());
                    AppPreferences.getInstance().saveString(DropDowns.AcquisitionExecutiveName.name(),new Gson().toJson(response.body()));
                    AppLogger.INSTANCE.log("departmentUsers is saved :"+AppPreferences.getInstance().getString(DropDowns.AcquisitionExecutiveName.name()));
                    userDataResponseLiveData.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    userDataResponseLiveData.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    userDataResponseLiveData.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    userDataResponseLiveData.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }
    public void getuserByWorkflow(GetUserList model) {

        apiClient.getuserByWorkflowlist(model).enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<UserDataResponse> response) {
                if (response.body() != null) {
                    AppLogger.INSTANCE.log("getuserByWorkflow with department reportSuccessResponse :"+response.toString());
                    AppPreferences.getInstance().saveString(DropDowns.AcquisitionExecutiveName.name(),new Gson().toJson(response.body()));
                    AppLogger.INSTANCE.log("departmentUsers is saved by getuserByWorkflow:"+AppPreferences.getInstance().getString(DropDowns.AcquisitionExecutiveName.name()));
                    userDataResponseLiveData.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    userDataResponseLiveData.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    userDataResponseLiveData.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    userDataResponseLiveData.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void getDepartments(DropdownParam model) {

        apiClient.getDepartmentList(model).enqueue(new Callback<DepartmentDropdown>() {
            @Override
            public void onResponse(Call<DepartmentDropdown> call, Response<DepartmentDropdown> response) {
                if (response.isSuccessful()){
                    reportSuccessResponse(response);
                } else if (response.errorBody()!=null){
                    AppLogger.INSTANCE.log("error :"+response);
                }else {
                    AppLogger.INSTANCE.log("error :"+response);
                }
            }

            @Override
            public void onFailure(Call<DepartmentDropdown> call, Throwable t) {
                reportErrorResponse(null, t.getLocalizedMessage());
            }

            private void reportSuccessResponse(Response<DepartmentDropdown> response) {

                if (response.body() != null) {
                    AppLogger.INSTANCE.log("reportSuccessResponse :"+response.toString());
                    departmentDropDownData.postValue(Resource.success(response.body(), 200));
                }
            }

            private void reportErrorResponse(APIError response, String iThrowableLocalMessage) {
                if (response != null) {
                    userDataResponseLiveData.postValue(Resource.error(response.getMessage(), null, 400));
                } else if (iThrowableLocalMessage != null)
                    departmentDropDownData.postValue(Resource.error(iThrowableLocalMessage, null, 500));
                else
                    departmentDropDownData.postValue(Resource.error(AppConstants.GENERIC_ERROR, null, 500));
            }
        });
    }

    public void searchSiteAll(String category,String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(category,id);
        jsonObject.addProperty("ownername",AppController.getInstance().ownerName);
        AppLogger.INSTANCE.log("category:"+category);
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



}
