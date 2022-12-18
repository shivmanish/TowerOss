package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BasicInfoDetailsBottomSheetBinding
import com.smarthub.baseapplication.databinding.SrDetailsBottomSheetDialogBinding
import com.smarthub.baseapplication.model.serviceRequest.SRDetail
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoServiceData
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SRDetailsBottomSheet(contentLayoutId: Int,var viewModel: HomeViewModel,var template: String) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding : SrDetailsBottomSheetDialogBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SrDetailsBottomSheetDialogBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }

        var srDetail: SRDetail? = null
  

        if (viewModel.getTaskDataResponse?.hasActiveObservers() == true)
            viewModel.getTaskDataResponse?.removeObservers(viewLifecycleOwner)
        viewModel.getTaskDataResponse?.observe(viewLifecycleOwner){

        }

        viewModel.fetchServiceRequestData(template)

   /*     binding.icMenuClose.setOnClickListener {
            basicinfo!!.let{

                it.Buildingtype = binding.txBuildingType.text.toString()
                it.Locationzone= binding.txtLocationZone.text.toString()
                it.MaintenancePoint= ""
                it.Projectname= binding.txtProjectName.text.toString()
                *//* it.Sitecategory= binding.siteCategory.selectedValue.id!!
                 it.Siteownership= binding.siteOwnership.selectedValue.id!!
                 it.Sitestatus= binding.siteStatus.selectedValue.id!!
                 it.Sitetype= binding.siteType.selectedValue.id!!*//*
                it.aliasName= ""
                it.id = srDetail.id
                it.siteID= binding.txSiteID.text.toString()
                it.siteInChargeName= binding.txtSiteInChargeName.text.toString()
                it.siteInChargeNumber= binding.txtSiteInChargeNumber.text.toString()
                it.siteName= binding.txSiteName.text.toString()
                it.siteaddress= binding.address.text.toString()
            }
            basicinfoModel!!.basicinfo = basicinfo!!
            viewModel.updateData(basicinfoModel!!)
        }
        binding.s.setText(srDetail?.SRType)
*/

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SrDetailsBottomSheetDialogBinding.inflate(inflater)
        return binding.root
    }

}