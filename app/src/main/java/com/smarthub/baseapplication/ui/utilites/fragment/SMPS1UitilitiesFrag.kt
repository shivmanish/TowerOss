package com.smarthub.baseapplication.ui.utilites.fragment

import android.os.Bundle
import android.service.voice.VoiceInteractionSession.VisibleActivityCallback
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.Ac1TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.databinding.BatteryFragmentBinding
import com.smarthub.baseapplication.databinding.Smps1TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.dialog.utils.ConnectedLoadsBottomSheetDialog
import com.smarthub.baseapplication.ui.dialog.utils.RectifierModuleBottomSheetDialog
import com.smarthub.baseapplication.ui.utilites.adapter.SMPSViewRecyclerAdapter
import com.smarthub.baseapplication.ui.utilites.editdialouge.BatteryEquipmentDialouge
import com.smarthub.baseapplication.ui.utilites.editdialouge.ConsumableMaterialsBottomSheetDialog
import com.smarthub.baseapplication.ui.utilites.editdialouge.InstalationAcceptanceDialouge
import com.smarthub.baseapplication.ui.utilites.editdialouge.PoDetailsBottomSheetDialog
import com.smarthub.baseapplication.utils.Utils

class SMPS1UitilitiesFrag: Fragment() {

    lateinit var binding: Smps1TabUtilitiesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Smps1TabUtilitiesFragmentBinding.inflate(inflater,container,false)
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
        binding.rectifierModuleEdit.setOnClickListener{
            val dalouge = RectifierModuleBottomSheetDialog()
            dalouge.show(childFragmentManager,"")
        }
        binding.rectifierModuleRecycler.rv.adapter=SMPSViewRecyclerAdapter()
        binding.connectedLoadsEdit.setOnClickListener{
            val dalouge = ConnectedLoadsBottomSheetDialog()
            dalouge.show(childFragmentManager,"")
        }
        binding.connectedLoadsRecycler.rv.adapter=SMPSViewRecyclerAdapter()
        binding.consumableMaterialsEdit.setOnClickListener{
            val dalouge = ConsumableMaterialsBottomSheetDialog()
            dalouge.show(childFragmentManager,"")
        }
        binding.consumableMaterialsRecycler.rv.adapter=SMPSViewRecyclerAdapter()
        binding.poDetailsEdit.setOnClickListener{
            val dalouge = PoDetailsBottomSheetDialog()
            dalouge.show(childFragmentManager,"")
        }
        binding.poDetailsRecycler.rv.adapter=SMPSViewRecyclerAdapter()
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

        binding.rectifierModuleRoot.setOnClickListener {
            if(binding.rectifierModuleCollapse.visibility == View.VISIBLE){
                Utils.collapse(binding.rectifierModuleCollapse)
                binding.rectifierModuleRoot.isSelected = false
                binding.rectifierModuleArrow.rotation = 0f
                binding.rectifierModuleEdit.visibility = View.GONE
                binding.rectifierModuleDelete.visibility = View.GONE
                binding.rectifierModuleAdd.visibility = View.GONE
            }else{
                Utils.expand(binding.rectifierModuleCollapse)
                binding.rectifierModuleRoot.isSelected = true
                binding.rectifierModuleArrow.rotation = 180f
                binding.rectifierModuleEdit.visibility = View.VISIBLE
                binding.rectifierModuleDelete.visibility = View.VISIBLE
                binding.rectifierModuleAdd.visibility = View.VISIBLE
            }
        }
        binding.connectedLoadsRoot.setOnClickListener {
            if(binding.connectedLoadsCollapse.visibility == View.VISIBLE){
                Utils.collapse(binding.connectedLoadsCollapse)
                binding.connectedLoadsRoot.isSelected = false
                binding.connectedLoadsArrow.rotation = 0f
                binding.connectedLoadsEdit.visibility = View.GONE
                binding.connectedLoadsDelete.visibility = View.GONE
                binding.connectedLoadsAdd.visibility = View.GONE
            }else{
                Utils.expand(binding.connectedLoadsCollapse)
                binding.connectedLoadsRoot.isSelected = true
                binding.connectedLoadsArrow.rotation = 180f
                binding.connectedLoadsEdit.visibility = View.VISIBLE
                binding.connectedLoadsDelete.visibility = View.VISIBLE
                binding.connectedLoadsAdd.visibility = View.VISIBLE
            }
        }
        binding.consumableMaterialsRoot.setOnClickListener {
            if(binding.consumableMaterialsCollapse.visibility == View.VISIBLE){
                Utils.collapse(binding.consumableMaterialsCollapse)
                binding.consumableMaterialsRoot.isSelected = false
                binding.consumableMaterialsArrow.rotation = 0f
                binding.consumableMaterialsEdit.visibility = View.GONE
                binding.consumableMaterialsDelete.visibility = View.GONE
                binding.consumableMaterialsAdd.visibility = View.GONE
            }else{
                Utils.expand(binding.consumableMaterialsCollapse)
                binding.consumableMaterialsRoot.isSelected = true
                binding.consumableMaterialsArrow.rotation = 180f
                binding.consumableMaterialsEdit.visibility = View.VISIBLE
                binding.consumableMaterialsDelete.visibility = View.VISIBLE
                binding.consumableMaterialsAdd.visibility = View.VISIBLE
            }
        }
        binding.poDetailsRoot.setOnClickListener {
            if(binding.poDetailsCollapse.visibility == View.VISIBLE){
                Utils.collapse(binding.poDetailsCollapse)
                binding.poDetailsRoot.isSelected = false
                binding.poDetailsArrow.rotation = 0f
                binding.poDetailsEdit.visibility = View.GONE
                binding.poDetailsDelete.visibility = View.GONE
                binding.poDetailsAdd.visibility = View.GONE
            }else{
                Utils.expand(binding.poDetailsCollapse)
                binding.poDetailsRoot.isSelected = true
                binding.poDetailsArrow.rotation = 180f
                binding.poDetailsEdit.visibility = View.VISIBLE
                binding.poDetailsDelete.visibility = View.VISIBLE
                binding.poDetailsAdd.visibility = View.VISIBLE
            }
        }

    }
}