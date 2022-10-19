package com.smarthub.baseapplication.ui.fragments.sitedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.Visibility
import com.smarthub.baseapplication.databinding.CustomeDetailsFragmentBinding
import com.smarthub.baseapplication.utils.Utils

class CustomeDetailsFragment : Fragment() {

    lateinit var customeDetailsFragmentBinding: CustomeDetailsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customeDetailsFragmentBinding =
            CustomeDetailsFragmentBinding.inflate(inflater, container, false)
        setData()
        return customeDetailsFragmentBinding.root

    }


    fun setData(){
        customeDetailsFragmentBinding.operatornameMain.setOnClickListener(){
            if(customeDetailsFragmentBinding.operatornameCollaps.visibility == GONE){
                Utils.expand(customeDetailsFragmentBinding.operatornameCollaps)
                customeDetailsFragmentBinding.arrowOperator.rotation = 180f
            }else{
                Utils.collapse(customeDetailsFragmentBinding.operatornameCollaps)
                customeDetailsFragmentBinding.arrowOperator.rotation = 0f
            }
        }


        customeDetailsFragmentBinding.loadview.loadmesurmentMain.setOnClickListener(){
            if(customeDetailsFragmentBinding.loadview.loadManagementColaps.visibility == GONE){
                Utils.expand(customeDetailsFragmentBinding.loadview.loadManagementColaps)
                customeDetailsFragmentBinding.loadview.loadmanagementArrow.rotation = 180f
            }else{
                Utils.collapse(customeDetailsFragmentBinding.loadview.loadManagementColaps)
                customeDetailsFragmentBinding.loadview.loadmanagementArrow.rotation = 0f
            }
        }


    }


}