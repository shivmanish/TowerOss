package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AgreementDetailsBotomSheetBinding
import com.smarthub.baseapplication.databinding.PropertyDetailsListItemBinding
import com.smarthub.baseapplication.databinding.PropertyOwnerBotomSheetBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.serviceRequest.softAqusition.PropertyOwnerAndPaymentDetail
import com.smarthub.baseapplication.model.serviceRequest.softAqusition.SoftAcquisition
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PropertyOwnerDetailsBottomSheet(
    contentLayoutId: Int,
    var agrement: ArrayList<PropertyOwnerAndPaymentDetail>,
    var id: String?,
    var viewmodel: HomeViewModel,
    var serviceRequestAllData: ServiceRequestAllDataItem
) : BottomSheetDialogFragment(contentLayoutId) {
    var basicinfoModel: BasicinfoModel? = null
    lateinit var binding : PropertyOwnerBotomSheetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basicinfoModel = BasicinfoModel()
        binding = PropertyOwnerBotomSheetBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        try{
        agrement!!.get(0).let {
          binding.Seq.setText(it.Seq)
            binding.Status.setText(it.PayeeStatus)
            binding.pannno.setText(it.PanNumber)
            binding.Share.setText(it.Share)
        }

        }catch (e:Exception){
            AppLogger.log("Error in Feasibility planing Adapter ${e.localizedMessage}")
        }

        binding.include.update.setOnClickListener {
            agrement?.let {


                val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
                mServiceRequestAllDataItem.id = serviceRequestAllData?.id
                mServiceRequestAllDataItem.SoftAcquisition = ArrayList()

                val softAcquisition = SoftAcquisition()
                softAcquisition.PropertyOwnerAndPaymentDetails = agrement
                softAcquisition.id = serviceRequestAllData?.SoftAcquisition!![0].id
                mServiceRequestAllDataItem.SoftAcquisition?.add(softAcquisition)

                val serviceRequestList = ServiceRequestAllData()
                serviceRequestList.add(mServiceRequestAllDataItem)

                basicinfoModel?.ServiceRequestMain = serviceRequestList
                basicinfoModel?.id = id!!
                viewmodel?.updateBasicInfo(basicinfoModel!!)
            }
        }
    }

    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PropertyOwnerBotomSheetBinding.inflate(inflater)
        return binding.root
    }

}