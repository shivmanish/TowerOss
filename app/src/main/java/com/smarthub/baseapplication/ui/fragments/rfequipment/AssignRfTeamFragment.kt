package com.smarthub.baseapplication.ui.fragments.rfequipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.RfDataListLayoutBinding
import com.smarthub.baseapplication.databinding.SiteAcqTeamNonSwitLayoutBinding
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.rfequipment.adapter.RfSurvayTeamFragAdapter
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AssignRfTeamFragment(var rfSurvey:RfSurvey?, var parentIndex:Int?): BaseFragment(),RfSurvayTeamFragAdapter.RfListListener{
    lateinit var viewmodel: HomeViewModel
    lateinit var binding : RfDataListLayoutBinding
    lateinit var adapter:RfSurvayTeamFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = RfDataListLayoutBinding.inflate(inflater, container, false)
        Toast.makeText(requireContext(),"this is called 3", Toast.LENGTH_SHORT).show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= RfSurvayTeamFragAdapter(this@AssignRfTeamFragment,this@AssignRfTeamFragment,rfSurvey)
        binding.listItem.adapter = adapter

    }

    override fun onDestroy() {
        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true){
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    override fun updateTeamClicked(data: RfSurvey) {

    }




}
