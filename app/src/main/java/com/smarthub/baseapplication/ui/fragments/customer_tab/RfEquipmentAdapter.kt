package com.smarthub.baseapplication.ui.fragments.customer_tab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpHeaderTitleBinding
import com.smarthub.baseapplication.databinding.CardItemBinding
import com.smarthub.baseapplication.databinding.CommercialListInvoiceStatusBinding
import com.smarthub.baseapplication.databinding.LangItemBinding
import com.smarthub.baseapplication.databinding.RfEquipmentListItemsBinding
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.LangModel
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.AtpListItem
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class RfEquipmentAdapter(var listener: ImageAttachmentAdapter.ItemClickListener) : RecyclerView.Adapter<RfEquipmentAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    init {
       list.add("no_data")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold2(itemView: View,listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView) {
        var adapter =  ImageAttachmentAdapter(listener)
        var binding : RfEquipmentListItemsBinding = RfEquipmentListItemsBinding.bind(itemView)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                //binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up, 0)
                binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.lstItem.setBackgroundResource(R.drawable.bg_expansion_bar)
            }
            else {
                //binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.arrow_farword, 0)
                binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.lstItem.setBackgroundResource(R.color.collapse_card_bg)

            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }
    class ViewHold1(itemView: View,listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView) {
        var adapter =  ImageAttachmentAdapter(listener)
        var binding : RfEquipmentListItemsBinding = RfEquipmentListItemsBinding.bind(itemView)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.lstItem.setBackgroundResource(R.drawable.bg_expansion_bar)

            }
            else {

                binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.lstItem.setBackgroundResource(R.color.collapse_card_bg)
            }

            binding.itemLine.visibility =
                if (binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
            binding.itemCollapse.visibility =
                if (binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
            binding.editRfEquipmentItem.visibility=
                if (binding.itemTitle.tag as Boolean) View.VISIBLE else View.INVISIBLE

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }

    fun addItem(){
        if (list[0] == "no_data") {
            list.removeAt(0)
            list.add(0,"3G RRH - S3058940 - 10-Nov-22")
            notifyDataSetChanged()
        }else{
            list.add(0,"3G RRH - S3058940 - 10-Nov-22")
            notifyItemChanged(0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return if (viewType==1) {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_list_items, parent, false)
            ViewHold2(view,listener)
        }else  if (viewType==2) {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data, parent, false)
            ViewHold(view)
        }else{
            var view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_list_items, parent, false)
            ViewHold1(view,listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position] == "no_data")
            return 2
        else if (position == 0)
            return 1
        return 0
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ViewHold1) {
            holder.binding.itemTitle.setOnClickListener {
                holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                if ((holder.binding.itemTitle.tag as Boolean)) {
                    holder.binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.lstItem.setBackgroundResource(R.drawable.bg_expansion_bar)

                } else {
                    holder.binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.lstItem.setBackgroundResource(R.color.collapse_card_bg)
                }

                holder.binding.editRfEquipmentItem.setOnClickListener {
                    listener.itemClicked()
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                holder.binding.editRfEquipmentItem.visibility=
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.INVISIBLE
            }
            holder.binding.itemTitleStr.text = list[position]
        }
        else if (holder is ViewHold2) {
            holder.binding.itemTitle.setOnClickListener {
                holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                if ((holder.binding.itemTitle.tag as Boolean)) {
                    holder.binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.lstItem.setBackgroundResource(R.drawable.bg_expansion_bar)
                }
                else {
                    holder.binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.lstItem.setBackgroundResource(R.color.collapse_card_bg)
                }

                holder.binding.editRfEquipmentItem.setOnClickListener {
                    listener.itemClicked()
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                holder.binding.editRfEquipmentItem.visibility=
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.INVISIBLE
            }
            holder.binding.itemTitleStr.text = list[position]
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    interface SiteInfoLisListener {
        fun itemClicked()
    }
}