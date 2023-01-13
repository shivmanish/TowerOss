package com.smarthub.baseapplication.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.profile.UserProfileUpdate
import com.smarthub.baseapplication.model.profile.viewProfile.newData.ProfileData
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.ProfileUpdate
import com.smarthub.baseapplication.network.repo.ProfileRepo

class ProfileViewModel: ViewModel() {
    var profileRepo: ProfileRepo?=null
    var profileResponse : SingleLiveEvent<Resource<List<ProfileData>>>?=null
    var profileUpdate : SingleLiveEvent<Resource<ProfileUpdate>>?=null
    init {
        profileRepo = ProfileRepo(APIInterceptor.get())
        profileResponse = profileRepo?.profileResponse
        profileUpdate = profileRepo?.profileUpdate
    }
    fun getProfileData() {
        profileRepo?.getProfileData()
    }

    fun updateProfileData(data : UserProfileUpdate?){
        Log.d("status","Update Profile Data in Profile View Model")
        profileRepo?.updateProfileData(data)
    }

}