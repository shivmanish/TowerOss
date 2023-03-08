package com.smarthub.baseapplication.ui.fragments.acquisitionSurvey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentAcquisitionSurveyMainBinding
import com.smarthub.baseapplication.databinding.TabNameItemBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.acquisitionSurvey.AcquisitionSurveyModel
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.acquisitionSurvey.adapter.AcqquisitionSurverPagerAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AcquisitionSurveyFragmentNew(var Id : String) : BaseFragment() {
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    var viewmodel: HomeViewModel?=null
    lateinit var binding : FragmentAcquisitionSurveyMainBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAcquisitionSurveyMainBinding.inflate(inflater)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewmodel?.acquisitionSurveyAllDataItem?.hasActiveObservers() == true){
            viewmodel?.acquisitionSurveyAllDataItem?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.acquisitionSurveyAllDataItem?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS ){
                initViews(it.data)
            }
            else if (it!=null) {
                Toast.makeText(requireContext(),"Service request Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("Service Request Fragment Something went wrong")
                Toast.makeText(requireContext(),"Service Request Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
        viewmodel?.siteAcquisitionSurveyById(Id)
    }
    var isDataLoaded = false
    override fun onViewPageSelected() {
        super.onViewPageSelected()
        if (viewmodel!=null && !isDataLoaded){
            viewmodel?.siteAcquisitionSurveyById(Id)
            showLoader()
        }
        AppLogger.log("onViewPageSelected AcquisitionSurveyFragmentNew")
    }

    private fun initViews(ServiceRequestdata : AcquisitionSurveyModel){
        isDataLoaded = true
        try {
            val data = AppController.getInstance().siteInfoModel.item
            binding.siteId.text="${data?.get(0)?.Basicinfo?.get(0)?.siteID}"
            binding.subTitle.text="${data?.get(0)?.Basicinfo?.get(0)?.siteID}"
            binding.rfiDate.text= "${data?.get(0)?.OperationalInfo?.get(0)?.RFIDate}"
        }catch (e:java.lang.Exception){
            AppLogger.log("e:${e.localizedMessage}")
        }
        binding.viewpager.adapter = AcqquisitionSurverPagerAdapter(childFragmentManager, ServiceRequestdata)
        binding.tabs.setupWithViewPager(binding.viewpager)
        binding.tabs.setOnTabSelectedListener(onTabSelectedListener())
        binding.viewpager.beginFakeDrag()
        for (i in 0..binding.tabs.tabCount.minus(1)){
            if (i==0)
                binding.tabs.getTabAt(i)?.view?.setBackgroundResource(R.color.white)
            val itemBinding = TabNameItemBinding.inflate(layoutInflater)
            itemBinding.tabName.text = binding.viewpager.adapter?.getPageTitle(i)
            itemBinding.tabName.textSize = 12f
            binding.tabs.getTabAt(i)?.customView = itemBinding.root
        }

    }

    private fun onTabSelectedListener(): TabLayout.OnTabSelectedListener {
        return object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.color.white)
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.color.tab_deselected)
            }
            override fun onTabReselected(tab: TabLayout.Tab) {}
        }
    }

}