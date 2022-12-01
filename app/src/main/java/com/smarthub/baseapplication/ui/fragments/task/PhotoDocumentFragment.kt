package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.CaptureSiteFragmentDataBinding
import com.smarthub.baseapplication.databinding.PhotoDocumentFragmentBinding
import com.smarthub.baseapplication.ui.fragments.task.adapter.CaptureSiteAdapter

class PhotoDocumentFragment(var listener: PhotoDocumentListener): Fragment() {
    lateinit var binding:PhotoDocumentFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PhotoDocumentFragmentBinding.inflate(inflater)
        setdata()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            binding.editRemark.clearFocus()
            listener.nextClicked()
        }
        binding.canecelText.setOnClickListener {
            binding.editRemark.clearFocus()
        }
    }

    private fun setdata() {
    }

    interface PhotoDocumentListener{
        fun nextClicked()
    }

}