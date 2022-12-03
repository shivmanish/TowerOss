package com.smarthub.baseapplication.ui.utilites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityBatteryBankDetailsBinding
import com.smarthub.baseapplication.databinding.ActivitySmpsdetailsBinding
import com.smarthub.baseapplication.ui.utilites.adapter.BatteryViewpagerAdapter
import com.smarthub.baseapplication.ui.utilites.adapter.SMPSViewpagerAdapter

class SMPSDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySmpsdetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySmpsdetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    fun initview(){
        binding.viewpager.adapter = SMPSViewpagerAdapter(supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewpager)
    }
}