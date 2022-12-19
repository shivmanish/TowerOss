package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BackhaulLinkListItemDialougeBinding
import com.smarthub.baseapplication.utils.Utils

class BachhualLinkBottomSheet(contentLayoutId: Int) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : BackhaulLinkListItemDialougeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = BackhaulLinkListItemDialougeBinding.bind(view)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()

        binding.canecl.setOnClickListener {
            dismiss()
        }
    }
    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BackhaulLinkListItemDialougeBinding.inflate(inflater)
        return binding.root
    }
}