package com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter

import android.content.Context
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
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerConsumableTableAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerFuelPaymentTableAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerFuelPoTableAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerFuelTariffTableAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class PowerConnecFragAdapter(var context: Context, var listener: PowerConnectionListListener,data:NewPowerFuelAllData?) : RecyclerView.Adapter<PowerConnecFragAdapter.ViewHold>() {
    private var datalist: PowerConnectionAllData?=null
    private var PowerConnData:PowerConnectionDetail?=null
    private var insAccepData:PowerInstallationAndAcceptence?=null

    fun setData(data: PowerConnectionAllData?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.PowerAndFuelEBConnection.isNotEmpty()){
                datalist=data.PowerAndFuelEBConnection.get(0)
            }
        }catch (e:java.lang.Exception){
            Toast.makeText(context,"TowerInfoFrag error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
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
                addTableItem("dfsdh")
            }
        }
        private fun addTableItem(item:String){
            if (poTableList.adapter!=null && poTableList.adapter is PowerFuelPoTableAdapter){
                val adapter = poTableList.adapter as PowerFuelPoTableAdapter
                adapter.addItem(item)
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
                addTableItem("gsfbgksf")
            }
        }
        private fun addTableItem(item:String){
            if (ConsumableTableList.adapter!=null && ConsumableTableList.adapter is PowerConsumableTableAdapter){
                var adapter = ConsumableTableList.adapter as PowerConsumableTableAdapter
                adapter.addItem(item)
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
                addTableItem("gsfbgksf")
            }
        }
        private fun addTableItem(item:String){
            if (tariffTable.adapter!=null && tariffTable.adapter is PowerFuelTariffTableAdapter){
                val adapter = tariffTable.adapter as PowerFuelTariffTableAdapter
                adapter.addItem(item)
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
                addTableItem("gsfbgksf")
            }
        }
        private fun addTableItem(item:String){
            if (authorityPaymentTable.adapter!=null && authorityPaymentTable.adapter is PowerFuelPaymentTableAdapter){
                var adapter = authorityPaymentTable.adapter as PowerFuelPaymentTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold7(itemView: View,listener: PowerConnectionListListener) : ViewHold(itemView) {
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
                    if (datalist!=null && datalist?.PowerAndFuelEBConnectionEBDetail?.isNotEmpty()==true){
                        PowerConnData=datalist?.PowerAndFuelEBConnectionEBDetail?.get(0)
                        if (PowerConnData?.PowerConnectionType?.isNotEmpty() == true)
                            AppPreferences.getInstance().setDropDown(holder.binding.PowerConnectionType,DropDowns.PowerConnectionType.name,PowerConnData?.PowerConnectionType?.get(0).toString())
                        holder.binding.powerType.text=PowerConnData?.PowerType.toString()
                        holder.binding.MeterType.text=PowerConnData?.MeterType.toString()
                        holder.binding.PowerSupplier.text=PowerConnData?.ElectricitySupplier
                        holder.binding.ConsumerNo.text=PowerConnData?.ConsumerNumber
                        holder.binding.ConnectedLoad.text=PowerConnData?.ConnectedLoad
                        holder.binding.AvgAvailibillity.text=PowerConnData?.AverageAvailibility
                        holder.binding.PowerConnRating.text=PowerConnData?.PowerRating
                        holder.binding.MeterLocationMark.text=PowerConnData?.MeterLocationMark
                        holder.binding.MeterSerialNumber.text=PowerConnData?.MeterSerialNumber
                        holder.binding.VoltageRatingMin.text=PowerConnData?.VoltageMin
                        holder.binding.VoltageRatingMax.text=PowerConnData?.VoltageMax
                        holder.binding.remarks.text=PowerConnData?.Remark

                    }
                    else
                        AppLogger.log("error in Power Connection details data")
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
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
                    if (datalist!=null && datalist?.PowerAndFuelEBConnectionEBDetail?.isNotEmpty()==true){
                        insAccepData=datalist?.InstallationAndAcceptence?.get(0)

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
                        holder.binding.installationDate.text=Utils.getFormatedDate(insAccepData?.InstallationDate!!.substring(0,10),"dd-MMM-yyyy")
                        holder.binding.acceptenceDate.text=Utils.getFormatedDate(insAccepData?.AcceptanceDate!!.substring(0,10),"dd-MMM-yyyy")

                    }
                    else
                        AppLogger.log("error in Power fuel installation details data")

                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
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
                    if (datalist!=null)
                        holder.poTableList.adapter=PowerFuelPoTableAdapter(context,listener,datalist?.PODetail)
                    else
                        AppLogger.log("error in Power fuel Po details data")
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
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
                        AppLogger.log("Tower Consumable Table Data : ====>${Gson().toJson(datalist?.ConsumableMaterial)}")
                        holder.ConsumableTableList.adapter=PowerConsumableTableAdapter(context,listener,datalist?.ConsumableMaterial)
                    }
                    else
                        AppLogger.log("error in Power fuel Consumable material details data")
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
                    if (datalist!=null){
                        holder.tariffTable.adapter=PowerFuelTariffTableAdapter(context,listener,datalist?.PowerAndFuelEBConnectionTariffDetail)
                    }
                    else
                        AppLogger.log("error in Power fuel Consumable material details data")
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
                        holder.authorityPaymentTable.adapter=PowerFuelPaymentTableAdapter(context,listener,datalist?.PowerAndFuelEBConnectionPayment)
                    }
                    else
                        AppLogger.log("error in Power fuel Consumable material details data")
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"ToewerInfoadapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
                }
            }
            is ViewHold7 -> {
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



    interface PowerConnectionListListener {
       fun attachmentItemClicked()
       fun viewPoClicked(position: Int,data:PowerFuelPODetail)
       fun viewConsumableClicked(position: Int,data:PowerConsumableMaterial)
       fun viewAuthorityPaymentClicked(position: Int,data:PowerFuelAuthorityPayments)
    }

}
