package com.smarthub.baseapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency.OpcoInfoUpdateResponse
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency.UpdateOpcoTenencyModel
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency.updateOpcoDataItem
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.network.repo.SiteIboardUpdateRepo
import com.smarthub.baseapplication.network.repo.SiteInfoRepo
import com.smarthub.baseapplication.ui.dialog.siteinfo.repo.BasicInfoDialougeResponse
import com.smarthub.baseapplication.utils.AppLogger

class SiteInfoViewModel: ViewModel() {
    var siteInfoRepo: SiteInfoRepo?=null
    var updateSiteInfoRepo:SiteIboardUpdateRepo?=null
    var dropDownResponse : SingleLiveEvent<Resource<SiteInfoDropDownData>>?=null
    var opcoTenencyUpdateResoponse: SingleLiveEvent<Resource<OpcoInfoUpdateResponse>>? = null
    init {
        siteInfoRepo = SiteInfoRepo(APIInterceptor.get())
        updateSiteInfoRepo= SiteIboardUpdateRepo(APIInterceptor.get())
        dropDownResponse = siteInfoRepo?.dropDownResoonse
        opcoTenencyUpdateResoponse=updateSiteInfoRepo?.opcoTenencyUpdateResponse
    }
    fun fetchDropDown() {
        siteInfoRepo?.siteInfoDropDown()
    }

    fun updateOpcoTenency(data: updateOpcoDataItem){
        var item=UpdateOpcoTenencyModel()
        item.Operator=data
        AppLogger.log("opcoInfo data for update on view model: ${data}, whole Data: ${item}")

        updateSiteInfoRepo?.updateOpcoTeency(item)
    }


}