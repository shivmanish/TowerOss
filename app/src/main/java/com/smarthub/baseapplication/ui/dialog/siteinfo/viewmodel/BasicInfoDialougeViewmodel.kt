package com.smarthub.baseapplication.ui.dialog.siteinfo.viewmodel

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.repo.BasicInfoDialougeRepo
import com.smarthub.baseapplication.ui.dialog.siteinfo.repo.BasicInfoDialougeResponse

class BasicInfoDialougeViewmodel : ViewModel() {

    var basicinfoModel: SingleLiveEvent<Resource<BasicInfoDialougeResponse>>? = null
    var repo: BasicInfoDialougeRepo? = null

    init {
        repo = BasicInfoDialougeRepo(APIInterceptor.get())
        basicinfoModel = repo!!.getDropDownResoonse()
    }

    fun updateData(basicinfoModel: BasicinfoModel){
        repo!!.updateData(basicinfoModel)
    }

}