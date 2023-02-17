package com.smarthub.baseapplication.ui.taskUi

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
import com.smarthub.baseapplication.databinding.TaskDynamicTitleListBinding
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

class TaskDynamicSiteFragment(var slectedTabs:ArrayList<String>,var childIndex:Int): BaseFragment() {

    lateinit var binding: TaskDynamicTitleListBinding
    lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = TaskDynamicTitleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemCollapse.visibility=View.VISIBLE
        binding.collapsingLayout.setOnClickListener {
            if (binding.itemCollapse.visibility==View.VISIBLE){
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                binding.itemCollapse.visibility = View.GONE
                binding.itemLine.visibility=View.VISIBLE
                binding.imgEdit.visibility=View.GONE
            }
            else
            {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                binding.itemCollapse.visibility = View.VISIBLE
                binding.itemLine.visibility=View.GONE
                binding.imgEdit.visibility=View.VISIBLE
            }
        }
        val json = Utils.getJsonDataFromAsset(requireContext(),"dynamic_list.json")
        val model : DynamicTitleList = Gson().fromJson(json,DynamicTitleList::class.java)
        var data=model.data[childIndex]
        binding.itemTitleStr.text=data.title
        binding.itemCollapse.adapter = DynamicItemListAdapter(data.listData,
            object : DynamicItemListAdapter.DynamicItemListAdapterListener{
            override fun onDateFieldFind(text: TextView) {
                setDatePickerView(text)
            }

        })
//            DynamicTitleListAdapter(model,object : DynamicItemListAdapter.DynamicItemListAdapterListener{
//            override fun onDateFieldFind(text: TextView) {
//                setDatePickerView(text)
//            }
//
//        })
    }

    override fun onDestroy() {
        if (homeViewModel.siteInfoDataResponse?.hasActiveObservers() == true)
            homeViewModel.siteInfoDataResponse?.removeObservers(viewLifecycleOwner)
        super.onDestroy()
    }

}