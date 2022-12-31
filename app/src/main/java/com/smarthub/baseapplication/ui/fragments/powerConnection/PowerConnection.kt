package com.smarthub.baseapplication.ui.fragments.powerConnection

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.PowerConnectionFragmentBinding
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.powerConnection.adapter.PowerConnDataAdapter
import com.smarthub.baseapplication.ui.fragments.powerConnection.adapter.PowerConnDataDataDataAdapterListener
import com.smarthub.baseapplication.ui.fragments.powerConnection.pojo.PowerAndFuel
import com.smarthub.baseapplication.ui.fragments.powerConnection.viewmodel.PowerConnectionRootViewModel

class PowerConnection : BaseFragment(), PowerConnDataDataDataAdapterListener {

    lateinit var binding: PowerConnectionFragmentBinding
    lateinit var viewmodel: PowerConnectionRootViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PowerConnectionFragmentBinding.inflate(inflater)
        viewmodel = ViewModelProvider(this).get(PowerConnectionRootViewModel::class.java)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {
        viewmodel.getPowerListingData(requireContext())
        viewmodel.powerfuelListLivedata.observe(viewLifecycleOwner, Observer {
            binding.powerConnList.adapter = PowerConnDataAdapter(
                this@PowerConnection,
                it.PowerAndFuel as ArrayList<PowerAndFuel>
            )
        })
    }

    override fun clickedItem(data: PowerAndFuel) {
        val intent = Intent(
            requireContext(),
            PowerConnectionDetailsActivity::class.java
        )
        intent.putExtra("data", data)
        requireActivity().startActivity(intent)
    }
}