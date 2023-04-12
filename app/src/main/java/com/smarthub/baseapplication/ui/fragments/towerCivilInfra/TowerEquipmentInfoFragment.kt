package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerEquipmentInfoFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.*
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.*
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TowerEquipmentInfoFragment(var equipmentData: TowerAndCivilInfraEquipmentRoom?,var fullData:NewTowerCivilAllData?,var childIndex:Int): BaseFragment(), TowerEquipmentInfoAdapter.EquipmentItemListener {
    var viewmodel: HomeViewModel?=null
    lateinit var binding : TowerEquipmentInfoFragmentBinding
    lateinit var adapter:TowerEquipmentInfoAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = TowerEquipmentInfoFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=TowerEquipmentInfoAdapter(requireContext(),this@TowerEquipmentInfoFragment,equipmentData)
        binding.listItem?.adapter = adapter

        if (viewmodel?.TowerCivilInfraModelResponse?.hasActiveObservers() == true){
            viewmodel?.TowerCivilInfraModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.TowerCivilInfraModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                binding.swipeLayout.isRefreshing=false
                hideLoader()
                AppLogger.log("TowerEquipmentInfoFragment card Data fetched successfully")
                try {
                    adapter.setData(it.data.TowerAndCivilInfra?.get(0)?.TowerAndCivilInfraEquipmentRoom?.get(childIndex))
                    fullData=it.data.TowerAndCivilInfra?.get(0)
                    equipmentData=it.data.TowerAndCivilInfra?.get(0)?.TowerAndCivilInfraEquipmentRoom?.get(childIndex)
                }catch (e:java.lang.Exception){
                    AppLogger.log("TowerEquipmentInfoFragment error : ${e.localizedMessage}")
                }
                AppLogger.log("size :${it.data.TowerAndCivilInfra?.size}")
            }else if (it!=null) {
                AppLogger.log("TowerEquipmentInfoFragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("TowerEquipmentInfoFragment Something went wrong")
            }
        }

        binding.swipeLayout.setOnRefreshListener {
            viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
        }
    }

    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }
    override fun EditInstallationAcceptence() {
        val bottomSheetDialogFragment = EquipmentInstallationEditAdapter(R.layout.equiupment_installation_edit_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }

    override fun EditEquipmentRoomItem() {

        val bottomSheetDialogFragment = EquipmentRoomInfoEditAdapter(R.layout.equipment_room_info_edit_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }
    override fun editPoClicked(position: Int) {
        var bm = EquipmentPoEditDialougeAdapter(R.layout.tower_equipment_po_edit_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewPoClicked(position: Int,data:TwrCivilPODetail) {
        var bm = EquipmentPoViewDialougeAdapter(R.layout.equipment_room_po_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun editConsumableClicked(position: Int) {
        var bm = EquipmentConsumEditAdapter(R.layout.equipment_consumable_edit_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewConsumableClicked(position: Int,data:TwrCivilConsumableMaterial) {
        var bm = EquipmentConsumViewAdapter(R.layout.equipment_consumable_table_view_dialouge,data)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewMaintenenceClicked(position: Int, data: PreventiveMaintenance) {
        val bm= TowerMaintenenceViewAdapter(data)
        bm.show(childFragmentManager, "category")
    }


}