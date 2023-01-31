package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfEquipmentFregmentTempBinding
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.rfEquipmentData
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.AddNewOpcoCardAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.AddNewRfEquipmentAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.RfEquipment.RfEquipmentEditDialouge

class RfEquipmentFragment(var opcodata: OpcoDataItem?) :Fragment(), RfEquipmentAdapter.RfEquipmentItemListner {

    var adapter : RfEquipmentAdapter?=null
    var binding : RfEquipmentFregmentTempBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = RfEquipmentFregmentTempBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RfEquipmentAdapter(this@RfEquipmentFragment,opcodata!!)
        binding?.listItem?.adapter = adapter

        binding?.addItemsLayout?.setOnClickListener {
            val dalouge = AddNewRfEquipmentAdapter(R.layout.addnew_rfequipment_dialouge)
            dalouge.show(childFragmentManager,"")
        }
    }

    override fun attachmentItemClicked() {
    }
    override fun EditDialouge(data : rfEquipmentData) {
        val bottomSheetDialogFragment = RfEquipmentEditDialouge(R.layout.addnew_rfequipment_dialouge,data,opcodata?.id.toString())
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }


}