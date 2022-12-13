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
import com.smarthub.baseapplication.databinding.ProjectButtonSheetLayoutBinding
import com.smarthub.baseapplication.databinding.ProjectFragmentLayoutBinding
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class ProjectsFragment : BaseFragment() {

    var homeViewModel : HomeViewModel?=null
    lateinit var projectFragmentLayoutBinding: ProjectFragmentLayoutBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        projectFragmentLayoutBinding = ProjectFragmentLayoutBinding.inflate(inflater)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return projectFragmentLayoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setList()
    }

    private fun setList(){
        val projectListAdapter = ProjectListAdapter()
        projectFragmentLayoutBinding.projectList.layoutManager = LinearLayoutManager(context)
        projectFragmentLayoutBinding.projectList.adapter = projectListAdapter
        projectFragmentLayoutBinding.add.setOnClickListener{
            showBottomDialog()
        }

        if (homeViewModel?.getProjectDataResponse?.hasActiveObservers() == true)
            homeViewModel?.getProjectDataResponse?.removeObservers(viewLifecycleOwner)
        homeViewModel?.getProjectDataResponse?.observe(viewLifecycleOwner){
            hideLoader()
            if (it!=null){
                projectListAdapter.updateList(it.data!!)
            }
        }

        showLoader()
        homeViewModel?.fetchProjectsData()
    }

    private fun showBottomDialog(){
        val dialog = BottomSheetDialog(requireActivity(), R.style.NewDialogTask)
        val view : View = layoutInflater.inflate(R.layout.project_button_sheet_layout, null)
        var dialogBinding = ProjectButtonSheetLayoutBinding.bind(view)
         dialog.window?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true)
        // on below line we are setting
        // content view to our view.
        dialog.setContentView(view)
        // on below line we are calling
        // a show method to display a dialog.
        dialogBinding.cancel.setOnClickListener {
            dialog.dismiss()
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.task_list)
        recyclerView!!.layoutManager = GridLayoutManager(context,2)
        recyclerView.adapter = TaskAdapter()
        dialog.show()
    }


}