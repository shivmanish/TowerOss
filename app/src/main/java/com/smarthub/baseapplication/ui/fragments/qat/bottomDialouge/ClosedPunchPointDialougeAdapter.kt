package com.smarthub.baseapplication.ui.fragments.qat.bottomDialouge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ClosedPunchpointDialougeListItemBinding
import com.smarthub.baseapplication.databinding.PunchpointDialougeListItemBinding
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.rfEquipmentData
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.PunchpointData
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.AppLogger

class ClosedPunchPointDialougeAdapter(var listener: PunchPointItemListner, var context: Context,var data:ArrayList<PunchpointData>) : RecyclerView.Adapter<ClosedPunchPointDialougeAdapter.ViewHold>() {

    var list : ArrayList<PunchpointData> = ArrayList()

    init {
        this.list=data
    }

    var currentOpened = -1
    var recyclerView: RecyclerView?=null

    fun updateItem(pos : Int,data :PunchpointData){
        list[pos] = data
        notifyItemChanged(pos)
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View,listener: PunchPointItemListner) : ViewHold(itemView) {
        var binding :ClosedPunchpointDialougeListItemBinding  = ClosedPunchpointDialougeListItemBinding.bind(itemView)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.closed_punchpoint_dialouge_list_item, parent, false)
                ViewHold1(view,listener)
            }
            2 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data, parent, false)
                ViewHold(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.closed_punchpoint_dialouge_list_item, parent, false)
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

            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if(currentOpened==position){
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility=View.GONE
                holder.binding.itemCollapse.visibility=View.VISIBLE
            }
            else {
                holder.binding.collapsingLayout.tag=false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility=View.VISIBLE
                holder.binding.itemCollapse.visibility=View.GONE
            }
            try{
                
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


    interface PunchPointItemListner {
        fun attachmentItemClicked()
    }
}