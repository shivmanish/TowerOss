package com.smarthub.baseapplication.ui.utilites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.ActivityAcDetailsBinding
import com.smarthub.baseapplication.databinding.ActivityBatteryBankDetailsBinding
import com.smarthub.baseapplication.ui.utilites.adapter.BatteryViewpagerAdapter
import com.smarthub.baseapplication.ui.utilites.fragment.AcFragment
import com.smarthub.baseapplication.ui.utilites.fragment.BatteryFragment

class AcDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityAcDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    fun initview(){
        val fragmentlist = ArrayList<Fragment>()
        fragmentlist.add(AcFragment())
        fragmentlist.add(AcFragment())
        val titels = ArrayList<String>()
        titels.add("AC #1")
        titels.add("AC #1")
        binding.viewpager.adapter = BatteryViewpagerAdapter(supportFragmentManager,fragmentlist,titels)
        binding.tabs.setupWithViewPager(binding.viewpager)
    }


}