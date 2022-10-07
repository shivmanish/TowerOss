package com.smarthub.baseapplication.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.login.UserLoginPost
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.RefreshToken
import com.smarthub.baseapplication.network.repo.LoginRepo

class LoginViewModel: ViewModel() {
    var loginRepo: LoginRepo?=null
    var loginResponse : SingleLiveEvent<Resource<RefreshToken>>?=null
    init {
        loginRepo = LoginRepo(APIInterceptor.get())
        loginResponse = loginRepo?.loginResponse
    }

    fun getLoginToken(data : UserLoginPost) {
        loginRepo?.getLoginToken(data)
    }

}