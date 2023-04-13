package com.smarthub.baseapplication.ui.fragments.noc.bottomSheetAdapters

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
import com.smarthub.baseapplication.databinding.NocCompAddnewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocApplicationInitial
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AddNewNocCmpDialouge (var listner: AddNocCompDataListener): BaseBottomSheetDialogFragment() {
    lateinit var binding : NocCompAddnewDialougeBinding
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NocCompAddnewDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
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
        AppPreferences.getInstance().setDropDown(binding.ApplicationTypeEdit, DropDowns.AuthorityApplicationType.name)
        AppPreferences.getInstance().setDropDown(binding.CategoryEdit, DropDowns.ApplicationCategory.name)
        AppPreferences.getInstance().setDropDown(binding.StatusEdit, DropDowns.ApplicationStatus.name)

        setDatePickerView(binding.ApplicationDateEdit)
        setDatePickerView(binding.IssueDateEdit)
        setDatePickerView(binding.StatusDateEdit)
        setDatePickerView(binding.ExpiryDateEdit)

        binding.Add.setOnClickListener {
            val tempApplicatioData= NocApplicationInitial()
            val tempNocCompAllData = NocCompAllData()
            tempApplicatioData.let {
                it.ApplicationNumber=binding.ApplicationNumberEdit.text.toString()
                it.DocumentNo=binding.DocumentNumberEdit.text.toString()
                it.ApplicationDate=Utils.getFullFormatedDate(binding.ApplicationDateEdit.text.toString())
                it.StatusDate=Utils.getFullFormatedDate(binding.StatusDateEdit.text.toString())
                it.IssueDate=Utils.getFullFormatedDate(binding.IssueDateEdit.text.toString())
                it.ExpiryDate=Utils.getFullFormatedDate(binding.ExpiryDateEdit.text.toString())
                it.AuthorityApplicationType= arrayListOf(binding.ApplicationTypeEdit.selectedValue.id.toInt())
                it.ApplicationStatus= arrayListOf(binding.StatusEdit.selectedValue.id.toInt())
                it.Category= binding.CategoryEdit.selectedValue.id.toInt()
            }
            tempNocCompAllData.ApplicationInitial= arrayListOf(tempApplicatioData)
            viewmodel.updateNocAndComp(tempNocCompAllData)
        }

        if (viewmodel.updateNocCompDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSiteAcqDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateNocCompDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("AddNewNocCmpDialouge Fragment data adding in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.NOCCompliance==200) {
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

    interface AddNocCompDataListener{
        fun addNewData()
    }
}