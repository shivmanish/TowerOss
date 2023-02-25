package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.databinding.RfEquipmentViewPagerFragmentBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.ui.fragments.BaseFragment

class RfEquipmentViewPagerFragment(var opcoData:OpcoTenencyAllData?):BaseFragment() {
    lateinit var binding:RfEquipmentViewPagerFragmentBinding
    lateinit var adapter: EquipmentViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=RfEquipmentViewPagerFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= EquipmentViewPagerAdapter(childFragmentManager,opcoData)
        binding.viewpager.adapter=adapter

        binding.tabs.setupWithViewPager(binding.viewpager)
    }

}