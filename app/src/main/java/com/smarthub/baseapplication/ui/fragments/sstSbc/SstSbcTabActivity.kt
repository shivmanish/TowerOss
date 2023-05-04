package com.smarthub.baseapplication.ui.fragments.sstSbc

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityNewSiteAcquisitionBinding
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.sstSbc.adapter.SstSbcTabAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.Utils

class SstSbcTabActivity : BaseActivity() {
    companion object {
        var sstSbcData: SstSbcAllData? = null
        var parentIndex: Int? = 0
    }

    lateinit var binding: ActivityNewSiteAcquisitionBinding
    lateinit var adapter: SstSbcTabAdapter
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
        binding.DateLebel.text="PO Date"
        binding.titel.text="SST / SBC"
        binding.subTitle.text=AppController.getInstance().siteName
        if (sstSbcData!=null && sstSbcData?.SstSbcTeam?.isNotEmpty()==true && sstSbcData?.SstSbcTeam?.get(0)?.PODate?.isNotEmpty()==true)
            binding.dateText.text=Utils.getFormatedDate(sstSbcData?.SstSbcTeam?.get(0)?.PODate,"dd-MMM-yyyy")
        binding.addMore.setOnClickListener {
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager, "")
        }
        adapter = SstSbcTabAdapter(supportFragmentManager, sstSbcData, parentIndex!!)
        binding.viewpager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewpager)
        if(binding.tabs.tabCount<=3)
            binding.tabs.tabMode = TabLayout.MODE_FIXED
        else
            binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE

    }





}