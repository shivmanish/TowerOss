package com.smarthub.baseapplication.ui.fragments.services_request

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.SingleLiveEvent


class ServiceFragmentViewModel : ViewModel() {
    var customer_data = SingleLiveEvent<String>()


    fun fetchData() {
        // Network work will handel there
        customer_data.postValue("one")
    }


}