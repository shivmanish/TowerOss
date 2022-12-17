package com.smarthub.baseapplication.ui.utilites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.ActivityFireExtinguisherDetailsBinding
import com.smarthub.baseapplication.ui.utilites.adapter.BatteryViewpagerAdapter
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.utilites.fragment.FireExtinguisherFragment

class FireExtinguisherDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityFireExtinguisherDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFireExtinguisherDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    fun initview(){
        binding.addMore.setOnClickListener{
            val dalouge = CommonBottomSheetDialog()
            dalouge.show(supportFragmentManager,"")
        }
        val fragmentlist = ArrayList<Fragment>()
        fragmentlist.add(FireExtinguisherFragment())
        fragmentlist.add(FireExtinguisherFragment())
        val titels = ArrayList<String>()
        titels.add("Fire Extinguisher#1")
        titels.add("Fire Extinguisher#2")
        binding.viewpager.adapter = BatteryViewpagerAdapter(supportFragmentManager,fragmentlist,titels)
        binding.tabs.setupWithViewPager(binding.viewpager)
    }


}