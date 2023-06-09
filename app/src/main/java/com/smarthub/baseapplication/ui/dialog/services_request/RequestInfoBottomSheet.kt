package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RequestInfoBottomSheetDialogBinding
import com.smarthub.baseapplication.model.serviceRequest.*
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class RequestInfoBottomSheet(
    contentLayoutId: Int,
    var requesterInfo:RequesterInfo,
    var serviceRequestAllData: ServiceRequestAllDataItem?,
    var viewmodel: HomeViewModel,
    var Id: String
) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : RequestInfoBottomSheetDialogBinding
    var basicinfoModel: BasicinfoModel? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RequestInfoBottomSheetDialogBinding.bind(view)
        basicinfoModel = BasicinfoModel()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
         binding.editRequesterExcutiveName.setText(requesterInfo?.RequesterExecutiveName)
         binding.editEmailId.setText(requesterInfo?.EmailID)
         binding.editPhoneNumber.setText(requesterInfo?.PhoneNumber)
         binding.editRemarks.setText(requesterInfo?.Remarks)
        binding.includeid.update.setOnClickListener {
            requesterInfo.let {
                it.EmailID = binding.editEmailId.text.toString()
                it.RequesterExecutiveName = binding.editRequesterExcutiveName.text.toString()
                it.PhoneNumber = binding.editPhoneNumber.text.toString()
            }
            val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
            mServiceRequestAllDataItem.id = serviceRequestAllData!!.id
            mServiceRequestAllDataItem.ServiceRequest = ArrayList()

            val serviceRequest = ServiceRequest()
            serviceRequest.RequesterInfo = ArrayList()
            (serviceRequest.RequesterInfo as ArrayList<RequesterInfo>)?.add(requesterInfo)
            serviceRequest.id = serviceRequestAllData!!.ServiceRequest!![0].id
            mServiceRequestAllDataItem.ServiceRequest?.add(serviceRequest)

            val serviceRequestList = ServiceRequestAllData()
            serviceRequestList.add(mServiceRequestAllDataItem)

            basicinfoModel?.ServiceRequestMain = serviceRequestList
            basicinfoModel?.id = Id
            viewmodel.updateBasicInfo(basicinfoModel!!)

        }


    }

    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = RequestInfoBottomSheetDialogBinding.inflate(inflater)
        return binding.root
    }

}