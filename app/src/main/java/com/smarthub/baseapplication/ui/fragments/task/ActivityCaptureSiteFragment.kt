package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.CaptureSiteFragmentDataBinding
import com.smarthub.baseapplication.ui.fragments.task.adapter.CaptureSiteAdapter

class ActivityCaptureSiteFragment: Fragment() {
    lateinit var binding:CaptureSiteFragmentDataBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CaptureSiteFragmentDataBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = CaptureSiteAdapter(requireContext())

        binding.next.setOnClickListener {
            nextClicked()
        }
        binding.cancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun nextClicked() {
        findNavController().navigate(TaskSecondFragmentDirections.actionToMoveThirdFrag())
    }
}