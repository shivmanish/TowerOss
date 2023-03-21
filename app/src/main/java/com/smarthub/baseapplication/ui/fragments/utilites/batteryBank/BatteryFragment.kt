package com.smarthub.baseapplication.ui.fragments.utilites.batteryBank

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
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityConsumableMaterial
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentBatteryBank
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityPoDetails
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityPreventiveMaintenance
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.utilites.batteryBank.adapters.BatteryFragAdapter
import com.smarthub.baseapplication.ui.fragments.utilites.batteryBank.editDialouge.BatteryConsumMaterialEditDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.batteryBank.editDialouge.UtilityBatteryMaintenanceEditDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.batteryBank.editDialouge.UtilityBatteryPoEditDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.viewDialouge.SmpsConsuMaterialViewDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.viewDialouge.UtilityMaintenanceViewDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.viewDialouge.UtilitySmpsPoViewDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class BatteryFragment(var baterryAllData: UtilityEquipmentBatteryBank?, var smpsIndex:Int, var smpsAllDataId: Int?):BaseFragment(),
    BatteryFragAdapter.BatterryBankListListener {

    lateinit var binding:BatteryFragmentBinding
    lateinit var adapter: BatteryFragAdapter
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BatteryFragmentBinding.inflate(inflater,container,false)
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listItem.layoutManager = LinearLayoutManager(requireContext())
        adapter= BatteryFragAdapter(this@BatteryFragment,this@BatteryFragment,baterryAllData)
        binding.listItem.adapter=adapter

        if (viewmodel.utilityEquipResponse?.hasActiveObservers() == true){
            viewmodel.utilityEquipResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.utilityEquipResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                AppLogger.log("UtilityEquipSmps Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("UtilityEquipSmps Fragment card Data fetched successfully")
                try {
                    baterryAllData=it.data.UtilityEquipment?.get(0)?.UtilityEquipmentBatteryBank?.get(smpsIndex)
                    adapter.setData(it.data.UtilityEquipment?.get(0)?.UtilityEquipmentBatteryBank?.get(smpsIndex))
                }catch (e:java.lang.Exception){
                    AppLogger.log("UtilityEquipSmps Fragment error : ${e.localizedMessage}")
                }
                AppLogger.log("UtilityEquipSmps size :${it.data.UtilityEquipment?.get(0)?.UtilityEquipmentSmps?.size}")
            }else if (it!=null) {
                AppLogger.log("UtilityEquipSmps Fragment error :${it.message}, it.data : ${it.data}, in line 71")
            }
            else {
                AppLogger.log("UtilityEquipSmps Fragment Something went wrong")
            }
        }
    }


    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"attechments item clicked", Toast.LENGTH_SHORT).show()
    }


    override fun addAttachment(childIndex:Int?) {
        val bm = AttachmentCommonDialogBottomSheet("UtilityEquipmentBatteryBank",childIndex.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun editPoClicked(position: Int,data: UtilityPoDetails) {
        val bm = UtilityBatteryPoEditDialouge(data,baterryAllData,smpsAllDataId,
            object : UtilityBatteryPoEditDialouge.UtilityPoUpdateListener {
                override fun updatedData() {
                    viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager,"sdg")    }

    override fun viewPoClicked(position: Int,data:UtilityPoDetails) {
        val bm= UtilitySmpsPoViewDialouge(R.layout.tower_po_view_dialouge,data)
        bm.show(childFragmentManager,"smpsRectifierView")    }

    override fun editConsumMaterialTableItem(position: Int,data: UtilityConsumableMaterial) {
        val bm = BatteryConsumMaterialEditDialouge(data,baterryAllData,smpsAllDataId,
            object : BatteryConsumMaterialEditDialouge.BatteryConsumMaterialUpdateListener {
                override fun updatedData() {
                    viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager,"sdg")     }

    override fun viewConsumMaterialTableItem(position: Int,data: UtilityConsumableMaterial) {
        val bm= SmpsConsuMaterialViewDialouge(R.layout.utility_smpsconsu_material_view_dialouge,data)
        bm.show(childFragmentManager,"smpsRectifierView")      }

    override fun editMaintenamceTableItem(position: Int, data: UtilityPreventiveMaintenance) {
        val bm = UtilityBatteryMaintenanceEditDialouge(data,baterryAllData,smpsAllDataId,
            object : UtilityBatteryMaintenanceEditDialouge.UtilityMaintenanceUpdateListener {
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

    override fun updateBatteryData(updatedData: UtilityEquipmentBatteryBank) {
        showLoader()
        val utilityAllDataModel = UpdateUtilityEquipmentAllData()
        utilityAllDataModel.UtilityEquipmentBatteryBank= arrayListOf(updatedData)
        if (smpsAllDataId!=null)
            utilityAllDataModel.id=smpsAllDataId
        viewmodel.updateUtilityEquip(utilityAllDataModel)
        if (viewmodel.updateUtilityDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateUtilityDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateUtilityDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SMPS1UitilitiesFrag Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && (it.data.status.Equipment==200 || it.data.status.InstallationAndAcceptence==200)) {
                AppLogger.log("SMPS1UitilitiesFrag Fragment card Data fetched successfully")
                viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("SMPS1UitilitiesFrag Fragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("SMPS1UitilitiesFrag Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SMPS1UitilitiesFrag Fragment Something went wrong")

            }
        }
    }
}

