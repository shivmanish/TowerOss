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
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentSearchTaskBinding
import com.smarthub.baseapplication.databinding.TabItemBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.ui.basic_info.SiteImages
import com.smarthub.baseapplication.ui.dialog.services_request.EquipmentDetailsBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.opcoInfo.OperationsItemsEditDialouge
import com.smarthub.baseapplication.ui.fragments.sitedetail.SiteDetailViewModel
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskAdapter
import com.smarthub.baseapplication.ui.fragments.task.editdialog.OPCOSiteInfoEdit
import com.smarthub.baseapplication.ui.fragments.task.editdialog.SiteInfoEditBottomSheet
import com.smarthub.baseapplication.ui.fragments.task.task_tab.TaskOPCOTabFragment
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.viewmodels.MainViewModel

class TaskSearchTabFragment : BaseFragment(), TaskAdapter.TaskLisListener {

    private lateinit var binding: FragmentSearchTaskBinding
    private lateinit var mainViewModel:MainViewModel
    private lateinit var v: TabItemBinding
    private var tabNames: Array<String>? = null
    private lateinit var siteDetailViewModel: SiteDetailViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mainViewModel.isActionBarHide(false)
        tabNames = siteDetailViewModel.getStrings(requireActivity())

        binding = FragmentSearchTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // binding.listItem.adapter = TaskAdapter(requireContext(),this@TaskSearchTabFragment)
        setDataObserver()
    }

    private fun setDataObserver() {

        showLoader()
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
                val view: View? = tab.customView
                val constraintLayout: ConstraintLayout = view!!.findViewById(R.id.parent_id)
                constraintLayout.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tab_selected_color))
                val constraintLay: ConstraintLayout = view!!.findViewById(R.id.parent_tab_child)
                val texttabchange = constraintLay.getChildAt(0).findViewById<AppCompatTextView>(R.id.txt_tab)
                texttabchange.setTextColor(resources.getColor(R.color.tab_selected_color))
                Log.e("TAG", " $view  ${tab.position}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val view: View? = tab.customView
                val constraintLayout: ConstraintLayout = view!!.findViewById(R.id.parent_id)
                constraintLayout.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
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
            v = TabItemBinding.inflate(layoutInflater)
            val texttab: AppCompatTextView = v.textTab
            val texttabchange: TextView = v.txtTab
            val imagetab: AppCompatImageView = v.tabImage
            texttab.text = tabNames?.get(i)
            texttabchange.text = tabNames?.get(i)
            imagetab.setImageResource(typedImages.getResourceId(i, 0))
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

                0-> TaskOPCOTabFragment.newInstance(tabNames?.get(0) ?: "OPCO Info")
                1-> TaskOPCOTabFragment.newInstance(tabNames?.get(1) ?: "Equipment")
                2-> TaskOPCOTabFragment.newInstance(tabNames?.get(2) ?: "Operator & Equip")

                else -> TaskOPCOTabFragment.newInstance(tabNames?.get(1) ?: "OPCO Info")
            }
        }


        override fun getCount(): Int {
            return tabNames?.size!!
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return tabNames?.get(position)
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
//OperationsItemsEditDialouge
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

}