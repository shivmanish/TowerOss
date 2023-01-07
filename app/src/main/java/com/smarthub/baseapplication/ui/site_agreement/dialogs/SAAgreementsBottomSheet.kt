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
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteAgreementsData
import com.smarthub.baseapplication.utils.Utils


class SAAgreementsBottomSheet(
    contentLayoutId: Int,
    siteacquisitionAgreements: List<SiteAgreementsData>?
): BottomSheetDialogFragment(contentLayoutId)  {
    lateinit var binding : SaAgreementDialogBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.constraint.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()
        binding.canecl.setOnClickListener {
            dismiss()
        }

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