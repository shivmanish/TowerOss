package com.smarthub.baseapplication.ui.site_agreement.siteagreements_tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NominalsFragmentBinding
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteacquisitionAgreement
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.site_agreement.tableadapter.SANominalsFragmentAdapter
import com.smarthub.baseapplication.ui.site_agreement.dialogs.SAAgreementsBottomSheet
import com.smarthub.baseapplication.ui.site_agreement.dialogs.SAPOEditBottomSheet
import com.smarthub.baseapplication.ui.site_agreement.dialogs.SAPOViewBottomSheet
import com.smarthub.baseapplication.ui.site_agreement.tableadapter.PoTableAdapter
import com.smarthub.baseapplication.viewmodels.HomeViewModel


class SANomonalsFrag( val siteacquisitionAgreements: List<SiteacquisitionAgreement>?) : BaseFragment(), PoTableAdapter.PoInfoListListener {
    lateinit var adapter : SANominalsFragmentAdapter
    var binding : NominalsFragmentBinding?=null
    lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NominalsFragmentBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= SANominalsFragmentAdapter(requireContext(),this@SANomonalsFrag,siteacquisitionAgreements)

        binding?.nominalslist?.adapter = adapter
    }


    override fun attachmentItemClicked() {
//        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun editClicked(position: Int) {
        var editPo = SAPOEditBottomSheet(R.layout.sa_po_edit_dialog)
        editPo?.show(childFragmentManager,"category")
    }

    override fun viewClicked(position: Int) {
        var viewPo = SAPOViewBottomSheet(R.layout.sa_po_view_dialog)
        viewPo?.show(childFragmentManager,"category")
    }

    override fun AgreementEditViewClick(siteAgreementsData: SiteacquisitionAgreement?) {
        var saAgreementsBottomSheet = SAAgreementsBottomSheet(R.layout.sa_agreement_dialog,siteAgreementsData,homeViewModel)
        saAgreementsBottomSheet?.show(childFragmentManager,"category")
    }




}