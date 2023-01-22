package com.smarthub.baseapplication.ui.fragments.task.editdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AssignTaskDialougeBinding
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel

class AssignTaskDialouge(contentLayoutId: Int,var task : MyTeamTask?) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: AssignTaskDialougeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AssignTaskDialougeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AssignTaskDialougeBinding.bind(view)

        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        binding.closeTaskBtn.setOnClickListener {
            dismiss()
        }
        binding.taskName.text=task?.Taskname
        binding.WorkOrderNumber.text=task?.workorderid
    }


    override fun getTheme() = R.style.NewDialogTask



//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = BottomSheetDialog(requireContext(), theme)
//        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
//        dialog.behavior.skipCollapsed = false
//        return dialog
//    }

}