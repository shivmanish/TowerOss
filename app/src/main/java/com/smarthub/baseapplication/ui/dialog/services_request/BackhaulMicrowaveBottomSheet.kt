package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BackhaulMicrowaveBottomSheetDialogBinding
import com.smarthub.baseapplication.databinding.RfFeasibilityBottomSheetDialogBinding
import com.smarthub.baseapplication.databinding.RfSectorBottomSheetDialogBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.serviceRequest.AssignACQTeam
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.BackhaulFeasibility
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.OpcoTSSR
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.RFFeasibility
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class BackhaulMicrowaveBottomSheet(
    contentLayoutId: Int,
    var Id: String?,
    var viewmodel: HomeViewModel,
    var BackhaulFeasibility: BackhaulFeasibility?,
    var serviceRequestAllData: ServiceRequestAllDataItem?
) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var basicinfoModel: BasicinfoModel
    lateinit var binding : BackhaulMicrowaveBottomSheetDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basicinfoModel = BasicinfoModel()
        binding = BackhaulMicrowaveBottomSheetDialogBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        BackhaulFeasibility!!.MicrowaveOrUBR!!.get(0).let {
            if (BackhaulFeasibility != null) {
//                AppPreferences.getInstance().setDropDown(
//                    binding.spinEquipmentName,
//                    DropDowns.Rftechnology.name,
//                    it.
//                )
//                binding.trxcounter.setText(it.TRXCount)
                binding.anteenaheight.setText(it.AntennaHeight)
            }
        }
        binding.spinEquipmentName.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
//                RfFeasibility!!.SectorsOrCellDetails!!.get(0).Technology = AppPreferences.getInstance().getDropDownList(DropDowns.Rftechnology.name).get(position).id
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding.include.update.setOnClickListener {
            BackhaulFeasibility!!.MicrowaveOrUBR!!.get(0).let {
//                it.TRXCount = binding.trxcounter.text.toString()
                it.AntennaHeight = binding.anteenaheight.text.toString()
            }

            val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
            mServiceRequestAllDataItem.id = serviceRequestAllData!!.id
            mServiceRequestAllDataItem.OpcoTSSR = ArrayList()

            val opcoTSSR = OpcoTSSR()
            opcoTSSR.BackHaulFeasibility = ArrayList()
            opcoTSSR.BackHaulFeasibility?.add(BackhaulFeasibility!!)
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
        binding = BackhaulMicrowaveBottomSheetDialogBinding.inflate(inflater)
        return binding.root
    }

}