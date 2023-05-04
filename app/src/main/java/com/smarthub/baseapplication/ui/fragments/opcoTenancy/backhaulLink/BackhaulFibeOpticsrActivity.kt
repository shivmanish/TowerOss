package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.databinding.MwUbrActivityBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkData
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.utils.AppController


class BackhaulFibeOpticsrActivity : BaseActivity() {
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    lateinit var binding : MwUbrActivityBinding

    companion object{
        var Backhauldata : BackhaulLinkData?=null
        var parentIndex:Int?=0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MwUbrActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        binding.subTitle.text=AppController.getInstance().siteName
        binding.titel.text="Fiber Optics"
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager,"")
        }

        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.viewpager.adapter = FiberOpticsTabAdapter(supportFragmentManager, Backhauldata, parentIndex!!)
        binding.tabs.setupWithViewPager(binding.viewpager)
        if(binding.tabs.tabCount==1) {
            binding.tabs.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"))
        }
        if(binding.tabs.tabCount<=3)
            binding.tabs.tabMode = TabLayout.MODE_FIXED
        else
            binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE
    }




}