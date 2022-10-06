package com.smarthub.baseapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpViewType1Binding
import com.smarthub.baseapplication.databinding.AtpViewType2Binding
import com.smarthub.baseapplication.model.atp.AtpCardList
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.HeaderList

class AtpListAdapter : RecyclerView.Adapter<AtpListAdapter.ViewHold>() {

    val imageData: ArrayList<Any> = ArrayList()
    val VIEW_TYPE_1 = 0
    val VIEW_TYPE_2 = 1
    val DEFAULT = 2

    init {

        var atpCardListAdapter = AtpCardList(AtpHeaderStatus("QAT Activites","Add New"),ArrayList())
        imageData.add(HeaderList(ArrayList()))
        imageData.add(atpCardListAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view: View
        if (viewType == VIEW_TYPE_1) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.atp_view_type_1, parent, false)
            return ViewType1(view)
        }else if (viewType == VIEW_TYPE_2) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.atp_view_type_2, parent, false)
            return ViewType2(view)
        }
        else view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty, parent, false)
        return ViewHold(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (imageData[position] is HeaderList) VIEW_TYPE_1
        else if (imageData[position] is AtpCardList) VIEW_TYPE_2
        else DEFAULT
    }

    override fun onBindViewHolder(hold: ViewHold, pos: Int) {
        if (hold is ViewType2){
            var item = imageData[pos] as AtpCardList
            hold.atpViewType2Binding.headerList.adapter = AtpCardListAdapter(ArrayList(item.list))
        }
        if (hold is ViewType1){
            var item = imageData[pos] as HeaderList
            hold.atpViewType1Binding.headerList.adapter = AtpHeaderItemsAdapter(ArrayList(item.list))
        }
    }

    override fun getItemCount(): Int {
        return imageData?.size!!
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewType1(itemView: View) : ViewHold(itemView) {
        var atpViewType1Binding = AtpViewType1Binding.bind(itemView)

    }

    class ViewType2(itemView: View) : ViewHold(itemView) {
        var atpViewType2Binding = AtpViewType2Binding.bind(itemView)
    }

}