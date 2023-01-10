package com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SrdetailsBackhaulLinkItemsBinding
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesRequestAdapter

class BackhaulLinkTableAdapter (var context : Context, var listener : ServicesRequestAdapter.ServicesRequestLisListener): RecyclerView.Adapter<BackhaulLinkTableAdapter.ViewHold>() {
    var list  = ArrayList<String>()
    var type1="Fiber"
    var type2="MW"
    init {
        list.add("Fiber")
        list.add("MW")
    }
    fun addItem(){
//        list.add(
//            RadioAntenna(AntennaCount = "3", AntennaHeight = "76", AntennaSize = "50",
//        AntennaTotalWeight = "54",Technology = "empty", created_at = "22-10-2022", id = 448, isActive = true,
//        modified_at = "22-12-2022")
//        )
//        notifyItemInserted(list.size.plus(1))
    }

    fun removeItem(position:Int){
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= SrdetailsBackhaulLinkItemsBinding.bind(view)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.srdetails_backhaul_link_items,parent,false)
        return ViewHold(view)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if(list[position]==type1){
            holder.binding.AnteenaHeight.visibility=View.GONE
            holder.binding.fiberAnteenaCommonLable.text="Fiber Pairs"
        }
        else if(list[position]==type2){
            holder.binding.AnteenaHeight.visibility=View.VISIBLE
            holder.binding.fiberAnteenaCommonLable.text="Antenna Size, Dia (m)"
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }

}
