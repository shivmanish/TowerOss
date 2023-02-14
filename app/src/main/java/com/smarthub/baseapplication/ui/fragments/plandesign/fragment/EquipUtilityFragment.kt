package com.smarthub.baseapplication.ui.fragments.plandesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.UtilityEquipFragmentBinding
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.*
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.UtilityEquipAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.dialouge.*

class EquipUtilityFragment(var data:List<UtilityEquip>?) : Fragment(),UtilityEquipAdapter.ItemClicListiner{

    lateinit var binding: UtilityEquipFragmentBinding
    lateinit var adapter: UtilityEquipAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UtilityEquipFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listItem.layoutManager = LinearLayoutManager(requireContext())
        adapter= UtilityEquipAdapter(requireContext(),this@EquipUtilityFragment,data)
        binding.listItem.adapter=adapter
    }


    override fun smpsclicked(data: SMPS) {
        val dalouge = SMPSDialouge(data)
        dalouge.show(childFragmentManager, "")
    }

    override fun batterybankclicked(data: BatteryBank) {
        val dalouge = BatteryBankDialouge(data)
        dalouge.show(childFragmentManager, "")
    }

    override fun dgClicked(data: DG) {
        val dalouge = DgDialouge(data)
        dalouge.show(childFragmentManager, "")
    }

    override fun acClicked(data: AC) {
        val dalouge = AcUtilityDialouge(data)
        dalouge.show(childFragmentManager, "")
    }

    override fun fireExtinguisherClicked(data: FireExtinguisher) {
        val dalouge = FireDialouge(data)
        dalouge.show(childFragmentManager, "")
    }

    override fun surgeProtectedClicked(data: SurgeProtectionDevice) {
        val dalouge = SurgeProtectroDeviceDialouge(data)
        dalouge.show(childFragmentManager, "")
    }

    override fun dcdbClicked(data: DCDB) {
        val dalouge = DcdbDialouge(data)
        dalouge.show(childFragmentManager, "")
    }

    override fun attachmentClicked() {

    }

}