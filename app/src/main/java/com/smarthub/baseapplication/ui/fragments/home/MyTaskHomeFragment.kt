package com.smarthub.baseapplication.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentMyTaskHomeBinding

class MyTaskHomeFragment : Fragment() {

    lateinit var binding : FragmentMyTaskHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMyTaskHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.taskList.setHasFixedSize(true)
        binding.taskList.adapter = TaskHomeItemAdapter()
    }

}