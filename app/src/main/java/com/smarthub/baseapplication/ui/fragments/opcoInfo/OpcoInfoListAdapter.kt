package com.smarthub.baseapplication.ui.fragments.opcoInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModelDropDown

class OpcoInfoListAdapter(var listener: OpcoInfoLisListener) : RecyclerView.Adapter<OpcoInfoListAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    var type1 = "OPCO/Site Info"
    var type2 = "Operations Team"
    var type3 = "Attachments"
    private var data : BasicInfoModelDropDown?=null

    fun setData(data : BasicInfoModelDropDown){
        this.data = data
        notifyDataSetChanged()
    }

    init {
        list.add("OPCO/Site Info")
        list.add("Operations Team")
        list.add("Attachments")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position]==type1)
            return 1
        else if (list[position] is String && list[position]==type2)
            return 2
        else if (list[position] is String && list[position]==type3)
            return 3
        return 0
    }

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : BasicDetailItemViewBinding = BasicDetailItemViewBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }
    class ViewHold2(itemView: View) : ViewHold(itemView) {
        var binding : OperationInfoViewBinding = OperationInfoViewBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }
    class ViewHold3(itemView: View) : ViewHold(itemView) {
        var binding : GeoConditionListItemBinding = GeoConditionListItemBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.basic_detail_item_view, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.operation_info_view, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.geo_condition_list_item, parent, false)
                return ViewHold3(view)
            }

        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                holder.binding.collapsingLayout.setOnClickListener {
                    holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                    if ((holder.binding.itemTitle.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)

                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }
                    holder.binding.itemLine.visibility = if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.iconLayout.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.imgEdit.setOnClickListener {
                        listener.detailsItemClicked()
                    }

                    holder.binding.itemCollapse.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.iconLayout.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                }
                holder.binding.itemTitle.text = list[position]

                if (data!=null) {
                  /*  holder.binding.siteStatusSpinner.setSpinnerData(data?.sitestatus?.data)
                    holder.binding.siteCategorySpinner.setSpinnerData(data?.sitecategory?.data)
                    holder.binding.siteOwnershipSpinner.setSpinnerData(data?.siteownership?.data)
                    holder.binding.siteTypeSpinner.setSpinnerData(data?.sitetype?.data)*/
                }
            }
            is ViewHold2 -> {
                holder.binding.collapsingLayout.setOnClickListener {
                    holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                    if ((holder.binding.itemTitle.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }

                    holder.binding.itemLine.visibility =
                        if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.iconLayout.visibility =
                        if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                    holder.binding.imgEdit.setOnClickListener()
                    {
                        listener.operationInfoDetailsItemClicked()
                    }

                    holder.binding.itemCollapse.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.iconLayout.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                }
                holder.binding.itemTitle.text = list[position]
            }
            is ViewHold3 -> {
                holder.binding.collapsingLayout.setOnClickListener {
                    holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                    if ((holder.binding.itemTitle.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }

                    holder.binding.itemLine.visibility =
                        if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.iconLayout.visibility =
                        if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                    holder.binding.imgEdit.setOnClickListener()
                    {
                        listener.geoConditionsDetailsItemClicked()
                    }

                    holder.binding.itemCollapse.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.iconLayout.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                }
                holder.binding.itemTitle.text = list[position]
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OpcoInfoLisListener {
        fun attachmentItemClicked()
        fun operationsItemClicked()
        fun opcoSiteInfoItemClicked()
    }
}