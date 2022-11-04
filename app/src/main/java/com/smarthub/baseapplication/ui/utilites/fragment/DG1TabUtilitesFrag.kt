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
import com.smarthub.baseapplication.databinding.Dg1TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.databinding.FireExtinguisher1TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.databinding.Spd1TabUtilitiesFragmentBinding

import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.Utils

class DG1TabUtilitesFrag :Fragment(), ImageAttachmentAdapter.ItemClickListener {
    var binding : Dg1TabUtilitiesFragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Dg1TabUtilitiesFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyclerListener = view.findViewById<RecyclerView>(R.id.list_item)
        var eqipmantcheck=false;
        var installationcheck=false;
        var othercheck=false;
        var item_title_eqipment = view.findViewById<TextView>(R.id.item_title_eqipment)
        var collapsing_layout_equipment = view.findViewById<LinearLayout>(R.id.collapsing_layout_equipment)
        var item_collapse_equipment = view.findViewById<LinearLayout>(R.id.item_collapse_equipment)
        // acceptance
        var item_InstallationAcceptance = view.findViewById<TextView>(R.id.item_InstallationAcceptance)
        var collapsing_InstallationAcceptance = view.findViewById<LinearLayout>(R.id.collapsing_InstallationAcceptance)
        var item_collapse = view.findViewById<LinearLayout>(R.id.item_collapse)
//  others
        var collapsing_Maintenance = view.findViewById<LinearLayout>(R.id.collapsing_Maintenance)
        var item_maintenance = view.findViewById<TextView>(R.id.item_maintenance)
        var item_collapse_maintance = view.findViewById<LinearLayout>(R.id.item_collapse_maintance)

        collapsing_layout_equipment.setOnClickListener(){

            if(eqipmantcheck==true)
            {
                eqipmantcheck=false
                Utils.expand(item_collapse_equipment)
                item_title_eqipment?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_arrow_up,0)

            }
            else{
                eqipmantcheck=true
                Utils.collapse(item_collapse_equipment)
                item_title_eqipment?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_arrow_down,0)

            }
        }

        collapsing_InstallationAcceptance.setOnClickListener(){

            if(installationcheck==true)
            {
                installationcheck=false
                Utils.expand(item_collapse)
                item_InstallationAcceptance?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_arrow_up,0)

            }
            else{
                installationcheck=true
                Utils.collapse(item_collapse)
                item_InstallationAcceptance?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_arrow_down,0)

            }
        }
        collapsing_Maintenance.setOnClickListener(){

            if(othercheck==true)
            {
                othercheck=false
                Utils.expand(item_collapse_maintance)
                item_maintenance?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_arrow_up,0)

            }
            else{
                othercheck=true
                Utils.collapse(item_collapse_maintance)
                item_maintenance?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_arrow_down,0)

            }
        }

        var adapter =  ImageAttachmentAdapter(this@DG1TabUtilitesFrag)
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