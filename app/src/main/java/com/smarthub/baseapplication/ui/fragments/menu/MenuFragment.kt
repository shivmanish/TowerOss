package com.smarthub.baseapplication.ui.fragments.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.activities.FAQActivity
import com.smarthub.baseapplication.activities.SettingActivity
import com.smarthub.baseapplication.databinding.FragmentMenuBinding
import com.smarthub.baseapplication.ui.fragments.profile.ProfileActivity
import com.smarthub.baseapplication.ui.fragments.qat.QATCheckActivity
import com.smarthub.baseapplication.ui.site_lease_acquisition.NewSiteAcquisitionActivity
import com.smarthub.baseapplication.viewmodels.MainViewModel

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var mainViewModel:MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val notificationsViewModel = ViewModelProvider(this)[MenuViewModel::class.java]
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mainViewModel.isActionBarHide(false)
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileCard.setOnClickListener {
//            var intent = Intent(requireContext(), NewSiteAcquisitionActivity::class.java)
//            requireActivity().startActivity(intent)
            var intent = Intent(requireContext(), ProfileActivity::class.java)
            requireActivity().startActivity(intent)
        }
        binding.setting.setOnClickListener {
            var intent = Intent(requireContext(),SettingActivity::class.java)
            requireActivity().startActivity(intent)
        }
        binding?.cardQat?.setOnClickListener {
            var intent = Intent(requireActivity(),QATCheckActivity::class.java)
            startActivity(intent)
        }

        binding.quickHelp.setOnClickListener {
            var intent = Intent(requireActivity(),FAQActivity::class.java)
            startActivity(intent)
        }

        binding.cardAnalytics.setOnClickListener {
            Toast.makeText(requireContext(),"Screen coming soon",Toast.LENGTH_SHORT).show()
        }

        binding.cardLocation.setOnClickListener {
            Toast.makeText(requireContext(),"Screen coming soon",Toast.LENGTH_SHORT).show()
        }
        binding.cardTeam.setOnClickListener {
            Toast.makeText(requireContext(),"Screen coming soon",Toast.LENGTH_SHORT).show()
        }

        binding.cardOther1.setOnClickListener {
            Toast.makeText(requireContext(),"Screen coming soon",Toast.LENGTH_SHORT).show()
        }

        binding.cardOther2.setOnClickListener {
            Toast.makeText(requireContext(),"Screen coming soon",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}