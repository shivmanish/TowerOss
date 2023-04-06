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

class OutSidePremisesEditDialouge (var data: SAcqOutsidePremise, var fullData: NewSiteAcquiAllData?, var listner:AcqOutsidePremisesUpdateListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: AcqOutSidePremisesEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AcqOutSidePremisesEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.60).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.DistanceFromBoundaryEdit.setText(data.DistanceFromBoundry)
        binding.HeightAGlEdit.setText(data.Height)
        binding.remarksEdit.setText(data.remark)
        if (data.ExternalStructureType.isNotEmpty())
            AppPreferences.getInstance().setDropDown(binding.ExternalStructureTypeEdit, DropDowns.ExternalStructureType.name,data.ExternalStructureType[0].toString())
        else
            AppPreferences.getInstance().setDropDown(binding.ExternalStructureTypeEdit, DropDowns.ExternalStructureType.name)
        if (data.Direction.isNotEmpty())
            AppPreferences.getInstance().setDropDown(binding.DirectionFromCentreEdit, DropDowns.Direction.name,data.Direction[0].toString())
        else
            AppPreferences.getInstance().setDropDown(binding.DirectionFromCentreEdit, DropDowns.Direction.name)

        binding.update.setOnClickListener {
            showProgressLayout()
            if (fullData?.SAcqAcquitionSurvey==null || fullData?.SAcqAcquitionSurvey?.isEmpty()==true){
                data.let {
                    it.DistanceFromBoundry=binding.DistanceFromBoundaryEdit.text.toString()
                    it.Height=binding.HeightAGlEdit.text.toString()
                    it.remark=binding.remarksEdit.text.toString()
                    it.ExternalStructureType= arrayListOf(binding.ExternalStructureTypeEdit.selectedValue.id.toInt())
                    it.Direction= arrayListOf(binding.DirectionFromCentreEdit.selectedValue.id.toInt())
                    // add OutSidePremises data to agreement model list
                    val tempList:ArrayList<SAcqOutsidePremise> = ArrayList()
                    tempList.clear()
                    tempList.add(it)
                    // add data in External Structure
                    val tempExternalStructure=SAcqExternalStructure()
                    tempExternalStructure.SAcqOutsidePremise=tempList
                    // add data in AcqSurvey Data
                    val tempData= AcquisitionSurveyData()
                    tempData.SAcqExternalStructure= arrayListOf(tempExternalStructure)
                    //add add agreement model to update rootModel
                    val dataModel = UpdateSiteAcquiAllData()
                    val tempList2:ArrayList<AcquisitionSurveyData> =ArrayList()
                    tempList2.clear()
                    tempList2.add(tempData)
                    dataModel.SAcqAcquitionSurvey=tempList2
                    dataModel.id=fullData?.id
                    viewmodel.updateSiteAcq(dataModel)
                }
            }
            else if (fullData?.SAcqAcquitionSurvey?.get(0)?.SAcqExternalStructure==null ||
                fullData?.SAcqAcquitionSurvey?.get(0)?.SAcqExternalStructure?.isEmpty()==true ){
                data.let {
                    it.DistanceFromBoundry=binding.DistanceFromBoundaryEdit.text.toString()
                    it.Height=binding.HeightAGlEdit.text.toString()
                    it.remark=binding.remarksEdit.text.toString()
                    it.ExternalStructureType= arrayListOf(binding.ExternalStructureTypeEdit.selectedValue.id.toInt())
                    it.Direction= arrayListOf(binding.DirectionFromCentreEdit.selectedValue.id.toInt())
                    // add OutSidePremises data to agreement model list
                    val tempList:ArrayList<SAcqOutsidePremise> = ArrayList()
                    tempList.clear()
                    tempList.add(it)
                    // add data in External Structure
                    val tempExternalStructure=SAcqExternalStructure()
                    tempExternalStructure.SAcqOutsidePremise=tempList
                    // add data in AcqSurvey Data
                    val tempData= AcquisitionSurveyData()
                    tempData.id=fullData?.SAcqAcquitionSurvey?.get(0)?.id
                    tempData.SAcqExternalStructure= arrayListOf(tempExternalStructure)
                    //add add agreement model to update rootModel
                    val dataModel = UpdateSiteAcquiAllData()
                    val tempList2:ArrayList<AcquisitionSurveyData> =ArrayList()
                    tempList2.clear()
                    tempList2.add(tempData)
                    dataModel.SAcqAcquitionSurvey=tempList2
                    dataModel.id=fullData?.id
                    viewmodel.updateSiteAcq(dataModel)
                }
            }
            else{
                data.let {
                    it.DistanceFromBoundry=binding.DistanceFromBoundaryEdit.text.toString()
                    it.Height=binding.HeightAGlEdit.text.toString()
                    it.remark=binding.remarksEdit.text.toString()
                    if (data.ExternalStructureType.isNotEmpty())
                        it.ExternalStructureType[0]=binding.ExternalStructureTypeEdit.selectedValue.id.toInt()
                    else
                        it.ExternalStructureType.add(binding.ExternalStructureTypeEdit.selectedValue.id.toInt())
                    if (data.Direction.isNotEmpty())
                        it.Direction[0]=binding.DirectionFromCentreEdit.selectedValue.id.toInt()
                    else
                        it.Direction.add(binding.DirectionFromCentreEdit.selectedValue.id.toInt())

                    // add OutSidePremises data to agreement model list
                    val tempList:ArrayList<SAcqOutsidePremise> = ArrayList()
                    tempList.clear()
                    tempList.add(it)
                    // add data in External Structure
                    val tempExternalStructure=SAcqExternalStructure()
                    tempExternalStructure.SAcqOutsidePremise=tempList
                    tempExternalStructure.id=fullData?.SAcqAcquitionSurvey?.get(0)?.SAcqExternalStructure?.get(0)?.id
                    // add data in AcqSurvey Data
                    val tempData= AcquisitionSurveyData()
                    tempData.id=fullData?.SAcqAcquitionSurvey?.get(0)?.id
                    tempData.SAcqExternalStructure= arrayListOf(tempExternalStructure)
                    //add add agreement model to update rootModel
                    val dataModel = UpdateSiteAcquiAllData()
                    val tempList2:ArrayList<AcquisitionSurveyData> =ArrayList()
                    tempList2.clear()
                    tempList2.add(tempData)
                    dataModel.SAcqAcquitionSurvey=tempList2
                    dataModel.id=fullData?.id
                    viewmodel.updateSiteAcq(dataModel)
                }
            }
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