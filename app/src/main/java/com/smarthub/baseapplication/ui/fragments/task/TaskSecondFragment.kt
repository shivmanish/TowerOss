package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TabNameItemBinding
import com.smarthub.baseapplication.databinding.TaskSecondFragmnetBinding
import com.smarthub.baseapplication.ui.basic_info.BasicInfoPagerAdapter
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskViewpagerAdapter
import kotlinx.android.synthetic.main.new_customer_detail_fragment.*
import kotlinx.android.synthetic.main.qat_punch_point_item.view.*
import kotlinx.android.synthetic.main.tab_name_item.view.*

class TaskSecondFragment:Fragment() {
    lateinit var binding:TaskSecondFragmnetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TaskSecondFragmnetBinding.inflate(inflater)
        setview()
        return binding.root
    }

    private fun setview() {
        binding.viewpager.adapter =
            TaskViewpagerAdapter(childFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewpager)
//        binding.tabs.setOnTabSelectedListener(onTabSelectedListener(binding.viewpager))
/*
        for (i in 0..binding.tabs.tabCount.minus(1)) {
            if (i == 0)
                binding.tabs.getTabAt(i)?.view?.setBackgroundResource(R.color.white)
            var itemBinding = TabNameItemBinding.inflate(layoutInflater).root
            itemBinding.tab_name.text = viewpager.adapter?.getPageTitle(i)
            itemBinding.tab_name.textSize = 10f
            binding.tabs.getTabAt(i)?.customView = itemBinding.view
        }
*/
    }
    private fun onTabSelectedListener(pager: ViewPager): TabLayout.OnTabSelectedListener? {
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