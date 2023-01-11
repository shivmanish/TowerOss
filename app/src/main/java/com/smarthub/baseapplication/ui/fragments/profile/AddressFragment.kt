package com.smarthub.baseapplication.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.ProfileOfficeComunicationAddressBinding
import com.smarthub.baseapplication.databinding.ProfileRoleGeographiBinding
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AddressFragment (var manager: FragmentManager) : BaseFragment() {
    var binding : ProfileOfficeComunicationAddressBinding?=null
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding=ProfileOfficeComunicationAddressBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




}