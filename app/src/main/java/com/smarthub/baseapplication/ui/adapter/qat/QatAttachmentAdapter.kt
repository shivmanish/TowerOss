package com.smarthub.baseapplication.ui.adapter.qat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PunchAttachmentLayoutBinding
import com.smarthub.baseapplication.listeners.PunchPointListener

class QatAttachmentAdapter(var listener: PunchPointListener) : RecyclerView.Adapter<QatAttachmentAdapter.ViewHold>() {

    var list = ArrayList<String>()

    fun addItem(item : String){
        list.add(item)
        notifyDataSetChanged()
    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : PunchAttachmentLayoutBinding = PunchAttachmentLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.punch_attachment_layout,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {

    }

    override fun getItemCount(): Int {
        return list.size
    }
}