package com.smarthub.baseapplication.ui.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment(){

    var progressDialog : ProgressDialog?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(requireContext())
        progressDialog?.setMessage("Loading ...")
        progressDialog?.setCancelable(true)
    }

    fun showLoader(){
        if (progressDialog!=null && progressDialog?.isShowing == true){
            progressDialog?.show()
        }
    }

    fun hideLoader(){
        if (progressDialog!=null && progressDialog?.isShowing == false){
            progressDialog?.hide()
        }
    }

    override fun onStop() {
        super.onStop()
        hideLoader()
    }
}