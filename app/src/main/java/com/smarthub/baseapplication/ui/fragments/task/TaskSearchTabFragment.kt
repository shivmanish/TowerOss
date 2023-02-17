package com.smarthub.baseapplication.ui.fragments.task

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentSearchTaskBinding
import com.smarthub.baseapplication.databinding.TaskTabItemsBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.taskModel.dropdown.Tab
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel
import com.smarthub.baseapplication.ui.dialog.services_request.EquipmentDetailsBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.sitedetail.SiteDetailViewModel
import com.smarthub.baseapplication.ui.fragments.task.adapter.HorizontalTabAdapter
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskAdapter
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskSiteInfoAdapter
import com.smarthub.baseapplication.ui.fragments.task.editdialog.OPCOSiteInfoEdit
import com.smarthub.baseapplication.ui.fragments.task.editdialog.SiteInfoEditBottomSheet
import com.smarthub.baseapplication.ui.fragments.task.task_tab.TaskEqupmentFragment
import com.smarthub.baseapplication.ui.fragments.task.task_tab.TaskOPCOEditTab
import com.smarthub.baseapplication.ui.fragments.task.task_tab.TaskOPCOTabFragment
import com.smarthub.baseapplication.ui.mapui.MapActivity
import com.smarthub.baseapplication.ui.taskUi.serviceRequest.acquisitionSurvey.AcquisitionSurveyPageAdapter
import com.smarthub.baseapplication.ui.taskUi.serviceRequest.srDetails.SrDetauilsPageAdapter
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.viewmodels.MainViewModel
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

class TaskSearchTabFragment(var siteID:String?) : BaseFragment(), TaskAdapter.TaskLisListener,HorizontalTabAdapter.TaskCardClickListner,
    TaskSiteInfoAdapter.TaskSiteInfoListener {
    private lateinit var binding: FragmentSearchTaskBinding
    private lateinit var mainViewModel:MainViewModel
    private lateinit var v: TaskTabItemsBinding
    private var tabNames: ArrayList<String> = ArrayList()
    var viewmodel: HomeViewModel?=null
    private lateinit var horizontalTabAdapter:HorizontalTabAdapter
    private lateinit var siteDetailViewModel: SiteDetailViewModel
    lateinit var TaskListmodel :TaskDropDownModel
    var taskAndCardList:ArrayList<String> = ArrayList()
    var selectedTabIndex:Int=0
    var lat ="19.25382218490181"
    var long="72.98213045018673"
    var radius="2"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        siteDetailViewModel = ViewModelProvider(requireActivity())[SiteDetailViewModel::class.java]
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        var json = Utils.getJsonDataFromAsset(requireContext(),"task_drop_down.json")
        TaskListmodel = Gson().fromJson(json, TaskDropDownModel::class.java)
        mainViewModel.isActionBarHide(false)
        tabNames.add("OPCO Info")
        tabNames.add("Equipment")
        tabNames.add("Operator & Equip")
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

       // showLoader()
        setData()
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
    fun setData(){
        val adapter = ViewPagerAdapter(childFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        binding.viewpager.adapter = getViewPagerAdapter(childFragmentManager,siteID!!)
        binding.tabs.setupWithViewPager( binding.viewpager)
        setCustomTab()
        binding.viewpager.currentItem = 0
        binding.tabs.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val view: View = tab.customView ?: return
                val constraintLayout: ConstraintLayout = view!!.findViewById(R.id.parent_id)
               // constraintLayout.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                constraintLayout.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                val constraintLay: ConstraintLayout = view!!.findViewById(R.id.parent_tab_child)
                val texttabchange = constraintLay.getChildAt(0).findViewById<AppCompatTextView>(R.id.txt_tab)
                texttabchange.setTextColor(resources.getColor(R.color.tab_selected_color))
                Log.e("TAG", " $view  ${tab.position}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val view: View = tab.customView ?: return
                val constraintLayout: ConstraintLayout = view!!.findViewById(R.id.parent_id)
                constraintLayout.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tab_selected))
                val constraintLay: ConstraintLayout = view.findViewById(R.id.parent_tab_child)
                val texttabchange = constraintLay.getChildAt(0).findViewById<AppCompatTextView>(R.id.txt_tab)
                texttabchange.setTextColor(resources.getColor(R.color.white))

            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

    }
    @SuppressLint("SuspiciousIndentation")
    fun setCustomTab() {

        val typedImages = siteDetailViewModel.getImageArray(requireActivity())
        for (i in 0..tabNames?.size!!.minus(1)) {
            v = TaskTabItemsBinding.inflate(layoutInflater)
            val texttab: AppCompatTextView = v.textTab
            val texttabchange: TextView = v.txtTab
            texttab.text = tabNames?.get(i)
            texttabchange.text = tabNames?.get(i)
            texttab.textSize=12f
            texttabchange.textSize=13f
          //  imagetab.setImageResource(typedImages.getResourceId(i, 0))
            binding.tabs.getTabAt(i)?.customView = v.root
        }
        val constraintLayout: ConstraintLayout = binding.tabs.getTabAt(0)?.customView!!.findViewById(R.id.parent_id)
        val constraintLay: ConstraintLayout = binding.tabs.getTabAt(0)?.customView!!.findViewById(R.id.parent_tab_child)
        val texttabchange = constraintLay.getChildAt(0).findViewById<AppCompatTextView>(R.id.txt_tab)
        texttabchange.setTextColor(resources.getColor(R.color.tab_selected_color))

        constraintLayout.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tab_selected_color))
    }
    internal inner class ViewPagerAdapter(manager: FragmentManager, behaviour:Int) : FragmentPagerAdapter(manager,behaviour) {


        override fun getItem(position: Int): Fragment {
            return when(position){
                0-> TaskOPCOTabFragment(siteID!!)
                1-> TaskEqupmentFragment.newInstance(tabNames[1])
                2-> TaskOPCOEditTab.newInstance(tabNames[2])

                else -> TaskOPCOTabFragment(siteID!!)
            }
        }


        override fun getCount(): Int {
            return tabNames.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return tabNames[position]
        }

    }

    private fun mapView(){
        val intent = Intent(requireContext(), MapActivity::class.java)
        intent.putExtra("lat",lat)
        intent.putExtra("long",long)
        intent.putExtra("rad",radius)
        startActivity(intent)
    }

    fun getViewPagerAdapter(fm:FragmentManager,siteId:String): FragmentPagerAdapter {
        var selectedAdapter=
            when(taskAndCardList[0].toInt()){
                0->{
                    when(selectedTabIndex){
                        0->{
                            SrDetauilsPageAdapter(fm,siteId)
                        }
                        else->{
                            SrDetauilsPageAdapter(fm,siteId)
                        }
                    }
                }
                1->{
                    when(selectedTabIndex){
                        0->{
                            SrDetauilsPageAdapter(fm,siteId)
                        }
                        else->{
                            AcquisitionSurveyPageAdapter(fm,siteId)
                        }
                    }
                }
                else->{
                    when(selectedTabIndex){
                        0->{
                            SrDetauilsPageAdapter(fm,siteId)
                        }
                        else->{
                            SrDetauilsPageAdapter(fm,siteId)
                        }
                    }
                }
            }
        return selectedAdapter
    }


    fun createHoriZentalList():ArrayList<Tab>{
        var cardList:ArrayList<Tab> =ArrayList()
        var selectedTask=TaskListmodel[taskAndCardList[0].toInt()]
        cardList.clear()
        for(i in 1..taskAndCardList.size.minus(1)){
            cardList.add(selectedTask.tabs[taskAndCardList[i].toInt()])
        }
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
    override fun TaskCardItemClicked(selectedTab:Tab) {
        selectedTabIndex=TaskListmodel[taskAndCardList[0].toInt()].tabs.indexOf(selectedTab)
        AppLogger.log("selectedTabIndex====> $selectedTabIndex")
    }
    override fun taskSiteInfoItemClicked() {
        val bottomSheetDialogFragment = SiteInfoEditBottomSheet(R.layout.task_site_info_dialouge_layout)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }
}