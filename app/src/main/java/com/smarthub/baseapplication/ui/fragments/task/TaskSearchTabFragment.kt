package com.smarthub.baseapplication.ui.fragments.task

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentSearchTaskBinding
import com.smarthub.baseapplication.databinding.TaskTabItemsBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.basic_info.SiteImages
import com.smarthub.baseapplication.ui.dialog.services_request.EquipmentDetailsBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.opcoInfo.OperationsItemsEditDialouge
import com.smarthub.baseapplication.ui.fragments.sitedetail.SiteDetailViewModel
import com.smarthub.baseapplication.ui.fragments.task.adapter.HorizontalTabAdapter
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskAdapter
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskSiteInfoAdapter
import com.smarthub.baseapplication.ui.fragments.task.editdialog.OPCOSiteInfoEdit
import com.smarthub.baseapplication.ui.fragments.task.editdialog.SiteInfoEditBottomSheet
import com.smarthub.baseapplication.ui.fragments.task.task_tab.TaskEqupmentFragment
import com.smarthub.baseapplication.ui.fragments.task.task_tab.TaskOPCOEditTab
import com.smarthub.baseapplication.ui.fragments.task.task_tab.TaskOPCOTabFragment
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.viewmodels.MainViewModel

class TaskSearchTabFragment : BaseFragment(), TaskAdapter.TaskLisListener,HorizontalTabAdapter.ItemClickListener,
    TaskSiteInfoAdapter.TaskSiteInfoListener {
    private lateinit var binding: FragmentSearchTaskBinding
    private lateinit var mainViewModel:MainViewModel
    private lateinit var v: TaskTabItemsBinding
    private var tabNames: ArrayList<String> = ArrayList()
    private lateinit var horizontalTabAdapter:HorizontalTabAdapter
    private lateinit var siteDetailViewModel: SiteDetailViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        siteDetailViewModel = ViewModelProvider(requireActivity())[SiteDetailViewModel::class.java]
        mainViewModel.isActionBarHide(false)
        tabNames.add("OPCO Info")
        tabNames.add("Equipment")
        tabNames.add("Operator & Equip")
        binding = FragmentSearchTaskBinding.inflate(inflater, container, false)
        return binding.root
    }
    var list : ArrayList<Any> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // binding.listItem.adapter = TaskAdapter(requireContext(),this@TaskSearchTabFragment)

        var recyclerListener = view.findViewById<RecyclerView>(R.id.horizontal_only_list)
        var adapter =  HorizontalTabAdapter(this@TaskSearchTabFragment)
        recyclerListener.adapter = adapter
        adapter.addItem()
        setDataObserver()
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
                    setData()
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
    }
    fun setData(){
        val adapter = ViewPagerAdapter(childFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        binding.viewpager.adapter = adapter
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

        val addImage = binding.root.findViewById<ImageView>(R.id.add_image)
        addImage.setOnClickListener {
            val intent = Intent(activity, SiteImages::class.java)
            startActivity(intent)
        }
    }
    @SuppressLint("SuspiciousIndentation")
    fun setCustomTab() {

        val typedImages = siteDetailViewModel.getImageArray(requireActivity())
        for (i in 0..tabNames?.size!!.minus(1)) {
            v = TaskTabItemsBinding.inflate(layoutInflater)
            val texttab: AppCompatTextView = v.textTab
            val texttabchange: TextView = v.txtTab
            //val imagetab: AppCompatImageView = v.tabImage
            texttab.text = tabNames?.get(i)
            texttabchange.text = tabNames?.get(i)
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

                0-> TaskOPCOTabFragment.newInstance(tabNames[0])
                1-> TaskEqupmentFragment.newInstance(tabNames[1])
                2-> TaskOPCOEditTab.newInstance(tabNames[2])

                else -> TaskOPCOTabFragment.newInstance(tabNames[1])
            }
        }


        override fun getCount(): Int {
            return tabNames.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return tabNames[position]
        }

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

        val bottomSheetDialogFragment = OperationsItemsEditDialouge(R.layout.opco_operations_team_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
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
    override fun itemClicked() {
    }
    override fun taskSiteInfoItemClicked() {
        val bottomSheetDialogFragment = SiteInfoEditBottomSheet(R.layout.task_site_info_dialouge_layout)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }
}