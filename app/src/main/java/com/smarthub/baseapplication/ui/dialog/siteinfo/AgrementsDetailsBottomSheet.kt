package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AgreementDetailsBotomSheetBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.serviceRequest.acquisitionSurvey.AcquisitionSurveyModel
import com.smarthub.baseapplication.model.serviceRequest.softAqusition.AgreementTerm
import com.smarthub.baseapplication.model.serviceRequest.softAqusition.SoftAcquisition
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AgrementsDetailsBottomSheet(
    contentLayoutId: Int,
    var agrement: AgreementTerm,
    var id: String?,
    var viewmodel: HomeViewModel,
    var serviceRequestAllData: AcquisitionSurveyModel
) : BottomSheetDialogFragment(contentLayoutId) {
    var basicinfoModel: BasicinfoModel? = null
    lateinit var binding : AgreementDetailsBotomSheetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basicinfoModel = BasicinfoModel()
        binding = AgreementDetailsBotomSheetBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        try{
        agrement!!.let {
            binding.agrementType.setText(it.AgreementPeriod)
//            binding.registrationNumber.setText("")
//            binding.registrationDate.setText("")
//            binding.bookingCostCentre.setText("")
            binding.agreemenPeriod.setText(it.AgreementPeriod)
            binding.lockPeriod.setText(it.LockInPeriod)
            binding.agreemenEffectiveDate.setText("")
            binding.agreementExpiryDate.setText("")
            binding.rentStartDate.setText("")
            binding.initialAnnualRentAmount.setText("")

//            binding.rentPaymentFrequency.setText("")
            binding.periodicRentPaybleAmount.setText(it.PeriodicRentPayableAmount)
            binding.rentEscalation.setText("")
            binding.rentEscalationPeriod.setText(it.RentEscalationPeriod)
            binding.lastEscalationDate.setText("")
            binding.lastRevisedRentAmount.setText("")
//            binding.eBInclusiveRental.setText("")
//            binding.eBBillLimit.setText("")
//            binding.eBBillingBasis.setText(it.EBBillingBasis)
//            binding.eBperunitRate.setText(it.EBPerUnitRate)
//            binding.propertyOwnership.setText(it.PropertyOwnership)
//            binding.propertyAcquired.setText("")

            binding.onetimeAmount.setText(it.OnetimeAmount)
            binding.securityDepositeAmount.setText(it.SecurityDepositAmount)
            binding.rooftopAcquiredArea.setText("")
            binding.groundAcquiredArea.setText("")
        }

        }catch (e:Exception){
            AppLogger.log("Error in Feasibility planing Adapter ${e.localizedMessage}")
        }

        binding.include.update.setOnClickListener {
            agrement?.let {


                val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
                mServiceRequestAllDataItem.id = 448
                mServiceRequestAllDataItem.SoftAcquisition = ArrayList()

                val softAcquisition = SoftAcquisition()
                softAcquisition.AgreementTerms = ArrayList()
                softAcquisition.AgreementTerms!!.add(it)
                softAcquisition.id = serviceRequestAllData?.SAcqASAcquitionSurvey!![0].id
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
        binding = AgreementDetailsBotomSheetBinding.inflate(inflater)
        return binding.root
    }

}