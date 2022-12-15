package com.smarthub.baseapplication.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.FragmentMyTaskHomeBinding
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class MyTeamTaskFragment : Fragment() {

    var homeViewModel : HomeViewModel?=null
    lateinit var binding : FragmentMyTaskHomeBinding
    lateinit var adapterList : MyTaskItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMyTaskHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.taskList.setHasFixedSize(true)
        adapterList = MyTaskItemAdapter()
        binding.taskList.adapter = adapterList

        adapterList.addItem("loading")
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        if (homeViewModel?.myTeamTask?.hasActiveObservers() == true)
            homeViewModel?.myTeamTask?.removeObservers(viewLifecycleOwner)
        homeViewModel?.myTeamTask?.observe(viewLifecycleOwner){
            binding.refreshLayout.isRefreshing = false
            if (it!=null && it.isNotEmpty()){
                val list :ArrayList<Any> = ArrayList()
                list.add("header")
                list.addAll(it)
                adapterList.updateList(list)
            }else{
//                no data found
                adapterList.addItem("no_data")
            }
        }

        binding.refreshLayout.setOnRefreshListener {
            homeViewModel?.fetchHomeData()
        }
    }

}