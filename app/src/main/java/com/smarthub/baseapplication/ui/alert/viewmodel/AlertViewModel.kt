package com.smarthub.baseapplication.ui.alert.viewmodel

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.register.dropdown.DepartmentDropdown
import com.smarthub.baseapplication.model.register.dropdown.DropdownParam
import com.smarthub.baseapplication.model.search.SearchList
import com.smarthub.baseapplication.model.search.SearchListItem
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.ui.alert.AlertRepo
import com.smarthub.baseapplication.ui.alert.model.UpdateAlertData
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.model.request.SendAlertModel
import com.smarthub.baseapplication.ui.alert.model.request.SendAlertUser
import com.smarthub.baseapplication.ui.alert.model.request.SupportRequiredUser
import com.smarthub.baseapplication.ui.alert.model.response.SendAlertResponse
import com.smarthub.baseapplication.ui.alert.model.response.SendAlertResponseNew
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponse
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponseItem

class AlertViewModel : ViewModel() {
    var sendAlertModel: SendAlertModel
    var sendAlertUsers: ArrayList<SendAlertUser>
    var selecteduserposition: ArrayList<Int>
    var searchListItem : SingleLiveEvent<SearchListItem>
    var supportRequiredUsers: ArrayList<SupportRequiredUser>
    var repo: AlertRepo
    var department: String = "D1"
    var siteSearchResponse : SingleLiveEvent<Resource<SearchList>>?=null

    var sendAlertResponseLivedata: SingleLiveEvent<Resource<SendAlertResponse>>
    var sendAlertResponseLivedataNew: SingleLiveEvent<Resource<SendAlertResponseNew>>
    var userDataResponseLiveData: SingleLiveEvent<Resource<UserDataResponse>>
    var departmentDropdown: SingleLiveEvent<Resource<DepartmentDropdown>>
    var userDataList: ArrayList<UserDataResponseItem>

    init {
        selecteduserposition = ArrayList()
        userDataList = ArrayList()
        sendAlertModel = SendAlertModel()
        supportRequiredUsers = ArrayList()
        sendAlertUsers = ArrayList()
        val ddaa = SendAlertUser()
        val tww = SupportRequiredUser()
        sendAlertUsers.add(ddaa)
        supportRequiredUsers.add(tww)
        sendAlertModel.SupportRequiredUsers = supportRequiredUsers
        sendAlertModel.SendAlertUsers = sendAlertUsers
        repo = AlertRepo(APIInterceptor.get())
        sendAlertResponseLivedata = repo.alertResponseLiveData
        sendAlertResponseLivedataNew = repo.alertResponseLiveDataNew
        userDataResponseLiveData = repo.userDataReponseLiveData
        departmentDropdown = repo.departmentDropDownData
        siteSearchResponse = repo.siteSearchResponseData
        searchListItem = SingleLiveEvent()
    }

    fun updateSearchListItem(item : SearchListItem){
        searchListItem.postValue(item)
    }

    fun fetchSiteSearchData(id:String,category :String) {
        repo.searchSiteAll(id,category)
    }

    fun updateSupportRequiredUserList(supportRequiredUsers : ArrayList<SupportRequiredUser>){
        sendAlertModel.SupportRequiredUsers = supportRequiredUsers
    }

    fun updateSendAlertUserList(sendAlertUser : ArrayList<SendAlertUser>){
        sendAlertModel.SendAlertUsers = sendAlertUser
    }


    fun sendAlert() {
        var supportRequiredUserList = ArrayList<SupportRequiredUser>()
        var sendAlertUserList = ArrayList<SendAlertUser>()
        for(i in selecteduserposition){
            val userData = userDataList[i]
            var supportRequiredUser = SupportRequiredUser(department,userData.email,userData.phone,userData.username)
            var sendAlertUser = SendAlertUser(department,userData.email,userData.phone,userData.username)
            supportRequiredUserList.add(supportRequiredUser)
            sendAlertUserList.add(sendAlertUser)
        }
        sendAlertModel.SupportRequiredUsers = supportRequiredUserList
        sendAlertModel.SendAlertUsers = sendAlertUserList
        repo.sendAlertNew(sendAlertModel)
    }

    fun getAlertDetails(id:String){
        repo.getAlertDetails(id)
    }

    fun updateAlertDetails(data:UpdateAlertData){
        repo.updateAlertDetails(data)
    }

    fun sendSms(id:String,sms :String){
        repo.sendSms(id,sms)
    }
    fun getUser(getuserservice: GetUserList) {
        department = getuserservice.department
        repo.getuser(getuserservice)
    }

    fun getDepartments(getuserservice: DropdownParam) {
        repo.getDepartments(getuserservice)
    }

    fun getDemoObject() {
//        sendAlertModel = Ut
    }


}