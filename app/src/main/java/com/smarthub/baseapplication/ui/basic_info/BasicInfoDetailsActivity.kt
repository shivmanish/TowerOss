package com.smarthub.baseapplication.ui.basic_info

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.smarthub.baseapplication.R
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.databinding.BasicInfoDetailActivityBinding
import com.smarthub.baseapplication.databinding.TabNameItemBinding
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.viewmodels.BasicInfoDetailViewModel


class BasicInfoDetailsActivity : BaseActivity() {
    lateinit var binding: BasicInfoDetailActivityBinding
    lateinit var siteViewModel: BasicInfoDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BasicInfoDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setViewModel()

    }
    private fun initViews(siteInfoDropDownData: SiteInfoDropDownData) {
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.viewpager.adapter =
            BasicInfoPagerAdapter(supportFragmentManager, siteInfoDropDownData)
        binding.tabs.setupWithViewPager(binding.viewpager)
        binding.tabs.setOnTabSelectedListener(onTabSelectedListener(binding.viewpager))
        binding.viewpager.beginFakeDrag()
        for (i in 0..binding.tabs.tabCount.minus(1)) {
            if (i == 0)
                binding.tabs.getTabAt(i)?.view?.setBackgroundResource(R.color.white)
            val itemBinding : TabNameItemBinding = TabNameItemBinding.inflate(layoutInflater)
            itemBinding.tabName.text = binding.viewpager.adapter?.getPageTitle(i)
            itemBinding.tabName.textSize = 10f
            binding.tabs.getTabAt(i)?.customView = itemBinding.root
        }
    }

    private fun onTabSelectedListener(pager: ViewPager): OnTabSelectedListener? {
        return object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.color.white)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.color.tab_deselected)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        }
    }

    private fun setViewModel() {
        siteViewModel = ViewModelProvider(this@BasicInfoDetailsActivity)[BasicInfoDetailViewModel::class.java]
        siteViewModel?.fetchDropDown()
        siteViewModel?.dropDownResponse?.observe(this@BasicInfoDetailsActivity) {
            mapUiData(it)
        }

    }

    private fun mapUiData(siteInfoDropDownData: SiteInfoDropDownData) {
        if (siteInfoDropDownData != null) {
//            start data mapping
            initViews(siteInfoDropDownData)

        }
    }

}