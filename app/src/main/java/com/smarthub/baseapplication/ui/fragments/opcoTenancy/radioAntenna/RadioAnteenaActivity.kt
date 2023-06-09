package com.smarthub.baseapplication.ui.fragments.opcoTenancy.radioAntenna

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityRadioAnteenaBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.RadioAnteenaAndRRUData
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger


class RadioAnteenaActivity : BaseActivity() {
    lateinit var binding : ActivityRadioAnteenaBinding

    companion object{
        var AntennaData : RadioAnteenaAndRRUData?=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRadioAnteenaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }


    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.subTitle.text=AppController.getInstance().siteName

        AppLogger.log("RadioAntennaAndRRURadioAntennaDetail data : ${Gson().toJson(AntennaData?.RadioAntennaAndRRURadioAntennaDetail)}")
        AppLogger.log("RadioAntennaAndRRUCableDetail data : ${Gson().toJson(AntennaData?.CableDetail)}")
        AppLogger.log("RadioAntennaAndRRURRUDetail data : ${Gson().toJson(AntennaData?.RadioAntennaAndRRURRUDetail)}")
        binding.viewpager.adapter = RadioAntennaPageAdapter(supportFragmentManager, AntennaData!!)
        binding.tabs.setupWithViewPager(binding.viewpager)
//        binding.tabs.setOnTabSelectedListener(onTabSelectedListener(binding.viewpager))
//        for (i in 0..binding.tabs.tabCount.minus(1)){
//            if (i==0)
//                binding.tabs.getTabAt(i)?.view?.setBackgroundResource(R.color.white)
//
//        }


    }

    private fun onTabSelectedListener(pager: ViewPager): OnTabSelectedListener {
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

}