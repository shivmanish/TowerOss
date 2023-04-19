package com.smarthub.baseapplication.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.ProfileRoleGeographiBinding
import com.smarthub.baseapplication.model.profile.viewProfile.ProfileDetails
import com.smarthub.baseapplication.model.profile.viewProfile.newData.ProfileData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class RoleGeographiFragment (var profiledata: ProfileData?) : BaseFragment() {
    lateinit var binding : ProfileRoleGeographiBinding
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding=ProfileRoleGeographiBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.level1.text="National"
        binding.level2.text="Region"
        binding.level3.text="State"
        binding.level4.text="MP"

        binding.text1.text="---"
        binding.text2.text="---"
        binding.text3.text="---"
        binding.text4.text= "---"

        try {
            binding.text1.text=profiledata?.national
            binding.text2.text=profiledata?.region?.get(0)
            binding.text3.text=profiledata?.state?.get(0)
            binding.text4.text= profiledata?.maintenancepoint?.get(0)
        }catch (e : Exception){
            AppLogger.log("Role Geography view frag Error ${e.localizedMessage}")
            Toast.makeText(context,"Role Geography view frag Error", Toast.LENGTH_LONG).show()
        }

    }




}