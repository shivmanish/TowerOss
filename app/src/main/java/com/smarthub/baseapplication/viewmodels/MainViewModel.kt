package com.smarthub.baseapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var isActionbarHide = MutableLiveData<Boolean>()
    fun isActionBarHide(istrue:Boolean){
        isActionbarHide.value = istrue
    }
}