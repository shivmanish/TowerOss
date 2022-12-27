package com.smarthub.baseapplication.ui.fragments.services_request

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.NewCustomerDetailFragmentBinding
import com.smarthub.baseapplication.databinding.TabNameItemBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicePageAdapter
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.OpcoTenancyActivity
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel

class ServicesRequestActivity : BaseActivity() {
    private var profileViewModel : SiteInfoViewModel?=null
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    lateinit var binding : NewCustomerDetailFragmentBinding

    companion object{
        var ServiceRequestdata : ServiceRequestAllDataItem?=null
        lateinit var Id : String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewCustomerDetailFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun mapUiData(siteInfoDropDownData: SiteInfoDropDownData){
        this.siteInfoDropDownData = siteInfoDropDownData
    }

    private fun initViews(){
        var data = AppController.getInstance().siteInfoModel.item
        binding.siteId.text="${data?.get(0)?.Basicinfo?.get(0)?.siteID}"
        binding.subTitle.text="${data?.get(0)?.Basicinfo?.get(0)?.siteID}"
        binding.rfiDate.text= "${data?.get(0)?.OperationalInfo?.get(0)?.RFIDate}"
        binding.back.setOnClickListener {
            onBackPressed()
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

        profileViewModel= ViewModelProvider(this)[SiteInfoViewModel::class.java]

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