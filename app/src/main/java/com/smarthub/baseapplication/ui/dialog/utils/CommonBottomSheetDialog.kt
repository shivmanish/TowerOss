package com.smarthub.baseapplication.ui.dialog.utils

import android.content.Intent
import android.os.Bundle
import android.view.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AddMoreBotomSheetDailogBinding
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.ApplicationInitial
import com.smarthub.baseapplication.ui.alert.AlertActivity
import com.smarthub.baseapplication.ui.fragments.logs.LogActivity


class CommonBottomSheetDialog(contentLayoutId: Int) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: AddMoreBotomSheetDailogBinding

    lateinit var applicationInitial:ApplicationInitial
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddMoreBotomSheetDailogBinding.bind(view)

        binding.canecl.setOnClickListener {
            dismiss()
        }

        binding.cardLogs.setOnClickListener {
            val intent = Intent(requireContext(), LogActivity::class.java)
            startActivity(intent)
            dismiss()
        }

        binding.sendAlert.setOnClickListener {
            val intent = Intent(requireContext(),AlertActivity::class.java)
            startActivity(intent)
            dismiss()
        }

        binding.cardLogs.setOnClickListener {
            val intent = Intent(requireContext(),LogActivity::class.java)
            startActivity(intent)
            dismiss()
        }
    }
    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AddMoreBotomSheetDailogBinding.inflate(inflater)
        return binding.root
    }

}