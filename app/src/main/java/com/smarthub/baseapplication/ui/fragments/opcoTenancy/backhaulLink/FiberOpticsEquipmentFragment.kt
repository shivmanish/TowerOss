package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.databinding.OpcoRadioAntenaCommonFragBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackBackhaulLinkFiberOpticEquipment
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink.adapters.FiberOpticsEquipmentFragAdapter

class FiberOpticsEquipmentFragment(var BackhaulData:BackhaulLinkData?, var parentIndex:Int):BaseFragment(),FiberOpticsEquipmentFragAdapter.BackhaulEquipmentClickListener{
    lateinit var binding:OpcoRadioAntenaCommonFragBinding
    lateinit var adapter: FiberOpticsEquipmentFragAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=OpcoRadioAntenaCommonFragBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= FiberOpticsEquipmentFragAdapter(this@FiberOpticsEquipmentFragment,requireContext(),BackhaulData?.BackBackhaulLinkFiberOpticEquipment)
        binding.listItem.adapter=adapter

    }

    override fun editModeCliked(data: BackBackhaulLinkFiberOpticEquipment, pos: Int) {

    }


}