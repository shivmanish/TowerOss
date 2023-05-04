package com.smarthub.baseapplication.ui.site_agreement

import android.os.Bundle
import com.smarthub.baseapplication.R
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityNewSiteAcquisitionBinding
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.Siteacquisition
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.site_agreement.adapter.SiteAgreemetAdapter

class SiteAgreementCaredItemActivity : BaseActivity() {


    companion object {
        var siteacquisition: Siteacquisition? = null
        var Id: String = "430"
        var index: Int? = 0
    }

    lateinit var binding: ActivityNewSiteAcquisitionBinding
//    lateinit var viewmodel: HomeViewModel
    lateinit var adapter: SiteAgreemetAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewSiteAcquisitionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.addMore.setOnClickListener {
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager, "")
        }

//        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true) {
//            viewmodel.siteAgreementModel?.removeObservers(this)
//        }
//        viewmodel.siteAgreementModel?.observe(this) {
//            if (it != null && it.status == Resource.Status.LOADING) {
//                return@observe
//            }
//            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
//                AppLogger.log("planDesign Fragment card Data fetched successfully")
//                adapter.updateData(
//                    it.data.item!![0].Siteacquisition.get(
//                        SiteAgreementCaredItemActivity.index!!
//                    )
//                )
//                AppLogger.log("size :${it.data.item?.size}")
//            } else if (it != null) {
//                Toast.makeText(
//                    this,
//                    "planDesign Fragment error :${it.message}, data : ${it.data}",
//                    Toast.LENGTH_SHORT
//                ).show()
//                AppLogger.log("planDesign Fragment error :${it.message}, data : ${it.data}")
//            } else {
//                AppLogger.log("planDesign Fragment Something went wrong")
//                Toast.makeText(
//                    this,
//                    "planDesign Fragment Something went wrong",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }

    }


    private fun initViews() {
        adapter = SiteAgreemetAdapter(
            supportFragmentManager,
            SiteAgreementCaredItemActivity.siteacquisition,
            SiteAgreementCaredItemActivity.Id
        )
        binding.viewpager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewpager)

    }

    override fun onDestroy() {
//        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true) {
//            viewmodel.siteAgreementModel?.removeObservers(this)
//        }
        super.onDestroy()
    }

}