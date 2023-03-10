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
import com.smarthub.baseapplication.databinding.Smps2TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.dialog.utils.ConnectedLoadsBottomSheetDialog
import com.smarthub.baseapplication.ui.dialog.utils.RectifierModuleBottomSheetDialog
import com.smarthub.baseapplication.ui.utilites.adapter.SMPSViewRecyclerAdapter
import com.smarthub.baseapplication.ui.utilites.editdialouge.*
import com.smarthub.baseapplication.utils.Utils

class SMPS2UitilitiesFrag: Fragment() {

    lateinit var binding: Smps2TabUtilitiesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Smps2TabUtilitiesFragmentBinding.inflate(inflater,container,false)
        setView()
        return binding.root
    }

    fun setView(){
        binding.maintenanceEdit.setOnClickListener{
            val dalouge = MaintanceBottomSheetDialog()
            dalouge.show(childFragmentManager,"")
        }
        binding.ServiceDetailsEdit.setOnClickListener{
            val dalouge = ServicesDetailsSMPSBottomSheetDialog()
            dalouge.show(childFragmentManager,"")
        }
        binding.serviceDetailsRecycler.rv.adapter=SMPSViewRecyclerAdapter()
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
        binding.ServiceDetailsRoot.setOnClickListener {
            if(binding.ServiceDetailsCollasp.visibility == View.VISIBLE){
                Utils.collapse(binding.ServiceDetailsCollasp)
                binding.ServiceDetailsArrow.rotation = 0f
                binding.ServiceDetailsEdit.visibility = View.GONE
                binding.ServiceDetailsDelete.visibility = View.GONE
                binding.ServiceDetailsAdd.visibility = View.GONE
                binding.ServiceDetailsRoot.isSelected = false
            }else{
                Utils.expand(binding.ServiceDetailsCollasp)
                binding.ServiceDetailsRoot.isSelected = true
                binding.ServiceDetailsArrow.rotation = 180f
                binding.ServiceDetailsEdit.visibility = View.VISIBLE
                binding.ServiceDetailsDelete.visibility = View.VISIBLE
                binding.ServiceDetailsAdd.visibility =  View.VISIBLE
            }
        }

    }
}