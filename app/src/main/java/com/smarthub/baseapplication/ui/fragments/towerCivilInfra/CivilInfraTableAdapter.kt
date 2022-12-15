package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.databinding.CivilInfraListItemBinding
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.databinding.EarthingPoTableBinding
import com.smarthub.baseapplication.databinding.EarthingPoTableItemBinding

class CivilInfraTableAdapter: Adapter<CivilInfraTableAdapter.ViewHold>() {

    var list  = ArrayList<String>()

    init {
        list.add("item1")
        list.add("item1")
        list.add("item1")
        list.add("item1")
    }

    fun addItem(item:String){
        list.add(item)
        notifyItemInserted(list.size.minus(1))
    }

    fun removeItem(position:Int){
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : ViewHolder(view){
        var binding= EarthingPoTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.earthing_po_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
//            show pop up menu

        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    interface CivilInfraTableAdapterListener{

    }
}

