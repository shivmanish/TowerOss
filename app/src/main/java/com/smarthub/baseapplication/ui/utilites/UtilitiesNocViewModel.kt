package com.smarthub.baseapplication.ui.utilites

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.SingleLiveEvent

class UtilitiesNocViewModel : ViewModel() {

    var utilites_noc_data = SingleLiveEvent<String>()


    fun fetchData() {
        // Network work will handel there
        utilites_noc_data.postValue("one")
    }


}