package com.smarthub.baseapplication.ui.fragments.plandesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.BatteryFragmentBinding
import com.smarthub.baseapplication.databinding.EquipRoomFragmentBinding
import com.smarthub.baseapplication.databinding.TowerCivilFragmentBinding
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.PoleTableAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.TableCallback
import com.smarthub.baseapplication.ui.fragments.plandesign.dialouge.PoleDialouge
import com.smarthub.baseapplication.ui.fragments.plandesign.dialouge.PoleTableEditDialouge
import com.smarthub.baseapplication.ui.fragments.plandesign.dialouge.TowerDialouge
import com.smarthub.baseapplication.ui.utilites.editdialouge.BatteryEquipmentDialouge
import com.smarthub.baseapplication.ui.utilites.editdialouge.InstalationAcceptanceDialouge
import com.smarthub.baseapplication.utils.Utils

class TowerCivilFragment:Fragment() {

    lateinit var binding:TowerCivilFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TowerCivilFragmentBinding.inflate(inflater,container,false)
        setView()
        return binding.root
    }

    fun setView(){
        binding.equipmentEdit.setOnClickListener{
            val dalouge = TowerDialouge()
            dalouge.show(childFragmentManager,"")
        }
        binding.poleEdit.setOnClickListener{
            val dalouge = PoleDialouge()
            dalouge.show(childFragmentManager,"")
        }
        binding.poleTable.adapter = PoleTableAdapter(requireContext(),object:TableCallback{
            override fun editItem(obj: Any?) {
                val dalouge = PoleTableEditDialouge()
                dalouge.show(childFragmentManager,"")
            }

            override fun viewItem(obj: Any?) {
                val dalouge = PoleTableEditDialouge()
                dalouge.show(childFragmentManager,"")
            }

        })
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
        binding.poleRoot.setOnClickListener {
            if(binding.itemCollapsePole.visibility == View.VISIBLE){
                Utils.collapse(binding.itemCollapsePole)
                binding.poleArrow.rotation = 0f
                binding.poleEdit.visibility = View.GONE
                binding.poleRoot.isSelected = false
            }else{
                Utils.expand(binding.itemCollapsePole)
                binding.poleRoot.isSelected = true
                binding.poleArrow.rotation = 180f
                binding.poleEdit.visibility = View.VISIBLE
            }
        }

    }

}