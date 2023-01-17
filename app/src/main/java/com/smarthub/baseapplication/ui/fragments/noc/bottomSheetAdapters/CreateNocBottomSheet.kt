package com.smarthub.baseapplication.ui.fragments.noc.bottomSheetAdapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CreateNocSiteInfoDialogeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.ApplicationInitial
import com.smarthub.baseapplication.utils.DropDowns

class CreateNocBottomSheet(contentLayoutId: Int) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding: CreateNocSiteInfoDialogeBinding
    lateinit var applicationInitial: ApplicationInitial
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancle.setOnClickListener {
            dismiss()
        }

/*        AppPreferences.getInstance().setDropDown(
            binding.spinCategory,
            DropDowns.ApplicationInitialCategory.name,
            applicationInitial.ApplicationInitialCategory
        )
        AppPreferences.getInstance().setDropDown(
            binding.spinApplicationType,
            DropDowns.ApplicationInitialApplicationType.name,
            applicationInitial.ApplicationInitialApplicationType
        )

        AppPreferences.getInstance().setDropDown(
            binding.spinApplicationsStatus,
            DropDowns.ApplicationInitialApplicationStatus.name,
            applicationInitial.ApplicationInitialApplicationStatus
        )
        AppPreferences.getInstance().setDropDown(
            binding.spinApplicationsStatus,
            DropDowns.ApplicationInitialApplicationStatus.name,
            applicationInitial.ApplicationInitialApplicationStatus
        )
        binding.ApplicationName.setText(applicationInitial.ApplicationName)
        binding.ApplicationNumber.setText(applicationInitial.ApplicationNumber)
        binding.ApplicationDate.text = applicationInitial.ApplicationDate
        binding.IssueDate.text = applicationInitial.IssueDate
        binding.DocumentNo.setText(applicationInitial.DocumentNo)
        binding.ExpiryDate.text = applicationInitial.ExpiryDate
        binding.VendorName.setText(applicationInitial.VendorName)
        binding.VendorExecutive.setText(applicationInitial.VendorExecutive)
        binding.VendorPhoneNo.setText(applicationInitial.VendorPhoneNo)
        binding.update.setOnClickListener {
            applicationInitial.let {

                it.ApplicationName = binding.ApplicationName.text.toString()
                it.ApplicationNumber = binding.ApplicationNumber.text.toString()

                it.ApplicationDate = binding.ApplicationDate.text.toString()
                it.IssueDate = binding.IssueDate.text.toString()
                it.DocumentNo = binding.DocumentNo.text.toString()
                it.ExpiryDate = binding.ExpiryDate.text.toString()
                it.VendorName = binding.VendorName.text.toString()
                it.VendorExecutive = binding.VendorExecutive.text.toString()
                it.VendorPhoneNo = binding.VendorPhoneNo.text.toString()


            }


        }*/
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreateNocSiteInfoDialogeBinding.inflate(inflater)
        return binding.root
    }
}