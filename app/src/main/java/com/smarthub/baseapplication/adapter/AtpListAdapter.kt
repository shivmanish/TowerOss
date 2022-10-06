package com.smarthub.baseapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpViewType1Binding
import com.smarthub.baseapplication.databinding.AtpViewType2Binding
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.atp.AtpCardList
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.HeaderList

class AtpListAdapter(var listener: QatProfileListener) : RecyclerView.Adapter<AtpListAdapter.ViewHold>() {

    val imageData: ArrayList<Any> = ArrayList()
    val VIEW_TYPE_1 = 0
    val VIEW_TYPE_2 = 1
    val DEFAULT = 2

    var adapter : AtpCardListAdapter?=null
    init {

        var atpCardListAdapter = AtpCardList(AtpHeaderStatus("QAT Activites","Add New"),ArrayList())
        imageData.add(HeaderList(ArrayList()))
        imageData.add(atpCardListAdapter)

        adapter = AtpCardListAdapter(ArrayList(atpCardListAdapter.list),listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view: View
        if (viewType == VIEW_TYPE_1) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.atp_view_type_1, parent, false)
            return ViewType1(view,listener)
        }else if (viewType == VIEW_TYPE_2) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.atp_view_type_2, parent, false)
            return ViewType2(view,listener)
        }
        else view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty, parent, false)
        return ViewHold(view,listener)
    }

    override fun getItemViewType(position: Int): Int {
        return if (imageData[position] is HeaderList) VIEW_TYPE_1
        else if (imageData[position] is AtpCardList) VIEW_TYPE_2
        else DEFAULT
    }

    override fun onBindViewHolder(hold: ViewHold, pos: Int) {
        if (hold is ViewType2){
            hold.atpViewType2Binding.root.setOnClickListener {
                listener.itemClicked()
            }
            hold.atpViewType2Binding.addNew.setOnClickListener {
                adapter?.addListItem()
                Toast.makeText(hold.atpViewType2Binding.root.context,"Item Added",Toast.LENGTH_SHORT).show()
            }
//            var item = imageData[pos] as AtpCardList
            hold.atpViewType2Binding.headerList.adapter = adapter
        }
        else if (hold is ViewType1){
            hold.atpViewType1Binding.root.setOnClickListener {
                listener.itemClicked()
            }
            var item = imageData[pos] as HeaderList
            hold.atpViewType1Binding.headerList.adapter = AtpHeaderItemsAdapter(ArrayList(item.list),listener)
        }else {
            hold.itemView.setOnClickListener {
                listener.itemClicked()
            }
        }
    }

    override fun getItemCount(): Int {
        return imageData?.size!!
    }

    open class ViewHold(itemView: View,listener: QatProfileListener) : RecyclerView.ViewHolder(itemView)

    class ViewType1(itemView: View,listener: QatProfileListener) : ViewHold(itemView,listener) {
        var atpViewType1Binding = AtpViewType1Binding.bind(itemView)

    }

    class ViewType2(itemView: View,listener: QatProfileListener) : ViewHold(itemView,listener) {
        var atpViewType2Binding = AtpViewType2Binding.bind(itemView)
    }

}