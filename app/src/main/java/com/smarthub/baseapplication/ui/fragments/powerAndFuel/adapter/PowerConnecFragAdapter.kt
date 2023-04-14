package com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.*
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentSmp
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerConsumableTableAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerFuelPaymentTableAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerFuelPoTableAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerFuelTariffTableAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class PowerConnecFragAdapter(var baseFragment: BaseFragment, var listener: PowerConnectionListListener,data:NewPowerFuelAllData?) : RecyclerView.Adapter<PowerConnecFragAdapter.ViewHold>() {
    private var datalist: PowerConnectionAllData?=null
    private var PowerConnData:PowerConnectionDetail?=null
    private var insAccepData:PowerInstallationAndAcceptence?=null

    fun setData(data: PowerConnectionAllData?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.PowerAndFuelEBConnection?.isNotEmpty()==true){
                datalist=data.PowerAndFuelEBConnection?.get(0)
            }
        }catch (e:java.lang.Exception){
            Toast.makeText(baseFragment.requireContext(),"TowerInfoFrag error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }



    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Power Connection Details"
    var type2 = "Installation & Acceptence"
    var type3 = "PO Details"
    var type4 = "Consumable Materials"
    var type5 = "Tariffs  Details"
    var type6 = "Authority Payments"
    var type7 = "Attachment"

    init {
        list.add("Power Connection Details")
        list.add("Installation & Acceptence")
        list.add("PO Details")
        list.add("Consumable Materials")
        list.add("Tariffs  Details")
        list.add("Authority Payments")
        list.add("Attachment")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : PowerConnectionsDetailsBinding = PowerConnectionsDetailsBinding.bind(itemView)

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
    class ViewHold2(itemView: View) : ViewHold(itemView) {
        var binding : PowerInstallationAcceptenceBinding = PowerInstallationAcceptenceBinding.bind(itemView)

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
    class ViewHold3(itemView: View) : ViewHold(itemView) {
        var binding: TowerPoItemBinding = TowerPoItemBinding.bind(itemView)
        var poTableList: RecyclerView=binding.towerPoTableItem

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
            if (poTableList.adapter!=null && poTableList.adapter is PowerFuelPoTableAdapter){
                val adapter = poTableList.adapter as PowerFuelPoTableAdapter
                adapter.addItem()
            }
        }
    }
    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding: TowerConsumableItemBinding = TowerConsumableItemBinding.bind(itemView)
        val ConsumableTableList : RecyclerView = binding.towerConsumableTableItem
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
            if (ConsumableTableList.adapter!=null && ConsumableTableList.adapter is PowerConsumableTableAdapter){
                val adapter = ConsumableTableList.adapter as PowerConsumableTableAdapter
                adapter.addItem()
            }
        }
    }
    class ViewHold5(itemView: View) : ViewHold(itemView) {
        var binding: PowerFuelTariffsDetailsBinding = PowerFuelTariffsDetailsBinding.bind(itemView)
        var tariffTable: RecyclerView = binding.tariffTableItem
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
            if (tariffTable.adapter!=null && tariffTable.adapter is PowerFuelTariffTableAdapter){
                val adapter = tariffTable.adapter as PowerFuelTariffTableAdapter
                adapter.addItem()
            }
        }
    }
    class ViewHold6(itemView: View) : ViewHold(itemView) {
        var binding: PowerAuthorityPaymentTableBinding = PowerAuthorityPaymentTableBinding.bind(itemView)
        var authorityPaymentTable: RecyclerView = binding.powerPaymentTableItem
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
            if (authorityPaymentTable.adapter!=null && authorityPaymentTable.adapter is PowerFuelPaymentTableAdapter){
                val adapter = authorityPaymentTable.adapter as PowerFuelPaymentTableAdapter
                adapter.addItem()
            }
        }
    }
    class ViewHold7(itemView: View,listener: PowerConnectionListListener) : ViewHold(itemView) {
        var binding: TowerAttachmentInfoBinding = TowerAttachmentInfoBinding.bind(itemView)
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
        else if (list[position]==type7)
            return 7
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.power_connections_details, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.power_installation_acceptence, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_po_item, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_consumable_item, parent, false)
                return ViewHold4(view)
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.power_fuel_tariffs_details, parent, false)
                return ViewHold5(view)
            }
            6 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.power_authority_payment_table, parent, false)
                return ViewHold6(view)
            }
            7 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_attachment_info, parent, false)
                return ViewHold7(view,listener)
            }

        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                holder.binding.imgEdit.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.GONE
                    holder.binding.editLayout.visibility = View.VISIBLE
                }
                holder.binding.cancel.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE

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
                if (datalist!=null && datalist?.PowerAndFuelEBConnectionEBDetail?.isNotEmpty()==true){
                    PowerConnData=datalist?.PowerAndFuelEBConnectionEBDetail?.get(0)
                }
                if (PowerConnData!=null){
                    // view mode
                    if (PowerConnData?.PowerConnectionType?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.PowerConnectionType,DropDowns.PowerConnectionType.name,PowerConnData?.PowerConnectionType?.get(0).toString())
                    if (PowerConnData?.PowerType!= null && PowerConnData?.PowerType!! >0)
                        AppPreferences.getInstance().setDropDown(holder.binding.powerType,DropDowns.PowerType.name,PowerConnData?.PowerType.toString())
                    if (PowerConnData?.MeterType!= null && PowerConnData?.MeterType!! >0)
                        AppPreferences.getInstance().setDropDown(holder.binding.MeterType,DropDowns.MeterType.name,PowerConnData?.MeterType.toString())
                    holder.binding.PowerSupplier.text=PowerConnData?.ElectricitySupplier
                    holder.binding.ConsumerNo.text=PowerConnData?.ConsumerNumber
                    holder.binding.ConnectedLoad.text=PowerConnData?.ConnectedLoad
                    holder.binding.AvgAvailibillity.text=PowerConnData?.AverageAvailibility
                    holder.binding.PowerConnRating.text=PowerConnData?.PowerRating
                    holder.binding.MeterLocationMark.text=PowerConnData?.MeterLocationMark
                    holder.binding.MeterSerialNumber.text=PowerConnData?.MeterSerialNumber
                    holder.binding.VoltageRatingMin.text=PowerConnData?.VoltageMin
                    holder.binding.VoltageRatingMax.text=PowerConnData?.VoltageMax
                    holder.binding.remarks.text=PowerConnData?.remark

                    // edit mode
                    holder.binding.PowerSupplierEdit.setText(PowerConnData?.ElectricitySupplier)
                    holder.binding.ConsumerNumberEdit.setText(PowerConnData?.ConsumerNumber)
                    holder.binding.ConnectedLoadEdit.setText(PowerConnData?.ConnectedLoad)
                    holder.binding.AvgAvailabilityEdit.setText(PowerConnData?.AverageAvailibility)
                    holder.binding.PowerConnRatingEdit.setText(PowerConnData?.PowerRating)
                    holder.binding.MeterLocationMarkEdit.setText(PowerConnData?.MeterLocationMark)
                    holder.binding.MeterSerialNumberEdit.setText(PowerConnData?.MeterSerialNumber)
                    holder.binding.minVoltageRatingEdit.setText(PowerConnData?.VoltageMin)
                    holder.binding.maxVoltageRatingEdit.setText(PowerConnData?.VoltageMax)
                    holder.binding.remarksEdit.setText(PowerConnData?.remark)
                }
                if (PowerConnData!=null && PowerConnData?.PowerConnectionType?.isNotEmpty() == true)
                    AppPreferences.getInstance().setDropDown(holder.binding.PowerConnectionTypeEdit,DropDowns.PowerConnectionType.name,PowerConnData?.PowerConnectionType?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.PowerConnectionTypeEdit,DropDowns.PowerConnectionType.name)
                if (PowerConnData!=null && PowerConnData?.PowerType!= null && PowerConnData?.PowerType!! >0)
                    AppPreferences.getInstance().setDropDown(holder.binding.PowerTypeEdit,DropDowns.PowerType.name,PowerConnData?.PowerType.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.PowerTypeEdit,DropDowns.PowerType.name)
                if (PowerConnData!=null && PowerConnData?.MeterType!= null && PowerConnData?.MeterType!! >0)
                    AppPreferences.getInstance().setDropDown(holder.binding.MeterTypeEdit,DropDowns.MeterType.name,PowerConnData?.MeterType.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.MeterTypeEdit,DropDowns.MeterType.name)

                holder.binding.update.setOnClickListener {
                    val tempPowerConnDetailData= PowerConnectionDetail()
                    val tempPowerConnAllData= PowerConnectionAllData()
                    tempPowerConnDetailData.let {
                        it.ElectricitySupplier=holder.binding.PowerSupplierEdit.text.toString()
                        it.ConsumerNumber=holder.binding.ConsumerNumberEdit.text.toString()
                        it.MeterSerialNumber=holder.binding.MeterSerialNumberEdit.text.toString()
                        it.VoltageMin=holder.binding.minVoltageRatingEdit.text.toString()
                        it.VoltageMax=holder.binding.maxVoltageRatingEdit.text.toString()
                        it.PowerRating=holder.binding.PowerConnRatingEdit.text.toString()
                        it.ConnectedLoad=holder.binding.ConnectedLoadEdit.text.toString()
                        it.MeterLocationMark=holder.binding.MeterLocationMarkEdit.text.toString()
                        it.AverageAvailibility=holder.binding.AvgAvailabilityEdit.text.toString()
                        it.remark=holder.binding.remarksEdit.text.toString()
                        it.PowerConnectionType= arrayListOf(holder.binding.PowerConnectionTypeEdit.selectedValue.id.toInt())
                        it.MeterType= holder.binding.MeterTypeEdit.selectedValue.id.toIntOrNull()
                        it.PowerType= holder.binding.PowerTypeEdit.selectedValue.id.toIntOrNull()
                        if (PowerConnData!=null)
                            it.id=PowerConnData?.id
                    }
                    tempPowerConnAllData.PowerAndFuelEBConnectionEBDetail= arrayListOf(tempPowerConnDetailData)
                    if (datalist!=null)
                        tempPowerConnAllData.id=datalist?.id
                    listener.updateData(tempPowerConnAllData)
                }
            }
            is ViewHold2 -> {
                holder.binding.imgEdit.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.GONE
                    holder.binding.editLayout.visibility = View.VISIBLE
                }
                holder.binding.cancel.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE
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
                if (datalist!=null && datalist?.InstallationAndAcceptence?.isNotEmpty()==true)
                    insAccepData=datalist?.InstallationAndAcceptence?.get(0)
                if (insAccepData!=null){
                    //view mode
                    if (insAccepData?.VendorCompany?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.vendorName,DropDowns.VendorCompany.name,insAccepData?.VendorCompany?.get(0).toString())
                    if (insAccepData?.AcceptanceStatus?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.acceptenceStatus,DropDowns.AcceptanceStatus.name,insAccepData?.AcceptanceStatus?.get(0).toString())
                    holder.binding.vendorExecutiveName.text=insAccepData?.VendorExecutiveName
                    holder.binding.vendorExecutiveNo.text=insAccepData?.VendorExecutiveNumber
                    holder.binding.vendorExecutiveEmailId.text=insAccepData?.VendorEmailId
                    holder.binding.Remarks.text=insAccepData?.remark
                    holder.binding.vendorCode.text=insAccepData?.VendorCode
                    holder.binding.installationDate.text=Utils.getFormatedDate(insAccepData?.InstallationDate,"dd-MMM-yyyy")
                    holder.binding.acceptenceDate.text=Utils.getFormatedDate(insAccepData?.AcceptanceDate,"dd-MMM-yyyy")

                    // edit mode
                    holder.binding.VendorExcutiveNameEdit.setText(insAccepData?.VendorExecutiveName)
                    holder.binding.VendorExecutiveNumberEdit.setText(insAccepData?.VendorExecutiveNumber)
                    holder.binding.VendorExecutiveEmailEdit.setText(insAccepData?.VendorEmailId)
                    holder.binding.remarksEdit.setText(insAccepData?.remark)
                    holder.binding.InstallationDateEdit.text=Utils.getFormatedDate(insAccepData?.InstallationDate,"dd-MMM-yyyy")
                    holder.binding.AcceptenceDateEdit.text=Utils.getFormatedDate(insAccepData?.AcceptanceDate,"dd-MMM-yyyy")
                }
                if (insAccepData!=null && insAccepData?.VendorCompany?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit,DropDowns.VendorCompany.name,insAccepData?.VendorCompany?.get(0).toString(),holder.binding.VendorCodeEdit)
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit,DropDowns.VendorCompany.name,holder.binding.VendorCodeEdit)
                if (insAccepData!=null && insAccepData?.AcceptanceStatus?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.AcceptenceStatusEdit,DropDowns.AcceptanceStatus.name,insAccepData?.AcceptanceStatus?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.AcceptenceStatusEdit,DropDowns.AcceptanceStatus.name,)

                holder.binding.update.setOnClickListener {
                    val tempInsData= PowerInstallationAndAcceptence()
                    val tempPowerConnAllData= PowerConnectionAllData()
                    tempInsData.let {
                        it.VendorCode=holder.binding.VendorCodeEdit.text.toString()
                        it.VendorExecutiveName=holder.binding.VendorExcutiveNameEdit.text.toString()
                        it.VendorEmailId=holder.binding.VendorExecutiveEmailEdit.text.toString()
                        it.VendorExecutiveNumber=holder.binding.VendorExecutiveNumberEdit.text.toString()
                        it.remark=holder.binding.remarksEdit.text.toString()
                        it.InstallationDate=Utils.getFullFormatedDate(holder.binding.InstallationDateEdit.text.toString())
                        it.AcceptanceDate=Utils.getFullFormatedDate(holder.binding.AcceptenceDateEdit.text.toString())
                        it.VendorCompany= arrayListOf(holder.binding.VendorNameEdit.selectedValue.id.toInt())
                        it.AcceptanceStatus= arrayListOf(holder.binding.AcceptenceStatusEdit.selectedValue.id.toInt())
                        if (insAccepData!=null)
                            it.id=insAccepData?.id
                    }
                    tempPowerConnAllData.InstallationAndAcceptence= arrayListOf(tempInsData)
                    if (datalist!=null)
                        tempPowerConnAllData.id=datalist?.id
                    listener.updateData(tempPowerConnAllData)
                }
                baseFragment.setDatePickerView(holder.binding.AcceptenceDateEdit)
                baseFragment.setDatePickerView(holder.binding.InstallationDateEdit)
            }
            is ViewHold3 -> {
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
                    holder.poTableList.adapter=PowerFuelPoTableAdapter(baseFragment.requireContext(),listener,datalist?.PODetail)
                else{
                    AppLogger.log("error in Power fuel Po details data")
                    holder.poTableList.adapter=PowerFuelPoTableAdapter(baseFragment.requireContext(),listener,ArrayList())
                }

            }
            is ViewHold4 -> {
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
                try {
                    if (datalist!=null){
                        holder.ConsumableTableList.adapter=PowerConsumableTableAdapter(baseFragment.requireContext(),listener,datalist?.ConsumableMaterial)
                    }
                    else{
                        AppLogger.log("error in Power fuel Consumable material details data")
                        holder.ConsumableTableList.adapter=PowerConsumableTableAdapter(baseFragment.requireContext(),listener,ArrayList())
                    }
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }
            }
            is ViewHold5 -> {
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
                try {
                    if (datalist!=null && datalist?.PowerAndFuelEBConnectionTariffDetail!=null){
                        holder.tariffTable.adapter=PowerFuelTariffTableAdapter(baseFragment.requireContext(),listener,datalist?.PowerAndFuelEBConnectionTariffDetail)
                    }
                    else{
                        AppLogger.log("error in Power fuel Consumable material details data")
                        holder.tariffTable.adapter=PowerFuelTariffTableAdapter(baseFragment.requireContext(),listener,ArrayList())
                    }
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }
            }
            is ViewHold6 -> {
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
                if (datalist!=null && datalist?.PowerAndFuelEBConnectionPayment!=null){
                    holder.authorityPaymentTable.adapter=PowerFuelPaymentTableAdapter(baseFragment.requireContext(),listener,datalist?.PowerAndFuelEBConnectionPayment)
                }
                else{
                    AppLogger.log("error in Power fuel Consumable material details data")
                    holder.authorityPaymentTable.adapter=PowerFuelPaymentTableAdapter(baseFragment.requireContext(),listener,ArrayList())
                }

            }
            is ViewHold7 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE

                    holder.binding.root.findViewById<View>(R.id.attach_card).setOnClickListener {
                        if (datalist!=null){
                            listener.addAttachment()
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
                try {
                    if (datalist!=null){
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),datalist?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
                            override fun itemClicked() {
                                listener.attachmentItemClicked()
                            }
                        })
                    }
                    else
                        AppLogger.log("Attachments Error")
                }catch (e:java.lang.Exception){
                    AppLogger.log("Acq Survey error : ${e.localizedMessage}")
                }
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



    interface PowerConnectionListListener {
       fun attachmentItemClicked()
       fun addAttachment()
       fun updateData(updatedData:PowerConnectionAllData)
       fun viewPoClicked(position: Int,data:PowerFuelPODetail)
       fun editPoClicked(position: Int,data:PowerFuelPODetail)
       fun viewConsumableClicked(position: Int,data:PowerConsumableMaterial)
       fun editConsumableClicked(position: Int,data:PowerConsumableMaterial)
       fun editTarrifClicked(position: Int,data:PowerFuelTariffDetails)
       fun viewAuthorityPaymentClicked(position: Int,data:PowerFuelAuthorityPayments)
       fun editAuthorityPaymentClicked(position: Int,data:PowerFuelAuthorityPayments)
    }

}
