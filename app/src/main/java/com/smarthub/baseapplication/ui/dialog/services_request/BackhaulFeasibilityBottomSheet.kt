package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.BackhaulFeasibility
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.OpcoTSSR
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.RFFeasibility
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class BackhaulFeasibilityBottomSheet(
    contentLayoutId: Int,
    var Id: String?,
    var viewmodel: HomeViewModel,
    var backhaulFeasibility: BackhaulFeasibility?,
    var serviceRequestAllData: ServiceRequestAllDataItem?
) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : BackhaulFeasibilityBottomSheetDialogBinding
    lateinit var basicinfoModel: BasicinfoModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = BackhaulFeasibilityBottomSheetDialogBinding.bind(view)
        basicinfoModel = BasicinfoModel()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }

        if (backhaulFeasibility != null) {
//            AppPreferences.getInstance().setDropDown(
//                binding.backhaulnodetype,
//                DropDowns.Off.name,
//                backhaulFeasibility!!.BackHaulNodeType
//            )
            binding.editRemarks.setText(backhaulFeasibility!!.BackHaulFeasibilityRemark)
            binding.ofsetpolerequired.setText(backhaulFeasibility!!.OffSetPoleRequired)
//            binding.spinRRUCount.setText(backhaulFeasibility!!.RRUCount)
//            binding.spinOfSetPoleRead.setText(backhaulFeasibility!!.OffSetPoleReqd)
        }
        binding.backhaulnodetype.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
//                backhaulFeasibility!!.Technology = AppPreferences.getInstance().getDropDownList(DropDowns.Rftechnology.name).get(position).id
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding.include.update.setOnClickListener {

            backhaulFeasibility!!.let {

                it.BackHaulFeasibilityRemark = binding.editRemarks.text.toString()
                it.OffSetPoleRequired = binding.ofsetpolerequired.text.toString()
//                it.RRUCount = binding.spinRRUCount.text.toString()
//                it.OffSetPoleReqd = binding.spinOfSetPoleRead.text.toString()
            }

            val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
            mServiceRequestAllDataItem.id = serviceRequestAllData!!.id
            mServiceRequestAllDataItem.OpcoTSSR = ArrayList()

            val opcoTSSR = OpcoTSSR()
            opcoTSSR.BackHaulFeasibility = ArrayList()
            opcoTSSR.BackHaulFeasibility?.add(backhaulFeasibility!!)
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BackhaulFeasibilityBottomSheetDialogBinding.inflate(inflater)
        return binding.root
    }
}