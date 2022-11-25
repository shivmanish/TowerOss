package com.smarthub.baseapplication.ui.project

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ProjectFragmentLayoutBinding

class ProjectsFragment : Fragment() {
    lateinit var projectFragmentLayoutBinding: ProjectFragmentLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        projectFragmentLayoutBinding = ProjectFragmentLayoutBinding.inflate(inflater)
    setList()

        return projectFragmentLayoutBinding.root
    }

    fun setList(){
        val projectListAdapter = ProjectListAdapter()
        projectFragmentLayoutBinding.projectList.layoutManager = LinearLayoutManager(context)
        projectFragmentLayoutBinding.projectList.adapter = projectListAdapter
        projectFragmentLayoutBinding.add.setOnClickListener{
            showbottomDialouge()
        }

    }

    fun showbottomDialouge(){
        val dialog = BottomSheetDialog(requireActivity(), R.style.NewDialogTask)
        val view = layoutInflater.inflate(R.layout.project_button_sheet_layout, null)
         dialog.window?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false)
        // on below line we are setting
        // content view to our view.
        dialog.setContentView(view)
        // on below line we are calling
        // a show method to display a dialog.
        val recyclerView = view.findViewById<RecyclerView>(R.id.task_list)
        recyclerView!!.layoutManager = GridLayoutManager(context,2)
        recyclerView!!.adapter = TaskAdapter()
        dialog.show()
    }


}