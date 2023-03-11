package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BackhaulOpticsEquipmentItemsBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackBackhaulLinkFiberOpticEquipment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class FiberOpticsEquipmentFragAdapter(var listener: BackhaulEquipmentClickListener, var context: Context, BackhaulEquipData:ArrayList<BackBackhaulLinkFiberOpticEquipment>?) : RecyclerView.Adapter<FiberOpticsEquipmentFragAdapter.ViewHold>() {

    var list = ArrayList<Any>()

    fun setData(data: ArrayList<BackBackhaulLinkFiberOpticEquipment>?) {
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
        this.list.addAll(BackhaulEquipData!!)
    }

    fun updateItem(pos : Int,data : BackBackhaulLinkFiberOpticEquipment){
        list[pos] = data
        notifyItemChanged(pos)
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : BackhaulOpticsEquipmentItemsBinding = BackhaulOpticsEquipmentItemsBinding.bind(itemView)
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
                val view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_optics_equipment_items,parent,false)
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
            val data: BackBackhaulLinkFiberOpticEquipment=list[position] as BackBackhaulLinkFiberOpticEquipment
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
                holder.binding.itemTitleStr.text = String.format(context.resources.getString(R.string.rf_antenna_title_str_formate),data.SerialNumber,data.Model, Utils.getFormatedDate(data.InstallationDate.substring(0,10),"ddMMMyyyy"))
                holder.binding.Type.text=data.Type
                holder.binding.Make.text=data.Make
                holder.binding.Model.text=data.Model
                holder.binding.SerialNumber.text=data.SerialNumber
                holder.binding.sizeL.text=data.SizeL
                holder.binding.sizeB.text=data.SizeB
                holder.binding.sizeH.text=data.SizeH
                holder.binding.Weight.text=data.Weight
                holder.binding.VoltageMin.text=data.VoltageMin
                holder.binding.VoltageMax.text=data.VoltageMax
                holder.binding.MaxPowerRating.text=data.MaxPowerRating
                holder.binding.RackUSpace.text=data.RackUSpaceUsed.toString()
                holder.binding.RackNo.text=data.RackNo
                holder.binding.MaxOperatingTemp.text=data.OperatingTempMax
                holder.binding.minOperatingTemp.text=data.OperatingTempMin
                holder.binding.InstallationLocationType.text=data.InstallationLocation.toString()
                holder.binding.IntallationDate.text=Utils.getFormatedDate(data.InstallationDate.substring(0,10),"dd-MMM-yyyy")

            }catch (e:Exception){
                AppLogger.log("Somthig went wrong in mw ubr linkInfo adapter ${e.localizedMessage}")
                e.localizedMessage?.let { AppLogger.log(it) }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (list.isEmpty())
            2
        else if(list[position] is BackBackhaulLinkFiberOpticEquipment)
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

    interface BackhaulEquipmentClickListener{
        fun editModeCliked(data :BackBackhaulLinkFiberOpticEquipment,pos:Int)
    }
}