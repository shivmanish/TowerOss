package com.smarthub.baseapplication.ui.fragments.customer_tab

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.NewCustomerDetailFragmentBinding
import com.smarthub.baseapplication.databinding.TabNameItemBinding
import kotlinx.android.synthetic.main.new_customer_detail_fragment.*
import kotlinx.android.synthetic.main.qat_punch_point_item.view.*
import kotlinx.android.synthetic.main.tab_name_item.view.*


class NewCustomerDetailsActivity : BaseActivity() {

    lateinit var binding : NewCustomerDetailFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewCustomerDetailFragmentBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initViews()
    }

    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.viewpager.adapter = CustomerPageAdapter(supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewpager)
        binding.tabs.setOnTabSelectedListener(onTabSelectedListener(binding.viewpager))
        binding.viewpager.beginFakeDrag()
        for (i in 0..binding.tabs.tabCount.minus(1)){
            if (i==0)
                binding.tabs.getTabAt(i)?.view?.setBackgroundResource(R.color.white)
            var itemBinding = TabNameItemBinding.inflate(layoutInflater).root
            itemBinding.tab_name.text = viewpager.adapter?.getPageTitle(i)
            itemBinding.tab_name.textSize = 10f
            binding.tabs.getTabAt(i)?.customView = itemBinding.view
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

}