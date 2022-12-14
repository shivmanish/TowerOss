package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.CivilInfraFragmentBinding
import com.smarthub.baseapplication.ui.fragments.customer_tab.NewCustomerDetailsActivity

class CivilInfraFragment : Fragment(),CivilInfraAdapter.CivilInfraAdapterListner {

    lateinit var binding : CivilInfraFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CivilInfraFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews(){

        binding.powerConnList.adapter = CivilInfraAdapter(this@CivilInfraFragment)
    }

    override fun clickedTowerItem() {
        requireActivity().startActivity(Intent(requireContext(), TwrInfraDetails::class.java))
    }

    override fun clickedPoleItem() {
        requireActivity().startActivity(Intent(requireContext(), TowerPoleFragemnt::class.java))
    }

    override fun clickedEquipmentRoomItem() {
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun clickedEarthingItem() {
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }
}