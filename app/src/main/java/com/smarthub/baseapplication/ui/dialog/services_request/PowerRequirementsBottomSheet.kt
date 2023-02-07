package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BackhaulFiberBottomSheetDialogBinding
import com.smarthub.baseapplication.databinding.BackhaulMicrowaveBottomSheetDialogBinding
import com.smarthub.baseapplication.databinding.PowerRequirementDialougeLayoutBinding
import com.smarthub.baseapplication.databinding.RfFeasibilityBottomSheetDialogBinding
import com.smarthub.baseapplication.databinding.RfSectorBottomSheetDialogBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.serviceRequest.AssignACQTeam
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.BackhaulFeasibility
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.OpcoTSSR
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.PowerAndMcb
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.RFFeasibility
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerRequirementsBottomSheet(
    contentLayoutId: Int,
    var Id: String?,
    var viewmodel: HomeViewModel,
    var powerandmcb: PowerAndMcb?,
    var serviceRequestAllData: ServiceRequestAllDataItem?
) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var basicinfoModel: BasicinfoModel
    lateinit var binding : PowerRequirementDialougeLayoutBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basicinfoModel = BasicinfoModel()
        binding = PowerRequirementDialougeLayoutBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        if (powerandmcb != null && powerandmcb!!.PowerRequirements != null ) {
            powerandmcb!!.PowerRequirements.get(0).let {

//                AppPreferences.getInstance().setDropDown(
//                    binding.inputtype,
//                    DropDowns..name,
//                    it.InputType
//                )
//                binding.trxcounter.setText(it.TRXCount)

                binding.inputVoltage.setText(it.InputVoltage)
                binding.maxtotalpower.setText(it.MaxTotalPower)
                binding.batteryBackup.setText(it.BatteryBackupRequired)
            }

        }
        binding.include.update.setOnClickListener {
            powerandmcb!!.PowerRequirements!!.get(0).let {
//                it.TRXCount = binding.trxcounter.text.toString()
                it.InputType = binding.inputVoltage.text.toString()
                it.MaxTotalPower = binding.maxtotalpower.text.toString()
                it.BatteryBackupRequired = binding.batteryBackup.text.toString()
            }

            val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
            mServiceRequestAllDataItem.id = serviceRequestAllData!!.id
            mServiceRequestAllDataItem.OpcoTSSR = ArrayList()

            val opcoTSSR = OpcoTSSR()
            opcoTSSR.PowerAndMCB = ArrayList()
            opcoTSSR.PowerAndMCB?.add(powerandmcb!!)
            opcoTSSR.id = serviceRequestAllData!!.OpcoTSSR!![0].id
            mServiceRequestAllDataItem.OpcoTSSR?.add(opcoTSSR)
            val serviceRequestList = ServiceRequestAllData()
            serviceRequestList.add(mServiceRequestAllDataItem)

            basicinfoModel?.ServiceRequestMain = serviceRequestList
            basicinfoModel?.id = Id!!
            viewmodel.updateBasicInfo(basicinfoModel!!)
        }


    }

    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PowerRequirementDialougeLayoutBinding.inflate(inflater)
        return binding.root
    }

}