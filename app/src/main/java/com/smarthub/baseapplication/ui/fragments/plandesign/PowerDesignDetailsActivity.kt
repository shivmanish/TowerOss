package com.smarthub.baseapplication.ui.fragments.plandesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.ActivityPlanDesignDetailsBinding
import com.smarthub.baseapplication.ui.fragments.plandesign.fragment.*
import com.smarthub.baseapplication.ui.utilites.adapter.BatteryViewpagerAdapter

class PowerDesignDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityPlanDesignDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanDesignDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

fun initview(){
    val fragmentlist = ArrayList<Fragment>()
    fragmentlist.add(EquipRoomFragment())
    fragmentlist.add(TowerCivilFragment())
    fragmentlist.add(PowerFragment())
    fragmentlist.add(EquipUtilityFragment())
    fragmentlist.add(NocCompFragment())
    val titels = ArrayList<String>()
    titels.add("Equipment Room")
    titels.add("Tower & Civil")
    titels.add("Power")
    titels.add("Utility Equip")
    titels.add("NOC & Comp")
    binding.viewpager.adapter = BatteryViewpagerAdapter(supportFragmentManager,fragmentlist,titels)
    binding.tabs.setupWithViewPager(binding.viewpager)
}


}