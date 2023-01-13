package com.smarthub.baseapplication.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.Ac0demoBinding
import com.smarthub.baseapplication.model.profile.viewProfile.ProfileDetails
import com.smarthub.baseapplication.model.profile.viewProfile.newData.ProfileData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class UserRoleTabFragment(var profiledata: ProfileData?) : BaseFragment() {
    var binding: Ac0demoBinding? = null
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Ac0demoBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.profileItemsList?.adapter=UserRoleFragAdapter(requireContext(),profiledata)

    }
}