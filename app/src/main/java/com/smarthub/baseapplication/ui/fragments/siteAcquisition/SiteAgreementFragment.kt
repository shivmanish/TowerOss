package com.smarthub.baseapplication.ui.fragments.siteAcquisition

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentSiteLeaseAcquitionBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.model.dropdown.newData.DropDownNewItem
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.taskModel.GeoGraphyLevelPostData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.task.TaskViewModel
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.viewmodels.HomeViewModel


class SiteAgreementFragment(var id: String) : BaseFragment(), SiteAcqsitionFragAdapter.SiteAcqListListener {

    var isDataLoaded = false
    lateinit var viewmodel: HomeViewModel
    lateinit var taskViewmodel: TaskViewModel
//    lateinit var siteLeaseDataAdapter:
    lateinit var adapter:SiteAcqsitionFragAdapter
    lateinit var binding: FragmentSiteLeaseAcquitionBinding
    var geoGraphyLevelList=ArrayList<DropDownItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSiteLeaseAcquitionBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        taskViewmodel = ViewModelProvider(this).get(TaskViewModel::class.java)
        setobserver()
        taskViewmodel.getGeoGraphyData(GeoGraphyLevelPostData(""))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.siteLeaseListItem.layoutManager = LinearLayoutManager(requireContext())
        adapter = SiteAcqsitionFragAdapter(requireContext(),this@SiteAgreementFragment)
        binding.siteLeaseListItem.adapter = adapter
        binding.addMore.setOnClickListener {
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager, "")
        }
        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true) {
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.siteAgreementModel?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SiteAgreemnets Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                hideLoader()
                AppLogger.log("SiteAgreemnets Fragment card Data fetched successfully")
                try {
                    adapter.setData(it.data.SAcqSiteAcquisition)
                } catch (e: java.lang.Exception) {
                    AppLogger.log("SiteAgreemnets Fragment error : ${e.localizedMessage}")
                }
                AppLogger.log("SiteAgreemnets size :${it.data.SAcqSiteAcquisition?.size}")
                isDataLoaded = true
            } else if (it != null) {
                AppLogger.log("SiteAgreemnets Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")

            }
        }
        
        binding.swipingLayout.setOnRefreshListener {
            binding.swipingLayout.isRefreshing=false
            adapter.addLoading()
            viewmodel.fetchSiteAgreementModelRequest(id)
        }
        viewmodel.fetchSiteAgreementModelRequest(id)

        binding.addNew.setOnClickListener {
            val bm = AddNewSiteAcqDialouge(
                object : AddNewSiteAcqDialouge.AddSiteAcqDataListener {
                    override fun addNewData(){
                        showLoader()
                        viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                    }
                })
            bm.show(childFragmentManager,"sdg")
        }
    }

    override fun onViewPageSelected() {
        super.onViewPageSelected()
        if (!isDataLoaded) {
            adapter.addLoading()
            viewmodel.fetchSiteAgreementModelRequest(id)
        }
        AppLogger.log("onViewPageSelected PowerAndFuel")
    }

    override fun onDestroy() {
        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true) {
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    override fun clickedItem(data: NewSiteAcquiAllData,parentIndex:Int) {
        SiteAcqTabActivity.siteacquisition = data
        SiteAcqTabActivity.parentIndex = parentIndex
        requireActivity().startActivity(Intent(requireContext(), SiteAcqTabActivity::class.java))
    }
    fun setobserver(){
        if (taskViewmodel.geoGraphyLevelDataResponse!!.hasActiveObservers())
            taskViewmodel.geoGraphyLevelDataResponse!!.removeObservers(viewLifecycleOwner)
        taskViewmodel.geoGraphyLevelDataResponse!!.observe(viewLifecycleOwner, Observer {
            if (it?.data != null) {
                geoGraphyLevelList.clear()
                var i=0
                for (x in it.data.Data){
                    geoGraphyLevelList.add(DropDownItem(x,"$i"))
                    i+=1
                }
                AppLogger.log("GeoGraphy Level: ${geoGraphyLevelList}")
                AppPreferences.getInstance().saveString(DropDowns.GeographyLevel.name, Gson().toJson(DropDownNewItem(geoGraphyLevelList,DropDowns.GeographyLevel.name,true)))
                AppLogger.log("GeoGraphy Level savePreData: ${AppPreferences.getInstance().getString(DropDowns.GeographyLevel.name)}")
            }else AppLogger.log("Department not fetched")
            // Toast.makeText(requireContext(),"Department not fetched",Toast.LENGTH_LONG).show()

        })
    }
}