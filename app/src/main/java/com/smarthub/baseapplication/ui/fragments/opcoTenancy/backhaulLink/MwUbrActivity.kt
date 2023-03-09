package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.MwUbrActivityBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkData
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel


class MwUbrActivity : BaseActivity() {
    private var profileViewModel : SiteInfoViewModel?=null
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
        initViews()
    }

    private fun mapUiData(siteInfoDropDownData: SiteInfoDropDownData){
        this.siteInfoDropDownData = siteInfoDropDownData
    }

    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.subTitle.text=AppController.getInstance().siteName
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager,"")
        }

        binding.viewpager.adapter = MwUbrTabAdapter(supportFragmentManager, Backhauldata, parentIndex!!)
        binding.tabs.setupWithViewPager(binding.viewpager)
        if(binding.tabs.tabCount==1) {
            binding.tabs.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"))
        }
        if(binding.tabs.tabCount<=5)
            binding.tabs.tabMode = TabLayout.MODE_FIXED
        else
            binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE

    }



}