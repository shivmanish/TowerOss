package com.smarthub.baseapplication.adapter.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.adapter.AtpCardListAdapter
import com.smarthub.baseapplication.adapter.CardItemAdapter
import com.smarthub.baseapplication.adapter.qat.AtpMainItemAdapter
import com.smarthub.baseapplication.databinding.AtpMainCardItemBinding
import com.smarthub.baseapplication.databinding.AtpViewType1Binding
import com.smarthub.baseapplication.databinding.AtpViewType2Binding
import com.smarthub.baseapplication.databinding.CardItemBinding
import com.smarthub.baseapplication.databinding.HistoryLayoutBinding
import com.smarthub.baseapplication.databinding.TitleTextLayoutBinding
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.atp.AtpCardList
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.HeaderList

class AtpMainListAdapter(var listener: QatProfileListener) : RecyclerView.Adapter<AtpMainListAdapter.ViewHold>() {

    val imageData: ArrayList<Any> = ArrayList()
    val VIEW_TYPE_1 = 0
    val VIEW_TYPE_2 = 1
    val VIEW_TYPE_3 = 2
    val DEFAULT = 3
    init {

//        imageData.add("type1")
        imageData.add("type3")
        imageData.add("type2")
        imageData.add("type2")
        imageData.add("type2")
        imageData.add("type2")
        imageData.add("type2")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view: View
        if (viewType == VIEW_TYPE_1) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.history_layout, parent, false)
            return ViewType1(view)
        }else if (viewType == VIEW_TYPE_2) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.atp_main_card_item, parent, false)
            return ViewType2(view)
        }else if (viewType == VIEW_TYPE_3) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.title_text_layout, parent, false)
            return ViewType3(view)
        }
        else view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty, parent, false)
        return ViewHold(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (imageData[position] is String && (imageData[position].toString() == "type1")) VIEW_TYPE_1
        else if (imageData[position] is String && (imageData[position].toString() == "type2")) VIEW_TYPE_2
        else if (imageData[position] is String && (imageData[position].toString() == "type3")) VIEW_TYPE_3
        else DEFAULT
    }

    override fun onBindViewHolder(hold: ViewHold, pos: Int) {
        if (hold is ViewType2){
            hold.binding.headerList.adapter = AtpMainItemAdapter(listener)
            hold.binding.root.setOnClickListener {
                listener.itemClicked()
            }
        }
        else if (hold is ViewType1){
            hold.atpViewType1Binding.root.setOnClickListener {
                listener.itemClicked()
            }
        }else {
            hold.itemView.setOnClickListener {
                listener.itemClicked()
            }
        }
    }

    override fun getItemCount(): Int {
        return imageData?.size!!
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewType1(itemView: View) : ViewHold(itemView) {
        var atpViewType1Binding = HistoryLayoutBinding.bind(itemView)

    }

    class ViewType2(itemView: View) : ViewHold(itemView) {
        var binding : AtpMainCardItemBinding = AtpMainCardItemBinding.bind(itemView)
    }

    class ViewType3(itemView: View) : ViewHold(itemView) {
        var binding : TitleTextLayoutBinding = TitleTextLayoutBinding.bind(itemView)
    }

}