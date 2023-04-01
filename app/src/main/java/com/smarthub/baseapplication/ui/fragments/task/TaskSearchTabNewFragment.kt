package com.smarthub.baseapplication.ui.fragments.task

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.patrollerapp.db.LatlongData
import com.example.patrollerapp.homepage.HomePage
import com.example.patrollerapp.util.LocationService
import com.example.patrollerapp.util.PatrollerPriference
import com.example.patrollerapp.util.Util
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.FragmentSearchTaskBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.AllsiteInfoDataModel
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAllData
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignDataItem
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.QATMainLaunch
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel
import com.smarthub.baseapplication.model.workflow.TaskDataListItem
import com.smarthub.baseapplication.model.workflow.TaskDataUpdateModel
import com.smarthub.baseapplication.ui.alert.dialog.ChatFragment
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.noc.NocCompPageAdapter
import com.smarthub.baseapplication.ui.fragments.noc.TaskNocDataAdapter
import com.smarthub.baseapplication.ui.fragments.noc.TaskNocDataAdapterListener
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.OpcoTenancyActivity
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.OpcoTenancyPageAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.TaskCustomerDataAdapterListener
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.TaskOpcoTanancyFragAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.PowerDesignDetailPageAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.PowerDesignDetailsActivity
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.PlanDesignAdapterListener
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.TaskPlanDesignAdapter
import com.smarthub.baseapplication.ui.fragments.qat.OpenQatFragment
import com.smarthub.baseapplication.ui.fragments.qat.SubmitQatFragment
import com.smarthub.baseapplication.ui.fragments.qat.adapter.QatMainAdapterListener
import com.smarthub.baseapplication.ui.fragments.qat.adapter.TaskQATListAdapter
import com.smarthub.baseapplication.ui.fragments.qat.adapter.TaskQatViewPagerAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestActivity
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicePageAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesDataAdapterListener
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.TaskServicesDataAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.SiteAcqTabActivity
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.TaskSiteAcqsitionFragAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.SiteAcquisitionTabAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.SiteAcquisitionTaskTabAdapter
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskSiteInfoAdapter
import com.smarthub.baseapplication.ui.fragments.task.editdialog.SiteInfoEditBottomSheet
import com.smarthub.baseapplication.ui.fragments.utilites.SMPSDetailsActivity
import com.smarthub.baseapplication.ui.fragments.utilites.ac.ACDetailsActivity
import com.smarthub.baseapplication.ui.fragments.utilites.ac.adapters.ACViewpagerAdapter
import com.smarthub.baseapplication.ui.fragments.utilites.adapter.SMPSViewpagerAdapter
import com.smarthub.baseapplication.ui.fragments.utilites.adapter.TaskUtilitesNocDataAdapterListener
import com.smarthub.baseapplication.ui.fragments.utilites.adapter.TaskUtilitiesNocDataAdapter
import com.smarthub.baseapplication.ui.fragments.utilites.batteryBank.BatteryBankDetailsActivity
import com.smarthub.baseapplication.ui.fragments.utilites.batteryBank.adapters.BatterryBankViewpagerAdapter
import com.smarthub.baseapplication.ui.fragments.utilites.dg.DGDetailsActivity
import com.smarthub.baseapplication.ui.fragments.utilites.dg.adapters.DGViewpagerAdapter
import com.smarthub.baseapplication.ui.fragments.utilites.fireExtinguisher.FireExtinguisherActivity
import com.smarthub.baseapplication.ui.fragments.utilites.fireExtinguisher.adapters.FireExtViewpagerAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.viewmodels.TaskViewModel

class TaskSearchTabNewFragment(
    var siteID: String?, var taskId: String, var taskDetailId: String?,
    var lattitude: String, var longitude: String, var tempWhere: String,var isFancing:Boolean,
    var fancingDistance :Double,var Trackingflag:Boolean) : BaseFragment(),
    TaskSiteInfoAdapter.TaskSiteInfoListener, ServicesDataAdapterListener {
    private lateinit var binding: FragmentSearchTaskBinding
    lateinit var taskViewModel: TaskViewModel
    var TaskTabListmodel: TaskDropDownModel?=null
    lateinit var homeViewModel: HomeViewModel
    var taskDetailData: TaskDataListItem? = null
    var taskAndCardList: ArrayList<String> = ArrayList()

    //    var lat = 19.25382218490181
    var mLocationService: LocationService = LocationService()
    lateinit var mServiceIntent: Intent

    //    var long = 72.98213045018673
//    val isFancing: Boolean = false
//    val fancingDistance: Double = 0.0
    var radius = "2"
    var previousListSize: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
//        siteID = "1526"
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        val json = Utils.getJsonDataFromAsset(requireContext(), "taskDropDown.json")
        TaskTabListmodel = Gson().fromJson(json, TaskDropDownModel::class.java)
        tempWhere = tempWhere.replace("[", "")
        tempWhere = tempWhere.replace("]", "")
        taskAndCardList.addAll(tempWhere.split(","))
        binding = FragmentSearchTaskBinding.inflate(inflater, container, false)
        initVariable()
        AppPreferences.getInstance().saveTaskId(taskId)
        return binding.root
    }

    var list: ArrayList<Any> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataObserver()
        AppLogger.log("TaskTAbList===>: ${Gson().toJson(TaskTabListmodel)}")
        binding.collapsingLayout.tag = false
        binding.horizontalOnlyList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.trackinglayout.visibility = View.GONE
        binding.dropdownImg.setOnClickListener {
            binding.collapsingLayout.tag = !(binding.collapsingLayout.tag as Boolean)
            if (binding.collapsingLayout.tag as Boolean) {
                binding.collapsingLayout.visibility = View.VISIBLE
                binding.topLine.visibility = View.VISIBLE
                binding.dropdownImg.setImageResource(R.drawable.down_arrow)
            } else {
                binding.collapsingLayout.visibility = View.GONE
                binding.topLine.visibility = View.GONE
                binding.dropdownImg.setImageResource(R.drawable.ic_arrow_up_faq)
            }
        }
        binding.mapView.setOnClickListener {
            mapView()
        }
        binding.closeBtn.setOnClickListener {
            if (isFancing) {
                val userlatlongdata = LatlongData()
                val start_lattitiude_string =
                    PatrollerPriference(requireContext()).getStartLattitude()
                val start_longitude_string =
                    PatrollerPriference(requireContext()).getStartLongitude().toDouble()
                if (start_lattitiude_string.equals("Na", true)) {
                    Snackbar.make(binding.name, "You are not in feance !", Snackbar.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                userlatlongdata.start_lattitiude = start_lattitiude_string.toDouble()
                userlatlongdata.start_longitude = start_longitude_string.toDouble()

                var userPosition =
                    LatLng(userlatlongdata.start_lattitiude, userlatlongdata.start_longitude)
                var sitePosition = LatLng(lattitude.toDouble(), longitude.toDouble())
                val distance_btwn_user_and_site =
                    Util.getDistanceFromLatlongmanually(userPosition, sitePosition)
                if (distance_btwn_user_and_site <= fancingDistance) {
                    //Close Request Hit
                } else {
                    Snackbar.make(binding.name, "You are not in feance !", Snackbar.LENGTH_SHORT)
                        .show()
                }
            } else {
                //Close Request Hit
            }

        }
        binding.start.setOnClickListener {
            if (binding.start.text.toString().equals("Start", true)) {
                PatrollerPriference(requireContext()).setstartTime(System.currentTimeMillis())
                PatrollerPriference(requireContext()).setPauseTimestamp(0)
                PatrollerPriference(requireContext()).setTotalPauseTime(0)
                binding.start.text = "Stop"
                PatrollerPriference(requireContext()).setPtrollingStatus(PatrollerPriference.PATROLING_STATUS_running)
                PatrollerPriference(requireContext()).settime("")
                PatrollerPriference(requireContext()).setStartLattitude("Na")
                PatrollerPriference(requireContext()).setStartLongitude("Na")
//                startServiceBackground()
            } else {
//                mapView()
                if (mServiceIntent != null) {
                    PatrollerPriference(requireContext()).setPtrollingStatus(PatrollerPriference.PATROLING_STATUS_STOP)
                    LocationService.is_canceled_by_me = true
                    requireContext().stopService(mServiceIntent)
                    PatrollerPriference(requireContext()).settime("")
                    binding.start.text = "Start"
                }

            }
        }
        binding.messages.setOnClickListener {
            val chatfragment = ChatFragment()
            val bundle = Bundle()
            bundle.putString("reportedBy", "7269024641")
            bundle.putString("id", "93")
            chatfragment.arguments = bundle
            addFragment(chatfragment)
        }
        if (taskViewModel.taskDataList?.hasActiveObservers() == true)
            taskViewModel.taskDataList?.removeObservers(this)
        taskViewModel.taskDataList?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
//                showLoader()
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                if (it.data.isNotEmpty()) {
                    AppLogger.log("fetched task data =====> : ${Gson().toJson(it.data[0])}")
                    taskDetailData = it.data[0]
//                    set variables here
//                    isFancing = taskDetailData.
                    setParentData()
                } else
                    AppLogger.log("not any assigned task found at task Id $taskDetailId")
            } else {
                AppLogger.log("Something went wrong in TaskSearchTabNewFragment")
            }
        }
    }

    fun setParentData() {
        AppLogger.log("Where Tab list====>:$tempWhere")
        var subTabList:ArrayList<String> = ArrayList()
//        val splittedData = tempWhere.split(",") as ArrayList
        val splittedData = findTaskSubtabList(taskDetailData).split(",")
        AppLogger.log("Where Tab list spiletted====>:$splittedData")
        var parentId: Int = 0
        if (splittedData.isNotEmpty()) {
            val firstIdx = splittedData[0]
            AppLogger.log("Where Tab list first index data====>:$firstIdx")
            if (firstIdx.isNotEmpty()) {
                try {
                    parentId = firstIdx.toInt().div(10)
                }catch (e:Exception){
                    AppLogger.log("e:${e.localizedMessage}")
                }
                AppLogger.log("parentId=====>:${parentId}")
                if (TaskTabListmodel!=null){
                    for (item in TaskTabListmodel!!){
                        if (item.id==parentId){
                            for (subitem in item.tabs){
                                if (splittedData.contains(subitem.id.toString()))
                                {
                                    subTabList.add(subitem.id.toString())
                                }
                            }
                        }
                    }
                }
            }
            AppLogger.log("subTask list od subTab====>:$subTabList")
            when (parentId){
                1->{
                    setUpOpcoData()
                    AppLogger.log("Selected TAb is OpcoTenency")
                }
                2->{
                    setUpSiteAcqusitionData(splittedData as ArrayList<String>)
                }
                3->{
                    setUpUtilityUqipData()
                    AppLogger.log("Selected TAb is Utility Equipments")
                }
                4->{
                    AppLogger.log("Selected TAb is Site Info")
                }
                5->{
                    setUpPnanigAndDesignData()
                    AppLogger.log("Selected TAb is Planning & Design")
                }
                6->{
                    setUpNocComplianceData()
                    AppLogger.log("Selected TAb is NOC & Compliance")
                }
                7->{
                    AppLogger.log("Selected TAb is Tower & Civil Infra")
                }
                8->{
                    AppLogger.log("Selected TAb is Power & Fuel")
                }
                9->{
                    setUpServiceRequestData()
                    AppLogger.log("Selected TAb is Service Request")
                }
                else ->{
                    setUpNocComplianceData()
                    AppLogger.log("Selected TAb is NOC & Compliance by default for testing")
                }
            }
        }
    }

    fun initVariable() {
        mServiceIntent = Intent(requireContext(), mLocationService.javaClass)
        mLocationService = LocationService()
        Util.updateLocation(requireContext())
    }

    override fun onResume() {
        super.onResume()
        if ((PatrollerPriference(requireContext()).getPtrollingStatus()).equals(
                PatrollerPriference.PATROLING_STATUS_PAUSE, ignoreCase = true
            )
        ) {
            binding.start.text = "Stop"
//            homePageBinding.pause.visibility = View.VISIBLE
//            homePageBinding.stop.visibility = View.VISIBLE
//            homePageBinding.pause.text = "Resume"

        } else if ((PatrollerPriference(requireContext()).getPtrollingStatus()).equals(
                PatrollerPriference.PATROLING_STATUS_running, ignoreCase = true
            )
        ) {

            startServiceBackground()
            binding.start.text = "Stop"
//            homePageBinding.pause.visibility = View.VISIBLE
//            homePageBinding.stop.visibility = View.VISIBLE
//            homePageBinding.pause.text = "Pause"

        } else if ((PatrollerPriference(requireContext()).getPtrollingStatus()).equals(
                PatrollerPriference.PATROLING_STATUS_STOP, ignoreCase = true
            )
        ) {
            binding.start.text = "Start"

        }
    }

    private fun startServiceBackground() {

        if (!Util.isMyServiceRunning(mLocationService.javaClass, requireActivity())) {
            requireActivity().startService(mServiceIntent)
            Toast.makeText(
                requireContext(),
                getString(com.example.trackermodule.R.string.service_start_successfully),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                requireContext(),
                getString(com.example.trackermodule.R.string.service_already_running),
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun setDataObserver() {
        taskViewModel.fetchTaskDetails(taskDetailId)
        if (homeViewModel.siteInfoDataResponse?.hasActiveObservers() == true)
            homeViewModel.siteInfoDataResponse?.removeObservers(viewLifecycleOwner)
        homeViewModel.siteInfoDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
//                showLoader()
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                (requireActivity() as BaseActivity).hideLoader()
                val data: AllsiteInfoDataModel = it.data
                if (data.Siteaddress != null && data.Siteaddress?.isNotEmpty() == true) {
                    val siteData = data.Siteaddress!![0]
                    lattitude = siteData.locLatitude
                    longitude = siteData.locLongitude
                    if(Trackingflag){
                        binding.trackinglayout.visibility = View.VISIBLE
                    }else{
                        binding.trackinglayout.visibility = View.GONE
                    }
                    AppLogger.log("fetched latitude:${lattitude},longitude:$longitude")
                } else {
                    AppLogger.log("Site address not fetched")
                }
            } else if (it != null) {
                AppLogger.log("SiteInfoNewFragment error :${it.message}")
            } else {
                AppLogger.log("SiteInfoNewFragment Something went wrong")
            }
        }
        homeViewModel.siteInfoRequestAll(AppController.getInstance().taskSiteId)
//        siteDetailViewModel.fetchDropDown()
    }

    fun setUpServiceRequestData() {
        if (homeViewModel.serviceRequestModelResponse?.hasActiveObservers() == true) {
            homeViewModel.serviceRequestModelResponse?.removeObservers(viewLifecycleOwner)
        }
        val serviceFragAdapterAdapter =
            TaskServicesDataAdapter(this@TaskSearchTabNewFragment, siteID.toString())
        binding.horizontalOnlyList.adapter = serviceFragAdapterAdapter

        homeViewModel.serviceRequestModelResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                return@observe
            }
            (requireActivity() as BaseActivity).hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.ServiceRequestMain != null && it.data.ServiceRequestMain.isNotEmpty()) {
                AppLogger.log("Service request Fragment card Data fetched successfully")
                val listData = it.data.ServiceRequestMain as ArrayList<ServiceRequestAllDataItem>
                serviceFragAdapterAdapter.setData(listData)
                AppLogger.log("size :${it.data.ServiceRequestMain.size}")

                if (listData.isNotEmpty())
                    clickedItem(listData[0], siteID.toString())
                isDataLoaded = true
            } else if (it != null) {
                Toast.makeText(
                    requireContext(),
                    "Service request Fragment error :${it.message}, data : ${it.data}",
                    Toast.LENGTH_SHORT
                ).show()
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("Service Request Fragment Something went wrong")
                Toast.makeText(
                    requireContext(),
                    "Service Request Fragment Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        (requireActivity() as BaseActivity).showLoader()
        homeViewModel.serviceRequestAll("1526")
    }

    fun setUpPnanigAndDesignData() {
        if (homeViewModel.serviceRequestModelResponse?.hasActiveObservers() == true) {
            homeViewModel.serviceRequestModelResponse?.removeObservers(viewLifecycleOwner)
        }
        val serviceFragAdapterAdapter = TaskPlanDesignAdapter(object : PlanDesignAdapterListener {
            override fun clickedItem(data: PlanAndDesignDataItem?, Id: String, index: Int) {
                //this is for the listiner

                PowerDesignDetailsActivity.Id = Id
                PowerDesignDetailsActivity.planDesigndata = data
                binding.viewpager.adapter = PowerDesignDetailPageAdapter(childFragmentManager, data, Id)
                binding.tabs.setupWithViewPager(binding.viewpager)
                setViewPager()
            }
        }, siteID.toString())
        binding.horizontalOnlyList.adapter = serviceFragAdapterAdapter
        homeViewModel?.PlanDesignModelResponse?.observe(viewLifecycleOwner, Observer {

            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("planDesign Fragment card Data fetched successfully")
                serviceFragAdapterAdapter.setData(it.data.PlanningAndDesign!!)
            } else if (it != null) {
                Toast.makeText(
                    requireContext(),
                    "planDesign Fragment error :${it.message}, data : ${it.data}",
                    Toast.LENGTH_SHORT
                ).show()
                AppLogger.log("planDesign Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("planDesign Fragment Something went wrong")
                Toast.makeText(requireContext(),
                    "planDesign Fragment Something went wrong",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        })
/*
        homeViewModel.serviceRequestModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            (requireActivity() as BaseActivity).hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.ServiceRequestMain!=null && it.data.ServiceRequestMain.isNotEmpty()){
                AppLogger.log("Service request Fragment card Data fetched successfully")
                val listData = it.data.ServiceRequestMain as ArrayList<ServiceRequestAllDataItem>
                serviceFragAdapterAdapter.setData(listData)
                AppLogger.log("size :${it.data.ServiceRequestMain.size}")

                if (listData.isNotEmpty())
                    clickedItem(listData[0],siteID.toString())
                isDataLoaded = true
            }
            else if (it!=null) {
                Toast.makeText(requireContext(),"Service request Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("Service Request Fragment Something went wrong")
                Toast.makeText(requireContext(),"Service Request Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
*/
        (requireActivity() as BaseActivity).showLoader()
        homeViewModel.planAndDesignRequestAll(siteID.toString())
    }

    fun setUpQatData() {
        if (homeViewModel.QatModelResponse?.hasActiveObservers() == true) {
            homeViewModel.QatModelResponse?.removeObservers(viewLifecycleOwner)
        }
        val serviceFragAdapterAdapter =
            TaskQATListAdapter(requireContext(), object : QatMainAdapterListener {
                override fun clickedItem(data: QATMainLaunch?, Id: String, index: Int) {
                    //this is for the listiner

//                QATCheckActivity.Id=Id
//                QATCheckActivity.planDesigndata=data
                    val adapterviewpager = TaskQatViewPagerAdapter(childFragmentManager)
                    adapterviewpager.addFragment(OpenQatFragment(), "Open QAT")
                    adapterviewpager.addFragment(SubmitQatFragment(), "Submitted QAT")
                    binding.viewpager.adapter = adapterviewpager
                    binding.tabs.setupWithViewPager(binding.viewpager)
                    setViewPager()
                }


            }, siteID.toString())
        binding.horizontalOnlyList.adapter = serviceFragAdapterAdapter
        homeViewModel?.QatModelResponse?.observe(viewLifecycleOwner, Observer {

            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.item != null && it.data.item?.isNotEmpty() == true) {
                AppLogger.log("Service request Fragment card Data fetched successfully")
                isDataLoaded = true
                serviceFragAdapterAdapter.setData(it.data.item!![0].QATMainLaunch)
                AppLogger.log("size :${it.data.item?.size}")
            } else if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.itemNew != null && it.data.itemNew?.isNotEmpty() == true) {
                AppLogger.log("Service request Fragment card Data fetched successfully")
                it.data.item = it.data.itemNew
//                qatMainModel = it.data
                isDataLoaded = true
                serviceFragAdapterAdapter.setData(it.data.itemNew!![0].QATMainLaunch)
                AppLogger.log("size :${it.data.itemNew!![0].QATMainLaunch.size}")
            } else if (it != null) {
                Toast.makeText(requireContext(),
                    "Service request Fragment error :${it.message}, data : ${it.data}",
                    Toast.LENGTH_SHORT).show()
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            }
        })
/*
        homeViewModel.serviceRequestModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            (requireActivity() as BaseActivity).hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.ServiceRequestMain!=null && it.data.ServiceRequestMain.isNotEmpty()){
                AppLogger.log("Service request Fragment card Data fetched successfully")
                val listData = it.data.ServiceRequestMain as ArrayList<ServiceRequestAllDataItem>
                serviceFragAdapterAdapter.setData(listData)
                AppLogger.log("size :${it.data.ServiceRequestMain.size}")

                if (listData.isNotEmpty())
                    clickedItem(listData[0],siteID.toString())
                isDataLoaded = true
            }
            else if (it!=null) {
                Toast.makeText(requireContext(),"Service request Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("Service Request Fragment Something went wrong")
                Toast.makeText(requireContext(),"Service Request Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
*/
        (requireActivity() as BaseActivity).showLoader()
        homeViewModel.qatMainRequestAll("1526")
    }

    private fun setUpNocComplianceData() {
        AppLogger.log("opened task Site ID: ${AppController.getInstance().taskSiteId}")
        AppLogger.log("opened task details : ======> ${Gson().toJson(taskDetailData)}")
        val nocDataAdapterListener = TaskNocDataAdapter(requireContext(), object :
            TaskNocDataAdapterListener {
            override fun clickedItem(data: NocCompAllData, id: String, dataIndex: Int?) {
                binding.viewpager.adapter =
                    NocCompPageAdapter(childFragmentManager, data, dataIndex)
                binding.tabs.setupWithViewPager(binding.viewpager)
                setViewPager()
            }

            override fun addNew() {
                homeViewModel.updateNocAndComp(NocCompAllData())
                if (homeViewModel.updateNocCompDataResponse?.hasActiveObservers() == true) {
                    homeViewModel.updateNocCompDataResponse?.removeObservers(viewLifecycleOwner)
                }
                homeViewModel.updateNocCompDataResponse?.observe(viewLifecycleOwner) {
                    if (it != null && it.status == Resource.Status.LOADING) {
                        AppLogger.log("TaskSearchTabNewFragment data creating in progress ")
                        return@observe
                    }
                    if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.NOCCompliance == 200) {
                        AppLogger.log("TaskSearchTabNewFragment card Data Created successfully")
                        taskDetailData?.ModuleId=it.data.data.cardId.toString()
                        taskDetailData?.ModuleName=it.data.data.name
                        val tempTaskDataUpdate=TaskDataUpdateModel()
                        tempTaskDataUpdate.ModuleId=it.data.data.cardId
                        tempTaskDataUpdate.ModuleName=it.data.data.name
                        tempTaskDataUpdate.updatemodule=taskDetailData?.id
                        taskViewModel.updateTaskDataWithDataId(tempTaskDataUpdate,taskDetailData?.id!!)
                    }
                    else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                        hideLoader()
                        AppLogger.log("TaskSearchTabNewFragment Something went wrong in creating Data")
                    } else if (it != null) {
                        AppLogger.log("TaskSearchTabNewFragment error :${it.message}, data : ${it.data}")
                    } else {
                        AppLogger.log("TaskSearchTabNewFragment Something went wrong in creating Data")

                    }
                }
            }
        }, taskDetailData)
        binding.horizontalOnlyList.adapter = nocDataAdapterListener

        if (homeViewModel.NocAndCompModelResponse?.hasActiveObservers() == true) {
            homeViewModel.NocAndCompModelResponse?.removeObservers(viewLifecycleOwner)
        }
        homeViewModel.NocAndCompModelResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                return@observe
            }
            (requireActivity() as BaseActivity).hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                AppLogger.log("NocAndComp Fragment card Data fetched successfully")
                if (taskDetailData?.ModuleId!="0" && it.data.NOCCompliance?.size!!>0){
                    var data:NocCompAllData?=null
                    var dataIndex:Int?=null
                    for (item in it.data.NOCCompliance!!){
                        if (item.id.toString()==taskDetailData?.ModuleId){
                            data=item
                            dataIndex=it.data.NOCCompliance?.indexOf(item)
                            break
                        }
                    }
                    binding.viewpager.adapter = NocCompPageAdapter(childFragmentManager, data,dataIndex)
                    binding.tabs.setupWithViewPager(binding.viewpager)
                    setViewPager()
                }
//                if (previousListSize!=-1 && previousListSize<it.data.NOCCompliance?.size!! && it.data.NOCCompliance?.size!!>0){
//                    val newAddedData=it.data.NOCCompliance?.get(it.data.NOCCompliance?.size!!.minus(1))
//                    taskDetailData?.ModuleId=newAddedData?.id.toString()
//                    nocDataAdapterListener.setData(newAddedData!!)
//                        // update task api
//                    }
                nocDataAdapterListener.setData(it.data.NOCCompliance!!)
                previousListSize=it.data.NOCCompliance?.size!!

                AppLogger.log("size :${it.data.NOCCompliance?.size}")
                isDataLoaded = true
            }
            else if (it!=null) {
                Toast.makeText(requireContext(),"NocAndComp Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("NocAndComp Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("NocAndComp Fragment Something went wrong")
                Toast.makeText(requireContext(),"NocAndComp Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        if (taskViewModel.updateTaskDataResponse?.hasActiveObservers() == true) {
            taskViewModel.updateTaskDataResponse?.removeObservers(viewLifecycleOwner)
        }
        taskViewModel.updateTaskDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("TaskSearchTabNewFragment TaskData Updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS  ) {
                AppLogger.log("TaskSearchTabNewFragment Task Data Updated successfully")

                taskViewModel.fetchTaskDetails(taskDetailId)
                homeViewModel.NocAndCompRequestAll(AppController.getInstance().taskSiteId)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("TaskSearchTabNewFragment Something went wrong in Updating Task Data")
            }
            else if (it != null) {
                AppLogger.log("TaskSearchTabNewFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("TaskSearchTabNewFragment Something went wrong in Updating Task Data")

            }
        }
        (requireActivity() as BaseActivity).showLoader()
        homeViewModel.NocAndCompRequestAll(AppController.getInstance().taskSiteId)
    }

    fun setUpSiteAcqusitionData(subTaskTabList:ArrayList<String>) {
        AppLogger.log("opened task Site ID: ${AppController.getInstance().taskSiteId}")
        AppLogger.log("opened task details : ======> ${Gson().toJson(taskDetailData)}")
        AppLogger.log("Site Acq subTab List : ======> $subTaskTabList")
        val serviceFragAdapterAdapter = TaskSiteAcqsitionFragAdapter(requireContext(),taskDetailData,object : TaskSiteAcqsitionFragAdapter.SiteAcqListListener {
            override fun clickedItem(data: NewSiteAcquiAllData, parentIndex: Int) {
                SiteAcqTabActivity.siteacquisition = data
                SiteAcqTabActivity.parentIndex = parentIndex
                binding.viewpager.adapter = SiteAcquisitionTaskTabAdapter(childFragmentManager, data,parentIndex,subTaskTabList)
                binding.tabs.setupWithViewPager(binding.viewpager)
                setViewPager()
            }

            override fun addNew() {
                showLoader()
                if (homeViewModel.updateSiteAcqDataResponse?.hasActiveObservers() == true){
                    homeViewModel.updateSiteAcqDataResponse?.removeObservers(viewLifecycleOwner)
                }
                homeViewModel.updateSiteAcqDataResponse?.observe(viewLifecycleOwner) {
                    if (it != null && it.status == Resource.Status.LOADING) {
                        AppLogger.log("TaskSearchTabNewFragment SiteAcq data creating in progress ")
                        return@observe
                    }
                    if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SAcqSiteAcquisition==200 ) {
                        AppLogger.log("TaskSearchTabNewFragment card SiteAcq Data Created successfully")
                        taskDetailData?.ModuleId=it.data.data.cardId.toString()
                        taskDetailData?.ModuleName=it.data.data.name
                        val tempTaskDataUpdate=TaskDataUpdateModel()
                        tempTaskDataUpdate.ModuleId=it.data.data.cardId
                        tempTaskDataUpdate.ModuleName=it.data.data.name
                        tempTaskDataUpdate.updatemodule=taskDetailData?.id
                        taskViewModel.updateTaskDataWithDataId(tempTaskDataUpdate,taskDetailData?.id!!)
                    }
                    else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                        hideLoader()
                        AppLogger.log("TaskSearchTabNewFragment Something went wrong in creating SiteAcq Data")
                    }
                    else if (it != null) {
                        AppLogger.log("TaskSearchTabNewFragment error :${it.message}, data : ${it.data}")
                    } else {
                        AppLogger.log("TaskSearchTabNewFragment Something went wrong in creating SiteAcq Data")

                    }
                }
                homeViewModel.updateSiteAcq(UpdateSiteAcquiAllData())
            }

        })
        binding.horizontalOnlyList.adapter = serviceFragAdapterAdapter
        if (homeViewModel.siteAgreementModel?.hasActiveObservers() == true) {
            homeViewModel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        homeViewModel.siteAgreementModel?.observe(viewLifecycleOwner, Observer {
            hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("planDesign Fragment card Data fetched successfully")
                if (taskDetailData?.ModuleId!="0" && it.data.SAcqSiteAcquisition?.size!!>0){
                    var data:NewSiteAcquiAllData?=null
                    var dataIndex:Int?=null
                    for (item in it.data.SAcqSiteAcquisition!!){
                        if (item.id.toString()==taskDetailData?.ModuleId){
                            data=item
                            dataIndex=it.data.SAcqSiteAcquisition?.indexOf(item)

                            binding.viewpager.adapter = SiteAcquisitionTaskTabAdapter(childFragmentManager, data,dataIndex!!,subTaskTabList)
                            binding.tabs.setupWithViewPager(binding.viewpager)
                            setViewPager()
                            break
                        }
                    }
                }
                serviceFragAdapterAdapter.setData(it.data.SAcqSiteAcquisition)
                previousListSize=it.data.SAcqSiteAcquisition?.size!!

            } else if (it != null) {
                Toast.makeText(
                    requireContext(),
                    "planDesign Fragment error :${it.message}, data : ${it.data}",
                    Toast.LENGTH_SHORT
                ).show()
                AppLogger.log("planDesign Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("planDesign Fragment Something went wrong")
                Toast.makeText(requireContext(), "planDesign Fragment Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        if (taskViewModel.updateTaskDataResponse?.hasActiveObservers() == true){
            taskViewModel.updateTaskDataResponse?.removeObservers(viewLifecycleOwner)
        }
        taskViewModel.updateTaskDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("TaskSearchTabNewFragment TaskData Updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS  ) {
                AppLogger.log("TaskSearchTabNewFragment Task Data Updated successfully")

                taskViewModel.fetchTaskDetails(taskDetailId)
                homeViewModel.fetchSiteAgreementModelRequest(AppController.getInstance().taskSiteId)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("TaskSearchTabNewFragment Something went wrong in Updating Task Data")
            }
            else if (it != null) {
                AppLogger.log("TaskSearchTabNewFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("TaskSearchTabNewFragment Something went wrong in Updating Task Data")

            }
        }
        showLoader()
        homeViewModel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
    }

    fun setUpUtilityUqipData() {
        if (homeViewModel.utilityEquipResponse?.hasActiveObservers() == true) {
            homeViewModel.utilityEquipResponse?.removeObservers(viewLifecycleOwner)
        }
        val serviceFragAdapterAdapter =
            TaskUtilitiesNocDataAdapter(object : TaskUtilitesNocDataAdapterListener {
                /*override fun clickedItem(data: NewSiteAcquiAllData, parentIndex: Int) {
                    //this is for the listiner

                    SiteAcqTabActivity.siteacquisition = data
                    SiteAcqTabActivity.parentIndex = parentIndex
                    binding.viewpager.adapter = SiteAcquisitionTabAdapter(childFragmentManager, data,parentIndex)
                    binding.tabs.setupWithViewPager(binding.viewpager)
                    setViewPager()
                }*/
                override fun SMPSItemClicked(data: UtilityEquipmentAllData?) {
                    SMPSDetailsActivity.utilitySmpsData = data
                    binding.viewpager.adapter = SMPSViewpagerAdapter(childFragmentManager,
                        data!!.UtilityEquipmentSmps,
                        data!!.id)
                    binding.tabs.setupWithViewPager(binding.viewpager)
                    setViewPager()
                }

                override fun BateeryItemClicked(data: UtilityEquipmentAllData?) {
                    BatteryBankDetailsActivity.utilityData = data
                    binding.viewpager.adapter = BatterryBankViewpagerAdapter(childFragmentManager,
                        data!!.UtilityEquipmentBatteryBank,
                        data!!.id)
                    binding.tabs.setupWithViewPager(binding.viewpager)
                    setViewPager()
                }

                override fun DGItemClicked(data: UtilityEquipmentAllData?) {
                    DGDetailsActivity.utilityData = data
                    binding.viewpager.adapter = DGViewpagerAdapter(childFragmentManager,
                        data!!.UtilityEquipmentDG,
                        data!!.id)
                    binding.tabs.setupWithViewPager(binding.viewpager)
                    setViewPager()
                }

                override fun ACItemClicked(data: UtilityEquipmentAllData?) {
                    ACDetailsActivity.utilityData = data
                    binding.viewpager.adapter = ACViewpagerAdapter(childFragmentManager,
                        data!!.UtilityEquipmentAC,
                        data!!.id)
                    binding.tabs.setupWithViewPager(binding.viewpager)
                    setViewPager()
                }

                override fun FireExtinguisherItemClicked(data: UtilityEquipmentAllData?) {
                    FireExtinguisherActivity.utilityData = data
                    binding.viewpager.adapter = FireExtViewpagerAdapter(childFragmentManager,
                        data!!.UtilityEquipmentFireExtinguisher,
                        data!!.id)
                    binding.tabs.setupWithViewPager(binding.viewpager)
                    setViewPager()
                }

                override fun SuregeProtectionDeviceItemClicked(data: UtilityEquipmentAllData?) {
                    //TODO this need to discuss
                }

                override fun PowerDistributionBoxItemClicked(data: UtilityEquipmentAllData?) {
                    //TODO this need to discuss
                }

                override fun CableItemClicked(data: UtilityEquipmentAllData?) {
                    //TODO this need to discuss
                }

            })
        binding.horizontalOnlyList.adapter = serviceFragAdapterAdapter
        homeViewModel.utilityEquipResponse?.observe(viewLifecycleOwner, Observer {
            hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("planDesign Fragment card Data fetched successfully")
                serviceFragAdapterAdapter.setData(it.data.UtilityEquipment)
                if (it.data.UtilityEquipment?.isNotEmpty() == true) {
                    SMPSDetailsActivity.utilitySmpsData = it.data.UtilityEquipment?.get(0)
                    binding.viewpager.adapter = SMPSViewpagerAdapter(childFragmentManager,
                        it.data.UtilityEquipment?.get(0)!!.UtilityEquipmentSmps,
                        it.data.UtilityEquipment?.get(0)!!.id)
                    binding.tabs.setupWithViewPager(binding.viewpager)
                    setViewPager()
                }
            } else if (it != null) {
                AppLogger.log("Utility Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("Utility Fragment Something went wrong")
            }
        })
        showLoader()
//        homeViewModel.utilityRequestAll(siteID.toString())
        homeViewModel.utilityRequestAll("1526")
    }


    fun setUpOpcoData() {
        if (homeViewModel.opcoTenencyModelResponse?.hasActiveObservers() == true) {
            homeViewModel.opcoTenencyModelResponse?.removeObservers(viewLifecycleOwner)
        }
        val serviceFragAdapterAdapter = TaskOpcoTanancyFragAdapter(requireContext(), object :
            TaskCustomerDataAdapterListener {

            override fun clickedItem(data: OpcoTenencyAllData, parentIndex: Int) {
                OpcoTenancyActivity.parentIndex = parentIndex
                OpcoTenancyActivity.Opcodata = data
                binding.viewpager.adapter =
                    OpcoTenancyPageAdapter(childFragmentManager, data, parentIndex)
                binding.tabs.setupWithViewPager(binding.viewpager)
                setViewPager()
            }
        })
        binding.horizontalOnlyList.adapter = serviceFragAdapterAdapter
        homeViewModel?.opcoTenencyModelResponse?.observe(viewLifecycleOwner, Observer {

            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("planDesign Fragment card Data fetched successfully")
                serviceFragAdapterAdapter.setOpData(it.data.Operator!!)
            } else if (it != null) {
                Toast.makeText(
                    requireContext(),
                    "planDesign Fragment error :${it.message}, data : ${it.data}",
                    Toast.LENGTH_SHORT
                ).show()
                AppLogger.log("planDesign Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("planDesign Fragment Something went wrong")
                Toast.makeText(requireContext(), "planDesign Fragment Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        })
/*
        homeViewModel.serviceRequestModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            (requireActivity() as BaseActivity).hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.ServiceRequestMain!=null && it.data.ServiceRequestMain.isNotEmpty()){
                AppLogger.log("Service request Fragment card Data fetched successfully")
                val listData = it.data.ServiceRequestMain as ArrayList<ServiceRequestAllDataItem>
                serviceFragAdapterAdapter.setData(listData)
                AppLogger.log("size :${it.data.ServiceRequestMain.size}")

                if (listData.isNotEmpty())
                    clickedItem(listData[0],siteID.toString())
                isDataLoaded = true
            }
            else if (it!=null) {
                Toast.makeText(requireContext(),"Service request Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("Service Request Fragment Something went wrong")
                Toast.makeText(requireContext(),"Service Request Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
*/
        (requireActivity() as BaseActivity).showLoader()
        homeViewModel.opcoTenancyRequestAll(siteID.toString())
    }

    var isDataLoaded = false

    private fun setViewPager() {

        if (binding.tabs.tabCount == 1) {
            binding.tabs.setBackgroundColor(Color.parseColor("#E9EEF7"))
            binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#E9EEF7"))
        }
        else if (binding.tabs.tabCount <= 4)
            binding.tabs.tabMode = TabLayout.MODE_FIXED
        else
            binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE

        if (binding.tabs.tabCount == 0)
            binding.tabs.visibility = View.INVISIBLE
        else binding.tabs.visibility = View.VISIBLE
    }

    fun addFragment(fragment: Fragment?) {
        val backStateName: String = childFragmentManager.javaClass.name
        val manager = childFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val transaction = manager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            transaction.add(R.id.container, fragment!!)
            transaction.addToBackStack(backStateName)
            transaction.commit()
        }
    }

    private fun mapView() {
        val intent = Intent(requireContext(), HomePage::class.java)
        intent.putExtra("lat", lattitude)
        intent.putExtra("long", longitude)
        PatrollerPriference(requireContext()).setOwnername(siteID!!)
        PatrollerPriference(requireContext()).setTaskID(taskId)
        intent.putExtra("rad", radius)
        startActivity(intent)
    }

    override fun taskSiteInfoItemClicked() {
        val bottomSheetDialogFragment =
            SiteInfoEditBottomSheet(R.layout.task_site_info_dialouge_layout)
        bottomSheetDialogFragment.show(childFragmentManager, "category")

    }

    override fun clickedItem(data: ServiceRequestAllDataItem, Id: String) {
        ServicesRequestActivity.ServiceRequestdata = data
        ServicesRequestActivity.Id = Id
        binding.viewpager.adapter = ServicePageAdapter(childFragmentManager, data)
        binding.tabs.setupWithViewPager(binding.viewpager)
        setViewPager()
    }

    fun findTaskSubtabList(taskDetailData:TaskDataListItem?):String{
        if (taskDetailData?.Where!=null){
            var subTaskList: String? =taskDetailData?.Where
            subTaskList = subTaskList?.replace("[", "")
            subTaskList = subTaskList?.replace("]", "")
            return subTaskList!!
        }
        return  ""
    }


}