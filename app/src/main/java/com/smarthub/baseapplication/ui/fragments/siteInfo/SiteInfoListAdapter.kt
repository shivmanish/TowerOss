package com.smarthub.baseapplication.ui.fragments.siteInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModel
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.customer_tab.CustomerInvoiceAdapter

class SiteInfoListAdapter(var listener: ImageAttachmentAdapter.ItemClickListener) : RecyclerView.Adapter<SiteInfoListAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    var type1 = "Basic Details"
    var type2 = "Operational Info"
    var type3 = "Rental/ Energy Charges"
    var type4 = "Invoice/ Payment Status"
    var type5 = "OPCO Contact Details"
    private var data : BasicInfoModel?=null

    fun setData(data : BasicInfoModel){
        this.data = data
        notifyDataSetChanged()
    }

    init {
        list.add("Basic Details")
        list.add("Operational Info")
    }

    open class ViewHold(itemView: View, var listener: ImageAttachmentAdapter.ItemClickListener) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position]==type1)
            return 1
        else if (list[position] is String && list[position]==type2)
            return 2
        else if (list[position] is String && list[position]==type3)
            return 3
        else if (list[position] is String && list[position]==type4)
            return 4
        else if (list[position] is String && list[position]==type5)
            return 5
        return 0
    }

    class ViewHold1(itemView: View, listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView,listener) {
        var binding : BasicDetailItemViewBinding = BasicDetailItemViewBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
            }
        }
    }
    class ViewHold2(itemView: View, listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView,listener) {
        var binding : OperationInfoViewBinding = OperationInfoViewBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
            }
        }
    }
    class ViewHold3(itemView: View, listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView,listener) {
        var binding : CommercialListItem3Binding = CommercialListItem3Binding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }
    class ViewHold4(itemView: View, listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView,listener) {
        var binding : CommercialListItem4Binding = CommercialListItem4Binding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
            }

            binding.listItem.adapter = CustomerInvoiceAdapter()
        }
    }

    class ViewHold5(itemView: View, listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView,listener) {
        var binding : CommercialListItem5Binding = CommercialListItem5Binding.bind(itemView)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.basic_detail_item_view, parent, false)
                return ViewHold1(view,listener)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.operation_info_view, parent, false)
                return ViewHold2(view,listener)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.commercial_list_item3, parent, false)
                return ViewHold3(view,listener)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.commercial_list_item4, parent, false)
                return ViewHold4(view,listener)
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.commercial_list_item5, parent, false)
                return ViewHold5(view,listener)
            }

        }
        return ViewHold(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                holder.binding.itemTitle.setOnClickListener {
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

                if (data!=null) {
                    holder.binding.siteStatusSpinner.setSpinnerData(data?.sitestatus?.data)
                    holder.binding.siteCategorySpinner.setSpinnerData(data?.sitecategory?.data)
                    holder.binding.siteOwnershipSpinner.setSpinnerData(data?.siteownership?.data)
                    holder.binding.siteTypeSpinner.setSpinnerData(data?.sitetype?.data)
                }
            }
            is ViewHold2 -> {
                holder.binding.itemTitle.setOnClickListener {
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
            is ViewHold3 -> {
                holder.binding.itemTitle.setOnClickListener {
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
            is ViewHold4 -> {
                holder.binding.itemTitle.setOnClickListener {
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
            is ViewHold5 -> {
                holder.binding.itemTitle.setOnClickListener {
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