package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CivilInfraFragmentBinding
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class CivilInfraFragment : BaseFragment(),CivilInfraAdapter.CivilInfraAdapterListner {
    var viewmodel: HomeViewModel?=null
    lateinit var binding : CivilInfraFragmentBinding
    lateinit var adapter: CivilInfraAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CivilInfraFragmentBinding.inflate(inflater)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        binding.addMore.setOnClickListener{
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")

        }

        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing=false
        }
    }

    fun initViews(){
        adapter=CivilInfraAdapter(this@CivilInfraFragment)
        binding.powerConnList.adapter = adapter
    }

    override fun clickedTowerItem() {
        requireActivity().startActivity(Intent(requireContext(), TwrInfraDetails::class.java))
    }

    override fun clickedPoleItem() {
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun clickedEquipmentRoomItem() {
        requireActivity().startActivity(Intent(requireContext(), TowerEquipmentFragemnt::class.java))
    }

    override fun clickedEarthingItem() {
        requireActivity().startActivity(Intent(requireContext(), TowerEarthingFragment::class.java))
    }
}