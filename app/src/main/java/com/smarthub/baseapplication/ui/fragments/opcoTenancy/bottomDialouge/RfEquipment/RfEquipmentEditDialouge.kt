package com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.RfEquipment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AddnewRfequipmentDialougeBinding
import com.smarthub.baseapplication.databinding.RfEquipmentListItemDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.rfEquipmentData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency.updateOpcoDataItem
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel

class RfEquipmentEditDialouge(contentLayoutId: Int,var rfData : rfEquipmentData,var id:String): BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : AddnewRfequipmentDialougeBinding
    lateinit var viewModel: SiteInfoViewModel
    lateinit var homeViewModel: HomeViewModel
    var dataModel: updateOpcoDataItem?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AddnewRfequipmentDialougeBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity())[SiteInfoViewModel::class.java]
        homeViewModel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataModel= updateOpcoDataItem()
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.8).toInt()
        binding.cancelTxt.visibility=View.VISIBLE
        binding.Submit.text="Update"
        binding.titleText.text="Edit RF-Equipment"
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.equipmentName.setText(rfData.Equipementname)
        binding.model.setText(rfData.Model)
        binding.serialNumber.setText(rfData.SerialNumber)
        binding.make.setText(rfData.Make)
        binding.band.setText(rfData.Band)
        binding.minOpratingTemp.setText(rfData.OperatingTempMin)
        binding.minOpratingTemp.setText(rfData.OperatingTempMax)
        binding.InatallationDate.text=rfData.InstallationDate
        binding.maxPowerRating.setText(rfData.PowerRating)
        binding.Weight.setText(rfData.Weight)
        binding.rackNumber.setText(rfData.RackNumber)
        binding.remark.setText(rfData.Remarks)

        AppPreferences.getInstance().setDropDown(binding.technology,DropDowns.Technology.name,rfData.Technology)
        AppPreferences.getInstance().setDropDown(binding.ownerCompany,DropDowns.OwnerCompany.name,rfData.OemCompany)
        AppPreferences.getInstance().setDropDown(binding.userCompany,DropDowns.OemCompany.name,rfData.OemCompany)
        AppPreferences.getInstance().setDropDown(binding.oprationalStatus,DropDowns.OperationStatus.name,rfData.OperationStatus)
        AppPreferences.getInstance().setDropDown(binding.rackSpaceUsed,DropDowns.RackSpaceUsed.name,rfData.RackSpaceUsed)

        binding.Submit.setOnClickListener {
            rfData.let {
                it.Equipementname=binding.equipmentName.text.toString()
                it.Model=binding.model.text.toString()
                it.SerialNumber=binding.serialNumber.text.toString()
                it.Make=binding.make.text.toString()
                it.Band=binding.band.text.toString()
                it.OperatingTempMin=binding.minOpratingTemp.text.toString()
                it.OperatingTempMax=binding.maxOpratingTemp.text.toString()
                it.InstallationDate=binding.InatallationDate.text.toString()
                it.PowerRating=binding.maxPowerRating.text.toString()
                it.Weight=binding.Weight.text.toString()
                it.RackNumber=binding.rackNumber.text.toString()
                it.Remarks=binding.remark.text.toString()
                it.Technology=binding.technology.selectedValue.id
                it.OwnerCompany=binding.ownerCompany.selectedValue.id
                it.OemCompany=binding.userCompany.selectedValue.id
                it.OperationStatus=binding.oprationalStatus.selectedValue.id
                it.RackSpaceUsed=binding.rackSpaceUsed.selectedValue.id
            }
            dataModel?.RfEquipment=rfData
            dataModel?.id=id
            AppLogger.log("rfequipment data for update: ${dataModel?.RfEquipment}, whole Data: ${dataModel}")
            viewModel.updateOpcoTenency(dataModel!!)
        }

        hideProgressLayout()
        if (viewModel.opcoTenencyUpdateResoponse?.hasActiveObservers() == true)
            viewModel.opcoTenencyUpdateResoponse?.removeObservers(viewLifecycleOwner)
        viewModel.opcoTenencyUpdateResoponse?.observe(viewLifecycleOwner){
            if (it!=null){
                if (it.status == Resource.Status.LOADING){
                    showProgressLayout()
                }else{
                    hideProgressLayout()
                }
                if (it.status == Resource.Status.SUCCESS && it.data?.Status?.isNotEmpty() == true){
                    AppLogger.log("Successfully updated all fields")
                    dismiss()
                    homeViewModel.opcoTenancyRequestAll(AppController.getInstance().siteid)
                }else{
                    AppLogger.log("UnExpected Error found")
                }
            }else{
                AppLogger.log("Something went wrong")
            }
        }
    }

    fun showProgressLayout(){
        if (binding.progressLayout.visibility != View.VISIBLE)
            binding.progressLayout.visibility = View.VISIBLE
    }
    fun hideProgressLayout(){
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }


    override fun getTheme() = R.style.NewDialogTask


}