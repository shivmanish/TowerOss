package com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpsrTabFragmaentLayoutBinding
import com.smarthub.baseapplication.ui.dialog.services_request.*
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.SiteProposalAdapter
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel
class SiteProposalTabFragment : BaseFragment(), SiteProposalAdapter.SiteProposalListener {
    var siteViewModel : SiteInfoViewModel?=null
    var binding : OpsrTabFragmaentLayoutBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OpsrTabFragmaentLayoutBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = SiteProposalAdapter(this@SiteProposalTabFragment)
    }
    override fun attachmentItemClicked() {

     }
    override fun detailsItemClicked() {
        val bottomSheetDialogFragment = CommersalSiteProposalBottomSheet(R.layout.rf_feasibility_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun operationInfoDetailsItemClicked() {
        val bottomSheetDialogFragment = GenereteSiteProposalBottomSheet(R.layout.generate_site_proposal_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun geoConditionsDetailsItemClicked() {
        val bottomSheetDialogFragment = SpSubmisstioniteProposalBottomSheet(R.layout.sp_submission_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun siteAccessDetailsItemClicked() {
//        val bottomSheetDialogFragment = TssrExecutiveBottomSheet(
//            R.layout.tssr_executive_bottom_sheet_dialog,
//            Id,
//            viewmodel,
//            backhaulFeasibility,
//            serviceRequestAllData
//        )
//        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun requestinfoClicked() {
//        val bottomSheetDialogFragment = RequestInfoBottomSheet(
//            R.layout.request_info_bottom_sheet_dialog,
//            backhaulLinksData,
//            serviceRequestAllData,
//            viewmodel,
//            Id
//        )
//        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }


}