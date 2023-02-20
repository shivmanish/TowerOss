package com.smarthub.baseapplication.ui.fragments.task.task_tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentSiteLeaseAcquitionBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel


class TaskOPCOTabFragment(var siteID: String) : BaseFragment(), TaskAdapter.TaskLisListener {

    lateinit var fragmentSiteLeaseBinding: FragmentSiteLeaseAcquitionBinding
    lateinit var viewmodel: HomeViewModel
    lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSiteLeaseBinding = FragmentSiteLeaseAcquitionBinding.inflate(inflater, container, false)
        fragmentSiteLeaseBinding.siteLeaseListItem.layoutManager = LinearLayoutManager(requireContext())
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return fragmentSiteLeaseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskAdapter = TaskAdapter(requireActivity(),this@TaskOPCOTabFragment)
        fragmentSiteLeaseBinding.siteLeaseListItem.adapter = taskAdapter
        if (viewmodel.opcoTenencyModelResponse?.hasActiveObservers() == true)
            viewmodel.opcoTenencyModelResponse?.removeObservers(viewLifecycleOwner)
        viewmodel.opcoTenencyModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it!=null && it.status == Resource.Status.SUCCESS){
                AppLogger.log("TaskOPCoTab Fragment Data fetched successfully")
                try {
                    taskAdapter.setValueData(it.data?.item?.get(0)?.Operator?.get(0)?.Opcoinfo?.get(0))
                }catch (e:Exception){
                    AppLogger.log("data fetched for opcoInfo")
                    Toast.makeText(requireContext(),"data fetched for opcoInfo",Toast.LENGTH_SHORT).show()

                }
            }else if (it!=null) {
                Toast.makeText(requireContext(),"TaskOPCoTab Fragment error :${it.message}", Toast.LENGTH_SHORT).show()
            }else{
                AppLogger.log("TaskOPCoTab Fragment Something went wrong")
                Toast.makeText(requireContext(),"TaskOPCoTab Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        fragmentSiteLeaseBinding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")
        }
    }

    override fun onDestroy() {
        if (viewmodel.opcoTenencyModelResponse?.hasActiveObservers() == true)
            viewmodel.opcoTenencyModelResponse?.removeObservers(viewLifecycleOwner)
        super.onDestroy()
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