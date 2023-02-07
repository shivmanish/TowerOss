package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerMsbPlanningBootomShhetDialogBinding
import com.smarthub.baseapplication.model.serviceRequest.FeasibilityPlanning
import com.smarthub.baseapplication.model.serviceRequest.PowerAndMCB
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerMsbPlanningBottomSheet(
    contentLayoutId: Int,
    var id: String,
    var viewmodel: HomeViewModel,
    var powerAndMcb: PowerAndMCB?,
    var serviceRequestAllData: ServiceRequestAllDataItem
) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : PowerMsbPlanningBootomShhetDialogBinding
    var basicinfoModel: BasicinfoModel? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basicinfoModel = BasicinfoModel()
        binding = PowerMsbPlanningBootomShhetDialogBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        try {
            powerAndMcb!!.Power.get(0).let {
                binding.editPowerType.setText(it.PowerType)
                binding.editVoltage.setText(it.Voltage)
                binding.editMaxTotalPower.setText(it.MaxTotalPower)
                binding.editBasicSitePowerrating.setText(it.BasicSitePowerRating)
                binding.editAdditionalPower.setText(it.AdditionalPower)
                binding.editTotalSitePower.setText(it.TotalSitePower)
                binding.editBasicBatteryBackup.setText(it.BasicBatteryBackup)
                binding.editAdditionalBatteryBackup.setText(it.AdditionalBatteryBackup)
                binding.editTotalBatterybackup.setText(it.TotalBatteryBackup)
                binding.editPowerRequirement.setText(it.PowerRequirement)
                binding.editTimeline.setText(it.Timeline)
                binding.editRemark.setText(it.Remark)

            }

            powerAndMcb!!.MCB[0].let {
                binding.editMCBRequirement.setText(it.MCBRequirement)
                binding.editMCBCount.setText(it.TotalMCBCount)
                binding.editRemark.setText(it.Remark)
            }

        }catch (e:Exception){
            AppLogger.log("Error in Feasibility planing Adapter ${e.localizedMessage}")
        }


        binding.include.update.setOnClickListener {
            powerAndMcb?.let {

                val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
                mServiceRequestAllDataItem.id = serviceRequestAllData?.id
                mServiceRequestAllDataItem.FeasibilityPlanning = ArrayList()

                val fesibiliity = FeasibilityPlanning()
                fesibiliity.PowerAndMCB = ArrayList()
                fesibiliity.PowerAndMCB!!.add(it)
                fesibiliity.id = serviceRequestAllData?.FeasibilityPlanning!![0].id
                mServiceRequestAllDataItem.FeasibilityPlanning?.add(fesibiliity)

                val serviceRequestList = ServiceRequestAllData()
                serviceRequestList.add(mServiceRequestAllDataItem)

                basicinfoModel?.ServiceRequestMain = serviceRequestList
                basicinfoModel?.id = id
                viewmodel?.updateBasicInfo(basicinfoModel!!)
            }
        }
    }
    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PowerMsbPlanningBootomShhetDialogBinding.inflate(inflater)
        return binding.root
    }
}