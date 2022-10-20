package com.smarthub.baseapplication.ui.fragments.customer_tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.CommercialFragmentBinding
import com.smarthub.baseapplication.databinding.CustomerInfoFragmentInfoBinding
import com.smarthub.baseapplication.databinding.CustomerInfoTempFragmentBinding
import com.smarthub.baseapplication.databinding.RfEquipmentFragmentBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.adapter.customer.CommercialListAdapter

class RfEquipmentFragment :Fragment(), ImageAttachmentAdapter.ItemClickListener {

    var adapter : RfEquipmentAdapter?=null
    var binding : RfEquipmentFragmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = RfEquipmentFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RfEquipmentAdapter(this@RfEquipmentFragment)
        binding?.listItem?.adapter = adapter

        binding?.addItems?.setOnClickListener {
            adapter?.addItem()
        }
    }

    override fun itemClicked() {

        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }
}