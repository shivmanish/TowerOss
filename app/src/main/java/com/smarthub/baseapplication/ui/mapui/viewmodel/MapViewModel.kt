package com.smarthub.baseapplication.ui.mapui.viewmodel

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.helpers.SingleLiveEvent
import com.smarthub.baseapplication.model.home.HomeResponse
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.ui.mapui.pojo.MapMarkerService
import com.smarthub.baseapplication.ui.mapui.pojo.MarkerResponse

class MapViewModel : ViewModel(){
var repo:MapRepo?= null
    var mapmarketLivedata : SingleLiveEvent<Resource<MarkerResponse>>?=null

init {
    repo = MapRepo(APIInterceptor.get())
    mapmarketLivedata = repo!!.marker
}
    fun fetchData(data:MapMarkerService) {
        repo!!.getmarkedata(data)
    }

}