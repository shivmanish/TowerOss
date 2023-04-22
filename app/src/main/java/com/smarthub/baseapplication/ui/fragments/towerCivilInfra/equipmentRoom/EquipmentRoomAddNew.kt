package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.equipmentRoom

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerCivilAddEquipmentRoomBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.NewTowerCivilAllData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TowerAndCivilInfraEquipmentRoom
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilEquipmentRoomDetail
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class EquipmentRoomAddNew (var fulldata: NewTowerCivilAllData?, var listner: AddEquipmentRoomListner): BaseBottomSheetDialogFragment() {
    lateinit var binding : TowerCivilAddEquipmentRoomBinding
    lateinit var viewmodel: HomeViewModel
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TowerCivilAddEquipmentRoomBinding.inflate(inflater)
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.6).toInt()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        AppPreferences.getInstance().setDropDown(binding.TypeEdit, DropDowns.EquipmentType.name)
        AppPreferences.getInstance().setDropDown(binding.FoundationTypeEdit, DropDowns.FoundationType.name)
        binding.Add.setOnClickListener {
            showProgressLayout()
            val tempEquipRoomDetailData= TwrCivilEquipmentRoomDetail()
            val tempEquipRoomAllData= TowerAndCivilInfraEquipmentRoom()
            val tempTowerCivilAllData= NewTowerCivilAllData()
            tempEquipRoomDetailData.let {
                it.MakeType=binding.MakeTypeEdit.text.toString()
                it.MaterialUsed=binding.MaterialUsedEdit.text.toString()
                it.SizeL=binding.SizeLEdit.text.toString()
                it.SizeB=binding.SizeBEdit.text.toString()
                it.SizeH=binding.SizeHEdit.text.toString()
                it.FoundationSizeL=binding.FoundationSizeLEdit.text.toString()
                it.FoundationSizeB=binding.FoundationSizeBEdit.text.toString()
                it.FoundationSizeH=binding.FoundationSizeHEdit.text.toString()
                it.LocationMark=binding.LocationMarkEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.Type=binding.TypeEdit.selectedValue.id.toIntOrNull()
                it.FoundationType= arrayListOf(binding.FoundationTypeEdit.selectedValue.id.toInt())
            }
            tempEquipRoomAllData.TowerAndCivilInfraEquipmentRoomEquipmentRoomDetail= arrayListOf(tempEquipRoomDetailData)
            if (fulldata!=null)
                tempTowerCivilAllData.id=fulldata?.id
            tempTowerCivilAllData.TowerAndCivilInfraEquipmentRoom= arrayListOf(tempEquipRoomAllData)
            viewmodel.updateTwrCivilInfra(tempTowerCivilAllData)
        }
        if (viewmodel.updateTwrCivilInfraDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateTwrCivilInfraDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateTwrCivilInfraDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("EquipmentRoomAddNew data adding in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                listner.addEquipmentRoom()
                hideProgressLayout()
                dismiss()
//                Toast.makeText(context,"Data Added successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
//                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("EquipmentRoomAddNew Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("EquipmentRoomAddNew error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("EquipmentRoomAddNew Something went wrong")

            }
        }
        hideProgressLayout()
    }

    override fun getTheme() = R.style.NewDialogTask


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }

    fun showProgressLayout(){
        if (Utils.isNetworkConnected()) {
            if (binding.progressLayout.visibility != View.VISIBLE)
                binding.progressLayout.visibility = View.VISIBLE
        }
    }
    fun hideProgressLayout(){
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }

    interface AddEquipmentRoomListner{
        fun addEquipmentRoom()
    }
}