package com.smarthub.baseapplication.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.profile.UserProfileGet
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.ProfileDetails
import com.smarthub.baseapplication.network.repo.ProfileRepo

class ProfileViewModel: ViewModel() {
    var profileRepo: ProfileRepo?=null
    var profileResponse : SingleLiveEvent<Resource<List<ProfileDetails>>>?=null

    init {
        profileRepo = ProfileRepo(APIInterceptor.get())
        profileResponse = profileRepo?.profileResponse
    }

    fun getProfileData(data : UserProfileGet) {
        profileRepo?.getProfileData(data)
    }

}