package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.*
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters.*
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class PoleInfoFragAdapter (var context: Context, var listener: PoleInfoListListener, poleData: TowerAndCivilInfraPole?) : RecyclerView.Adapter<PoleInfoFragAdapter.ViewHold>() {
    private var datalist: TowerAndCivilInfraPole?=null
    private var poleInfoData: TwrCivilInfraPoleDetail?=null
    private var insAccepData: TwrInstallationAndAcceptence?=null
    fun setData(data: TowerAndCivilInfraPole?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            datalist=poleData
            poleInfoData=datalist?.TowerAndCivilInfraPolePoleDetail?.get(0)
            insAccepData=datalist?.InstallationAndAcceptence?.get(0)
        }catch (e:java.lang.Exception){
            Toast.makeText(context,"TowerInfoFrag error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }


    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Pole"
    var type2 = "Installation & Acceptence"
    var type3 = "PO"
    var type4 = "Consumables"
    var type6 = "Attachment"
    var type5 = "Preventive Maintenance"
    init {
        list.add("Pole")
        list.add("Installation & Acceptence")
        list.add("PO")
        list.add("Consumables")
        list.add("Preventive Maintenance")
        list.add("Attachment")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View) : ViewHold(itemView) {
//        var binding : TwrPoleInfoItemBinding = TwrPoleInfoItemBinding.bind(itemView)
        var binding : TowerListItemBinding = TowerListItemBinding.bind(itemView)
//        var offsetTableList: RecyclerView =binding.root.findViewById(R.id.pole_ofset_table)

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
                addTableItem("dfsdh")
            }
        }
        private fun addTableItem(item:String){
            if (poTableList.adapter!=null && poTableList.adapter is PolePoTableAdapter){
                var adapter = poTableList.adapter as PolePoTableAdapter
                adapter.addItem(item)
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
                addTableItem("gsfbgksf")
            }
        }
        private fun addTableItem(item:String){
            if (towerConsumableTableList.adapter!=null && towerConsumableTableList.adapter is PoleconsumableTableAdapter){
                val adapter = towerConsumableTableList.adapter as PoleconsumableTableAdapter
                adapter.addItem(item)
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
                addTableItem("gsfbgksf")
            }
        }
        private fun addTableItem(item:String){
            if (towerPreMaintenenceTableList.adapter!=null && towerPreMaintenenceTableList.adapter is PoleMaintenenceTableAdapter){
                var adapter = towerPreMaintenenceTableList.adapter as PoleMaintenenceTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold6(itemView: View, listener: PoleInfoListListener) : ViewHold(itemView) {
        var binding: TwrPoleAttachmentsBinding = TwrPoleAttachmentsBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
            override fun itemClicked() {
                listener.attachmentItemClicked()
            }
        })

        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

            val recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
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
                view = LayoutInflater.from(parent.context).inflate(R.layout.twr_pole_attachments, parent, false)
                return ViewHold6(view,listener)
            }

        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE

                    holder.binding.imgEdit.setOnClickListener {
//                        listener.EditTowerItem()
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
                try {
                    holder.binding.TowerId.text=position.toString()
                    if (poleInfoData?.TowerPoleType?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.TowerType,
                            DropDowns.TowerPoleType.name,poleInfoData?.TowerPoleType?.get(0).toString())
                    if (poleInfoData?.FoundationType?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.FoundationType,
                            DropDowns.FoundationType.name,poleInfoData?.FoundationType?.get(0).toString())
                    holder.binding.InstalledType.text=poleInfoData?.InstalledType.toString()
                    holder.binding.Height.text=poleInfoData?.Height
                    holder.binding.Camouflage.text=poleInfoData?.Camouflage.toString()
                    holder.binding.AntennaSlots.text=poleInfoData?.AntennaSlot.toString()
                    holder.binding.LegCount.text=poleInfoData?.Count.toString()
                    holder.binding.Weight.text=poleInfoData?.Weight.toString()
                    holder.binding.LightningArrester.text=poleInfoData?.LightningArrester.toString()
                    holder.binding.FoundationSize.text=
                        "${poleInfoData?.FoundationSizeL}X${poleInfoData?.FoundationSizeB}X${poleInfoData?.FoundationSizeH}"
                    holder.binding.OffsetPoleCount.text=poleInfoData?.OffsetPoleCount.toString()
                    holder.binding.offsetPoleLenth.text=poleInfoData?.OffsetPoleLength
                    holder.binding.LocationMark.text=poleInfoData?.LocationMark
                    holder.binding.Remarks.text=poleInfoData?.Remark
                    holder.binding.DesignedLoad.text=poleInfoData?.DesignedLoad
//                    holder.offsetTableList.adapter= poleOffsetTableAdapter(context,listener)

                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"ToewerInfoadapter error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }

            }
            is ViewHold2 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE

                    holder.binding.imgEdit.setOnClickListener {
//                        listener.EditInstallationAcceptence()
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
                try {
                    if (insAccepData?.VendorCompany?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.vendorName,DropDowns.VendorCompany.name,insAccepData?.VendorCompany?.get(0).toString())
                    if (insAccepData?.AcceptanceStatus?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.acceptenceStatus,DropDowns.AcceptanceStatus.name,insAccepData?.AcceptanceStatus?.get(0).toString())
                    holder.binding.installationDate.text=insAccepData?.InstallationDate
                    holder.binding.vendorExecutiveName.text=insAccepData?.VendorExecutiveName
                    holder.binding.vendorExecutiveNo.text=insAccepData?.VendorExecutiveNumber
                    holder.binding.vendorExecutiveEmailId.text=insAccepData?.VendorEmailId
                    holder.binding.Remarks.text=insAccepData?.Remark
                    holder.binding.vendorCode.text=insAccepData?.VendorCode
                    holder.binding.installationDate.text=
                        Utils.getFormatedDate(insAccepData?.InstallationDate!!.substring(0,10),"dd-MMM-yyyy")
                    holder.binding.acceptenceDate.text=
                        Utils.getFormatedDate(insAccepData?.AcceptanceDate!!.substring(0,10),"dd-MMM-yyyy")

//
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"ToewerInfoadapter error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
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
                try {
                    holder.poTableList.adapter= PolePoTableAdapter(context,listener,datalist?.PODetail)
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"ToewerInfoadapter error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
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
                    holder.towerConsumableTableList.adapter= PoleconsumableTableAdapter(context,listener,datalist?.ConsumableMaterial)
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"ToewerInfoadapter error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
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
                    holder.towerPreMaintenenceTableList.adapter=PoleMaintenenceTableAdapter(context,listener,datalist?.PreventiveMaintenance)
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"ToewerInfoadapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
                }
            }
            is ViewHold6 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
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
        fun EditInstallationAcceptence()
        fun EditTowerItem()
        fun editPoClicked(position:Int)
        fun viewPoClicked(position:Int,data:TwrCivilPODetail)
        fun viewMaintenenceClicked(position:Int,data:PreventiveMaintenance)
        fun editConsumableClicked(position:Int)
        fun viewConsumableClicked(position:Int,data:TwrCivilConsumableMaterial)
        fun editOffsetClicked(position:Int)
        fun viewOffsetClicked(position:Int)
    }

}
