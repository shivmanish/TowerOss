package com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqPropertyOwnerEditDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.AcquisitionSurveyData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqPropertyOwnerDetail
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PropertyOwnerEditDialouge (var data: SAcqPropertyOwnerDetail, var fullData: NewSiteAcquiAllData?, var listner:AcqPropertyOwnerUpdateListener, var titelFlag:Int) : BaseBottomSheetDialogFragment(){

    lateinit var binding: AcqPropertyOwnerEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AcqPropertyOwnerEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (titelFlag<0){
            binding.titleText.text="Add Property Owner Details"
            binding.update.text="Add"
        }
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.60).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.ShareEdit.setText(data.Share)
        binding.OwnerNameEdit.setText(data.OwnerName)
        binding.PhNumberEdit.setText(data.PhoneNumber)
        binding.EmailIdEdit.setText(data.EmailId)
        binding.AddressEdit.setText(data.Address)
        binding.remarksEdit.setText(data.remark)
        if (data.PropertyOwnership!=null && data.PropertyOwnership?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.PropertyOwnershipEdit, DropDowns.PropertyOwnership.name,data.PropertyOwnership?.get(0).toString())
        else
            AppPreferences.getInstance().setDropDown(binding.PropertyOwnershipEdit, DropDowns.PropertyOwnership.name)

        binding.update.setOnClickListener {
            showProgressLayout()
            data.let {
                it.Share=binding.ShareEdit.text.toString()
                it.OwnerName=binding.OwnerNameEdit.text.toString()
                it.PhoneNumber=binding.PhNumberEdit.text.toString()
                it.EmailId=binding.EmailIdEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.Address=binding.AddressEdit.text.toString()
                it.PropertyOwnership= arrayListOf(binding.PropertyOwnershipEdit.selectedValue.id.toInt())
            }
            val tempData= AcquisitionSurveyData()
            val dataModel = UpdateSiteAcquiAllData()
            if (fullData!=null){
                dataModel.id=fullData?.id
                if (fullData?.SAcqAcquitionSurvey!=null && fullData?.SAcqAcquitionSurvey?.isNotEmpty()==true)
                    tempData.id=fullData?.SAcqAcquitionSurvey?.get(0)?.id
            }
            tempData.SAcqPropertyOwnerDetail= arrayListOf(data)
            dataModel.SAcqAcquitionSurvey= arrayListOf(tempData)
            viewmodel.updateSiteAcq(dataModel)
        }

        if (viewmodel.updateSiteAcqDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSiteAcqDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSiteAcqDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("SiteAgreemnets Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SAcqPropertyOwnerDetail==200) {
                AppLogger.log("SiteAgreemnets Fragment card Data fetched successfully")
                listner.updatedData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("SiteAgreemnets Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")

            }
        }
        hideProgressLayout()
    }

    override fun getTheme() = R.style.NewDialogTask


    fun showProgressLayout(){
        if (binding.progressLayout.visibility != View.VISIBLE)
            binding.progressLayout.visibility = View.VISIBLE
    }
    fun hideProgressLayout(){
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }


    interface AcqPropertyOwnerUpdateListener{
        fun updatedData()
    }

}