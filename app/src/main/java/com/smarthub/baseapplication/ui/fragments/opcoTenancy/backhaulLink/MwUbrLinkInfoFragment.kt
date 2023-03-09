package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.databinding.OpcoRadioAntenaCommonFragBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelBills
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink.adapters.MwUbrLinkFragAdapter

class MwUbrLinkInfoFragment(var BackhaulData:BackhaulLinkData?, var parentIndex:Int):BaseFragment(),MwUbrLinkFragAdapter.MwUbrLinkInfoClickListener{
    lateinit var binding:OpcoRadioAntenaCommonFragBinding
    lateinit var adapter: MwUbrLinkFragAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=OpcoRadioAntenaCommonFragBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= MwUbrLinkFragAdapter(this@MwUbrLinkInfoFragment,requireContext(),BackhaulData?.BackhaulLinkMWLinkInfo)
        binding.listItem.adapter=adapter

    }

    override fun editModeCliked(data: PowerFuelBills, pos: Int) {

    }


}