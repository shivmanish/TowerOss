package com.smarthub.baseapplication.ui.site_agreement

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentSiteLeaseAcquitionBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.Siteacquisition
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo.PowerAndFuel
import com.smarthub.baseapplication.ui.site_agreement.adapter.SiteLeaseDataAdapter
import com.smarthub.baseapplication.ui.site_agreement.adapter.SiteLeaseDataAdapterListener
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel


class SiteAgreementFragment(var id : String) : BaseFragment(), SiteLeaseDataAdapterListener {

    var isDataLoaded = false
    lateinit var viewmodel: HomeViewModel
    lateinit var siteLeaseDataAdapter: SiteLeaseDataAdapter
    lateinit var fragmentSiteLeaseBinding: FragmentSiteLeaseAcquitionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSiteLeaseBinding = FragmentSiteLeaseAcquitionBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return fragmentSiteLeaseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentSiteLeaseBinding.siteLeaseListItem.layoutManager = LinearLayoutManager(requireContext())
        siteLeaseDataAdapter = SiteLeaseDataAdapter(this@SiteAgreementFragment, id)
        fragmentSiteLeaseBinding.siteLeaseListItem.adapter = siteLeaseDataAdapter


        viewmodel.fetchDropDown()


        fragmentSiteLeaseBinding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")
        }
        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true){
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.siteAgreementModel?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                AppLogger.log("SiteAgreemnets Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                AppLogger.log("SiteAgreemnets Fragment card Data fetched successfully")
                try {
                    siteLeaseDataAdapter.setData(it.data.item!![0].Siteacquisition)
                }catch (e:java.lang.Exception){
                    AppLogger.log("SiteAgreemnets Fragment error : ${e.localizedMessage}")
                    Toast.makeText(context,"SiteAgreemnets Fragment error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
                AppLogger.log("SiteAgreemnets size :${it.data.item!![0].Siteacquisition.size}")
                isDataLoaded = true
            }else if (it!=null) {
                Toast.makeText(requireContext(),"NocAndComp Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("SiteAgreemnets Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")
                Toast.makeText(requireContext(),"SiteAgreemnets Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewPageSelected() {
        super.onViewPageSelected()
        if (viewmodel!=null || !isDataLoaded){
            siteLeaseDataAdapter.addLoading()
            viewmodel.fetchPowerAndFuel(id)
        }
        AppLogger.log("onViewPageSelected PowerAndFuel")
    }

    override fun onDestroy() {
        if (viewmodel.powerAndFuelResponse?.hasActiveObservers() == true){
            viewmodel.powerAndFuelResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

     override fun clickedItem(data: Siteacquisition) {
         SiteAgreementCaredItemActivity.siteacquisition = data
        val intent = Intent(requireContext(), SiteAgreementCaredItemActivity::class.java)
         requireActivity().startActivity(intent)
    }
}