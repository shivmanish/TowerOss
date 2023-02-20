package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.databinding.TaskSecondFragmnetBinding
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskViewpagerAdapter
import com.smarthub.baseapplication.utils.AppLogger

class TaskSecondFragment:Fragment(){

    lateinit var binding:TaskSecondFragmnetBinding
    lateinit var viewmodel: TaskViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TaskSecondFragmnetBinding.inflate(inflater)
        viewmodel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewpager.adapter = TaskViewpagerAdapter(childFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewpager)
        binding.next.setOnClickListener {
            AppLogger.log("final model for second step===> : ${viewmodel.processTemplatemanual}")
            nextClicked()
        }
        binding.cancel.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun nextClicked() {
        findNavController().navigate(TaskSecondFragmentDirections.actionToMoveThirdFrag())
    }


}