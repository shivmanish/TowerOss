package com.smarthub.baseapplication.ui.fragments.noc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocAuthorityItemsBinding
import com.smarthub.baseapplication.databinding.NocCompCommonFragBinding
import com.smarthub.baseapplication.databinding.RfAntinaFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocAuthorityDetail
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.rfAnteena.RfAnteenaItemsEditDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class NocAuthorityDetailsFragment(var nocdata: NocCompAllData?,var childIndex:Int?) : BaseFragment() {

    lateinit var binding : NocAuthorityItemsBinding
    var viewmodel: HomeViewModel?=null
    var datalist: NocAuthorityDetail?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = NocAuthorityItemsBinding.inflate(inflater, container, false)
        if (nocdata!=null && nocdata?.AuthorityDetail?.isNotEmpty()==true)
            datalist=nocdata?.AuthorityDetail?.get(0)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (nocdata!=null && nocdata?.AuthorityDetail?.isNotEmpty()==true){
            datalist=nocdata?.AuthorityDetail?.get(0)
            mapUiData(datalist)
        }
        setObserver()
        binding.imgEdit.setOnClickListener {
            showEditView()
        }
        binding.cancel.setOnClickListener {
            hideEditView()
        }
        binding.update.setOnClickListener {
            val tempAuthorityData=NocAuthorityDetail()
            tempAuthorityData.let {
                it.Name=binding.NameEdit.text.toString()
                it.ContactNumber=binding.ContactNumberEdit.text.toString()
                it.Address=binding.AddressEdit.text.toString()
                it.EmailId=binding.EmailIdEdit.text.toString()
                it.PreferredLangauage=binding.PreferredLaungaugeEdit.text.toString()
                if (datalist!=null)
                    it.id=datalist?.id

                updataDataClicked(it)
            }
        }

    }

    fun setObserver() {
        if (viewmodel?.NocAndCompModelResponse?.hasActiveObservers() == true){
            viewmodel?.NocAndCompModelResponse?.removeObservers(this)
        }
        viewmodel?.NocAndCompModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("NocAuthorityDetailsFragment card Data fetched successfully")
                if (it.data.NOCCompliance?.isNotEmpty()==true && childIndex!=null){
                    AppLogger.log("childIndex===>: $childIndex")
                    nocdata=it.data.NOCCompliance?.get(childIndex!!)
                    AppLogger.log("data fetched for all noc ui======>:${Gson().toJson(nocdata)}")
                    if (nocdata!=null && nocdata?.AuthorityDetail?.isNotEmpty()==true){
                        AppLogger.log("data fetched for ui======>:${nocdata?.AuthorityDetail?.get(0)}")
                        datalist=nocdata?.AuthorityDetail?.get(0)
                        mapUiData(datalist)
                    }
                }
                AppLogger.log("size :${it.data.NOCCompliance?.size}")
//                isDataLoaded = true
            }
            else if (it!=null) {
                AppLogger.log("NocAuthorityDetailsFragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("NocAuthorityDetailsFragment Something went wrong")
            }
        }
    }

    fun mapUiData(authorityData:NocAuthorityDetail?){
        if (authorityData!=null){
            // view Mode
            binding.Name.text=authorityData.Name
            binding.ContactNumber.text=authorityData.ContactNumber
            binding.Address.text=authorityData.Address
            binding.EmailID.text=authorityData.EmailId
            binding.PreferredLaungauge.text=authorityData.PreferredLangauage

            // edit Mode
            binding.NameEdit.setText(authorityData.Name)
            binding.ContactNumberEdit.setText(authorityData.ContactNumber)
            binding.EmailIdEdit.setText(authorityData.EmailId)
            binding.AddressEdit.setText(authorityData.Address)
            binding.PreferredLaungaugeEdit.setText(authorityData.PreferredLangauage)
        }
    }
    fun updataDataClicked(updatedData: NocAuthorityDetail) {
        showLoader()
        val tempNocCompAllData = NocCompAllData()
        tempNocCompAllData.AuthorityDetail= arrayListOf(updatedData)
        if (nocdata!=null)
            tempNocCompAllData.id=nocdata?.id
        viewmodel?.updateNocAndComp(tempNocCompAllData)
        if (viewmodel?.updateNocCompDataResponse?.hasActiveObservers() == true) {
            viewmodel?.updateNocCompDataResponse?.removeObservers(this)
        }
        viewmodel?.updateNocCompDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("NocAuthorityDetailsFragment data updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.NOCCompliance==200) {
                AppLogger.log("NocAuthorityDetailsFragment Data Updated successfully")
                setObserver()
                viewmodel?.NocAndCompRequestAll(AppController.getInstance().siteid)
                hideEditView()
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("NocAuthorityDetailsFragment Something went wrong in data update")
            }
            else if (it != null) {
                AppLogger.log("NocAuthorityDetailsFragment in updateData error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("NocAuthorityDetailsFragment Something went wrong in data update")

            }
        }
    }

    fun showEditView(){
        binding.viewLayout.visibility = View.GONE
        binding.editLayout.visibility = View.VISIBLE
    }
    fun hideEditView(){
        binding.viewLayout.visibility = View.VISIBLE
        binding.editLayout.visibility = View.GONE
    }
}