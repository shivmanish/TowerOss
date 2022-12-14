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

class TowerInfoFragment: Fragment(), TowerInfoListAdapter.TowerInfoListListener {
    var binding : TowerFragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = TowerFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = TowerInfoListAdapter(this@TowerInfoFragment)
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


}