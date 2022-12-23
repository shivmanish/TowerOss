package com.smarthub.baseapplication.ui.site_agreement.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NominalsFragmentBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.dialog.siteinfo.NominalsDetailsBottomSheet
import com.smarthub.baseapplication.ui.site_agreement.adapter.SiteLeaseListAdapter

class Nominals :Fragment(), SiteLeaseListAdapter.SiteLeaseListListener {

    var binding : NominalsFragmentBinding?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NominalsFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.nominalslist?.adapter = SiteLeaseListAdapter(this@Nominals)
    }

    fun initViews(view: View){


    }

    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()
    }
    override fun detailsItemClicked() {
        var bottomSheetDialogFragment = NominalsDetailsBottomSheet(R.layout.nominals_details_bottom_sheet)
        bottomSheetDialogFragment?.show(childFragmentManager,"category")
    }

    fun fetchDropDown() {
        var jsonData: String =
            AppPreferences.getInstance().getString(AppPreferences.DROPDOWNDATA)
        val gson = Gson()
        val siteInfoDropDownData: SiteInfoDropDownData = gson.fromJson(jsonData,
            SiteInfoDropDownData::class.java)
        if(siteInfoDropDownData != null) {
//            binding!!.spinRequestCompany.setSpinnerData(siteInfoDropDownData!!.opcoinfo.)
        }
    }

}