package com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpsrTabFragmaentLayoutBinding
import com.smarthub.baseapplication.ui.dialog.services_request.*
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.OpcoTssrAdapter
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel
class OppoTssrTabFragment : BaseFragment(), OpcoTssrAdapter.OpcoTssrLisListener {
    var binding : OpsrTabFragmaentLayoutBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OpsrTabFragmaentLayoutBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = OpcoTssrAdapter(this@OppoTssrTabFragment)
    }
    override fun attachmentItemClicked() {

    }
    override fun detailsItemClicked() {
        val bottomSheetDialogFragment = RFFeasibilityBottomSheet(R.layout.rf_feasibility_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun operationInfoDetailsItemClicked() {
        val bottomSheetDialogFragment = EquipmentDetailsBottomSheetDialog(R.layout.equipment_bottom_sheet)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun geoConditionsDetailsItemClicked() {
        val bottomSheetDialogFragment = PowerMSBBottomSheet(R.layout.power_msb_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun siteAccessDetailsItemClicked() {
        val bottomSheetDialogFragment = TssrExecutiveBottomSheet(R.layout.tssr_executive_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun requestinfoClicked() {
        val bottomSheetDialogFragment = RequestInfoBottomSheet(R.layout.request_info_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
}