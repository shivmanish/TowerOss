package com.smarthub.baseapplication.ui.fragments.customer_tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SiteInfoNewFragmentBinding
import com.smarthub.baseapplication.ui.dialog.opco.BasicInfoBottomSheet
import com.smarthub.baseapplication.ui.dialog.opco.GeoConditionsBottomSheet
import com.smarthub.baseapplication.ui.dialog.opco.OperationsInfoBottomSheet
import com.smarthub.baseapplication.ui.dialog.opco.SaftyAccessBottomSheet
import com.smarthub.baseapplication.ui.fragments.siteInfo.SiteInfoListAdapter
import com.smarthub.baseapplication.viewmodels.BasicInfoDetailViewModel

class SiteInfoNewFragment :Fragment(), SiteInfoListAdapter.SiteInfoLisListener {
    var binding : SiteInfoNewFragmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = SiteInfoNewFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = SiteInfoListAdapter(this@SiteInfoNewFragment)
        val siteViewModel = ViewModelProvider(requireActivity())[BasicInfoDetailViewModel::class.java]
        siteViewModel.fetchDropDown()
        siteViewModel.dropDownResponse?.observe(requireActivity()) {
            (binding?.listItem?.adapter as SiteInfoListAdapter).setData(it.basicInfoModel)
        }
    }

    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()
    }
    override fun detailsItemClicked() {
        val bottomSheetDialogFragment = BasicInfoBottomSheet(R.layout.basic_info_details_bottom_sheet)
        bottomSheetDialogFragment?.show(childFragmentManager,"category")

    }
    override fun operationInfoDetailsItemClicked() {
        val bottomSheetDialogFragment = OperationsInfoBottomSheet(R.layout.operations_info_details_bottom_sheet)
        bottomSheetDialogFragment?.show(childFragmentManager,"category")

    }
    override fun geoConditionsDetailsItemClicked() {
        val bottomSheetDialogFragment = GeoConditionsBottomSheet(R.layout.geo_conditions_details_bottom_sheet)
        bottomSheetDialogFragment?.show(childFragmentManager,"category")

    }

    override fun siteAccessDetailsItemClicked() {
        val bottomSheetDialogFragment = SaftyAccessBottomSheet(R.layout.safty_access_details_bottom_sheet)
        bottomSheetDialogFragment?.show(childFragmentManager,"category")
    }

}