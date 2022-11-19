package com.smarthub.baseapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.network.repo.SiteInfoRepo

class BasicInfoDetailViewModel : ViewModel() {

    var dropDownResponse: MutableLiveData<SiteInfoDropDownData>? = null

    init {
        dropDownResponse = MutableLiveData<SiteInfoDropDownData>()
    }

    fun fetchDropDown() {
        var jsonData: String =
            AppPreferences.getInstance().getString(AppPreferences.DROPDOWNDATA)
        val gson = Gson()
        val siteInfoDropDownData: SiteInfoDropDownData = gson.fromJson(jsonData,SiteInfoDropDownData::class.java)
        if(siteInfoDropDownData != null) {
            dropDownResponse?.postValue(siteInfoDropDownData)
        }
    }


}