package com.smarthub.baseapplication.ui.site_lease_acquisition

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.network.repo.SiteInfoRepo
import com.smarthub.baseapplication.network.repo.SiteLeaseListRepo

class SiteLeaseAcqusitionViewModel : ViewModel() {

    var site_lease_data = SingleLiveEvent<String>()


    fun fetchData() {
        // Network work will handel there
        site_lease_data.postValue("one")
    }

    var siteLeaseListRepo: SiteLeaseListRepo?=null
    var dropDownResponse : SingleLiveEvent<Resource<SiteInfoDropDownData>>?=null
    init {
        siteLeaseListRepo = SiteLeaseListRepo(APIInterceptor.get())
        dropDownResponse = siteLeaseListRepo?.dropDownResoonse
    }
    fun fetchDropDown() {
        siteLeaseListRepo?.siteInfoDropDown()
    }
}