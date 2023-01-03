package com.smarthub.baseapplication.ui.fragments.towerCivilInfra
import android.graphics.Color
import android.os.Bundle
import com.google.android.material.tabs.TabLayout.MODE_FIXED
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityTowerPoleFragemntBinding
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraEquipmentModel
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog

class TowerEquipmentFragemnt : BaseActivity() {
    lateinit var binding : ActivityTowerPoleFragemntBinding
    companion object{
        var EquipmentModelData : ArrayList<TowerAndCivilInfraEquipmentModel>?=null
        var Id : String?="448"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTowerPoleFragemntBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
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
        binding.viewpager.adapter = TowerEquipmentFragmentAdapter(supportFragmentManager,
            EquipmentModelData, Id)
        binding.tabs.setupWithViewPager(binding.viewpager)
        if(binding.tabs.tabCount==1) {
            binding.tabs.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"))
        }
        if(binding.tabs.tabCount<=2) {
            binding.tabs.tabMode = MODE_FIXED

        }else {
            binding.tabs.tabMode = MODE_SCROLLABLE
        }

    }




}