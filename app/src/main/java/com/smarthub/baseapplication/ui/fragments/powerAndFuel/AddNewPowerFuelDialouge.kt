package com.smarthub.baseapplication.ui.fragments.powerAndFuel

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
import com.smarthub.baseapplication.databinding.AddNewPowerFuelDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerConnectionAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerConnectionDetail
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AddNewPowerFuelDialouge (var listner: AddPowerFuelDataListener): BaseBottomSheetDialogFragment() {
    lateinit var binding : AddNewPowerFuelDialougeBinding
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AddNewPowerFuelDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.7).toInt()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        binding.Add.setOnClickListener {
            dismiss()
        }
        AppPreferences.getInstance().setDropDown(binding.MeterTypeEdit, DropDowns.MeterType.name)
        AppPreferences.getInstance().setDropDown(binding.PowerConnectionTypeEdit, DropDowns.PowerConnectionType.name)
        AppPreferences.getInstance().setDropDown(binding.PowerTypeEdit, DropDowns.PowerType.name)

        binding.Add.setOnClickListener {
            showProgressLayout()
            val tempPowerConnDetailData= PowerConnectionDetail()
            val tempPowerConnAllData= PowerConnectionAllData()
            val tempPowerFuelAllData = NewPowerFuelAllData()
            tempPowerConnDetailData.let {
                it.ElectricitySupplier=binding.PowerSupplierEdit.text.toString()
                it.ConsumerNumber=binding.ConsumerNumberEdit.text.toString()
                it.MeterSerialNumber=binding.MeterSerialNumberEdit.text.toString()
                it.VoltageMin=binding.minVoltageRatingEdit.text.toString()
                it.VoltageMax=binding.maxVoltageRatingEdit.text.toString()
                it.PowerRating=binding.PowerConnRatingEdit.text.toString()
                it.ConnectedLoad=binding.ConnectedLoadEdit.text.toString()
                it.MeterLocationMark=binding.MeterLocationMarkEdit.text.toString()
                it.AverageAvailibility=binding.AvgAvailabilityEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.PowerConnectionType= arrayListOf(binding.PowerConnectionTypeEdit.selectedValue.id.toInt())
                it.MeterType= binding.MeterTypeEdit.selectedValue.id.toIntOrNull()
                it.PowerType= binding.PowerTypeEdit.selectedValue.id.toIntOrNull()
            }
            tempPowerConnAllData.PowerAndFuelEBConnectionEBDetail= arrayListOf(tempPowerConnDetailData)
            tempPowerFuelAllData.PowerAndFuelEBConnection= arrayListOf(tempPowerConnAllData)
            viewmodel.updatePowerFuel(tempPowerFuelAllData)
        }

        if (viewmodel.updatePowerFuelDataResponse?.hasActiveObservers() == true) {
            viewmodel.updatePowerFuelDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updatePowerFuelDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING && it.data?.status?.PowerAndFuel==200) {
                showProgressLayout()
                AppLogger.log("AddNewNocCmpDialouge Fragment data adding in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                listner.addNewData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Added successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("AddNewNocCmpDialouge Fragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("AddNewNocCmpDialouge Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("AddNewNocCmpDialouge Fragment Something went wrong")

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

    interface AddPowerFuelDataListener{
        fun addNewData()
    }
}