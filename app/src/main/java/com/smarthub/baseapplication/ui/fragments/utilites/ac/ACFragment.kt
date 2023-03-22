package com.smarthub.baseapplication.ui.fragments.utilites.ac

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
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.*
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.utilites.ac.adapters.ACFragAdapter
import com.smarthub.baseapplication.ui.fragments.utilites.ac.editDialouge.ACConsumMaterialEditDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.ac.editDialouge.UtilityACMaintenanceEditDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.ac.editDialouge.UtilityACPoEditDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.viewDialouge.SmpsConsuMaterialViewDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.viewDialouge.UtilityMaintenanceViewDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.viewDialouge.UtilitySmpsPoViewDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class ACFragment(var ACAllData: UtilityEquipmentAC?, var smpsIndex:Int, var utilityAllDataId: Int?):BaseFragment(),
    ACFragAdapter.ACListListener {

    lateinit var binding:BatteryFragmentBinding
    lateinit var adapter: ACFragAdapter
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BatteryFragmentBinding.inflate(inflater,container,false)
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listItem.layoutManager = LinearLayoutManager(requireContext())
        adapter= ACFragAdapter(this@ACFragment,this@ACFragment,ACAllData)
        binding.listItem.adapter=adapter

        if (viewmodel.utilityEquipResponse?.hasActiveObservers() == true){
            viewmodel.utilityEquipResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.utilityEquipResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                AppLogger.log("ACFragment Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("ACFragment card Data fetched successfully")
                try {
                    ACAllData=it.data.UtilityEquipment?.get(0)?.UtilityEquipmentAC?.get(smpsIndex)
                    adapter.setData(it.data.UtilityEquipment?.get(0)?.UtilityEquipmentAC?.get(smpsIndex))
                }catch (e:java.lang.Exception){
                    AppLogger.log("ACFragment error : ${e.localizedMessage}")
                }
                AppLogger.log("ACFragment size :${it.data.UtilityEquipment?.get(0)?.UtilityEquipmentSmps?.size}")
            }else if (it!=null) {
                AppLogger.log("ACFragment error :${it.message}, it.data : ${it.data}, in line 71")
            }
            else {
                AppLogger.log("ACFragment Something went wrong")
            }
        }
    }


    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"attechments item clicked", Toast.LENGTH_SHORT).show()
    }


    override fun addAttachment(childIndex:Int?) {
        val bm = AttachmentCommonDialogBottomSheet("UtilityEquipmentAC",childIndex.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun editPoClicked(position: Int,data: UtilityPoDetails) {
        val bm = UtilityACPoEditDialouge(data,ACAllData,utilityAllDataId,
            object : UtilityACPoEditDialouge.UtilityPoUpdateListener {
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

    override fun editConsumMaterialTableItem(position: Int,data: UtilityConsumableMaterial) {
        val bm = ACConsumMaterialEditDialouge(data,ACAllData,utilityAllDataId,
            object : ACConsumMaterialEditDialouge.ACConsumMaterialUpdateListener {
                override fun updatedData() {
                    viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun viewConsumMaterialTableItem(position: Int,data: UtilityConsumableMaterial) {
        val bm= SmpsConsuMaterialViewDialouge(R.layout.utility_smpsconsu_material_view_dialouge,data)
        bm.show(childFragmentManager,"smpsRectifierView")      }

    override fun editMaintenamceTableItem(position: Int, data: UtilityPreventiveMaintenance) {
        val bm = UtilityACMaintenanceEditDialouge(data,ACAllData,utilityAllDataId,
            object : UtilityACMaintenanceEditDialouge.UtilityMaintenanceUpdateListener {
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

    override fun updateACData(updatedData: UtilityEquipmentAC) {
        showLoader()
        val utilityAllDataModel = UpdateUtilityEquipmentAllData()
        utilityAllDataModel.UtilityEquipmentAC= arrayListOf(updatedData)
        if (utilityAllDataId!=null)
            utilityAllDataModel.id=utilityAllDataId
        viewmodel.updateUtilityEquip(utilityAllDataModel)
        if (viewmodel.updateUtilityDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateUtilityDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateUtilityDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("ACFragment data updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && (it.data.status.Equipment==200 || it.data.status.InstallationAndAcceptence==200)) {
                AppLogger.log("ACFragment Data Updated successfully")
                viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("ACFragment Something went wrong in data update")
            }
            else if (it != null) {
                AppLogger.log("ACFragment in updateData error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("ACFragment Something went wrong in data update")

            }
        }
    }
}

