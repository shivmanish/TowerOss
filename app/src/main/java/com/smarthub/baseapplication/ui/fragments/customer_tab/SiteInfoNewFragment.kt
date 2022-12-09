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
import com.smarthub.baseapplication.model.siteInfo.Basicinfo
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModelDropDown
import com.smarthub.baseapplication.ui.dialog.siteinfo.BasicInfoBottomSheet
import com.smarthub.baseapplication.ui.dialog.siteinfo.GeoConditionsBottomSheet
import com.smarthub.baseapplication.ui.dialog.siteinfo.OperationsInfoBottomSheet
import com.smarthub.baseapplication.ui.dialog.siteinfo.SaftyAccessBottomSheet
import com.smarthub.baseapplication.ui.fragments.siteInfo.SiteInfoListAdapter
import com.smarthub.baseapplication.viewmodels.BasicInfoDetailViewModel
import kotlinx.android.synthetic.main.search_fragment.*

class SiteInfoNewFragment :Fragment(), SiteInfoListAdapter.SiteInfoLisListener {
    var binding : SiteInfoNewFragmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        siteViewModel = ViewModelProvider(requireActivity())[BasicInfoDetailViewModel::class.java]
        binding = SiteInfoNewFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
    lateinit var siteViewModel : BasicInfoDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = SiteInfoListAdapter(requireContext(),this@SiteInfoNewFragment)
        siteViewModel.fetchDropDown()
        siteViewModel.dropDownResponse?.observe(requireActivity()) {
            (binding?.listItem?.adapter as SiteInfoListAdapter).setData(it.basicInfoModel)
        }
        if (siteViewModel.siteInfoResponse?.hasActiveObservers() == true)
            siteViewModel.siteInfoResponse?.removeObservers(viewLifecycleOwner)

        siteViewModel.siteInfoResponse?.observe(viewLifecycleOwner){
           /* if (it?.data != null){
//                map data here
                it.data?.let { it1 -> mapUIData(it1) }
                Toast.makeText(requireContext(),"siteInfo fetched successfully",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"siteInfo fetched successfully but null",Toast.LENGTH_SHORT).show()
            }*/

        }
        siteViewModel.fetchSiteInfo()
    }

    private fun mapUIData(data : SiteInfoModel){
        (binding?.listItem?.adapter as SiteInfoListAdapter).setValueData(data)
    }

    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()
    }
    override fun detailsItemClicked(data: BasicInfoModelDropDown, basicinfodata: Basicinfo) {
        val bottomSheetDialogFragment = BasicInfoBottomSheet(R.layout.basic_info_details_bottom_sheet,data,basicinfodata!!)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }
    override fun operationInfoDetailsItemClicked() {
        val bottomSheetDialogFragment = OperationsInfoBottomSheet(R.layout.operations_info_details_bottom_sheet)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }
    override fun geoConditionsDetailsItemClicked() {
        val bottomSheetDialogFragment = GeoConditionsBottomSheet(R.layout.geo_conditions_details_bottom_sheet)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }

    override fun siteAccessDetailsItemClicked() {
        val bottomSheetDialogFragment = SaftyAccessBottomSheet(R.layout.safty_access_details_bottom_sheet)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

}