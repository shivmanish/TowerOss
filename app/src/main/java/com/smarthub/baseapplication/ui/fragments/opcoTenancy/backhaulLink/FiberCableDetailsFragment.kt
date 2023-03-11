package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.databinding.OpcoRadioAntenaCommonFragBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkData
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaullinkFiberCableDetail
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink.adapters.FiberCableDetailsFragAdapter

class FiberCableDetailsFragment(var BackhaulData:BackhaulLinkData?, var parentIndex:Int):BaseFragment(),FiberCableDetailsFragAdapter.FiberCableDetailsClickListener{
    lateinit var binding:OpcoRadioAntenaCommonFragBinding
    lateinit var adapter: FiberCableDetailsFragAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=OpcoRadioAntenaCommonFragBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= FiberCableDetailsFragAdapter(this@FiberCableDetailsFragment,requireContext(),BackhaulData?.BackhaullinkFiberCableDetail)
        binding.listItem.adapter=adapter

    }

    override fun editModeCliked(data: BackhaullinkFiberCableDetail, pos: Int) {

    }


}