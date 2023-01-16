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

class ManagerInfoFragment (var profiledata: ProfileData?) : BaseFragment() {
    lateinit var binding : ProfileRoleGeographiBinding
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding=ProfileRoleGeographiBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.level1.text="Manager Name"
        binding.level2.text="Email Id"
        binding.level3.text="Mobile Number"
        binding.lavel4Layout.visibility= View.GONE

        try {
            binding.text1.text=profiledata?.managername
            binding.text2.text=profiledata?.manageremail
            binding.text3.text=profiledata?.managerphone
        }catch (e : Exception){
            AppLogger.log("Manager Info view frag Error ${e.localizedMessage}")
            Toast.makeText(context,"Manager Info view frag Error", Toast.LENGTH_LONG).show()
        }

    }




}