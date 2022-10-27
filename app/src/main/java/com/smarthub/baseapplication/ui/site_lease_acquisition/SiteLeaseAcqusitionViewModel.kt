package com.smarthub.baseapplication.ui.site_lease_acquisition

import androidx.lifecycle.ViewModel
import com.smarthub.baseapplication.helpers.SingleLiveEvent

class SiteLeaseAcqusitionViewModel : ViewModel() {

    var site_lease_data = SingleLiveEvent<String>()


    fun fetchData() {
        // Network work will handel there
        site_lease_data.postValue("one")
    }


}