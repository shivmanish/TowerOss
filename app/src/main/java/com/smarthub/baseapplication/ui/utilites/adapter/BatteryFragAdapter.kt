package com.smarthub.baseapplication.ui.utilites.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraTowerModel
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerModelTowerInfo
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerModelTowerInstallationAndAcceptance
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.BatteryBank
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.UtilitieSmp
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters.*
import com.smarthub.baseapplication.ui.utilites.tableAdapters.*
import com.smarthub.baseapplication.utils.AppLogger

class BatteryFragAdapter(var context: Context, var listener: BatterryBankListListener, BatteryData:BatteryBank?) : RecyclerView.Adapter<BatteryFragAdapter.ViewHold>() {
    private var datalist: BatteryBank?=null
    fun setData(data: BatteryBank?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            datalist=BatteryData
        }catch (e:java.lang.Exception){
            Toast.makeText(context,"TowerInfoFrag error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }


    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Equipments"
    var type2 = "Battery Module"
    var type3 = "Installation & Acceptance"
    var type4 = "Consumable Materials"
    var type5= "PO Details"
    var type6="Service Details"
    var type7="Attachments"
    init {
        list.add("Equipments")
        list.add("Battery Module")
        list.add("Installation & Acceptance")
        list.add("Consumable Materials")
        list.add("PO Details")
        list.add("Service Details")
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
    class BatteryViewHold(itemView: View) : ViewHold(itemView) {
        var binding : UtilityBatteryModuleBinding = UtilityBatteryModuleBinding.bind(itemView)
        val BatteryModuleTableList:RecyclerView=binding.batteryModeleTable
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
            if (BatteryModuleTableList.adapter!=null && BatteryModuleTableList.adapter is BatteryModuleTableAdapter){
                var adapter = BatteryModuleTableList.adapter as BatteryModuleTableAdapter
                adapter.addItem(item)
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
                addTableItem("dfsdh")
            }
        }
        private fun addTableItem(item:String){
            if (ConsumTableList.adapter!=null && ConsumTableList.adapter is BatteryConsumeTableAdapter){
                var adapter = ConsumTableList.adapter as BatteryConsumeTableAdapter
                adapter.addItem(item)
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
                addTableItem("dfsdh")
            }
        }
        private fun addTableItem(item:String){
            if (poTableList.adapter!=null && poTableList.adapter is BatteryPoTableAdapter){
                var adapter = poTableList.adapter as BatteryPoTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ServiceDetailsViewHold(itemView: View) : ViewHold(itemView) {
        var binding: UtilitySmpsServiceDetailsBinding = UtilitySmpsServiceDetailsBinding.bind(itemView)
        var serviceTableList: RecyclerView=binding.smpsServiceTable

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
            if (serviceTableList.adapter!=null && serviceTableList.adapter is BatteryServiceTableAdapter){
                var adapter = serviceTableList.adapter as BatteryServiceTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class Attachments(itemView: View,listener: BatterryBankListListener) : ViewHold(itemView) {
        var binding: TowerAttachmentInfoBinding = TowerAttachmentInfoBinding.bind(itemView)
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
        else if (list[position] is String && list[position]==type6)
            return 6
        else if (list[position] is String && list[position]==type7)
            return 7
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
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_battery_module, parent, false)
                return BatteryViewHold(view)
            }

            3-> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_installation_accep, parent, false)
                return InstAccepViewHold(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_connsum_material, parent, false)
                return ConsumMaterilViewHold(view)
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_po_details, parent, false)
                return PoDetailsViewHold(view)
            }

            7 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_service_details, parent, false)
                return ServiceDetailsViewHold(view)
            }
            7 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_attachment_info, parent, false)
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

                    holder.binding.imgEdit.setOnClickListener {
                        listener.EditEquipmentItem()
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

                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"ToewerInfoadapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
                }

            }
            is BatteryViewHold -> {
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
                    holder.BatteryModuleTableList.adapter=BatteryModuleTableAdapter(context,listener)
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"ToewerInfoadapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
                }
            }
            is InstAccepViewHold -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE

                    holder.binding.imgEdit.setOnClickListener {
                        listener.EditInstallationAcceptence()
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

                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"ToewerInfoadapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
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
                try {
                 holder.ConsumTableList.adapter=BatteryConsumeTableAdapter(context,listener)
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"ToewerInfoadapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
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
                try {
                    holder.poTableList.adapter=BatteryPoTableAdapter(context,listener)
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"ToewerInfoadapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
                }
            }
            is ServiceDetailsViewHold -> {
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
                    holder.serviceTableList.adapter=BatteryServiceTableAdapter(context,listener)
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"ToewerInfoadapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
                }
            }
            is Attachments -> {
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



    interface BatterryBankListListener {
        fun attachmentItemClicked()
        fun EditInstallationAcceptence()
        fun EditEquipmentItem()
        fun editPoClicked(position:Int)
        fun viewPoClicked(position:Int)
        fun editBatteryTableItem(position: Int)
        fun viewBatteryTableItem(position: Int)
        fun editConsumMaterialTableItem(position: Int)
        fun viewConsumMaterialTableItem(position: Int)
        fun editServiceTableItem(position: Int)
        fun viewServiceTableItem(position: Int)
    }

}
