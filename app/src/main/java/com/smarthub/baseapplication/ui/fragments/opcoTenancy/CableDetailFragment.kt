package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfEquipmentFregmentTempBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.CableDetailsData
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.rfEquipmentData
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.AddNewRfEquipmentAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.rfEquipment.RfEquipmentEditDialouge

class CableDetailFragment(var opcodata: OpcoTenencyAllData?) :Fragment(), CableDetailsAdapter.CableDetailItemListner {

    var adapter : CableDetailsAdapter?=null
    var binding : RfEquipmentFregmentTempBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = RfEquipmentFregmentTempBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CableDetailsAdapter(this@CableDetailFragment,opcodata?.RfEquipment,requireContext())
        binding?.listItem?.adapter = adapter

        binding?.addItemsLayout?.setOnClickListener {
//            val dalouge = AddNewRfEquipmentAdapter(R.layout.addnew_rfequipment_dialouge)
//            dalouge.show(childFragmentManager,"")
        }
    }

    override fun attachmentItemClicked() {
    }
    override fun EditDialouge(data : rfEquipmentData,pos:Int) {
        val bottomSheetDialogFragment = RfEquipmentEditDialouge(R.layout.addnew_rfequipment_dialouge,data,opcodata?.id.toString(),
        object : RfEquipmentEditDialouge.RfEquipmentListener{
            override fun updatedData(data: rfEquipmentData) {
                adapter?.updateItem(pos,data)
            }

        })
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }


}