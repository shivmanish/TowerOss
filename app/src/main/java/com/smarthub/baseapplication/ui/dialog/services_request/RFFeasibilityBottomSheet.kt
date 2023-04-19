package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfFeasibilityBottomSheetDialogBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.serviceRequest.AssignACQTeam
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.OpcoTSSR
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.RFFeasibility
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.widgets.CustomSpinner

class RFFeasibilityBottomSheet(
    contentLayoutId: Int,
    var Id: String?,
    var viewmodel: HomeViewModel,
    var RfFeasibility: RFFeasibility?,
    var serviceRequestAllData: ServiceRequestAllDataItem?
) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var basicinfoModel: BasicinfoModel
    lateinit var binding : RfFeasibilityBottomSheetDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basicinfoModel = BasicinfoModel()
        binding = RfFeasibilityBottomSheetDialogBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        if (RfFeasibility != null) {
            AppPreferences.getInstance().setDropDown(
                binding.spinEquipmentName,
                DropDowns.Rftechnology.name,
                RfFeasibility!!.Technology
            )
            binding.editRemarks.setText(RfFeasibility!!.RFFeasibiltyRemarks)
            binding.spinSectorCount.setText(RfFeasibility!!.SectorCount)
            binding.spinRRUCount.setText(RfFeasibility!!.RRUCount)
            binding.spinOfSetPoleRead.setText(RfFeasibility!!.OffSetPoleReqd)
        }
        binding.spinEquipmentName.onItemSelectedListener = object :OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                try {
                    RfFeasibility!!.Technology = AppPreferences.getInstance().getDropDownList(DropDowns.Rftechnology.name).get(position).id
                }catch (e:java.lang.Exception){
                    AppLogger.log("e:"+e.localizedMessage)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding.include.update.setOnClickListener {

            RfFeasibility!!.let {

                it.RFFeasibiltyRemarks = binding.editRemarks.text.toString()
                it.SectorCount = binding.spinSectorCount.text.toString()
                it.RRUCount = binding.spinRRUCount.text.toString()
                it.OffSetPoleReqd = binding.spinOfSetPoleRead.text.toString()
            }

            val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
            mServiceRequestAllDataItem.id = serviceRequestAllData!!.id
            mServiceRequestAllDataItem.OpcoTSSR = ArrayList()

            val opcoTSSR = OpcoTSSR()
            opcoTSSR.RFFeasibility = ArrayList()
            opcoTSSR.RFFeasibility?.add(RfFeasibility!!)
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
        binding = RfFeasibilityBottomSheetDialogBinding.inflate(inflater)
        return binding.root
    }

}