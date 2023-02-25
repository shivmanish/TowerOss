package com.smarthub.baseapplication.ui.fragments.dynamicui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.DynamicTabFragmentBinding
import com.smarthub.baseapplication.databinding.TabNameItemBinding
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.dynamicui.viewmodel.DynamicFragmentViewModel

class DynamicTabFragment(var Id: String) : BaseFragment() {
    lateinit var binding: DynamicTabFragmentBinding
    lateinit var viewmodel: DynamicFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel = ViewModelProvider(this).get(DynamicFragmentViewModel::class.java)
        binding = DynamicTabFragmentBinding.inflate(inflater)
        fetchData()
        return binding.root
    }

    fun setData(data: ArrayList<FragmentPojo>) {
        binding.viewpager.adapter = DynamicPageAdapter(childFragmentManager, data)
        binding.tabs.setupWithViewPager(binding.viewpager)
        binding.tabs.addOnTabSelectedListener(onTabSelectedListener(binding.viewpager))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun fetchData() {
        setObserver()
        viewmodel.fetchData(requireContext())
    }

    fun setObserver() {
        viewmodel.dynamicUiResponse.observe(viewLifecycleOwner, Observer {
            viewmodel.setFragmentList(it)
        })
        viewmodel.fragmentList.observe(viewLifecycleOwner, Observer {
            setData(it)
        })

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