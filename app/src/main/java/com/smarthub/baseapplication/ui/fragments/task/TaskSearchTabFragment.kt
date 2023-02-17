package com.smarthub.baseapplication.ui.fragments.task

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentSearchTaskBinding
import com.smarthub.baseapplication.databinding.TaskTabItemsBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.taskModel.dropdown.CollectionItem
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel
import com.smarthub.baseapplication.ui.dialog.services_request.EquipmentDetailsBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.sitedetail.SiteDetailViewModel
import com.smarthub.baseapplication.ui.fragments.task.adapter.HorizontalTabAdapter
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskAdapter
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskSiteInfoAdapter
import com.smarthub.baseapplication.ui.fragments.task.editdialog.OPCOSiteInfoEdit
import com.smarthub.baseapplication.ui.fragments.task.editdialog.SiteInfoEditBottomSheet
import com.smarthub.baseapplication.ui.mapui.MapActivity
import com.smarthub.baseapplication.ui.taskUi.serviceRequest.srDetails.SrDetauilsPageAdapter
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.viewmodels.MainViewModel

class TaskSearchTabFragment(var siteID:String?) : BaseFragment(), TaskAdapter.TaskLisListener,HorizontalTabAdapter.TaskCardClickListner,
    TaskSiteInfoAdapter.TaskSiteInfoListener {
    private lateinit var binding: FragmentSearchTaskBinding
    private lateinit var mainViewModel:MainViewModel
    private lateinit var v: TaskTabItemsBinding
//    private var tabNames: ArrayList<String> = ArrayList()
    var viewmodel: HomeViewModel?=null
    private lateinit var horizontalTabAdapter:HorizontalTabAdapter
    private lateinit var siteDetailViewModel: SiteDetailViewModel
    lateinit var TaskListmodel :TaskDropDownModel
    var taskAndCardList:ArrayList<String> = ArrayList()
//    var selectedTabIndex:Int=0
    var lat ="19.25382218490181"
    var long="72.98213045018673"
    var radius="2"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        siteDetailViewModel = ViewModelProvider(requireActivity())[SiteDetailViewModel::class.java]
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        val json = Utils.getJsonDataFromAsset(requireContext(),"task_drop_down.json")
        TaskListmodel = Gson().fromJson(json, TaskDropDownModel::class.java)
        mainViewModel.isActionBarHide(false)

        taskAndCardList.addAll(listOf("1","0","2","3"))
        binding = FragmentSearchTaskBinding.inflate(inflater, container, false)
        return binding.root
    }
    var list : ArrayList<Any> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // binding.listItem.adapter = TaskAdapter(requireContext(),this@TaskSearchTabFragment)
        binding.collapsingLayout.tag= false
        horizontalTabAdapter =  HorizontalTabAdapter(this@TaskSearchTabFragment,createHoriZentalList())
        binding.horizontalOnlyList.adapter = horizontalTabAdapter
        binding.horizontalOnlyList.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        setDataObserver()

        binding.dropdownImg.setOnClickListener {
            binding.collapsingLayout.tag= !(binding.collapsingLayout.tag as Boolean)
            if (binding.collapsingLayout.tag as Boolean){
                binding.collapsingLayout.visibility=View.VISIBLE
                binding.topLine.visibility=View.VISIBLE
                binding.dropdownImg.setImageResource(R.drawable.down_arrow)
            }
            else{
                binding.collapsingLayout.visibility=View.GONE
                binding.topLine.visibility=View.GONE
                binding.dropdownImg.setImageResource(R.drawable.ic_arrow_up_faq)
            }
        }

        binding.mapView.setOnClickListener {
            mapView()
        }
//        binding.back.setOnClickListener {
//            requireActivity().onBackPressedDispatcher.onBackPressed()
//        }
    }
    private fun setDataObserver() {
        val list = TaskListmodel[taskAndCardList[0].toInt()].tabs[taskAndCardList[1].toInt()].list
        setViewPager(list)

        if (siteDetailViewModel.dropDownResponse?.hasActiveObservers() == true)
            siteDetailViewModel.dropDownResponse?.removeObservers(viewLifecycleOwner)
        siteDetailViewModel.dropDownResponse?.observe(viewLifecycleOwner) {
            hideLoader()

            if (it != null) {
                if (it.status == Resource.Status.SUCCESS && it.data != null) {
                   // saveDataToLocal(it.data)
//                    setData()
                    return@observe
                } else {
                    Log.d("status", "${it.message}")
                    Toast.makeText(context, "error:" + it.message, Toast.LENGTH_LONG).show()

                }
            } else {
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(context, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
            }
        }
        siteDetailViewModel.fetchDropDown()
        viewmodel?.opcoTenancyRequestAll(siteID!!)
    }

    fun setViewPager(list:List<String>){
        binding.viewpager.adapter = SrDetauilsPageAdapter(childFragmentManager,list,taskAndCardList)
        binding.tabs.setupWithViewPager( binding.viewpager)

        if(binding.tabs.tabCount==1) {
            binding.tabs.setBackgroundColor(Color.parseColor("#E9EEF7"))
            binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#E9EEF7"))
        }
        if(binding.tabs.tabCount<=4)
            binding.tabs.tabMode = TabLayout.MODE_FIXED
        else
            binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE
    }

    private fun mapView(){
        val intent = Intent(requireContext(), MapActivity::class.java)
        intent.putExtra("lat",lat)
        intent.putExtra("long",long)
        intent.putExtra("rad",radius)
        startActivity(intent)
    }

    private fun createHoriZentalList():ArrayList<CollectionItem>{
        if (taskAndCardList.isEmpty()) {
            Toast.makeText(requireContext(),"Collection list is empty",Toast.LENGTH_SHORT).show()
            return ArrayList()
        }
        val cardList:ArrayList<CollectionItem> =ArrayList()

        val selectedTask=TaskListmodel[taskAndCardList[0].toInt()]

        for(i in 1..taskAndCardList.size.minus(1)){
            val card = selectedTask.tabs[taskAndCardList[i].toInt()]
            cardList.add(card)
        }
        AppLogger.log("task name:${selectedTask.name},size:${selectedTask.tabs.size}")
        return cardList
    }

    override fun attachmentItemClicked() {

    }
    override fun detailsItemClicked() {
        val bottomSheetDialogFragment = SiteInfoEditBottomSheet(R.layout.task_site_info_dialouge_layout)
        bottomSheetDialogFragment.show(childFragmentManager,"category")


    }
    override fun requestinfoClicked() {
        val bottomSheetDialogFragment = OPCOSiteInfoEdit(R.layout.opco_info_site_dialouge_layout)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun operationInfoDetailsItemClicked() {

    }
    override fun geoConditionsDetailsItemClicked() {

    }
    override fun siteAccessDetailsItemClicked() {
        Toast.makeText(context,"site acces botton sheet clicked",Toast.LENGTH_SHORT).show()

//        val bottomSheetDialogFragment = OperationsItemsEditDialouge(R.layout.opco_operations_team_dialouge)
//        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun EditInstallationAcceptence() {

    }
    override fun EditTowerItem() {
        val bottomSheetDialogFragment = EquipmentDetailsBottomSheetDialog(R.layout.equipment_room_dialouge_layout)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun editPoClicked(position: Int) {

    }
    override fun viewPoClicked(position: Int) {
        val bottomSheetDialogFragment = EquipmentDetailsBottomSheetDialog(R.layout.equipment_view_bottom_sheet)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun editConsumableClicked(position: Int) {

    }
    override fun viewConsumableClicked(position: Int) {

    }
    override fun editOffsetClicked(position: Int) {

    }
    override fun viewOffsetClicked(position: Int) {

    }
    override fun taskCardItemClicked(selectedCollectionItem:CollectionItem) {
        setViewPager(selectedCollectionItem.list)
    }
    override fun taskSiteInfoItemClicked() {
        val bottomSheetDialogFragment = SiteInfoEditBottomSheet(R.layout.task_site_info_dialouge_layout)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }
}