package com.smarthub.baseapplication.ui.fragments.utilites.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.Smps1TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentSmp
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityRectifierModule
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.utilites.adapter.SmpsUtilityFragAdapter
import com.smarthub.baseapplication.ui.utilites.editdialouge.BatteryEquipmentDialouge
import com.smarthub.baseapplication.ui.utilites.editdialouge.InstalationAcceptanceDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SMPS1UitilitiesFrag(var smpsAllData: UtilityEquipmentSmp?,var smpsIndex:Int,var smpsAllDataId: Int?): BaseFragment(),
    SmpsUtilityFragAdapter.SmpsInfoListListener {
    lateinit var binding: Smps1TabUtilitiesFragmentBinding
    lateinit var viewmodel: HomeViewModel
    lateinit var adapter: SmpsUtilityFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Smps1TabUtilitiesFragmentBinding.inflate(inflater,container,false)
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listItem.layoutManager = LinearLayoutManager(requireContext())
        adapter= SmpsUtilityFragAdapter(this@SMPS1UitilitiesFrag,this@SMPS1UitilitiesFrag,smpsAllData)
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
                    smpsAllData=it.data.UtilityEquipment?.get(0)?.UtilityEquipmentSmps?.get(smpsIndex)
                    adapter.setData(it.data.UtilityEquipment?.get(0)?.UtilityEquipmentSmps?.get(smpsIndex))
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
        Toast.makeText(requireContext(),"attechments item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun EditInstallationAcceptence() {
        val dalouge = InstalationAcceptanceDialouge()
        dalouge.show(childFragmentManager,"")
        Toast.makeText(requireContext(),"Edit Installation item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun EditEquipmentItem() {
        Toast.makeText(requireContext(),"Edit Equipment item clicked",Toast.LENGTH_SHORT).show()
        val dalouge = BatteryEquipmentDialouge()
        dalouge.show(childFragmentManager,"")
    }

    override fun EditMaintenance() {
        Toast.makeText(requireContext(),"Edit maintenance  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editPoClicked(position: Int) {
        Toast.makeText(requireContext(),"Edit po table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewPoClicked(position: Int) {
        Toast.makeText(requireContext(),"view po table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editRectifireTableItem(position: Int,data: UtilityRectifierModule) {
        Toast.makeText(requireContext(),"Edit rectifier table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewRectifireTableItem(position: Int,data:UtilityRectifierModule) {
        Toast.makeText(requireContext(),"View rectifier table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editConnLoadsTableItem(position: Int) {
        Toast.makeText(requireContext(),"Edit Connections Loads table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewConnLoadsTableItem(position: Int) {
        Toast.makeText(requireContext(),"View Connection Loads table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editConsumMaterialTableItem(position: Int) {
        Toast.makeText(requireContext(),"Edit Consumable Materials table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewConsumMaterialTableItem(position: Int) {
        Toast.makeText(requireContext(),"View Consumable Materials table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editServiceTableItem(position: Int) {
        Toast.makeText(requireContext(),"Edit Service table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewServiceTableItem(position: Int) {
        Toast.makeText(requireContext(),"View Service table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun updateSMPSData(updatedData: UtilityEquipmentSmp) {
        showLoader()
        val utilityAllDataModel = UpdateUtilityEquipmentAllData()
        utilityAllDataModel.UtilityEquipmentSmps= arrayListOf(updatedData)
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
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.UtilityEquipmentSmps==200) {
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