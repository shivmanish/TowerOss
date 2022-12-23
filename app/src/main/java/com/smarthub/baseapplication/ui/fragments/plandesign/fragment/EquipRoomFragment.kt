package com.smarthub.baseapplication.ui.fragments.plandesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.BatteryFragmentBinding
import com.smarthub.baseapplication.databinding.EquipRoomFragmentBinding
import com.smarthub.baseapplication.ui.fragments.plandesign.dialouge.EquipmentRoomDialouge
import com.smarthub.baseapplication.ui.utilites.editdialouge.BatteryEquipmentDialouge
import com.smarthub.baseapplication.ui.utilites.editdialouge.InstalationAcceptanceDialouge
import com.smarthub.baseapplication.utils.Utils

class EquipRoomFragment:Fragment() {

    lateinit var binding:EquipRoomFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EquipRoomFragmentBinding.inflate(inflater,container,false)
        setView()
        return binding.root
    }

    fun setView(){
        binding.equipmentEdit.setOnClickListener{
            val dalouge = EquipmentRoomDialouge()
            dalouge.show(childFragmentManager,"")
        }
        binding.editInstanlation.setOnClickListener{
            val dalouge = InstalationAcceptanceDialouge()
            dalouge.show(childFragmentManager,"")
        }
        binding.equipmentRoot.setOnClickListener {
            if(binding.itemCollapseEquipment.visibility == View.VISIBLE){
                Utils.collapse(binding.itemCollapseEquipment)
                binding.equipmentArrow.rotation = 0f
                binding.equipmentEdit.visibility = View.GONE
                binding.equipmentRoot.isSelected = false
            }else{
                Utils.expand(binding.itemCollapseEquipment)
                binding.equipmentRoot.isSelected = true
                binding.equipmentArrow.rotation = 180f
                binding.equipmentEdit.visibility = View.VISIBLE
            }
        }

    }

}