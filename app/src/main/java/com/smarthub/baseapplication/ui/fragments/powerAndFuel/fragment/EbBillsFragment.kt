package com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.EbBillsFragmentBinding
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.EbBillsDialouge
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo.PowerAndFuel
import com.smarthub.baseapplication.utils.Utils

class EbBillsFragment : Fragment() {

    lateinit var binding: EbBillsFragmentBinding
    var data: PowerAndFuel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EbBillsFragmentBinding.inflate(inflater, container, false)
        setView()
        return binding.root
    }

    fun setView() {
        binding.equipmentEdit.setOnClickListener {
            val dalouge = EbBillsDialouge()
            dalouge.show(childFragmentManager, "")
        }
        binding.equipmentRoot.setOnClickListener {
            if (binding.itemCollapseEquipment.visibility == View.VISIBLE) {
                Utils.collapse(binding.itemCollapseEquipment)
                binding.equipmentArrow.rotation = 0f
                binding.equipmentEdit.visibility = View.GONE
                binding.equipmentRoot.isSelected = false
            } else {
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