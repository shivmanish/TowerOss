package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerEarthingInfoFragmentBinding
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.*

class TowerEarthingInfoFragment: Fragment(), EarthingInfoFragmentAdapter.TowerEarthingListListener {
    var binding : TowerEarthingInfoFragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = TowerEarthingInfoFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = EarthingInfoFragmentAdapter(requireContext(),this@TowerEarthingInfoFragment)
    }

    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }
    //
    override fun EditInstallationAcceptence() {
        val bottomSheetDialogFragment = EarthingInstallationEditAdapter(R.layout.earthing_installation_edit_dilaouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }

    override fun EditEarthingItem() {

        val bottomSheetDialogFragment = EarthingInfoDialougeAdapter(R.layout.tower_earthing_info_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }

    override fun editPoClicked(position: Int) {
        var bm = EditEarthingPOTableBottomSheet(R.layout.earthing_po_item_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewPoClicked(position: Int) {
        var bm = EarthingPoTableViewDialougeAdapter(R.layout.earthing_po_item_dialouge)
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