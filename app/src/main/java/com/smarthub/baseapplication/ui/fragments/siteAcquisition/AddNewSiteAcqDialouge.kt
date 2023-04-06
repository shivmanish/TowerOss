package com.smarthub.baseapplication.ui.fragments.siteAcquisition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AddNewSiteAcqDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.AssignACQTeamDAta
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AddNewSiteAcqDialouge ( var listner:AddSiteAcqDataListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: AddNewSiteAcqDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AddNewSiteAcqDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.70).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        
        AppPreferences.getInstance().setDropDown(binding.AcquisitionTypeEdit,DropDowns.Acquisitiontype.name)
        AppPreferences.getInstance().setDropDown(binding.AcquisitionModeEdit,DropDowns.AcquisitionMode.name)
        AppPreferences.getInstance().setDropDown(binding.VendorNameEdit,DropDowns.VendorCompany.name)

        setDatePickerView(binding.AcquisitionTargetDateEdit)
        setDatePickerView(binding.PODateEdit)


//
        binding.Submit.setOnClickListener {

            showProgressLayout()
            val data=AssignACQTeamDAta()
            data.let {
                it.LeadName=binding.AcquisitionLeadNameEdit.text.toString()
                it.ExecutiveName=binding.AcquisitionExecutiveNameEdit.text.toString()
                it.AcquisitionBudget=binding.AcquisitionBudgetEdit.text.toString().ifEmpty { "0" }
                it.AcquisitionTargetDate=Utils.getFullFormatedDate(binding.AcquisitionTargetDateEdit.text.toString())
                it.VendorCode=binding.VendorCodeEdit.text.toString()
                it.PONumber=binding.PONumberEdit.text.toString()
                it.PODate=Utils.getFullFormatedDate(binding.PODateEdit.text.toString())
                try {
                    it.POLineItemNo=binding.POLineNoEdit.text.toString().toInt()
                }catch (e:Exception){
                    it.POLineItemNo=0
                }
                it.POAmount=binding.POAmountEdit.text.toString()
                it.VendorExecutiveName=binding.VendorExecutiveNameEdit.text.toString()
                it.VendorExecutiveEmailId=binding.VendorExecutiveEmailIDEdit.text.toString()
                it.VendorExecutiveMobile=binding.VendorExecutiveNumberEdit.text.toString()
                it.AcquisitionMode = arrayListOf(binding.AcquisitionModeEdit.selectedValue.id.toInt())
                it.Acquisitiontype = arrayListOf(binding.AcquisitionTypeEdit.selectedValue.id.toInt())
                it.VendorCompany = arrayListOf(binding.VendorNameEdit.selectedValue.id.toInt())
                it.Remark=binding.remarksEdit.text.toString()

                val dataModel = UpdateSiteAcquiAllData()
                val tempList:ArrayList<AssignACQTeamDAta> =ArrayList()
                tempList.clear()
                tempList.add(it)
                dataModel.SAcqAssignACQTeam=tempList
                viewmodel.updateSiteAcq(dataModel)
            }
        }

        if (viewmodel.updateSiteAcqDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSiteAcqDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSiteAcqDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("SiteAgreemnets Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SAcqAssignACQTeam==200) {
                listner.addNewData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Added successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("SiteAgreemnets Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")

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


    interface AddSiteAcqDataListener{
        fun addNewData()
    }

}