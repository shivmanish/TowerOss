package com.smarthub.baseapplication.ui.site_lease_acquisition.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.adapter.customer.BackhaulListAdapter

class AgrementLeaseListAdapter(var listener: ImageAttachmentAdapter.ItemClickListener) :
    RecyclerView.Adapter<AgrementLeaseListAdapter.ViewHold>() {
    var list: ArrayList<String> = ArrayList()
    var AGREMENT_VIEW_TYPE = 0

    var PROPERTY_VIEW_TYPE = 1

    var ATTACHMENT_VIEW_TYPE =2
    init {
        list.add("Agreement")
        list.add("Property Owner & Payment..")
        list.add("Attachments")

      }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    class AgreementViewHold(itemView: View) : ViewHold(itemView) {
        var binding: AgreementsListItemBinding= AgreementsListItemBinding.bind(itemView)

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
    class BoundaryAgreementViewHold(itemView: View) :ViewHold(itemView) {
        var binding: BoudryDetailsListItemBinding =
            BoudryDetailsListItemBinding.bind(itemView)

        //   var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
            }

            /*    var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
                recyclerListener.adapter = adapter

                itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                    adapter.addItem()
                }*/
        }
    }
    class PropertyAgreementViewHold(itemView: View) :ViewHold(itemView) {
        var binding: PropertyDetailsListItemBinding =
            PropertyDetailsListItemBinding.bind(itemView)

        //   var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
            }

            /*    var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
                recyclerListener.adapter = adapter

                itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                    adapter.addItem()
                }*/
        }
    }
    class POAgreementViewHold(itemView: View) :ViewHold(itemView) {
        var binding: BackhaulPoDetailsListItemBinding =
            BackhaulPoDetailsListItemBinding.bind(itemView)

        //   var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
            }

            /*    var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
                recyclerListener.adapter = adapter

                itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                    adapter.addItem()
                }*/
        }
    }
    class AttachmentViewHold(itemView: View) : ViewHold(itemView) {
        var binding: NominalsListItemBinding = NominalsListItemBinding.bind(itemView)

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.agreements_list_item, parent, false)
        return when (viewType) {
            AGREMENT_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.agreements_list_item, parent, false)
                AgreementViewHold(view)
            }


            PROPERTY_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.property_details_list_item, parent, false)
                PropertyAgreementViewHold(view)
            }


            ATTACHMENT_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.attachment_list_item, parent, false)
                AttachmentViewHold(view)
            }

            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.feasibility_list_item, parent, false)
                ViewHold(view)
            }
        }
    }
      override fun getItemViewType(position: Int): Int {
        return if (list[position] == "Agreement") AGREMENT_VIEW_TYPE

        else if (list[position] == "Property Owner & Payment..") PROPERTY_VIEW_TYPE

        else if (list[position] == "Attachment") ATTACHMENT_VIEW_TYPE
      else 0
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is AgreementViewHold) {
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
        else if (holder is BoundaryAgreementViewHold) {
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
        else if (holder is PropertyAgreementViewHold) {
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
        else if (holder is POAgreementViewHold) {
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