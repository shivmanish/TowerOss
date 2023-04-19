package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.pole.dialouges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerConsumableEditDialougeBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.NewTowerCivilAllData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TowerAndCivilInfraPole
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilConsumableMaterial
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PoleConsumableEditAdapter (var data: TwrCivilConsumableMaterial, var childId:Int?, var parentId:Int?, var listner: PoleConsumUpdateListner) : BaseBottomSheetDialogFragment() {

    lateinit var binding: TowerConsumableEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = TowerConsumableEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TowerConsumableEditDialougeBinding.bind(view)
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.ItemNameEdit.setText(data.ItemName)
        binding.TypeEdit.setText(data.ItemType)
        binding.MakeEdit.setText(data.Make)
        binding.ModelEdit.setText(data.Model)
        binding.UoMEdit.setText(data.UOM)
        binding.UsedQtyEdit.setText(data.UsedQty)
        binding.InstallationDateEdit.text= Utils.getFormatedDate(data.InstallationDate,"dd-MMM-yyyy")

        setDatePickerView( binding.InstallationDateEdit)

        binding.update.setOnClickListener {
            showProgressLayout()
            val tempPoleAllData= TowerAndCivilInfraPole()
            val tempTowerCivilAllData= NewTowerCivilAllData()
            data.let {
                it.ItemName=binding.ItemNameEdit.text.toString()
                it.ItemType=binding.TypeEdit.text.toString()
                it.Make=binding.MakeEdit.text.toString()
                it.Model=binding.ModelEdit.text.toString()
                it.UOM=binding.UoMEdit.text.toString()
                it.UsedQty=binding.UsedQtyEdit.text.toString()
                it.InstallationDate= Utils.getFullFormatedDate(binding.InstallationDateEdit.text.toString())
                tempPoleAllData.ConsumableMaterial= arrayListOf(it)
            }
            if (childId!=null)
                tempPoleAllData.id=childId
            if (parentId!=null)
                tempTowerCivilAllData.id=id
            tempTowerCivilAllData.TowerAndCivilInfraPole= arrayListOf(tempPoleAllData)
            viewmodel.updateTwrCivilInfra(tempTowerCivilAllData)
        }
        if (viewmodel.updateTwrCivilInfraDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateTwrCivilInfraDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateTwrCivilInfraDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("PoleConsumableEditAdapter data Updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("PoleConsumableEditAdapter card Data Updated successfully")
                listner.updatedData()
                hideProgressLayout()
                dismiss()
//                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
//                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("PoleConsumableEditAdapter Something went wrong in updating data")
            }
            else if (it != null) {
                AppLogger.log("PoleConsumableEditAdapter error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("PoleConsumableEditAdapter Something went wrong in updating data")

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

    

interface PoleConsumUpdateListner{
    fun updatedData()
}
}