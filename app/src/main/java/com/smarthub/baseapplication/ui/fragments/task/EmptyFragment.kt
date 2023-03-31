package com.smarthub.baseapplication.ui.fragments.task

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.smarthub.baseapplication.databinding.CaptureSiteFragmentDataBinding
import com.smarthub.baseapplication.databinding.LayoutEmptyBinding
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel
import com.smarthub.baseapplication.ui.fragments.task.adapter.CaptureSiteAdapter
import com.smarthub.baseapplication.ui.fragments.task.adapter.CapturedSite
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class EmptyFragment: Fragment() {
    lateinit var binding:LayoutEmptyBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LayoutEmptyBinding.inflate(inflater)
        return binding.root
    }




}