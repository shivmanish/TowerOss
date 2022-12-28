package com.smarthub.baseapplication.ui.site_agreement.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ServiceRequestTeamvendorAttachmentBinding
import com.smarthub.baseapplication.databinding.TeamVendorListItemBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class TeamVendorFragAdapter (var listener: TeamVendorListItemListner) : RecyclerView.Adapter<TeamVendorFragAdapter.ViewHold>() {

    var currentOpened = -1
    var list: ArrayList<String> = ArrayList()
    var type1="Details"
    var type2="Attachments"

    init {
        list.add("Details")
        list.add("Attachments")

    }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    class DetailsViewHold(itemView: View) : ViewHold(itemView) {
        var binding: TeamVendorListItemBinding = TeamVendorListItemBinding.bind(itemView)

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
    class AttachmentViewHold(itemView: View, listener: TeamVendorListItemListner) : ViewHold(itemView) {
        var binding: ServiceRequestTeamvendorAttachmentBinding = ServiceRequestTeamvendorAttachmentBinding.bind(itemView)
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
                val view = LayoutInflater.from(parent.context).inflate(R.layout.team_vendor_list_item, parent, false)
                DetailsViewHold(view)
            }
            2 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.service_request_teamvendor_attachment, parent, false)
                AttachmentViewHold(view,listener)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.team_vendor_list_item, parent, false)
                ViewHold(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position]==type1)
            return 1
        else if (list[position] is String && list[position]==type2)
            return 2
        else return 0
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is DetailsViewHold) {
            holder.binding.imgEdit.setOnClickListener {
                listener.EditdetailsItemClicked()
            }
            if (currentOpened == position) {
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility = View.GONE
                holder.binding.itemCollapse.visibility = View.VISIBLE
                holder.binding.imgEdit.visibility = View.VISIBLE
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
        }

        else if (holder is AttachmentViewHold) {
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


    interface TeamVendorListItemListner {
        fun attachmentItemClicked()
        fun EditdetailsItemClicked()
    }
}