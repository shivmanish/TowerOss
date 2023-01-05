package com.smarthub.baseapplication.ui.site_agreement.siteagreements_tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NominalsFragmentBinding
import com.smarthub.baseapplication.ui.dialog.siteinfo.NominalsDetailsBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.site_agreement.adapter.SANominalsFragmentAdapter
import com.smarthub.baseapplication.ui.site_agreement.dialogs.SAAgreementsBottomSheet


class SANomonalsFrag : BaseFragment(), SANominalsFragmentAdapter.SANominalsListListener {
    lateinit var adapter : SANominalsFragmentAdapter
    var binding : NominalsFragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NominalsFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= SANominalsFragmentAdapter(this@SANomonalsFrag)
        binding?.nominalslist?.adapter = adapter
    }


    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun AgreementEditViewClick() {
        var saAgreementsBottomSheet = SAAgreementsBottomSheet(R.layout.sa_agreement_dialog)
        saAgreementsBottomSheet?.show(childFragmentManager,"category")
    }




}