package com.smarthub.baseapplication.ui.fragments.noc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocCompCommonFragBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocApplicationInitial
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.updateNocComp.UpdateNocCompAllData
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.rfAnteena.RfAnteenaItemsEditDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class NocDetailsFragment(var nocdata: NocCompAllData?,var childIndex:Int?) :BaseFragment(), NocApplicationDetailsAdapter.NocApplicationClickListener {

    var binding : NocCompCommonFragBinding?=null
    lateinit var viewmodel: HomeViewModel
    lateinit var adapter: NocApplicationDetailsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = NocCompCommonFragBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserber()
        adapter = NocApplicationDetailsAdapter(this@NocDetailsFragment,nocdata?.ApplicationInitial,this@NocDetailsFragment)
        binding?.listItem?.adapter=adapter

        binding?.addItemsLayout?.setOnClickListener {
            updataDataClicked(NocApplicationInitial())
//            val dalouge = AddNewRfAntennaAdapter(R.layout.rf_anteena_list_item_dialouge)
//            dalouge.show(childFragmentManager,"")
        }
    }


    fun setObserber(){
        if (viewmodel.NocAndCompModelResponse?.hasActiveObservers() == true){
            viewmodel.NocAndCompModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.NocAndCompModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("NocDetailsFragment card Data fetched successfully")
                if (it.data.NOCCompliance?.isNotEmpty()==true && childIndex!=null && childIndex!!<it.data.NOCCompliance?.size!!){
                    AppLogger.log("childIndex===>: $childIndex")
                    nocdata=it.data.NOCCompliance?.get(childIndex!!)
                    adapter.setData(it.data.NOCCompliance?.get(childIndex!!)?.ApplicationInitial)
                }
                AppLogger.log("size :${it.data.NOCCompliance?.size}, childIndex :$childIndex")
//                isDataLoaded = true
            }
            else if (it!=null) {
                AppLogger.log("NocDetailsFragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("NocDetailsFragment Something went wrong")
            }
        }
    }
    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Attachment Item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun updataDataClicked(updatedData: NocApplicationInitial) {
        showLoader()
        val tempNocCompAllData = NocCompAllData()
        tempNocCompAllData.ApplicationInitial= arrayListOf(updatedData)
        if (nocdata!=null)
            tempNocCompAllData.id=nocdata?.id
        viewmodel.updateNocAndComp(tempNocCompAllData)
        if (viewmodel.updateNocCompDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateNocCompDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateNocCompDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("NocDetailsFragment data updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.NOCCompliance==200) {
                AppLogger.log("NocDetailsFragment Data Updated successfully")
                setObserber()
                viewmodel.NocAndCompRequestAll(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("NocDetailsFragment Something went wrong in data update")
            }
            else if (it != null) {
                AppLogger.log("NocDetailsFragment in updateData error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("NocDetailsFragment Something went wrong in data update")

            }
        }
    }

}