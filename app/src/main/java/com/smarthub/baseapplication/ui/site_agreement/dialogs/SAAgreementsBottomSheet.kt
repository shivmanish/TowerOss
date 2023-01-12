package com.smarthub.baseapplication.ui.site_agreement.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SaAgreementDialogBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteacquisitionAgreement
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils


class SAAgreementsBottomSheet(
    contentLayoutId: Int,
    var siteAgreementsData: SiteacquisitionAgreement?,

    ): BottomSheetDialogFragment(contentLayoutId)  {
    lateinit var binding : SaAgreementDialogBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.constraint.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()
        binding.canecl.setOnClickListener {
            dismiss()
        }
     binding.textSave.setOnClickListener {
            dismiss()
        }

        AppPreferences.getInstance().setDropDown(
            binding.textRegistrationNumber,
            DropDowns.RegistrationNumber.name, siteAgreementsData?.RegistrationNumber
        )
    
        AppPreferences.getInstance().setDropDown(
            binding.textBookingCostCentre,
            DropDowns.BookingCostCentre.name, siteAgreementsData?.BookingCostCentre
        )
        AppPreferences.getInstance().setDropDown(
            binding.textRentPaymentFrequency,
            DropDowns.RentPaymentFrequency.name, siteAgreementsData?.RentPaymentFrequency
        )
        AppPreferences.getInstance().setDropDown(
            binding.editEBInclusiveRental,
            DropDowns.EBInclusiveinRental.name, siteAgreementsData?.EBInclusiveinRental
        )
        AppPreferences.getInstance().setDropDown(
            binding.editEBBillLimit,
            DropDowns.EBBillLimit.name, siteAgreementsData?.EBBillLimit
        )

        AppPreferences.getInstance().setDropDown(
            binding.editPropertyOwnership,
            DropDowns.PropertyOwnership.name, siteAgreementsData?.PropertyOwnership
        )
        AppPreferences.getInstance().setDropDown(
            binding.editPropertyAcquired,
            DropDowns.PropertyAcquired.name, siteAgreementsData?.PropertyAcquired
        )
        binding.editTermLease.setText(siteAgreementsData?.AgreementType)
        binding.textRegistrationDate.setText(siteAgreementsData?.RegistrationDate)
        binding.editAgreemenPeriod.setText(siteAgreementsData?.AgreementPeriod)
        binding.editLockPeriod.setText(siteAgreementsData?.LockInPeriod)
        binding.editAgreemenDate.setText(siteAgreementsData?.AgreementEffectiveDate)
        binding.editAgreementExpiryDate.setText(siteAgreementsData?.AgreementExpiryDate)
        binding.editRentStartDate.setText(siteAgreementsData?.RentStartDate)
        binding.editInitialAnnualRentAmount.setText(siteAgreementsData?.InitialAnnualRentAmount)
        binding.editPeriodicRent.setText(siteAgreementsData?.PeriodicRentPayableAmount)
        binding.editRentEscalation.setText(siteAgreementsData?.RentEscalation)
        binding.editRentEscalationPeriod.setText(siteAgreementsData?.RentEscalationPeriod)
        binding.editLastEscalationDate.setText(siteAgreementsData?.LastescalationDate)
        binding.editLastRevisedRentAmount.setText(siteAgreementsData?.LastRevisedRentAmount)
        binding.editLastRevisedRentAmount.setText(siteAgreementsData?.LastRevisedRentAmount)
        binding.textEBBillingBasis.setText(siteAgreementsData?.EBBillingBasis)
        binding.editEBperunitRate.setText(siteAgreementsData?.EBPerUnitRate)
        binding.editOnetimeAmount.setText(siteAgreementsData?.OnetimeAmount)
        binding.editAmount.setText(siteAgreementsData?.SecurityDepositAmount)
        binding.editArea.setText(siteAgreementsData?.GroundUsableArea)
        binding.editGroundArea.setText(siteAgreementsData?.GroundUsableArea)
        binding.editArea.setText(siteAgreementsData?.RooftopacquiredArea)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SaAgreementDialogBinding.inflate(inflater)



        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = true
        return dialog
    }



    override fun getTheme() = R.style.NewDialogTask
}