package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.CaptureSiteFragmentDataBinding
import com.smarthub.baseapplication.databinding.PhotoDocumentFragmentBinding
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.task.adapter.CaptureSiteAdapter
import com.smarthub.baseapplication.utils.AppLogger

class PhotoDocumentFragment: BaseFragment() {
    lateinit var binding:PhotoDocumentFragmentBinding
    lateinit var viewmodel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PhotoDocumentFragmentBinding.inflate(inflater)
        viewmodel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next.setOnClickListener {
            viewmodel.processTemplatemanual.pictures=binding.pictureBox.isChecked
            viewmodel.processTemplatemanual.documents=binding.documentBox.isChecked
            viewmodel.processTemplatemanual.remark=binding.Remark.text.toString()
            AppLogger.log("all data: ${viewmodel.processTemplatemanual}")
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