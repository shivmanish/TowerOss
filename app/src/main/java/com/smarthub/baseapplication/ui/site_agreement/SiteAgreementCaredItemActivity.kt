package com.smarthub.baseapplication.ui.site_agreement

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityNewSiteAcquisitionBinding
import com.smarthub.baseapplication.databinding.TabNameItemBinding
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.Siteacquisition
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.site_agreement.adapter.SiteAgreemetAdapter

class SiteAgreementCaredItemActivity : BaseActivity() {

    companion object{
        var siteacquisition : Siteacquisition ?=null
    }
    lateinit var binding : ActivityNewSiteAcquisitionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewSiteAcquisitionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.viewpager.adapter = SiteAgreemetAdapter(supportFragmentManager)
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
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager,"")
        }
    }

    private fun onTabSelectedListener(): TabLayout.OnTabSelectedListener? {
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