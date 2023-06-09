package com.smarthub.baseapplication.ui.dialog.services_request

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SrDetailsBottomSheetDialogBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.SRDetails
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequest
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SRDetailsBottomSheet(
    contentLayoutId: Int,
    var viewModel: HomeViewModel,
    var id: String,
    var srDetailsData: SRDetails,
    var serviceRequestAllData: ServiceRequestAllDataItem
) : BaseBottomSheetDialogFragment() {

    var basicinfoModel: BasicinfoModel? = null
    lateinit var binding: SrDetailsBottomSheetDialogBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SrDetailsBottomSheetDialogBinding.bind(view)
        basicinfoModel = BasicinfoModel()
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight() * 0.95).toInt()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        setDatePickerView(binding.requestDate)
        setDatePickerView(binding.expectedDate)

        srDetailsData.let {
            AppPreferences.getInstance()
                .setDropDown(binding.srType, DropDowns.SRType.name, it.SRType.toString())
            AppPreferences.getInstance().setDropDown(
                binding.editTechnology,
                DropDowns.SRDetailTechnology.name,
                it.Technology[0].toString()
            )
            AppPreferences.getInstance()
                .setDropDown(binding.editSrStatus, DropDowns.SRStatus.name, it.SRStatus[0].toString())
            AppPreferences.getInstance().setDropDown(
                binding.editRequesterCompany,
                DropDowns.SRDetailRequesterCompany.name,
                it.RequesterCompany
            )
            AppPreferences.getInstance()
                .setDropDown(binding.editPriority, DropDowns.Priority.name, it.Priority[0].toString())


            binding.expectedDate.text = it.ExpectedDate
            binding.requestDate.text = it.RequestDate
            binding.editOPCOSiteID.setText("")
            binding.editNominalsLat.setText(it.locLatitude)
            binding.editNominalsLong.setText(it.locLongitude)
            binding.editSearchRadius.setText(it.SearchRadius)
            binding.editCircle.setText(it.Circle)
            binding.editCityTown.setText(it.CityOrTown)
            binding.editArea.setText(it.Area)
            binding.editPinCode.setText(it.Pincode)
        }

        binding.update.setOnClickListener {
            srDetailsData.let {
                it.ExpectedDate = binding.expectedDate.text.toString()
                it.RequestDate = binding.requestDate.text.toString()
                it.ExpectedDate = binding.expectedDate.text.toString()
                it.locLatitude = binding.editNominalsLat.text.toString()
                it.locLongitude = binding.editNominalsLong.text.toString()
                it.SearchRadius = binding.editSearchRadius.text.toString()
                it.Circle = binding.editCircle.text.toString()
                it.CityOrTown = binding.editCityTown.text.toString()
                it.Area = binding.editArea.text.toString()
                it.Pincode = binding.editPinCode.text.toString()
            }

            val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
            mServiceRequestAllDataItem.id = serviceRequestAllData.id
            mServiceRequestAllDataItem.ServiceRequest = ArrayList()

            val serviceRequest = ServiceRequest()
            serviceRequest.SRDetails = ArrayList()
            serviceRequest.SRDetails?.add(srDetailsData)
            serviceRequest.id = serviceRequestAllData.ServiceRequest!![0].id
            mServiceRequestAllDataItem.ServiceRequest?.add(serviceRequest)

            val serviceRequestList = ServiceRequestAllData()
            serviceRequestList.add(mServiceRequestAllDataItem)

            basicinfoModel?.ServiceRequestMain = serviceRequestList
            basicinfoModel?.id = id
            viewModel.updateBasicInfo(basicinfoModel!!)
        }

        if (viewModel.basicInfoUpdate?.hasActiveObservers() == true)
            viewModel.basicInfoUpdate?.removeObservers(viewLifecycleOwner)
        viewModel.basicInfoUpdate?.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it.status == Resource.Status.LOADING) {
                    showProgressLayout()
                } else {
                    hideProgressLayout()
                }
                if (it.status == Resource.Status.SUCCESS && it.data?.Status?.isNotEmpty() == true) {
                    AppLogger.log("Successfully updated all fields")
                    dismiss()
                    viewModel.serviceRequestAll(id)
                } else {
                    AppLogger.log("UnExpected Error found")
                }
            } else {
                AppLogger.log("Something went wrong")
            }
        }
    }

    private fun showProgressLayout() {
        if (binding.progressLayout.visibility != View.VISIBLE)
            binding.progressLayout.visibility = View.VISIBLE
    }

    private fun hideProgressLayout() {
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = true
        return dialog
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SrDetailsBottomSheetDialogBinding.inflate(inflater)
        return binding.root
    }

}