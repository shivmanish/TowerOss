package com.smarthub.baseapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.network.repo.SiteInfoRepo

class BasicInfoDetailViewModel:ViewModel() {

    var siteInfoRepo: SiteInfoRepo?=null
    var dropDownResponse : SingleLiveEvent<Resource<SiteInfoDropDownData>>?=null
    init {
        siteInfoRepo = SiteInfoRepo(APIInterceptor.get())
        dropDownResponse = siteInfoRepo?.dropDownResoonse
    }
    fun fetchDropDown() {
        siteInfoRepo?.siteInfoDropDown()
    }


}