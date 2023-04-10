package com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerFuelTarrifEditDialougeBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerConnectionAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelTariffDetails
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerFuelTarrifEditDialouge (var data: PowerFuelTariffDetails, var fullData: NewPowerFuelAllData?, var listner:PowerFuelTarrifUpdateListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: PowerFuelTarrifEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PowerFuelTarrifEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.50).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.AverageConsUnitEdit.setText(data.AverageConsumableUnit)
        binding.ApplicableTarrifRateEdit.setText(data.TariffRate)
        binding.TarrifEffectiveDateEdit.text=Utils.getFormatedDate(data.TarrifEffectiveDate,"dd-MMM-yyyy")
        setDatePickerView( binding.TarrifEffectiveDateEdit)

        binding.update.setOnClickListener {
            showProgressLayout()
            val tempPowerConnAllData= PowerConnectionAllData()
            val dataModel = NewPowerFuelAllData()
            data.let {
                it.AverageConsumableUnit=binding.AverageConsUnitEdit.text.toString()
                it.TariffRate=binding.ApplicableTarrifRateEdit.text.toString()
                it.TarrifEffectiveDate=Utils.getFullFormatedDate(binding.TarrifEffectiveDateEdit.text.toString())
                tempPowerConnAllData.PowerAndFuelEBConnectionTariffDetail= arrayListOf(it)
            }
            if (fullData!=null){
                dataModel.id=fullData?.id
                if (fullData?.PowerAndFuelEBConnection?.isNotEmpty()==true && fullData?.PowerAndFuelEBConnection?.get(0)!=null)
                    tempPowerConnAllData.id=fullData?.PowerAndFuelEBConnection?.get(0)?.id
            }
            dataModel.PowerAndFuelEBConnection= arrayListOf(tempPowerConnAllData)
            viewmodel.updatePowerFuel(dataModel)

        }

        if (viewmodel.updatePowerFuelDataResponse?.hasActiveObservers() == true) {
            viewmodel.updatePowerFuelDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updatePowerFuelDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("PowerFuelTarrifEditDialouge data Updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status?.PowerAndFuelEBConnection==200) {
                AppLogger.log("PowerFuelTarrifEditDialouge card Data Updated successfully")
                listner.updatedData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("PowerFuelTarrifEditDialouge Something went wrong in updating data")
            }
            else if (it != null) {
                AppLogger.log("PowerFuelTarrifEditDialouge error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("PowerFuelTarrifEditDialouge Something went wrong in updating data")

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


    interface PowerFuelTarrifUpdateListener{
        fun updatedData()
    }

}