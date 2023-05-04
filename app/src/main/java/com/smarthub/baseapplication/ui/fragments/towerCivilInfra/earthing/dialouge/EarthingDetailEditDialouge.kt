package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.earthing.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerEarthingInfoDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.NewTowerCivilAllData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TowerAndCivilInfraEarthing
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilInfraEarthingDetail
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class EarthingDetailEditDialouge(var data: TwrCivilInfraEarthingDetail, var childId: Int?, var parentId: Int?, var listner: EarthingUpdateListner): BaseBottomSheetDialogFragment() {
    lateinit var binding : TowerEarthingInfoDialougeBinding
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = TowerEarthingInfoDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.PitSizeLEdit.setText(data.SizeL)
        binding.PitSizeBEdit.setText(data.SizeB)
        binding.PitSizeHEdit.setText(data.SizeH)
        binding.PitDepthEdit.setText(data.Height)
        binding.EarthingCableLengthEdit.setText(data.EarthingCableLength)
        binding.LocationMarkEdit.setText(data.LocationMark)
        binding.remarksEdit.setText(data.remark)
        if (data.EarthingCableType!=null && data.EarthingCableType!!>0)
            AppPreferences.getInstance().setDropDown(binding.EarthingCableTypeEdit, DropDowns.EarthingCableType.name, data.EarthingCableType.toString())
        else
            AppPreferences.getInstance().setDropDown(binding.EarthingCableTypeEdit, DropDowns.EarthingCableType.name)
        if (data.PitRodMaterial!=null && data.PitRodMaterial!!>0)
            AppPreferences.getInstance().setDropDown(binding.PitRodMaterialEdit, DropDowns.PitRodMaterial.name, data.PitRodMaterial.toString())
        else
            AppPreferences.getInstance().setDropDown(binding.PitRodMaterialEdit, DropDowns.PitRodMaterial.name)
        binding.update.setOnClickListener {
            showProgressLayout()
            val tempEarthingAllData= TowerAndCivilInfraEarthing()
            val tempTowerCivilAllData= NewTowerCivilAllData()
            data.let {
                it.Height=binding.PitDepthEdit.text.toString()
                it.EarthingCableLength=binding.EarthingCableLengthEdit.text.toString()
                it.SizeL=binding.PitSizeLEdit.text.toString()
                it.SizeB=binding.PitSizeBEdit.text.toString()
                it.SizeH=binding.PitSizeHEdit.text.toString()
                it.LocationMark=binding.LocationMarkEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.PitRodMaterial=binding.PitRodMaterialEdit.selectedValue.id.toIntOrNull()
                it.EarthingCableType=binding.EarthingCableTypeEdit.selectedValue.id.toIntOrNull()
                tempEarthingAllData.TowerAndCivilInfraEarthingEarthingDetail= arrayListOf(it)
            }
            if (childId!=null)
                tempEarthingAllData.id=childId
            if (parentId!=null)
                tempTowerCivilAllData.id=id
            tempTowerCivilAllData.TowerAndCivilInfraEarthing= arrayListOf(tempEarthingAllData)
            viewmodel.updateTwrCivilInfra(tempTowerCivilAllData)

        }
        if (viewmodel.updateTwrCivilInfraDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateTwrCivilInfraDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateTwrCivilInfraDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("EarthingDetailEditDialouge data Updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("EarthingDetailEditDialouge card Data Updated successfully")
                listner.updateData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("EarthingDetailEditDialouge Something went wrong in updating data")
            }
            else if (it != null) {
                AppLogger.log("EarthingDetailEditDialouge error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("EarthingDetailEditDialouge Something went wrong in updating data")

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
    
    interface EarthingUpdateListner{
        fun updateData()
    }

}