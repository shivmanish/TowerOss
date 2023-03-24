package com.smarthub.baseapplication.ui.fragments.utilites.fireExtinguisher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BatteryFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentFireExtinguisher
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityPoDetails
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityPreventiveMaintenance
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.utilites.fireExtinguisher.adapters.FireExtinguisherFragAdapter
import com.smarthub.baseapplication.ui.fragments.utilites.fireExtinguisher.editDialouge.UtilityFireExtMaintenanceEditDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.fireExtinguisher.editDialouge.UtilityFireExtPoEditDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.viewDialouge.UtilityMaintenanceViewDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.viewDialouge.UtilitySmpsPoViewDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class FireExtinguisherFragment(var FireExtAllData: UtilityEquipmentFireExtinguisher?, var fireExtIndex:Int, var utilityAllDataId: Int?):BaseFragment(),
    FireExtinguisherFragAdapter.FireExtListListener {

    lateinit var binding:BatteryFragmentBinding
    lateinit var adapter: FireExtinguisherFragAdapter
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BatteryFragmentBinding.inflate(inflater,container,false)
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listItem.layoutManager = LinearLayoutManager(requireContext())
        adapter= FireExtinguisherFragAdapter(this@FireExtinguisherFragment,this@FireExtinguisherFragment,FireExtAllData)
        binding.listItem.adapter=adapter

        if (viewmodel.utilityEquipResponse?.hasActiveObservers() == true){
            viewmodel.utilityEquipResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.utilityEquipResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                AppLogger.log("FireExtinguisherFragment Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("FireExtinguisherFragment card Data fetched successfully")
                try {
                    FireExtAllData=it.data.UtilityEquipment?.get(0)?.UtilityEquipmentFireExtinguisher?.get(fireExtIndex)
                    adapter.setData(it.data.UtilityEquipment?.get(0)?.UtilityEquipmentFireExtinguisher?.get(fireExtIndex))
                }catch (e:java.lang.Exception){
                    AppLogger.log("FireExtinguisherFragment error : ${e.localizedMessage}")
                }
                AppLogger.log("FireExtinguisherFragment size :${it.data.UtilityEquipment?.get(0)?.UtilityEquipmentSmps?.size}")
            }else if (it!=null) {
                AppLogger.log("FireExtinguisherFragment error :${it.message}, it.data : ${it.data}, in line 71")
            }
            else {
                AppLogger.log("FireExtinguisherFragment Something went wrong")
            }
        }
    }


    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"attechments item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun addEquipmentClicked() {
        val bm = AddNewUtilityFireEquipDialouge(
            FireExtAllData, utilityAllDataId,
            object : AddNewUtilityFireEquipDialouge.AddUtilityFireEquipDataListener {
                override fun addNewData(){
                    showLoader()
                    viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }


    override fun addAttachment(childIndex:Int?) {
        val bm = AttachmentCommonDialogBottomSheet("UtilityEquipmentFireExtinguisher",childIndex.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun editPoClicked(position: Int,data: UtilityPoDetails) {
        val bm = UtilityFireExtPoEditDialouge(data,FireExtAllData,utilityAllDataId,
            object : UtilityFireExtPoEditDialouge.UtilityPoUpdateListener {
                override fun updatedData() {
                    viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager,"sdg")
    }



    override fun viewPoClicked(position: Int,data:UtilityPoDetails) {
        val bm= UtilitySmpsPoViewDialouge(R.layout.tower_po_view_dialouge,data)
        bm.show(childFragmentManager,"smpsRectifierView")
    }

    override fun editMaintenamceTableItem(position: Int, data: UtilityPreventiveMaintenance) {
        val bm = UtilityFireExtMaintenanceEditDialouge(data,FireExtAllData,utilityAllDataId,
            object : UtilityFireExtMaintenanceEditDialouge.UtilityMaintenanceUpdateListener {
                override fun updatedData() {
                    viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun viewMaintenanceTableItem(position: Int, data: UtilityPreventiveMaintenance) {
        val bm= UtilityMaintenanceViewDialouge(R.layout.tower_po_view_dialouge,data)
        bm.show(childFragmentManager,"smpsRectifierView")
    }

    override fun updateFireExtData(updatedData: UtilityEquipmentFireExtinguisher) {
        showLoader()
        val utilityAllDataModel = UpdateUtilityEquipmentAllData()
        utilityAllDataModel.UtilityEquipmentFireExtinguisher= arrayListOf(updatedData)
        if (utilityAllDataId!=null)
            utilityAllDataModel.id=utilityAllDataId
        viewmodel.updateUtilityEquip(utilityAllDataModel)
        if (viewmodel.updateUtilityDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateUtilityDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateUtilityDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("FireExtinguisherFragment data updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.UtilityEquipmentFireExtinguisher==200) {
                AppLogger.log("FireExtinguisherFragment Data Updated successfully")
                viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("FireExtinguisherFragment Something went wrong in data update")
            }
            else if (it != null) {
                AppLogger.log("FireExtinguisherFragment in updateData error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("FireExtinguisherFragment Something went wrong in data update")

            }
        }
    }
}

