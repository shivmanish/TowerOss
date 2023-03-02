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
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters.EarthingConsumabletableAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters.EarthingDetailsTableAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters.EarthingPoTableAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class EarthingInfoFragmentAdapter(var context: Context,var listner: TowerEarthingListListener,earthingData: TowerAndCivilInfraEarthing?): RecyclerView.Adapter<EarthingInfoFragmentAdapter.ViewHold>() {
    private var datalist: TowerAndCivilInfraEarthing?=null
    private var earthingInfoData: TwrCivilInfraEarthingDetail?=null
    private var insAccepData: TwrInstallationAndAcceptence?=null
    fun setData(data: TowerAndCivilInfraEarthing?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            datalist=earthingData
            earthingInfoData=datalist?.TowerAndCivilInfraEarthingEarthingDetail?.get(0)
            insAccepData=datalist?.InstallationAndAcceptence?.get(0)
        }catch (e:java.lang.Exception){
            Toast.makeText(context,"TowerInfoFrag error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }
    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Earthing Detail"
    var type2 = "Installation & Acceptence"
    var type3 = "PO Details"
    var type4 = "Consumable Materials"
    var type5 = "Preventive Maintenance"
    var type6 = "Attachment"
    init {
        list.add("Earthing Detail")
        list.add("Installation & Acceptence")
        list.add("PO Details")
        list.add("Consumable Materials")
        list.add("Preventive Maintenance")
        list.add("Attachment")
    }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : TowerEartrhingItemBinding = TowerEartrhingItemBinding.bind(itemView)
        val earthindTable:RecyclerView = binding.towerEarthingTableItem

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
                addTableItem("sggreg")
            }
        }
        private fun addTableItem(item:String){
            if (earthindTable.adapter!=null && earthindTable.adapter is EarthingDetailsTableAdapter){
                val adapter = earthindTable.adapter as EarthingDetailsTableAdapter
                adapter.addItem(item)
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
        var EarthingPoTable : RecyclerView = binding.towerPoTableItem
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
            if (EarthingPoTable.adapter!=null && EarthingPoTable.adapter is EarthingPoTableAdapter){
                val adapter = EarthingPoTable.adapter as EarthingPoTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding: TowerConsumableItemBinding = TowerConsumableItemBinding.bind(itemView)
        var ConsumableTableList : RecyclerView = binding.towerConsumableTableItem

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
            if (ConsumableTableList.adapter!=null && ConsumableTableList.adapter is EarthingConsumabletableAdapter){
                val adapter = ConsumableTableList.adapter as EarthingConsumabletableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold5(itemView: View,listener: TowerEarthingListListener) :
        ViewHold(itemView) {
        var binding: EarthingAttachmentsBinding = EarthingAttachmentsBinding.bind(itemView)

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
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_eartrhing_item, parent, false)
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
                view = LayoutInflater.from(parent.context).inflate(R.layout.earthing_attachments, parent, false)
                return ViewHold5(view, listner)
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
                    holder.earthindTable.adapter=EarthingDetailsTableAdapter(context,listner,datalist?.TowerAndCivilInfraEarthingEarthingDetail)
                }catch (e:java.lang.Exception){
                    AppLogger.log("Twrcivil earth adapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"Twrcivil earth adapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
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
                    if (insAccepData?.VendorCompany?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.vendorName,
                            DropDowns.VendorCompany.name,insAccepData?.VendorCompany?.get(0).toString())
                    if (insAccepData?.AcceptanceStatus?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.acceptenceStatus,
                            DropDowns.AcceptanceStatus.name,insAccepData?.AcceptanceStatus?.get(0).toString())
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

                }catch (e:java.lang.Exception){
                    AppLogger.log("Twrcivil earth adapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"Twrcivil earth adapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
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
                    holder.EarthingPoTable.adapter = EarthingPoTableAdapter( context,listner,datalist?.PODetail)
                }catch (e:java.lang.Exception){
                    AppLogger.log("Twrcivil earth adapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"Twrcivil earth adapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
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
                    holder.ConsumableTableList.adapter=EarthingConsumabletableAdapter(context,listner,datalist?.ConsumableMaterial)
                }catch (e:java.lang.Exception){
                    AppLogger.log("Twrcivil earth adapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"Twrcivil earth adapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
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

    interface TowerEarthingListListener {
        fun attachmentItemClicked()
        fun EditInstallationAcceptence()
        fun EditEarthingItem()
        fun editPoClicked(position:Int)
        fun viewPoClicked(position:Int,data:TwrCivilPODetail)
        fun editConsumableClicked(position:Int)
        fun viewConsumableClicked(position:Int,data: TwrCivilConsumableMaterial)
        fun viewEarthingDetails(position:Int,data:TwrCivilInfraEarthingDetail)

    }
}