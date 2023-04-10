package com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge
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
import com.smarthub.baseapplication.databinding.PowerFuelAddNewBillPaymentDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelEBPayments
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AddNewPowerFuelBillsPayDialouge (var fullData:NewPowerFuelAllData?, var listner: AddBillPaymentsListener): BaseBottomSheetDialogFragment() {
    lateinit var binding : PowerFuelAddNewBillPaymentDialougeBinding
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PowerFuelAddNewBillPaymentDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.5).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        AppPreferences.getInstance().setDropDown(binding.PaymentModeEdit, DropDowns.PaymentMode.name)

        setDatePickerView(binding.PaymentDateEdit)

        binding.AddAction.setOnClickListener {
            showProgressLayout()
            val tempBillPaymentsData= PowerFuelEBPayments()
            val tempPowerFuelAllData = NewPowerFuelAllData()
            tempBillPaymentsData.let {
                it.BillNumber=binding.BillNumberEdit.text.toString()
                it.Amount=binding.AmountEdit.text.toString()
                it.PaymentDate=Utils.getFullFormatedDate(binding.PaymentDateEdit.text.toString())
                it.PaymentRefNo=binding.PaymentRefNoEdit.text.toString()
                it.PaymentMode= binding.PaymentModeEdit.selectedValue.id.toIntOrNull()
                
            }
            if (fullData!=null){
                tempPowerFuelAllData.id=fullData?.id
            }
            tempPowerFuelAllData.PowerAndFuelEBPayment= arrayListOf(tempBillPaymentsData)
            viewmodel.updatePowerFuel(tempPowerFuelAllData)
        }

        if (viewmodel.updatePowerFuelDataResponse?.hasActiveObservers() == true) {
            viewmodel.updatePowerFuelDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updatePowerFuelDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("AddNewPowerFuelBillsPayDialouge Fragment data adding in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status?.PowerAndFuelEBPayment==200) {
                listner.addNewData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Added successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("AddNewPowerFuelBillsPayDialouge Fragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("AddNewPowerFuelBillsPayDialouge Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("AddNewPowerFuelBillsPayDialouge Fragment Something went wrong")

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

    interface AddBillPaymentsListener{
        fun addNewData()
    }
}