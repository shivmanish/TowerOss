package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerFragmentBinding
import com.smarthub.baseapplication.ui.fragments.opcoInfo.OpcoSiteInfoEditDialouge
import com.smarthub.baseapplication.ui.fragments.opcoInfo.OperationsItemsEditDialouge
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.*

class TowerInfoFragment: Fragment(), TowerInfoListAdapter.TowerInfoListListener {
    var binding : TowerFragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = TowerFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = TowerInfoListAdapter(requireContext(),this@TowerInfoFragment)
    }

    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }
//
    override fun EditInstallationAcceptence() {
        val bottomSheetDialogFragment = TowerInstallationEditAdapter(R.layout.tower_installation_edit_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }

    override fun EditTowerItem() {
        val bottomSheetDialogFragment = TowerInfoEditDialougeAdapter(R.layout.tower_info_edit_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }
    override fun editPoClicked(position: Int) {
        var bm = TowerPoEditAdapter(R.layout.tower_po_edit_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewPoClicked(position: Int) {
        var bm = TowerPoViewAdapter(R.layout.tower_po_view_dialouge)
        bm.show(childFragmentManager, "category")
    }

    override fun editConsumableClicked(position: Int) {
        var bm = TowerConsumableEditAdapter(R.layout.tower_consumable_edit_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewConsumableClicked(position: Int) {
        var bm = TowerConsumableViewAdapter(R.layout.tower_consumable_view_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun editOffsetClicked(position: Int) {
        var bm = TowerOffsetEditAdapter(R.layout.tower_offset_edit_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewOffsetClicked(position: Int) {
        var bm = TowerOffsetViewAdapter(R.layout.tower_offset_view_dialouge)
        bm.show(childFragmentManager, "category")
    }


}