package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BackhaulLinkListItemDialougeBinding
import com.smarthub.baseapplication.databinding.BackhaulPlanningBootomSheetDialogBinding
import com.smarthub.baseapplication.databinding.PowerMsbPlanningBootomShhetDialogBinding
import com.smarthub.baseapplication.databinding.SiteDetailBootomSheetDialogBinding
import com.smarthub.baseapplication.databinding.SoftAcutionFeasibilityBottomSheetDialogBinding

class SoftAcutionsFeasibilityBottomSheet(contentLayoutId: Int) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : SoftAcutionFeasibilityBottomSheetDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SoftAcutionFeasibilityBottomSheetDialogBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
    }
    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SoftAcutionFeasibilityBottomSheetDialogBinding.inflate(inflater)
        return binding.root
    }
}