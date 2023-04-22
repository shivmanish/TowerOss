package com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqInsidePremisesEditDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.AcquisitionSurveyData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqExternalStructure
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqInsidePremise
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class InsidePremisesEditDialouge (var data: SAcqInsidePremise, var fullData: NewSiteAcquiAllData?, var listner:AcqInsidePremisesUpdateListener,var titelFlag:Int) : BaseBottomSheetDialogFragment(){

    lateinit var binding: AcqInsidePremisesEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AcqInsidePremisesEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (titelFlag<0){
            binding.titleText.text="Add Inside Premises"
            binding.update.text="Add"
        }
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.60).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.DistanceFromCentreEdit.setText(data.DistanceFromCentre)
        binding.HeightAGlEdit.setText(data.Height)
        binding.remarksEdit.setText(data.remark)
        if (data.ExternalStructureType!=null && data.ExternalStructureType?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.ExternalStructureTypeEdit, DropDowns.ExternalStructureType.name,data.ExternalStructureType?.get(0).toString())
        else
            AppPreferences.getInstance().setDropDown(binding.ExternalStructureTypeEdit, DropDowns.ExternalStructureType.name)
        if (data.Direction!=null && data.Direction?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.DirectionFromCentreEdit, DropDowns.Direction.name,data.Direction?.get(0).toString())
        else
            AppPreferences.getInstance().setDropDown(binding.DirectionFromCentreEdit, DropDowns.Direction.name)
        if (data.LocationType!=null && data.LocationType!=null && data.LocationType!!>0)
            AppPreferences.getInstance().setDropDown(binding.LocationTypeEdit, DropDowns.LocationType.name,data.LocationType.toString())
        else
            AppPreferences.getInstance().setDropDown(binding.LocationTypeEdit, DropDowns.LocationType.name)

        binding.update.setOnClickListener {
            showProgressLayout()
            data.let {
                it.DistanceFromCentre=binding.DistanceFromCentreEdit.text.toString()
                it.Height=binding.HeightAGlEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.ExternalStructureType= arrayListOf(binding.ExternalStructureTypeEdit.selectedValue.id.toInt())
                it.LocationType= binding.LocationTypeEdit.selectedValue.id.toInt()
                it.Direction= arrayListOf(binding.DirectionFromCentreEdit.selectedValue.id.toInt())
            }
            val tempExternalStructure=SAcqExternalStructure()
            val tempData= AcquisitionSurveyData()
            val dataModel = UpdateSiteAcquiAllData()
            if (fullData!=null){
                dataModel.id=fullData?.id
                if (fullData?.SAcqAcquitionSurvey!=null && fullData?.SAcqAcquitionSurvey?.isNotEmpty()==true){
                    tempData.id=fullData?.SAcqAcquitionSurvey?.get(0)?.id
                    if (fullData?.SAcqAcquitionSurvey?.get(0)?.SAcqExternalStructure!=null &&
                        fullData?.SAcqAcquitionSurvey?.get(0)?.SAcqExternalStructure?.isNotEmpty()==true)
                        tempExternalStructure.id=fullData?.SAcqAcquitionSurvey?.get(0)?.SAcqExternalStructure?.get(0)?.id

                }
            }
            tempExternalStructure.SAcqInsidePremise= arrayListOf(data)
            tempData.SAcqExternalStructure= arrayListOf(tempExternalStructure)
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
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SAcqInsidePremise==200) {
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


    interface AcqInsidePremisesUpdateListener{
        fun updatedData()
    }

}