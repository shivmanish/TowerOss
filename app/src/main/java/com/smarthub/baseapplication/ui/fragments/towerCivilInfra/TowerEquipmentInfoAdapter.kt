package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters.EquipmentConsumableTableAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters.EquipmentPoTableAdapter
import com.smarthub.baseapplication.utils.AppLogger

class TowerEquipmentInfoAdapter(var context: Context, var listner: TowerPoleListListener,equipData: TowerAndCivilInfraEquipmentModel?): RecyclerView.Adapter<TowerEquipmentInfoAdapter.ViewHold>() {
    private var datalist: TowerAndCivilInfraEquipmentModel?=null
    private var equipInfoData: EquipmentModelEquipmentInfo?=null
    private var insAccepData: EquipmentModelInstallationAndAcceptance?=null
    fun setData(data: TowerAndCivilInfraEquipmentModel?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            datalist=equipData
            equipInfoData=datalist?.TowerAndCivilInfraEquipment?.get(0)
            insAccepData=datalist?.TowerAndCivilInfraTowerInstallationAndAcceptance?.get(0)
        }catch (e:java.lang.Exception){
            Toast.makeText(context,"TowerInfoFrag error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }
    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Equipment Room"
    var type2 = "Installation & Acceptence"
    var type3 = "PO"
    var type4 = "Consumables"
    var type5 = "Attachment"
    init {
        list.add("Equipment Room")
        list.add("Installation & Acceptence")
        list.add("PO")
        list.add("Consumables")
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
        var binding : EquipmentInstallationAcceptenceBinding = EquipmentInstallationAcceptenceBinding.bind(itemView)

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
        var binding: EquipmentPoItemBinding = EquipmentPoItemBinding.bind(itemView)
        var poTableList: RecyclerView=binding.root.findViewById(R.id.po_tables)
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
            if (poTableList.adapter!=null && poTableList.adapter is EquipmentPoTableAdapter){
                var adapter = poTableList.adapter as EquipmentPoTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding: EquipmentConsumableItemBinding =
            EquipmentConsumableItemBinding.bind(itemView)

        var EquipmentConsumableTableList : RecyclerView = binding.root.findViewById(R.id.equipment_consumable_table)
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
            if (EquipmentConsumableTableList.adapter!=null && EquipmentConsumableTableList.adapter is EquipmentConsumableTableAdapter){
                var adapter = EquipmentConsumableTableList.adapter as EquipmentConsumableTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold5(itemView: View,listener: TowerPoleListListener) :ViewHold(itemView) {
        var binding: EquipmentAttachmentBinding = EquipmentAttachmentBinding.bind(itemView)

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



            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position]==type1)
            return 1
        else if (list[position] is String && list[position]==type2)
            return 2
        else if (list[position] is String && list[position]==type3)
            return 3
        else if (list[position] is String && list[position]==type4)
            return 4
        else if (list[position] is String && list[position]==type5)
            return 5
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.equipment_room_info_list, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.equipment_installation_acceptence, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.equipment_po_item, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.equipment_consumable_item, parent, false)
                return ViewHold4(view)
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.equipment_attachment, parent, false)
                return ViewHold5(view,listner)
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
                        listner.EditEquipmentRoomItem()
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
                    holder.binding.ShelterRoom.text=""
                    holder.binding.ShelterSize.text=
                        "${equipInfoData?.shelterL}X${equipInfoData?.shelterB}X${equipInfoData?.shelterH}"
                    holder.binding.FoundationSize.text=
                        "${equipInfoData?.foundationL}X${equipInfoData?.foundationB}X${equipInfoData?.foundationH}"
                    holder.binding.Foundation.text=""
                    holder.binding.LocationMark.text=equipInfoData?.locationmark
                }catch (e:java.lang.Exception){
                    AppLogger.log("Twrcivil equip adapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"Twrcivil equip adapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
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
                        listner.EditInstallationAcceptence()
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
                    holder.binding.InstallationVendor.text=insAccepData?.InstallationVendor
                    holder.binding.InstallationDate.text=insAccepData?.InstallationDate
                    holder.binding.InstallationExcutiveName.text=""
                    holder.binding.VendorPhonNo.text=insAccepData?.VendorPhoneNumber
                    holder.binding.InstallationVendor.text=insAccepData?.InstallationVendor
                    holder.binding.AcceptanceStatus.text=insAccepData?.AcceptanceStatus
                    holder.binding.ConditionalAcceptenceDate.text=insAccepData?.ConditionalAcceptanceDate
                    holder.binding.FinalAcceptenceDate.text=insAccepData?.FinalAcceptanceDate
                    holder.binding.OperationalStatus.text=insAccepData?.OperationalStatus
                    holder.binding.NextPmDate.text=insAccepData?.NextPMDate
                }catch (e:java.lang.Exception){
                    AppLogger.log("Twrcivil equip adapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"Twrcivil equip adapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
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
                    holder.poTableList.adapter=EquipmentPoTableAdapter(context,listner,datalist?.AuthorityPODetails)
                }catch (e:java.lang.Exception){
                    AppLogger.log("Twrcivil equip adapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"Twrcivil equip adapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
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
                    holder.EquipmentConsumableTableList.adapter=EquipmentConsumableTableAdapter(context,listner,datalist?.TowerAndCivilInfraConsumable)
                }catch (e:java.lang.Exception){
                    AppLogger.log("Twrcivil equip adapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"Twrcivil equip adapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
                }
            }
            is ViewHold5 -> {
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

    interface TowerPoleListListener {
        fun attachmentItemClicked()
        fun EditInstallationAcceptence()
        fun EditEquipmentRoomItem()
        fun editPoClicked(position:Int)
        fun viewPoClicked(position:Int)
        fun editConsumableClicked(position:Int)
        fun viewConsumableClicked(position:Int)
    }
}