package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.pole.adapters

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
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class PoleInfoFragAdapter (var baseFragment: BaseFragment, var listener: PoleInfoListListener, poleData: TowerAndCivilInfraPole?) : RecyclerView.Adapter<PoleInfoFragAdapter.ViewHold>() {
    private var datalist: TowerAndCivilInfraPole?=null
    private var poleInfoData: TwrCivilInfraPoleDetail?=null
    private var insAccepData: TwrInstallationAndAcceptence?=null
    fun setData(data: TowerAndCivilInfraPole?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        datalist=poleData
    }


    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Pole Details"
    var type2 = "Installation & Acceptance"
    var type3 = "PO Details"
    var type4 = "Consumable Material"
    var type5 = "Preventive Maintenance"
    var type6 = "Attachments"
    init {
        list.add("Pole Details")
        list.add("Installation & Acceptance")
        list.add("PO Details")
        list.add("Consumable Material")
        list.add("Preventive Maintenance")
        list.add("Attachments")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : TowerListItemBinding = TowerListItemBinding.bind(itemView)

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
        var poTableList: RecyclerView =binding.towerPoTableItem

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
            if (poTableList.adapter!=null && poTableList.adapter is PolePoTableAdapter){
                val adapter = poTableList.adapter as PolePoTableAdapter
                adapter.addItem()
            }
        }
    }
    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding: TowerConsumableItemBinding = TowerConsumableItemBinding.bind(itemView)
        var towerConsumableTableList : RecyclerView = binding.towerConsumableTableItem
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
            if (towerConsumableTableList.adapter!=null && towerConsumableTableList.adapter is PoleconsumableTableAdapter){
                val adapter = towerConsumableTableList.adapter as PoleconsumableTableAdapter
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
            if (towerPreMaintenenceTableList.adapter!=null && towerPreMaintenenceTableList.adapter is PoleMaintenenceTableAdapter){
                val adapter = towerPreMaintenenceTableList.adapter as PoleMaintenenceTableAdapter
                adapter.addItem()
            }
        }
    }
    class ViewHold6(itemView: View, listener: PoleInfoListListener) : ViewHold(itemView) {
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
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_list_item, parent, false)
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
                return ViewHold6(view,listener)
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
                if (datalist!=null && datalist?.TowerAndCivilInfraPolePoleDetail!=null &&
                    datalist?.TowerAndCivilInfraPolePoleDetail?.isNotEmpty()==true){
                    poleInfoData=datalist?.TowerAndCivilInfraPolePoleDetail?.get(0)
                }
                if (poleInfoData!=null){
                    // view mode
                    holder.binding.TowerId.text=poleInfoData?.AGId
                    holder.binding.Height.text=poleInfoData?.Height
                    holder.binding.AntennaSlots.text=poleInfoData?.AntennaSlot.toString()
                    holder.binding.LegCount.text=poleInfoData?.Count.toString()
                    holder.binding.Weight.text=poleInfoData?.Weight.toString()
                    holder.binding.FoundationSizeL.text=poleInfoData?.FoundationSizeL
                    holder.binding.FoundationSizeB.text=poleInfoData?.FoundationSizeB
                    holder.binding.FoundationSizeH.text=poleInfoData?.FoundationSizeH
                    holder.binding.OffsetPoleCount.text=poleInfoData?.OffsetPoleCount.toString()
                    holder.binding.offsetPoleLenth.text=poleInfoData?.OffsetPoleLength
                    holder.binding.LocationMark.text=poleInfoData?.LocationMark
                    holder.binding.Remarks.text=poleInfoData?.remark
                    holder.binding.DesignedLoad.text=poleInfoData?.DesignedLoad
                    AppPreferences.getInstance().setDropDown(holder.binding.InstalledType,
                        DropDowns.InstalledType.name,poleInfoData?.InstalledType.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.CamouflageEdit,
                        DropDowns.Camouflage.name,poleInfoData?.Camouflage.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.LightningArrester,
                        DropDowns.LightningArrester.name,poleInfoData?.LightningArrester.toString())



                    // edit mode
                    holder.binding.HeightEdit.setText(poleInfoData?.Height)
                    holder.binding.AntennaSlotsEdit.setText(poleInfoData?.AntennaSlot.toString())
                    holder.binding.LegCountEdit.setText(poleInfoData?.Count.toString())
                    holder.binding.WeightEdit.setText(poleInfoData?.Weight.toString())
                    holder.binding.FoundationSizeLEdit.setText(poleInfoData?.FoundationSizeL)
                    holder.binding.FoundationSizeBEdit.setText(poleInfoData?.FoundationSizeB)
                    holder.binding.FoundationSizeHEdit.setText(poleInfoData?.FoundationSizeH)
                    holder.binding.OffsetPoleCountEdit.setText(poleInfoData?.OffsetPoleCount.toString())
                    holder.binding.OffsetPoleLengthEdit.setText(poleInfoData?.OffsetPoleLength)
                    holder.binding.LocationMarkEdit.setText(poleInfoData?.LocationMark)
                    holder.binding.remarksEdit.setText(poleInfoData?.remark)
                    holder.binding.DesignedLoadEdit.setText(poleInfoData?.DesignedLoad)
                    AppPreferences.getInstance().setDropDown(holder.binding.InstalledTypeEdit,
                        DropDowns.InstalledType.name,poleInfoData?.InstalledType.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.CamouflageEdit,
                        DropDowns.Camouflage.name,poleInfoData?.Camouflage.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.LightningArresterEdit,
                        DropDowns.LightningArrester.name,poleInfoData?.LightningArrester.toString())

                }
                if (poleInfoData!=null && poleInfoData?.TowerPoleType!=null && poleInfoData?.TowerPoleType?.isNotEmpty() == true){
                    AppPreferences.getInstance().setDropDown(holder.binding.TowerType,DropDowns.TowerPoleType.name,poleInfoData?.TowerPoleType?.get(0).toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.TowerTypeEdit,DropDowns.TowerPoleType.name,poleInfoData?.TowerPoleType?.get(0).toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.TowerTypeEdit,DropDowns.TowerPoleType.name)
                if (poleInfoData!=null && poleInfoData?.FoundationType!=null && poleInfoData?.FoundationType?.isNotEmpty() == true){
                    AppPreferences.getInstance().setDropDown(holder.binding.FoundationType,DropDowns.FoundationType.name,poleInfoData?.FoundationType?.get(0).toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.FoundationTypeEdit,DropDowns.FoundationType.name,poleInfoData?.FoundationType?.get(0).toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.FoundationTypeEdit,DropDowns.FoundationType.name)
                if (poleInfoData!=null && poleInfoData?.InstalledType!=null && poleInfoData?.InstalledType!! > 0){
                    AppPreferences.getInstance().setDropDown(holder.binding.InstalledType,DropDowns.InstalledType.name,poleInfoData?.InstalledType.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.InstalledTypeEdit,DropDowns.InstalledType.name,poleInfoData?.InstalledType.toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.InstalledTypeEdit,DropDowns.InstalledType.name)
                if (poleInfoData!=null && poleInfoData?.Camouflage!=null && poleInfoData?.Camouflage!! > 0){
                    AppPreferences.getInstance().setDropDown(holder.binding.Camouflage,DropDowns.Camouflage.name,poleInfoData?.Camouflage.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.CamouflageEdit,DropDowns.Camouflage.name,poleInfoData?.Camouflage.toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.CamouflageEdit,DropDowns.Camouflage.name)
                if (poleInfoData!=null && poleInfoData?.LightningArrester!=null && poleInfoData?.LightningArrester!! > 0){
                    AppPreferences.getInstance().setDropDown(holder.binding.LightningArrester,DropDowns.LightningArrester.name,poleInfoData?.LightningArrester.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.LightningArresterEdit,DropDowns.LightningArrester.name,poleInfoData?.LightningArrester.toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.LightningArresterEdit,DropDowns.LightningArrester.name)

                holder.binding.update.setOnClickListener {
                    val tempPoleDetailData= TwrCivilInfraPoleDetail()
                    val tempPoleAllData= TowerAndCivilInfraPole()
                    tempPoleDetailData.let {
                        it.Height=holder.binding.HeightEdit.text.toString()
                        it.Count=holder.binding.LegCountEdit.text.toString().toIntOrNull()
                        it.Weight=holder.binding.WeightEdit.text.toString()
                        it.DesignedLoad=holder.binding.DesignedLoadEdit.text.toString()
                        it.AntennaSlot=holder.binding.AntennaSlotsEdit.text.toString().toIntOrNull()
                        it.FoundationSizeL=holder.binding.FoundationSizeLEdit.text.toString()
                        it.FoundationSizeB=holder.binding.FoundationSizeBEdit.text.toString()
                        it.FoundationSizeH=holder.binding.FoundationSizeHEdit.text.toString()
                        it.OffsetPoleCount=holder.binding.OffsetPoleCountEdit.text.toString().toIntOrNull()
                        it.OffsetPoleLength=holder.binding.OffsetPoleLengthEdit.text.toString()
                        it.LocationMark=holder.binding.LocationMarkEdit.text.toString()
                        it.remark=holder.binding.remarksEdit.text.toString()
                        it.InstalledType=holder.binding.InstalledTypeEdit.selectedValue.id.toIntOrNull()
                        it.Camouflage=holder.binding.CamouflageEdit.selectedValue.id.toIntOrNull()
                        it.LightningArrester=holder.binding.LightningArresterEdit.selectedValue.id.toIntOrNull()
                        it.TowerPoleType= arrayListOf(holder.binding.TowerTypeEdit.selectedValue.id.toInt())
                        it.FoundationType= arrayListOf(holder.binding.FoundationTypeEdit.selectedValue.id.toInt())
                        if (poleInfoData!=null)
                            it.id=poleInfoData?.id
                    }
                    tempPoleAllData.TowerAndCivilInfraPolePoleDetail= arrayListOf(tempPoleDetailData)
                    if (datalist!=null)
                        tempPoleAllData.id=datalist?.id
                    listener.updatePoleData(tempPoleAllData)
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
                    val tempPoleAllData= TowerAndCivilInfraPole()
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
                    tempPoleAllData.InstallationAndAcceptence= arrayListOf(tempInsData)
                    if (datalist!=null)
                        tempPoleAllData.id=datalist?.id
                    listener.updatePoleData(tempPoleAllData)
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
                if (datalist!=null && datalist?.ConsumableMaterial!=null)
                    holder.poTableList.adapter= PolePoTableAdapter(baseFragment.requireContext(),listener,datalist?.PODetail)
                else
                    holder.poTableList.adapter= PolePoTableAdapter(baseFragment.requireContext(),listener,datalist?.PODetail)
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
                if (datalist!=null && datalist?.ConsumableMaterial!=null)
                    holder.towerConsumableTableList.adapter= PoleconsumableTableAdapter(baseFragment.requireContext(),listener,datalist?.ConsumableMaterial)
                else
                    holder.towerConsumableTableList.adapter= PoleconsumableTableAdapter(baseFragment.requireContext(),listener,ArrayList())

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
                if (datalist!=null && datalist?.PreventiveMaintenance!=null)
                    holder.towerPreMaintenenceTableList.adapter=PoleMaintenenceTableAdapter(baseFragment.requireContext(),listener,datalist?.PreventiveMaintenance)
                else
                    holder.towerPreMaintenenceTableList.adapter=PoleMaintenenceTableAdapter(baseFragment.requireContext(),listener,ArrayList())
            }
            is ViewHold6 -> {
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



    interface PoleInfoListListener {
        fun attachmentItemClicked()
        fun addAttachment()
        fun EditInstallationAcceptence()
        fun updatePoleData(data:TowerAndCivilInfraPole)
        fun editPoClicked(data:TwrCivilPODetail)
        fun viewPoClicked(data:TwrCivilPODetail)
        fun editMaintenenceClicked(data:PreventiveMaintenance)
        fun viewMaintenenceClicked(data:PreventiveMaintenance)
        fun editConsumableClicked(data:TwrCivilConsumableMaterial)
        fun viewConsumableClicked(data:TwrCivilConsumableMaterial)
    }

}
