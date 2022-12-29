package com.smarthub.baseapplication.ui.site_agreement.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NominalsFragmentBinding
import com.smarthub.baseapplication.ui.dialog.siteinfo.NominalsDetailsBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.site_agreement.adapter.NominalsFragmentAdapter

class Nominals :BaseFragment(), NominalsFragmentAdapter.SiteLeaseListListener {
    lateinit var adapter : NominalsFragmentAdapter
    var binding : NominalsFragmentBinding?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NominalsFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=NominalsFragmentAdapter(this@Nominals)
        binding?.nominalslist?.adapter = adapter
    }


    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()
    }
    override fun EditdetailsItemClicked() {
        var bottomSheetDialogFragment = NominalsDetailsBottomSheet(R.layout.nominals_details_bottom_sheet)
        bottomSheetDialogFragment?.show(childFragmentManager,"category")
    }


}