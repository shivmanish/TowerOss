package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BackhaulLinkListItemDialougeBinding
import com.smarthub.baseapplication.databinding.GenerateSiteProposalDialogBinding
import com.smarthub.baseapplication.databinding.PowerMsbBottomSheetDialogBinding
import com.smarthub.baseapplication.databinding.SpSubmissionDialogBinding
import com.smarthub.baseapplication.databinding.TssrExecutiveBottomSheetDialogBinding

class SpSubmisstioniteProposalBottomSheet(contentLayoutId: Int) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : SpSubmissionDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SpSubmissionDialogBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
    }
    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SpSubmissionDialogBinding.inflate(inflater)
        return binding.root
    }
}