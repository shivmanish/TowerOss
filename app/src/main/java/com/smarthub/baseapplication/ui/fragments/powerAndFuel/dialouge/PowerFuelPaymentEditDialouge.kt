package com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerFuelAuthorityPaymentEditDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerConnectionAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelAuthorityPayments
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerFuelPaymentEditDialouge (var data: PowerFuelAuthorityPayments, var fullData: NewPowerFuelAllData?, var listner:PowerFuelPaymentUpdateListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: PowerFuelAuthorityPaymentEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PowerFuelAuthorityPaymentEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.70).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.PaymentTypeEdit.setText(data.PaymentType)
        binding.DemandReceiptDateEdit.text=Utils.getFormatedDate(data.DemandReceiptDate,"dd-MMM-yyyy")
        binding.DueDateEdit.text=Utils.getFormatedDate(data.DueDate,"dd-MMM-yyyy")
        binding.StatusDateEdit.text=Utils.getFormatedDate(data.StatusDate,"dd-MMM-yyyy")
        binding.AmountEdit.setText(data.Amount)
        binding.remarksEdit.setText(data.remark)
        if (data.PaymentStatus?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.PaymentStatusEdit, DropDowns.PaymentStatus.name, data.PaymentStatus?.get(0).toString())
        else
            AppPreferences.getInstance().setDropDown(binding.PaymentStatusEdit, DropDowns.PaymentStatus.name)

        setDatePickerView( binding.DemandReceiptDateEdit)
        setDatePickerView( binding.DueDateEdit)
        setDatePickerView( binding.StatusDateEdit)

        binding.update.setOnClickListener {
            showProgressLayout()
            val tempPowerConnAllData= PowerConnectionAllData()
            val dataModel = NewPowerFuelAllData()
            data.let {
                it.Amount=binding.AmountEdit.text.toString()
                it.PaymentType=binding.PaymentTypeEdit.text.toString()
                it.Amount=binding.AmountEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.DemandReceiptDate=Utils.getFullFormatedDate(binding.DemandReceiptDateEdit.text.toString())
                it.DueDate=Utils.getFullFormatedDate(binding.DueDateEdit.text.toString())
                it.StatusDate=Utils.getFullFormatedDate(binding.StatusDateEdit.text.toString())
                it.PaymentStatus= arrayListOf(binding.PaymentStatusEdit.selectedValue.id.toInt())
                tempPowerConnAllData.PowerAndFuelEBConnectionPayment= arrayListOf(it)
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
                AppLogger.log("PowerFuelPaymentEditDialouge data Updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status?.PowerAndFuelEBConnection==200) {
                AppLogger.log("PowerFuelPaymentEditDialouge card Data Updated successfully")
                listner.updatedData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("PowerFuelPaymentEditDialouge Something went wrong in updating data")
            }
            else if (it != null) {
                AppLogger.log("PowerFuelPaymentEditDialouge error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("PowerFuelPaymentEditDialouge Something went wrong in updating data")

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


    interface PowerFuelPaymentUpdateListener{
        fun updatedData()
    }

}