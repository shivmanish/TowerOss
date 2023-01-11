package com.smarthub.baseapplication.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.ProfileRoleGeographiBinding
import com.smarthub.baseapplication.network.ProfileDetails
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesRequestAdapter
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class OfficialDetailFragment(var profiledata: ProfileDetails?) : BaseFragment() {
    lateinit var binding:ProfileRoleGeographiBinding
    lateinit var viewmodel: HomeViewModel
    lateinit var adapter: ServicesRequestAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding=ProfileRoleGeographiBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.level1?.text="Company Name"
        binding?.level2?.text="Department Name"
        binding?.level3?.text="Role Name"
        binding?.lavel4Layout?.visibility=View.GONE
        if(profiledata!=null){

        }

    }




}