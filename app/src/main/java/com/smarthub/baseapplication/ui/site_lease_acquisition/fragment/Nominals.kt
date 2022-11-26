package com.smarthub.baseapplication.ui.site_lease_acquisition.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BasicInfoFragmentBinding
import com.smarthub.baseapplication.databinding.CustomerInfoFragmentInfoBinding
import com.smarthub.baseapplication.databinding.NominalsFragmentBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.adapter.customer.BackhaulListAdapter
import com.smarthub.baseapplication.ui.dialog.opco.DetailsBottomSheet
import com.smarthub.baseapplication.ui.site_lease_acquisition.adapter.SiteLeaseListAdapter

class Nominals :Fragment(), SiteLeaseListAdapter.SiteLeaseListListener {

    var binding : NominalsFragmentBinding?=null
    var bottomSheetDialogFragment : DetailsBottomSheet?=null

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
        if (bottomSheetDialogFragment==null) {
            bottomSheetDialogFragment = DetailsBottomSheet(R.layout.details_bottom_sheet_view)
            bottomSheetDialogFragment?.show(childFragmentManager,"category")
        }else bottomSheetDialogFragment?.show(childFragmentManager,"category")

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