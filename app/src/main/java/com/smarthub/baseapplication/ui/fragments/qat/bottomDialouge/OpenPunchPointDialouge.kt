package com.smarthub.baseapplication.ui.fragments.qat.bottomDialouge

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.QatPunchPointDialougeBinding
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.Utils

class OpenPunchPointDialouge (): BaseBottomSheetDialogFragment(),OpenPunchPointDialougeAdapter.PunchPointItemListner {
    lateinit var binding : QatPunchPointDialougeBinding
    lateinit var adapter: OpenPunchPointDialougeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = QatPunchPointDialougeBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()
        binding.listItem.layoutManager=LinearLayoutManager(requireContext())
        adapter= OpenPunchPointDialougeAdapter(this,requireContext())
        binding.listItem.adapter=adapter
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        binding.titleText.text="Open Punch Points"

    }

    override fun getTheme() = R.style.NewDialogTask


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }


    override fun attachmentItemClicked() {
        Toast.makeText(context,"attachment Clicked",Toast.LENGTH_SHORT).show()
    }
}