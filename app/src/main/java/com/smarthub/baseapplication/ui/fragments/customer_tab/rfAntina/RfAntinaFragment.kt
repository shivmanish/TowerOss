package com.smarthub.baseapplication.ui.fragments.customer_tab.rfAntina

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BackhaulFragmentBinding
import com.smarthub.baseapplication.databinding.RfAntinaFragmentBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.adapter.customer.RfAntinaListAdapter
import com.smarthub.baseapplication.ui.fragments.opcoInfo.OperationsItemsEditDialouge

class RfAntinaFragment :Fragment(), ImageAttachmentAdapter.ItemClickListener,RfAntinaListAdapter.ItemClickListener {

    var binding : RfAntinaFragmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = RfAntinaFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = RfAntinaListAdapter(this@RfAntinaFragment,this@RfAntinaFragment)
    }

    override fun itemClicked() {
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editModeCliked(){
        val bottomSheetDialogFragment = RfAnteenaItemsEditDialouge(R.layout.rf_anteena_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }
}