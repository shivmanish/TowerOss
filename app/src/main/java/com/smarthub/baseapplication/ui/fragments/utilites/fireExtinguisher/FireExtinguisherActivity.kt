package com.smarthub.baseapplication.ui.fragments.utilites.fireExtinguisher

import android.graphics.Color
import android.os.Bundle
import com.example.trackermodule.homepage.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivitySmpsdetailsBinding
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAllData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.utilites.ac.adapters.ACViewpagerAdapter
import com.smarthub.baseapplication.ui.fragments.utilites.fireExtinguisher.adapters.FireExtViewpagerAdapter

class FireExtinguisherActivity : BaseActivity() {
    lateinit var binding: ActivitySmpsdetailsBinding
    companion object{
        var utilityData: UtilityEquipmentAllData?=null
        var id:String?="448"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySmpsdetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        if (utilityData !=null)
            binding.viewpager.adapter = FireExtViewpagerAdapter(supportFragmentManager, utilityData?.UtilityEquipmentFireExtinguisher, utilityData?.id)
        else
            binding.viewpager.adapter = FireExtViewpagerAdapter(supportFragmentManager, ArrayList(), utilityData?.id)
        binding.tabs.setupWithViewPager(binding.viewpager)
        if(binding.tabs.tabCount==1) {
            binding.tabs.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"))
        }
        if(binding.tabs.tabCount<=3)
            binding.tabs.tabMode = TabLayout.MODE_FIXED
        else
            binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE

        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager,"")
        }
        binding.title.text="Fire Extinguisher"
        if (utilityData!=null )
            binding.Count.text= utilityData?.UtilityEquipmentFireExtinguisher?.size.toString()
        else
            binding.Count.text="0"
    }


}