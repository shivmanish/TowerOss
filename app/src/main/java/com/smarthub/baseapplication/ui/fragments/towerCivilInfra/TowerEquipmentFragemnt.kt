package com.smarthub.baseapplication.ui.fragments.towerCivilInfra
import android.os.Bundle
import com.google.android.material.tabs.TabLayout.MODE_FIXED
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityTowerPoleFragemntBinding
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog

class TowerEquipmentFragemnt : BaseActivity() {
//    private var profileViewModel : SiteInfoViewModel?=null
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    lateinit var binding : ActivityTowerPoleFragemntBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTowerPoleFragemntBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initViews()
    }

    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager,"")
        }
        binding.viewpager.adapter = TowerEquipmentFragmentAdapter(supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewpager)
        if(binding.tabs.tabCount<=5)
            binding.tabs.tabMode = MODE_FIXED
        else
            binding.tabs.tabMode = MODE_SCROLLABLE

    }


}