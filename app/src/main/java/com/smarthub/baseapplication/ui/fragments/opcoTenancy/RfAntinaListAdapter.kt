package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.rfEquipmentData
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class RfAntinaListAdapter(var listener: RfAnteenaItemClickListener,opcodata: OpcoDataItem?) : RecyclerView.Adapter<RfAntinaListAdapter.ViewHold>() {

    var list : List<RfAnteenaData> ? = opcodata?.RfAntena
    var currentOpened = -1
    lateinit var data : RfAnteenaData

    class ViewHold(itemView: View,listener: RfAnteenaItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var binding : RfAntinaListItemBinding = RfAntinaListItemBinding.bind(itemView)
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
            1 ->
        }
        var view = LayoutInflater.from(parent.context).inflate(R.layout.rf_antina_list_item,parent,false)
        return ViewHold(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.imgEdit.setOnClickListener {
            listener.editModeCliked()
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
        holder.binding.itemTitleStr.text = "3G RRH - S3058940 - 10-Nov-22"
    }

    override fun getItemViewType(position: Int): Int {
        return if (list?.isEmpty()!! || list?.get(position)==null)
            2
        else
            1
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

    interface RfAnteenaItemClickListener{
        fun attachmentItemClicked()
        fun editModeCliked()
    }
}