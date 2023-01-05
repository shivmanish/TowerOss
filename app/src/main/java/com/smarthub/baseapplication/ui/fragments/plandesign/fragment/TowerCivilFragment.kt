package com.smarthub.baseapplication.ui.fragments.plandesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerCivilFragmentBinding
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanningAndDesignTowerAndCivil
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.plandesignTowerCivilAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.dialouge.TowerDialouge

class TowerCivilFragment(var twrCivilData:List<PlanningAndDesignTowerAndCivil>?):Fragment(),plandesignTowerCivilAdapter.TowrCivilListner {

    lateinit var binding:TowerCivilFragmentBinding
    lateinit var adapter:plandesignTowerCivilAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = TowerCivilFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listItem.layoutManager = LinearLayoutManager(requireContext())
        adapter= plandesignTowerCivilAdapter(requireContext(),this@TowerCivilFragment,twrCivilData)
        binding.listItem.adapter=adapter

    }

    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"attachment item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun editTower() {
        val dalouge = TowerDialouge()
        dalouge.show(childFragmentManager,"")
    }

    override fun editPole() {
        Toast.makeText(requireContext(),"edit pole info clicked", Toast.LENGTH_SHORT).show()
    }

    override fun editPoleTableItem(position: Int) {
        Toast.makeText(requireContext(),"edit pole table item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun viewPoleTableItem(position: Int) {
        Toast.makeText(requireContext(),"view pole table item clicked", Toast.LENGTH_SHORT).show()
    }

}