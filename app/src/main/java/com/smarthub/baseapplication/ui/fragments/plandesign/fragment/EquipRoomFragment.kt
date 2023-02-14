package com.smarthub.baseapplication.ui.fragments.plandesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.EquipRoomFragmentBinding
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanningAndDesignEquipRoomEquipmentRoom
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.equipmentRoomAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.dialouge.EquipmentRoomDialouge

class EquipRoomFragment(var equipRoomData:List<PlanningAndDesignEquipRoomEquipmentRoom>?):Fragment(),equipmentRoomAdapter.equipmentRoomListner {
    lateinit var binding:EquipRoomFragmentBinding
    lateinit var adapter: equipmentRoomAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = EquipRoomFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listItem.layoutManager = LinearLayoutManager(requireContext())
        adapter=equipmentRoomAdapter(requireContext(),this@EquipRoomFragment,equipRoomData)
        binding.listItem.adapter=adapter
    }


    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"attachment item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editequipmentRoom(data: PlanningAndDesignEquipRoomEquipmentRoom?) {
        val dalouge = EquipmentRoomDialouge(data)
        dalouge.show(childFragmentManager,"")
    }

}