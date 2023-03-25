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
import com.example.patrollerapp.homepage.HomePage
import com.example.patrollerapp.util.LocationService
import com.example.patrollerapp.util.PatrollerPriference
import com.example.patrollerapp.util.Util
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.FragmentSearchTaskBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.AllsiteInfoDataModel
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignDataItem
import com.smarthub.baseapplication.ui.alert.dialog.ChatFragment
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.OpcoTenancyActivity
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.OpcoTenancyPageAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.TaskCustomerDataAdapterListener
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.TaskOpcoTanancyFragAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.PowerDesignDetailPageAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.PowerDesignDetailsActivity
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.PlanDesignAdapterListener
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.TaskPlanDesignAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestActivity
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicePageAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesDataAdapterListener
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.TaskServicesDataAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.SiteAcqTabActivity
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.TaskSiteAcqsitionFragAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.SiteAcquisitionTabAdapter
import com.smarthub.baseapplication.ui.fragments.sitedetail.SiteDetailViewModel
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskSiteInfoAdapter
import com.smarthub.baseapplication.ui.fragments.task.editdialog.SiteInfoEditBottomSheet
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TaskSearchTabNewFragment(
    var siteID: String?, var taskId: String,
    var lattitude: String, var longitude: String, var tempWhere: String
) : BaseFragment(),
    TaskSiteInfoAdapter.TaskSiteInfoListener, ServicesDataAdapterListener{
    private lateinit var binding: FragmentSearchTaskBinding
    private lateinit var siteDetailViewModel: SiteDetailViewModel
    lateinit var homeViewModel: HomeViewModel
    var taskAndCardList: ArrayList<String> = ArrayList()
    var lat = "19.25382218490181"
    var mLocationService: LocationService = LocationService()
    lateinit var mServiceIntent: Intent
    var long = "72.98213045018673"
    var radius = "2"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        siteDetailViewModel = ViewModelProvider(this)[SiteDetailViewModel::class.java]
        tempWhere = tempWhere.replace("[", "")
        tempWhere = tempWhere.replace("]", "")
        taskAndCardList.addAll(tempWhere.split(","))
        binding = FragmentSearchTaskBinding.inflate(inflater, container, false)
        initVariable()
        return binding.root
    }

    var list: ArrayList<Any> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.collapsingLayout.tag = false
        binding.horizontalOnlyList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        setDataObserver()
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
        setParentData()
    }

    fun setParentData() {
        var splittedData = tempWhere.split(",")
        if (splittedData.isNotEmpty()) {
            val firstIdx = splittedData[0]
            if (firstIdx.length > 1) {
                val parentId = firstIdx[0].toInt()
                AppLogger.log("parentId${parentId}")
//                if (parentId == 2) {
//                    setUpServiceRequestData()
//                } else if(parentId == 3) {
//                    setUpPnanigAndDesignData()
//                }else{
//                    setUpServiceRequestData()
//                }

//                setUpPnanigAndDesignData()
            setUpOpcoData()
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
                    AppLogger.log("fetched latitude:${lattitude},longitude:$longitude")
                } else {
                    AppLogger.log("Site address not fetched")
                }
            } else if (it != null) {
                AppLogger.log("SiteInfoNewFragment error :${it.message}")

                Toast.makeText(
                    requireContext(),
                    "SiteInfoNewFragment error :${it.message}",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                AppLogger.log("SiteInfoNewFragment Something went wrong")
                Toast.makeText(
                    requireContext(),
                    "SiteInfoNewFragment Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        homeViewModel.siteInfoRequestAll(siteID!!)
        siteDetailViewModel.fetchDropDown()
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

                PowerDesignDetailsActivity.Id=Id
                PowerDesignDetailsActivity.planDesigndata=data
                binding.viewpager.adapter =PowerDesignDetailPageAdapter(childFragmentManager, data,Id)
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
        homeViewModel.planAndDesignRequestAll("1526")
    }

    fun setUpSiteAcqusitionData() {
        if (homeViewModel.serviceRequestModelResponse?.hasActiveObservers() == true) {
            homeViewModel.serviceRequestModelResponse?.removeObservers(viewLifecycleOwner)
        }
        val serviceFragAdapterAdapter = TaskSiteAcqsitionFragAdapter(requireContext(),object : TaskSiteAcqsitionFragAdapter.SiteAcqListListener {
            override fun clickedItem(data: NewSiteAcquiAllData, parentIndex: Int) {
                //this is for the listiner

                SiteAcqTabActivity.siteacquisition = data
                SiteAcqTabActivity.parentIndex = parentIndex
                binding.viewpager.adapter = SiteAcquisitionTabAdapter(childFragmentManager, data,parentIndex)
                binding.tabs.setupWithViewPager(binding.viewpager)
                setViewPager()
            }

        })
        binding.horizontalOnlyList.adapter = serviceFragAdapterAdapter
        homeViewModel?.siteAgreementModel?.observe(viewLifecycleOwner, Observer {

            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("planDesign Fragment card Data fetched successfully")
                serviceFragAdapterAdapter.setData(it.data.SAcqSiteAcquisition)
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
        (requireActivity() as BaseActivity).showLoader()
        homeViewModel.fetchSiteAgreementModelRequest("1526")
    }


    fun setUpOpcoData() {
        if (homeViewModel.opcoTenencyModelResponse?.hasActiveObservers() == true) {
            homeViewModel.opcoTenencyModelResponse?.removeObservers(viewLifecycleOwner)
        }
        val serviceFragAdapterAdapter = TaskOpcoTanancyFragAdapter(requireContext(),object :
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
        homeViewModel.opcoTenancyRequestAll("1526")
    }

    var isDataLoaded = false

    private fun setViewPager() {

        if (binding.tabs.tabCount == 1) {
            binding.tabs.setBackgroundColor(Color.parseColor("#E9EEF7"))
            binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#E9EEF7"))
        }
        if (binding.tabs.tabCount <= 4)
            binding.tabs.tabMode = TabLayout.MODE_FIXED
        else
            binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE
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


}