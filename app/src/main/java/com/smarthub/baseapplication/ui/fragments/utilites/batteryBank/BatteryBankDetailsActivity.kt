package com.smarthub.baseapplication.ui.fragments.utilites.batteryBank

import android.graphics.Color
import android.os.Bundle
import com.example.trackermodule.homepage.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivitySmpsdetailsBinding
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAllData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.utilites.adapter.SMPSViewpagerAdapter
import com.smarthub.baseapplication.ui.fragments.utilites.batteryBank.adapters.BatterryBankViewpagerAdapter

class BatteryBankDetailsActivity : BaseActivity() {
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
        initview()

        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager,"")
        }
        binding.title.text="Battery Bank"
        if (utilityData!=null )
            binding.Count.text= utilityData?.UtilityEquipmentBatteryBank?.size.toString()
        else
            binding.Count.text="0"
    }

    fun initview(){
        if (utilityData!=null )
            binding.viewpager.adapter = BatterryBankViewpagerAdapter(supportFragmentManager, utilityData?.UtilityEquipmentBatteryBank,utilityData?.id)
        else
            binding.viewpager.adapter = SMPSViewpagerAdapter(supportFragmentManager, ArrayList(),utilityData?.id)
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