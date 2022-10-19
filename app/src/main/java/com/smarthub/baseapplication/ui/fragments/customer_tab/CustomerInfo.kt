package com.smarthub.baseapplication.ui.fragments.customer_tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.CustomerInfoFragmentInfoBinding

class CustomerInfo :Fragment() {

    var binding : CustomerInfoFragmentInfoBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = CustomerInfoFragmentInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }
}