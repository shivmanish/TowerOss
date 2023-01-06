package com.smarthub.baseapplication.ui.fragments.powerAndFuel.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo.DataResponse
import com.smarthub.baseapplication.utils.Utils

class PowerConnectionRootViewModel : ViewModel() {

    var powerfuelListLivedata: MutableLiveData<DataResponse>

    init {
        powerfuelListLivedata = MutableLiveData<DataResponse>()
    }

    fun getPowerListingData(context: Context) {
        var jsondata = Utils.getJsonDataFromAsset(context, "powerandfuel.json")
        val data : DataResponse = Gson().fromJson(jsondata,DataResponse::class.java)
        powerfuelListLivedata.postValue(data)
    }

}