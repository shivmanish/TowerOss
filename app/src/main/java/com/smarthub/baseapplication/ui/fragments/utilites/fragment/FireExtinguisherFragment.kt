package com.smarthub.baseapplication.ui.utilites.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.AcFragmentBinding
import com.smarthub.baseapplication.databinding.BatteryFragmentBinding
import com.smarthub.baseapplication.databinding.FireExtinguisherFragmentBinding
import com.smarthub.baseapplication.ui.utilites.editdialouge.*
import com.smarthub.baseapplication.utils.Utils

class FireExtinguisherFragment:Fragment() {

    lateinit var binding:FireExtinguisherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FireExtinguisherFragmentBinding.inflate(inflater,container,false)
        setView()
        return binding.root
    }

    fun setView(){
        binding.equipmentEdit.setOnClickListener{
            val dalouge = BatteryEquipmentDialouge()
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
        binding.maintenanceEdit.setOnClickListener{
            val dalouge = MaintanceBottomSheetDialog()
            dalouge.show(childFragmentManager,"")
        }
        binding.poEdit.setOnClickListener{
            val dalouge = PoDetailsBottomSheetDialog()
            dalouge.show(childFragmentManager,"")
        }
        binding.ServiceDetailsEdit.setOnClickListener{
            val dalouge = ServicesDetailsBottomSheetDialog()
            dalouge.show(childFragmentManager,"")
        }
        binding.instanlationRoot.setOnClickListener {
            if(binding.itemCollapseAcceptance.visibility == View.VISIBLE){
                Utils.collapse(binding.itemCollapseAcceptance)
                binding.instanlationRoot.isSelected = false
                binding.instalationArrow.rotation = 0f
                binding.editInstanlation.visibility = View.GONE
            }else{
                Utils.expand(binding.itemCollapseAcceptance)
                binding.instanlationRoot.isSelected = true
                binding.instalationArrow.rotation = 180f
                binding.editInstanlation.visibility = View.VISIBLE
            }
        }
        // Maintenance
        binding.maintannaceRoot.setOnClickListener {
            if(binding.itemCollapseMaintance.visibility == View.VISIBLE){
                Utils.collapse(binding.itemCollapseMaintance)
                binding.maintannceArrow.rotation = 0f
                binding.maintenanceEdit.visibility = View.GONE
                binding.maintannaceRoot.isSelected = false
            }else{
                Utils.expand(binding.itemCollapseMaintance)
                binding.maintannaceRoot.isSelected = true
                binding.maintannceArrow.rotation = 180f
                binding.maintenanceEdit.visibility = View.VISIBLE
            }
        }
        // PO Details
        binding.poRoot.setOnClickListener {
            if(binding.poCollasp.visibility == View.VISIBLE){
                Utils.collapse(binding.poCollasp)
                binding.poArrow.rotation = 0f
                binding.poEdit.visibility = View.GONE
                binding.poRoot.isSelected = false
            }else{
                Utils.expand(binding.poCollasp)
                binding.poRoot.isSelected = true
                binding.poArrow.rotation = 180f
                binding.poEdit.visibility = View.VISIBLE
            }
        }
        // Service Details
        binding.ServiceDetailsRoot.setOnClickListener {
            if(binding.ServiceDetailsCollasp.visibility == View.VISIBLE){
                Utils.collapse(binding.ServiceDetailsCollasp)
                binding.ServiceDetailsArrow.rotation = 0f
                binding.ServiceDetailsEdit.visibility = View.GONE
                binding.ServiceDetailsRoot.isSelected = false
            }else{
                Utils.expand(binding.ServiceDetailsCollasp)
                binding.ServiceDetailsRoot.isSelected = true
                binding.ServiceDetailsArrow.rotation = 180f
                binding.ServiceDetailsEdit.visibility = View.VISIBLE
            }
        }


    }

}