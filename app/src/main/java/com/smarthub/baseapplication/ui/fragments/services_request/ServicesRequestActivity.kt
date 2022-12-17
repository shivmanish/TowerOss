package com.smarthub.baseapplication.ui.fragments.services_request

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.NewCustomerDetailFragmentBinding
import com.smarthub.baseapplication.databinding.TabNameItemBinding
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicePageAdapter
import com.smarthub.baseapplication.ui.utilites.editdialouge.CommonBottomSheetDialog
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel

class ServicesRequestActivity : BaseActivity() {
    private var profileViewModel : SiteInfoViewModel?=null
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    lateinit var binding : NewCustomerDetailFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewCustomerDetailFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun mapUiData(siteInfoDropDownData: SiteInfoDropDownData){
        this.siteInfoDropDownData = siteInfoDropDownData
    }

    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog()
            dalouge.show(supportFragmentManager,"")
        }
        binding.viewpager.adapter = ServicePageAdapter(supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewpager)
        binding.tabs.setOnTabSelectedListener(onTabSelectedListener(binding.viewpager))
        binding.viewpager.beginFakeDrag()
        for (i in 0..binding.tabs.tabCount.minus(1)){
            if (i==0)
                binding.tabs.getTabAt(i)?.view?.setBackgroundResource(R.color.white)
            val itemBinding = TabNameItemBinding.inflate(layoutInflater)
            itemBinding.tabName.text = binding.viewpager.adapter?.getPageTitle(i)
            itemBinding.tabName.textSize = 10f
            binding.tabs.getTabAt(i)?.customView = itemBinding.root
        }

        profileViewModel= ViewModelProvider(this)[SiteInfoViewModel::class.java]

//        profileViewModel?.profileResponse?.observe(this) {
//            hideLoader()
//            if (it != null) {
//                if (it.status == Resource.Status.SUCCESS && it.data != null) {
//                    mapUiData(it.data)
//                    return@observe
//                } else {
//                    Log.d("status", "${it.message}")
//                    Toast.makeText(this@NewCustomerDetailsActivity, "error:" + it.message, Toast.LENGTH_LONG).show()
//                }
//            } else {
//                Log.d("status", AppConstants.GENERIC_ERROR)
//                Toast.makeText(this@NewCustomerDetailsActivity, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
//            }
//        }
    }

    private fun onTabSelectedListener(pager: ViewPager): TabLayout.OnTabSelectedListener {
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