package com.smarthub.baseapplication.ui.fragments.services_request

import android.os.Bundle
import android.view.View
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.NocActivityDetailsBinding
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.fragments.services_request.editdialouge.NocApplicationDetailsDialouge
import com.smarthub.baseapplication.ui.fragments.services_request.editdialouge.NocAuthorityDetailsDialouge
import com.smarthub.baseapplication.ui.utilites.editdialouge.BatteryEquipmentDialouge
import com.smarthub.baseapplication.ui.utilites.editdialouge.InstalationAcceptanceDialouge
import com.smarthub.baseapplication.utils.Utils

class NocDetailsActivity : BaseActivity() {
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    lateinit var binding : NocActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NocActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initViews()
        setView()
    }

    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }

    }
    fun setView(){
        binding.applicationDetailsEdit.setOnClickListener{
            val dalouge = NocApplicationDetailsDialouge()
            dalouge.show(supportFragmentManager,"")
        }
        binding.authorityEdit.setOnClickListener{
            val dalouge = NocAuthorityDetailsDialouge()
            dalouge.show(supportFragmentManager,"")
        }
        binding.applicationDetailsRoot.setOnClickListener {
            if(binding.itemCollapseApplicationStatus.visibility == View.VISIBLE){
                Utils.collapse(binding.itemCollapseApplicationStatus)
                binding.applicationDetailsArrow.rotation = 0f
                binding.applicationDetailsEdit.visibility = View.GONE
                binding.applicationDetailsRoot.isSelected = false
            }else{
                Utils.expand(binding.itemCollapseApplicationStatus)
                binding.applicationDetailsRoot.isSelected = true
                binding.applicationDetailsArrow.rotation = 180f
                binding.applicationDetailsEdit.visibility = View.VISIBLE
            }
        }

        binding.authorityRoot.setOnClickListener {
            if(binding.itemCollapseAuthorityDetails.visibility == View.VISIBLE){
                Utils.collapse(binding.itemCollapseAuthorityDetails)
                binding.authorityRoot.isSelected = false
                binding.authorityArrow.rotation = 0f
                binding.authorityEdit.visibility = View.GONE
            }else{
                Utils.expand(binding.itemCollapseAuthorityDetails)
                binding.authorityRoot.isSelected = true
                binding.authorityArrow.rotation = 180f
                binding.authorityEdit.visibility = View.VISIBLE
            }
        }

    }



}