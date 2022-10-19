package com.smarthub.baseapplication.ui.adapter.qat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpHeaderTitleBinding
import com.smarthub.baseapplication.databinding.AtpMainHeaderTitleBinding
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus

class AtpMainItemAdapter(var listener: QatProfileListener) : RecyclerView.Adapter<AtpMainItemAdapter.ViewHold>() {

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : AtpMainHeaderTitleBinding = AtpMainHeaderTitleBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.atp_main_header_title,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.root.setOnClickListener {
            listener.itemClicked()
        }
    }

    override fun getItemCount(): Int {
        return 6
    }
}