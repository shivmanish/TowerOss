package com.smarthub.baseapplication.ui.fragments.sstSbc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SstSbcAddNewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcTeam
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AddNewSstSbcDialouge (var listner:AddSstSbcDataListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: SstSbcAddNewDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SstSbcAddNewDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.70).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        
        AppPreferences.getInstance().setDropDown(binding.TestTypeEdit,DropDowns.SstSbcType.name)
        AppPreferences.getInstance().setDropDown(binding.VendorNameEdit,DropDowns.VendorCompany.name)

        setDatePickerView(binding.PODateEdit)


//
        binding.Submit.setOnClickListener {

            showProgressLayout()
            val data=SstSbcTeam()
            data.let {
                it.PONumber=binding.PONumberEdit.text.toString()
                it.Budget=binding.BudgetEdit.text.toString()
                it.PODate=Utils.getFullFormatedDate(binding.PODateEdit.text.toString())
                it.POLineItemNo=binding.POLineNoEdit.text.toString().toInt()
                it.POAmount=binding.POAmountEdit.text.toString()
                it.VendorExecutiveName=binding.VendorExecutiveNameEdit.text.toString()
                it.VendorExecutiveEmailId=binding.VendorExecutiveEmailIDEdit.text.toString()
                it.VendorExecutiveMobile=binding.VendorExecutiveNumberEdit.text.toString()
                it.Type = binding.TestTypeEdit.selectedValue.id.toInt()
                it.VendorCompany = arrayListOf(binding.VendorNameEdit.selectedValue.id.toInt())
                it.Remark=binding.remarksEdit.text.toString()

                val dataModel = SstSbcAllData()
                dataModel.SstSbcTeam= arrayListOf(it)
                viewmodel.updateSstSbc(dataModel)
            }
        }

        if (viewmodel.updateSstSbcDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSstSbcDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSstSbcDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("AddNewSstSbcDialouge data adding in progress ")
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
                Toast.makeText(context,"Something went wrong in add data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("AddNewSstSbcDialouge Something went wrong in adding data")
            }
            else if (it != null) {
                AppLogger.log("AddNewSstSbcDialouge error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("AddNewSstSbcDialouge Something went wrong in adding data")

            }
        }
        hideProgressLayout()
    }

    override fun getTheme() = R.style.NewDialogTask


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


    interface AddSstSbcDataListener{
        fun addNewData()
    }

}