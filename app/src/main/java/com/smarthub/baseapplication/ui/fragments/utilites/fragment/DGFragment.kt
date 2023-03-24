package com.smarthub.baseapplication.ui.utilites.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.AdditinalAccessoresBottomSheetBinding
import com.smarthub.baseapplication.databinding.Dg1TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.databinding.MaintanaceBottomSheetBinding
import com.smarthub.baseapplication.ui.utilites.editdialouge.*
import com.smarthub.baseapplication.utils.Utils

class DGFragment:Fragment() {
    lateinit var binding: Dg1TabUtilitiesFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Dg1TabUtilitiesFragmentBinding.inflate(inflater,container,false)
        setView()
        return binding.root
    }
    fun setView(){
        binding.equipmentEdit.setOnClickListener{
            val dalouge = DgBottomSheetDialog()
            dalouge.show(childFragmentManager,"")
        }
         binding.additionalAccessoriesEdit.setOnClickListener{
            val dalouge = AdditionalAccessoriesBottomSheetDialog()
            dalouge.show(childFragmentManager,"")
        }
        binding.consumableMaterialsEdit.setOnClickListener{
            val dalouge = ConsumableMaterialsBottomSheetDialog()
            dalouge.show(childFragmentManager,"")
        }
        binding.installationEdit.setOnClickListener{
            val dalouge = InstalationAcceptanceDialouge()
            dalouge.show(childFragmentManager,"")
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

        // Installtuions
        binding.installationRoot.setOnClickListener {
            if(binding.installtionCollapse.visibility == View.VISIBLE){
                Utils.collapse(binding.installtionCollapse)
                binding.installtionArrow.rotation = 0f
                binding.installationEdit.visibility = View.GONE
                binding.installationRoot.isSelected = false
            }else{
                Utils.expand(binding.installtionCollapse)
                binding.installationRoot.isSelected = true
                binding.installtionArrow.rotation = 180f
                binding.installationEdit.visibility = View.VISIBLE
            }
        }

        //Additional Accessories`
        binding.additionalAccessoriesRoot.setOnClickListener {
            if(binding.additionalAccessoriesCollasp.visibility == View.VISIBLE){
                Utils.collapse(binding.additionalAccessoriesCollasp)
                binding.additionalAccessoriesArrow.rotation = 0f
                binding.additionalAccessoriesEdit.visibility = View.GONE
                binding.additionalAccessoriesRoot.isSelected = false
            }else{
                Utils.expand(binding.additionalAccessoriesCollasp)
                binding.additionalAccessoriesRoot.isSelected = true
                binding.additionalAccessoriesArrow.rotation = 180f
                binding.additionalAccessoriesEdit.visibility = View.VISIBLE
            }
        }
        // Consumable Materials
        binding.consumableMaterialsRoot.setOnClickListener {
            if(binding.consumableMaterialsCollasp.visibility == View.VISIBLE){
                Utils.collapse(binding.consumableMaterialsCollasp)
                binding.consumableMaterialsArrow.rotation = 0f
                binding.consumableMaterialsEdit.visibility = View.GONE
                binding.consumableMaterialsRoot.isSelected = false
            }else{
                Utils.expand(binding.consumableMaterialsCollasp)
                binding.consumableMaterialsRoot.isSelected = true
                binding.consumableMaterialsArrow.rotation = 180f
                binding.consumableMaterialsEdit.visibility = View.VISIBLE
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