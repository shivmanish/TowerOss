package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.databinding.CivilInfraListItemBinding
import com.smarthub.baseapplication.databinding.CustomerListItemBinding

class CivilInfraAdapter (var listner: CivilInfraAdapterListner): Adapter<CivilInfraAdapter.ViewHold>() {

    class ViewHold(view: View) : ViewHolder(view){
        var binding= CivilInfraListItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.civil_infra_list_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.itemView.setOnClickListener{
            listner.clickedItem()
        }

    }

    override fun getItemCount(): Int {
        return 5
    }

    interface CivilInfraAdapterListner{
        fun clickedItem()
    }
}

