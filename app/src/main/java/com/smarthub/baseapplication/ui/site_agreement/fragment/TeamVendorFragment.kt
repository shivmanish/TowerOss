package com.smarthub.baseapplication.ui.site_agreement.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TeamVendorFragmentBinding
import com.smarthub.baseapplication.ui.dialog.siteinfo.TeamVendorDetailsBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.site_agreement.adapter.TeamVendorFragAdapter
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TeamVendorFragment : BaseFragment(), TeamVendorFragAdapter.TeamVendorListItemListner {
    var binding : TeamVendorFragmentBinding?=null
    lateinit var viewmodel: HomeViewModel
    lateinit var adapter: TeamVendorFragAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TeamVendorFragmentBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding?.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= TeamVendorFragAdapter(this@TeamVendorFragment)
        binding?.teamVendorList?.adapter = adapter


    }

    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }
    override fun EditdetailsItemClicked() {
        var bottomSheetDialogFragment = TeamVendorDetailsBottomSheet(
            R.layout.teamvender_details_botom_sheet,
            null,
            null,
            viewmodel,
            ""
        )
        bottomSheetDialogFragment?.show(childFragmentManager,"category")
    }

}