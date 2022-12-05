package com.smarthub.baseapplication.ui.utilites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.ActivityAcDetailsBinding
import com.smarthub.baseapplication.databinding.ActivityBatteryBankDetailsBinding
import com.smarthub.baseapplication.databinding.ActivityDetailActivityBinding
import com.smarthub.baseapplication.databinding.ActivityDgDetailsBinding
import com.smarthub.baseapplication.databinding.ActivityFireExtinguisherDetailsBinding
import com.smarthub.baseapplication.databinding.ActivitySpdDetailsBinding
import com.smarthub.baseapplication.ui.utilites.adapter.BatteryViewpagerAdapter
import com.smarthub.baseapplication.ui.utilites.fragment.AcFragment
import com.smarthub.baseapplication.ui.utilites.fragment.BatteryFragment
import com.smarthub.baseapplication.ui.utilites.fragment.FireExtinguisherFragment

class SurgeProtectionDeviceDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivitySpdDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpdDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    fun initview(){
        val fragmentlist = ArrayList<Fragment>()
        fragmentlist.add(FireExtinguisherFragment())
        fragmentlist.add(FireExtinguisherFragment())
        val titels = ArrayList<String>()
        titels.add("SPD #1")
        titels.add("SPD #2")
        binding.viewpager.adapter = BatteryViewpagerAdapter(supportFragmentManager,fragmentlist,titels)
        binding.tabs.setupWithViewPager(binding.viewpager)
    }


}