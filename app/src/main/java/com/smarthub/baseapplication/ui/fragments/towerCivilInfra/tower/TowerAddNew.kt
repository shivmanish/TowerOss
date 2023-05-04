package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tower

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
import com.smarthub.baseapplication.databinding.TowerCivilAddTowerBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.NewTowerCivilAllData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TowerAndCivilInfraTower
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilInfraTowerDetail
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TowerAddNew (var fulldata: NewTowerCivilAllData?,var listner:AddNewTowerListner): BaseBottomSheetDialogFragment() {
    lateinit var binding : TowerCivilAddTowerBinding
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = TowerCivilAddTowerBinding.inflate(inflater)
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        AppPreferences.getInstance().setDropDown(binding.InstalledTypeEdit,DropDowns.InstalledType.name)
        AppPreferences.getInstance().setDropDown(binding.CamouflageEdit,DropDowns.Camouflage.name)
        AppPreferences.getInstance().setDropDown(binding.LightningArresterEdit,DropDowns.LightningArrester.name)
        AppPreferences.getInstance().setDropDown(binding.TowerTypeEdit,DropDowns.TowerPoleType.name)
        AppPreferences.getInstance().setDropDown(binding.FoundationTypeEdit,DropDowns.FoundationType.name)

        binding.Add.setOnClickListener {
            showProgressLayout()
            val tempTowerDetailData= TwrCivilInfraTowerDetail()
            val tempTowerAllData= TowerAndCivilInfraTower()
            val tempTowerCivilAllData= NewTowerCivilAllData()
            tempTowerDetailData.let { 
                it.Height=binding.HeightEdit.text.toString()
                it.Count=binding.LegCountEdit.text.toString().toIntOrNull()
                it.Weight=binding.WeightEdit.text.toString()
                it.DesignedLoad=binding.DesignedLoadEdit.text.toString()
                it.AntennaSlot=binding.AntennaSlotsEdit.text.toString().toIntOrNull()
                it.FoundationSizeL=binding.FoundationSizeLEdit.text.toString()
                it.FoundationSizeB=binding.FoundationSizeBEdit.text.toString()
                it.FoundationSizeH=binding.FoundationSizeHEdit.text.toString()
                it.OffsetPoleCount=binding.OffsetPoleCountEdit.text.toString().toIntOrNull()
                it.OffsetPoleLength=binding.OffsetPoleLengthEdit.text.toString()
                it.LocationMark=binding.LocationMarkEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.InstalledType=binding.InstalledTypeEdit.selectedValue.id.toIntOrNull()
                it.Camouflage=binding.CamouflageEdit.selectedValue.id.toIntOrNull()
                it.LightningArrester=binding.LightningArresterEdit.selectedValue.id.toIntOrNull()
                it.TowerPoleType= arrayListOf(binding.TowerTypeEdit.selectedValue.id.toInt())
                it.FoundationType= arrayListOf(binding.FoundationTypeEdit.selectedValue.id.toInt())
            }
            tempTowerAllData.TowerAndCivilInfraTowerTowerDetail= arrayListOf(tempTowerDetailData)
            if (fulldata!=null)
                tempTowerCivilAllData.id=fulldata?.id
            tempTowerCivilAllData.TowerAndCivilInfraTower= arrayListOf(tempTowerAllData)
            viewmodel.updateTwrCivilInfra(tempTowerCivilAllData)
        }

        if (viewmodel.updateTwrCivilInfraDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateTwrCivilInfraDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateTwrCivilInfraDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("TowerAddNew Fragment data adding in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                listner.addNew()
                hideProgressLayout()
                dismiss()
//                Toast.makeText(context,"Data Added successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
//                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("TowerAddNew Fragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("TowerAddNew Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("TowerAddNew Fragment Something went wrong")

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

    interface AddNewTowerListner{
         fun addNew()
    }
}