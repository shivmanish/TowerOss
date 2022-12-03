package com.smarthub.baseapplication.ui.utilites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.ActivityBatteryBankDetailsBinding
import com.smarthub.baseapplication.ui.utilites.adapter.BatteryViewpagerAdapter
import com.smarthub.baseapplication.ui.utilites.fragment.BatteryFragment

class BatteryBankDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityBatteryBankDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatteryBankDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

fun initview(){
    val fragmentlist = ArrayList<Fragment>()
    fragmentlist.add(BatteryFragment())
    fragmentlist.add(BatteryFragment())
    val titels = ArrayList<String>()
    titels.add("Battery Bank#1")
    titels.add("Battery Bank#2")
    binding.viewpager.adapter = BatteryViewpagerAdapter(supportFragmentManager,fragmentlist,titels)
    binding.tabs.setupWithViewPager(binding.viewpager)
}


}