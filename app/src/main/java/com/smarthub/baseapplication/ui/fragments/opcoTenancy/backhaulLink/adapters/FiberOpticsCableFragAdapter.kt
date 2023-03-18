package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FiberOpticsCableItemsBinding
import com.smarthub.baseapplication.databinding.FiberOpticsLinkInfoDetailsBinding
import com.smarthub.baseapplication.databinding.MwUbrLinkInfoDetailsBinding
import com.smarthub.baseapplication.databinding.PowerFuelBillsItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkFiberLinkInfo
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkFiberOpticCable
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkMWLinkInfo
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelBills
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class FiberOpticsCableFragAdapter(var listener: FiberOpticsCableClickListener, var context: Context, opticsCableData:ArrayList<BackhaulLinkFiberOpticCable>?) : RecyclerView.Adapter<FiberOpticsCableFragAdapter.ViewHold>() {

    var list = ArrayList<Any>()

    fun setData(data: ArrayList<BackhaulLinkFiberOpticCable>?) {
        this.list.clear()
        this.list.addAll(data!!)
        notifyDataSetChanged()
    }
//    fun addLoading(){
//        this.list.clear()
//        this.list.add("loading")
//        notifyDataSetChanged()
//    }
    init {
        this.list.clear()
        this.list.addAll(opticsCableData!!)
    }

    fun updateItem(pos : Int,data : BackhaulLinkFiberLinkInfo){
        list[pos] = data
        notifyItemChanged(pos)
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : FiberOpticsCableItemsBinding= FiberOpticsCableItemsBinding.bind(itemView)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            1 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.fiber_optics_cable_items,parent,false)
                ViewHold1(view)
            }
            2 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data,parent,false)
                ViewHold(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar,parent,false)
                ViewHold(view)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ViewHold1) {
            val data: BackhaulLinkFiberOpticCable=list[position] as BackhaulLinkFiberOpticCable
            holder.binding.imgEdit.setOnClickListener {
//                listener.editModeCliked(data,position)
            }
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if (currentOpened == position) {
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility = View.GONE
                holder.binding.itemCollapse.visibility = View.VISIBLE
                holder.binding.imgEdit.visibility = View.VISIBLE
            } else {
                holder.binding.collapsingLayout.tag = false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility = View.VISIBLE
                holder.binding.itemCollapse.visibility = View.GONE
                holder.binding.imgEdit.visibility = View.GONE
            }
            try {
                holder.binding.itemTitleStr.text = String.format(context.resources.getString(R.string.rf_antenna_title_str_formate),AppPreferences.getInstance().getDropDownValue(DropDowns.VendorCompany.name,data.VendorCompany.get(0).toString()),data.CableType,Utils.getFormatedDate(data.InstallationDate.substring(0,10),"ddMMMyyyy"))
                if(data.VendorCompany.isNotEmpty())
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorName, DropDowns.VendorCompany.name,data.VendorCompany.get(0).toString())
                if(data.LayingType.isNotEmpty())
                    AppPreferences.getInstance().setDropDown(holder.binding.LayingType, DropDowns.LayingType.name,data.LayingType.get(0).toString())
                if(data.AcceptanceStatus.isNotEmpty())
                    AppPreferences.getInstance().setDropDown(holder.binding.AcceptanceStatus, DropDowns.AcceptanceStatus.name,data.AcceptanceStatus.get(0).toString())

                holder.binding.CableType.text=data.CableType
                holder.binding.Make.text=data.Make
                holder.binding.LMLength.text=data.LMLength
                holder.binding.CableLength.text=data.CableLength
                holder.binding.OTDRLength.text=data.OTDRLength
                holder.binding.BillableLength.text=data.BillableLength
                holder.binding.UsedFiberPairs.text=data.UsedFiberPair.toString()
                holder.binding.FMSPortNumbers.text=data.FMSPortNo
                holder.binding.ZeroManholeLat.text=data.ZeroManHoleLatitude
                holder.binding.ZeroManholeLong.text=data.ZeroManHoleLongitude
                holder.binding.VendorCode.text=data.VendorCode
                holder.binding.AcceptanceDate.text=Utils.getFormatedDate(data.AcceptanceDate.substring(0,10),"dd-MMM-yyyy")
                holder.binding.InstallationDate.text=Utils.getFormatedDate(data.InstallationDate.substring(0,10),"dd-MMM-yyyy")

            }catch (e:Exception){
                AppLogger.log("Somthig went wrong in mw ubr linkInfo adapter ${e.localizedMessage}")
                e.localizedMessage?.let { AppLogger.log(it) }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (list.isEmpty())
            2
        else if(list[position] is BackhaulLinkFiberOpticCable)
            1
        else
            3
    }

    override fun getItemCount(): Int {
        return list.size
    }

    var currentOpened = -1
    var recyclerView: RecyclerView?=null
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }

    interface FiberOpticsCableClickListener{
        fun editModeCliked(data :BackhaulLinkFiberOpticCable,pos:Int)
    }
}