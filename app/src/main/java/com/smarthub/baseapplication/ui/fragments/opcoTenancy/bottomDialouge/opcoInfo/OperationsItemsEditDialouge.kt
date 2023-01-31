package com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.opcoInfo

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoOperationsTeamDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.Opcoinfo
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency.updateOpcoDataItem
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel

class OperationsItemsEditDialouge (contentLayoutId: Int, var opcoInfo: Opcoinfo, var id:String): BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : OpcoOperationsTeamDialougeBinding
    lateinit var viewModel: SiteInfoViewModel
    lateinit var homeViewModel: HomeViewModel
    var dataModel: updateOpcoDataItem?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OpcoOperationsTeamDialougeBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity())[SiteInfoViewModel::class.java]
        homeViewModel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataModel= updateOpcoDataItem()
        binding.canecl.setOnClickListener {
            dismiss()
        }
        binding.cancelText.setOnClickListener {
            Utils.dialogYesOrNo(requireContext(),"Cancel !! ","Do you want to discard changes"
            ) { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        dialog.dismiss()
                        Handler().postDelayed({
                            dismiss()
                        }, 200)
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        dialog.dismiss()
                    }
                }
            }
        }

        binding.SiteInChargeName.setText(opcoInfo.Siteinchargename)
        binding.SiteInChargeEmailID.setText(opcoInfo.Siteinchargeemail)
        binding.SiteInChargeNumber.setText(opcoInfo.Siteinchargenumber)
        binding.opraterMaintenanceLocation.setText(opcoInfo.Operatormaintenancelocation)
        AppPreferences.getInstance().setDropDown(binding.InstallationVendor,DropDowns.InstallationVendor.name,opcoInfo.InstallationVendor)
        AppPreferences.getInstance().setDropDown(binding.maintenanceVendor,DropDowns.MaintenanceVendor.name,opcoInfo.MaintenanceVendor)
        AppPreferences.getInstance().setDropDown(binding.BackhaulTechnology,DropDowns.Backhaultechnology.name,opcoInfo.Backhaultechnology)

        binding.updateAction.setOnClickListener {

            opcoInfo.let {
                it.Siteinchargename=binding.SiteInChargeName.text.toString()
                it.Siteinchargeemail=binding.SiteInChargeEmailID.text.toString()
                it.Siteinchargenumber=binding.SiteInChargeNumber.text.toString()
                it.Operatormaintenancelocation=binding.opraterMaintenanceLocation.text.toString()
                it.MaintenanceVendor=binding.maintenanceVendor.selectedValue.id
                it.InstallationVendor=binding.InstallationVendor.selectedValue.id
                it.Backhaultechnology=binding.BackhaulTechnology.selectedValue.id
            }
            dataModel?.Opcoinfo=opcoInfo
            dataModel?.id=id
            AppLogger.log("oprations team data for update: ${dataModel?.Opcoinfo}, whole Data: ${dataModel}")
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

    override fun getTheme() = R.style.NewDialogTask

    fun showProgressLayout(){
        if (binding.progressLayout.visibility != View.VISIBLE)
            binding.progressLayout.visibility = View.VISIBLE
    }
    fun hideProgressLayout(){
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }

}