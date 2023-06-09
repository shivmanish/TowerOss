package com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqInsidePremisesEditDialougeBinding
import com.smarthub.baseapplication.databinding.AcqOutSidePremisesEditDialougeBinding
import com.smarthub.baseapplication.databinding.AcqPoEditDialougeBinding
import com.smarthub.baseapplication.databinding.AcqPropertyOwnerEditDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.*
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.Opcoinfo
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class OutSidePremisesEditDialouge (var data: SAcqOutsidePremise, var fullData: NewSiteAcquiAllData?, var listner:AcqOutsidePremisesUpdateListener,var titelFlag:Int) : BaseBottomSheetDialogFragment(){

    lateinit var binding: AcqOutSidePremisesEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AcqOutSidePremisesEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (titelFlag<0){
            binding.titleText.text="Add OutSide Premises"
            binding.update.text="Add"
        }
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.60).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.DistanceFromBoundaryEdit.setText(data.DistanceFromBoundry)
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

        if (data.MajorShadowCasting!=null && data.MajorShadowCasting!!>0)
            AppPreferences.getInstance().setDropDown(binding.MajorShadowCastingEdit, DropDowns.MajorShadowCasting.name,data.MajorShadowCasting.toString())
        else
            AppPreferences.getInstance().setDropDown(binding.MajorShadowCastingEdit, DropDowns.MajorShadowCasting.name)

        binding.update.setOnClickListener {
            showProgressLayout()
            data.let {
                it.DistanceFromBoundry=binding.DistanceFromBoundaryEdit.text.toString()
                it.Height=binding.HeightAGlEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.ExternalStructureType= arrayListOf(binding.ExternalStructureTypeEdit.selectedValue.id.toInt())
                it.Direction= arrayListOf(binding.DirectionFromCentreEdit.selectedValue.id.toInt())
                it.MajorShadowCasting=binding.MajorShadowCastingEdit.selectedValue.id.toIntOrNull()
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
            tempExternalStructure.SAcqOutsidePremise= arrayListOf(data)
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
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SAcqOutsidePremise==200) {
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


    interface AcqOutsidePremisesUpdateListener{
        fun updatedData()
    }

}