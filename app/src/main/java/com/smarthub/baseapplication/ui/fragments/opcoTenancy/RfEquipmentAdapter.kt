package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfEquipmentListItemsBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.EquipmentData
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.NewRfEquipmentData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.rfEquipmentData
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class RfEquipmentAdapter(var listener: RfEquipmentItemListner, allEquipData: ArrayList<NewRfEquipmentData>?, var context: Context) : RecyclerView.Adapter<RfEquipmentAdapter.ViewHold>() {

    val list :ArrayList<EquipmentData> =ArrayList()
    var currentOpened = -1
    var recyclerView: RecyclerView?=null

    init {
        try {
            list.clear()
            allEquipData?.get(0)?.Equipment01?.let { list.addAll(it) }
            notifyDataSetChanged()
        }catch (e:Exception){
            AppLogger.log("Data error on Equipment Adapter: ${e.localizedMessage}")
        }

    }

    fun updateItem(pos : Int,data :rfEquipmentData){
//        list[pos] = data
//        notifyItemChanged(pos)
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View,listener: RfEquipmentItemListner) : ViewHold(itemView) {
        var binding : RfEquipmentListItemsBinding = RfEquipmentListItemsBinding.bind(itemView)

//        var adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
//            override fun itemClicked() {
//                listener.attachmentItemClicked()
//            }
//        })
        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

//            val recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
//            recyclerListener.adapter = adapter
//
//            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
//                adapter.addItem()
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_list_items, parent, false)
                ViewHold1(view,listener)
            }
            2 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data, parent, false)
                ViewHold(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_list_items, parent, false)
                ViewHold1(view,listener)
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
        var data : EquipmentData = list[position]
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
                    holder.binding.itemTitleStr.text = String.format(context.resources.getString(R.string.rf_equipment_title_str_formate),AppPreferences.getInstance().getDropDownValue(DropDowns.EquipmentName.name,"1"),data.SerialNumber,Utils.getFormatedDate(data.InstallationDate.substring(0,10),"ddMMMyyyy"))
                    AppPreferences.getInstance().setDropDown(holder.binding.EquipmentName,DropDowns.EquipmentName.name,"1")
                    AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatus,DropDowns.OperationalStatus.name,"1")
                    holder.binding.Model.text=data.Model
                    holder.binding.SerialNumber.text=data.SerialNumber
                    holder.binding.Make.text=data.Make
                    holder.binding.InstallationDate.text=data.InstallationDate
                    holder.binding.MaxPowerRating.text=data.MaxPowerRating
                    holder.binding.Weight.text=data.Weight
                    holder.binding.RackNo.text=data.RackNo
                    holder.binding.minOpratingTemp.text=data.OperatingTempMin
                    holder.binding.maxOpratingTemp.text=data.OperatingTempMax
                    holder.binding.sizeLenth.text=data.SizeL
                    holder.binding.sizeBidth.text=data.SizeB
                    holder.binding.sizeHeight.text=data.SizeH
                    holder.binding.RackSpaceUsed.text=data.RackUSpaceUsed.toString()
                    holder.binding.minVoltageRating.text=data.VoltageMin
                    holder.binding.maxVoltageRating.text=data.VoltageMax
                    holder.binding.Type.text=data.Type

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


    interface RfEquipmentItemListner {
        fun EditDialouge(data : rfEquipmentData,pos:Int)
        fun attachmentItemClicked()
    }
}