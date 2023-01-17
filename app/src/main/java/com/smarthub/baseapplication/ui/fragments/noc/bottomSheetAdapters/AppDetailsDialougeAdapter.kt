package com.smarthub.baseapplication.ui.fragments.noc.bottomSheetAdapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocApplicationDetailsDialougeLayoutBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.ApplicationInitial
import com.smarthub.baseapplication.utils.DropDowns

class AppDetailsDialougeAdapter(contentLayoutId: Int, var applicationDetailsData: ApplicationInitial?): BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : NocApplicationDetailsDialougeLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }

        AppPreferences.getInstance().setDropDown(
            binding.spincategory,
            DropDowns.ApplicationInitialApplicationStatus.name,
            applicationDetailsData?.ApplicationInitialApplicationStatus
        )
     /*   AppPreferences.getInstance().setDropDown(
            binding.applycationType,
            DropDowns.ApplicationInitialApplicationType.name,
            applicationDetailsData?.ApplicationInitialApplicationType
        )*/

        AppPreferences.getInstance().setDropDown(
            binding.applicationStatus,
            DropDowns.ApplicationInitialApplicationStatus.name,
            applicationDetailsData?.ApplicationInitialApplicationStatus
        )




        binding.applicationName.setText(applicationDetailsData?.ApplicationName)
        binding.aplicationNumber.setText(applicationDetailsData?.ApplicationNumber)
        binding.applicationDate.text = applicationDetailsData?.ApplicationDate
        binding.issueDate.text = applicationDetailsData?.IssueDate
        binding.documentNo.setText(applicationDetailsData?.DocumentNo)
        binding.expireDate.text = applicationDetailsData?.ExpiryDate
        binding.vandorName.setText(applicationDetailsData?.VendorName)
        binding.venderExcutivity.setText(applicationDetailsData?.VendorExecutive)
        binding.venderPhonoNumber.setText(applicationDetailsData?.VendorPhoneNo)
        binding.update.setOnClickListener {
            applicationDetailsData.let {

                it?.ApplicationName = binding.applicationName.text.toString()
                  it?.ApplicationNumber = binding.aplicationNumber.text.toString()

                  it?.ApplicationDate = binding.applicationDate.text.toString()
                  it?.IssueDate = binding.issueDate.text.toString()
                  it?.DocumentNo = binding.documentNo.text.toString()
                  it?.ExpiryDate = binding.expireDate.text.toString()
                  it?.VendorName = binding.vandorName.text.toString()
                  it?.VendorExecutive = binding.venderExcutivity.text.toString()
                  it?.VendorPhoneNo = binding.venderExcutivity.text.toString()


            }


       }
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NocApplicationDetailsDialougeLayoutBinding.inflate(inflater)
        return binding.root
    }
}