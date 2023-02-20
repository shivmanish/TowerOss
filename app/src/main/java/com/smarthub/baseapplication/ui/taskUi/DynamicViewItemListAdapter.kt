package com.smarthub.baseapplication.ui.taskUi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TextItemViewBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.ui.dynamic.ItemData

class DynamicViewItemListAdapter(var data: List<ItemData>) : RecyclerView.Adapter<DynamicViewItemListAdapter.ViewHold>() {

    val ITEM_TEXT = 0
    val ITEM_SPINNER = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            ITEM_TEXT -> TextViewHold(LayoutInflater.from(parent.context).inflate(R.layout.text_item_view, parent, false))
            ITEM_SPINNER -> SpinnerViewHold(LayoutInflater.from(parent.context).inflate(R.layout.text_item_view, parent, false))
            else -> TextViewHold(LayoutInflater.from(parent.context).inflate(R.layout.text_item_view, parent, false))
        }
    }

    override fun onBindViewHolder(hold: ViewHold, pos: Int) {
        val item : ItemData = data[pos]
        when (hold) {
            is TextViewHold -> {
                hold.binding.name.text = "${item.value}"
            }
            is SpinnerViewHold -> {
                AppPreferences.getInstance().setDropDown(hold.binding.name,item.value,"1")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position].type) {
            "text" -> ITEM_TEXT
            "spinner" -> ITEM_SPINNER
            else -> ITEM_TEXT
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    open class TextViewHold(itemView: View) : ViewHold(itemView){
        var binding : TextItemViewBinding = TextItemViewBinding.bind(itemView)
    }
    open class SpinnerViewHold(itemView: View) : ViewHold(itemView){
        var binding : TextItemViewBinding = TextItemViewBinding.bind(itemView)
    }

    interface DynamicItemListAdapterListener{
        fun onDateFieldFind(text:TextView)
    }

}