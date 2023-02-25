package com.smarthub.baseapplication.ui.fragments.siteInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SiteInfoNewFragmentBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.siteInfoData.GeoCondition
import com.smarthub.baseapplication.model.siteInfo.siteInfoData.OperationalInfo
import com.smarthub.baseapplication.model.siteInfo.siteInfoData.SafetyAndAcces
import com.smarthub.baseapplication.model.siteInfo.siteInfoData.SiteBasicinfo
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.adapter.DynamicItemListAdapter
import com.smarthub.baseapplication.ui.adapter.DynamicTitleListAdapter
import com.smarthub.baseapplication.ui.dialog.siteinfo.BasicInfoBottomSheet
import com.smarthub.baseapplication.ui.dialog.siteinfo.GeoConditionsBottomSheet
import com.smarthub.baseapplication.ui.dialog.siteinfo.OperationsInfoBottomSheet
import com.smarthub.baseapplication.ui.dialog.siteinfo.SaftyAccessBottomSheet
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.dynamic.DynamicTitleList
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SiteInfoNewFragment(var id : String) : BaseFragment(), SiteInfoListAdapter.SiteInfoLisListener {
    var dropdowndata: SiteInfoDropDownData ?= null
    lateinit var binding: SiteInfoNewFragmentBinding
    lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = SiteInfoNewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addMore.setOnClickListener{
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")
        }
//        val json = Utils.getJsonDataFromAsset(requireContext(),"dynamic_list.json")
//        val model : DynamicTitleList = Gson().fromJson(json,DynamicTitleList::class.java)
//        binding.listItem.adapter = DynamicTitleListAdapter(model,object : DynamicItemListAdapter.DynamicItemListAdapterListener{
//            override fun onDateFieldFind(text: TextView) {
//                setDatePickerView(text)
//            }
//
//        })

        dropdowndata = AppPreferences.getInstance().dropDown

        if (homeViewModel.siteInfoDataResponse?.hasActiveObservers() == true)
            homeViewModel.siteInfoDataResponse?.removeObservers(viewLifecycleOwner)
        homeViewModel.siteInfoDataResponse?.observe(viewLifecycleOwner) {
//            binding.swipeLayout.isRefreshing = false
            if (it!=null && it.status == Resource.Status.LOADING){
               showLoader()
                return@observe
            }
            if (it!=null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("SiteInfoNewFragment Site Data fetched successfully: ${it.data?.item?.get(0)!!}")
//                Toast.makeText(requireContext(),"SiteInfoNewFragment error :Site Data fetched successfully",Toast.LENGTH_SHORT).show()
                var currentOpened = -1
                if (binding.listItem.adapter is SiteInfoListAdapter){
                    val adapter = binding.listItem.adapter as SiteInfoListAdapter
                    currentOpened = adapter.currentOpened
                }
                binding.listItem.adapter = SiteInfoListAdapter(requireContext(), this@SiteInfoNewFragment,it.data.item?.get(0)!!)
                AppLogger.log("currentOpened:$currentOpened")
                if (currentOpened>=0){
                    (binding.listItem.adapter as SiteInfoListAdapter).updateList(currentOpened)
                }
            }else if (it!=null) {
                AppLogger.log("SiteInfoNewFragment error :${it.message}")

                Toast.makeText(requireContext(),"SiteInfoNewFragment error :${it.message}",Toast.LENGTH_SHORT).show()
            }else{
                AppLogger.log("SiteInfoNewFragment Something went wrong")
                Toast.makeText(requireContext(),"SiteInfoNewFragment Something went wrong",Toast.LENGTH_SHORT).show()
            }
        }

//        binding.swipeLayout.setOnRefreshListener {
//            homeViewModel.siteInfoRequestAll(id)
//        }
        homeViewModel.siteInfoRequestAll(id)
    }

    override fun onDestroy() {
        if (homeViewModel.siteInfoDataResponse?.hasActiveObservers() == true)
            homeViewModel.siteInfoDataResponse?.removeObservers(viewLifecycleOwner)
        super.onDestroy()
    }


    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(), "Item Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun detailsItemClicked(siteBasicinfo: SiteBasicinfo, id : String) {
        val bottomSheetDialogFragment = BasicInfoBottomSheet(R.layout.basic_info_details_bottom_sheet, siteBasicinfo,id,homeViewModel)
        bottomSheetDialogFragment.show(childFragmentManager, "category")
    }

    override fun operationInfoDetailsItemClicked(operationalInfo: OperationalInfo, id : String) {
        if (dropdowndata != null) {
            val bottomSheetDialogFragment =
                OperationsInfoBottomSheet(R.layout.operations_info_details_bottom_sheet, id, dropdowndata?.operationalInfo!!, operationalInfo,homeViewModel)
            bottomSheetDialogFragment.show(childFragmentManager, "category")
        } else {
            Toast.makeText(context, "DropDown, Please Try again !", Toast.LENGTH_SHORT).show()
        }
    }

    override fun geoConditionsDetailsItemClicked(geoCondition: GeoCondition, id : String) {
        if (dropdowndata != null) {
            val bottomSheetDialogFragment = GeoConditionsBottomSheet(R.layout.geo_conditions_details_bottom_sheet,id, dropdowndata?.geoCondition!!, geoCondition,homeViewModel)
            bottomSheetDialogFragment.show(childFragmentManager, "category")
        } else {
            Toast.makeText(context, "DropDown, Please Try again !", Toast.LENGTH_SHORT).show()
        }
    }

    override fun siteAccessDetailsItemClicked(safetyAndAccess: SafetyAndAcces, id : String) {
        if (dropdowndata != null) {
        val bottomSheetDialogFragment = SaftyAccessBottomSheet(R.layout.safty_access_details_bottom_sheet,id,dropdowndata?.safetyAndAccess!!,safetyAndAccess,homeViewModel)
        bottomSheetDialogFragment.show(childFragmentManager, "category")
        } else {
            Toast.makeText(context, "DropDown, Please Try again !", Toast.LENGTH_SHORT).show()
        }
    }

}