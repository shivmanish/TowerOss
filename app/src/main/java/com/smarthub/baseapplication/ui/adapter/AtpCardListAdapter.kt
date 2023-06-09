package com.smarthub.baseapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CardItemBinding
import com.smarthub.baseapplication.databinding.LangItemBinding
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.LangModel
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.AtpListItem

class AtpCardListAdapter(var listener: QatListListener) : RecyclerView.Adapter<AtpCardListAdapter.ViewHold>() {

    var indexSize = 1
    fun addListItem(){
        indexSize++
        notifyItemChanged(indexSize - 1)
    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : CardItemBinding = CardItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.card_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.headerList.adapter = CardItemAdapter(ArrayList(),listener)
        holder.binding.root.setOnClickListener {
            listener.cardClicked()
        }
    }

    override fun getItemCount(): Int {
        return indexSize
    }
}