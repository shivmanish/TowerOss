package com.smarthub.baseapplication.ui.alert.viewmodel

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.ui.alert.AlertRepo
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.model.request.SendAlertModel
import com.smarthub.baseapplication.ui.alert.model.request.SendAlertUser
import com.smarthub.baseapplication.ui.alert.model.request.SupportRequiredUser
import com.smarthub.baseapplication.ui.alert.model.response.SendAlertResponse
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponse
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponseItem

class AlertViewModel : ViewModel() {
    public var sendAlertModel: SendAlertModel
    public var sendAlertUsers: ArrayList<SendAlertUser>
    public var selecteduserposition: ArrayList<Int>
    public var supportRequiredUsers: ArrayList<SupportRequiredUser>
    var repo: AlertRepo
    var sendAlertResponseLivedata: SingleLiveEvent<Resource<SendAlertResponse>>
    var userDataResponseLiveData: SingleLiveEvent<Resource<UserDataResponse>>
    var userDataList: ArrayList<UserDataResponseItem>

    init {
        selecteduserposition = ArrayList()
        userDataList = ArrayList()
        sendAlertModel = SendAlertModel()
        supportRequiredUsers = ArrayList()
        sendAlertUsers = ArrayList()
        var ddaa: SendAlertUser = SendAlertUser()
        var tww = SupportRequiredUser()
        sendAlertUsers.add(ddaa)
        supportRequiredUsers.add(tww)
        sendAlertModel.SupportRequiredUsers = supportRequiredUsers
        sendAlertModel.SendAlertUsers = sendAlertUsers
        repo = AlertRepo(APIInterceptor.get())
        sendAlertResponseLivedata = repo.getAlertResponseLiveData()
        userDataResponseLiveData = repo.getUserDataReponseLiveData()
    }

    fun sendAlert() {
        repo.sendAlert(sendAlertModel)
    }

    fun getUser(getuserservice: GetUserList) {
        repo.getuser(getuserservice)
    }

    fun getDemoObject() {
//        sendAlertModel = Ut
    }


}