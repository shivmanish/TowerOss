package com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqPoEditDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerConnectionAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelPODetail
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerFuelPoEditDialouge (var data: PowerFuelPODetail, var fullData: NewPowerFuelAllData?, var listner:PowerFuelPoUpdateListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: AcqPoEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AcqPoEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.70).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.PoItemEdit.setText(data.POItem)
        binding.VendorCodeEdit.setText(data.VendorCode)
        binding.PoNumberEdit.setText(data.PONumber)
        binding.PoAmountEdit.setText(data.POAmount)
        binding.PoLineNumberEdit.setText(data.POLineNo.toString())
        binding.remarksEdit.setText(data.remark)
        if (data.VendorCompany.isNotEmpty())
            AppPreferences.getInstance().setDropDown(binding.VendorNameEdit, DropDowns.VendorCompany.name, data.VendorCompany[0].toString())
        else
            AppPreferences.getInstance().setDropDown(binding.VendorNameEdit, DropDowns.VendorCompany.name)

        if (data.PODate?.isNotEmpty()==true)
            binding.PoDateEdit.text=Utils.getFormatedDate(data.PODate,"dd-MMM-yyyy")
        else
            binding.PoDateEdit.text=data.PODate
        setDatePickerView( binding.PoDateEdit)

        binding.update.setOnClickListener {
            showProgressLayout()
            val tempPowerConnAllData= PowerConnectionAllData()
            val dataModel = NewPowerFuelAllData()
            data.let {
                it.POAmount=binding.PoAmountEdit.text.toString()
                it.POItem=binding.PoItemEdit.text.toString()
                it.VendorCode=binding.VendorCodeEdit.text.toString()
                it.PONumber=binding.PoNumberEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.POLineNo=binding.PoLineNumberEdit.text.toString().toInt()
                it.PODate=Utils.getFullFormatedDate(binding.PoDateEdit.text.toString())!!
                it.VendorCompany= arrayListOf(binding.VendorNameEdit.selectedValue.id.toInt())
                tempPowerConnAllData.PODetail= arrayListOf(it)
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
                AppLogger.log("PowerFuelPoEditDialouge data Updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status?.PowerAndFuelEBConnection==200) {
                AppLogger.log("PowerFuelPoEditDialouge card Data Updated successfully")
                listner.updatedData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("PowerFuelPoEditDialouge Something went wrong in updating data")
            }
            else if (it != null) {
                AppLogger.log("PowerFuelPoEditDialouge error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("PowerFuelPoEditDialouge Something went wrong in updating data")

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


    interface PowerFuelPoUpdateListener{
        fun updatedData()
    }

}