package com.smarthub.baseapplication.ui.fragments.siteInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SiteInfoNewFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.*
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.dialog.siteinfo.BasicInfoBottomSheet
import com.smarthub.baseapplication.ui.dialog.siteinfo.GeoConditionsBottomSheet
import com.smarthub.baseapplication.ui.dialog.siteinfo.OperationsInfoBottomSheet
import com.smarthub.baseapplication.ui.dialog.siteinfo.SaftyAccessBottomSheet
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SiteInfoNewFragment(var id : String) : Fragment(), SiteInfoListAdapter.SiteInfoLisListener {
    var dropdowndata: SiteInfoDropDownData ?= null
    lateinit var binding: SiteInfoNewFragmentBinding
    lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = SiteInfoNewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.addMore?.setOnClickListener{
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")
        }

        if (homeViewModel.siteDropData?.hasActiveObservers() == true){
            homeViewModel.siteDropData?.removeObservers(viewLifecycleOwner)
        }
        homeViewModel.siteDropData?.observe(viewLifecycleOwner) {
            if (it!=null){
                dropdowndata = it.data
            }else homeViewModel.fetchSiteDropDownData()
        }

        if (homeViewModel.siteInfoResponse?.hasActiveObservers() == true)
            homeViewModel.siteInfoResponse?.removeObservers(viewLifecycleOwner)
        homeViewModel.siteInfoResponse?.observe(viewLifecycleOwner) {
            binding.swipeLayout.isRefreshing = false
            if (it!=null && it.status == Resource.Status.SUCCESS){
                AppLogger.log("SiteInfoNewFragment Site Data fetched successfully")
                Toast.makeText(requireContext(),"SiteInfoNewFragment error :Site Data fetched successfully",Toast.LENGTH_SHORT).show()
                binding.listItem.adapter = SiteInfoListAdapter(requireContext(), this@SiteInfoNewFragment,it.data?.get(0)!!)
            }else if (it!=null) {
                Toast.makeText(requireContext(),"SiteInfoNewFragment error :${it.message}",Toast.LENGTH_SHORT).show()
            }else{
                AppLogger.log("SiteInfoNewFragment Something went wrong")
                Toast.makeText(requireContext(),"SiteInfoNewFragment Something went wrong",Toast.LENGTH_SHORT).show()
            }
        }

        binding.swipeLayout.setOnRefreshListener {
            homeViewModel.fetchSiteInfoData(id)
        }
    }

    private fun mapUIData(data: SiteInfoModel) {
        (binding.listItem?.adapter as SiteInfoListAdapter).setValueData(data)
    }

    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(), "Item Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun detailsItemClicked(basicinfodata: SiteBasicinfo,id : String) {
        if (dropdowndata != null) {
            val bottomSheetDialogFragment = BasicInfoBottomSheet(R.layout.basic_info_details_bottom_sheet, dropdowndata?.basicInfoModel!!, basicinfodata,id,homeViewModel)
            bottomSheetDialogFragment.show(childFragmentManager, "category")
        } else {
            Toast.makeText(context, "DropDownData not found, Please Try again !", Toast.LENGTH_SHORT).show()
        }
    }

    override fun operationInfoDetailsItemClicked(operationalInfo: List<OperationalInfo>) {
        if (dropdowndata != null) {
            val bottomSheetDialogFragment =
                OperationsInfoBottomSheet(R.layout.operations_info_details_bottom_sheet, dropdowndata?.operationalInfo!!, operationalInfo,homeViewModel)
            bottomSheetDialogFragment.show(childFragmentManager, "category")
        } else {
            Toast.makeText(context, "DropDownData not found, Please Try again !", Toast.LENGTH_SHORT).show()
        }
    }

    override fun geoConditionsDetailsItemClicked(geoCondition: List<GeoCondition>) {
        if (dropdowndata != null) {
            val bottomSheetDialogFragment = GeoConditionsBottomSheet(R.layout.geo_conditions_details_bottom_sheet, dropdowndata?.geoCondition!!, geoCondition)
            bottomSheetDialogFragment.show(childFragmentManager, "category")
        } else {
            Toast.makeText(context, "DropDownData not found, Please Try again !", Toast.LENGTH_SHORT).show()
        }
    }

    override fun siteAccessDetailsItemClicked(safetyAndAccess: List<SafetyAndAcces>) {
        if (dropdowndata != null) {
        val bottomSheetDialogFragment = SaftyAccessBottomSheet(R.layout.safty_access_details_bottom_sheet,dropdowndata?.safetyAndAccess!!,safetyAndAccess)
        bottomSheetDialogFragment.show(childFragmentManager, "category")
        } else {
            Toast.makeText(context, "DropDownData not found, Please Try again !", Toast.LENGTH_SHORT).show()
        }
    }

}