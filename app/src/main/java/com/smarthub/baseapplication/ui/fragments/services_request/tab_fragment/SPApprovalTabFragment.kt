package com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment

import SODetail
import SPApproval
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SpApprovalTabFragmentBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.dialog.services_request.*
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.SPApprovalAdapter
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SPApprovalTabFragment(var data: ServiceRequestAllDataItem?, var Id: String?) : BaseFragment(), SPApprovalAdapter.SPSoftLisListener {
    lateinit var viewmodel: HomeViewModel
    var binding : SpApprovalTabFragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SpApprovalTabFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding?.listItem?.adapter = SPApprovalAdapter( requireContext(),this@SPApprovalTabFragment, data!!)
    }
    override fun attachmentItemClicked() {

    }
    override fun detailsItemClicked(
        spApproval: SPApproval,
        serviceRequestAllData: ServiceRequestAllDataItem
    ) {
        val bottomSheetDialogFragment = SpApprovalBottomSheet(R.layout.sp_approval_dialog,viewmodel,Id,spApproval,serviceRequestAllData)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun requestinfoClicked(
        soDetail: SODetail,
        serviceRequestAllData: ServiceRequestAllDataItem
    ) {
        val bottomSheetDialogFragment = SoApprovalBottomSheet(R.layout.so_approval_dialog,Id,viewmodel,soDetail,serviceRequestAllData)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun operationInfoDetailsItemClicked() {
        val bottomSheetDialogFragment = EquipmentDetailsBottomSheetDialog(R.layout.equipment_bottom_sheet)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun geoConditionsDetailsItemClicked() {
        val bottomSheetDialogFragment = RadioAntennasBottomSheetDialog(R.layout.radio_antina_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun siteAccessDetailsItemClicked() {
        val bottomSheetDialogFragment = BachhualLinkBottomSheet(R.layout.backhaul_link_list_item)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }



}