package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerPoleInfoFragmentBinding
import com.smarthub.baseapplication.ui.fragments.opcoInfo.OpcoSiteInfoEditDialouge
import com.smarthub.baseapplication.ui.fragments.opcoInfo.OperationsItemsEditDialouge
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.*

class TowerEquipmentInfoFragment: Fragment(), TowerEquipmentInfoAdapter.TowerPoleListListener {
    var binding : TowerPoleInfoFragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = TowerPoleInfoFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = TowerEquipmentInfoAdapter(requireContext(),this@TowerEquipmentInfoFragment)
    }

    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }
    //
    override fun EditInstallationAcceptence() {
        val bottomSheetDialogFragment = OperationsItemsEditDialouge(R.layout.opco_operations_team_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }

    override fun EditTowerItem() {

        val bottomSheetDialogFragment = OpcoSiteInfoEditDialouge(R.layout.opco_info_site_dialouge_layout)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }
    override fun editPoClicked(position: Int) {
        var bm = EquipmentPoEditDialougeAdapter(R.layout.tower_equipment_po_edit_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewPoClicked(position: Int) {
        var bm = EquipmentPoViewDialougeAdapter(R.layout.equipment_room_view_dialouge)
        bm.show(childFragmentManager, "category")
    }

    override fun editConsumableClicked(position: Int) {
        var bm = EarthingConsumableEditDialougeAdapter(R.layout.earthing_consumable_table_edit_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewConsumableClicked(position: Int) {
        var bm = EarthingConsumableTableViewDialougeAdapter(R.layout.earthing_consumable_table_view_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }


}