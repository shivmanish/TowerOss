package com.smarthub.baseapplication.ui.utilites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smarthub.baseapplication.databinding.ActivityBatteryBankDetailsBinding
import com.smarthub.baseapplication.ui.utilites.adapter.BatteryViewpagerAdapter

class BatteryBankDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityBatteryBankDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatteryBankDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

fun initview(){
    binding.viewpager.adapter = BatteryViewpagerAdapter(supportFragmentManager)
    binding.tabs.setupWithViewPager(binding.viewpager)
}


}