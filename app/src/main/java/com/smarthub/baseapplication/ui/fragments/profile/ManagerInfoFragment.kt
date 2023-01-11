package com.smarthub.baseapplication.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.ProfileRoleGeographiBinding
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesRequestAdapter
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class ManagerInfoFragment (var manager: FragmentManager) : BaseFragment() {
    var binding : ProfileRoleGeographiBinding?=null
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding=ProfileRoleGeographiBinding.inflate(inflater, container, false)

        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.level1?.text="Manager Name"
        binding?.level2?.text="Email Id"
        binding?.level3?.text="Mobile Number"
        binding?.lavel4Layout?.visibility= View.GONE

    }




}