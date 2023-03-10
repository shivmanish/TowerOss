package com.smarthub.baseapplication.ui.utilites

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityUtilitiesNocBinding
import com.smarthub.baseapplication.databinding.TabNameItemBinding

class UtilitiesNocActivity : BaseActivity() {

    lateinit var binding : ActivityUtilitiesNocBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUtilitiesNocBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initViews()
    }

    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.viewpager.adapter = UtilitiesNocAdapter(supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewpager)
        binding.tabs.setOnTabSelectedListener(onTabSelectedListener())
        binding.viewpager.beginFakeDrag()
        for (i in 0..binding.tabs.tabCount.minus(1)){
            if (i==0)
                binding.tabs.getTabAt(i)?.view?.setBackgroundResource(R.color.white)
            var itemBinding = TabNameItemBinding.inflate(layoutInflater)
            itemBinding.tabName.text = binding.viewpager.adapter?.getPageTitle(i)
            itemBinding.tabName.textSize = 10f
            binding.tabs.getTabAt(i)?.customView = itemBinding.root
        }
    }

    private fun onTabSelectedListener(): TabLayout.OnTabSelectedListener {
        return object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.color.white)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.color.tab_deselected)
            }
            override fun onTabReselected(tab: TabLayout.Tab) {}
        }
    }

}