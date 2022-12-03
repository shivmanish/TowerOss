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
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.Ac1TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.databinding.Smps1TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.Utils

class SMPS1UitilitiesFrag: Fragment(), ImageAttachmentAdapter.ItemClickListener {

    var binding : Smps1TabUtilitiesFragmentBinding?=null
    var equipmentCollapse : Boolean=false
    var installationCollapse : Boolean=false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Smps1TabUtilitiesFragmentBinding.inflate(inflater, container, false)
        initViews()
        return binding?.root
    }


    fun initViews(){
//        var b = view.findViewById<View>(R.id.attach_card)
//        b.setOnClickListener {
//
//        }
        binding?.collapsingLayoutEquipment?.setOnClickListener{
            if(equipmentCollapse==true)
            {
                equipmentCollapse=false
                binding?.itemCollapseEquipment?.visibility=View.VISIBLE
                binding?.itemTitleEqipment?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_arrow_up,0)
            }
            else{
                equipmentCollapse=true
                binding?.itemCollapseEquipment?.visibility=View.GONE
                binding?.itemTitleEqipment?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_arrow_down_black,0)
            }
        }
        binding?.collapsingInstallationAcceptance?.setOnClickListener{
            if(installationCollapse==true)
            {
                installationCollapse=false
                binding?.itemCollapseInstallations?.visibility=View.VISIBLE
                binding?.InstallationText?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_arrow_up,0)
            }
            else{
                installationCollapse=true
                binding?.itemCollapseInstallations?.visibility=View.GONE
                binding?.InstallationText?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_arrow_down_black,0)
            }
        }
    }

    override fun itemClicked() {
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }
}