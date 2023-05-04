package com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqPayeeAccViewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqPayeeAccountDetail
import com.smarthub.baseapplication.utils.DropDowns

class PayeeAccDetailsViewDialouge (contentLayoutId: Int, var data: SAcqPayeeAccountDetail) : BottomSheetDialogFragment(contentLayoutId){

    lateinit var binding: AcqPayeeAccViewDialougeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
        if (data.PayeeStatus!=null && data.PayeeStatus!! >0)
            AppPreferences.getInstance().setDropDown(binding.PayeeStatus, DropDowns.PayeeStatus.name,data.PayeeStatus.toString())
        binding.Share.text=data.Share
        binding.PayeeName.text=data.PayeeName
        binding.AccountNo.text=data.AccountNumber
        binding.PanNumber.text=data.PAN
        binding.BranchName.text=data.BankBranch
        binding.PayeeBank.text=data.PayeeBank
        binding.IFSCCode.text=data.BankIFSCode
        binding.GSTNumber.text=data.GSTIN

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AcqPayeeAccViewDialougeBinding.inflate(inflater)
        return binding.root
    }



}