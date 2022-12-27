package com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoInfoFregmentBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.dialog.services_request.*
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesRequestAdapter

class ServiceRequestTabFragment(var data : ServiceRequestAllDataItem?) : BaseFragment(), ServicesRequestAdapter.ServicesRequestLisListener {
    var binding : OpcoInfoFregmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OpcoInfoFregmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = ServicesRequestAdapter(requireContext(),this@ServiceRequestTabFragment,data!!)

    }
    override fun attachmentItemClicked() {
    }
    override fun EditSRdetailsItemClicked() {
        val bottomSheetDialogFragment = SRDetailsBottomSheet(R.layout.sr_details_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun EditBackhaulLinkItemClicked() {
        val bottomSheetDialogFragment = BachhualLinkBottomSheet(R.layout.backhaul_link_list_item)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun editEquipmentClicked(position: Int) {
        Toast.makeText(requireContext(),"SR Equipment  Item clicked for edit", Toast.LENGTH_SHORT).show()

    }

    override fun viewEquipmentClicked(position: Int) {
        Toast.makeText(requireContext(),"SR Equipment  Item clicked for view", Toast.LENGTH_SHORT).show()
    }

    override fun editRadioAnteenaClicked(position: Int) {
        Toast.makeText(requireContext(),"SR Radio Anteena  Item clicked for edit", Toast.LENGTH_SHORT).show()
    }

    override fun viewRadioAnteenaClicked(position: Int) {
        Toast.makeText(requireContext(),"SR Radio Anteena  Item clicked for view", Toast.LENGTH_SHORT).show()
    }

    override fun EditrequestinfoClicked() {
        val bottomSheetDialogFragment = RequestInfoBottomSheet(R.layout.request_info_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }


}