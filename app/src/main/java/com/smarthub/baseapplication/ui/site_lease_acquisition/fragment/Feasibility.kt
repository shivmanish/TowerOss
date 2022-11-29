package com.smarthub.baseapplication.ui.site_lease_acquisition.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FeasibilityFragmentBinding
import com.smarthub.baseapplication.ui.dialog.siteinfo.DetailsBottomSheet
import com.smarthub.baseapplication.ui.site_lease_acquisition.adapter.FeasibilityLeaseListAdapter

class Feasibility :Fragment(), FeasibilityLeaseListAdapter.FeasibilityListItemListener {
    var binding : FeasibilityFragmentBinding?=null
    var bottomSheetDialogFragment : DetailsBottomSheet?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FeasibilityFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding?.feasibilityList?.adapter = FeasibilityLeaseListAdapter(this@Feasibility)
        initViews(view)
    }

    fun initViews(view: View){
//        var b = view.findViewById<View>(R.id.attach_card)
//        b.setOnClickListener {
//
//        }
    }


    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()
    }
    override fun detailsItemClicked() {
        if (bottomSheetDialogFragment==null) {
            bottomSheetDialogFragment = DetailsBottomSheet(R.layout.building_details_botom_sheet)
            bottomSheetDialogFragment?.show(childFragmentManager,"category")
        }else bottomSheetDialogFragment?.show(childFragmentManager,"category")

    }
}