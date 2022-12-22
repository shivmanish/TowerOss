package com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.Backhaul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BackhaulAntenaListItemDialougeBinding
import com.smarthub.baseapplication.databinding.BackhaulInstallationTeamListItemDialougeBinding

class BackhaulInstallationTeamEditDialouge (contentLayoutId: Int): BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : BackhaulInstallationTeamListItemDialougeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BackhaulInstallationTeamListItemDialougeBinding.inflate(inflater)
        return binding.root
    }
}