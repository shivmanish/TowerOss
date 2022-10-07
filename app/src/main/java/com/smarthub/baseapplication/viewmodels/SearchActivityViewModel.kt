package com.smarthub.baseapplication.viewmodels

import androidx.lifecycle.ViewModel

class SearchActivityViewModel: ViewModel() {

    public fun getFlowData(): List<String> {
        return listOf("Site Name","Site Id","Maintenance Point","Area")
    }

}