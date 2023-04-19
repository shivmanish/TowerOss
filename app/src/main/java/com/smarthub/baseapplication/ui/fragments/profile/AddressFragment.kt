package com.smarthub.baseapplication.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.ProfileOfficeComunicationAddressBinding
import com.smarthub.baseapplication.model.profile.viewProfile.ProfileDetails
import com.smarthub.baseapplication.model.profile.viewProfile.newData.ProfileData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AddressFragment (var profiledata: ProfileData?) : BaseFragment() {
    lateinit var binding : ProfileOfficeComunicationAddressBinding
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding=ProfileOfficeComunicationAddressBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.officeHouseNo.text="---"
        binding.officeBuilding.text="---"
        binding.officeDistrict.text="---"
        binding.officeState.text= "---"
        binding.officePinCode.text="---"

        binding.communicationHouseNo.text="---"
        binding.communicationBuilding.text="---"
        binding.communicationDistrict.text="---"
        binding.communicationState.text= "---"
        binding.communicationPinCode.text="---"

        try {
            binding.officeHouseNo.text=profiledata?.officeaddress?.address1 +" "+profiledata?.officeaddress?.address2
            binding.officeBuilding.text=""
            binding.officeDistrict.text=""
            binding.officeState.text= profiledata?.officeaddress?.state
            binding.officePinCode.text=""

            binding.communicationHouseNo.text=profiledata?.communicationaddress?.address1+" "+profiledata?.communicationaddress?.address2
            binding.communicationBuilding.text=""
            binding.communicationDistrict.text=""
            binding.communicationState.text= profiledata?.communicationaddress?.state
            binding.communicationPinCode.text=""
        }catch (e : Exception){
            AppLogger.log("Address view frag Error ${e.localizedMessage}")
            Toast.makeText(context,"Address view frag Error", Toast.LENGTH_LONG).show()
        }

    }




}