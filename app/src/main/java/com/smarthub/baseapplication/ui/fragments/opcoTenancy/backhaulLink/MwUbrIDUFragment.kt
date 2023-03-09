package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.databinding.OpcoRadioAntenaCommonFragBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkData
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkMWIDU
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelBills
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink.adapters.MwUbrIDUFragAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink.adapters.MwUbrLinkFragAdapter

class MwUbrIDUFragment(var BackhaulData:BackhaulLinkData?, var parentIndex:Int):BaseFragment(),MwUbrIDUFragAdapter.MwUbrIDUClickListener{
    lateinit var binding:OpcoRadioAntenaCommonFragBinding
    lateinit var adapter: MwUbrIDUFragAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=OpcoRadioAntenaCommonFragBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= MwUbrIDUFragAdapter(this@MwUbrIDUFragment,requireContext(),BackhaulData?.BackhaulLinkMWIDU)
        binding.listItem.adapter=adapter

    }

    override fun editModeCliked(data: BackhaulLinkMWIDU, pos: Int) {

    }


}