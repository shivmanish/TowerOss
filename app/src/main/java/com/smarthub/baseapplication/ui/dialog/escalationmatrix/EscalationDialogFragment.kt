package com.smarthub.baseapplication.ui.dialog.escalationmatrix

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.DetailsBottomSheetViewBinding
import com.smarthub.baseapplication.databinding.EscalationsDialogBinding
import com.smarthub.baseapplication.utils.AppLogger
import java.util.*



    class EscalationDialogFragment(contentLayoutId: Int) : BottomSheetDialogFragment(contentLayoutId) {

        lateinit var binding : EscalationsDialogBinding

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding = EscalationsDialogBinding.bind(view)

        }

        override fun getTheme() = R.style.NewDialogTask

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            binding = EscalationsDialogBinding.inflate(inflater)
            return binding.root
        }

    }

