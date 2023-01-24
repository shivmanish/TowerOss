package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.EditEquipementDialougeBinding
import com.smarthub.baseapplication.databinding.RadioAntenaDialougeBinding
import com.smarthub.baseapplication.model.serviceRequest.*
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class RadioAnteenaBottomSheet(
    contentLayoutId: Int,
    var radioantena: RadioAntenna,
    var serviceRequestAllData: ServiceRequestAllDataItem,
    var viewmodel: HomeViewModel,
    var Id: String,
) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding: RadioAntenaDialougeBinding
    var basicinfoModel: BasicinfoModel? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basicinfoModel = BasicinfoModel()
        binding = RadioAntenaDialougeBinding.bind(view)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight() * 0.75).toInt()
        binding.canecl.setOnClickListener {
            dismiss()
        }

        radioantena.let {
            binding.technology.setText(it.Technology)

        }

        binding.update.setOnClickListener {
            radioantena.let {
                it.Technology = binding.technology.text.toString()

            }
            val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
            mServiceRequestAllDataItem.id = serviceRequestAllData.id
            mServiceRequestAllDataItem.ServiceRequest = ArrayList()

            val serviceRequest = ServiceRequest()
            serviceRequest.RadioAntennas = ArrayList()
            (serviceRequest.RadioAntennas as ArrayList<RadioAntenna>)?.add(radioantena)
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
        binding = RadioAntenaDialougeBinding.inflate(inflater)
        return binding.root
    }
}