package com.smarthub.baseapplication.ui.fragments.utilites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.*
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.ui.fragments.utilites.tableAdapters.*
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class SmpsUtilityFragAdapter(var baseFragment: BaseFragment, var listener: SmpsInfoListListener,smpsData: UtilityEquipmentSmp?) : RecyclerView.Adapter<SmpsUtilityFragAdapter.ViewHold>() {
    private var datalist: UtilityEquipmentSmp?=null
    private var equipmentData: UtilitySMPSEquipment?=null
    private var InsAccepData: UtiltyInstallationAcceptence?=null

    fun setData(data: UtilityEquipmentSmp?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            datalist=smpsData
        }catch (e:java.lang.Exception){
            Toast.makeText(baseFragment.requireContext(),"TowerInfoFrag error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }


    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Equipments"
    var type2 = "Rectifier Module"
    var type3 = "Connected Loads"
    var type4 = "Installation & Acceptance"
    var type5 = "Consumable Materials"
    var type6= "PO Details"
    var type7="Preventive Maintenance"
    var type8="Attachments"
    init {
        list.add("Equipments")
        list.add("Rectifier Module")
        list.add("Connected Loads")
        list.add("Installation & Acceptance")
        list.add("Consumable Materials")
        list.add("PO Details")
        list.add("Preventive Maintenance")
        list.add("Attachments")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class EquipViewHold(itemView: View) : ViewHold(itemView) {
        var binding : UtilitySmpsEquipmentsBinding = UtilitySmpsEquipmentsBinding.bind(itemView)

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
    class RectifierViewHold(itemView: View) : ViewHold(itemView) {
        var binding : UtilitySmpsRectifireBinding = UtilitySmpsRectifireBinding.bind(itemView)
        val RectifireTableList:RecyclerView=binding.smpsRectifireTable
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
            if (RectifireTableList.adapter!=null && RectifireTableList.adapter is SmpsRectifireTableAdapter){
                val adapter = RectifireTableList.adapter as SmpsRectifireTableAdapter
                adapter.addItem()
            }
        }
    }
    class ConnectedLoadsViewHold(itemView: View) : ViewHold(itemView) {
        var binding : UtilitySmpsConnectedLoadsBinding = UtilitySmpsConnectedLoadsBinding.bind(itemView)
        val ConnLoadTableList:RecyclerView=binding.smpsConnLoadsTable
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
            if (ConnLoadTableList.adapter!=null && ConnLoadTableList.adapter is SmpsConnLoadsTableAdapter){
                val adapter = ConnLoadTableList.adapter as SmpsConnLoadsTableAdapter
                adapter.addItem()
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
        val ConsumTableList:RecyclerView=binding.smpsConsumTable
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
            if (ConsumTableList.adapter!=null && ConsumTableList.adapter is SmpsConsumeTableAdapter){
                val adapter = ConsumTableList.adapter as SmpsConsumeTableAdapter
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
            if (poTableList.adapter!=null && poTableList.adapter is SmpsPoTableAdapter){
                val adapter = poTableList.adapter as SmpsPoTableAdapter
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
            if (maintenanceTable.adapter!=null && maintenanceTable.adapter is SmpsMaintenanceTableAdapter){
                val adapter = maintenanceTable.adapter as SmpsMaintenanceTableAdapter
                adapter.addItem()
            }
        }

    }
    class Attachments(itemView: View,listener: SmpsInfoListListener) : ViewHold(itemView) {
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
        if ( list[position]==type1)
            return 1
        else if (list[position]==type2)
            return 2
        else if ( list[position]==type3)
            return 3
        else if ( list[position]==type4)
            return 4
        else if (list[position]==type5)
            return 5
        else if (list[position]==type6)
            return 6
        else if (list[position]==type7)
            return 7
        else if (list[position]==type8)
            return 8
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_equipments, parent, false)
                return EquipViewHold(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_rectifire, parent, false)
                return RectifierViewHold(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_connected_loads, parent, false)
                return ConnectedLoadsViewHold(view)
            }
            4->  {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_installation_accep, parent, false)
                return InstAccepViewHold(view)
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_connsum_material, parent, false)
                return ConsumMaterilViewHold(view)
            }
            6 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_po_details, parent, false)
                return PoDetailsViewHold(view)
            }
            7 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_maintenence, parent, false)
                return MaintenanceViewHold(view)
            }
            8 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_attachments, parent, false)
                return Attachments(view,listener)
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
                        holder.binding.viewLayout.visibility = View.GONE
                        holder.binding.editLayout.visibility = View.VISIBLE
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
                    holder.binding.minVoltageRating.text=equipmentData?.VoltageMin
                    holder.binding.maxVoltageRating.text=equipmentData?.VoltageMax
                    holder.binding.CapacityRating.text=equipmentData?.CapacityRating

                    if(equipmentData?.InstalledLocationType!! > 0) {
                        AppPreferences.getInstance().setDropDown(holder.binding.InstallationLocationType,
                            DropDowns.InstallationLocationType.name, "1")
                    }

                    holder.binding.CabinetSizeL.text=equipmentData?.SizeL
                    holder.binding.CabinetSizeB.text=equipmentData?.SizeB
                    holder.binding.CabinetSizeH.text=equipmentData?.SizeH
                    holder.binding.Weight.text=equipmentData?.Weight
                    holder.binding.ManufacturingMonthYear.text=Utils.getFormatedDateMonthYear(equipmentData?.ManufacturedOn,"MMM-yyyy")
                    holder.binding.WarrantyPeriod.text=equipmentData?.WarrantyPeriod
                    holder.binding.WarrantyExpiryDate.text=Utils.getFormatedDate(equipmentData?.WarrantyExpiryDate,"dd-MMM-yyyy")
                    holder.binding.RackNo.text=equipmentData?.RackNo
                    holder.binding.remarks.text=equipmentData?.remark
                    holder.binding.RackUSpaceUsed.text=equipmentData?.RackUSpaceUsed.toString()
                    if (equipmentData?.InstalledLocationType!=null)
                        AppPreferences.getInstance().setDropDown(holder.binding.InstallationLocationType,DropDowns.InstallationLocationType.name,equipmentData?.InstalledLocationType.toString())
                    if (equipmentData?.OperationStatus?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatus,DropDowns.OperationStatus.name,equipmentData?.OperationStatus?.get(0).toString())

                    // edit mode
                    holder.binding.TypeEdit.setText(equipmentData?.Type)
                    holder.binding.SerialNumberEdit.setText(equipmentData?.SerialNumber)
                    holder.binding.MakeEdit.setText(equipmentData?.Make)
                    holder.binding.ModelEdit.setText(equipmentData?.Model)
                    holder.binding.minVoltageRatingEdit.setText(equipmentData?.VoltageMin)
                    holder.binding.maxVoltageRatingEdit.setText(equipmentData?.VoltageMax)
                    holder.binding.CapacityRatingEdit.setText(equipmentData?.CapacityRating)
                    holder.binding.CabinetSizeLEdit.setText(equipmentData?.SizeL)
                    holder.binding.CabinetSizeBEdit.setText(equipmentData?.SizeB)
                    holder.binding.CabinetSizeHEdit.setText(equipmentData?.SizeH)
                    holder.binding.WeightEdit.setText(equipmentData?.Weight)
                    holder.binding.ManufacturingMonthYearEdit.text=Utils.getFormatedDateMonthYear(equipmentData?.ManufacturedOn,"MMM-yyyy")
                    holder.binding.WarrantyPeriodEdit.setText(equipmentData?.WarrantyPeriod)
                    holder.binding.WarrantyExpiryDateEdit.text=Utils.getFormatedDate(equipmentData?.WarrantyExpiryDate,"dd-MMM-yyyy")
                    holder.binding.RackNoEdit.setText(equipmentData?.RackNo)
                    holder.binding.remarksEdit.setText(equipmentData?.remark)
                    holder.binding.RackUSpaceUsedEdit.setText(equipmentData?.RackUSpaceUsed.toString())
                }
                if (equipmentData!=null && equipmentData?.OperationStatus?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatusEdit,DropDowns.OperationStatus.name,equipmentData?.OperationStatus?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatusEdit,DropDowns.OperationStatus.name)

                if (equipmentData!=null && equipmentData?.InstalledLocationType!=null)
                    AppPreferences.getInstance().setDropDown(holder.binding.InstalledLocationTypeEdit,DropDowns.InstallationLocationType.name,equipmentData?.InstalledLocationType.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.InstalledLocationTypeEdit,DropDowns.InstallationLocationType.name)

                holder.binding.update.setOnClickListener {
                    val tempEquipData=UtilitySMPSEquipment()
                    tempEquipData.let {
                        it.Type=holder.binding.TypeEdit.text.toString()
                        it.SerialNumber=holder.binding.SerialNumberEdit.text.toString()
                        it.Make=holder.binding.MakeEdit.text.toString()
                        it.Model=holder.binding.ModelEdit.text.toString()
                        it.VoltageMax=holder.binding.maxVoltageRatingEdit.text.toString()
                        it.VoltageMin=holder.binding.minVoltageRatingEdit.text.toString()
                        it.CapacityRating=holder.binding.CapacityRatingEdit.text.toString()
                        it.InstalledLocationType=holder.binding.InstalledLocationTypeEdit.selectedValue.id.toIntOrNull()
                        it.SizeL=holder.binding.CabinetSizeLEdit.text.toString()
                        it.SizeB=holder.binding.CabinetSizeHEdit.text.toString()
                        it.SizeH=holder.binding.CabinetSizeHEdit.text.toString()
                        it.ManufacturedOn=Utils.getFullFormatedDate(holder.binding.ManufacturingMonthYearEdit.text.toString())
                        it.WarrantyPeriod=holder.binding.WarrantyPeriodEdit.text.toString()
                        it.WarrantyExpiryDate=Utils.getFullFormatedDate(holder.binding.WarrantyExpiryDateEdit.text.toString())
                        it.RackNo=holder.binding.RackNoEdit.text.toString()
                        it.RackUSpaceUsed=holder.binding.RackUSpaceUsedEdit.text.toString().toIntOrNull()
                        it.remark=holder.binding.remarksEdit.text.toString()
                        it.OperationStatus= arrayListOf(holder.binding.OperationalStatusEdit.selectedValue.id.toInt())
                        if (datalist!=null && datalist?.Equipment?.isNotEmpty()==true)
                            it.id=datalist?.Equipment?.get(0)?.id
                        val utilitySMPSData=UtilityEquipmentSmp()
                        utilitySMPSData.Equipment= arrayListOf(it)
                        if (datalist!=null)
                            utilitySMPSData.id=datalist?.id
                        listener.updateSMPSData(utilitySMPSData)
                    }
                }
                baseFragment.setDatePickerView(holder.binding.WarrantyExpiryDateEdit)
                baseFragment.setDatePickerView(holder.binding.ManufacturingMonthYearEdit)

            }
            is RectifierViewHold -> {
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
                    holder.RectifireTableList.adapter=SmpsRectifireTableAdapter(baseFragment.requireContext(),listener,datalist?.RectifierModule!!)
                else
                    holder.RectifireTableList.adapter=SmpsRectifireTableAdapter(baseFragment.requireContext(),listener,ArrayList())


            }
            is ConnectedLoadsViewHold -> {
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
                    holder.ConnLoadTableList.adapter=SmpsConnLoadsTableAdapter(baseFragment.requireContext(),listener,datalist?.ConnectedLoad)
                else
                    holder.ConnLoadTableList.adapter=SmpsConnLoadsTableAdapter(baseFragment.requireContext(),listener,
                        ArrayList()
                    )
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

                    holder.binding.imgEdit.setOnClickListener {
                        holder.binding.viewLayout.visibility = View.GONE
                        holder.binding.editLayout.visibility = View.VISIBLE
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
                    holder.binding.OPVoltage.text=InsAccepData?.OutputVoltage
                    holder.binding.OPCurrent.text=InsAccepData?.OutputCurrent
                    holder.binding.remarks.text=InsAccepData?.remark
                    holder.binding.nstallationDate.text=Utils.getFormatedDate(InsAccepData?.InstallationDate,"dd-MMM-yyyy")
                    holder.binding.AcceptenceDate.text=Utils.getFormatedDate(InsAccepData?.AcceptanceDate,"dd-MMM-yyyy")

                    // edit mode
                    holder.binding.VendorCodeEdit.setText(InsAccepData?.VendorCode)
                    holder.binding.VendorExcutiveNameEdit.setText(InsAccepData?.VendorExecutiveName)
                    holder.binding.VendorExecutiveEmailEdit.setText(InsAccepData?.VendorEmailId)
                    holder.binding.VendorExecutiveNumberEdit.setText(InsAccepData?.VendorExecutiveNumber)
                    holder.binding.IPVoltageEdit.setText(InsAccepData?.InputVoltage)
                    holder.binding.IPCurrentEdit.setText(InsAccepData?.InputCurrent)
                    holder.binding.OPVoltageEdit.setText(InsAccepData?.OutputVoltage)
                    holder.binding.OPCurrentEdit.setText(InsAccepData?.OutputCurrent)
                    holder.binding.remarksEdit.setText(InsAccepData?.remark)
                    holder.binding.InstallationDateEdit.text=Utils.getFormatedDate(InsAccepData?.InstallationDate,"dd-MMM-yyyy")
                    holder.binding.AcceptenceDateEdit.text=Utils.getFormatedDate(InsAccepData?.AcceptanceDate,"dd-MMM-yyyy")

                }
                if (InsAccepData!=null && InsAccepData?.VendorCompany?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit,DropDowns.VendorCompany.name,InsAccepData?.VendorCompany?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit,DropDowns.VendorCompany.name)
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
                        it.OutputVoltage=holder.binding.OPVoltageEdit.text.toString()
                        it.InputCurrent=holder.binding.IPCurrentEdit.text.toString()
                        it.OutputCurrent=holder.binding.OPCurrentEdit.text.toString()
                        it.remark=holder.binding.remarksEdit.text.toString()
                        it.InstallationDate=Utils.getFullFormatedDate(holder.binding.InstallationDateEdit.text.toString())
                        it.AcceptanceDate=Utils.getFullFormatedDate(holder.binding.AcceptenceDateEdit.text.toString())
                        it.VendorCompany= arrayListOf(holder.binding.VendorNameEdit.selectedValue.id.toInt())
                        it.AcceptanceStatus= arrayListOf(holder.binding.AcceptenceStatusEdit.selectedValue.id.toInt())

                        if (datalist!=null && datalist?.InstallationAndAcceptence?.isNotEmpty()==true)
                            it.id=datalist?.InstallationAndAcceptence?.get(0)?.id
                        val utilitySMPSData=UtilityEquipmentSmp()
                        utilitySMPSData.InstallationAndAcceptence= arrayListOf(it)
                        if (datalist!=null)
                            utilitySMPSData.id=datalist?.id
                        listener.updateSMPSData(utilitySMPSData)
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
                    holder.ConsumTableList.adapter= SmpsConsumeTableAdapter(baseFragment.requireContext(),listener,datalist?.ConsumableMaterial)
                else
                    holder.ConsumTableList.adapter= SmpsConsumeTableAdapter(baseFragment.requireContext(),listener,
                        ArrayList()
                    )
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
                    holder.poTableList.adapter= SmpsPoTableAdapter(baseFragment.requireContext(),listener,datalist?.PODetail)
                else
                    holder.poTableList.adapter= SmpsPoTableAdapter(baseFragment.requireContext(),listener,
                        ArrayList()
                    )

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
                    holder.maintenanceTable.adapter=SmpsMaintenanceTableAdapter(baseFragment.requireContext(),listener,datalist?.PreventiveMaintenance,datalist?.id)
                else
                    holder.maintenanceTable.adapter=SmpsMaintenanceTableAdapter(baseFragment.requireContext(),listener,ArrayList(),null)
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
                        override fun itemClicked() {
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



    interface SmpsInfoListListener {
        fun attachmentItemClicked()
        fun addAttachment(childIndex:Int?)
        fun EditMaintenance(data:UtilityPreventiveMaintenance,SmpsIndex:Int?)
        fun ViewMaintenance(data:UtilityPreventiveMaintenance,SmpsIndex:Int?)
        fun editPoClicked(position:Int,data:UtilityPoDetails)
        fun viewPoClicked(position:Int,data:UtilityPoDetails)
        fun editRectifireTableItem(position: Int,data:UtilityRectifierModule)
        fun viewRectifireTableItem(position: Int,data: UtilityRectifierModule)
        fun editConnLoadsTableItem(position: Int,data: UtilityConnectedLoad)
        fun viewConnLoadsTableItem(position: Int,data:UtilityConnectedLoad)
        fun editConsumMaterialTableItem(position: Int,data:UtilityConsumableMaterial)
        fun viewConsumMaterialTableItem(position: Int,data:UtilityConsumableMaterial)
        fun updateSMPSData(updatedData: UtilityEquipmentSmp)
    }

}
