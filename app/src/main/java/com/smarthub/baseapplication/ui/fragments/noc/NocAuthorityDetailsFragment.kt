package com.smarthub.baseapplication.ui.fragments.noc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfAntinaFragmentBinding
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.rfAnteena.RfAnteenaItemsEditDialouge

class NocAuthorityDetailsFragment(var nocdata: NocCompAllData?) :Fragment(), NocAuthorityDetailsAdapter.NocauthorityClickListener {

    var binding : RfAntinaFragmentBinding?=null
    lateinit var adapter: NocAuthorityDetailsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = RfAntinaFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NocAuthorityDetailsAdapter(this@NocAuthorityDetailsFragment,nocdata?.AuthorityDetail,requireContext())
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
        val bottomSheetDialogFragment = RfAnteenaItemsEditDialouge(R.layout.rf_anteena_list_item_dialouge,data,nocdata?.id.toString(),
            object : RfAnteenaItemsEditDialouge.rfAntenaListener {
                override fun updatedData(data: RfAnteenaData) {
//                    adapter.updateItem(pos,data)
                }

            })
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
}