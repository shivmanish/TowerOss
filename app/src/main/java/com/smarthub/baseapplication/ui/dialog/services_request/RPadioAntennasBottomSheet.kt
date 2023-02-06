package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FpAntinaBotomSheetDialogBinding
import com.smarthub.baseapplication.model.serviceRequest.FeasibilityPlanning
import com.smarthub.baseapplication.model.serviceRequest.RadioAntennaX
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class RPadioAntennasBottomSheet(
    contentLayoutId: Int,
    var id: String,
    var viewmodel: HomeViewModel,
    var radioAntena: RadioAntennaX?,
    var serviceRequestAllData: ServiceRequestAllDataItem
) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding : FpAntinaBotomSheetDialogBinding
    var basicinfoModel: BasicinfoModel? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            basicinfoModel = BasicinfoModel() 
        binding = FpAntinaBotomSheetDialogBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        try{
            radioAntena.let {
                binding.editTechnology.setText(it?.RFTechnology)
                binding.editRRUCount.setText(it?.RRUCount)
                binding.editSectocCount.setText(it?.SectorCount)
                binding.editMaxAntenna.setText(it?.MaxAntennaHeight)
                binding.editAntennaType.setText(it?.AntennaType)
                binding.editTower.setText(it?.TowerOrPoleHeight)
                binding.editAdditional.setText(it?.AdditionalPoleHeight)
                binding.editTotalWaight.setText(it?.TotalWeight)
                binding.editOffsetPoleCount.setText(it?.OffsetPoleCount)
                binding.editAntennaSpace.setText(it?.AntennaSpace)
                binding.editTimeline.setText(it?.Timeline)
            }
        }catch (e:Exception){
            AppLogger.log("Error in Feasibility planing Adapter ${e.localizedMessage}")
        }

        binding.include.update.setOnClickListener {
            radioAntena?.let {
                
                val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
                mServiceRequestAllDataItem.id = serviceRequestAllData?.id
                mServiceRequestAllDataItem.FeasibilityPlanning = ArrayList()
                
                val fesibiliity = FeasibilityPlanning()
                fesibiliity.RadioAntenna = ArrayList()
                fesibiliity.RadioAntenna!!.add(it)
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
        binding = FpAntinaBotomSheetDialogBinding.inflate(inflater)
        return binding.root
    }

}