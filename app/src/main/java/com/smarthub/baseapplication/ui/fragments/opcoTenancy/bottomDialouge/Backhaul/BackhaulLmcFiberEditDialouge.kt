package com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.Backhaul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BackhaulInstallationTeamListItemDialougeBinding
import com.smarthub.baseapplication.databinding.BackhaulLmcListItemDialougeBinding

class BackhaulLmcFiberEditDialouge (contentLayoutId: Int): BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : BackhaulLmcListItemDialougeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BackhaulLmcListItemDialougeBinding.inflate(inflater)
        return binding.root
    }
}