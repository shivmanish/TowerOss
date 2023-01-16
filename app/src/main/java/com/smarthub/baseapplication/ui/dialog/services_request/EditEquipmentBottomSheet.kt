package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.EditEquipementDialougeBinding
import com.smarthub.baseapplication.model.serviceRequest.*
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class EditEquipmentBottomSheet(
    contentLayoutId: Int,
    var equipmant: Equipment,
    var serviceRequestAllData: ServiceRequestAllDataItem,
    var viewmodel: HomeViewModel,
    var Id: String,
) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding: EditEquipementDialougeBinding
    lateinit var equipment: Equipment
    var basicinfoModel: BasicinfoModel? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basicinfoModel = BasicinfoModel()
        binding = EditEquipementDialougeBinding.bind(view)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight() * 0.75).toInt()
        binding.canecl.setOnClickListener {
            dismiss()
        }

        equipmant.let {
            binding.technology.setText(it.Technology)
            binding.equipment.setText("NA")
            binding.type.setText("NA")
            binding.cabinetsize.setText("NA")
            binding.equipmentWeight.setText(it.EquipmentWeight)
            binding.inputPower.setText(it.InputPower)
            binding.voltage.setText(it.Voltage)
            binding.maxPowerRating.setText(it.MaxPowerRating)
            binding.operatingPower.setText(it.OperatingTemp)
        }

        binding.update.setOnClickListener {
            equipmant.let {
                it.Technology = binding.technology.text.toString()
                it.EquipmentWeight = binding.equipmentWeight.text.toString()
                it.InputPower = binding.inputPower.text.toString()
                it.Voltage = binding.voltage.text.toString()
                it.MaxPowerRating = binding.maxPowerRating.text.toString()
                it.OperatingTemp = binding.operatingPower.text.toString()
            }
            val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
            mServiceRequestAllDataItem.id = serviceRequestAllData.id
            mServiceRequestAllDataItem.ServiceRequest = ArrayList()

            val serviceRequest = ServiceRequest()
            serviceRequest.Equipments = ArrayList()
            (serviceRequest.Equipments as ArrayList<Equipment>)?.add(equipmant)
            serviceRequest.id = serviceRequestAllData.ServiceRequest!![0].id
            mServiceRequestAllDataItem.ServiceRequest?.add(serviceRequest)

            val serviceRequestList = ServiceRequestAllData()
            serviceRequestList.add(mServiceRequestAllDataItem)

            basicinfoModel?.ServiceRequestMain = serviceRequestList
            basicinfoModel?.id = Id
            viewmodel.updateBasicInfo(basicinfoModel!!)

        }
    }

    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditEquipementDialougeBinding.inflate(inflater)
        return binding.root
    }
}