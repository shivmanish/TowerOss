package com.smarthub.baseapplication.ui.fragments.utilites.dg

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityDgDetailsBinding
import com.smarthub.baseapplication.databinding.ActivitySmpsdetailsBinding
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAllData
import com.smarthub.baseapplication.ui.utilites.adapter.DGViewpagerAdapter
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.utilites.adapter.SMPSViewpagerAdapter
import com.smarthub.baseapplication.ui.fragments.utilites.batteryBank.BatteryBankDetailsActivity
import com.smarthub.baseapplication.ui.fragments.utilites.batteryBank.adapters.BatterryBankViewpagerAdapter
import com.smarthub.baseapplication.ui.utilites.fragment.DGFragment

class DGDetailsActivity : AppCompatActivity() {
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
            binding.viewpager.adapter = BatterryBankViewpagerAdapter(supportFragmentManager, utilityData?.UtilityEquipmentBatteryBank, utilityData?.id)
        else
            binding.viewpager.adapter = SMPSViewpagerAdapter(supportFragmentManager, ArrayList(), utilityData?.id)
        binding.tabs.setupWithViewPager(binding.viewpager)
        if(binding.tabs.tabCount==1) {
            binding.tabs.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"))
        }
        if(binding.tabs.tabCount<=5)
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
        binding.title.text="DG"
        if (utilityData!=null )
            binding.Count.text= utilityData?.UtilityEquipmentDG?.size.toString()
        else
            binding.Count.text="0"
    }


}