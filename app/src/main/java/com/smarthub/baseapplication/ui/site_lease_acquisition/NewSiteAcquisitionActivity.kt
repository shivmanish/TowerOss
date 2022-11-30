package com.smarthub.baseapplication.ui.site_lease_acquisition

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityNewSiteAcquisitionBinding
import com.smarthub.baseapplication.databinding.TabNameItemBinding
import com.smarthub.baseapplication.ui.fragments.customer_tab.CustomerPageAdapter
import kotlinx.android.synthetic.main.new_customer_detail_fragment.*
import kotlinx.android.synthetic.main.qat_punch_point_item.view.*
import kotlinx.android.synthetic.main.tab_name_item.view.*

/*activity_new_site_acquisition*/
class NewSiteAcquisitionActivity : BaseActivity() {

    lateinit var binding : ActivityNewSiteAcquisitionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewSiteAcquisitionBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initViews()
    }

    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.viewpager.adapter = SiteLeaseAcquisitionAdapter(supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewpager)
        binding.tabs.setOnTabSelectedListener(onTabSelectedListener(binding.viewpager))
//        binding.viewpager.beginFakeDrag()
        for (i in 0..binding.tabs.tabCount.minus(1)){
            if (i==0)
                binding.tabs.getTabAt(i)?.view?.setBackgroundResource(R.color.white)
            var itemBinding = TabNameItemBinding.inflate(layoutInflater).root
            itemBinding.tab_name.text = viewpager.adapter?.getPageTitle(i)
            itemBinding.tab_name.textSize = 10f
            binding.tabs.getTabAt(i)?.customView = itemBinding.view
        }
        binding.addMore.setOnClickListener(){
            val dialog = BottomSheetDialog(this,R.style.NewDialog)
            // on below line we are inflating a layout file which we have created.
            val view = layoutInflater.inflate(R.layout.site_release_bottom_sheet_dialog_layout, null)
            val close = view.findViewById<ImageView>(R.id.ic_menu_close)

            dialog.window?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
            close.setOnClickListener {
                // on below line we are calling a dismiss
                // method to close our dialog.
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            // on below line we are setting
            // content view to our view.
            dialog.setContentView(view)
            // on below line we are calling
            // a show method to display a dialog.
//            dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_bg)
            dialog.show()
        }
    }

    private fun onTabSelectedListener(pager: ViewPager): TabLayout.OnTabSelectedListener? {
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