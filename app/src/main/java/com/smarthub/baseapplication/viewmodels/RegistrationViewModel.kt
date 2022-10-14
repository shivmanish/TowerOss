package com.smarthub.baseapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.login.UserLoginPost
import com.smarthub.baseapplication.model.register.RegisterData
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.pojo.RefreshToken
import com.smarthub.baseapplication.network.repo.LoginRepo

class RegistrationViewModel: ViewModel() {
    var loginRepo: LoginRepo?=null
    var loginResponse : RegisterData?=null
    init {
        loginRepo = LoginRepo(APIInterceptor.get())
    }

}