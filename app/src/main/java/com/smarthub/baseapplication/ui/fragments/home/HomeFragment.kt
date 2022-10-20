package com.smarthub.baseapplication.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.activities.SearchActivity
import com.smarthub.baseapplication.databinding.FragmentHomeBinding
import com.smarthub.baseapplication.viewmodels.MainViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    var mainViewModel : MainViewModel ?=null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel?.isActionbarHide?.value = true
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.searchBoxContainer.searchCardView.setOnClickListener {
            var intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}