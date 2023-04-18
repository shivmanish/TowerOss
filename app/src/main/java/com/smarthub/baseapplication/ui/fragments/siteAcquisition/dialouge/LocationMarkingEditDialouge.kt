package com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqLocationMarkingEditDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.*
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class LocationMarkingEditDialouge (var data: SAcqLocationMarking, var fullData: NewSiteAcquiAllData?, var listner:AcqLocationMarkUpdateListener, var titelFlag:Int) : BaseBottomSheetDialogFragment(){

    lateinit var binding: AcqLocationMarkingEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AcqLocationMarkingEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (titelFlag<0){
            binding.titleText.text="Add Location Marking for key"
            binding.update.text="Add"
        }
        if (titelFlag in 0..4){
            binding.ObjectsEdit.isEnabled = false
            binding.ObjectsEdit.isClickable = false
            binding.ObjectsEdit.isFocusable = false
        }
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.70).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.ObjectsEdit.setText(data.Object)
        binding.DistanceAEdit.setText(data.DistanceA)
        binding.DistanceBEdit.setText(data.DistanceB)
        binding.remarksEdit.setText(data.remark)
        if (data.DirectionA!=null && data.DirectionA?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.BoundaryInDirectionAEdit, DropDowns.Direction.name,data.DirectionA?.get(0).toString())
        else
            AppPreferences.getInstance().setDropDown(binding.BoundaryInDirectionAEdit, DropDowns.Direction.name)
        if (data.DirectionB!=null && data.DirectionB?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.BoundaryInDirectionBEdit, DropDowns.Direction.name,data.DirectionB?.get(0).toString())
        else
            AppPreferences.getInstance().setDropDown(binding.BoundaryInDirectionBEdit, DropDowns.Direction.name)
        if (data.MarkedInLayout!=null && data.MarkedInLayout!!>0)
            AppPreferences.getInstance().setDropDown(binding.MarkedInLayoutEdit, DropDowns.YesNoDropdown.name,data.MarkedInLayout.toString())
        else
            AppPreferences.getInstance().setDropDown(binding.MarkedInLayoutEdit, DropDowns.YesNoDropdown.name)

        binding.update.setOnClickListener {
            showProgressLayout()
            data.let {
                it.Object=binding.ObjectsEdit.text.toString()
                it.DistanceA=binding.DistanceAEdit.text.toString()
                it.DistanceB=binding.DistanceBEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.DirectionA= arrayListOf(binding.BoundaryInDirectionAEdit.selectedValue.id.toInt())
                it.MarkedInLayout= binding.MarkedInLayoutEdit.selectedValue.id.toIntOrNull()
                it.DirectionB= arrayListOf(binding.BoundaryInDirectionBEdit.selectedValue.id.toInt())
            }
            val tempFeasibilityDetail=SAcqFeasibilityDetail()
            val tempData= AcquisitionSurveyData()
            val dataModel = UpdateSiteAcquiAllData()
            if (fullData!=null){
                dataModel.id=fullData?.id
                if (fullData?.SAcqAcquitionSurvey!=null && fullData?.SAcqAcquitionSurvey?.isNotEmpty()==true){
                    tempData.id=fullData?.SAcqAcquitionSurvey?.get(0)?.id
                    if (fullData?.SAcqAcquitionSurvey?.get(0)?.SAcqFeasibilityDetail!=null &&
                        fullData?.SAcqAcquitionSurvey?.get(0)?.SAcqFeasibilityDetail?.isNotEmpty()==true)
                        tempFeasibilityDetail.id=fullData?.SAcqAcquitionSurvey?.get(0)?.SAcqFeasibilityDetail?.get(0)?.id

                }
            }
            tempFeasibilityDetail.SAcqLocationMarking= arrayListOf(data)
            tempData.SAcqFeasibilityDetail= arrayListOf(tempFeasibilityDetail)
            dataModel.SAcqAcquitionSurvey= arrayListOf(tempData)
            viewmodel.updateSiteAcq(dataModel)
        }

        if (viewmodel.updateSiteAcqDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSiteAcqDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSiteAcqDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("LocationMarkingEditDialouge data updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SAcqFeasibilityDetail==200) {
                AppLogger.log("LocationMarkingEditDialouge card Data updated successfully")
                listner.updatedData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("LocationMarkingEditDialouge Something went wrong in updating data")
            }
            else if (it != null) {
                AppLogger.log("LocationMarkingEditDialouge error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("LocationMarkingEditDialouge Something went wrong in updating data")

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


    interface AcqLocationMarkUpdateListener{
        fun updatedData()
    }

}