package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfEquipmentListItemsBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.rfEquipmentData
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns

class RfEquipmentAdapter(var listener: RfEquipmentItemListner,opcodata: OpcoDataItem?) : RecyclerView.Adapter<RfEquipmentAdapter.ViewHold>() {

    var list : List<rfEquipmentData> ? = opcodata?.RfEquipment
    lateinit var data : rfEquipmentData
    var currentOpened = -1

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View,listener: RfEquipmentItemListner) : ViewHold(itemView) {
        var binding : RfEquipmentListItemsBinding = RfEquipmentListItemsBinding.bind(itemView)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            1 -> {
                var view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_list_items, parent, false)
                ViewHold1(view,listener)
            }
            2 -> {
                var view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data, parent, false)
                ViewHold(view)
            }
            else -> {
                var view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_list_items, parent, false)
                ViewHold1(view,listener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list?.isEmpty()!! || list?.get(position)==null)
            2
        else
            1

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ViewHold1) {
            holder.binding.imgEdit.setOnClickListener {
                if (list !=null && list?.isNotEmpty()!!)
                    list?.get(position)?.let { it1 -> listener.EditDialouge(it1) }
                else
                    AppLogger.log("found null value")
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
                if (list !=null && list?.isNotEmpty()!!){
                    data= list!![position]
                    if(data!=null){
                        holder.binding.itemTitleStr.text = "${AppPreferences.getInstance().getDropDownValue(DropDowns.Technology.name,data?.Technology)} " +
                                "- ${data.Equipementname} - ${data.SerialNumber} - ${data.InstallationDate.substring(0,10)}"
                        holder.binding.EquipmentName.text=data.Equipementname
                        holder.binding.Model.text=data.Model
                        holder.binding.SerialNumber.text=data.SerialNumber
                        holder.binding.Make.text=data.Make
                        holder.binding.Band.text=data.Band
                        holder.binding.InstallationDate.text=data.InstallationDate
                        holder.binding.MaxPowerRating.text=data.PowerRating
                        holder.binding.Weight.text=data.Weight
                        holder.binding.RackNumber.text=data.RackNumber
                        holder.binding.remark.text=data.Remarks
                        holder.binding.minOpratingTemp.text=data.OperatingTempMin
                        holder.binding.maxOpratingTemp.text=data.OperatingTempMax
                        AppPreferences.getInstance().setDropDown(holder.binding.Technology, DropDowns.Technology.name,data?.Technology)
                        AppPreferences.getInstance().setDropDown(holder.binding.OwnerCompany, DropDowns.OwnerCompany.name,data?.OwnerCompany)
                        AppPreferences.getInstance().setDropDown(holder.binding.UserCompany, DropDowns.OemCompany.name,data?.OemCompany)
                        AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatus, DropDowns.OperationStatus.name,data?.OperationStatus)
                        AppPreferences.getInstance().setDropDown(holder.binding.RackSpaceUsed, DropDowns.RackSpaceUsed.name,data?.RackSpaceUsed)

                    }
                }
            }catch (e:Exception){
                AppLogger.log("${e.localizedMessage}")
            }

        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    var recyclerView: RecyclerView?=null
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }


    interface RfEquipmentItemListner {
        fun EditDialouge(data : rfEquipmentData)
        fun attachmentItemClicked()
    }
}