package com.smarthub.baseapplication.ui.alert.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.databinding.SubmitAllertBottomSheetBinding
import com.smarthub.baseapplication.ui.alert.model.response.Data
import com.smarthub.baseapplication.ui.dialog.BaseBottomSheetDialogFragment

class SubmitAletrBottomSheet(contentLayoutId: Int, var data: Data) :
    BaseBottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding: SubmitAllertBottomSheetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SubmitAllertBottomSheetBinding.inflate(inflater)
        binding.troublemaker.text = data.SATroubleMaker
        binding.issuetype.text = data.SAIssueType
        binding.issuetypetwo.text = data.SAIssueType
        binding.severity.text = data.SASeverity
        binding.severitytwo.text = data.SASeverity
        binding.address.text = data.SADetails
        binding.name.text = data.sitename
        binding.senderName.text = data.sitename
        binding.sendDate.text = data.HeppenDateTime
        binding.date.text = data.HeppenDateTime
        binding.sendDate.text = "D1"
        var comma_string: String = ""
        for (dat in data.Sendalertsupportdata) {
            if (comma_string.equals("")) {
                comma_string = dat.SuRecepientusername
            } else {
                comma_string = comma_string + "" + dat.SuRecepientusername
            }
        }

        binding.recipentaName.text = comma_string

        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }
}