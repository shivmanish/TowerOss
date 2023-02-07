package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BackhaulPlanningBootomSheetDialogBinding
import com.smarthub.baseapplication.model.serviceRequest.BackHaul
import com.smarthub.baseapplication.model.serviceRequest.FeasibilityPlanning
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class BackhualPlanningBottomSheet(
    contentLayoutId: Int,
    var id: String,
    var viewmodel: HomeViewModel,
    var backHaul: BackHaul?,
    var serviceRequestAllData: ServiceRequestAllDataItem
) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : BackhaulPlanningBootomSheetDialogBinding
    var basicinfoModel: BasicinfoModel? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basicinfoModel = BasicinfoModel()
        binding = BackhaulPlanningBootomSheetDialogBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        try {
            backHaul!!.Microwave.get(0).let {
                binding.editAntennaCount.setText(it.AntennaCount)
                binding.editMaxheight.setText(it.MaxHeight)
                binding.editTotalWaight.setText(it.TotalWeight)
                binding.editOffsetPoleCount.setText(it.OffsetPoleCount)
                binding.editAntennaSpace.setText(it.AntennaSpace)
                binding.editTotalWaight.setText(it.TotalWeight)
                binding.editOffsetPoleCount.setText(it.OffsetPoleCount)
                binding.editAntennaSpace.setText(it.AntennaSpace)
                binding.editOffsetPoleCount.setText(it.OffsetPoleCount)
                binding.editAntennaSpace.setText(it.AntennaSpace)
                binding.editRemark.setText(it.Remark)

            }

            backHaul!!.Fiber.get(0).let {
                binding.editLMLength.setText(it.LMLength)
                binding.editCableLength.setText(it.CableLength)
                binding.editFibreCore.setText(it.FiberCore)
                binding.editLayingType.setText(it.FiberLaying)
                binding.editRemark1.setText(it.Remark)
            }

        }catch (e:Exception){
            AppLogger.log("Error in Feasibility planing Adapter ${e.localizedMessage}")
        }

        binding.include.update.setOnClickListener {
            backHaul?.let {

                val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
                mServiceRequestAllDataItem.id = serviceRequestAllData?.id
                mServiceRequestAllDataItem.FeasibilityPlanning = ArrayList()

                val fesibiliity = FeasibilityPlanning()
                fesibiliity.BackHaul = ArrayList()
                fesibiliity.BackHaul!!.add(it)
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
        binding = BackhaulPlanningBootomSheetDialogBinding.inflate(inflater)
        return binding.root
    }
}