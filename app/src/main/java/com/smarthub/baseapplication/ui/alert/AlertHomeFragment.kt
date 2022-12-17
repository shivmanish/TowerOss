package com.smarthub.baseapplication.ui.alert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.databinding.AlertHomeBinding


class AlertHomeFragment : Fragment() {

    lateinit var binding : AlertHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AlertHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customizeAlert.setOnClickListener {
            findNavController().navigate(AlertHomeFragmentDirections.actionAlertHomeFragmentToAlertStatusFragment())
        }

        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}


