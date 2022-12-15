package com.smarthub.baseapplication.ui.fragments.project

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ProjectBottomSheetLayoutBinding
import com.smarthub.baseapplication.databinding.ProjectFragmentLayoutBinding
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class ProjectsFragment : BaseFragment() {

    var homeViewModel : HomeViewModel?=null
    lateinit var binding: ProjectFragmentLayoutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ProjectFragmentLayoutBinding.inflate(inflater)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setList()
    }

    private fun setList(){
        val projectListAdapter = ProjectListAdapter(requireContext())
        binding.projectList.layoutManager = LinearLayoutManager(context)
        binding.projectList.adapter = projectListAdapter
        binding.add.setOnClickListener{
            showBottomDialog()
        }

        if (homeViewModel?.getProjectDataResponse?.hasActiveObservers() == true)
            homeViewModel?.getProjectDataResponse?.removeObservers(viewLifecycleOwner)
        homeViewModel?.getProjectDataResponse?.observe(viewLifecycleOwner){
            binding.refreshLayout.isRefreshing = false
            hideLoader()
            if (it!=null){
                projectListAdapter.updateList(it.data!!)
            }
        }

        showLoader()
        homeViewModel?.fetchProjectsData()
        binding.refreshLayout.setOnRefreshListener {
            homeViewModel?.fetchProjectsData()
        }
    }

    private fun showBottomDialog(){
        val bottomSheetDialogFragment = TemplateTaskBottomSheet(R.layout.project_bottom_sheet_layout, homeViewModel!!)
        bottomSheetDialogFragment.show(childFragmentManager, "category")
    }


}