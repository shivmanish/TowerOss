package com.smarthub.baseapplication.ui.utilites.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.Ac1TabUtilitiesFragmentBinding

import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class AC1UtilitesFrag :Fragment(), ImageAttachmentAdapter.ItemClickListener {

    var binding : Ac1TabUtilitiesFragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Ac1TabUtilitiesFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyclerListener = view.findViewById<RecyclerView>(R.id.list_item)
        var collapsing_layout = view.findViewById<LinearLayout>(R.id.collapsing_layout)
        var collapsing_InstallationAcceptance = view.findViewById<LinearLayout>(R.id.collapsing_InstallationAcceptance)
        var collapsing_layoutup = view.findViewById<LinearLayout>(R.id.collapsing_layoutup)
        var collapsing_MaintenanceUp = view.findViewById<LinearLayout>(R.id.collapsing_MaintenanceUp)
        var collapsing_InstallationAcceptanceUp = view.findViewById<LinearLayout>(R.id.collapsing_InstallationAcceptanceUp)
        var item_collapse_equipment = view.findViewById<LinearLayout>(R.id.item_collapse_equipment)
        var item_collapse_installations = view.findViewById<LinearLayout>(R.id.item_collapse_installations)
        var item_collapse_maintance = view.findViewById<LinearLayout>(R.id.item_collapse_maintance)
         //Eqipment collapsing
           collapsing_layout.setOnClickListener(){
            item_collapse_equipment.setVisibility(View.VISIBLE);
            collapsing_layoutup.setVisibility(View.VISIBLE);
            collapsing_layout.setVisibility(View.GONE);

        }
           collapsing_layoutup.setOnClickListener(){
            item_collapse_equipment.setVisibility(View.GONE);
            collapsing_layoutup.setVisibility(View.GONE);
            collapsing_layout.setVisibility(View.VISIBLE);
        }
        //installation collapsing
            collapsing_InstallationAcceptance.setOnClickListener(){
            item_collapse_installations.setVisibility(View.VISIBLE);
            collapsing_InstallationAcceptanceUp.setVisibility(View.VISIBLE);
            collapsing_InstallationAcceptance.setVisibility(View.GONE);



        }
            collapsing_InstallationAcceptanceUp.setOnClickListener(){
            item_collapse_installations.setVisibility(View.GONE);
            collapsing_InstallationAcceptanceUp.setVisibility(View.GONE);
            collapsing_InstallationAcceptance.setVisibility(View.VISIBLE);



        }

        var adapter =  ImageAttachmentAdapter(this@AC1UtilitesFrag)
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