package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityTowerEarthingFragmentBinding
import com.smarthub.baseapplication.databinding.ActivityTowerPoleFragemntBinding
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel

class TowerEarthingFragment : BaseActivity() {
    private var profileViewModel : SiteInfoViewModel?=null
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    lateinit var binding : ActivityTowerEarthingFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTowerEarthingFragmentBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initViews()
    }

    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.addMore.setOnClickListener(){
            val dialog = BottomSheetDialog(this,R.style.NewDialog)
            // on below line we are inflating a layout file which we have created.
            val view = layoutInflater.inflate(R.layout.main_memu_bottom_sheet_dialog_layout, null)
            val close = view.findViewById<CircularRevealCardView>(R.id.ic_menu_close)
            val ic_menu_call = view.findViewById<CircularRevealCardView>(R.id.ic_menu_call)
            val ic_map_view = view.findViewById<CircularRevealCardView>(R.id.ic_map_view)
            val ic_send_alert = view.findViewById<CircularRevealCardView>(R.id.ic_send_alert)
            val ic_menu_open_faults = view.findViewById<CircularRevealCardView>(R.id.ic_menu_open_faults)
            val ic_menu_escalations = view.findViewById<CircularRevealCardView>(R.id.ic_menu_escalations)
            val ic_menu_picture = view.findViewById<CircularRevealCardView>(R.id.ic_menu_picture)
            val ic_pm_task = view.findViewById<CircularRevealCardView>(R.id.ic_pm_task)
            val ic_menu_logs = view.findViewById<CircularRevealCardView>(R.id.ic_menu_logs)
            dialog.window?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
            close.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }
        binding.viewpager.adapter = TowerEarthingAdapter(supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewpager)
//        binding.tabs.setOnTabSelectedListener(onTabSelectedListener(binding.viewpager))
//        binding.viewpager.beginFakeDrag()
//        for (i in 0..binding.tabs.tabCount.minus(1)){
//            if (i==0)
//                binding.tabs.getTabAt(i)?.view?.setBackgroundResource(R.color.white)
//            var itemBinding = TabNameItemBinding.inflate(layoutInflater)
//            itemBinding.tabName.text = binding.viewpager.adapter?.getPageTitle(i)
//            itemBinding.tabName.textSize = 15f
//            binding.tabs.getTabAt(i)?.customView = itemBinding.root
//        }

        profileViewModel= ViewModelProvider(this)[SiteInfoViewModel::class.java]

//        profileViewModel?.profileResponse?.observe(this) {
//            hideLoader()
//            if (it != null) {
//                if (it.status == Resource.Status.SUCCESS && it.data != null) {
//                    mapUiData(it.data)
//                    return@observe
//                } else {
//                    Log.d("status", "${it.message}")
//                    Toast.makeText(this@NewCustomerDetailsActivity, "error:" + it.message, Toast.LENGTH_LONG).show()
//                }
//            } else {
//                Log.d("status", AppConstants.GENERIC_ERROR)
//                Toast.makeText(this@NewCustomerDetailsActivity, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
//            }
//        }
    }

    private fun onTabSelectedListener(pager: ViewPager): TabLayout.OnTabSelectedListener {
        return object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.color.white)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.color.tab_deselected)
            }
            override fun onTabReselected(tab: TabLayout.Tab) {}
        }
    }

}