package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfAntinaFragmentBinding
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.RfAnteena.RfAnteenaItemsEditDialouge

class RfAntinaFragment :Fragment(), RfAntinaListAdapter.RfAnteenaItemClickListener {

    var binding : RfAntinaFragmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = RfAntinaFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = RfAntinaListAdapter(this@RfAntinaFragment)
    }


    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Attachment Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editModeCliked(){
        val bottomSheetDialogFragment = RfAnteenaItemsEditDialouge(R.layout.rf_anteena_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }
}