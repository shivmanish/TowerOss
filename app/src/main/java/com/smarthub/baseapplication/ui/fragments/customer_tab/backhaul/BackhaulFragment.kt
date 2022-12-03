package com.smarthub.baseapplication.ui.fragments.customer_tab.backhaul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.BackhaulFragmentBinding
import com.smarthub.baseapplication.databinding.BackhaulFragmentBinding.*
import com.smarthub.baseapplication.databinding.CommercialFragmentBinding
import com.smarthub.baseapplication.databinding.CustomerInfoFragmentInfoBinding
import com.smarthub.baseapplication.databinding.CustomerInfoTempFragmentBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.adapter.customer.BackhaulListAdapter
import com.smarthub.baseapplication.ui.adapter.customer.CommercialListAdapter

class BackhaulFragment :Fragment(), ImageAttachmentAdapter.ItemClickListener {

    var binding : BackhaulFragmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = BackhaulListAdapter(this@BackhaulFragment)
    }

    override fun itemClicked() {

        Toast.makeText(requireContext(),"Commercial Item clicked",Toast.LENGTH_SHORT).show()
    }
}