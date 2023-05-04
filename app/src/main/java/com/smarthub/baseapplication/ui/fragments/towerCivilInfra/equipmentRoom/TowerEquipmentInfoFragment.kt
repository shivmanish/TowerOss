package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.equipmentRoom

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
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.equipmentRoom.adapters.TowerEquipmentInfoAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.*
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.equipmentRoom.dialouges.*
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TowerEquipmentInfoFragment(var equipmentData: TowerAndCivilInfraEquipmentRoom?,var fullData:NewTowerCivilAllData?,var childIndex:Int): BaseFragment(),
    TowerEquipmentInfoAdapter.EquipmentItemListener {
    var viewmodel: HomeViewModel?=null
    lateinit var binding : TowerEquipmentInfoFragmentBinding
    lateinit var adapter: TowerEquipmentInfoAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = TowerEquipmentInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= TowerEquipmentInfoAdapter(this@TowerEquipmentInfoFragment,this@TowerEquipmentInfoFragment,equipmentData)
        binding.listItem.adapter = adapter

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

    override fun addAttachment() {
        val bm = AttachmentCommonDialogBottomSheet("TowerAndCivilInfraEquipmentRoom",equipmentData?.id.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun EditInstallationAcceptence() {
        val bottomSheetDialogFragment = EquipmentInstallationEditAdapter(R.layout.equiupment_installation_edit_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }

    override fun updateEquipmentRoom(data: TowerAndCivilInfraEquipmentRoom) {
        showLoader()
        val dataModel = NewTowerCivilAllData()
        dataModel.TowerAndCivilInfraEquipmentRoom= arrayListOf(data)
        if (fullData!=null)
            dataModel.id=fullData?.id
        viewmodel?.updateTwrCivilInfra(dataModel)
        if (viewmodel?.updateTwrCivilInfraDataResponse?.hasActiveObservers() == true) {
            viewmodel?.updateTwrCivilInfraDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.updateTwrCivilInfraDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("TowerEquipmentInfoFragment data updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("TowerEquipmentInfoFragment card Data Updated successfully")
                viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("TowerEquipmentInfoFragment Something went wrong in updating data")
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
            }
            else if (it != null) {
                AppLogger.log("TowerEquipmentInfoFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("TowerEquipmentInfoFragment Something went wrong in updating data")

            }
        }
    }
    
    override fun editPoClicked(data:TwrCivilPODetail) {
        val bm = EquipmentPoEditDialougeAdapter(data,equipmentData?.id,fullData?.id,
            object : EquipmentPoEditDialougeAdapter.EquipmentPoUpdateListner {
                override fun updateData() {
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager, "category")
    }

    override fun viewPoClicked(data:TwrCivilPODetail) {
        val bm = EquipmentPoViewDialougeAdapter(data)
        bm.show(childFragmentManager, "category")
    }

    override fun editConsumableClicked(data:TwrCivilConsumableMaterial) {
        val bm = EquipmentConsumEditAdapter(data,equipmentData?.id,fullData?.id,
            object : EquipmentConsumEditAdapter.EquipConsumUpdateListner {
                override fun updateData() {
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager, "category")
    }

    override fun viewConsumableClicked(data:TwrCivilConsumableMaterial) {
        val bm = EquipmentConsumViewAdapter(data)
        bm.show(childFragmentManager, "category")
    }

    override fun viewMaintenenceClicked(data: PreventiveMaintenance) {
        val bm= TowerMaintenenceViewAdapter(data)
        bm.show(childFragmentManager, "category")
    }
    override fun editMaintenenceClicked(data: PreventiveMaintenance) {
        val bm= EquipMaintenanceEditDialouge(data,equipmentData?.id,fullData?.id,
            object : EquipMaintenanceEditDialouge.EquipMaintenanceUpdateListener {
                override fun updatedData() {
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager, "category")
    }


}