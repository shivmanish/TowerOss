package com.smarthub.baseapplication.ui.fragments.customer_tab.backhaul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BackhaulFragmentBinding
import com.smarthub.baseapplication.databinding.BackhaulFragmentBinding.*
import com.smarthub.baseapplication.databinding.CommercialFragmentBinding
import com.smarthub.baseapplication.databinding.CustomerInfoFragmentInfoBinding
import com.smarthub.baseapplication.databinding.CustomerInfoTempFragmentBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.adapter.customer.BackhaulListAdapter
import com.smarthub.baseapplication.ui.adapter.customer.CommercialListAdapter
import com.smarthub.baseapplication.ui.fragments.customer_tab.powerload.PowerLoadEditDialouge

class BackhaulFragment :Fragment(), ImageAttachmentAdapter.ItemClickListener , BackhaulListAdapter.ItemClickListener{

    var binding : BackhaulFragmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = BackhaulListAdapter(this@BackhaulFragment,this@BackhaulFragment)
    }

    override fun itemClicked() {

        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun LinkItemEdit() {
        val bottomSheetDialogFragment = PowerLoadEditDialouge(R.layout.backhaul_link_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun IduEditItem() {
        val bottomSheetDialogFragment = PowerLoadEditDialouge(R.layout.backhaul_idu_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun OduEditItem() {
        val bottomSheetDialogFragment = PowerLoadEditDialouge(R.layout.backhaul_odu_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun AnteenaItemEdit() {
        val bottomSheetDialogFragment = PowerLoadEditDialouge(R.layout.backhaul_antena_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun InstllationItemEdit() {
        val bottomSheetDialogFragment = PowerLoadEditDialouge(R.layout.backhaul_installation_team_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun LmcFiberItemEdit() {
        val bottomSheetDialogFragment = PowerLoadEditDialouge(R.layout.backhaul_lmc_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun ConnectedEquipmentItemEdit() {
        val bottomSheetDialogFragment = PowerLoadEditDialouge(R.layout.backhaul_connected_equipment_item_list_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }
}