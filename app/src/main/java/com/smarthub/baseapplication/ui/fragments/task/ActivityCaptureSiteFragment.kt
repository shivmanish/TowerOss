package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.CaptureSiteFragmentDataBinding
import com.smarthub.baseapplication.ui.fragments.task.adapter.CaptureSiteAdapter

class ActivityCaptureSiteFragment: Fragment() {
    lateinit var binding:CaptureSiteFragmentDataBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CaptureSiteFragmentDataBinding.inflate(inflater)
    setdata()
        return binding.root
    }

    private fun setdata() {
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = CaptureSiteAdapter(requireContext())
    }

}