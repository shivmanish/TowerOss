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
            findNavController().navigate(AlertHomeFragmentDirections.actionAlertHomeFragmentToAlertStatusFragment("custom"))
        }

        binding.icTheft.setOnClickListener {
            findNavController().navigate(AlertHomeFragmentDirections.actionAlertHomeFragmentToAlertStatusFragment("Theft"))
        }

        binding.icFire.setOnClickListener {
            findNavController().navigate(AlertHomeFragmentDirections.actionAlertHomeFragmentToAlertStatusFragment("Fire"))
        }

        binding.icDoorOpen.setOnClickListener {
            findNavController().navigate(AlertHomeFragmentDirections.actionAlertHomeFragmentToAlertStatusFragment("Door Open"))
        }

        binding.icSabotage.setOnClickListener {
            findNavController().navigate(AlertHomeFragmentDirections.actionAlertHomeFragmentToAlertStatusFragment("Sabotage"))
        }

        binding.icSuspiciousActivity.setOnClickListener {
            findNavController().navigate(AlertHomeFragmentDirections.actionAlertHomeFragmentToAlertStatusFragment("Suspicious Activity"))
        }

        binding.icHygieneIssue.setOnClickListener {
            findNavController().navigate(AlertHomeFragmentDirections.actionAlertHomeFragmentToAlertStatusFragment("Hygiene Issue"))
        }

        binding.icPropertyDamage.setOnClickListener {
            findNavController().navigate(AlertHomeFragmentDirections.actionAlertHomeFragmentToAlertStatusFragment("Property Damage"))
        }

        binding.icMobGathering.setOnClickListener {
            findNavController().navigate(AlertHomeFragmentDirections.actionAlertHomeFragmentToAlertStatusFragment("Mob Gathering"))
        }

        binding.icFlood.setOnClickListener {
            findNavController().navigate(AlertHomeFragmentDirections.actionAlertHomeFragmentToAlertStatusFragment("Flood"))
        }

        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}


