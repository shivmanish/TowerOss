package com.smarthub.baseapplication.ui.fragments.project

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ProjectBottomSheetLayoutBinding
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TemplateTaskBottomSheet(contentLayoutId: Int,var viewModel: HomeViewModel,var template: String) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: ProjectBottomSheetLayoutBinding
    lateinit var adapter : TaskAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProjectBottomSheetLayoutBinding.bind(view)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()
        adapter = TaskAdapter()

        binding.cancel.setOnClickListener {
            dismiss()
        }
        var lm = LinearLayoutManager(requireContext())
        lm.orientation = LinearLayoutManager.VERTICAL
        binding.taskList.layoutManager = lm
        binding.taskList.adapter = adapter
        adapter.addItem("loading")
        if (viewModel.getTaskDataResponse?.hasActiveObservers() == true)
            viewModel.getTaskDataResponse?.removeObservers(viewLifecycleOwner)
        viewModel.getTaskDataResponse?.observe(viewLifecycleOwner){
            if (it?.data != null && it.data.isNotEmpty()){
                val layoutManager = GridLayoutManager(context,2)
                layoutManager.orientation = GridLayoutManager.VERTICAL
                binding.taskList.layoutManager = layoutManager
                AppLogger.log("myTask data not null")
                val list :ArrayList<Any> = ArrayList()
                list.addAll(it.data)
                adapter.updateList(list)

            }else{
//                no data found
                val layoutManager = LinearLayoutManager(requireContext())
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                binding.taskList.layoutManager = layoutManager
                adapter.addItem("no_data")
            }
        }

        viewModel.fetchTaskData(template)
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ProjectBottomSheetLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
//        dialog.behavior.skipCollapsed = true
        return dialog
    }

}