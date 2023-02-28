package com.smarthub.baseapplication.ui.fragments.opcoTenancy.radioAntenna

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfAntinaFragmentBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.RadioAnteenaAndRRUData
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.AddNewRfAntennaAdapter

class NewRfAntinaFragment(var opcodata: OpcoTenencyAllData?) :Fragment(), RfAntennaItemListener {

    var binding : RfAntinaFragmentBinding?=null
    lateinit var adapter: RfAnteenaFragAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = RfAntinaFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RfAnteenaFragAdapter(requireContext(),this@NewRfAntinaFragment,opcodata?.RadioAntennaAndRRU!!)
        binding?.listItem?.adapter=adapter

        binding?.addItemsLayout?.setOnClickListener {
            val dalouge = AddNewRfAntennaAdapter(R.layout.rf_anteena_list_item_dialouge)
            dalouge.show(childFragmentManager,"")
        }
    }



    override fun clickedItem(data: RadioAnteenaAndRRUData) {
        RadioAnteenaActivity.AntennaData =data
        requireActivity().startActivity(Intent(requireContext(), RadioAnteenaActivity::class.java))

    }
}