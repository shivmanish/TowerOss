package com.smarthub.baseapplication.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.profile.UserProfileGet
import com.smarthub.baseapplication.model.profile.UserProfileUpdate
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.ProfileDetails
import com.smarthub.baseapplication.network.ProfileUpdate
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.network.repo.ProfileRepo
import com.smarthub.baseapplication.network.repo.SiteInfoRepo

class SiteInfoViewModel: ViewModel() {
    var profileRepo: SiteInfoRepo?=null
    var profileResponse : SingleLiveEvent<Resource<SiteInfoDropDownData>>?=null
    init {
        profileRepo = SiteInfoRepo(APIInterceptor.get())
        profileResponse = profileRepo?.profileResponse
    }
    fun getProfileData() {
        profileRepo?.getProfileData()
    }


}