package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerLoadFregmentBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.PowerLoadData
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.PowerLoad.PowerLoadEditDialouge

class PowerLoadFragment(var opcodata: OpcoTenencyAllData?): Fragment(),
    PowerLoadAdapter.PowerLoadItemClickListener {

    var adapter : PowerLoadAdapter?=null
    var binding : PowerLoadFregmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = PowerLoadFregmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PowerLoadAdapter(this@PowerLoadFragment,opcodata?.Loadmeasurement!!,requireContext())
        binding?.listItem?.adapter = adapter

    }


    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Attachment Item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun EditItemDialouge(powerloaddata: PowerLoadData, opcodata: OpcoDataItem?) {
        val bottomSheetDialogFragment = PowerLoadEditDialouge(R.layout.power_load_list_item_dialouge,powerloaddata,opcodata)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
//        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

}