package com.smarthub.baseapplication.ui.utilites.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.BatteryFragmentBinding
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.BatteryBank
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.UtilitieSmp
import com.smarthub.baseapplication.ui.utilites.adapter.BatteryFragAdapter
import com.smarthub.baseapplication.ui.utilites.editdialouge.BatteryEquipmentDialouge
import com.smarthub.baseapplication.ui.utilites.editdialouge.InstalationAcceptanceDialouge
import com.smarthub.baseapplication.utils.Utils

class BatteryFragment(var BatteryAllData: BatteryBank?, id:String):Fragment(),BatteryFragAdapter.BatterryBankListListener {

    lateinit var binding:BatteryFragmentBinding
    lateinit var adapter: BatteryFragAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BatteryFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listItem.layoutManager = LinearLayoutManager(requireContext())
        adapter= BatteryFragAdapter(requireContext(),this@BatteryFragment,BatteryAllData)
        binding.listItem.adapter=adapter
    }


    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"attechments item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun EditInstallationAcceptence() {
        val dalouge = InstalationAcceptanceDialouge()
        dalouge.show(childFragmentManager,"")
        Toast.makeText(requireContext(),"Edit Installation item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun EditEquipmentItem() {
        Toast.makeText(requireContext(),"Edit Equipment item clicked", Toast.LENGTH_SHORT).show()
        val dalouge = BatteryEquipmentDialouge()
        dalouge.show(childFragmentManager,"")
    }

    override fun editPoClicked(position: Int) {
        Toast.makeText(requireContext(),"Edit po table  item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun viewPoClicked(position: Int) {
        Toast.makeText(requireContext(),"view po table  item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun editBatteryTableItem(position: Int) {
        Toast.makeText(requireContext(),"Edit rectifier table  item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun viewBatteryTableItem(position: Int) {
        Toast.makeText(requireContext(),"View rectifier table  item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun editConsumMaterialTableItem(position: Int) {
        Toast.makeText(requireContext(),"Edit Consumable Materials table  item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun viewConsumMaterialTableItem(position: Int) {
        Toast.makeText(requireContext(),"View Consumable Materials table  item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun editServiceTableItem(position: Int) {
        Toast.makeText(requireContext(),"Edit Service table  item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun viewServiceTableItem(position: Int) {
        Toast.makeText(requireContext(),"View Service table  item clicked", Toast.LENGTH_SHORT).show()
    }
}

