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
import java.text.SimpleDateFormat

class HistoryFragment (var profiledata: ProfileData?) : BaseFragment() {
    lateinit var binding : ProfileRoleGeographiBinding
    lateinit var viewmodel: HomeViewModel
    val sdf = SimpleDateFormat("yyyy-MMM-dd")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding=ProfileRoleGeographiBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.level1.text="Created by"
        binding.level2.text="Created Date"
        binding.level3.text="Approved by"
        binding.level4.text="Approved Date"

        try {
            binding.text1.text= String.format("%s %s",profiledata?.first_name,profiledata?.last_name)
            binding.text2.text= profiledata?.createddate?.take(10)
            binding.text3.text=profiledata?.approvedby
            binding.text4.text=profiledata?.approveddate
        }catch (e : Exception){
            AppLogger.log("Profile History view frag Error ${e.localizedMessage}")
            Toast.makeText(context,"Profile History view frag Error", Toast.LENGTH_LONG).show()
        }

    }




}