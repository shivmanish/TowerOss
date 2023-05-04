package com.smarthub.baseapplication.ui.dialog.task

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.DashboardActivity
import com.smarthub.baseapplication.databinding.CloseTaskLayoutBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.viewmodels.HomeViewModel


class CloseTaskBottomSheet(var taskId:String) : BaseBottomSheetDialogFragment()  {

    lateinit var homeViewModel: HomeViewModel
    lateinit var binding: CloseTaskLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = CloseTaskLayoutBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
       binding.update.setOnClickListener {
           showLoader()
           homeViewModel.closeTask(taskId,binding.Remark.text.toString())
        }

        if (homeViewModel.closeTaskModel?.hasActiveObservers() == true)
            homeViewModel.closeTaskModel?.removeObservers(viewLifecycleOwner)
        homeViewModel.closeTaskModel?.observe(viewLifecycleOwner) {
            hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                if (it.data.Error.isEmpty() || it.data.Message=="Data updated"){
                    dismiss()
                    Toast.makeText(requireContext(),it.data.Message,Toast.LENGTH_SHORT).show()
                    val intent = Intent (requireContext(), DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }else{
                    Toast.makeText(requireContext(),it.data.Error,Toast.LENGTH_SHORT).show()
                }
            }else Toast.makeText(requireContext(),"Task not closed",Toast.LENGTH_SHORT).show()
        }

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CloseTaskLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }

    interface CloseTaskBottomSheetListener{
        fun onTaskClosed(remark :String)
    }

}