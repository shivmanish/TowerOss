package com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoInfoFregmentBinding
import com.smarthub.baseapplication.ui.dialog.services_request.*
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.FeasibilityoplanningAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.SoftAcquisitionAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesRequestAdapter
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel

class FeasibilityPlanningTabFragment : Fragment(),
    FeasibilityoplanningAdapter.FeasibilityoplanningLisListener {
    var siteViewModel : SiteInfoViewModel?=null
    var binding : OpcoInfoFregmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OpcoInfoFregmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = FeasibilityoplanningAdapter(this@FeasibilityPlanningTabFragment)
    }
    override fun attachmentItemClicked() {

    }
    override fun detailsItemClicked() {
        val bottomSheetDialogFragment = SiteDetailsBottomSheet(R.layout.site_detail_bootom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun operationInfoDetailsItemClicked() {
        val bottomSheetDialogFragment = PowerMsbPlanningBottomSheet(R.layout.power_msb_planning_bootom_shhet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun geoConditionsDetailsItemClicked() {
        val bottomSheetDialogFragment = BackhualPlanningBottomSheet(R.layout.backhaul_planning_bootom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun siteAccessDetailsItemClicked() {
        val bottomSheetDialogFragment = BachhualLinkBottomSheet(R.layout.backhaul_link_list_item)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun requestinfoClicked() {
        val bottomSheetDialogFragment = RequestInfoBottomSheet(R.layout.request_info_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }


}