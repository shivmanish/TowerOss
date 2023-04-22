package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.earthing

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
import com.smarthub.baseapplication.databinding.TowerCivilAddEarthingBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.NewTowerCivilAllData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TowerAndCivilInfraEarthing
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilInfraEarthingDetail
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class EarthingAddNew (var fulldata: NewTowerCivilAllData?, var listner: AddEarthingListner): BaseBottomSheetDialogFragment() {
    lateinit var binding : TowerCivilAddEarthingBinding
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TowerCivilAddEarthingBinding.inflate(inflater)
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.65).toInt()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        AppPreferences.getInstance().setDropDown(binding.EarthingCableTypeEdit, DropDowns.EarthingCableType.name)
        AppPreferences.getInstance().setDropDown(binding.PitRodMaterialEdit, DropDowns.PitRodMaterial.name)
        binding.Add.setOnClickListener {
            showProgressLayout()
            val tempEarthingDetailData= TwrCivilInfraEarthingDetail()
            val tempEarthingAllData= TowerAndCivilInfraEarthing()
            val tempTowerCivilAllData= NewTowerCivilAllData()
            tempEarthingDetailData.let {
                it.Height=binding.PitDepthEdit.text.toString()
                it.EarthingCableLength=binding.EarthingCableLengthEdit.text.toString()
                it.SizeL=binding.PitSizeLEdit.text.toString()
                it.SizeB=binding.PitSizeBEdit.text.toString()
                it.SizeH=binding.PitSizeHEdit.text.toString()
                it.LocationMark=binding.LocationMarkEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.PitRodMaterial=binding.PitRodMaterialEdit.selectedValue.id.toIntOrNull()
                it.EarthingCableType=binding.EarthingCableTypeEdit.selectedValue.id.toIntOrNull()
            }
            tempEarthingAllData.TowerAndCivilInfraEarthingEarthingDetail= arrayListOf(tempEarthingDetailData)
            if (fulldata!=null)
                tempTowerCivilAllData.id=fulldata?.id
            tempTowerCivilAllData.TowerAndCivilInfraEarthing= arrayListOf(tempEarthingAllData)
            viewmodel.updateTwrCivilInfra(tempTowerCivilAllData)
        }
        if (viewmodel.updateTwrCivilInfraDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateTwrCivilInfraDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateTwrCivilInfraDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("EarthingAddNew data adding in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                listner.addEarthing()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Added successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("EarthingAddNew Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("EarthingAddNew error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("EarthingAddNew Something went wrong")

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

    interface AddEarthingListner{
        fun addEarthing()
    }
}