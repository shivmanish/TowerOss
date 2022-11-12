package com.smarthub.baseapplication.ui.basic_info.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OperationalInfoFragmentBinding
import com.smarthub.baseapplication.databinding.SafatyAccessFragmentBinding
import com.smarthub.baseapplication.network.pojo.site_info.GeoConditionModel
import com.smarthub.baseapplication.network.pojo.site_info.SafetyAndAccessModel
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class SafatyAccessFragment :Fragment(), ImageAttachmentAdapter.ItemClickListener {
    var data: SafetyAndAccessModel? = null
    var binding : SafatyAccessFragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = SafatyAccessFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }   

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = requireArguments().getSerializable("data") as SafetyAndAccessModel?

        var recyclerListener = view.findViewById<RecyclerView>(R.id.list_item)
        var adapter =  ImageAttachmentAdapter(this@SafatyAccessFragment)
        recyclerListener.adapter = adapter

        view.findViewById<View>(R.id.attach_card).setOnClickListener {
            adapter.addItem()
        }
        initViews(view)
    }

    fun initViews(view: View){
//        var b = view.findViewById<View>(R.id.attach_card)
//        b.setOnClickListener {
//
//        }

        binding!!.videoMonitoringSpinner.setSpinnerData(data!!.videomonitoring.data)
        binding!!.dangerSignageSpinner.setSpinnerData(data!!.dangerSignage.data)
        binding!!.siteAccessAreaSpinner.setSpinnerData(data!!.siteAccessArea.data)
        binding!!.siteAccess.setSpinnerData(data!!.siteaccess.data)

    }

    override fun itemClicked() {
        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()
    }
}