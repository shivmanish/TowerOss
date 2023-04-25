package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.equipmentRoom.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tower.adapter.PreveMaintenenceTableAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class TowerEquipmentInfoAdapter(var baseFragment: BaseFragment, var listner: EquipmentItemListener, equipData: TowerAndCivilInfraEquipmentRoom?): RecyclerView.Adapter<TowerEquipmentInfoAdapter.ViewHold>() {
    private var datalist: TowerAndCivilInfraEquipmentRoom?=null
    private var equipInfoData: TwrCivilEquipmentRoomDetail?=null
    private var insAccepData: TwrInstallationAndAcceptence?=null
    fun setData(data: TowerAndCivilInfraEquipmentRoom?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
            datalist=equipData
    }
    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Equipment Room Details"
    var type2 = "Installation & Acceptance"
    var type3 = "PO Details"
    var type4 = "Consumable Materials"
    var type5 = "Preventive Maintenance"
    var type6 = "Attachment"
    init {
        list.add("Equipment Room Details")
        list.add("Installation & Acceptance")
        list.add("PO Details")
        list.add("Consumable Materials")
        list.add("Preventive Maintenance")
        list.add("Attachment")
    }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : EquipmentRoomInfoListBinding = EquipmentRoomInfoListBinding.bind(itemView)

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
        var binding : TowerInstallationAcceptenceBinding = TowerInstallationAcceptenceBinding.bind(itemView)

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
            if (poTableList.adapter!=null && poTableList.adapter is EquipmentPoTableAdapter){
                val adapter = poTableList.adapter as EquipmentPoTableAdapter
                adapter.addItem()
            }
        }
    }
    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding: TowerConsumableItemBinding = TowerConsumableItemBinding.bind(itemView)
        var EquipmentConsumableTableList : RecyclerView = binding.towerConsumableTableItem
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
            if (EquipmentConsumableTableList.adapter!=null && EquipmentConsumableTableList.adapter is EquipmentConsumableTableAdapter){
                val adapter = EquipmentConsumableTableList.adapter as EquipmentConsumableTableAdapter
                adapter.addItem()
            }
        }
    }
    class ViewHold5(itemView: View) : ViewHold(itemView) {
        var binding: TowerPreventiveMaintenenceItemsBinding = TowerPreventiveMaintenenceItemsBinding.bind(itemView)
        var towerPreMaintenenceTableList : RecyclerView = binding.preventiveMaintenenceTableItem
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
            if (towerPreMaintenenceTableList.adapter!=null && towerPreMaintenenceTableList.adapter is EquipMaintenenceTableAdapter){
                val adapter = towerPreMaintenenceTableList.adapter as EquipMaintenenceTableAdapter
                adapter.addItem()
            }
        }
    }
    class ViewHold6(itemView: View,listener: EquipmentItemListener) : ViewHold(itemView) {
        var binding: TowerCivilInfraAttachmentCommonBinding = TowerCivilInfraAttachmentCommonBinding.bind(itemView)
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
                view = LayoutInflater.from(parent.context).inflate(R.layout.equipment_room_info_list, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_installation_acceptence, parent, false)
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
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_preventive_maintenence_items, parent, false)
                return ViewHold5(view)
            }
            6 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_civil_infra_attachment_common, parent, false)
                return ViewHold6(view,listner)
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
                if (datalist!=null && datalist?.TowerAndCivilInfraEquipmentRoomEquipmentRoomDetail?.isNotEmpty()==true){
                    equipInfoData=datalist?.TowerAndCivilInfraEquipmentRoomEquipmentRoomDetail?.get(0)
                }
                if (equipInfoData!=null){
                    //view mode
                    holder.binding.SizeL.text=equipInfoData?.SizeL
                    holder.binding.SizeB.text=equipInfoData?.SizeB
                    holder.binding.SizeH.text=equipInfoData?.SizeH
                    holder.binding.FoundationSizeL.text=equipInfoData?.FoundationSizeL
                    holder.binding.FoundationSizeB.text=equipInfoData?.FoundationSizeB
                    holder.binding.FoundationSizeH.text=equipInfoData?.FoundationSizeH
                    holder.binding.MakeType.text=equipInfoData?.MakeType
                    holder.binding.MaterialUsed.text=equipInfoData?.MaterialUsed
                    holder.binding.LocationMark.text=equipInfoData?.LocationMark
                    holder.binding.Remarks.text=equipInfoData?.remark

                    //edit mode
                    holder.binding.SizeLEdit.setText(equipInfoData?.SizeL)
                    holder.binding.SizeBEdit.setText(equipInfoData?.SizeB)
                    holder.binding.SizeHEdit.setText(equipInfoData?.SizeH)
                    holder.binding.FoundationSizeLEdit.setText(equipInfoData?.FoundationSizeL)
                    holder.binding.FoundationSizeBEdit.setText(equipInfoData?.FoundationSizeB)
                    holder.binding.FoundationSizeHEdit.setText(equipInfoData?.FoundationSizeH)
                    holder.binding.MakeTypeEdit.setText(equipInfoData?.MakeType)
                    holder.binding.MaterialUsedEdit.setText(equipInfoData?.MaterialUsed)
                    holder.binding.LocationMarkEdit.setText(equipInfoData?.LocationMark)
                    holder.binding.remarksEdit.setText(equipInfoData?.remark)
                }
                if (equipInfoData!=null && equipInfoData?.FoundationType?.isNotEmpty()==true){
                    AppPreferences.getInstance().setDropDown(holder.binding.FoundationType,DropDowns.FoundationType.name,equipInfoData?.FoundationType?.get(0).toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.FoundationTypeEdit,DropDowns.FoundationType.name,equipInfoData?.FoundationType?.get(0).toString())
                }
                else
                {
                    AppPreferences.getInstance().setDropDown(holder.binding.FoundationTypeEdit,DropDowns.FoundationType.name)
                }

                if (equipInfoData!=null && equipInfoData?.Type!=null && equipInfoData?.Type!! > 0){
                    AppPreferences.getInstance().setDropDown(holder.binding.Type,DropDowns.EquipmentType.name,equipInfoData?.Type.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.TypeEdit,DropDowns.EquipmentType.name,equipInfoData?.Type.toString())
                }
                else
                {
                    AppPreferences.getInstance().setDropDown(holder.binding.TypeEdit,DropDowns.EquipmentType.name)
                }
                holder.binding.update.setOnClickListener {
                    val tempEquipRoomDetailData= TwrCivilEquipmentRoomDetail()
                    val tempEquipRoomAllData= TowerAndCivilInfraEquipmentRoom()
                    tempEquipRoomDetailData.let {
                        it.MakeType=holder.binding.MakeTypeEdit.text.toString()
                        it.MaterialUsed=holder.binding.MaterialUsedEdit.text.toString()
                        it.SizeL=holder.binding.SizeLEdit.text.toString()
                        it.SizeB=holder.binding.SizeBEdit.text.toString()
                        it.SizeH=holder.binding.SizeHEdit.text.toString()
                        it.FoundationSizeL=holder.binding.FoundationSizeLEdit.text.toString()
                        it.FoundationSizeB=holder.binding.FoundationSizeBEdit.text.toString()
                        it.FoundationSizeH=holder.binding.FoundationSizeHEdit.text.toString()
                        it.LocationMark=holder.binding.LocationMarkEdit.text.toString()
                        it.remark=holder.binding.remarksEdit.text.toString()
                        it.Type=holder.binding.TypeEdit.selectedValue.id.toIntOrNull()
                        it.FoundationType= arrayListOf(holder.binding.FoundationTypeEdit.selectedValue.id.toInt())
                        if (equipInfoData!=null)
                            it.id=equipInfoData?.id
                    }
                    tempEquipRoomAllData.TowerAndCivilInfraEquipmentRoomEquipmentRoomDetail= arrayListOf(tempEquipRoomDetailData)
                    if (datalist!=null)
                        tempEquipRoomAllData.id=datalist?.id
                    listner.updateEquipmentRoom(tempEquipRoomAllData)
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
                if (insAccepData!=null && insAccepData?.VendorCompany?.isNotEmpty()==true){
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit,DropDowns.VendorCompany.name,insAccepData?.VendorCompany?.get(0).toString(),holder.binding.VendorCodeEdit)
                    AppPreferences.getInstance().setDropDown(holder.binding.vendorName,DropDowns.VendorCompany.name,insAccepData?.VendorCompany?.get(0).toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit,DropDowns.VendorCompany.name,holder.binding.VendorCodeEdit)
                if (insAccepData!=null && insAccepData?.AcceptanceStatus?.isNotEmpty()==true){
                    AppPreferences.getInstance().setDropDown(holder.binding.acceptenceStatus,DropDowns.AcceptanceStatus.name,insAccepData?.AcceptanceStatus?.get(0).toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.AcceptenceStatusEdit,DropDowns.AcceptanceStatus.name,insAccepData?.AcceptanceStatus?.get(0).toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.AcceptenceStatusEdit,DropDowns.AcceptanceStatus.name,)

                holder.binding.update.setOnClickListener {
                    val tempInsData= TwrInstallationAndAcceptence()
                    val tempEquipRoomAllData= TowerAndCivilInfraEquipmentRoom()
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
                    tempEquipRoomAllData.InstallationAndAcceptence= arrayListOf(tempInsData)
                    if (datalist!=null)
                        tempEquipRoomAllData.id=datalist?.id
                    listner.updateEquipmentRoom(tempEquipRoomAllData)
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
                if (datalist!=null && datalist?.PODetail!=null){
                    holder.poTableList.adapter= EquipmentPoTableAdapter(baseFragment.requireContext(),listner,datalist?.PODetail)
                }
                else{
                    holder.poTableList.adapter= EquipmentPoTableAdapter(baseFragment.requireContext(),listner,ArrayList())
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
                if (datalist!=null && datalist?.ConsumableMaterial!=null){
                    holder.EquipmentConsumableTableList.adapter= EquipmentConsumableTableAdapter(baseFragment.requireContext(),listner,datalist?.ConsumableMaterial)
                }
                else{
                    holder.EquipmentConsumableTableList.adapter= EquipmentConsumableTableAdapter(baseFragment.requireContext(),listner,
                        ArrayList())
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
                if (datalist!=null && datalist?.PreventiveMaintenance!=null){
                        holder.towerPreMaintenenceTableList.adapter= EquipMaintenenceTableAdapter(baseFragment.requireContext(),listner,datalist?.PreventiveMaintenance)
                    }
                else{
                    holder.towerPreMaintenenceTableList.adapter= EquipMaintenenceTableAdapter(baseFragment.requireContext(),listner,
                        ArrayList())
                }
            }
            is ViewHold6 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.root.findViewById<View>(R.id.attach_card).setOnClickListener {
                        if (datalist!=null){
                            listner.addAttachment()
                        }
                        else
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",
                                Toast.LENGTH_SHORT).show()
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
                            override fun itemClicked(item : Attachments) {
                                listner.attachmentItemClicked()
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

    interface EquipmentItemListener {
        fun attachmentItemClicked()
        fun addAttachment()
        fun EditInstallationAcceptence()
        fun updateEquipmentRoom(data:TowerAndCivilInfraEquipmentRoom)
        fun editPoClicked(data:TwrCivilPODetail)
        fun viewPoClicked(data:TwrCivilPODetail)
        fun editConsumableClicked(data:TwrCivilConsumableMaterial)
        fun viewConsumableClicked(data:TwrCivilConsumableMaterial)
        fun viewMaintenenceClicked(data:PreventiveMaintenance)
        fun editMaintenenceClicked(data:PreventiveMaintenance)
    }
}