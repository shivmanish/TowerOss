package com.smarthub.baseapplication.ui.fragments.sitedetail

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SiteLocationDetailBinding
import com.smarthub.baseapplication.databinding.TabItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.AppPreferences.DROPDOWNDATA
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.noc.NocFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.OpcoTanacyFragment
import com.smarthub.baseapplication.ui.fragments.plandesign.fragment.PlanDesignMainFrqagment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.PowerConnection
import com.smarthub.baseapplication.ui.fragments.qat.QATMainFragment
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestFrqagment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.SiteAgreementFragment
import com.smarthub.baseapplication.ui.fragments.siteInfo.SiteInfoNewFragment
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.CivilInfraFragment
import com.smarthub.baseapplication.ui.utilites.fragment.UtilitiesNocMainTabFragment
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.MainViewModel


class SiteDetailFragment : BaseFragment() {
    private var _sitebinding: SiteLocationDetailBinding? = null
    private val binding get() = _sitebinding!!
    private lateinit var ctx: Context
    private lateinit var siteDetailViewModel: SiteDetailViewModel
    private lateinit var mainViewModel: MainViewModel
    var fabVisible = false
    private lateinit var v: TabItemBinding
    private var tabNames: Array<String>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.ctx = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        siteDetailViewModel = ViewModelProvider(requireActivity())[SiteDetailViewModel::class.java]
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        if (arguments?.containsKey("id") == true)
            id = arguments?.getString("id")!!
        if (arguments?.containsKey("siteName") == true)
            siteName = arguments?.getString("siteName")!!

        AppController.getInstance().siteid=id
        AppController.getInstance().siteName=siteName
        AppLogger.log(" id ---:$id")
    }

    var id = "430"
    var siteName ="#ce-dlhi-sc-0179"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title: TextView = binding.root.findViewById<View>(R.id.title) as TextView
        title.text = siteName

        setData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mainViewModel.isActionBarHide(true)
        _sitebinding = SiteLocationDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        tabNames = siteDetailViewModel.getStrings(requireActivity())

        root.findViewById<View>(R.id.btn_back).setOnClickListener { requireActivity().onBackPressed() }
        return root
    }

    fun setData(){
        val adapter = ViewPagerAdapter(childFragmentManager,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        binding.viewpager.adapter = adapter
        binding.viewpager.offscreenPageLimit = 5
        binding.tabs.setupWithViewPager(binding.viewpager)
        setCustomTab()
        binding.viewpager.currentItem = 0
        binding.tabs.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val view: View? = tab.customView
                val constraintLayout: ConstraintLayout = view!!.findViewById(R.id.parent_id)
                constraintLayout.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tab_selected_color))
                val constraintLay: ConstraintLayout = view.findViewById(R.id.parent_tab_child)
                val texttabchange = constraintLay.getChildAt(0).findViewById<AppCompatTextView>(R.id.txt_tab)
                texttabchange.setTextColor(resources.getColor(R.color.tab_selected_color))
                Log.e("TAG", " $view  ${tab.position}")
                AppLogger.log("onTabSelected:"+tab.position)
//                adapter.getItemByPosition(tab.position).onViewPageSelected()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val view: View? = tab.customView
                val constraintLayout: ConstraintLayout = view!!.findViewById(R.id.parent_id)
                constraintLayout.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                val constraintLay: ConstraintLayout = view.findViewById(R.id.parent_tab_child)
                val texttabchange = constraintLay.getChildAt(0).findViewById<AppCompatTextView>(R.id.txt_tab)
                texttabchange.setTextColor(resources.getColor(R.color.white))
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                AppLogger.log("onTabSelected:"+tab.position)
                adapter.getItemByPosition(tab.position).onViewPageSelected()
            }
        })

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

//        val addImage = binding.root.findViewById<ImageView>(R.id.add_image)
//        addImage.setOnClickListener {
//            val intent = Intent(activity, SiteImages::class.java)
//            startActivity(intent)
//        }
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

    internal inner class ViewPagerAdapter(manager: FragmentManager,behaviour:Int) : FragmentPagerAdapter(manager,behaviour) {

        var list : ArrayList<BaseFragment> = ArrayList(tabNames?.size!!)
        init {
            for(i in 0 until tabNames?.size?.minus(1)!!){
                list.add(getCustomItemForLocal(i))
            }
        }

        private fun getCustomItemForLocal(position: Int) : BaseFragment{
            val f : BaseFragment =  when(position){
                0-> SiteInfoNewFragment(id)
                1-> ServicesRequestFrqagment(id)
                2-> OpcoTanacyFragment(id)
                3-> PlanDesignMainFrqagment(id)
                4-> SiteAgreementFragment(id)
                5-> UtilitiesNocMainTabFragment(id)
                6-> NocFragment(id)
                7-> CivilInfraFragment(id)
                8-> PowerConnection(id)
                9-> QATMainFragment(id)
//                10-> AcquisitionSurveyFragmentNew(id)
                else -> SiteInfoNewFragment(id)
            }
            return f
        }

        override fun getItem(position: Int): Fragment {
            val f = getCustomItemForLocal(position)
            if (list.size>position) list[position] = f
            else list.add(f)
            return list[position]
        }

        fun getItemByPosition(position: Int): BaseFragment{
            return list[position]
        }

        override fun getCount(): Int {
            return tabNames?.size!!
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return tabNames?.get(position)
        }

    }

    private fun setDataObserver() {

        showLoader()
        if (siteDetailViewModel.dropDownResponse?.hasActiveObservers() == true)
            siteDetailViewModel.dropDownResponse?.removeObservers(viewLifecycleOwner)
        siteDetailViewModel.dropDownResponse?.observe(viewLifecycleOwner) {
            hideLoader()
            if (it != null) {
                if (it.status == Resource.Status.SUCCESS && it.data != null) {
                    saveDataToLocal(it.data)
//                    setData()
                    return@observe
                } else {
                    Log.d("status", "${it.message}")
//                    Toast.makeText(context, "error:" + it.message, Toast.LENGTH_LONG).show()

                }
            } else {
                Log.d("status", AppConstants.GENERIC_ERROR)
//                Toast.makeText(context, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
            }
        }
        siteDetailViewModel.fetchDropDown()
    }

    private fun saveDataToLocal(data: SiteInfoDropDownData) {

        val gson = Gson()
        val stringDatajson = gson.toJson(data)
        AppPreferences.getInstance().saveString(DROPDOWNDATA, stringDatajson)

    }

}