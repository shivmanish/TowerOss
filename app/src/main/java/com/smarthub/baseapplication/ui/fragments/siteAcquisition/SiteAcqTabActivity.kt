package com.smarthub.baseapplication.ui.fragments.siteAcquisition

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityNewSiteAcquisitionBinding
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.SiteAcquisitionTabAdapter
import com.smarthub.baseapplication.utils.AppController

class SiteAcqTabActivity : BaseActivity() {
    companion object {
        var siteacquisition: NewSiteAcquiAllData? = null
        var parentIndex: Int? = 0
    }

    lateinit var binding: ActivityNewSiteAcquisitionBinding
    lateinit var adapter: SiteAcquisitionTabAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewSiteAcquisitionBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.titel.text="Site Acquisition #${parentIndex?.plus(1)}"
        binding.subTitle.text=AppController.getInstance().siteName
        binding.addMore.setOnClickListener {
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager, "")
        }
        adapter = SiteAcquisitionTabAdapter(supportFragmentManager, siteacquisition, parentIndex!!)
        binding.viewpager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewpager)
        if(binding.tabs.tabCount<=3)
            binding.tabs.tabMode = TabLayout.MODE_FIXED
        else
            binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE

    }





}