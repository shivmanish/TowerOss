package com.smarthub.baseapplication.ui.basic_info.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BasicInfoFragmentBinding
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModel
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoStatusData
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter



class BasicInfo : Fragment(), ImageAttachmentAdapter.ItemClickListener {

    var binding: BasicInfoFragmentBinding? = null
    var data: BasicInfoModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BasicInfoFragmentBinding.inflate(inflater, container, false)
        data = requireArguments().getSerializable("data") as BasicInfoModel?
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("this is called and data is " + data!!.getBuildingtype().data.size)
        var recyclerListener = view.findViewById<RecyclerView>(R.id.list_item)
        var adapter = ImageAttachmentAdapter(this@BasicInfo)
        recyclerListener.adapter = adapter

        view.findViewById<View>(R.id.attach_card).setOnClickListener {
            adapter.addItem()
        }
        initViews(view)
    }

    fun initViews(view: View) {
//        var b = view.findViewById<View>(R.id.attach_card)
//        b.setOnClickListener {
//
//        }
        binding!!.siteStatusSpinner.setSpinnerData(data!!.sitestatus.data)
        binding!!.siteCategorySpinner.setSpinnerData(data!!.sitecategory.data)
        binding!!.siteOwnershipSpinner.setSpinnerData(data!!.siteownership.data)
        binding!!.siteTypeSpinner.setSpinnerData(data!!.sitetype.data)

    }

    override fun itemClicked() {
        Toast.makeText(requireContext(), "Item Clicked", Toast.LENGTH_SHORT).show()
    }

}