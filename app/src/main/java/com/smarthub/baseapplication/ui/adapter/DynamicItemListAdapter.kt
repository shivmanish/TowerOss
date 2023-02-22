package com.smarthub.baseapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpHeaderTitleBinding
import com.smarthub.baseapplication.databinding.DateItemViewBinding
import com.smarthub.baseapplication.databinding.DynamicTitleListBinding
import com.smarthub.baseapplication.databinding.SpinnerItemViewBinding
import com.smarthub.baseapplication.databinding.TextItemEditBinding
import com.smarthub.baseapplication.databinding.TextItemViewBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.ui.dynamic.DynamicTitleList
import com.smarthub.baseapplication.ui.dynamic.ItemData
import com.smarthub.baseapplication.ui.dynamic.TitleItem

class DynamicItemListAdapter(var data: List<ItemData>,var listener:DynamicItemListAdapterListener) : RecyclerView.Adapter<DynamicItemListAdapter.ViewHold>() {

    val ITEM_TEXT = 0
    val ITEM_SPINNER = 1
    val ITEM_DATE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            ITEM_TEXT -> TextViewHold(LayoutInflater.from(parent.context).inflate(R.layout.text_item_edit, parent, false))
            ITEM_SPINNER -> SpinnerViewHold(LayoutInflater.from(parent.context).inflate(R.layout.spinner_item_view, parent, false))
            ITEM_DATE -> DateViewHold(LayoutInflater.from(parent.context).inflate(R.layout.date_item_view, parent, false))
            else -> TextViewHold(LayoutInflater.from(parent.context).inflate(R.layout.text_item_edit, parent, false))
        }
    }

    override fun onBindViewHolder(hold: ViewHold, pos: Int) {
        val item : ItemData = data[pos]
        when (hold) {
            is TextViewHold -> {
                hold.binding.label.text=item.label
                hold.binding.name.setText(item.value)
            }
            is SpinnerViewHold -> {
                hold.binding.label.text=item.label
                AppPreferences.getInstance().setDropDown(hold.binding.name,item.dropDownName,item.value)
            }
            is DateViewHold -> {
                hold.binding.label.text=item.label
                hold.binding.name.text = "${item.value}"
                listener.onDateFieldFind(hold.binding.name)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position].type) {
            "text" -> ITEM_TEXT
            "spinner" -> ITEM_SPINNER
            else -> ITEM_DATE
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    open class TextViewHold(itemView: View) : ViewHold(itemView){
        var binding : TextItemEditBinding = TextItemEditBinding.bind(itemView)
    }
    open class SpinnerViewHold(itemView: View) : ViewHold(itemView){
        var binding : SpinnerItemViewBinding = SpinnerItemViewBinding.bind(itemView)
    }
    open class DateViewHold(itemView: View) : ViewHold(itemView){
        var binding : DateItemViewBinding = DateItemViewBinding.bind(itemView)
    }

    interface DynamicItemListAdapterListener{
        fun onDateFieldFind(text:TextView)
    }

}