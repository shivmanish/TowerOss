package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerEquipmentInfoFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraEquipmentModel
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.*
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TowerEquipmentInfoFragment(var equipmentData: TowerAndCivilInfraEquipmentModel?, var id:String?, var index:Int): Fragment(), TowerEquipmentInfoAdapter.TowerPoleListListener {
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
        binding?.listItem?.adapter = adapter

        if (viewmodel?.TowerCivilInfraModelResponse?.hasActiveObservers() == true){
            viewmodel?.TowerCivilInfraModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.TowerCivilInfraModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                binding.swipeLayout.isRefreshing=false
                AppLogger.log("TowerCivil Fragment card Data fetched successfully")
                try {
                    adapter.setData(it.data.item!![0].TowerAndCivilInfra.get(0).TowerAndCivilInfraEquipmentModel.get(index))
                }catch (e:java.lang.Exception){
                    AppLogger.log("TowerCivil Fragment error : ${e.localizedMessage}")
                    Toast.makeText(context,"TowerCivil Fragment error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
                }
                AppLogger.log("size :${it.data.item?.size}")
            }else if (it!=null) {
                Toast.makeText(requireContext(),"TowerCivil Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("TowerCivil Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("TowerCivil Fragment Something went wrong")
                Toast.makeText(requireContext(),"TowerCivil Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        binding.swipeLayout.setOnRefreshListener {
            viewmodel?.TowerAndCivilRequestAll(id!!)
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

    override fun viewPoClicked(position: Int) {
        var bm = EquipmentPoViewDialougeAdapter(R.layout.equipment_room_po_view_dialouge)
        bm.show(childFragmentManager, "category")
    }

    override fun editConsumableClicked(position: Int) {
        var bm = EquipmentConsumEditAdapter(R.layout.equipment_consumable_edit_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewConsumableClicked(position: Int) {
        var bm = EquipmentConsumViewAdapter(R.layout.equipment_consumable_table_view_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }


}