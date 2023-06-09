package com.smarthub.baseapplication.ui.fragments.services_request

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.databinding.NewCustomerDetailFragmentBinding
import com.smarthub.baseapplication.databinding.TabNameItemBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicePageAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger

class ServicesRequestActivity : BaseActivity() {
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    lateinit var binding : NewCustomerDetailFragmentBinding

    companion object{
        var ServiceRequestdata : ServiceRequestAllDataItem?=null
        var Id : String?="448"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewCustomerDetailFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }


    private fun initViews(){
        try {
            val data = AppController.getInstance().siteInfoModel.item
            binding.siteId.text="${data?.get(0)?.Basicinfo?.get(0)?.siteID}"
            binding.subTitle.text="${data?.get(0)?.Basicinfo?.get(0)?.siteID}"
            binding.rfiDate.text= "${data?.get(0)?.OperationalInfo?.get(0)?.RFIDate}"
        }catch (e:java.lang.Exception){
            AppLogger.log("e:${e.localizedMessage}")
        }
        binding.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.addMore.setOnClickListener{
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager,"")
        }
        binding.viewpager.adapter = ServicePageAdapter(supportFragmentManager, ServiceRequestdata!!)
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