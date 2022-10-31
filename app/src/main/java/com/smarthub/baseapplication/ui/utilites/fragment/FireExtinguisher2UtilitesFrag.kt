package com.smarthub.baseapplication.ui.utilites.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.Ac1TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.databinding.FireExtinguisher1TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.databinding.FireExtinguisher2TabUtilitiesFragmentBinding

import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class FireExtinguisher2UtilitesFrag :Fragment(), ImageAttachmentAdapter.ItemClickListener {

    var binding : FireExtinguisher2TabUtilitiesFragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FireExtinguisher2TabUtilitiesFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyclerListener = view.findViewById<RecyclerView>(R.id.list_item)
        var adapter =  ImageAttachmentAdapter(this@FireExtinguisher2UtilitesFrag)
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
    }

    override fun itemClicked() {
        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()
    }
}