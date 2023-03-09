package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.databinding.OpcoRadioAntenaCommonFragBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkData
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkMWIDU
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkMWODU
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelBills
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink.adapters.MwUbrIDUFragAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink.adapters.MwUbrLinkFragAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink.adapters.MwUbrODUFragAdapter

class MwUbrODUFragment(var BackhaulData:BackhaulLinkData?, var parentIndex:Int):BaseFragment(),MwUbrODUFragAdapter.MwUbrODUClickListener{
    lateinit var binding:OpcoRadioAntenaCommonFragBinding
    lateinit var adapter: MwUbrODUFragAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=OpcoRadioAntenaCommonFragBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= MwUbrODUFragAdapter(this@MwUbrODUFragment,requireContext(),BackhaulData?.BackBackhaulLinkMWODU)
        binding.listItem.adapter=adapter

    }

    override fun editModeCliked(data: BackhaulLinkMWODU, pos: Int) {

    }


}