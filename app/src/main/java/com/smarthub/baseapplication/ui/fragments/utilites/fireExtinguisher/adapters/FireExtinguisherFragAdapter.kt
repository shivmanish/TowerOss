package com.smarthub.baseapplication.ui.fragments.utilites.fireExtinguisher.adapters

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
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class FireExtinguisherFragAdapter(var baseFragment: BaseFragment, var listener: FireExtListListener, FireExtData: UtilityEquipmentFireExtinguisher?) : RecyclerView.Adapter<FireExtinguisherFragAdapter.ViewHold>() {
    private var datalist: UtilityEquipmentFireExtinguisher?=null
    private var InsAccepData: UtiltyInstallationAcceptence?=null

    fun setData(data: UtilityEquipmentFireExtinguisher?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            datalist=FireExtData
        }catch (e:java.lang.Exception){
            Toast.makeText(baseFragment.requireContext(),"TowerInfoFrag error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }


    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Equipments"
    var type2 = "Installation & Acceptance"
    var type3= "PO Details"
    var type4="Preventive Maintenance"
    var type5="Attachments"
    init {
        list.add("Equipments")
        list.add("Installation & Acceptance")
        list.add("PO Details")
        list.add("Preventive Maintenance")
        list.add("Attachments")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    
    class EquipViewHold(itemView: View) : ViewHold(itemView) {
        var binding : UtilityFireExtEquipmentsBinding = UtilityFireExtEquipmentsBinding.bind(itemView)
        var equipTableList:RecyclerView=binding.fireExtEquipTable

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
            if (poTableList.adapter!=null && poTableList.adapter is FireExtPoTableAdapter){
                val adapter = poTableList.adapter as FireExtPoTableAdapter
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
            if (maintenanceTable.adapter!=null && maintenanceTable.adapter is FireExtMaintenanceTableAdapter){
                val adapter = maintenanceTable.adapter as FireExtMaintenanceTableAdapter
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
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_fire_ext_equipments, parent, false)
                return EquipViewHold(view)
            }

            2-> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_installation_accep, parent, false)
                return InstAccepViewHold(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_po_details, parent, false)
                return PoDetailsViewHold(view)
            }

            4-> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_maintenence, parent, false)
                return MaintenanceViewHold(view)
            }
            5-> {
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
                    holder.binding.imgAdd.visibility = View.VISIBLE

                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgAdd.visibility = View.GONE
                    AppPreferences.getInstance().saveInteger("UtilityFireEquipTableAdapter",currentOpened)
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                if (datalist!=null && datalist?.Equipment!=null)
                holder.binding.itemTitleStr.text = String.format("%s (%s)",list[position],datalist?.Equipment?.size)
                holder.binding.imgAdd.setOnClickListener {
                    listener.addEquipmentClicked()
                }
                if (datalist!=null && datalist?.Equipment!=null){
                    holder.equipTableList.adapter=UtilityFireEquipTableAdapter(baseFragment,datalist?.Equipment,
                        object :UtilityFireEquipTableAdapter.UtilityFireEquipListListener{
                            override fun updateUtilityFireEquipData(updatedData: UtilitySMPSEquipment) {
                                val utilityFireExtData= UtilityEquipmentFireExtinguisher()
                                utilityFireExtData.Equipment= arrayListOf(updatedData)
                                utilityFireExtData.id=datalist?.id
                                listener.updateFireExtData(utilityFireExtData)
                            }
                        }
                    )
                }
                else {
                    holder.equipTableList.adapter=UtilityFireEquipTableAdapter(baseFragment,
                        ArrayList(),
                        object :UtilityFireEquipTableAdapter.UtilityFireEquipListListener{
                            override fun updateUtilityFireEquipData(updatedData: UtilitySMPSEquipment) {

                            }
                        }
                    )
                }


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
                    holder.binding.IPVoltageLayout.visibility = View.GONE
                    holder.binding.IPCurrentLayout.visibility = View.GONE
                    holder.binding.IPVoltageLayoutEdit.visibility = View.GONE
                    holder.binding.IPCurrentLayoutEdit.visibility = View.GONE

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
                    holder.binding.remarks.text=InsAccepData?.Remark
                    holder.binding.nstallationDate.text=Utils.getFormatedDate(InsAccepData?.InstallationDate,"dd-MMM-yyyy")
                    holder.binding.AcceptenceDate.text=Utils.getFormatedDate(InsAccepData?.AcceptanceDate,"dd-MMM-yyyy")

                    // edit mode
                    holder.binding.VendorCodeEdit.setText(InsAccepData?.VendorCode)
                    holder.binding.VendorExcutiveNameEdit.setText(InsAccepData?.VendorExecutiveName)
                    holder.binding.VendorExecutiveEmailEdit.setText(InsAccepData?.VendorEmailId)
                    holder.binding.VendorExecutiveNumberEdit.setText(InsAccepData?.VendorExecutiveNumber)
                    holder.binding.remarksEdit.setText(InsAccepData?.Remark)
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
                        it.Remark=holder.binding.remarksEdit.text.toString()
                        it.InstallationDate=Utils.getFullFormatedDate(holder.binding.InstallationDateEdit.text.toString())
                        it.AcceptanceDate=Utils.getFullFormatedDate(holder.binding.AcceptenceDateEdit.text.toString())
                        it.VendorCompany= arrayListOf(holder.binding.VendorNameEdit.selectedValue.id.toInt())
                        it.AcceptanceStatus= arrayListOf(holder.binding.AcceptenceStatusEdit.selectedValue.id.toInt())

                        if (datalist!=null && datalist?.InstallationAndAcceptence?.isNotEmpty()==true)
                            it.id=datalist?.InstallationAndAcceptence?.get(0)?.id
                        val utilityFireExtData= UtilityEquipmentFireExtinguisher()
                        utilityFireExtData.InstallationAndAcceptence= arrayListOf(it)
                        if (datalist!=null)
                            utilityFireExtData.id=datalist?.id
                        listener.updateFireExtData(utilityFireExtData)
                    }
                }

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
                    holder.poTableList.adapter=FireExtPoTableAdapter(baseFragment.requireContext(),listener,datalist?.PODetail)
                else
                    holder.poTableList.adapter=FireExtPoTableAdapter(baseFragment.requireContext(),listener, ArrayList())
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
                    holder.maintenanceTable.adapter=FireExtMaintenanceTableAdapter(baseFragment.requireContext(),listener,datalist?.PreventiveMaintenance)
                else
                    holder.maintenanceTable.adapter=FireExtMaintenanceTableAdapter(baseFragment.requireContext(),listener,ArrayList())

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



    interface FireExtListListener {
        fun attachmentItemClicked()
        fun addEquipmentClicked()
        fun addAttachment(childIndex:Int?)
        fun editPoClicked(position:Int,data:UtilityPoDetails)
        fun viewPoClicked(position:Int,data:UtilityPoDetails)
        fun editMaintenamceTableItem(position: Int,data:UtilityPreventiveMaintenance)
        fun viewMaintenanceTableItem(position: Int,data:UtilityPreventiveMaintenance)
        fun updateFireExtData(updatedData: UtilityEquipmentFireExtinguisher)
    }

}
