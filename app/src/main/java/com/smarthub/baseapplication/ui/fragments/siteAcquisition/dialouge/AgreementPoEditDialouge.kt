package com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge

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
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqPODetail
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SiteAcqAgreement
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AgreementPoEditDialouge (var data: SAcqPODetail,var fullData: NewSiteAcquiAllData?,var listner:AcqPoUpdateListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: AcqPoEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AcqPoEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
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
        binding.PoLineNumberEdit.setText(data.POLineNumber.toString())
        binding.remarksEdit.setText(data.Remark)
        if (data.VendorCompany.isNotEmpty())
            AppPreferences.getInstance().setDropDown(binding.VendorNameEdit, DropDowns.VendorCompany.name, data.VendorCompany[0].toString())
        else
            AppPreferences.getInstance().setDropDown(binding.VendorNameEdit, DropDowns.VendorCompany.name)

        if (data.PODate.isNotEmpty())
            binding.PoDateEdit.text=Utils.getFormatedDate(data.PODate.substring(0,10),"dd-MMM-yyyy")
        else
            binding.PoDateEdit.text=data.PODate
        setDatePickerView( binding.PoDateEdit)

        binding.update.setOnClickListener {
            showProgressLayout()
            if (fullData?.SAcqAgreement== null || fullData?.SAcqAgreement?.isEmpty()==true){
                data.let {
                    it.POAmount=binding.PoAmountEdit.text.toString()
                    it.POItem=binding.PoItemEdit.text.toString()
                    it.VendorCode=binding.VendorCodeEdit.text.toString()
                    it.PONumber=binding.PoNumberEdit.text.toString()
                    it.Remark=binding.remarksEdit.text.toString()
                    it.POLineNumber=binding.PoLineNumberEdit.text.toString().toInt()
                    it.PODate=Utils.getFullFormatedDate(binding.PoDateEdit.text.toString())
                    it.VendorCompany= arrayListOf(binding.VendorNameEdit.selectedValue.id.toInt())

                    // add po data to agreement model list
                    val tempList:ArrayList<SAcqPODetail> = ArrayList()
                    tempList.clear()
                    tempList.add(it)
                    val tempData= SiteAcqAgreement()
                    tempData.SAcqPODetail= tempList
                    //add add agreement model to update rootModel
                    val dataModel = UpdateSiteAcquiAllData()
                    val tempList2:ArrayList<SiteAcqAgreement> =ArrayList()
                    tempList2.clear()
                    tempList2.add(tempData)
                    dataModel.SAcqAgreement=tempList2
                    dataModel.id=fullData?.id
                    viewmodel.updateSiteAcq(dataModel)
                }
            }
            else
            {
                data.let {
                    it.POAmount=binding.PoAmountEdit.text.toString()
                    it.POItem=binding.PoItemEdit.text.toString()
                    it.VendorCode=binding.VendorCodeEdit.text.toString()
                    it.PONumber=binding.PoNumberEdit.text.toString()
                    it.Remark=binding.remarksEdit.text.toString()
                    it.POLineNumber=binding.PoLineNumberEdit.text.toString().toInt()
                    it.PODate=Utils.getFullFormatedDate(binding.PoDateEdit.text.toString())
                    if (data.VendorCompany.isNotEmpty())
                        it.VendorCompany[0]=binding.VendorNameEdit.selectedValue.id.toInt()
                    else
                        it.VendorCompany.add(binding.VendorNameEdit.selectedValue.id.toInt())

                    // add po data to agreement model list
                    val tempList:ArrayList<SAcqPODetail> = ArrayList()
                    tempList.clear()
                    tempList.add(it)
                    val tempData= fullData?.SAcqAgreement?.get(0) as SiteAcqAgreement
                    tempData.SAcqAgreementDetail=null
                    tempData.SAcqPODetail= tempList
                    //add add agreement model to update rootModel
                    val dataModel = UpdateSiteAcquiAllData()
                    val tempList2:ArrayList<SiteAcqAgreement> =ArrayList()
                    tempList2.clear()
                    tempList2.add(tempData)
                    dataModel.SAcqAgreement=tempList2
                    dataModel.id=fullData?.id
                    viewmodel.updateSiteAcq(dataModel)
                }
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
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SAcqPODetail==200) {
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


    interface AcqPoUpdateListener{
        fun updatedData()
    }

}