package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SaftyAccessDetailsBottomSheetBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.siteInfoData.SafetyAndAcces
import com.smarthub.baseapplication.network.pojo.site_info.SafetyAndAccessModel
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SaftyAccessBottomSheet(contentLayoutId:Int, var id : String, var dropdown: SafetyAndAccessModel, var safetyAndAccess: SafetyAndAcces, var viewModel: HomeViewModel) : BottomSheetDialogFragment(contentLayoutId) {

    var basicinfoModel: BasicinfoModel? = null
    lateinit var binding : SaftyAccessDetailsBottomSheetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SaftyAccessDetailsBottomSheetBinding.bind(view)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()
        basicinfoModel = BasicinfoModel()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        var adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
            override fun itemClicked() {
            }
        })

        var recyclerListener = binding.root.findViewById<RecyclerView>(R.id.list_item)
        recyclerListener.adapter = adapter
        binding.root.findViewById<View>(R.id.attach_card).setOnClickListener {
            adapter.addItem()
        }

        binding.update.setOnClickListener {
            safetyAndAccess.let{
                it.id = safetyAndAccess.id
                it.CautionSignage = binding.textCautionSignage.selectedValue.id
                it.DangerSignage = binding.dangerSignageSpinner.selectedValue.id
                it.SiteAccessArea = binding.siteAccessAreaSpinner.selectedValue.id
                it.GateAndFence = binding.textGate.selectedValue.id
                it.NearByFireStation = binding.textFireStation.text.toString()
                it.NearByPoliceStation = binding.textPoliceStation.text.toString()
                it.NearByPoliceStationNumber = safetyAndAccess.NearByPoliceStationNumber//binding.textTempZone.text.toString()
                it.Physicalsecurity = safetyAndAccess.Physicalsecurity//binding.textTempZone.text.toString()
                it.Siteaccess = binding.siteAccess.selectedValue.id
                it.Siteaccessmethodology = binding.textSiteAccesseethodology.text.toString()
                it.Videomonitoring = binding.videoMonitoringSpinner.selectedValue.id
            }
            basicinfoModel?.SafetyAndAccess = safetyAndAccess
            basicinfoModel?.id = id
            viewModel.updateBasicInfo(basicinfoModel!!)
        }

        val saftyAcess: SafetyAndAcces = safetyAndAccess

        binding.textSiteAccesseethodology.setText(saftyAcess.Siteaccessmethodology)
        binding.textPoliceNumber.setText(saftyAcess.NearByPoliceStationNumber)
        binding.textPoliceStation.setText( saftyAcess.NearByPoliceStation)
        binding.textFireStation.setText( saftyAcess.NearByFireStation)
        binding.textFireNumber.setText( saftyAcess.NearByFireStationNumber)

        AppPreferences.getInstance().setDropDownByName(binding.siteAccess, DropDowns.Siteaccess.name,saftyAcess.Siteaccess)
        AppPreferences.getInstance().setDropDownByName(binding.physicalSecurity, DropDowns.Physicalsecurity.name,saftyAcess.Physicalsecurity)
        AppPreferences.getInstance().setDropDownByName(binding.textGate, DropDowns.GateAndFence.name,saftyAcess.GateAndFence)
        AppPreferences.getInstance().setDropDownByName(binding.textCautionSignage, DropDowns.CautionSignage.name,saftyAcess.CautionSignage)
        AppPreferences.getInstance().setDropDownByName(binding.videoMonitoringSpinner, DropDowns.VideoMonitoring.name,saftyAcess.Videomonitoring)
        AppPreferences.getInstance().setDropDownByName(binding.siteAccessAreaSpinner, DropDowns.SiteAccessArea.name,saftyAcess.SiteAccessArea)
        AppPreferences.getInstance().setDropDownByName(binding.dangerSignageSpinner, DropDowns.DangerSignage.name,saftyAcess.DangerSignage)
        hideProgressLayout()
        if (viewModel.basicInfoUpdate?.hasActiveObservers() == true)
            viewModel.basicInfoUpdate?.removeObservers(viewLifecycleOwner)
        viewModel.basicInfoUpdate?.observe(viewLifecycleOwner){
            if (it!=null){
                if (it.status == Resource.Status.LOADING){
                    showProgressLayout()
                }else{
                    hideProgressLayout()
                }
                if (it.status == Resource.Status.SUCCESS && it.data?.Status?.isNotEmpty() == true){
                    AppLogger.log("Successfully updated all fields")
                    dismiss()
                    viewModel.fetchSiteInfoData(id)
                }else{
                    AppLogger.log("UnExpected Error found")
                }
            }else{
                AppLogger.log("Something went wrong")
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (viewModel.basicinfoModel?.hasActiveObservers() == true)
            viewModel.basicinfoModel?.removeObservers(viewLifecycleOwner)
    }

    private fun showProgressLayout(){
        if (binding.progressLayout.visibility != View.VISIBLE)
            binding.progressLayout.visibility = View.VISIBLE
    }

    private fun hideProgressLayout(){
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SaftyAccessDetailsBottomSheetBinding.inflate(inflater)
        return binding.root
    }


}