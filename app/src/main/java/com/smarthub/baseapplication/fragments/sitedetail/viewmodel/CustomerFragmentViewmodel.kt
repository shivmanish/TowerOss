package com.smarthub.baseapplication.fragments.sitedetail.viewmodel

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.SingleLiveEvent

class CustomerFragmentViewmodel : ViewModel() {
    var customer_data = SingleLiveEvent<String>()


    fun fetchData() {
        // Network work will handel there
        customer_data.postValue("one")
    }


}