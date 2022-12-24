package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BackhaulFragmentBinding
import com.smarthub.baseapplication.databinding.BackhaulFragmentBinding.*
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.Backhaul.*

class BackhaulFragment(var opcodata: OpcoDataItem?) :Fragment(), BackhaulListAdapter.BackhaulListListener{

    var binding : BackhaulFragmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = BackhaulListAdapter(requireContext(),this@BackhaulFragment,opcodata)
    }

    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Attachment Item clicked",Toast.LENGTH_SHORT).show()
    }


    override fun LinkItemEdit() {
        val bottomSheetDialogFragment = backhaulLinkItemEditDialouge(R.layout.backhaul_link_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun IduEditItem() {
        val bottomSheetDialogFragment = IduListItemEditDialouge(R.layout.backhaul_idu_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun OduEditItem() {
        val bottomSheetDialogFragment = OduListItemEditDialouge(R.layout.backhaul_odu_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun AnteenaItemEdit() {
        val bottomSheetDialogFragment = BackhaulAnteenaItemEditDialouge(R.layout.backhaul_antena_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun InstllationItemEdit() {
        val bottomSheetDialogFragment = BackhaulInstallationTeamEditDialouge(R.layout.backhaul_installation_team_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun LmcFiberItemEdit() {
        val bottomSheetDialogFragment = BackhaulLmcFiberEditDialouge(R.layout.backhaul_lmc_list_item_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun ConnectedEquipmentItemEdit() {
        val bottomSheetDialogFragment = BackhaulConnectedEquipmentEditDialouge(R.layout.backhaul_connected_equipment_item_list_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun EditMaterialTableItem(position: Int) {
        Toast.makeText(requireContext(),"Material Edit Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun ViewMaterialTableItem(position: Int) {
        Toast.makeText(requireContext(),"Material View Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun EditPoTableItem(position: Int) {
        Toast.makeText(requireContext(),"PO Edit Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun ViewPoTableItem(position: Int) {
        Toast.makeText(requireContext(),"PO View Item clicked",Toast.LENGTH_SHORT).show()
    }


}