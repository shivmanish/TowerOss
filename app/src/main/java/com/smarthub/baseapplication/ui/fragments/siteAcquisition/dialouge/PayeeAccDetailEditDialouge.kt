package com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqPayeeAccDetailsEditDialougeBinding
import com.smarthub.baseapplication.databinding.AcqPoEditDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.*
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.Opcoinfo
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PayeeAccDetailEditDialouge (var data: SAcqPayeeAccountDetail, var fullData: NewSiteAcquiAllData?, var listner:PayeeAccUpdateListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: AcqPayeeAccDetailsEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AcqPayeeAccDetailsEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.70).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.PayeeNameEdit.setText(data.PayeeName)
        binding.ShareEdit.setText(data.Share)
        binding.AccountNoEdit.setText(data.AccountNumber)
        binding.PayeeBankEdit.setText(data.PayeeBank)
        binding.BranchNameEdit.setText(data.BankBranch.toString())
        binding.IfscCodeEdit.setText(data.BankIFSCode)
        binding.GSTNumberEdit.setText(data.GSTIN)
        binding.PanNumberEdit.setText(data.PAN)
        binding.PayeeStatusEdit.text=data.PayeeStatus.toString()


//
        binding.update.setOnClickListener {
            showProgressLayout()
            data.let {
                it.PayeeName=binding.PayeeNameEdit.text.toString()
                it.Share=binding.ShareEdit.text.toString()
                it.AccountNumber=binding.AccountNoEdit.text.toString()
                it.PayeeBank=binding.PayeeBankEdit.text.toString()
                it.BankBranch=binding.BranchNameEdit.text.toString()
                it.BankIFSCode=binding.IfscCodeEdit.text.toString()
                it.GSTIN=binding.GSTNumberEdit.text.toString()
                it.GSTIN=binding.GSTNumberEdit.text.toString()
                it.PAN=binding.PanNumberEdit.text.toString()


                // add po data to agreement model list
                val tempList:ArrayList<SAcqPayeeAccountDetail> = ArrayList()
                tempList.clear()
                tempList.add(it)
                val tempData= fullData?.SAcqSoftAcquisition?.get(0) as SoftAcquisitionData
                tempData.SAcqSoftAcquisitionAgreementTerm=null
                tempData.SAcqPayeeAccountDetail= tempList
                //add add agreement model to update rootModel
                val dataModel = UpdateSiteAcquiAllData()
                val tempList2:ArrayList<SoftAcquisitionData> =ArrayList()
                tempList2.clear()
                tempList2.add(tempData)
                dataModel.SAcqSoftAcquisition=tempList2
                dataModel.id=fullData?.id
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
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SAcqPayeeAccountDetail==200) {
                AppLogger.log("SiteAgreemnets Fragment card Data fetched successfully")
                listner.updatedData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
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
        if (binding.progressLayout.visibility != View.VISIBLE)
            binding.progressLayout.visibility = View.VISIBLE
    }
    fun hideProgressLayout(){
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }


    interface PayeeAccUpdateListener{
        fun updatedData()
    }

}