package com.smarthub.baseapplication.ui.fragments.powerAndFuel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.ActivityPowerConnectionDetailsBinding
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment.EbBillsFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment.EbConnectionFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment.EbPaymentFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo.PowerAndFuel
import com.smarthub.baseapplication.ui.utilites.adapter.BatteryViewpagerAdapter

class PowerConnectionDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityPowerConnectionDetailsBinding
    lateinit var data: PowerAndFuel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPowerConnectionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data = intent.getSerializableExtra("data") as PowerAndFuel
        initview()
    }

    fun initview() {
        val fragmentlist = ArrayList<Fragment>()
        val ebConnectionFragment =  EbConnectionFragment()
        ebConnectionFragment.setDatavalue(data)
        val ebBillsFragment =  EbBillsFragment()
        ebBillsFragment.setDatavalue(data)
        val ebPaymentFragment =  EbPaymentFragment()
        ebPaymentFragment.setDatavalue(data)


        fragmentlist.add(ebConnectionFragment)
        fragmentlist.add(ebBillsFragment)
        fragmentlist.add(ebPaymentFragment)
        val titels = ArrayList<String>()
        titels.add("EB Connection")
        titels.add("EB Bills")
        titels.add("EB Payments")
        binding.viewpager.adapter =
            BatteryViewpagerAdapter(supportFragmentManager, fragmentlist, titels)
        binding.tabs.setupWithViewPager(binding.viewpager)
    }


}