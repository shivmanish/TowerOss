package com.smarthub.baseapplication.ui.fragments.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ProjectBottomSheetLayoutBinding
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TemplateTaskBottomSheet(contentLayoutId: Int,var viewModel: HomeViewModel) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: ProjectBottomSheetLayoutBinding
    lateinit var adapter : TaskAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProjectBottomSheetLayoutBinding.bind(view)
        adapter = TaskAdapter()

        binding.taskList.layoutManager = GridLayoutManager(context,2)
        binding.taskList.adapter = adapter
        if (viewModel.getTaskDataResponse?.hasActiveObservers() == true)
            viewModel.getTaskDataResponse?.removeObservers(viewLifecycleOwner)
        viewModel.getTaskDataResponse?.observe(viewLifecycleOwner){
            if (it?.data != null){
                adapter.updateList(it.data)
            }
        }
        viewModel.fetchTaskData("SMPS Templeate")
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ProjectBottomSheetLayoutBinding.inflate(inflater)
        return binding.root
    }


}