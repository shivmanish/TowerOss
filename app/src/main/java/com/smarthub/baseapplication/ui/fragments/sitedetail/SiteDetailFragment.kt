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
import android.widget.Toast
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
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
import com.smarthub.baseapplication.popupmenu.EditPopMenu
import com.smarthub.baseapplication.ui.basic_info.SiteImages
import com.smarthub.baseapplication.ui.fragments.customer_tab.OpcoTanacyFragment
import com.smarthub.baseapplication.ui.fragments.customer_tab.SiteInfoNewFragment
import com.smarthub.baseapplication.ui.site_lease_acquisition.SiteLeaseAcqusitionFragment
import com.smarthub.baseapplication.ui.utilites.fragment.UtilitiesNocMainTabFragment
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.viewmodels.MainViewModel


class SiteDetailFragment : Fragment() {
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
//        siteDetailViewModel.isScrollUp.observe(requireActivity(), Observer {
//
//            if (it && isScroll) {
//                Log.d("scroll>>>", "up   $it")
//                setSelectTab(it)
//                isScroll = false
//            } else if (!it && !isScroll) {
//                Log.d("scroll>>>", "down   $it")
//                setSelectTab(it)
//                isScroll = true
//            }
//        })

        setDataObserver()
        setData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
                var view: View? = tab.customView
                var constraintLayout: ConstraintLayout = view!!.findViewById(R.id.parent_id)
                constraintLayout.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tab_selected_color))
                var constraintLay: ConstraintLayout = view!!.findViewById(R.id.parent_tab_child)
                var texttabchange = constraintLay.getChildAt(0).findViewById<AppCompatTextView>(R.id.txt_tab)
                texttabchange.setTextColor(resources.getColor(R.color.tab_selected_color))
                Log.e("TAG", " $view  ${tab.position}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
//                viewPager.currentItem = tab.position
                var view: View? = tab.customView
                var constraintLayout: ConstraintLayout = view!!.findViewById(R.id.parent_id)
                constraintLayout.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                var constraintLay: ConstraintLayout = view!!.findViewById(R.id.parent_tab_child)
                var texttabchange = constraintLay.getChildAt(0).findViewById<AppCompatTextView>(R.id.txt_tab)
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
                        var constraintLayout: ConstraintLayout =
                            binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_id)
                        var constraintL: ConstraintLayout =
                            binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_tab_child)

                        constraintL.alpha = offset
                        constraintLayout.alpha = (1 - offset)
                    }
                    Log.d("offsetvalues>>>onOffsetChanged>", "${offset}  ${1 - offset}")

                }
            }

            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                if (state === State.COLLAPSED) {
//						mToolbarTextView.setAlpha(1);
                    for (i in 0..tabNames?.size!!.minus(1)) {
                        var parentconstraintLayout: ConstraintLayout =
                            binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_id)
                        var childconstraint: ConstraintLayout =
                            binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_tab_child)
                        parentconstraintLayout?.visibility = View.GONE
                        childconstraint?.visibility = View.VISIBLE
//                            constraintL.alpha= 1f
//                            constraintLayout.alpha = 0f
                    }
                    Log.d("offsetvalues>>>onOffsetChanged>", "${state}")
                } else if (state === State.EXPANDED) {
//						mToolbarTextView.setAlpha(0);
                    for (i in 0..tabNames?.size!!.minus(1)) {
                        var parentconstraintLayout: ConstraintLayout =
                            binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_id)
                        var childconstraint: ConstraintLayout =
                            binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_tab_child)
                        parentconstraintLayout?.visibility = View.VISIBLE
                        childconstraint?.visibility = View.GONE
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

        var typedImages = siteDetailViewModel.getImageArray(requireActivity())
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
        var constraintLayout: ConstraintLayout = binding.tabs.getTabAt(0)?.customView!!.findViewById(R.id.parent_id)
        var constraintLay: ConstraintLayout = binding.tabs.getTabAt(0)?.customView!!.findViewById(R.id.parent_tab_child)
        var texttabchange = constraintLay.getChildAt(0).findViewById<AppCompatTextView>(R.id.txt_tab)
        texttabchange.setTextColor(resources.getColor(R.color.tab_selected_color))

        constraintLayout.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.tab_selected_color))
    }

    private fun setupViewPager(viewPager: ViewPager) {


    }


    internal inner class ViewPagerAdapter(manager: FragmentManager,behaviour:Int) :
        FragmentPagerAdapter(manager,behaviour) {

        override fun getItem(position: Int): Fragment {
            return when(position){
                0-> SiteInfoNewFragment()
                1-> OpcoTanacyFragment.newInstance(tabNames?.get(1) ?: "OPCO Tenancy")
                2-> SiteLeaseAcqusitionFragment.newInstance(tabNames?.get(2) ?: "SiteLease")
                3-> BlackhaulFrag.newInstance(tabNames?.get(3) ?: "Blackhaul")
                4-> UtilitiesNocMainTabFragment.newInstance(tabNames?.get(4) ?: "Utilities")
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

    private fun setSelectTab(isUpScroll: Boolean) {
        if (isUpScroll) {
            for (i in 0..tabNames?.size!!.minus(1)) {
                var constraintLayout: ConstraintLayout =
                    binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_id)
                var constraintL: ConstraintLayout =
                    binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_tab_child)
                constraintLayout.visibility = View.GONE
                constraintL.visibility = View.VISIBLE
            }
            binding.tabs!!.background = getDrawable(requireActivity(), R.drawable.tablayout_selector_change);
        } else {
            for (i in 0..tabNames?.size!!.minus(1)) {
                var constraintLayout: ConstraintLayout =
                    binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_id)
                var constraintL: ConstraintLayout =
                    binding.tabs.getTabAt(i)?.customView!!.findViewById(R.id.parent_tab_child)
                constraintLayout.visibility = View.VISIBLE
                constraintL.visibility = View.GONE
            }
            binding.tabs.background = getDrawable( requireActivity(),R.drawable.tablayout_selector)
        }

    }


    private fun setDataObserver() {
        siteDetailViewModel.fetchDropDown()
        if (siteDetailViewModel.dropDownResponse?.hasActiveObservers() == true)
            siteDetailViewModel.dropDownResponse?.removeObservers(viewLifecycleOwner)
        siteDetailViewModel.dropDownResponse?.observe(requireActivity()) {
            if (it != null) {
                if (it.status == Resource.Status.SUCCESS && it.data != null) {
                    saveDataToLocal(it.data)
//                    Toast.makeText(context, "data fetched successfully", Toast.LENGTH_LONG).show()
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

    }

    private fun saveDataToLocal(data: SiteInfoDropDownData) {

        val gson = Gson()
        var stringDatajson = gson.toJson(data)
        AppPreferences.getInstance().saveString(DROPDOWNDATA, stringDatajson)

    }



}