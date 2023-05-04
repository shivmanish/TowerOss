package com.smarthub.baseapplication.ui.fragments.rfequipment

import android.os.Bundle
import android.widget.Toast
import com.example.trackermodule.homepage.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityNewSiteAcquisitionBinding
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey
import com.smarthub.baseapplication.ui.fragments.sstSbc.adapter.SstSbcTabAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.Utils

class RfTabActivity : BaseActivity() {
    companion object {
        var rfSurvey: RfSurvey? = null
        var parentIndex: Int? = 0
    }

    lateinit var binding: ActivityNewSiteAcquisitionBinding
    lateinit var adapter: RfTabAdapter
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
        binding.DateLebel.text="Survey Date"
        binding.titel.text="ABCD"
        Toast.makeText(this,"this is called 2", Toast.LENGTH_SHORT).show()
        binding.subTitle.text=AppController.getInstance().siteName
       binding.dateText.text = rfSurvey!!.created_at
        adapter = RfTabAdapter(supportFragmentManager, rfSurvey, parentIndex!!)
        binding.viewpager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewpager)
        if(binding.tabs.tabCount<=3)
            binding.tabs.tabMode = TabLayout.MODE_FIXED
        else
            binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE

    }





}