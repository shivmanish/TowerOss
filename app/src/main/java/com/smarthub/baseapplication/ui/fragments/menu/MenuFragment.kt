package com.smarthub.baseapplication.ui.fragments.menu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.FAQActivity
import com.smarthub.baseapplication.activities.SettingActivity
import com.smarthub.baseapplication.databinding.FragmentMenuBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.profile.viewProfile.newData.ProfileData
import com.smarthub.baseapplication.ui.dialog.home.AdNewSiteInfoBottomSheet
import com.smarthub.baseapplication.ui.fragments.profile.ProfileActivity
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.viewmodels.ProfileViewModel

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    lateinit var homeViewModel : HomeViewModel
    private var profileViewModel : ProfileViewModel?=null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ViewModelProvider(this)[MenuViewModel::class.java]
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        profileViewModel= ViewModelProvider(this)[ProfileViewModel::class.java]
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel?.getProfileData()
        binding.profileCard.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            requireActivity().startActivity(intent)
        }
        binding.setting.setOnClickListener {
            val intent = Intent(requireContext(),SettingActivity::class.java)
            requireActivity().startActivity(intent)
        }
        binding.cardQat.setOnClickListener {
//            val intent = Intent(requireActivity(),QATCheckActivity::class.java)
//            startActivity(intent)
        }
        binding.cardProject.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionNavigationMenuToProjectsFragment2())
        }


        binding.quickHelp.setOnClickListener {
            val intent = Intent(requireActivity(),FAQActivity::class.java)
            startActivity(intent)
        }

        binding.cardAnalytics.setOnClickListener {
//            Toast.makeText(requireContext(),"Screen coming soon",Toast.LENGTH_SHORT).show()
        }

        binding.cardTeam.setOnClickListener {
//            Toast.makeText(requireContext(),"Screen coming soon",Toast.LENGTH_SHORT).show()
        }

        binding.cardOther1.setOnClickListener {
//            Toast.makeText(requireContext(),"Screen coming soon",Toast.LENGTH_SHORT).show()
        }

        binding.AddNewSiteCard.setOnClickListener {
            val bottomSheetDialogFragment = AdNewSiteInfoBottomSheet(R.layout.operations_info_details_bottom_sheet,homeViewModel,
                object : AdNewSiteInfoBottomSheet.AdNewSiteSheetListener{
                    override fun siteCreated(id: String) {
                findNavController().navigate(MenuFragmentDirections.actionNavigationMenuToSiteDetailFragment(id))
                    }
                })
            bottomSheetDialogFragment.show(childFragmentManager, "category")
        }

        if (profileViewModel?.profileResponse?.hasActiveObservers()==true)
            profileViewModel?.profileResponse?.removeObservers(this)
        profileViewModel?.profileResponse?.observe(requireActivity()) {

            if (it != null && it.data?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS) {
                    AppPreferences.getInstance().saveString("data", Gson().toJson(it.data[0]))
                    uiDataMapping(it.data[0])
                    Log.d("status", "${it.message}")
//                    Toast.makeText(this@ProfileActivity, "ProfileSuccessful", Toast.LENGTH_LONG).show()
                    return@observe
                } else {
                    Log.d("status", "${it.message}")
//                    Toast.makeText(this@ProfileActivity, "error:" + it.message, Toast.LENGTH_LONG).show()

                }
            } else {
                Log.d("status", AppConstants.GENERIC_ERROR)
//                Toast.makeText(this@ProfileActivity, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun uiDataMapping(profileDetails: ProfileData){
        try{
            binding.titleDesc.text = String.format("Welcome, %s %s",profileDetails.first_name,profileDetails.last_name)
        }
        catch (e : Exception){
            AppLogger.log("Profile View Error ${e.localizedMessage}")
//            Toast.makeText(this,"Profile View Error",Toast.LENGTH_LONG).show()
        }



    }

}