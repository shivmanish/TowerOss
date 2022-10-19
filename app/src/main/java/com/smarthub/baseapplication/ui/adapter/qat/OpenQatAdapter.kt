package com.smarthub.baseapplication.ui.adapter.qat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.QatCheckopenItemBinding
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.qatcheck.OpenQatDataModel

class OpenQatAdapter(var list : ArrayList<OpenQatDataModel>, var listener: QatProfileListener) : RecyclerView.Adapter<OpenQatAdapter.ViewHold>() {

    init {
        list.add(OpenQatDataModel("item1","item2","item2","item2","item2","item2"))
        list.add(OpenQatDataModel("item1","item2","item2","item2","item2","item2"))
        list.add(OpenQatDataModel("item1","item2","item2","item2","item2","item2"))
        list.add(OpenQatDataModel("item1","item2","item2","item2","item2","item2"))

    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : QatCheckopenItemBinding = QatCheckopenItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.qat_checkopen_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.root.setOnClickListener {
            listener.itemClicked()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}