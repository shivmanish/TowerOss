package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.databinding.OpcoRadioAntenaCommonFragBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkData
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkFiberLinkInfo
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelBills
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink.adapters.FiberOpticsLinkFragAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink.adapters.MwUbrLinkFragAdapter

class FiberOpticsLinkInfoFragment(var BackhaulData:BackhaulLinkData?, var parentIndex:Int):BaseFragment(),FiberOpticsLinkFragAdapter.FiberOpticsLinkInfoClickListener{
    lateinit var binding:OpcoRadioAntenaCommonFragBinding
    lateinit var adapter: FiberOpticsLinkFragAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=OpcoRadioAntenaCommonFragBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= FiberOpticsLinkFragAdapter(this@FiberOpticsLinkInfoFragment,requireContext(),BackhaulData?.BackhaulLinkFiberLinkInfo)
        binding.listItem.adapter=adapter

    }

    override fun editModeCliked(data: BackhaulLinkFiberLinkInfo, pos: Int) {

    }


}