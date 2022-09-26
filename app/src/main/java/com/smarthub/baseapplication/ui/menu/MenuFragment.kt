package com.smarthub.baseapplication.ui.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.activities.SettingActivity
import com.smarthub.baseapplication.databinding.FragmentMenuBinding
import com.smarthub.baseapplication.databinding.FragmentNotificationsBinding
import com.smarthub.baseapplication.ui.profile.ProfileActivity
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
            var intent = Intent(requireContext(),ProfileActivity::class.java)
            requireActivity().startActivity(intent)
        }
        binding.setting.setOnClickListener {
            var intent = Intent(requireContext(),SettingActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}