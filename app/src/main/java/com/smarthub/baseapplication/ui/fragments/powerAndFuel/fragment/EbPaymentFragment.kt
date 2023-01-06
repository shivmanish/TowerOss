package com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.EbPaymentsFragmentBinding
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.EbPaymentDialouge
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo.PowerAndFuel
import com.smarthub.baseapplication.utils.Utils

class EbPaymentFragment:Fragment() {

    lateinit var binding:EbPaymentsFragmentBinding
    var data: PowerAndFuel?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EbPaymentsFragmentBinding.inflate(inflater,container,false)
        setView()
        return binding.root
    }

    fun setView(){
        binding.equipmentEdit.setOnClickListener{
            val dalouge = EbPaymentDialouge()
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

    fun setDatavalue(data: PowerAndFuel) {
this.data = data
    }

}