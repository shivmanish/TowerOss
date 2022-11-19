package com.smarthub.baseapplication.ui.adapter.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class BackhaulListAdapter(var listener: ImageAttachmentAdapter.ItemClickListener) : RecyclerView.Adapter<BackhaulListAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    var LINK_VIEW_TYPE =0
    init {
        list.add("Link")
        list.add("IDU")
        list.add("ODU")
        list.add("Anteena")
        list.add("Installation Team")
        list.add(" Materials")
        list.add("LMC; Fiber")
        list.add("ATP Checklist")
        list.add("PO Details")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    class LinkViewHold(itemView: View,listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView) {
        var binding : BackhaulListItemBinding = BackhaulListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }
    class BackHaulViewHold(itemView: View,listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView) {
        var binding : BackhaulListItemBinding = BackhaulListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
        return when(viewType){
            LINK_VIEW_TYPE->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
                LinkViewHold(view,listener)
            }
            1->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
                BackHaulViewHold(view,listener)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
                ViewHold(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] == "Link") LINK_VIEW_TYPE
        else if (list[position] == "IDU") 1
        else if (list[position] == "ODU") 2
        else 0
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
       if (holder is LinkViewHold){
//           link data binding
           holder.binding.collapsingLayout.setOnClickListener {
               holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
               if ((holder.binding.itemTitle.tag as Boolean)) {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
               } else {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
               }

               holder.binding.itemLine.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
               holder.binding.itemCollapse.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
           }
           holder.binding.itemTitle.text = list[position]
       }else if (holder is BackHaulViewHold){
//            backhaul data binding
           if (holder is LinkViewHold){
               holder.binding.collapsingLayout.setOnClickListener {
                   holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                   if ((holder.binding.itemTitle.tag as Boolean)) {
                       holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
                   } else {
                       holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
                   }

                   holder.binding.itemLine.visibility =
                       if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                   holder.binding.itemCollapse.visibility =
                       if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
               }
               holder.binding.itemTitle.text = list[position]
           }
       }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ItemClickListener{
        fun itemClicked()
    }
}