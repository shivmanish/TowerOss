package com.smarthub.baseapplication.ui.site_lease_acquisition.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TeamVendorFragmentBinding
import com.smarthub.baseapplication.ui.dialog.opco.DetailsBottomSheet
import com.smarthub.baseapplication.ui.site_lease_acquisition.adapter.Team_VendorLeaseListAdapter

class TeamVendor :Fragment(), Team_VendorLeaseListAdapter.TeamVendorListItemListner {
    var binding : TeamVendorFragmentBinding?=null
    var bottomSheetDialogFragment : DetailsBottomSheet?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TeamVendorFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.teamVendorList?.adapter = Team_VendorLeaseListAdapter(this@TeamVendor)
      }
      override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()
     }
    override fun detailsItemClicked() {
        if (bottomSheetDialogFragment==null) {
            bottomSheetDialogFragment = DetailsBottomSheet(R.layout.teamvender_details_botom_sheet)
            bottomSheetDialogFragment?.show(childFragmentManager,"category")
        }else bottomSheetDialogFragment?.show(childFragmentManager,"category")

    }

}