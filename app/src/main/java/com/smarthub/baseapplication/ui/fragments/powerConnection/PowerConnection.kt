package com.smarthub.baseapplication.ui.fragments.powerConnection

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.PowerConnectionFragmentBinding
import com.smarthub.baseapplication.ui.fragments.powerConnection.adapter.PowerConnDataAdapter
import com.smarthub.baseapplication.ui.fragments.powerConnection.adapter.PowerConnDataDataDataAdapterListener

class PowerConnection : Fragment(),PowerConnDataDataDataAdapterListener {

    lateinit var binding : PowerConnectionFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PowerConnectionFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews(){

        binding.powerConnList.adapter = PowerConnDataAdapter(this@PowerConnection, arrayListOf(""))
    }

    override fun clickedItem() {
        requireActivity().startActivity(Intent(requireContext(), PowerConnectionDetailsActivity::class.java))
    }
}