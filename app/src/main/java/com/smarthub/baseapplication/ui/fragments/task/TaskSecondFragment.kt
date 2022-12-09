package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.databinding.TaskSecondFragmnetBinding
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskViewpagerAdapter

class TaskSecondFragment:Fragment(), PhotoDocumentFragment.PhotoDocumentListener {

    lateinit var binding:TaskSecondFragmnetBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TaskSecondFragmnetBinding.inflate(inflater)
        setview()
        return binding.root
    }

    private fun setview() {
        binding.viewpager.adapter = TaskViewpagerAdapter(childFragmentManager,this@TaskSecondFragment)
        binding.tabs.setupWithViewPager(binding.viewpager)

    }

    override fun nextClicked() {
        findNavController().navigate(TaskSecondFragmentDirections.actionToMoveThirdFrag())
    }

}