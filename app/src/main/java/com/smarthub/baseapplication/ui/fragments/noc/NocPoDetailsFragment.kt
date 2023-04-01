package com.smarthub.baseapplication.ui.fragments.noc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocCompCommonFragBinding
import com.smarthub.baseapplication.databinding.RfAntinaFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocPODetail
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.rfAnteena.RfAnteenaItemsEditDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class NocPoDetailsFragment(var nocdata: NocCompAllData?,var childIndex:Int?) : BaseFragment(), NocPoDetailsAdapter.NocPoClickListener {

    var binding : NocCompCommonFragBinding?=null
    lateinit var viewmodel: HomeViewModel
    lateinit var adapter: NocPoDetailsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = NocCompCommonFragBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserber()
//        if (nocdata?.PODetail!=null){
            adapter = NocPoDetailsAdapter(this@NocPoDetailsFragment,nocdata?.PODetail,this@NocPoDetailsFragment)
            binding?.listItem?.adapter=adapter
//        }else Toast.makeText(requireContext(),"PODetail null ",Toast.LENGTH_SHORT).show()

        binding?.addItemsLayout?.setOnClickListener {
            updataDataClicked(NocPODetail())
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
                AppLogger.log("NocPoDetailsFragment card Data fetched successfully")
                if (it.data.NOCCompliance?.isNotEmpty()==true && childIndex!=null){
                    AppLogger.log("childIndex===>: $childIndex")
                    nocdata=it.data.NOCCompliance?.get(childIndex!!)
                    adapter.setData(it.data.NOCCompliance?.get(childIndex!!)?.PODetail)
                }
                AppLogger.log("size :${it.data.NOCCompliance?.size}")
//                isDataLoaded = true
            }
            else if (it!=null) {
                AppLogger.log("NocPoDetailsFragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("NocPoDetailsFragment Something went wrong")
            }
        }
    }
    
    override fun attachmentItemClicked() {
    }

    override fun updataDataClicked(updatedData: NocPODetail) {
        showLoader()
        val tempNocCompAllData = NocCompAllData()
        tempNocCompAllData.PODetail= arrayListOf(updatedData)
        if (nocdata!=null)
            tempNocCompAllData.id=nocdata?.id
        viewmodel.updateNocAndComp(tempNocCompAllData)
        if (viewmodel.updateNocCompDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateNocCompDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateNocCompDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("NocPoDetailsFragment data updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.NOCCompliance==200) {
                AppLogger.log("NocPoDetailsFragment Data Updated successfully")
                setObserber()
                viewmodel.NocAndCompRequestAll(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("NocPoDetailsFragment Something went wrong in data update")
            }
            else if (it != null) {
                AppLogger.log("NocPoDetailsFragment in updateData error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("NocPoDetailsFragment Something went wrong in data update")

            }
        }
    }

}