package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.earthing.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.EarthingPoItemDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.NewTowerCivilAllData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TowerAndCivilInfraEarthing
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilPODetail
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class EarthingPOEditDialouge(var data: TwrCivilPODetail, var childId:Int?, var parentId:Int?, var listner: EarthingPoUpdateListner) : BaseBottomSheetDialogFragment() {
    lateinit var binding: EarthingPoItemDialougeBinding
    lateinit var viewmodel: HomeViewModel
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = EarthingPoItemDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EarthingPoItemDialougeBinding.bind(view)
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.PoItemEdit.setText(data.POItem)
        binding.VendorCodeEdit.setText(data.VendorCode)
        binding.PoNumberEdit.setText(data.PONumber)
        binding.PoAmountEdit.setText(data.POAmount)
        binding.PoLineNumberEdit.setText(data.POLineNo.toString())
        binding.PoDateEdit.text= Utils.getFormatedDate(data.PODate,"dd-MMM-yyyy")
        binding.remarksEdit.setText(data.remark)
        if (data.VendorCompany?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.VendorNameEdit, DropDowns.VendorCompany.name, data.VendorCompany?.get(0).toString())
        else
            AppPreferences.getInstance().setDropDown(binding.VendorNameEdit, DropDowns.VendorCompany.name)
        setDatePickerView( binding.PoDateEdit)
        binding.update.setOnClickListener {
            showProgressLayout()
            val tempEarthingAllData= TowerAndCivilInfraEarthing()
            val tempTowerCivilAllData= NewTowerCivilAllData()
            data.let {
                it.POAmount=binding.PoAmountEdit.text.toString()
                it.POItem=binding.PoItemEdit.text.toString()
                it.VendorCode=binding.VendorCodeEdit.text.toString()
                it.PONumber=binding.PoNumberEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.POLineNo=binding.PoLineNumberEdit.text.toString().toInt()
                it.PODate= Utils.getFullFormatedDate(binding.PoDateEdit.text.toString())!!
                it.VendorCompany= arrayListOf(binding.VendorNameEdit.selectedValue.id.toInt())
                tempEarthingAllData.PODetail= arrayListOf(it)
            }
            if (childId!=null)
                tempEarthingAllData.id=childId
            if (parentId!=null)
                tempTowerCivilAllData.id=id
            tempTowerCivilAllData.TowerAndCivilInfraEarthing= arrayListOf(tempEarthingAllData)
            viewmodel.updateTwrCivilInfra(tempTowerCivilAllData)

        }
        if (viewmodel.updateTwrCivilInfraDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateTwrCivilInfraDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateTwrCivilInfraDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("EarthingPOEditDialouge data Updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("EarthingPOEditDialouge card Data Updated successfully")
                listner.updateData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("EarthingPOEditDialouge Something went wrong in updating data")
            }
            else if (it != null) {
                AppLogger.log("EarthingPOEditDialouge error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("EarthingPOEditDialouge Something went wrong in updating data")

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

    interface EarthingPoUpdateListner{
        fun updateData()
    }

}