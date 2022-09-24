package com.smarthub.baseapplication.ui.site_detail

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.smarthub.baseapplication.DashboardFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SiteLocationDetailBinding
import com.smarthub.baseapplication.databinding.TabItemBinding
import com.smarthub.baseapplication.popupmenu.EditPopMenu
import com.smarthub.baseapplication.viewmodels.MainViewModel


class SiteDetailFragment : Fragment() {
    private var _sitebinding: SiteLocationDetailBinding? = null
    private val binding get() = _sitebinding!!
    private lateinit var ctx: Context
    private lateinit var siteDetailViewModel: SiteDetailViewModel
    private lateinit var mainViewModel: MainViewModel
    private val editPopMenu = EditPopMenu()
    var fabVisible = false
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.ctx = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        siteDetailViewModel = ViewModelProvider(requireActivity())[SiteDetailViewModel::class.java]
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel.isActionBarHide(true)
        _sitebinding = SiteLocationDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupViewPager(binding.viewpager)

        binding.tabs!!.setupWithViewPager(binding.viewpager)
//        binding.viewpager.offscreenPageLimit= 5
        binding.viewpager.currentItem = 0
        setCustomTab(root)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        setFabActionButton()

        return root
    }

    fun setFabActionButton() {
        // for our add floating action button
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

    fun setCustomTab(view: View) {
        var v: TabItemBinding? = null
        var tabNames = siteDetailViewModel.getStrings(requireActivity())
        var typedImages = siteDetailViewModel.getImageArray(requireActivity())
        for (i in 0..tabNames.size - 1) {
            v = TabItemBinding.inflate(layoutInflater)
            val texttab: AppCompatTextView = v.textTab
            val imagetab: AppCompatImageView = v.tabImage
            texttab.text = tabNames.get(i)
            imagetab.setImageResource(typedImages.getResourceId(i, 0))
            binding.tabs.getTabAt(i)?.customView = v.root
        }
        var constraintLayout: ConstraintLayout =
            binding.tabs.getTabAt(0)?.customView!!.findViewById(R.id.parent_id)
        constraintLayout.backgroundTintList =
            ColorStateList.valueOf(resources.getColor(R.color.tab_selected_color))
    }

    private fun setupViewPager(viewPager: ViewPager) {

        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        adapter.addFragment(DashboardFragment.newInstance("A"))
        adapter.addFragment(DashboardFragment.newInstance("B"))
        adapter.addFragment(DashboardFragment.newInstance("C"))
        adapter.addFragment(DashboardFragment.newInstance("D"))
        adapter.addFragment(DashboardFragment.newInstance("E"))

        viewPager.adapter = adapter

        binding.tabs.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                viewPager.currentItem = tab.position
                var view: View? = tab.customView
                if (view != null) {
                    var constraintLayout: ConstraintLayout = view!!.findViewById(R.id.parent_id)
                    constraintLayout.backgroundTintList =
                        ColorStateList.valueOf(resources.getColor(R.color.tab_selected_color))
                }
                Log.e("TAG", " $view  ${tab.position}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                var view: View? = tab.customView
                var constraintLayout: ConstraintLayout = view!!.findViewById(R.id.parent_id)
                constraintLayout.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.white))

            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) :
        FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
//        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment) {
            mFragmentList.add(fragment)
//            mFragmentTitleList.add(title)
        }

        /*  override fun getPageTitle(position: Int): CharSequence {
              return mFragmentTitleList[position]
          }*/
    }

    fun setSelectTab() {

    }
}