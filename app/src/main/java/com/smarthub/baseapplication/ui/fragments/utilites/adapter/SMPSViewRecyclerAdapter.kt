package com.smarthub.baseapplication.ui.utilites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PoItemListBinding

class SMPSViewRecyclerAdapter : RecyclerView.Adapter<SMPSViewRecyclerAdapter.ViewHolder>(){
    private val arr = arrayOf(
        "1st",
        "2nd",
        "3rd",
        "4th",
        "5th"
    )
    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val textViewCode: TextView
        init{
            textViewCode = view.findViewById(R.id.txtItemCode)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.po_item_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.textViewCode.text= arr.get(position)

    }

    override fun getItemCount(): Int {
       return arr.size
    }

}