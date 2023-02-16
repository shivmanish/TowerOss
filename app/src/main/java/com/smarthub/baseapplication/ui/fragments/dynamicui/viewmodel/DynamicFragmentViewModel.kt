package com.smarthub.baseapplication.ui.fragments.dynamicui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.smarthub.baseapplication.ui.fragments.dynamicui.FragmentPojo
import com.smarthub.baseapplication.ui.fragments.dynamicui.pojo.DynamicUiResponse
import com.smarthub.baseapplication.ui.fragments.siteInfo.SiteInfoNewFragment
import com.smarthub.baseapplication.utils.Utils
import kotlinx.coroutines.async

class DynamicFragmentViewModel : ViewModel() {
    var dynamicUiResponse: MutableLiveData<DynamicUiResponse>
    var fragmentList: MutableLiveData<ArrayList<FragmentPojo>>

    init {
        fragmentList = MutableLiveData()
        dynamicUiResponse = MutableLiveData()
    }

    fun fetchData(context: Context) {
        val json = Utils.getJsonDataFromAsset(context, "dynamiclistnew.json")
        val model = Gson().fromJson(json, DynamicUiResponse::class.java)
        dynamicUiResponse.postValue(model)
    }

    fun setFragmentList(dynamicUiResponse: DynamicUiResponse?) {
        var fraglist = ArrayList<FragmentPojo>()
        viewModelScope.async {
            for (data in dynamicUiResponse!!.data) {
                FragmentPojo().apply {
                    fragment = SiteInfoNewFragment(data.tab_id)
                    titel = data.tab_titel
                    fraglist.add(this)
                }
            }
            fragmentList.postValue(fraglist)
        }

    }


}