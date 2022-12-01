package com.smarthub.baseapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.search.SearchList
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.network.repo.SiteInfoRepo

class BasicInfoDetailViewModel : ViewModel() {
    var siteInfoRepo: SiteInfoRepo?=null
    var dropDownResponse: MutableLiveData<SiteInfoDropDownData>? = null
    var siteInfoResponse : SingleLiveEvent<Resource<SiteInfoModel>>?=null
    var siteSearchResponse : SingleLiveEvent<Resource<SearchList>>?=null

    init {
        siteInfoRepo = SiteInfoRepo(APIInterceptor.get())
        dropDownResponse = MutableLiveData<SiteInfoDropDownData>()
        siteInfoResponse = siteInfoRepo?.siteInfoResponseData
        siteSearchResponse = siteInfoRepo?.siteSearchResponseData

    }

    fun fetchDropDown() {
        var jsonData: String =
            AppPreferences.getInstance().getString(AppPreferences.DROPDOWNDATA)
        val gson = Gson()
        if (jsonData.isNotEmpty()) {
            val siteInfoDropDownData: SiteInfoDropDownData = gson.fromJson(jsonData, SiteInfoDropDownData::class.java)
            if (siteInfoDropDownData != null) {
                dropDownResponse?.postValue(siteInfoDropDownData)
            }
        }
    }

    fun fetchSiteInfo() {
        siteInfoRepo?.siteInfoData()
    }

    fun fetchSiteInfoById(id:String) {
        siteInfoRepo?.siteSearchData(id)
    }

    fun fetchSiteSearchData(id:String) {
        siteInfoRepo?.siteSearchData(id)
    }
    fun fetchSiteSearchData(id:String,category :String) {
        siteInfoRepo?.siteSearchData(id,category)
    }
}
