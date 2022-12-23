package com.smarthub.baseapplication.ui.fragments.plandesign.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.databinding.PlanDesignListItemBinding
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestFrqagment


class PlanDesignAdapter(var listener: PlanDesignAdapterListener, var array: ArrayList<String>) : RecyclerView.Adapter<PlanDesignViewHolder>() {

    fun setData(data: ArrayList<String>) {
        this.array.addAll(data)
        notifyDataSetChanged()
    }

    fun updateData(s : String) {
        this.array.add(s)
        notifyItemChanged(array.size.minus(1))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanDesignViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.plan_design_list_item, parent, false)
        return PlanDesignViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanDesignViewHolder, position: Int) {

        holder.binding?.cardItem?.setOnClickListener {
            listener.clickedItem()
        }
    }

    override fun getItemCount(): Int {
        return array.size
    }
}

class PlanDesignViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {
    var binding = PlanDesignListItemBinding.bind(itemView)
}

interface PlanDesignAdapterListener{
    fun clickedItem()
}