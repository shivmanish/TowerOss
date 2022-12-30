package com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoInfoFregmentBinding
import com.smarthub.baseapplication.ui.dialog.services_request.*
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.SoftAcquisitionAdapter




class SoftAcquisitionTabFragment : BaseFragment(), SoftAcquisitionAdapter.SoftAcquisitionLisListener {
    var binding : OpcoInfoFregmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OpcoInfoFregmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = SoftAcquisitionAdapter(this@SoftAcquisitionTabFragment)
    }
    override fun attachmentItemClicked() {

    }
    override fun detailsItemClicked() {
        val bottomSheetDialogFragment = SoftAcutionsFeasibilityBottomSheet(R.layout.soft_acution_feasibility_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

}