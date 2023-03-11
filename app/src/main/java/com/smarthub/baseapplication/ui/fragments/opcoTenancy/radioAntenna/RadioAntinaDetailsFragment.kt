package com.smarthub.baseapplication.ui.fragments.opcoTenancy.radioAntenna

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoRadioAntenaCommonFragBinding
import com.smarthub.baseapplication.databinding.RfAntinaFragmentBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.RadioAnteenaAndRRUData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.AddNewRfAntennaAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.rfAnteena.RfAnteenaItemsEditDialouge

class RadioAntinaDetailsFragment(var opcodata: RadioAnteenaAndRRUData?) :Fragment(),
    RadioAntennaDetailsAdapter.RfAnteenaItemClickListener {

    var binding : OpcoRadioAntenaCommonFragBinding?=null
    lateinit var adapter: RadioAntennaDetailsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = OpcoRadioAntenaCommonFragBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RadioAntennaDetailsAdapter(this@RadioAntinaDetailsFragment,opcodata?.RadioAntennaAndRRURadioAntennaDetail,requireContext())
        binding?.listItem?.adapter=adapter

//        binding?.addItemsLayout?.setOnClickListener {
//            val dalouge = AddNewRfAntennaAdapter(R.layout.rf_anteena_list_item_dialouge)
//            dalouge.show(childFragmentManager,"")
//        }
    }


    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Attachment Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editModeCliked(data : RfAnteenaData, pos:Int){
        val bottomSheetDialogFragment = RfAnteenaItemsEditDialouge(R.layout.rf_anteena_list_item_dialouge,data,opcodata?.id.toString(),
            object : RfAnteenaItemsEditDialouge.rfAntenaListener {
                override fun updatedData(data: RfAnteenaData) {
//                    adapter.updateItem(pos,data)
                }

            })
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
}