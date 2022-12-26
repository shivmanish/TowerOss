package com.smarthub.baseapplication.ui.fragments.task

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentSearchTaskBinding
import com.smarthub.baseapplication.databinding.FragmentTaskBinding
import com.smarthub.baseapplication.ui.fragments.customer_tab.powerload.PowerLoadEditDialouge
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskAdapter
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskAssignToDialouge
import com.smarthub.baseapplication.viewmodels.MainViewModel

class TaskSearchTabFragment : Fragment(), TaskAdapter.TaskLisListener {

    private lateinit var binding: FragmentSearchTaskBinding
    private lateinit var mainViewModel:MainViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mainViewModel.isActionBarHide(false)
        binding = FragmentSearchTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listItem.adapter = TaskAdapter(this@TaskSearchTabFragment)


    }



    override fun attachmentItemClicked() {

    }

    override fun detailsItemClicked() {

    }

    override fun requestinfoClicked() {

    }

    override fun operationInfoDetailsItemClicked() {

    }

    override fun geoConditionsDetailsItemClicked() {

    }

    override fun siteAccessDetailsItemClicked() {

    }

}