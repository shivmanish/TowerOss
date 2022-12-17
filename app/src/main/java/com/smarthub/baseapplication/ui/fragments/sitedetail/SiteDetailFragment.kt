package com.smarthub.baseapplication.ui.fragments.sitedetail

import android.annotation.SuppressLint
import android.content.Context
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
import com.google.android.material.appbar.AppBarLayout
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
import com.smarthub.baseapplication.ui.basic_info.SiteImages
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.customer_tab.OpcoTanacyFragment
import com.smarthub.baseapplication.ui.fragments.customer_tab.SiteInfoNewFragment
import com.smarthub.baseapplication.ui.fragments.powerConnection.PowerConnection
import com.smarthub.baseapplication.ui.fragments.services_request.NocFragment
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestFrqagment
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.CivilInfraFragment
import com.smarthub.baseapplication.ui.site_lease_acquisition.SiteLeaseAcqusitionFragment
import com.smarthub.baseapplication.ui.utilites.fragment.UtilitiesNocMainTabFragment
import com.smarthub.baseapplication.utils.AppConstants
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataObserver()

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
        binding.tabs.setupWithViewPager( binding.viewpager)
        setCustomTab()
        binding.viewpager.currentItem = 0
        binding.tabs.setOnTabSelectedListener(object : OnTabSelectedListener {
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
//                viewPager.currentItem = tab.position
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
        setFabActionButton()
        val addImage = binding.root.findViewById<ImageView>(R.id.add_image)
        addImage.setOnClickListener {
            val intent = Intent(activity, SiteImages::class.java)
            startActivity(intent)
        }
    }

    private fun setFabActionButton() {
        // for our add floating action button
        binding.appBar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onOffsetChanged(state: State?, offset: Float) {
                if (state === State.IDLE) {
//						mToolbarTextView.setAlpha(offset);
                    for (i in 0..tabNames?.size!!.minus(1)) {
                        val constraintLayout: ConstraintLayout =
                            binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_id)
                        val constraintL: ConstraintLayout =
                            binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_tab_child)

                        constraintL.alpha = offset
                        constraintLayout.alpha = (1 - offset)
                    }
                    Log.d("offsetvalues>>>onOffsetChanged>", "$offset  ${1 - offset}")

                }
            }

            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                if (state === State.COLLAPSED) {
//						mToolbarTextView.setAlpha(1);
                    for (i in 0..tabNames?.size!!.minus(1)) {
                        val parentconstraintLayout: ConstraintLayout =
                            binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_id)
                        val childconstraint: ConstraintLayout =
                            binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_tab_child)
                        parentconstraintLayout.visibility = View.GONE
                        childconstraint.visibility = View.VISIBLE
//                            constraintL.alpha= 1f
//                            constraintLayout.alpha = 0f
                    }
                    Log.d("offsetvalues>>>onOffsetChanged>", "${state}")
                } else if (state === State.EXPANDED) {
//						mToolbarTextView.setAlpha(0);
                    for (i in 0..tabNames?.size!!.minus(1)) {
                        val parentconstraintLayout: ConstraintLayout =
                            binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_id)
                        val childconstraint: ConstraintLayout =
                            binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_tab_child)
                        parentconstraintLayout.visibility = View.VISIBLE
                        childconstraint.visibility = View.GONE
//                            constraintL.alpha= 0f
//                            constraintLayout.alpha = 1f
                    }
                    Log.d("offsetvalues>>>onOffsetChanged>", "${state}")
                }
            }
        })
        binding.fabbtn.setOnClickListener {

            if (!fabVisible) {
                binding.fabbtn.setImageDrawable(
                    siteDetailViewModel.getDrawable(
                        requireActivity(),
                        true
                    )
                )
                siteDetailViewModel.openPopup(binding.fabbtn, requireActivity())
                fabVisible = true
            } else {
                binding.fabbtn.setImageDrawable(
                    siteDetailViewModel.getDrawable(
                        requireActivity(),
                        false
                    )
                )
                siteDetailViewModel.dismissPopub()
                fabVisible = false
            }
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

    internal inner class ViewPagerAdapter(manager: FragmentManager,behaviour:Int) : FragmentPagerAdapter(manager,behaviour) {

        override fun getItem(position: Int): Fragment {
            return when(position){
                0-> SiteInfoNewFragment()
                1-> ServicesRequestFrqagment.newInstance(tabNames?.get(1) ?: "Service Request")
                2-> OpcoTanacyFragment.newInstance(tabNames?.get(2) ?: "OPCO Tenancy")
                3-> SiteLeaseAcqusitionFragment.newInstance(tabNames?.get(3) ?: "Site Agreement")
                4-> UtilitiesNocMainTabFragment.newInstance(tabNames?.get(4) ?: "Utilitie Equip")
                5-> NocFragment.newInstance(tabNames?.get(5) ?: "Noc & Comp")
                6-> CivilInfraFragment()
                7-> PowerConnection()
                8-> BlackhaulFrag.newInstance(tabNames?.get(8) ?: "QA Inspection")
                else -> SiteInfoNewFragment()
            }
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

    private fun saveDataToLocal(data: SiteInfoDropDownData) {

        val gson = Gson()
        val stringDatajson = gson.toJson(data)
        AppPreferences.getInstance().saveString(DROPDOWNDATA, stringDatajson)

    }

}