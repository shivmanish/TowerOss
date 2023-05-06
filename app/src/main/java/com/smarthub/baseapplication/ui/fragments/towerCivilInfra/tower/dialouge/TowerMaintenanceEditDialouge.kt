package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tower.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilityMaitenanceEditDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.NewTowerCivilAllData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.PreventiveMaintenance
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TowerAndCivilInfraTower
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TowerMaintenanceEditDialouge (var data: PreventiveMaintenance, var childId: Int?, var parentId: Int?, var listner:TowerMaintenanceUpdateListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: UtilityMaitenanceEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = UtilityMaitenanceEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.60).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.ServiceTypeEdit.setText(data.ServiceType.toString())
        binding.NextPMIntervalEdit.setText(data.NextPMInterval)
        binding.VendorExecutiveNameEdit.setText(data.VendorExecutiveName)
        binding.remarksEdit.setText(data.remark)
        binding.PMDateEdit.text=Utils.getFormatedDate(data.PMDate,"dd-MMM-yyyy")
        if (data.VendorCompany!=null && data.VendorCompany?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.VendorNameEdit,DropDowns.VendorCompany.name,data.VendorCompany?.get(0).toString(),binding.VendorCodeEdit)
        else
            AppPreferences.getInstance().setDropDown(binding.VendorNameEdit,DropDowns.VendorCompany.name,binding.VendorCodeEdit)
        setDatePickerView( binding.PMDateEdit)

        binding.update.setOnClickListener {
            showProgressLayout()
            val tempTowerAllData= TowerAndCivilInfraTower()
            val tempTowerCivilAllData= NewTowerCivilAllData()
            data.let {
                it.ServiceType=binding.ServiceTypeEdit.text.toString()
                it.VendorCode=binding.VendorCodeEdit.text.toString()
                it.NextPMInterval=binding.NextPMIntervalEdit.text.toString()
                it.VendorExecutiveName=binding.VendorExecutiveNameEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.PMDate=Utils.getFullFormatedDate(binding.PMDateEdit.text.toString())
                it.VendorCompany= binding.VendorNameEdit.getSelectedArray()
                tempTowerAllData.PreventiveMaintenance= arrayListOf(it)
            }
            if (childId!=null)
                tempTowerAllData.id=childId
            if (parentId!=null)
                tempTowerCivilAllData.id=id
            tempTowerCivilAllData.TowerAndCivilInfraTower= arrayListOf(tempTowerAllData)
            viewmodel.updateTwrCivilInfra(tempTowerCivilAllData)


        }

        if (viewmodel.updateTwrCivilInfraDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateTwrCivilInfraDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateTwrCivilInfraDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("TowerMaintenanceEditDialouge data Updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("TowerMaintenanceEditDialouge card Data Updated successfully")
                listner.updatedData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
//                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("TowerMaintenanceEditDialouge Something went wrong in updating data")
            }
            else if (it != null) {
                AppLogger.log("TowerMaintenanceEditDialouge error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("TowerMaintenanceEditDialouge Something went wrong in updating data")

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


    interface TowerMaintenanceUpdateListener{
        fun updatedData()
    }

}