package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.smarthub.baseapplication.databinding.CaptureSiteFragmentDataBinding
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel
import com.smarthub.baseapplication.ui.fragments.task.adapter.CaptureSiteAdapter
import com.smarthub.baseapplication.utils.Utils

class ActivityCaptureSiteFragment: Fragment() {
    lateinit var binding:CaptureSiteFragmentDataBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CaptureSiteFragmentDataBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.layoutManager = LinearLayoutManager(context)
        var json = Utils.getJsonDataFromAsset(requireContext(),"task_drop_down.json")
        var model = Gson().fromJson(json,TaskDropDownModel::class.java)
        binding.list.adapter = CaptureSiteAdapter(requireContext(),model)

        binding.next.setOnClickListener {
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