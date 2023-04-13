package com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SmpsConsumMaterialEditDialougeBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerConnectionAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerConsumableMaterial
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerFuelConsumMaterialEditDialouge (var data: PowerConsumableMaterial, var fullData: NewPowerFuelAllData?, var listner:PowerFuelConsumMaterialUpdateListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: SmpsConsumMaterialEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SmpsConsumMaterialEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.60).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.ItemNameEdit.setText(data.ItemName)
        binding.TypeEdit.setText(data.ItemType)
        binding.MakeEdit.setText(data.Make)
        binding.ModelEdit.setText(data.Model)
        binding.UoMEdit.setText(data.UOM)
        binding.UsedQtyEdit.setText(data.UsedQty)
        binding.InstallationDateEdit.text=Utils.getFormatedDate(data.InstallationDate,"dd-MMM-yyyy")

        setDatePickerView( binding.InstallationDateEdit)

        binding.update.setOnClickListener {
            showProgressLayout()
            val tempPowerConnAllData= PowerConnectionAllData()
            val dataModel = NewPowerFuelAllData()
            data.let {
                it.ItemName=binding.ItemNameEdit.text.toString()
                it.ItemType=binding.TypeEdit.text.toString()
                it.Make=binding.MakeEdit.text.toString()
                it.Model=binding.ModelEdit.text.toString()
                it.UOM=binding.UoMEdit.text.toString()
                it.UsedQty=binding.UsedQtyEdit.text.toString()
                it.InstallationDate=Utils.getFullFormatedDate(binding.InstallationDateEdit.text.toString())!!

                tempPowerConnAllData.ConsumableMaterial= arrayListOf(it)
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
                AppLogger.log("PowerFuelConsumMaterialEditDialouge data Updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status?.PowerAndFuelEBConnection==200) {
                AppLogger.log("PowerFuelConsumMaterialEditDialouge card Data Updated successfully")
                listner.updatedData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("PowerFuelConsumMaterialEditDialouge Something went wrong in updating data")
            }
            else if (it != null) {
                AppLogger.log("PowerFuelConsumMaterialEditDialouge error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("PowerFuelConsumMaterialEditDialouge Something went wrong in updating data")

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


    interface PowerFuelConsumMaterialUpdateListener{
        fun updatedData()
    }

}