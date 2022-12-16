package com.smarthub.baseapplication.ui.fragments.powerConnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.ActivityBatteryBankDetailsBinding
import com.smarthub.baseapplication.databinding.ActivityPowerConnectionDetailsBinding
import com.smarthub.baseapplication.ui.fragments.powerConnection.fragment.EbBillsFragment
import com.smarthub.baseapplication.ui.fragments.powerConnection.fragment.EbConnectionFragment
import com.smarthub.baseapplication.ui.fragments.powerConnection.fragment.EbPaymentFragment
import com.smarthub.baseapplication.ui.utilites.adapter.BatteryViewpagerAdapter
import com.smarthub.baseapplication.ui.utilites.fragment.BatteryFragment

class PowerConnectionDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityPowerConnectionDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPowerConnectionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

fun initview(){
    val fragmentlist = ArrayList<Fragment>()
    fragmentlist.add(EbConnectionFragment())
    fragmentlist.add(EbBillsFragment())
    fragmentlist.add(EbPaymentFragment())
    val titels = ArrayList<String>()
    titels.add("EB Connection")
    titels.add("EB Bills")
    titels.add("EB Payments")
    binding.viewpager.adapter = BatteryViewpagerAdapter(supportFragmentManager,fragmentlist,titels)
    binding.tabs.setupWithViewPager(binding.viewpager)
}


}