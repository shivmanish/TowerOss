package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityTwrInfraDetailsBinding
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel

class TwrInfraDetails : BaseActivity() {
    private var profileViewModel : SiteInfoViewModel?=null
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    lateinit var binding : ActivityTwrInfraDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwrInfraDetailsBinding.inflate(layoutInflater)
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
            val ic_send_alert = view.findViewById<CircularRevealCardView>(R.id.send_alert)
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
        binding.viewpager.adapter = TowerPageAdapter(supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewpager)

        profileViewModel= ViewModelProvider(this)[SiteInfoViewModel::class.java]

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