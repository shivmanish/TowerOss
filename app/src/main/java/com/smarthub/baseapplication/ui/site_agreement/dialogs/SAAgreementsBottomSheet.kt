package com.smarthub.baseapplication.ui.site_agreement.dialogs

import android.app.Dialog
import android.content.DialogInterface
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
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteacquisitionAgreement
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SAAgreementsBottomSheet(
    contentLayoutId: Int,
    var siteAgreementsData: SiteacquisitionAgreement?, var viewModel: HomeViewModel

) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding: SaAgreementDialogBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.constraint.layoutParams.height = (Utils.getScreenHeight() * 0.75).toInt()
        binding.canecl.setOnClickListener {
            dismiss()
        }

        if (viewModel.siteAgreementModel?.hasActiveObservers() == true) {
            viewModel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        viewModel.siteAgreementModel?.observe(viewLifecycleOwner) {
            dialog!!.dismiss()
            dialog!!.cancel()
        }
        binding.textSave.setOnClickListener {
            siteAgreementsData.let {

                it?.AgreementType = binding.editTermLease.text.toString()
                it?.RegistrationDate = binding.textRegistrationDate.text.toString()

                it?.AgreementPeriod = binding.editAgreemenPeriod.text.toString()
                it?.LockInPeriod = binding.editLockPeriod.text.toString()
                it?.AgreementEffectiveDate = binding.editAgreemenDate.text.toString()
                it?.AgreementExpiryDate = binding.editAgreementExpiryDate.text.toString()
                it?.RentStartDate = binding.editRentStartDate.text.toString()
                it?.InitialAnnualRentAmount = binding.editInitialAnnualRentAmount.text.toString()
                it?.PeriodicRentPayableAmount = binding.editPeriodicRent.text.toString()
                it?.RentEscalation = binding.editRentEscalation.text.toString()
                it?.RentEscalationPeriod = binding.editRentEscalationPeriod.text.toString()
                it?.LastescalationDate = binding.editLastEscalationDate.text.toString()
                it?.LastRevisedRentAmount = binding.editLastRevisedRentAmount.text.toString()
                it?.EBBillingBasis = binding.textEBBillingBasis.text.toString()
                it?.EBPerUnitRate = binding.editEBperunitRate.text.toString()
                it?.OnetimeAmount = binding.editOnetimeAmount.text.toString()
                it?.SecurityDepositAmount = binding.editAmount.text.toString()
                it?.GroundUsableArea = binding.editArea.text.toString()
                it?.RooftopacquiredArea = binding.editArea.text.toString()


            }

            siteAgreementsData?.id = siteAgreementsData!!.id
            viewModel.updateSiteInfo(siteAgreementsData!!)
        }


        hideProgressLayout()
        if (viewModel.siteAgreementModel?.hasActiveObservers() == true)
            viewModel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        viewModel.siteAgreementModel?.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it.status == Resource.Status.LOADING) {
                    showProgressLayout()
                } else {
                    hideProgressLayout()
                }
                if (it.status == Resource.Status.SUCCESS && it.data?.SAcqSiteAcquisition?.isNotEmpty() == true) {
                    AppLogger.log("Successfully updated all fields")
                    dismiss()
                    //    viewModel.fetchSiteInfoData(id)
                } else {
                    AppLogger.log("UnExpected Error found")
                }
            } else {
                AppLogger.log("Something went wrong")
            }
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

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (viewModel.siteAgreementModel?.hasActiveObservers() == true)
            viewModel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
    }

    fun showProgressLayout() {
        if (binding.progressLayout.visibility != View.VISIBLE)
            binding.progressLayout.visibility = View.VISIBLE
    }

    fun hideProgressLayout() {
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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