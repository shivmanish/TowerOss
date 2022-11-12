package com.smarthub.baseapplication.ui.basic_info.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BasicInfoFragmentBinding
import com.smarthub.baseapplication.databinding.CustomerInfoFragmentInfoBinding
import com.smarthub.baseapplication.databinding.OperationalInfoFragmentBinding
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModel
import com.smarthub.baseapplication.network.pojo.site_info.OperationalInfoModel
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class OperationalInfo :Fragment(), ImageAttachmentAdapter.ItemClickListener {

    var binding : OperationalInfoFragmentBinding?=null
    var data: OperationalInfoModel? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = OperationalInfoFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }   

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = requireArguments().getSerializable("data") as OperationalInfoModel?
        var recyclerListener = view.findViewById<RecyclerView>(R.id.list_item)
        var adapter =  ImageAttachmentAdapter(this@OperationalInfo)
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

        binding!!.siteCategorySpinner.setSpinnerData(data!!.sitebillingstatus.data)

        binding!!.siteTypeSpinner.setSpinnerData(data!!.costcentre.data)
        binding!!.siteOwnershipSpinner.setSpinnerData(data!!.sharingfeasibility.data)
        binding!!.townCategorySpinner.setSpinnerData(data!!.towncategor.data)
        binding!!.townCategorySpinner.setSpinnerData(data!!.towncategor.data)
        binding!!.hubCitySpinner.setSpinnerData(data!!.hubsite.data)
        binding!!.ldcaSpinner.setSpinnerData(data!!.ldca.data)
        binding!!.scdaSpinner.setSpinnerData(data!!.scda.data)


    }

    override fun itemClicked() {
        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()
    }
}