package com.smarthub.baseapplication.ui.fragments.customer_tab.powerload

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.PowerLoadFregmentBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class PowerLoadFragment: Fragment(),ImageAttachmentAdapter.ItemClickListener {

    var adapter : PowerLoadAdapter?=null
    var binding : PowerLoadFregmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = PowerLoadFregmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PowerLoadAdapter(this@PowerLoadFragment)
        binding?.listItem?.adapter = adapter

        binding?.addItemsLayout?.setOnClickListener {
            adapter?.addItem()
        }
    }

    override fun itemClicked() {

        Toast.makeText(requireContext(),"Commercial Item clicked", Toast.LENGTH_SHORT).show()
    }

}