package com.smarthub.baseapplication.ui.fragments.utilites.ac.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.*
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class ACFragAdapter(var baseFragment: BaseFragment, var listener: ACListListener, DGData: UtilityEquipmentAC?) : RecyclerView.Adapter<ACFragAdapter.ViewHold>() {
    private var datalist: UtilityEquipmentAC?=null
    private var equipmentData: UtilitySMPSEquipment?=null
    private var InsAccepData: UtiltyInstallationAcceptence?=null

    fun setData(data: UtilityEquipmentAC?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            datalist=DGData
        }catch (e:java.lang.Exception){
            Toast.makeText(baseFragment.requireContext(),"TowerInfoFrag error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }


    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Equipments"
    var type2 = "Installation & Acceptance"
    var type3 = "Consumable Materials"
    var type4= "PO Details"
    var type5="Preventive Maintenance"
    var type6="Attachments"
    init {
        list.add("Equipments")
        list.add("Installation & Acceptance")
        list.add("Consumable Materials")
        list.add("PO Details")
        list.add("Preventive Maintenance")
        list.add("Attachments")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    
    class EquipViewHold(itemView: View) : ViewHold(itemView) {
        var binding : UtilityAcEquipmentBinding = UtilityAcEquipmentBinding.bind(itemView)

        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
        }
    }
    class InstAccepViewHold(itemView: View) : ViewHold(itemView) {
        var binding : UtilitySmpsInstallationAccepBinding = UtilitySmpsInstallationAccepBinding.bind(itemView)

        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
        }
    }
    class ConsumMaterilViewHold(itemView: View) : ViewHold(itemView) {
        var binding : UtilitySmpsConnsumMaterialBinding = UtilitySmpsConnsumMaterialBinding.bind(itemView)
        val consumTableList:RecyclerView=binding.smpsConsumTable
        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

            binding.imgAdd.setOnClickListener {
                addTableItem()
            }
        }
        private fun addTableItem(){
            if (consumTableList.adapter!=null && consumTableList.adapter is ACConsumeMaterialTableAdapter){
                val adapter = consumTableList.adapter as ACConsumeMaterialTableAdapter
                adapter.addItem()
            }
        }
    }
    class PoDetailsViewHold(itemView: View) : ViewHold(itemView) {
        var binding: UtilitySmpsPoDetailsBinding = UtilitySmpsPoDetailsBinding.bind(itemView)
        var poTableList: RecyclerView=binding.smpsPoTable

        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
            binding.imgAdd.setOnClickListener {
                addTableItem()
            }
        }
        private fun addTableItem(){
            if (poTableList.adapter!=null && poTableList.adapter is ACPoTableAdapter){
                val adapter = poTableList.adapter as ACPoTableAdapter
                adapter.addItem()
            }
        }
    }
    class MaintenanceViewHold(itemView: View) : ViewHold(itemView) {
        var binding: UtilitySmpsMaintenenceBinding = UtilitySmpsMaintenenceBinding.bind(itemView)
        var maintenanceTable:RecyclerView=binding.smpsMaintenanceTable
        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

            binding.imgAdd.setOnClickListener {
                addTableItem()
            }

        }
        private fun addTableItem(){
            if (maintenanceTable.adapter!=null && maintenanceTable.adapter is ACMaintenanceTableAdapter){
                val adapter = maintenanceTable.adapter as ACMaintenanceTableAdapter
                adapter.addItem()
            }
        }

    }
    class Attachments(itemView: View) : ViewHold(itemView) {
        var binding: UtilityAttachmentsBinding = UtilityAttachmentsBinding.bind(itemView)
        val recyclerListener:RecyclerView = binding.root.findViewById(R.id.list_item)

        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

//            recyclerListener.adapter = adapter

//            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
//                listener.addAttachment()
//            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position]==type1)
            return 1
        else if (list[position]==type2)
            return 2
        else if (list[position]==type3)
            return 3
        else if (list[position]==type4)
            return 4
        else if (list[position]==type5)
            return 5
        else if (list[position]==type6)
            return 6
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_ac_equipment, parent, false)
                return EquipViewHold(view)
            }

            2-> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_installation_accep, parent, false)
                return InstAccepViewHold(view)
            }
            3-> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_connsum_material, parent, false)
                return ConsumMaterilViewHold(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_po_details, parent, false)
                return PoDetailsViewHold(view)
            }

            5-> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_maintenence, parent, false)
                return MaintenanceViewHold(view)
            }
            6-> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_attachments, parent, false)
                return Attachments(view)
            }

        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is EquipViewHold -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE

                    holder.binding.imgEdit.setOnClickListener {
                        if (AppController.getInstance().isTaskEditable) {
                            holder.binding.viewLayout.visibility = View.GONE
                            holder.binding.editLayout.visibility = View.VISIBLE
                        }
                    }
                    holder.binding.cancel.setOnClickListener {
                        holder.binding.viewLayout.visibility = View.VISIBLE
                        holder.binding.editLayout.visibility = View.GONE
                    }
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                if (datalist!=null && datalist?.Equipment?.isNotEmpty()==true){
                    equipmentData=datalist?.Equipment?.get(0)
                }
                if (equipmentData!=null){
                    // view mode
                    holder.binding.Type.text=equipmentData?.Type
                    holder.binding.SerialNumber.text=equipmentData?.SerialNumber
                    holder.binding.Make.text=equipmentData?.Make
                    holder.binding.Model.text=equipmentData?.Model
                    holder.binding.MaxPowerRating.text=equipmentData?.MaxPowerRating
                    holder.binding.OULocationMark.text=equipmentData?.LocationMark
                    holder.binding.CapacityRating.text=equipmentData?.CapacityRating
                    holder.binding.CopperTubing.text= equipmentData?.CopperTubing
                    holder.binding.CoolingTempSetting.text=equipmentData?.CoolingTempSetting
                    holder.binding.IUSizeL.text=equipmentData?.SizeL
                    holder.binding.IUSizeB.text=equipmentData?.SizeB
                    holder.binding.IUSizeH.text=equipmentData?.SizeH
                    holder.binding.OUSizeL.text=equipmentData?.OUSizeL
                    holder.binding.OUSizeB.text=equipmentData?.OUSizeB
                    holder.binding.OUSizeH.text=equipmentData?.OUSizeH
                    holder.binding.IUWeight.text=equipmentData?.Weight
                    holder.binding.OUWeight.text=equipmentData?.OUWeight
                    holder.binding.ManufacturingMonthYear.text= Utils.getFormatedDateMonthYear(equipmentData?.ManufacturedOn,"MMM-yyyy")
                    holder.binding.WarrantyPeriod.text=equipmentData?.WarrantyPeriod
                    holder.binding.WarrantyExpiryDate.text= Utils.getFormatedDate(equipmentData?.WarrantyExpiryDate,"dd-MMM-yyyy")
                    holder.binding.remarks.text=equipmentData?.remark
                    if (equipmentData?.OperationStatus?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatus, DropDowns.OperationStatus.name,equipmentData?.OperationStatus?.get(0).toString())

                    // edit mode
                    holder.binding.TypeEdit.setText(equipmentData?.Type)
                    holder.binding.SerialNumberEdit.setText(equipmentData?.SerialNumber)
                    holder.binding.MakeEdit.setText(equipmentData?.Make)
                    holder.binding.ModelEdit.setText(equipmentData?.Model)
                    holder.binding.CapacityRatingEdit.setText(equipmentData?.CapacityRating)
                    holder.binding.IUSizeLEdit.setText(equipmentData?.SizeL)
                    holder.binding.IUSizeBEdit.setText(equipmentData?.SizeB)
                    holder.binding.IUSizeHEdit.setText(equipmentData?.SizeH)
                    holder.binding.IuWeightEdit.setText(equipmentData?.Weight)
                    holder.binding.OUSizeLEdit.setText(equipmentData?.OUSizeL)
                    holder.binding.OUSizeBEdit.setText(equipmentData?.OUSizeB)
                    holder.binding.OUSizeHEdit.setText(equipmentData?.OUSizeH)
                    holder.binding.OUWeightEdit.setText(equipmentData?.OUWeight)
                    holder.binding.OULocationMarkEdit.setText(equipmentData?.LocationMark)
                    holder.binding.CopperTubingEdit.setText(equipmentData?.CopperTubing)
                    holder.binding.CoolingTempSettingEdit.setText(equipmentData?.CoolingTempSetting)
                    holder.binding.ManufacturingMonthYearEdit.text= Utils.getFormatedDateMonthYear(equipmentData?.ManufacturedOn,"MMM-yyyy")
                    holder.binding.WarrantyPeriodEdit.setText(equipmentData?.WarrantyPeriod)
                    holder.binding.WarrantyExpiryDateEdit.text= Utils.getFormatedDate(equipmentData?.WarrantyExpiryDate,"dd-MMM-yyyy")
                    holder.binding.remarksEdit.setText(equipmentData?.remark)
                }
                if (equipmentData!=null && equipmentData?.OperationStatus?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatusEdit,
                        DropDowns.OperationStatus.name,equipmentData?.OperationStatus?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatusEdit,
                        DropDowns.OperationStatus.name)

                holder.binding.update.setOnClickListener {
                    val tempEquipData=UtilitySMPSEquipment()
                    tempEquipData.let {
                        it.Type=holder.binding.TypeEdit.text.toString()
                        it.SerialNumber=holder.binding.SerialNumberEdit.text.toString()
                        it.Make=holder.binding.MakeEdit.text.toString()
                        it.Model=holder.binding.ModelEdit.text.toString()
                        it.CapacityRating=holder.binding.CapacityRatingEdit.text.toString()
                        it.SizeL=holder.binding.IUSizeLEdit.text.toString()
                        it.SizeB=holder.binding.IUSizeBEdit.text.toString()
                        it.SizeH=holder.binding.IUSizeHEdit.text.toString()
                        it.Weight=holder.binding.IuWeightEdit.text.toString()
                        it.OUSizeL=holder.binding.OUSizeLEdit.text.toString()
                        it.OUSizeB=holder.binding.OUSizeBEdit.text.toString()
                        it.OUSizeH=holder.binding.OUSizeHEdit.text.toString()
                        it.OUWeight=holder.binding.OUWeightEdit.text.toString()
                        it.LocationMark=holder.binding.OULocationMarkEdit.text.toString()
                        it.CopperTubing=holder.binding.CopperTubingEdit.text.toString()
                        it.MaxPowerRating=holder.binding.MaxPowerRatingEdit.text.toString()
                        it.CoolingTempSetting=holder.binding.CoolingTempSettingEdit.text.toString()
                        it.ManufacturedOn= Utils.getFullFormatedDate(holder.binding.ManufacturingMonthYearEdit.text.toString())
                        it.WarrantyPeriod=holder.binding.WarrantyPeriodEdit.text.toString()
                        it.WarrantyExpiryDate= Utils.getFullFormatedDate(holder.binding.WarrantyExpiryDateEdit.text.toString())
                        it.remark=holder.binding.remarksEdit.text.toString()
                        it.OperationStatus= arrayListOf(holder.binding.OperationalStatusEdit.selectedValue.id.toInt())
                        if (datalist!=null && datalist?.Equipment?.isNotEmpty()==true)
                            it.id=datalist?.Equipment?.get(0)?.id
                        val utilityACData= UtilityEquipmentAC()
                        utilityACData.Equipment= arrayListOf(it)
                        if (datalist!=null)
                            utilityACData.id=datalist?.id
                        listener.updateACData(utilityACData)
                    }
                }
                baseFragment.setDatePickerView(holder.binding.WarrantyExpiryDateEdit)
                baseFragment.setDatePickerView(holder.binding.ManufacturingMonthYearEdit)

            }
            is InstAccepViewHold -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE
                    holder.binding.OPVoltageLayout.visibility = View.GONE
                    holder.binding.OPCurrentLayout.visibility = View.GONE
                    holder.binding.OPVoltageLayoutEdit.visibility = View.GONE
                    holder.binding.OPCurrentLayoutEdit.visibility = View.GONE

                    holder.binding.imgEdit.setOnClickListener {
                        if (AppController.getInstance().isTaskEditable) {
                            holder.binding.viewLayout.visibility = View.GONE
                            holder.binding.editLayout.visibility = View.VISIBLE
                        }
                    }
                    holder.binding.cancel.setOnClickListener {
                        holder.binding.viewLayout.visibility = View.VISIBLE
                        holder.binding.editLayout.visibility = View.GONE
                    }
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                if (datalist!=null && datalist?.InstallationAndAcceptence?.isNotEmpty()==true){
                    InsAccepData=datalist?.InstallationAndAcceptence?.get(0)
                }
                if (InsAccepData!=null){
                    // view mode
                    if (InsAccepData?.VendorCompany?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.VendorName,DropDowns.VendorCompany.name,InsAccepData?.VendorCompany?.get(0).toString())
                    if (InsAccepData?.AcceptanceStatus?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.AcceptanceStatus,DropDowns.AcceptanceStatus.name,InsAccepData?.AcceptanceStatus?.get(0).toString())
                    holder.binding.VendorCode.text=InsAccepData?.VendorCode
                    holder.binding.VendorExecutiveName.text=InsAccepData?.VendorExecutiveName
                    holder.binding.VendorExecutiveEmailId.text=InsAccepData?.VendorEmailId
                    holder.binding.VendorExecutiveNumber.text=InsAccepData?.VendorExecutiveNumber
                    holder.binding.IPVoltage.text=InsAccepData?.InputVoltage
                    holder.binding.IPCurrent.text=InsAccepData?.InputCurrent
//                    holder.binding.OPVoltage.text=InsAccepData?.OutputVoltage
//                    holder.binding.OPCurrent.text=InsAccepData?.OutputCurrent
                    holder.binding.remarks.text=InsAccepData?.remark
                    holder.binding.nstallationDate.text=Utils.getFormatedDate(InsAccepData?.InstallationDate,"dd-MMM-yyyy")
                    holder.binding.AcceptenceDate.text=Utils.getFormatedDate(InsAccepData?.AcceptanceDate,"dd-MMM-yyyy")

                    // edit mode
                    holder.binding.VendorExcutiveNameEdit.setText(InsAccepData?.VendorExecutiveName)
                    holder.binding.VendorExecutiveEmailEdit.setText(InsAccepData?.VendorEmailId)
                    holder.binding.VendorExecutiveNumberEdit.setText(InsAccepData?.VendorExecutiveNumber)
                    holder.binding.IPVoltageEdit.setText(InsAccepData?.InputVoltage)
                    holder.binding.IPCurrentEdit.setText(InsAccepData?.InputCurrent)
//                    holder.binding.OPVoltageEdit.setText(InsAccepData?.OutputVoltage)
//                    holder.binding.OPCurrentEdit.setText(InsAccepData?.OutputCurrent)
                    holder.binding.remarksEdit.setText(InsAccepData?.remark)
                    holder.binding.InstallationDateEdit.text=Utils.getFormatedDate(InsAccepData?.InstallationDate,"dd-MMM-yyyy")
                    holder.binding.AcceptenceDateEdit.text=Utils.getFormatedDate(InsAccepData?.AcceptanceDate,"dd-MMM-yyyy")

                }
                if (InsAccepData!=null && InsAccepData?.VendorCompany?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit,DropDowns.VendorCompany.name,InsAccepData?.VendorCompany?.get(0).toString(),holder.binding.VendorCodeEdit)
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit,DropDowns.VendorCompany.name,holder.binding.VendorCodeEdit)
                if (InsAccepData!=null && InsAccepData?.AcceptanceStatus?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.AcceptenceStatusEdit,DropDowns.AcceptanceStatus.name,InsAccepData?.AcceptanceStatus?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.AcceptenceStatusEdit,DropDowns.AcceptanceStatus.name)

                baseFragment.setDatePickerView(holder.binding.AcceptenceDateEdit)
                baseFragment.setDatePickerView(holder.binding.InstallationDateEdit)

                holder.binding.update.setOnClickListener {
                    val tempInsData=UtiltyInstallationAcceptence()
                    tempInsData.let {
                        it.VendorCode=holder.binding.VendorCodeEdit.text.toString()
                        it.VendorExecutiveName=holder.binding.VendorExcutiveNameEdit.text.toString()
                        it.VendorEmailId=holder.binding.VendorExecutiveEmailEdit.text.toString()
                        it.VendorExecutiveNumber=holder.binding.VendorExecutiveNumberEdit.text.toString()
                        it.InputVoltage=holder.binding.IPVoltageEdit.text.toString()
//                        it.OutputVoltage=holder.binding.OPVoltageEdit.text.toString()
                        it.InputCurrent=holder.binding.IPCurrentEdit.text.toString()
//                        it.OutputCurrent=holder.binding.OPCurrentEdit.text.toString()
                        it.remark=holder.binding.remarksEdit.text.toString()
                        it.InstallationDate=Utils.getFullFormatedDate(holder.binding.InstallationDateEdit.text.toString())
                        it.AcceptanceDate=Utils.getFullFormatedDate(holder.binding.AcceptenceDateEdit.text.toString())
                        it.VendorCompany= arrayListOf(holder.binding.VendorNameEdit.selectedValue.id.toInt())
                        it.AcceptanceStatus= arrayListOf(holder.binding.AcceptenceStatusEdit.selectedValue.id.toInt())

                        if (datalist!=null && datalist?.InstallationAndAcceptence?.isNotEmpty()==true)
                            it.id=datalist?.InstallationAndAcceptence?.get(0)?.id
                        val utilityDGData= UtilityEquipmentAC()
                        utilityDGData.InstallationAndAcceptence= arrayListOf(it)
                        if (datalist!=null)
                            utilityDGData.id=datalist?.id
                        listener.updateACData(utilityDGData)
                    }
                }

            }
            is ConsumMaterilViewHold -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgAdd.visibility = View.VISIBLE

                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgAdd.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                if (datalist!=null)
                    holder.consumTableList.adapter=ACConsumeMaterialTableAdapter(baseFragment.requireContext(),listener,datalist?.ConsumableMaterial)
                else
                    holder.consumTableList.adapter=ACConsumeMaterialTableAdapter(baseFragment.requireContext(),listener, ArrayList())
            }
            is PoDetailsViewHold -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgAdd.visibility = View.VISIBLE

                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgAdd.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                if (datalist!=null)
                    holder.poTableList.adapter=ACPoTableAdapter(baseFragment.requireContext(),listener,datalist?.PODetail)
                else
                    holder.poTableList.adapter=ACPoTableAdapter(baseFragment.requireContext(),listener, ArrayList())
            }
            is MaintenanceViewHold -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgAdd.visibility = View.VISIBLE
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgAdd.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                if (datalist!=null)
                    holder.maintenanceTable.adapter=ACMaintenanceTableAdapter(baseFragment.requireContext(),listener,datalist?.PreventiveMaintenance)
                else
                    holder.maintenanceTable.adapter=ACMaintenanceTableAdapter(baseFragment.requireContext(),listener,ArrayList())

            }
            is Attachments -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE

                    holder.binding.root.findViewById<View>(R.id.attach_card).setOnClickListener {
                        if (datalist!=null){
                            listener.addAttachment(datalist?.id)
                        }
                        else
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                if (datalist!=null){
                    holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),datalist?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
                        override fun itemClicked(item : com.smarthub.baseapplication.model.siteIBoard.Attachments) {
                            listener.attachmentItemClicked()
                        }
                    })
                }
                else
                    AppLogger.log("Attachments Error")

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    var recyclerView: RecyclerView?=null
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }



    interface ACListListener {
        fun attachmentItemClicked()
        fun addAttachment(childIndex:Int?)
        fun editPoClicked(position:Int,data:UtilityPoDetails)
        fun viewPoClicked(position:Int,data:UtilityPoDetails)
        fun editConsumMaterialTableItem(position: Int,data: UtilityConsumableMaterial)
        fun viewConsumMaterialTableItem(position: Int,data:UtilityConsumableMaterial)
        fun editMaintenamceTableItem(position: Int,data:UtilityPreventiveMaintenance)
        fun viewMaintenanceTableItem(position: Int,data:UtilityPreventiveMaintenance)
        fun updateACData(updatedData: UtilityEquipmentAC)
    }

}
