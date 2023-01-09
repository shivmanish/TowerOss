package com.smarthub.baseapplication.ui.utilites
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityBatteryBankDetailsBinding
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.AcUtility
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.BatteryBank
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.UtilitieSmp
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.utilites.adapter.BatteryViewpagerAdapter

class BatteryBankDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityBatteryBankDetailsBinding
    companion object{
        var utilityBatteryBankData:ArrayList<BatteryBank>?=null
        var utilityAcData:ArrayList<AcUtility>?=null
        var id:String?="448"
        var position:Int?=0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatteryBankDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
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
    }


fun initview(){
    if(position==1) {
        binding.title.text = "Battery Bank"
        binding.viewpager.adapter =
            BatteryViewpagerAdapter(supportFragmentManager, utilityBatteryBankData, id!!)
        binding.tabs.setupWithViewPager(binding.viewpager)
        binding.tabs.setupWithViewPager(binding.viewpager)
        if (binding.tabs.tabCount == 1) {
            binding.tabs.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"))
        }
        if (binding.tabs.tabCount <= 3)
            binding.tabs.tabMode = TabLayout.MODE_FIXED
        else
            binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE
    }
    else if(position==3){
        binding.title.text = "DG"
        binding.viewpager.adapter =
            BatteryViewpagerAdapter(supportFragmentManager, utilityBatteryBankData, id!!)
        binding.tabs.setupWithViewPager(binding.viewpager)
        binding.tabs.setupWithViewPager(binding.viewpager)
        if (binding.tabs.tabCount == 1) {
            binding.tabs.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"))
        }
        if (binding.tabs.tabCount <= 5)
            binding.tabs.tabMode = TabLayout.MODE_FIXED
        else
            binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE
    }
}


}