package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TeamvenderDetailsBotomSheetBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.serviceRequest.AssignACQTeam
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequest
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TeamVendorDetailsBottomSheet(
    contentLayoutId: Int,
    var assignAcqTeamData: AssignACQTeam?,
    var serviceRequestAllData: ServiceRequestAllDataItem?,
    var viewmodel: HomeViewModel,
    var Id: String?
) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: TeamvenderDetailsBotomSheetBinding
    var basicinfoModel: BasicinfoModel? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basicinfoModel = BasicinfoModel()
        binding = TeamvenderDetailsBotomSheetBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }

        assignAcqTeamData!!.AssignACQTeamTeam!!.get(0).let { it ->
            binding.AcquisitionExecutiveName.setText(it?.ExecutiveName)
            binding.excutiveEmail.setText(it?.ExecutiveEmailId)
            binding.excuteNumber.setText(it?.ExecutiveMobile)
            binding.acqusationLeadName.setText(it?.LeadName)
            binding.leadEmail.setText(it?.LeadEmailId)
            binding.leadPhone.setText(it?.LeadMobile)

            AppPreferences.getInstance().setDropDown(
                binding.spinAcquisitionMode,
                DropDowns.AssignACQAcquistionMode.name,
                it.AcquistionMode
            )
            AppPreferences.getInstance().setDropDown(
                binding.spinAcquisitionType,
                DropDowns.AssignACQAcquisitionType.name,
                it.AcquisitionType
            )
            binding.acquisitionBudget.setText(it?.AcquisitionBudget)
            binding.AcquisitionTargetData.setText(it?.AcquisitionTargetDate)
            AppPreferences.getInstance().setDropDown(
                binding.spinVendorName,
                DropDowns.AssignACQVendorName.name,
                it.VendorName
            )
            binding.spinPONumber.setText(it?.PONumber)
            binding.address.setText(it?.OfficeAddress)
            binding.polineitem.setText(it?.POLineItemNo)
            binding.podate.setText(it?.PODate)
            binding.poamount.setText(it?.POAmount)
            binding.venderExcutivityName.setText(it?.VendorExecutiveName)
            binding.vendorExcutiveNumber.setText(it?.VendorExecutiveMobile)
            binding.remark.setText(it.Remark)
        }

        binding.spinAcquisitionMode.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                assignAcqTeamData!!.AssignACQTeamTeam!!.get(0).AcquistionMode =
                    AppPreferences.getInstance().getDropDown(DropDowns.AssignACQVendorName.name)
                        .get(position).id

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.spinAcquisitionType.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                assignAcqTeamData!!.AssignACQTeamTeam!!.get(0).AcquisitionType =
                    AppPreferences.getInstance()
                        .getDropDown(DropDowns.AssignACQAcquisitionType.name)
                        .get(position).id

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.spinVendorName.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                assignAcqTeamData!!.AssignACQTeamTeam!!.get(0).VendorExecutiveName =
                    AppPreferences.getInstance().getDropDown(DropDowns.AssignACQVendorName.name)
                        .get(position).id

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        binding.include.update.setOnClickListener {
            assignAcqTeamData!!.AssignACQTeamTeam!!.get(0).apply {
                this?.ExecutiveName = binding.AcquisitionExecutiveName.text.toString()
                this?.ExecutiveEmailId = binding.excutiveEmail.text.toString()
                this?.ExecutiveMobile = binding.excuteNumber.text.toString()
                this?.LeadName = binding.acqusationLeadName.text.toString()
                this?.LeadEmailId = binding.leadEmail.text.toString()
                this?.LeadMobile = binding.leadPhone.text.toString()
                this?.AcquisitionBudget = binding.acquisitionBudget.text.toString().ifEmpty { "0" }
                this?.AcquisitionTargetDate = binding.AcquisitionTargetData.text.toString()
                this?.PONumber = binding.spinPONumber.text.toString()
                this?.OfficeAddress = binding.address.text.toString()
                this?.POLineItemNo = binding.polineitem.text.toString()
                this?.PODate = binding.podate.text.toString()
                this?.POAmount = binding.poamount.text.toString()
                this?.VendorExecutiveName = binding.venderExcutivityName.text.toString()
                this?.VendorExecutiveMobile = binding.vendorExcutiveNumber.text.toString()
                this?.Remark = binding.remark.text.toString()
            }

            val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
            mServiceRequestAllDataItem.id = serviceRequestAllData!!.id
            mServiceRequestAllDataItem.AssignACQTeam = ArrayList()

            val assignacq = AssignACQTeam()
            assignacq.AssignACQTeamTeam = ArrayList()
            assignacq.AssignACQTeamTeam?.add(assignAcqTeamData!!.AssignACQTeamTeam!!.get(0))
            assignacq.id = serviceRequestAllData!!.AssignACQTeam!![0].id
            mServiceRequestAllDataItem.AssignACQTeam?.add(assignacq)
            val serviceRequestList = ServiceRequestAllData()
            serviceRequestList.add(mServiceRequestAllDataItem)

            basicinfoModel?.ServiceRequestMain = serviceRequestList
            basicinfoModel?.id = Id!!
            viewmodel.updateBasicInfo(basicinfoModel!!)
        }

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TeamvenderDetailsBotomSheetBinding.inflate(inflater)
        return binding.root
    }


}