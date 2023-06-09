package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CableDetailsListItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.CableDetailsData
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.NewRfEquipmentData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.rfEquipmentData
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns

class CableDetailsAdapter(var listener: CableDetailItemListner, allEquipData: ArrayList<NewRfEquipmentData>?, var context: Context) : RecyclerView.Adapter<CableDetailsAdapter.ViewHold>() {

    val list :ArrayList<CableDetailsData> = ArrayList()
    var currentOpened = -1
    var recyclerView: RecyclerView?=null

    init {
        try {
            list.clear()
            allEquipData?.get(0)?.CableDetail?.let { list.addAll(it) }
            notifyDataSetChanged()
        }catch (e:Exception){
            AppLogger.log("Data error on cable details Adapter: ${e.localizedMessage}")
        }
    }

    fun updateItem(pos : Int,data :rfEquipmentData){
//        list[pos] = data
//        notifyItemChanged(pos)
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View,) : ViewHold(itemView) {
        var binding : CableDetailsListItemBinding = CableDetailsListItemBinding.bind(itemView)
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
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.cable_details_list_item, parent, false)
                ViewHold1(view,)
            }
            2 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data, parent, false)
                ViewHold(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.cable_details_list_item, parent, false)
                ViewHold1(view,)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list.isEmpty())
            2
        else
            1

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        var data : CableDetailsData = list[position]
        if (holder is ViewHold1) {
            holder.binding.imgEdit.setOnClickListener {
//                listener.EditDialouge(data,position)
            }
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if(currentOpened==position){
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility=View.GONE
                holder.binding.itemCollapse.visibility=View.VISIBLE
                holder.binding.imgEdit.visibility=View.VISIBLE
            }
            else {
                holder.binding.collapsingLayout.tag=false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility=View.VISIBLE
                holder.binding.itemCollapse.visibility=View.GONE
                holder.binding.imgEdit.visibility=View.GONE
            }
            try{
                if (list.isNotEmpty()){
                    data= list[position]
                    holder.binding.itemTitleStr.text = String.format(context.resources.getString(R.string.rf_equipment_title_str_formate),AppPreferences.getInstance().getDropDownValue(DropDowns.CableName.name,data.CableName.get(0).toString()),data.CableType,data.Length)
                    AppPreferences.getInstance().setDropDown(holder.binding.cableName,DropDowns.CableName.name,"1")
                    holder.binding.Type.text=data.CableType
                    holder.binding.usedFor.text=data.UsedFor
                    holder.binding.lenth.text=data.Length
                    holder.binding.remark.text=data.Remark

                }
            }catch (e:Exception){
                e.localizedMessage?.let { AppLogger.log(it) }
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }


    interface CableDetailItemListner {
        fun EditDialouge(data : rfEquipmentData,pos:Int)
        fun attachmentItemClicked()
    }
}