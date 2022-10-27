package com.smarthub.baseapplication.ui.fragments.customer_tab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.CustomerInfoFragmentInfoBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.model.profile.UserProfileGet
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.viewmodels.ProfileViewModel
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel

class CustomerInfo :Fragment(), ImageAttachmentAdapter.ItemClickListener {

    var binding : CustomerInfoFragmentInfoBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = CustomerInfoFragmentInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var recyclerListener = view.findViewById<RecyclerView>(R.id.list_item)
        var adapter =  ImageAttachmentAdapter(this@CustomerInfo)
        recyclerListener.adapter = adapter

        view.findViewById<View>(R.id.attach_card).setOnClickListener {
            adapter.addItem()
        }

    }

    override fun itemClicked() {
        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()
    }



}