package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tower.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

class TowerInfoListAdapter(var baseFragment: BaseFragment, var listener: TowerInfoListListener, towerData: TowerAndCivilInfraTower?) : RecyclerView.Adapter<TowerInfoListAdapter.ViewHold>() {
    private var datalist: TowerAndCivilInfraTower?=null
    private var towerInfoData:TwrCivilInfraTowerDetail?=null
    private var insAccepData:TwrInstallationAndAcceptence?=null
    private var excavationData:TowerExcavation?=null
    private var pccData:TowerPcc?=null
    private var barbendingData:TowerBarBending?=null
    private var raftCastingData:TowerRaftShaft?=null
    private var cubeTestingData:TowerCubeTesting?=null
    private var cast1Data:TowerLiftCast1?=null
    private var cast2Data:TowerLiftCast2?=null
    private var templetFixingData:TowerTemplateFixing?=null
    private var towerEarthingData:TowerTowerEarthing?=null
    private var eractionData:TowerErection?=null
    fun setData(data: TowerAndCivilInfraTower?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        datalist=towerData
    }


    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Tower Details"
    var type2 = "Installation & Acceptance"
    var type3 = "PO Details"
    var type4 = "Consumable Materials"
    var type5 = "Preventive Maintenance"
    var type6 = "Attachments"
    var type7 = "Excavation"
    var type8 = "PCC"
    var type9 = "Bar-Bending"
    var type10 = "Raft Casting"
    var type11 = "Cube Testing"
    var type12 = "1st Lift Cast"
    var type13 = "2nd Lift Cast"
    var type14 = "Template Fixing"
    var type15 = "Tower Earthing"
    var type16 = "Erection"

    init {
        list.add("Tower Details")
//        list.add("Installation & Acceptance")
//        list.add("PO Details")
        list.add("Consumable Materials")
        list.add("Excavation")
        list.add("PCC")
        list.add("Bar-Bending")
        list.add("Raft Casting")
        list.add("Cube Testing")
        list.add("1st Lift Cast")
        list.add("2nd Lift Cast")
        list.add("Template Fixing")
        list.add("Tower Earthing")
        list.add("Erection")
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
            if (poTableList.adapter!=null && poTableList.adapter is TowerPoTableAdapter){
                val adapter = poTableList.adapter as TowerPoTableAdapter
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
            if (towerConsumableTableList.adapter!=null && towerConsumableTableList.adapter is TowerConsumableTableAdapter){
                val adapter = towerConsumableTableList.adapter as TowerConsumableTableAdapter
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
            if (towerPreMaintenenceTableList.adapter!=null && towerPreMaintenenceTableList.adapter is PreveMaintenenceTableAdapter){
                val adapter = towerPreMaintenenceTableList.adapter as PreveMaintenenceTableAdapter
                adapter.addItem()
            }
        }
    }
    class ViewHold6(itemView: View,listener: TowerInfoListListener) : ViewHold(itemView) {
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
    class ViewHold7(itemView: View) : ViewHold(itemView) {
        var binding: TowerCivilDetailsCommonLayoutBinding = TowerCivilDetailsCommonLayoutBinding.bind(itemView)
        val recyclerListener:RecyclerView = binding.root.findViewById(R.id.list_item)

        init {
            binding.root.findViewById<TextView>(R.id.Attachment_Titel).visibility=View.GONE
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
        else if (list[position]==type7)
            return 7
        else if (list[position]==type8)
            return 8
        else if (list[position]==type9)
            return 9
        else if (list[position]==type10)
            return 10
        else if (list[position]==type11)
            return 11
        else if (list[position]==type12)
            return 12
        else if (list[position]==type13)
            return 13
        else if (list[position]==type14)
            return 14
        else if (list[position]==type15)
            return 15
        else if (list[position]==type16)
            return 16
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
            7,8,9,10,11,12,13,14,15,16 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_civil_details_common_layout, parent, false)
                return ViewHold7(view)
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
                if (datalist!=null && datalist?.TowerAndCivilInfraTowerTowerDetail!=null &&
                    datalist?.TowerAndCivilInfraTowerTowerDetail?.isNotEmpty()==true){
                    towerInfoData=datalist?.TowerAndCivilInfraTowerTowerDetail?.get(0)
                }
                if (towerInfoData!=null){
                    // view mode
                    holder.binding.TowerId.text=towerInfoData?.AGId
                    holder.binding.Height.text=towerInfoData?.Height
                    holder.binding.AntennaSlots.text=towerInfoData?.AntennaSlot.toString()
                    holder.binding.LegCount.text=towerInfoData?.Count.toString()
                    holder.binding.Weight.text=towerInfoData?.Weight.toString()
                    holder.binding.FoundationSizeL.text=towerInfoData?.FoundationSizeL
                    holder.binding.FoundationSizeB.text=towerInfoData?.FoundationSizeB
                    holder.binding.FoundationSizeH.text=towerInfoData?.FoundationSizeH
                    holder.binding.OffsetPoleCount.text=towerInfoData?.OffsetPoleCount.toString()
                    holder.binding.offsetPoleLenth.text=towerInfoData?.OffsetPoleLength
                    holder.binding.LocationMark.text=towerInfoData?.LocationMark
                    holder.binding.Remarks.text=towerInfoData?.remark
                    holder.binding.DesignedLoad.text=towerInfoData?.DesignedLoad
                    AppPreferences.getInstance().setDropDown(holder.binding.InstalledType,
                        DropDowns.InstalledType.name,towerInfoData?.InstalledType.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.Camouflage,
                        DropDowns.Camouflage.name,towerInfoData?.Camouflage.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.LightningArrester,
                        DropDowns.LightningArrester.name,towerInfoData?.LightningArrester.toString())

                    // edit mode
                    holder.binding.HeightEdit.setText(towerInfoData?.Height)
                    holder.binding.AntennaSlotsEdit.setText(towerInfoData?.AntennaSlot.toString())
                    holder.binding.LegCountEdit.setText(towerInfoData?.Count.toString())
                    holder.binding.WeightEdit.setText(towerInfoData?.Weight.toString())
                    holder.binding.FoundationSizeLEdit.setText(towerInfoData?.FoundationSizeL)
                    holder.binding.FoundationSizeBEdit.setText(towerInfoData?.FoundationSizeB)
                    holder.binding.FoundationSizeHEdit.setText(towerInfoData?.FoundationSizeH)
                    holder.binding.OffsetPoleCountEdit.setText(towerInfoData?.OffsetPoleCount.toString())
                    holder.binding.OffsetPoleLengthEdit.setText(towerInfoData?.OffsetPoleLength)
                    holder.binding.LocationMarkEdit.setText(towerInfoData?.LocationMark)
                    holder.binding.remarksEdit.setText(towerInfoData?.remark)
                    holder.binding.DesignedLoadEdit.setText(towerInfoData?.DesignedLoad)
                    AppPreferences.getInstance().setDropDown(holder.binding.InstalledTypeEdit,
                        DropDowns.InstalledType.name,towerInfoData?.InstalledType.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.CamouflageEdit,
                        DropDowns.Camouflage.name,towerInfoData?.Camouflage.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.LightningArresterEdit,
                        DropDowns.LightningArrester.name,towerInfoData?.LightningArrester.toString())

                }
                if (towerInfoData!=null && towerInfoData?.TowerPoleType!=null && towerInfoData?.TowerPoleType?.isNotEmpty() == true){
                    AppPreferences.getInstance().setDropDown(holder.binding.TowerType,DropDowns.TowerPoleType.name,towerInfoData?.TowerPoleType?.get(0).toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.TowerTypeEdit,DropDowns.TowerPoleType.name,towerInfoData?.TowerPoleType?.get(0).toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.TowerTypeEdit,DropDowns.TowerPoleType.name)
                if (towerInfoData!=null && towerInfoData?.FoundationType!=null && towerInfoData?.FoundationType?.isNotEmpty() == true){
                    AppPreferences.getInstance().setDropDown(holder.binding.FoundationType,DropDowns.FoundationType.name,towerInfoData?.FoundationType?.get(0).toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.FoundationTypeEdit,DropDowns.FoundationType.name,towerInfoData?.FoundationType?.get(0).toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.FoundationTypeEdit,DropDowns.FoundationType.name)
                if (towerInfoData!=null && towerInfoData?.InstalledType!=null && towerInfoData?.InstalledType!! > 0){
                    AppPreferences.getInstance().setDropDown(holder.binding.InstalledType,DropDowns.InstalledType.name,towerInfoData?.InstalledType.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.InstalledTypeEdit,DropDowns.InstalledType.name,towerInfoData?.InstalledType.toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.InstalledTypeEdit,DropDowns.InstalledType.name)
                if (towerInfoData!=null && towerInfoData?.Camouflage!=null && towerInfoData?.Camouflage!! > 0){
                    AppPreferences.getInstance().setDropDown(holder.binding.Camouflage,DropDowns.Camouflage.name,towerInfoData?.Camouflage.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.CamouflageEdit,DropDowns.Camouflage.name,towerInfoData?.Camouflage.toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.CamouflageEdit,DropDowns.Camouflage.name)
                if (towerInfoData!=null && towerInfoData?.LightningArrester!=null && towerInfoData?.LightningArrester!! > 0){
                    AppPreferences.getInstance().setDropDown(holder.binding.LightningArrester,DropDowns.LightningArrester.name,towerInfoData?.LightningArrester.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.LightningArresterEdit,DropDowns.LightningArrester.name,towerInfoData?.LightningArrester.toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.LightningArresterEdit,DropDowns.LightningArrester.name)

                holder.binding.update.setOnClickListener {
                    val tempTowerDetailData= TwrCivilInfraTowerDetail()
                    val tempTowerAllData= TowerAndCivilInfraTower()
                    tempTowerDetailData.let {
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
                        if (towerInfoData!=null)
                            it.id=towerInfoData?.id
                    }
                    tempTowerAllData.TowerAndCivilInfraTowerTowerDetail= arrayListOf(tempTowerDetailData)
                    if (datalist!=null)
                        tempTowerAllData.id=datalist?.id
                    listener.updateTowerData(tempTowerAllData)
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
                    val tempTowerAllData= TowerAndCivilInfraTower()
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
                    tempTowerAllData.InstallationAndAcceptence= arrayListOf(tempInsData)
                    if (datalist!=null)
                        tempTowerAllData.id=datalist?.id
                    listener.updateTowerData(tempTowerAllData)
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
                if (datalist!=null && datalist?.PODetail!=null)
                    holder.poTableList.adapter=
                        TowerPoTableAdapter(baseFragment.requireContext(),listener,datalist?.PODetail)
                else
                    holder.poTableList.adapter=
                        TowerPoTableAdapter(baseFragment.requireContext(),listener,
                        ArrayList())
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
                    holder.towerConsumableTableList.adapter=
                        TowerConsumableTableAdapter(baseFragment.requireContext(),listener,datalist?.ConsumableMaterial)
                else
                    holder.towerConsumableTableList.adapter=
                        TowerConsumableTableAdapter(baseFragment.requireContext(),listener,ArrayList())
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
                    holder.towerPreMaintenenceTableList.adapter=
                        PreveMaintenenceTableAdapter(baseFragment.requireContext(),listener,datalist?.PreventiveMaintenance)
                else
                    holder.towerPreMaintenenceTableList.adapter=
                        PreveMaintenenceTableAdapter(baseFragment.requireContext(),listener,ArrayList())
            }
            is ViewHold6 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.root.findViewById<View>(R.id.attach_card).setOnClickListener {
                        if (datalist!=null){
                            listener.addAttachment("TowerAndCivilInfraTower",datalist?.id.toString())
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
            is ViewHold7-> {
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
                holder.binding.StartDateEdit.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable?) {
                        var cmp:Int=0
                        if (holder.binding.ActualCompletionDateEdit.text.toString().isNotEmpty()){
                            cmp=Utils.dateDiffrence(holder.binding.StartDateEdit.text.toString(),holder.binding.ActualCompletionDateEdit.text.toString())
                        }
                        if(cmp<0){
                            AppLogger.log("Invalid End date")
                            holder.binding.TatEdit.text="0"
                        }
                        else{
                            holder.binding.TatEdit.text=String.format("%02d",cmp)
                        }
                    }
                })

                holder.binding.ActualCompletionDateEdit.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable?) {
                        var cmp:Int=0
                        if (holder.binding.StartDateEdit.text.toString().isEmpty()){
                            AppLogger.log("Please Select Started to calculate TAT")
                        }
                        else{
                            cmp=Utils.dateDiffrence(holder.binding.StartDateEdit.text.toString(),holder.binding.ActualCompletionDateEdit.text.toString())
                        }
                        if(cmp<0){
                            AppLogger.log("Invalid End date")
//                    Toast.makeText(context,"Invalid End date", Toast.LENGTH_SHORT).show()
                            holder.binding.TatEdit.text="0"
                        }
                        else{
                            holder.binding.TatEdit.text=String.format("%02d",cmp)
                        }
                    }

                })
                baseFragment.setDatePickerView(holder.binding.StartDateEdit)
                baseFragment.setDatePickerView(holder.binding.ActualCompletionDateEdit)
                if (list[position]==type7){
                    if (datalist!=null && datalist?.TowerAndCivilInfraTowerExcavation?.isNotEmpty()==true)
                        excavationData=datalist?.TowerAndCivilInfraTowerExcavation?.get(0)
                    if (excavationData!=null){
                        //view mode
                        holder.binding.StartDate.text=Utils.getFormatedDate(excavationData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDate.text=Utils.getFormatedDate(excavationData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.Tat.text=excavationData?.TAT?.toString()
                        holder.binding.VendorExecutiveName.text=excavationData?.VendorExecutiveName
                        holder.binding.VendorExecutiveEmail.text=excavationData?.VendorEmailId
                        holder.binding.VendorExecutiveNumber.text=excavationData?.VendorExecutiveNumber
                        holder.binding.Remarks.text=excavationData?.remark

                        // edit mode
                        holder.binding.StartDateEdit.text=Utils.getFormatedDate(excavationData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDateEdit.text=Utils.getFormatedDate(excavationData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.TatEdit.text=excavationData?.TAT?.toString()
                        holder.binding.VendorExecutiveNameEdit.setText(excavationData?.VendorExecutiveName)
                        holder.binding.VendorExecutiveEmailEdit.setText(excavationData?.VendorEmailId)
                        holder.binding.VendorExecutiveNumberEdit.setText(excavationData?.VendorExecutiveNumber)
                        holder.binding.remarksEdit.setText(excavationData?.remark)
                    }

                    try {
                    if (excavationData!=null){
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),excavationData?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
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
                if (list[position]==type8){
                    if (datalist!=null && datalist?.TowerAndCivilInfraTowerPcc?.isNotEmpty()==true)
                        pccData=datalist?.TowerAndCivilInfraTowerPcc?.get(0)
                    if (pccData!=null){
                        //view mode
                        holder.binding.StartDate.text=Utils.getFormatedDate(pccData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDate.text=Utils.getFormatedDate(pccData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.Tat.text=pccData?.TAT?.toString()
                        holder.binding.VendorExecutiveName.text=pccData?.VendorExecutiveName
                        holder.binding.VendorExecutiveEmail.text=pccData?.VendorEmailId
                        holder.binding.VendorExecutiveNumber.text=pccData?.VendorExecutiveNumber
                        holder.binding.Remarks.text=pccData?.remark

                        // edit mode
                        holder.binding.StartDateEdit.text=Utils.getFormatedDate(pccData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDateEdit.text=Utils.getFormatedDate(pccData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.TatEdit.text=pccData?.TAT?.toString()
                        holder.binding.VendorExecutiveNameEdit.setText(pccData?.VendorExecutiveName)
                        holder.binding.VendorExecutiveEmailEdit.setText(pccData?.VendorEmailId)
                        holder.binding.VendorExecutiveNumberEdit.setText(pccData?.VendorExecutiveNumber)
                        holder.binding.remarksEdit.setText(pccData?.remark)
                    }

                    try {
                    if (pccData!=null){
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),pccData?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
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
                if (list[position]==type9){
                    if (datalist!=null && datalist?.TowerAndCivilInfraTowerBarBending?.isNotEmpty()==true)
                        barbendingData=datalist?.TowerAndCivilInfraTowerBarBending?.get(0)
                    if (barbendingData!=null){
                        //view mode
                        holder.binding.StartDate.text=Utils.getFormatedDate(barbendingData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDate.text=Utils.getFormatedDate(barbendingData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.Tat.text=barbendingData?.TAT?.toString()
                        holder.binding.VendorExecutiveName.text=barbendingData?.VendorExecutiveName
                        holder.binding.VendorExecutiveEmail.text=barbendingData?.VendorEmailId
                        holder.binding.VendorExecutiveNumber.text=barbendingData?.VendorExecutiveNumber
                        holder.binding.Remarks.text=barbendingData?.remark

                        // edit mode
                        holder.binding.StartDateEdit.text=Utils.getFormatedDate(barbendingData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDateEdit.text=Utils.getFormatedDate(barbendingData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.TatEdit.text=barbendingData?.TAT?.toString()
                        holder.binding.VendorExecutiveNameEdit.setText(barbendingData?.VendorExecutiveName)
                        holder.binding.VendorExecutiveEmailEdit.setText(barbendingData?.VendorEmailId)
                        holder.binding.VendorExecutiveNumberEdit.setText(barbendingData?.VendorExecutiveNumber)
                        holder.binding.remarksEdit.setText(barbendingData?.remark)
                    }

                    try {
                    if (barbendingData!=null&& barbendingData?.attachment!=null){
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),barbendingData?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
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
                if (list[position]==type10){
                    if (datalist!=null && datalist?.TowerAndCivilInfraTowerRaftShaft?.isNotEmpty()==true)
                        raftCastingData=datalist?.TowerAndCivilInfraTowerRaftShaft?.get(0)
                    if (raftCastingData!=null){
                        //view mode
                        holder.binding.StartDate.text=Utils.getFormatedDate(raftCastingData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDate.text=Utils.getFormatedDate(raftCastingData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.Tat.text=raftCastingData?.TAT?.toString()
                        holder.binding.VendorExecutiveName.text=raftCastingData?.VendorExecutiveName
                        holder.binding.VendorExecutiveEmail.text=raftCastingData?.VendorEmailId
                        holder.binding.VendorExecutiveNumber.text=raftCastingData?.VendorExecutiveNumber
                        holder.binding.Remarks.text=raftCastingData?.remark

                        // edit mode
                        holder.binding.StartDateEdit.text=Utils.getFormatedDate(raftCastingData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDateEdit.text=Utils.getFormatedDate(raftCastingData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.TatEdit.text=raftCastingData?.TAT?.toString()
                        holder.binding.VendorExecutiveNameEdit.setText(raftCastingData?.VendorExecutiveName)
                        holder.binding.VendorExecutiveEmailEdit.setText(raftCastingData?.VendorEmailId)
                        holder.binding.VendorExecutiveNumberEdit.setText(raftCastingData?.VendorExecutiveNumber)
                        holder.binding.remarksEdit.setText(raftCastingData?.remark)
                    }

                    try {
                    if (raftCastingData!=null&& raftCastingData?.attachment!=null){
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),raftCastingData?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
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
                if (list[position]==type11){
                    if (datalist!=null && datalist?.TowerAndCivilInfraTowerCubeTesting?.isNotEmpty()==true)
                        cubeTestingData=datalist?.TowerAndCivilInfraTowerCubeTesting?.get(0)
                    if (cubeTestingData!=null){
                        //view mode
                        holder.binding.StartDate.text=Utils.getFormatedDate(cubeTestingData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDate.text=Utils.getFormatedDate(cubeTestingData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.Tat.text=cubeTestingData?.TAT?.toString()
                        holder.binding.VendorExecutiveName.text=cubeTestingData?.VendorExecutiveName
                        holder.binding.VendorExecutiveEmail.text=cubeTestingData?.VendorEmailId
                        holder.binding.VendorExecutiveNumber.text=cubeTestingData?.VendorExecutiveNumber
                        holder.binding.Remarks.text=cubeTestingData?.remark

                        // edit mode
                        holder.binding.StartDateEdit.text=Utils.getFormatedDate(cubeTestingData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDateEdit.text=Utils.getFormatedDate(cubeTestingData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.TatEdit.text=cubeTestingData?.TAT?.toString()
                        holder.binding.VendorExecutiveNameEdit.setText(cubeTestingData?.VendorExecutiveName)
                        holder.binding.VendorExecutiveEmailEdit.setText(cubeTestingData?.VendorEmailId)
                        holder.binding.VendorExecutiveNumberEdit.setText(cubeTestingData?.VendorExecutiveNumber)
                        holder.binding.remarksEdit.setText(cubeTestingData?.remark)
                    }

                    try {
                    if (cubeTestingData!=null&& cubeTestingData?.attachment!=null){
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),cubeTestingData?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
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
                if (list[position]==type12){
                    if (datalist!=null && datalist?.TowerAndCivilInfraTowerLiftCast1?.isNotEmpty()==true)
                        cast1Data=datalist?.TowerAndCivilInfraTowerLiftCast1?.get(0)
                    if (cast1Data!=null){
                        //view mode
                        holder.binding.StartDate.text=Utils.getFormatedDate(cast1Data?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDate.text=Utils.getFormatedDate(cast1Data?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.Tat.text=cast1Data?.TAT?.toString()
                        holder.binding.VendorExecutiveName.text=cast1Data?.VendorExecutiveName
                        holder.binding.VendorExecutiveEmail.text=cast1Data?.VendorEmailId
                        holder.binding.VendorExecutiveNumber.text=cast1Data?.VendorExecutiveNumber
                        holder.binding.Remarks.text=cast1Data?.remark

                        // edit mode
                        holder.binding.StartDateEdit.text=Utils.getFormatedDate(cast1Data?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDateEdit.text=Utils.getFormatedDate(cast1Data?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.TatEdit.text=cast1Data?.TAT?.toString()
                        holder.binding.VendorExecutiveNameEdit.setText(cast1Data?.VendorExecutiveName)
                        holder.binding.VendorExecutiveEmailEdit.setText(cast1Data?.VendorEmailId)
                        holder.binding.VendorExecutiveNumberEdit.setText(cast1Data?.VendorExecutiveNumber)
                        holder.binding.remarksEdit.setText(cast1Data?.remark)
                    }

                    try {
                    if (cast1Data!=null&& cast1Data?.attachment!=null){
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),cast1Data?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
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
                if (list[position]==type13){
                    if (datalist!=null && datalist?.TowerAndCivilInfraTowerLiftCast2?.isNotEmpty()==true)
                        cast2Data=datalist?.TowerAndCivilInfraTowerLiftCast2?.get(0)
                    if (cast2Data!=null){
                        //view mode
                        holder.binding.StartDate.text=Utils.getFormatedDate(cast2Data?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDate.text=Utils.getFormatedDate(cast2Data?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.Tat.text=cast2Data?.TAT?.toString()
                        holder.binding.VendorExecutiveName.text=cast2Data?.VendorExecutiveName
                        holder.binding.VendorExecutiveEmail.text=cast2Data?.VendorEmailId
                        holder.binding.VendorExecutiveNumber.text=cast2Data?.VendorExecutiveNumber
                        holder.binding.Remarks.text=cast2Data?.remark

                        // edit mode
                        holder.binding.StartDateEdit.text=Utils.getFormatedDate(cast2Data?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDateEdit.text=Utils.getFormatedDate(cast2Data?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.TatEdit.text=cast2Data?.TAT?.toString()
                        holder.binding.VendorExecutiveNameEdit.setText(cast2Data?.VendorExecutiveName)
                        holder.binding.VendorExecutiveEmailEdit.setText(cast2Data?.VendorEmailId)
                        holder.binding.VendorExecutiveNumberEdit.setText(cast2Data?.VendorExecutiveNumber)
                        holder.binding.remarksEdit.setText(cast2Data?.remark)
                    }

                    try {
                    if (cast2Data!=null&& cast2Data?.attachment!=null){
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),cast2Data?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
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
                if (list[position]==type14){
                    if (datalist!=null && datalist?.TowerAndCivilInfraTowerTemplateFixing?.isNotEmpty()==true)
                        templetFixingData=datalist?.TowerAndCivilInfraTowerTemplateFixing?.get(0)
                    if (templetFixingData!=null){
                        //view mode
                        holder.binding.StartDate.text=Utils.getFormatedDate(templetFixingData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDate.text=Utils.getFormatedDate(templetFixingData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.Tat.text=templetFixingData?.TAT?.toString()
                        holder.binding.VendorExecutiveName.text=templetFixingData?.VendorExecutiveName
                        holder.binding.VendorExecutiveEmail.text=templetFixingData?.VendorEmailId
                        holder.binding.VendorExecutiveNumber.text=templetFixingData?.VendorExecutiveNumber
                        holder.binding.Remarks.text=templetFixingData?.remark

                        // edit mode
                        holder.binding.StartDateEdit.text=Utils.getFormatedDate(templetFixingData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDateEdit.text=Utils.getFormatedDate(templetFixingData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.TatEdit.text=templetFixingData?.TAT?.toString()
                        holder.binding.VendorExecutiveNameEdit.setText(templetFixingData?.VendorExecutiveName)
                        holder.binding.VendorExecutiveEmailEdit.setText(templetFixingData?.VendorEmailId)
                        holder.binding.VendorExecutiveNumberEdit.setText(templetFixingData?.VendorExecutiveNumber)
                        holder.binding.remarksEdit.setText(templetFixingData?.remark)
                    }

                    try {
                    if (templetFixingData!=null&& templetFixingData?.attachment!=null){
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),templetFixingData?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
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
                if (list[position]==type15){
                    if (datalist!=null && datalist?.TowerAndCivilInfraTowerTowerEarthing?.isNotEmpty()==true)
                        towerEarthingData=datalist?.TowerAndCivilInfraTowerTowerEarthing?.get(0)
                    if (towerEarthingData!=null){
                        //view mode
                        holder.binding.StartDate.text=Utils.getFormatedDate(towerEarthingData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDate.text=Utils.getFormatedDate(towerEarthingData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.Tat.text=towerEarthingData?.TAT?.toString()
                        holder.binding.VendorExecutiveName.text=towerEarthingData?.VendorExecutiveName
                        holder.binding.VendorExecutiveEmail.text=towerEarthingData?.VendorEmailId
                        holder.binding.VendorExecutiveNumber.text=towerEarthingData?.VendorExecutiveNumber
                        holder.binding.Remarks.text=towerEarthingData?.remark

                        // edit mode
                        holder.binding.StartDateEdit.text=Utils.getFormatedDate(towerEarthingData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDateEdit.text=Utils.getFormatedDate(towerEarthingData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.TatEdit.text=towerEarthingData?.TAT?.toString()
                        holder.binding.VendorExecutiveNameEdit.setText(towerEarthingData?.VendorExecutiveName)
                        holder.binding.VendorExecutiveEmailEdit.setText(towerEarthingData?.VendorEmailId)
                        holder.binding.VendorExecutiveNumberEdit.setText(towerEarthingData?.VendorExecutiveNumber)
                        holder.binding.remarksEdit.setText(towerEarthingData?.remark)
                    }

                    try {
                    if (towerEarthingData!=null&& towerEarthingData?.attachment!=null){
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),towerEarthingData?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
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
                if (list[position]==type16){
                    if (datalist!=null && datalist?.TowerAndCivilInfraTowerErection?.isNotEmpty()==true)
                        eractionData=datalist?.TowerAndCivilInfraTowerErection?.get(0)
                    if (eractionData!=null){
                        //view mode
                        holder.binding.StartDate.text=Utils.getFormatedDate(eractionData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDate.text=Utils.getFormatedDate(eractionData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.Tat.text=eractionData?.TAT?.toString()
                        holder.binding.VendorExecutiveName.text=eractionData?.VendorExecutiveName
                        holder.binding.VendorExecutiveEmail.text=eractionData?.VendorEmailId
                        holder.binding.VendorExecutiveNumber.text=eractionData?.VendorExecutiveNumber
                        holder.binding.Remarks.text=eractionData?.remark

                        // edit mode
                        holder.binding.StartDateEdit.text=Utils.getFormatedDate(eractionData?.StartDAte,"dd-MMM-yyyy")
                        holder.binding.ActualCompletionDateEdit.text=Utils.getFormatedDate(eractionData?.ActualCompletionDate,"dd-MMM-yyyy")
                        holder.binding.TatEdit.text=eractionData?.TAT?.toString()
                        holder.binding.VendorExecutiveNameEdit.setText(eractionData?.VendorExecutiveName)
                        holder.binding.VendorExecutiveEmailEdit.setText(eractionData?.VendorEmailId)
                        holder.binding.VendorExecutiveNumberEdit.setText(eractionData?.VendorExecutiveNumber)
                        holder.binding.remarksEdit.setText(eractionData?.remark)
                    }

                    try {
                    if (eractionData!=null&& eractionData?.attachment!=null){
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),eractionData?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
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
                holder.binding.update.setOnClickListener {
                    val tempTowerAllData= TowerAndCivilInfraTower()
                    if (list[position]==type7){
                        val tempData=TowerExcavation()
                        tempData.let {
                            it.StartDAte=Utils.getFullFormatedDate(holder.binding.StartDateEdit.text.toString())
                            it.ActualCompletionDate=Utils.getFullFormatedDate(holder.binding.ActualCompletionDateEdit.text.toString())
                            it.TAT=holder.binding.TatEdit.text.toString().toIntOrNull()
                            it.VendorEmailId=holder.binding.VendorExecutiveEmailEdit.text.toString()
                            it.VendorExecutiveName=holder.binding.VendorExecutiveNameEdit.text.toString()
                            it.VendorExecutiveNumber=holder.binding.VendorExecutiveNumberEdit.text.toString()
                            it.remark=holder.binding.remarksEdit.text.toString()
                            if (excavationData!=null)
                                it.id=excavationData?.id
                        }
                        tempTowerAllData.TowerAndCivilInfraTowerExcavation= arrayListOf(tempData)
                        if (datalist!=null)
                            tempTowerAllData.id=datalist?.id
                        listener.updateTowerData(tempTowerAllData)
                    }
                    else if (list[position]==type8){
                        val tempData=TowerPcc()
                        tempData.let {
                            it.StartDAte=Utils.getFullFormatedDate(holder.binding.StartDateEdit.text.toString())
                            it.ActualCompletionDate=Utils.getFullFormatedDate(holder.binding.ActualCompletionDateEdit.text.toString())
                            it.TAT=holder.binding.TatEdit.text.toString().toIntOrNull()
                            it.VendorEmailId=holder.binding.VendorExecutiveEmailEdit.text.toString()
                            it.VendorExecutiveName=holder.binding.VendorExecutiveNameEdit.text.toString()
                            it.VendorExecutiveNumber=holder.binding.VendorExecutiveNumberEdit.text.toString()
                            it.remark=holder.binding.remarksEdit.text.toString()
                            if (pccData!=null)
                                it.id=pccData?.id
                        }
                        tempTowerAllData.TowerAndCivilInfraTowerPcc= arrayListOf(tempData)
                        if (datalist!=null)
                            tempTowerAllData.id=datalist?.id
                        listener.updateTowerData(tempTowerAllData)
                    }
                    else if (list[position]==type9){
                        val tempData=TowerBarBending()
                        tempData.let {
                            it.StartDAte=Utils.getFullFormatedDate(holder.binding.StartDateEdit.text.toString())
                            it.ActualCompletionDate=Utils.getFullFormatedDate(holder.binding.ActualCompletionDateEdit.text.toString())
                            it.TAT=holder.binding.TatEdit.text.toString().toIntOrNull()
                            it.VendorEmailId=holder.binding.VendorExecutiveEmailEdit.text.toString()
                            it.VendorExecutiveName=holder.binding.VendorExecutiveNameEdit.text.toString()
                            it.VendorExecutiveNumber=holder.binding.VendorExecutiveNumberEdit.text.toString()
                            it.remark=holder.binding.remarksEdit.text.toString()
                            if (barbendingData!=null)
                                it.id=barbendingData?.id
                        }
                        tempTowerAllData.TowerAndCivilInfraTowerBarBending= arrayListOf(tempData)
                        if (datalist!=null)
                            tempTowerAllData.id=datalist?.id
                        listener.updateTowerData(tempTowerAllData)
                    }
                    else if (list[position]==type10){
                        val tempData=TowerRaftShaft()
                        tempData.let {
                            it.StartDAte=Utils.getFullFormatedDate(holder.binding.StartDateEdit.text.toString())
                            it.ActualCompletionDate=Utils.getFullFormatedDate(holder.binding.ActualCompletionDateEdit.text.toString())
                            it.TAT=holder.binding.TatEdit.text.toString().toIntOrNull()
                            it.VendorEmailId=holder.binding.VendorExecutiveEmailEdit.text.toString()
                            it.VendorExecutiveName=holder.binding.VendorExecutiveNameEdit.text.toString()
                            it.VendorExecutiveNumber=holder.binding.VendorExecutiveNumberEdit.text.toString()
                            it.remark=holder.binding.remarksEdit.text.toString()
                            if (raftCastingData!=null)
                                it.id=raftCastingData?.id
                        }
                        tempTowerAllData.TowerAndCivilInfraTowerRaftShaft= arrayListOf(tempData)
                        if (datalist!=null)
                            tempTowerAllData.id=datalist?.id
                        listener.updateTowerData(tempTowerAllData)
                    }
                    else if (list[position]==type11){
                        val tempData=TowerCubeTesting()
                        tempData.let {
                            it.StartDAte=Utils.getFullFormatedDate(holder.binding.StartDateEdit.text.toString())
                            it.ActualCompletionDate=Utils.getFullFormatedDate(holder.binding.ActualCompletionDateEdit.text.toString())
                            it.TAT=holder.binding.TatEdit.text.toString().toIntOrNull()
                            it.VendorEmailId=holder.binding.VendorExecutiveEmailEdit.text.toString()
                            it.VendorExecutiveName=holder.binding.VendorExecutiveNameEdit.text.toString()
                            it.VendorExecutiveNumber=holder.binding.VendorExecutiveNumberEdit.text.toString()
                            it.remark=holder.binding.remarksEdit.text.toString()
                            if (cubeTestingData!=null)
                                it.id=cubeTestingData?.id
                        }
                        tempTowerAllData.TowerAndCivilInfraTowerCubeTesting= arrayListOf(tempData)
                        if (datalist!=null)
                            tempTowerAllData.id=datalist?.id
                        listener.updateTowerData(tempTowerAllData)
                    }
                    else if (list[position]==type12){
                        val tempData=TowerLiftCast1()
                        tempData.let {
                            it.StartDAte=Utils.getFullFormatedDate(holder.binding.StartDateEdit.text.toString())
                            it.ActualCompletionDate=Utils.getFullFormatedDate(holder.binding.ActualCompletionDateEdit.text.toString())
                            it.TAT=holder.binding.TatEdit.text.toString().toIntOrNull()
                            it.VendorEmailId=holder.binding.VendorExecutiveEmailEdit.text.toString()
                            it.VendorExecutiveName=holder.binding.VendorExecutiveNameEdit.text.toString()
                            it.VendorExecutiveNumber=holder.binding.VendorExecutiveNumberEdit.text.toString()
                            it.remark=holder.binding.remarksEdit.text.toString()
                            if (cast1Data!=null)
                                it.id=cast1Data?.id
                        }
                        tempTowerAllData.TowerAndCivilInfraTowerLiftCast1= arrayListOf(tempData)
                        if (datalist!=null)
                            tempTowerAllData.id=datalist?.id
                        listener.updateTowerData(tempTowerAllData)
                    }
                    else if (list[position]==type13){
                        val tempData=TowerLiftCast2()
                        tempData.let {
                            it.StartDAte=Utils.getFullFormatedDate(holder.binding.StartDateEdit.text.toString())
                            it.ActualCompletionDate=Utils.getFullFormatedDate(holder.binding.ActualCompletionDateEdit.text.toString())
                            it.TAT=holder.binding.TatEdit.text.toString().toIntOrNull()
                            it.VendorEmailId=holder.binding.VendorExecutiveEmailEdit.text.toString()
                            it.VendorExecutiveName=holder.binding.VendorExecutiveNameEdit.text.toString()
                            it.VendorExecutiveNumber=holder.binding.VendorExecutiveNumberEdit.text.toString()
                            it.remark=holder.binding.remarksEdit.text.toString()
                            if (cast2Data!=null)
                                it.id=cast2Data?.id
                        }
                        tempTowerAllData.TowerAndCivilInfraTowerLiftCast2= arrayListOf(tempData)
                        if (datalist!=null)
                            tempTowerAllData.id=datalist?.id
                        listener.updateTowerData(tempTowerAllData)
                    }
                    else if (list[position]==type14){
                        val tempData=TowerTemplateFixing()
                        tempData.let {
                            it.StartDAte=Utils.getFullFormatedDate(holder.binding.StartDateEdit.text.toString())
                            it.ActualCompletionDate=Utils.getFullFormatedDate(holder.binding.ActualCompletionDateEdit.text.toString())
                            it.TAT=holder.binding.TatEdit.text.toString().toIntOrNull()
                            it.VendorEmailId=holder.binding.VendorExecutiveEmailEdit.text.toString()
                            it.VendorExecutiveName=holder.binding.VendorExecutiveNameEdit.text.toString()
                            it.VendorExecutiveNumber=holder.binding.VendorExecutiveNumberEdit.text.toString()
                            it.remark=holder.binding.remarksEdit.text.toString()
                            if (templetFixingData!=null)
                                it.id=templetFixingData?.id
                        }
                        tempTowerAllData.TowerAndCivilInfraTowerTemplateFixing= arrayListOf(tempData)
                        if (datalist!=null)
                            tempTowerAllData.id=datalist?.id
                        listener.updateTowerData(tempTowerAllData)
                    }
                    else if (list[position]==type15){
                        val tempData=TowerTowerEarthing()
                        tempData.let {
                            it.StartDAte=Utils.getFullFormatedDate(holder.binding.StartDateEdit.text.toString())
                            it.ActualCompletionDate=Utils.getFullFormatedDate(holder.binding.ActualCompletionDateEdit.text.toString())
                            it.TAT=holder.binding.TatEdit.text.toString().toIntOrNull()
                            it.VendorEmailId=holder.binding.VendorExecutiveEmailEdit.text.toString()
                            it.VendorExecutiveName=holder.binding.VendorExecutiveNameEdit.text.toString()
                            it.VendorExecutiveNumber=holder.binding.VendorExecutiveNumberEdit.text.toString()
                            it.remark=holder.binding.remarksEdit.text.toString()
                            if (towerEarthingData!=null)
                                it.id=towerEarthingData?.id
                        }
                        tempTowerAllData.TowerAndCivilInfraTowerTowerEarthing= arrayListOf(tempData)
                        if (datalist!=null)
                            tempTowerAllData.id=datalist?.id
                        listener.updateTowerData(tempTowerAllData)
                    }
                    else if (list[position]==type16){
                        val tempData=TowerErection()
                        tempData.let {
                            it.StartDAte=Utils.getFullFormatedDate(holder.binding.StartDateEdit.text.toString())
                            it.ActualCompletionDate=Utils.getFullFormatedDate(holder.binding.ActualCompletionDateEdit.text.toString())
                            it.TAT=holder.binding.TatEdit.text.toString().toIntOrNull()
                            it.VendorEmailId=holder.binding.VendorExecutiveEmailEdit.text.toString()
                            it.VendorExecutiveName=holder.binding.VendorExecutiveNameEdit.text.toString()
                            it.VendorExecutiveNumber=holder.binding.VendorExecutiveNumberEdit.text.toString()
                            it.remark=holder.binding.remarksEdit.text.toString()
                            if (eractionData!=null)
                                it.id=eractionData?.id
                        }
                        tempTowerAllData.TowerAndCivilInfraTowerErection= arrayListOf(tempData)
                        if (datalist!=null)
                            tempTowerAllData.id=datalist?.id
                        listener.updateTowerData(tempTowerAllData)
                    }
                }
                holder.binding.root.findViewById<View>(R.id.attach_card).setOnClickListener {
                    if (list[position]==type7){
                        if (excavationData!=null){
                            listener.addAttachment("TowerAndCivilInfraTowerExcavation",excavationData?.id.toString())
                        }
                        else
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",Toast.LENGTH_SHORT).show()
                    }
                    else if (list[position]==type8){
                        if (pccData!=null){
                            listener.addAttachment("TowerAndCivilInfraTowerPcc",pccData?.id.toString())
                        }
                        else
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",Toast.LENGTH_SHORT).show()
                    }
                    else if (list[position]==type9){
                        if (barbendingData!=null){
                            listener.addAttachment("TowerAndCivilInfraTowerBarBending",barbendingData?.id.toString())
                        }
                        else
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",Toast.LENGTH_SHORT).show()
                    }
                    else if (list[position]==type10){
                        if (raftCastingData!=null){
                            listener.addAttachment("TowerAndCivilInfraTowerRaftShaft",raftCastingData?.id.toString())
                        }
                        else
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",Toast.LENGTH_SHORT).show()
                    }
                    else if (list[position]==type11){
                        if (cubeTestingData!=null){
                            listener.addAttachment("TowerAndCivilInfraTowerCubeTesting",cubeTestingData?.id.toString())
                        }
                        else
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",Toast.LENGTH_SHORT).show()
                    }
                    else if (list[position]==type12){
                        if (cast1Data!=null){
                            listener.addAttachment("TowerAndCivilInfraTowerLiftCast1",cast1Data?.id.toString())
                        }
                        else
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",Toast.LENGTH_SHORT).show()
                    }
                    else if (list[position]==type13){
                        if (cast2Data!=null){
                            listener.addAttachment("TowerAndCivilInfraTowerLiftCast2",cast2Data?.id.toString())
                        }
                        else
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",Toast.LENGTH_SHORT).show()
                    }
                    else if (list[position]==type14){
                        if (templetFixingData!=null){
                            listener.addAttachment("TowerAndCivilInfraTowerTemplateFixing",templetFixingData?.id.toString())
                        }
                        else
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",Toast.LENGTH_SHORT).show()
                    }
                    else if (list[position]==type15){
                        if (towerEarthingData!=null){
                            listener.addAttachment("TowerAndCivilInfraTowerTowerEarthing",towerEarthingData?.id.toString())
                        }
                        else
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",Toast.LENGTH_SHORT).show()
                    }
                    else if (list[position]==type16){
                        if (eractionData!=null){
                            listener.addAttachment("TowerAndCivilInfraTowerErection",eractionData?.id.toString())
                        }
                        else
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",Toast.LENGTH_SHORT).show()
                    }
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



    interface TowerInfoListListener {
       fun attachmentItemClicked()
       fun addAttachment(schemaName:String,schemaId:String)
        fun updateTowerData(updatedData:TowerAndCivilInfraTower)
        fun editPoClicked(data:TwrCivilPODetail)
        fun viewPoClicked(data:TwrCivilPODetail)
        fun editConsumableClicked(data:TwrCivilConsumableMaterial)
        fun viewConsumableClicked(data:TwrCivilConsumableMaterial)
        fun viewMaintenenceClicked(data:PreventiveMaintenance)
        fun editMaintenenceClicked(data:PreventiveMaintenance)
        fun editOffsetClicked(position:Int)
        fun viewOffsetClicked(position:Int)
    }

}
