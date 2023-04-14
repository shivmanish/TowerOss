package com.smarthub.baseapplication.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.smarthub.baseapplication.model.dropdown.DropDownItem;
import com.smarthub.baseapplication.model.dropdown.newData.DropDownNew;
import com.smarthub.baseapplication.model.dropdown.newData.DropDownNewItem;
import com.smarthub.baseapplication.model.home.MyTeamTask;
import com.smarthub.baseapplication.model.home.MyTeamTaskModel;
import com.smarthub.baseapplication.model.search.SearchHistoryList;
import com.smarthub.baseapplication.model.taskModel.OfflineTaskList;
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel;
import com.smarthub.baseapplication.network.APIClient;
import com.smarthub.baseapplication.network.APIInterceptor;
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData;
import com.smarthub.baseapplication.utils.AppConstants;
import com.smarthub.baseapplication.utils.AppController;
import com.smarthub.baseapplication.utils.AppLogger;
import com.smarthub.baseapplication.utils.DropDowns;
import com.smarthub.baseapplication.utils.Utils;
import com.smarthub.baseapplication.widgets.CustomSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppPreferences {
public static String DROPDOWNDATA = "dropdowndata";
public static String DROPDOWNDATANEW = "dropdowndatanew";
    private static AppPreferences mInstance = null;
    private final SharedPreferences mPrefs;

    private AppPreferences() {
        mPrefs = AppController.getInstance().getSharedPreferences("TowerOss-Preferences", Context.MODE_PRIVATE);
    }

    public static synchronized AppPreferences getInstance() {
        if (mInstance == null) {
            mInstance = new AppPreferences();
        }
        return mInstance;
    }

//    public  HashMap<String, String> getTaskOfflineQueue(){
//        //get from shared prefs
//        String storedHashMapString = mPrefs.getString("hashString", "");
//        java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>(){}.getType();
//        HashMap<String, String> testHashMap2 = new HashMap<>();
//        try {
//            testHashMap2 = new Gson().fromJson(storedHashMapString, type);
//        }catch (Exception e){
//            AppLogger.INSTANCE.log("e :"+e.getLocalizedMessage());
//        }
//        return testHashMap2;
//    }
//
//    public  String getNextTaskOfflineQueue(){
//        //get from shared prefs
//        String storedHashMapString = mPrefs.getString("hashString", "");
//        java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>(){}.getType();
//        HashMap<String, String> testHashMap2 = new HashMap<>();
//        try {
//            testHashMap2 = new Gson().fromJson(storedHashMapString, type);
//        }catch (Exception e){
//            AppLogger.INSTANCE.log("e :"+e.getLocalizedMessage());
//        }
//        if (testHashMap2.size()>0){
//            return  (new ArrayList<>(testHashMap2.keySet())).get(0);
//        }
//        return "";
//    }
//
//    public void removeTaskOfflineQueue(String taskId){
//        String createdId = "getTaskOfflineQueue"+taskId;
//        //get from shared prefs
//        String storedHashMapString = mPrefs.getString("hashString", "");
//        java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>(){}.getType();
//        HashMap<String, String> testHashMap2 = new HashMap<>();
//        try {
//            testHashMap2 = new Gson().fromJson(storedHashMapString, type);
//        }catch (Exception e){
//            AppLogger.INSTANCE.log("e :"+e.getLocalizedMessage());
//        }
//        if (testHashMap2.containsKey(createdId)){
//            testHashMap2.remove(createdId);
//            saveHashMapData(testHashMap2);
//        }
//    }

//    public void addTaskOfflineQueue(String taskId,Context context){
//        String createdId = "getTaskOfflineQueue"+taskId;
//        //get from shared prefs
//        String storedHashMapString = mPrefs.getString("hashString", "");
//        java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>(){}.getType();
//        HashMap<String, String> testHashMap2 = new HashMap<>();
//        try {
//            testHashMap2 = new Gson().fromJson(storedHashMapString, type);
//        }catch (Exception e){
//            AppLogger.INSTANCE.log("e :"+e.getLocalizedMessage());
//        }
//        String uiJson = getTaskUiModelJson(taskId,context);
//        testHashMap2.put(createdId,uiJson);
//        saveHashMapData(testHashMap2);
//    }

    public OfflineTaskList getOfflineTaskList() {
        OfflineTaskList list = new OfflineTaskList();
        String listJson=getString("OfflineTask");
        try{
            list=new Gson().fromJson(listJson, OfflineTaskList.class);
        }catch (Exception e){
            e.printStackTrace();
            AppLogger.INSTANCE.log("getNextOfflineTask error:"+e.getLocalizedMessage());
        }
        if(list==null)
            list=new OfflineTaskList();
        return list;
    }

    public void saveTaskId(String task){
        saveString("savedTaskId",task);
    }

//    private OfflineTaskList saveOfflineTaskById() {
//        String task = getString("savedTaskId");
//        OfflineTaskList list = getOfflineTaskList();
//        if (!list.contains(task))
//            list.add(task);
//        saveOfflineTaskList(list);
//        return list;
//    }

    public String getNextOfflineTask() {
        OfflineTaskList list = new OfflineTaskList();
        String listJson=getString("OfflineTask");
        AppLogger.INSTANCE.log("getNextOfflineTask list size:"+list.size());
        try{
            list=new Gson().fromJson(listJson, OfflineTaskList.class);
        }catch (Exception e){
            e.printStackTrace();
            AppLogger.INSTANCE.log("getNextOfflineTask error:"+e.getLocalizedMessage());
        }
        if(list==null)
            list=new OfflineTaskList();
        if (list.size()>0)
            return list.get(0);
        return "";
    }

    public void saveOfflineTaskList(OfflineTaskList iValue) {
        String searchhistoryJson= new Gson().toJson(iValue);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("OfflineTask",searchhistoryJson);
        prefsEditor.apply();
    }

    public void saveTaskOfflineApi(String json,String url,String key){
//        String taskId = getString("savedTaskId");
        AppPreferences.getInstance().saveString(key,json);
//        AppPreferences.getInstance().saveString(key+"Task",taskId);
        AppPreferences.getInstance().saveString(key+"Url",url);
        OfflineTaskList list = getOfflineTaskList();
        if (!list.contains(key))
            list.add(key);
        AppLogger.INSTANCE.log("offline saved:"+list.size()+",for json"+json+",url:"+url+",key:"+key);
        offlineTask.postValue(list.size());
        saveOfflineTaskList(list);
    }

    void removeTaskStatus(String key){
//        String taskId = getString(key+"Task");
//        AppPreferences.getInstance().removeString(key+"Data");
//        AppPreferences.getInstance().removeString(key+"Task");
//        AppPreferences.getInstance().removeString(key+"Url");
        OfflineTaskList list = getOfflineTaskList();
        list.remove(key);
        saveOfflineTaskList(list);
    }

    public SingleLiveEvent<Integer> offlineTask = new SingleLiveEvent<>();
    public void callAPI(){
        APIClient apiClient = APIInterceptor.get();
        String key = getNextOfflineTask();
//        String taskId = getString(key+"Task");
//        AppLogger.INSTANCE.log("taskId:"+taskId+",key:"+key);
        if (key.isEmpty())
            return;
        String data = getString(key);
        String apiUrl = getString(key+"Url");
        AppLogger.INSTANCE.log("callAPI url:"+apiUrl);
        AppLogger.INSTANCE.log("callAPI data:"+data);
        JsonObject jsonObject = new Gson().fromJson(data,JsonObject.class);
        apiClient.updateOfflineData(apiUrl,jsonObject).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                removeTaskStatus(key);
                int size = getOfflineTaskList().size();
                offlineTask.postValue(size);
                callAPI();
                AppLogger.INSTANCE.log("next api call for key :"+key+",size:"+size);
                if (response.isSuccessful()) {

                } else if (response.errorBody() != null) {
                    AppLogger.INSTANCE.log("error :" + response);
                } else {
                    AppLogger.INSTANCE.log("error :" + response);
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                removeTaskStatus(key);
                int size = getOfflineTaskList().size();
                offlineTask.postValue(size);
                callAPI();
                AppLogger.INSTANCE.log("next api call for key:"+key+",size:"+size);
                t.printStackTrace();
                AppLogger.INSTANCE.log("callAPI key:"+key+" error:"+t.getLocalizedMessage());
            }
        });
    }

    void saveHashMapData(HashMap<String, String> testHashMap){
        Gson gson = new Gson();
        String hashMapString = gson.toJson(testHashMap);
        saveString("hashString",hashMapString);
    }

    public List<MyTeamTask> getMyTeamTask(){
        ArrayList<MyTeamTask> list = new ArrayList<>();
        String string = getString("myTaskHome");
        if (string!=null && !string.isEmpty()){
            try {
                MyTeamTaskModel model = new Gson().fromJson(string,MyTeamTaskModel.class);
                if (model!=null && model.size()>0)
                    list = model;
            }catch (java.lang.Exception e){
                AppLogger.INSTANCE.log("e:${e.localizedMessage}");
            }
        }
        AppLogger.INSTANCE.log("my team task cache list found:"+list.size());
        return list;
    }

    public void saveMyTeamTask(List<MyTeamTask> list){
        try{
            MyTeamTaskModel model = new MyTeamTaskModel();
            model.addAll(list);
            String json = new Gson().toJson(model);
            saveString("myTaskHome",json);
            String modelJson= new Gson().toJson(model);
            AppLogger.INSTANCE.log("saved myTeamTask json: "+modelJson);
        }catch (Exception e){
            AppLogger.INSTANCE.log("e:"+e.getLocalizedMessage());
        }

    }

    public void saveTaskUiModel(TaskDropDownModel model, String taskId){
        String modelJson= new Gson().toJson(model);
        AppLogger.INSTANCE.log("saved Task json: "+model);
        AppLogger.INSTANCE.log("saved Task json task Id: "+taskId);
        saveString("task_"+ taskId,modelJson);
        AppLogger.INSTANCE.log("saved Task json: "+modelJson);
    }

    public TaskDropDownModel getTaskUiModel(String taskId,Context context){
        TaskDropDownModel taskModel=null;
        String modelJson= Utils.INSTANCE.getJsonDataFromAsset(context,"task_drop_down.json");
        try{
            if (!getString("task_"+ taskId).isEmpty())
                modelJson=getString("task_"+ taskId);
            else
                saveString("task_"+taskId,modelJson);
            taskModel= new Gson().fromJson(modelJson,TaskDropDownModel.class);
        }catch (Exception e){
            AppLogger.INSTANCE.log("error in fetching task ui model on appPrefrence"+e.getLocalizedMessage());
        }
        return taskModel;
    }

    public String getTaskUiModelJson(String taskId,Context context){
        String modelJson= Utils.INSTANCE.getJsonDataFromAsset(context,"task_drop_down.json");
        try{
            if (!getString("task_"+ taskId).isEmpty())
                modelJson=getString("task_"+ taskId);
            else
                saveString("task_"+taskId,modelJson);
        }catch (Exception e){
            AppLogger.INSTANCE.log("error in fetching task ui model on appPrefrence"+e.getLocalizedMessage());
        }
        return modelJson;
    }

    public void saveBoolean(String iKey, boolean iValue) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putBoolean(iKey, iValue);
        prefsEditor.apply();
    }

    public void saveDropDownData(DropDownNew data) {
        AppPreferences.getInstance().saveString(DROPDOWNDATANEW, "fetched");
        for (DropDownNewItem item : data.getDropdown()){
            Gson gson = new Gson();
            String stringDatajson = gson.toJson(item);
            AppPreferences.getInstance().saveString(item.getName(), stringDatajson);
        }
    }
    public void saveStaticDropDownData(Context context) {
        String modelJson= Utils.INSTANCE.getJsonDataFromAsset(context,"dropdown.json");
        try{
            DropDownNew data= new Gson().fromJson(modelJson,DropDownNew.class);
            for (DropDownNewItem item : data.getDropdown()){
                Gson gson = new Gson();
                String stringDatajson = gson.toJson(item);
                AppPreferences.getInstance().saveString(item.getName(), stringDatajson);
            }
        }catch (Exception e){
            AppLogger.INSTANCE.log("error in fetching task ui model on appPrefrence"+e.getLocalizedMessage());
        }
    }

    public boolean isSavedDropDown(){
        String jsonString = getString(DROPDOWNDATANEW);
        return (jsonString!=null && !jsonString.isEmpty());
    }

    public SiteInfoDropDownData getDropDown(){
        String jsonString = getString(DROPDOWNDATA);
        Gson gson = new Gson();
        return gson.fromJson(jsonString,SiteInfoDropDownData.class);
    }

    public void setDropDown(CustomSpinner customSpinner,String name,String id){
        Gson gson = new Gson();
        String jsonString = getString(name);
        DropDownNewItem dropDownNewItem = gson.fromJson(jsonString,DropDownNewItem.class);
        customSpinner.setSpinnerData(dropDownNewItem.getData(),id);
    }
    public void setDropDown(CustomSpinner customSpinner,String name,String id,TextView customText){
        Gson gson = new Gson();
        String jsonString = getString(name);
        DropDownNewItem dropDownNewItem = gson.fromJson(jsonString,DropDownNewItem.class);
        customSpinner.setSpinnerData(dropDownNewItem.getData(),id,customText);
    }
    public List<DropDownItem>  getDropDown(String name){
        Gson gson = new Gson();
        String jsonString = getString(name);
        DropDownNewItem dropDownNewItem = gson.fromJson(jsonString,DropDownNewItem.class);
        return dropDownNewItem.getData();
    }

    public void setDropDownByName(CustomSpinner customSpinner,String name,String item){
        Gson gson = new Gson();
        String jsonString = getString(name);
        DropDownNewItem dropDownNewItem = gson.fromJson(jsonString,DropDownNewItem.class);
        customSpinner.setSpinnerDataByName(dropDownNewItem.getData(),item);
    }

    public void setDropDown(CustomSpinner customSpinner,String name){
        Gson gson = new Gson();
        String jsonString = getString(name);
        DropDownNewItem dropDownNewItem = gson.fromJson(jsonString,DropDownNewItem.class);
        try{
            customSpinner.setSpinnerData(dropDownNewItem.getData());
        }catch (Exception e){
            AppLogger.INSTANCE.log("error:"+e.getLocalizedMessage());
        }
    }

    public void setDropDown(CustomSpinner customSpinner,String name,TextView customText){
        Gson gson = new Gson();
        String jsonString = getString(name);
        DropDownNewItem dropDownNewItem = gson.fromJson(jsonString,DropDownNewItem.class);
        try{
            customSpinner.setSpinnerData(dropDownNewItem.getData(),customText);
        }catch (Exception e){
            AppLogger.INSTANCE.log("error:"+e.getLocalizedMessage());
        }
    }

    public void setDropDown(CustomSpinner customSpinner, String name, EditText customText){
        Gson gson = new Gson();
        String jsonString = getString(name);
        DropDownNewItem dropDownNewItem = gson.fromJson(jsonString,DropDownNewItem.class);
        try{
            customSpinner.setSpinnerData(dropDownNewItem.getData(),customText);
        }catch (Exception e){
            AppLogger.INSTANCE.log("error:"+e.getLocalizedMessage());
        }
    }

    public void setDropDown(TextView customSpinner, String name, String id){
        List<DropDownItem> list = getDropDownList(name);
        for (DropDownItem i : list) {
            if (i.getId().equalsIgnoreCase(id)) {
                customSpinner.setText(i.getName());
                return;
            }
        }
    }

    public void setDropDown(TextView customSpinner, String name, ArrayList<Integer> ids){
        List<DropDownItem> list = getDropDownList(name);
        if (ids!=null && !ids.isEmpty()){
            String id = ids.get(0).toString();
            for (DropDownItem i : list) {
                if (i.getId().equalsIgnoreCase(id)) {
                    customSpinner.setText(i.getName());
                    return;
                }
            }
            if (!list.isEmpty()){
                customSpinner.setText(list.get(0).getName());
            }
        }
    }

    public void setDropDown(TextView customSpinner, String name, String id,TextView customText){
        List<DropDownItem> list = getDropDownList(name);
        for (DropDownItem i : list) {
            if (i.getId().equalsIgnoreCase(id)) {
                customSpinner.setText(i.getName());
                customText.setText(i.getShortName());
                return;
            }
        }
    }

    public void setDropDown(TextView customSpinner, String name, String id,EditText customText){
        List<DropDownItem> list = getDropDownList(name);
        for (DropDownItem i : list) {
            if (i.getId().equalsIgnoreCase(id)) {
                customSpinner.setText(i.getName());
                customText.setText(i.getShortName());
                return;
            }
        }
    }
    public String getDropDownValue(String name, String id){
        List<DropDownItem> list = getDropDownList(name);
        for (DropDownItem i : list) {
            if (i.getId().equalsIgnoreCase(id)) {
                return i.getName();
            }
        }
        return "";
    }

    public List<DropDownItem> getDropDownList(String name){
        try{
            Gson gson = new Gson();
            String jsonString = getString(name);
            DropDownNewItem dropDownNewItem = gson.fromJson(jsonString,DropDownNewItem.class);
            return dropDownNewItem.getData();
        }catch (Exception e){
           AppLogger.INSTANCE.log("Dropdown value error on: " +name + "message: "+ e.getLocalizedMessage());
        }
        return new ArrayList<DropDownItem>();
    }

    public void saveInteger(String iKey, int iValue) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putInt(iKey, iValue);
        prefsEditor.apply();
    }

    public void saveLong(String iKey, Long iValue) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putLong(iKey, iValue);
        prefsEditor.apply();
    }

    public SearchHistoryList getSearchList() {
        SearchHistoryList list = new SearchHistoryList();
        String listJson=getString("SearchHistory");
        try{
            list=new Gson().fromJson(listJson, SearchHistoryList.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(list==null)
            list=new SearchHistoryList();
        return list;
    }
    public void saveSearchList(SearchHistoryList iValue) {
        String searchhistoryJson= new Gson().toJson(iValue);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("SearchHistory",searchhistoryJson);
        prefsEditor.apply();
    }
    public void saveString(String iKey, String iValue) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(iKey, iValue);
        prefsEditor.apply();
    }
    public void removeString(String iKey) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.remove(iKey);
        prefsEditor.apply();
    }
    public boolean getBoolean(String iKey) {
        return mPrefs.getBoolean(iKey, false);
    }

    public int getInteger(String iKey) {
        return mPrefs.getInt(iKey, 0);
    }

    public Long getLong(String iKey) {
        return mPrefs.getLong(iKey, 0L);
    }

    public String getString(String iKey) {
        if (iKey.equals("locale")) {
            return mPrefs.getString(iKey, "en");
        } else {
            return mPrefs.getString(iKey, "");
        }
    }

    public String getToken(){
        return getString("accessToken");
    }

    public String getBearerToken(){
        return "Bearer "+getString("accessToken");
    }

    public String getRefresh(){
        return getString("refreshToken");
    }

    public void  saveGAId(String gaId){
        mPrefs.edit().putString("ga-property-id",gaId).apply();;
    }
    public String  getGAId(){
        return mPrefs.getString("ga-property-id",null);
    }
    public void removeItem(String iKey) {
        mPrefs.edit().remove(iKey).apply();
    }


    public void clearSavedData() {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.clear().apply();
    }

    public void saveVersionCode(int versionCode){
        mPrefs.edit().putInt("versionCode",versionCode).apply();
    }

    public void saveRateAppResponse(String response){
        mPrefs.edit().putString("rateApp",response).apply();
    }
    public String getRateAppResponse(){
        return mPrefs.getString("rateApp",null);
    }

    public void saveRemoveLogoAdPriority(String response){
        mPrefs.edit().putString("removeLogoadPriority",response).apply();
    }
    public String getRemoveLogoAdPriority(){
        return mPrefs.getString("removeLogoadPriority",null);
    }


    public int getVersionCode(){
        return mPrefs.getInt("versionCode",-1);
    }

    public ArrayList<String> getAIImagesStringList(String key) {
        return new ArrayList<>(Arrays.asList(TextUtils.split(mPrefs.getString(key, ""), "‚‗‚")));
    }


    /**
     * Put ArrayList of String into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param tag String tag to place in list
     */
    public void putAIImagesSPList(String key, String tag) {
        //check if the tag is available
        // if available then dont edit and add to list
        // if not then create a list of +1 the existing list and add to the last item
        Boolean isItemAlreadyAdded = false;
        ArrayList<String> existingList = getAIImagesStringList(key);
        if (existingList.size() == 0){
            Log.e("asdfasdf", "inside putAIImagesSPList inside if tag: " + tag);
            String[] myStringList = new String[1];
            myStringList[0] = tag;
            mPrefs.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
        } else {
            Log.e("asdfasdf", "inside putAIImagesSPList inside else tag: " + tag);
            for (int i =0; i < existingList.size();i++){
                if (existingList.get(i).equals(tag))
                    isItemAlreadyAdded = true;
            }
            if (!isItemAlreadyAdded) {
                String[] myStringList = new String[existingList.size() + 1];
                for (int i = 0; i < existingList.size(); i++) {
                    if (!existingList.get(i).equals(tag))
                        myStringList[i] = existingList.get(i);
                }
                myStringList[existingList.size()] = tag;
                mPrefs.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
            }
        }
    }
}
