package com.smarthub.baseapplication.ui.site_lease_acquisition.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class Team_VendorLeaseListAdapter(var listener: ImageAttachmentAdapter.ItemClickListener) :
    RecyclerView.Adapter<Team_VendorLeaseListAdapter.ViewHold>() {

    var list: ArrayList<String> = ArrayList()

    var DETAILS_VIEW_TYPE = 0
    var ATTACHMENT_VIEW_TYPE = 1
    init {
        list.add("Details")
        list.add("Attachments")

      }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    class DetailsViewHold(itemView: View) : ViewHold(itemView) {
        var binding: TeamVendorListItemBinding = TeamVendorListItemBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
            }


        }
    }
    class AttachmentViewHold(itemView: View,listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView) {
        var binding: AttachmentListItemBinding = AttachmentListItemBinding.bind(itemView)
       var adapter =  ImageAttachmentAdapter(listener)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
            }
                var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
                recyclerListener.adapter = adapter
                itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                    adapter.addItem()
                }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.team_vendor_list_item, parent, false)
        return when (viewType) {
            DETAILS_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.team_vendor_list_item, parent, false)
                DetailsViewHold(view)
            }
            ATTACHMENT_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.attachment_list_item, parent, false)
                AttachmentViewHold(view,listener)
            }

            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.team_vendor_list_item, parent, false)
                ViewHold(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] == "Details") DETAILS_VIEW_TYPE
        else if (list[position] == "Attachments") ATTACHMENT_VIEW_TYPE
      else 0
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is DetailsViewHold) {
            holder.binding.collapsingLayout.setOnClickListener {
                holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                if ((holder.binding.itemTitle.tag as Boolean)) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                } else {
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE


                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
            }
            holder.binding.itemTitle.text = list[position]
        }

       else if (holder is AttachmentViewHold) {
            holder.binding.collapsingLayout.setOnClickListener {
                holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                if ((holder.binding.itemTitle.tag as Boolean)) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                } else {
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.iconLayout.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
            }
            holder.binding.itemTitle.text = list[position]
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ItemClickListener {
        fun itemClicked()
    }
}