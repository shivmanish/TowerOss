package com.smarthub.baseapplication.ui.fragments.plandesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.UtilityEquipFragmentBinding
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.UtilityEquip
import com.smarthub.baseapplication.ui.fragments.plandesign.tableAdapters.SmpsTableAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.TableCallback
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.UtilityEquipAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.equipmentRoomAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.dialouge.*
import com.smarthub.baseapplication.utils.Utils

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


    override fun smpsclicked() {
        val dalouge = SMPSDialouge()
        dalouge.show(childFragmentManager, "")
    }

    override fun batterybankclicked() {
        val dalouge = BatteryBankDialouge()
        dalouge.show(childFragmentManager, "")
    }

    override fun dgClicked() {
        val dalouge = AcUtilityDialouge()
        dalouge.show(childFragmentManager, "")
    }

    override fun acClicked() {
        val dalouge = AcUtilityDialouge()
        dalouge.show(childFragmentManager, "")
    }

    override fun fireExtinguisherClicked() {
        val dalouge = FireDialouge()
        dalouge.show(childFragmentManager, "")
    }

    override fun surgeProtectedClicked() {
        val dalouge = SurgeProtectroDeviceDialouge()
        dalouge.show(childFragmentManager, "")
    }

    override fun dcdbClicked() {
        val dalouge = DcdbDialouge()
        dalouge.show(childFragmentManager, "")
    }

    override fun attachmentClicked() {

    }

}