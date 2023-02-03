package com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.rfAnteena

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfAnteenaListItemDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency.updateOpcoDataItem
import com.smarthub.baseapplication.ui.dialog.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel

class RfAnteenaItemsEditDialouge (contentLayoutId: Int,var rfAntenadata : RfAnteenaData,var id:String,var listner:rfAntenaListener): BaseBottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : RfAnteenaListItemDialougeBinding
    lateinit var viewModel: SiteInfoViewModel
    lateinit var homeViewModel: HomeViewModel
    var dataModel: updateOpcoDataItem?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = RfAnteenaListItemDialougeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataModel= updateOpcoDataItem()
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.85).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        setDatePickerView(binding.editInstallationDate)
        binding.editAntennaSerialNumber.text=rfAntenadata.AntenaSerialNumber.toEditable()
        binding.editAntennaMake.text=rfAntenadata.AntenaMake.toEditable()
        binding.antennaModel.text=rfAntenadata.AntenaModel.toEditable()
        binding.installedHeight.text=rfAntenadata.InstalledHeight.toEditable()
        binding.antennaLenth.text=rfAntenadata.AntenaSizeL.toEditable()
        binding.antennaBidth.text=rfAntenadata.AntenaSizeB.toEditable()
        binding.antennaHeight.text=rfAntenadata.AntenaSizeH.toEditable()
        binding.AntennaWeight.text=rfAntenadata.AntenaWeight.toEditable()
        binding.AntennaOrientation.text=rfAntenadata.AntenaOrentiation.toEditable()
        binding.AntennaTilt.text=rfAntenadata.AntenaTilt.toEditable()
        binding.TotalPowerRating.text=rfAntenadata.AntenaTotalPowerRating.toEditable()
        binding.FeederCableLength.text=rfAntenadata.FeederCableLength.toEditable()
        binding.PowerCableLength.text=rfAntenadata.PowerCableLength.toEditable()
        binding.CPRICableLength.text=rfAntenadata.CPRICableLength.toEditable()
        binding.GPSCableLength.text=rfAntenadata.GPSCableLength.toEditable()
        binding.editInstallationDate.text=rfAntenadata.InstallationDate.toEditable()
        binding.antenaType.text=rfAntenadata.AntenaType.toEditable()


//        AppPreferences.getInstance().setDropDown(binding.userCompany,DropDowns.UserCompany.name,rfAntenadata.UserCompany)
        AppPreferences.getInstance().setDropDown(binding.technology,DropDowns.Technology.name,rfAntenadata.Technology)
        AppPreferences.getInstance().setDropDown(binding.antennaShape,DropDowns.AntenaShape.name,rfAntenadata.AntenaShape)
        binding.update.setOnClickListener {
            rfAntenadata.let {
                it.AntenaSerialNumber=binding.editAntennaSerialNumber.text.toString()
                it.AntenaMake=binding.editAntennaMake.text.toString()
                it.AntenaModel=binding.antennaModel.text.toString()
                it.InstalledHeight=binding.installedHeight.text.toString()
                it.AntenaSizeL=binding.antennaLenth.text.toString()
                it.AntenaSizeB=binding.antennaBidth.text.toString()
                it.AntenaSizeH=binding.antennaHeight.text.toString()
                it.AntenaWeight=binding.AntennaWeight.text.toString()
                it.AntenaType=binding.antenaType.text.toString()
                it.AntenaOrentiation=binding.AntennaOrientation.text.toString()
                it.AntenaTilt=binding.AntennaTilt.text.toString()
                it.AntenaTotalPowerRating=binding.TotalPowerRating.text.toString()
                it.FeederCableLength=binding.FeederCableLength.text.toString()
                it.PowerCableLength=binding.PowerCableLength.text.toString()
                it.CPRICableLength=binding.CPRICableLength.text.toString()
                it.GPSCableLength=binding.GPSCableLength.text.toString()
                it.InstallationDate=binding.editInstallationDate.text.toString()

                it.Technology=binding.technology.selectedValue.id
                it.AntenaShape=binding.antennaShape.selectedValue.id

            }
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
                    listner.updatedData(dataModel?.RfAntena!!)
//                    homeViewModel.opcoTenancyRequestAll(AppController.getInstance().siteid)
                }else{
                    AppLogger.log("UnExpected Error found")
                }
            }else{
                AppLogger.log("Something went wrong")
            }
        }
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
    interface rfAntenaListener{
        fun updatedData(data : RfAnteenaData)
    }

}