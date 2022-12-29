package com.smarthub.baseapplication.ui.fragments.task.task_tab

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentSiteLeaseAcquitionBinding
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskAdapter
import com.smarthub.baseapplication.ui.site_agreement.SiteAgreementViewModel



class TaskOPCOEditTab : BaseFragment(), TaskAdapter.TaskLisListener {

    val ARG_PARAM1 = "param1"
    lateinit var fragmentSiteLeaseBinding: FragmentSiteLeaseAcquitionBinding
    lateinit var viewmodel: SiteAgreementViewModel
    lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSiteLeaseBinding = FragmentSiteLeaseAcquitionBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[SiteAgreementViewModel::class.java]
        initializeFragment()
        return fragmentSiteLeaseBinding.root
    }

    private fun initializeFragment() {
        fragmentSiteLeaseBinding.siteLeaseListItem.layoutManager = LinearLayoutManager(requireContext())
        taskAdapter = TaskAdapter(requireActivity(),this@TaskOPCOEditTab)
        fragmentSiteLeaseBinding.siteLeaseListItem.adapter = taskAdapter
        var arraydata = ArrayList<String>()
        arraydata.add("anything")
        arraydata.add("anything")
        arraydata.add("anything")
      //  taskAdapter.setData(arraydata)
        //   viewmodel.fetchData()

        viewmodel.fetchDropDown()
        viewmodel.site_lease_data.observe(requireActivity(), Observer {
            // Data is get from server and ui work will be start from here
            println("this is called data is $it")
            var arraydata = ArrayList<String>()
            arraydata.add(it)
            //taskAdapter.setData(arraydata)
        })

        fragmentSiteLeaseBinding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")
        }

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            TaskOPCOTabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }



    override fun attachmentItemClicked() {

    }

    override fun detailsItemClicked() {

    }

    override fun requestinfoClicked() {

    }

    override fun operationInfoDetailsItemClicked() {

    }

    override fun geoConditionsDetailsItemClicked() {

    }

    override fun siteAccessDetailsItemClicked() {

    }

    override fun EditInstallationAcceptence() {

    }

    override fun EditTowerItem() {

    }

    override fun editPoClicked(position: Int) {

    }

    override fun viewPoClicked(position: Int) {

    }

    override fun editConsumableClicked(position: Int) {

    }

    override fun viewConsumableClicked(position: Int) {

    }

    override fun editOffsetClicked(position: Int) {

    }

    override fun viewOffsetClicked(position: Int) {

    }
}