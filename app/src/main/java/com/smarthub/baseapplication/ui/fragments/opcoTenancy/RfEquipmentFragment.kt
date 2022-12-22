package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfEquipmentFregmentTempBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.RfEquipment.RfEquipmentEditDialouge

class RfEquipmentFragment :Fragment(), ImageAttachmentAdapter.ItemClickListener {

    var adapter : RfEquipmentAdapter?=null
    var binding : RfEquipmentFregmentTempBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = RfEquipmentFregmentTempBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RfEquipmentAdapter(this@RfEquipmentFragment)
        binding?.listItem?.adapter = adapter

        binding?.addItemsLayout?.setOnClickListener {
            adapter?.addItem()
        }
    }

    override fun itemClicked() {
        val bottomSheetDialogFragment = RfEquipmentEditDialouge(R.layout.rf_equipment_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }


}