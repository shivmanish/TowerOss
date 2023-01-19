package com.smarthub.baseapplication.ui.fragments.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CaptureSiteDataItemLayoutBinding
import com.smarthub.baseapplication.utils.Utils

class CaptureSiteAdapter(val context: Context) : Adapter<CaptureSiteViewholder>() {
    var subList : ArrayList<String> = ArrayList()
    var list : ArrayList<String> = ArrayList()
    var type1="Basic Info"
    var type2 = "Service Request"
    var type3 = "OPCO Tenency"
    var type4 = "Plan & Design"
    var type5 = "Site Agreement"
    var type6 = "Utilities Equip"
    var type7 = "NOC & Comp"
    var type8 = "Tower & Civil Infra"
    var type9 = "Power & Fuel"
    var type10 = "QA & Inspection"

    init {
        list.add("Basic Info")
        list.add("Service Request")
        list.add("OPCO Tenency")
        list.add("Plan & Design")
        list.add("Site Agreement")
        list.add("Utilities Equip")
        list.add("NOC & Comp")
        list.add("Tower & Civil Infra")
        list.add("Power & Fuel")
        list.add("QA & Inspection")
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaptureSiteViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.capture_site_data_item_layout, parent, false)
        return CaptureSiteViewholder(view)
    }

    override fun onBindViewHolder(holder: CaptureSiteViewholder, position: Int) {
        if (currentOpened == position) {
            holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
            holder.binding.itemLine.visibility = View.GONE
            holder.binding.itemCollapse.visibility = View.VISIBLE
        }
        else {
            holder.binding.collapsingLayout.tag = false
            holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
            holder.binding.itemLine.visibility = View.VISIBLE
            holder.binding.itemCollapse.visibility = View.GONE
        }
        holder.binding.collapsingLayout.setOnClickListener {
            updateList(position)
        }
        holder.binding.titleStr.text = list[position]
        if(list[position]==type1){
            subList.clear()
            subList.addAll(listOf("Basic Info","Operational Info","GeoConditional","Safety/Access","Attachments"))
            holder.binding.list.adapter = CaptureItemAdapter(context,subList)
        }
        else if(list[position]==type2){
            subList.clear()
            subList.addAll(listOf("Site Request","Assign ACQ Team","Acquisition Survey","OPCO TSSR",
                "Feasibility Plan","Soft Acquisition","Site Proposal","SP Approval/SO"))
            holder.binding.list.adapter = CaptureItemAdapter(context,subList)
        }
        else if(list[position]==type3){
            subList.clear()
            subList.addAll(listOf("OPCO Info","RF Equipment","Backhaul","RF Antenna","Power Load"))
            holder.binding.list.adapter = CaptureItemAdapter(context,subList)
        }
        else if(list[position]==type4){
            subList.clear()
            subList.addAll(listOf("Basic Info","Operational Info","GeoConditional","Safety/Access","Attachments"))
            holder.binding.list.adapter = CaptureItemAdapter(context,subList)
        }
        else if(list[position]==type5){
            subList.clear()
            subList.addAll(listOf("Basic Info","Operational Info","GeoConditional","Safety/Access","Attachments"))
            holder.binding.list.adapter = CaptureItemAdapter(context,subList)
        }
        else if(list[position]==type6){
            subList.clear()
            subList.addAll(listOf("Basic Info","Operational Info","GeoConditional","Safety/Access","Attachments"))
            holder.binding.list.adapter = CaptureItemAdapter(context,subList)
        }
        else if(list[position]==type7){
            subList.clear()
            subList.addAll(listOf("Basic Info","Operational Info","GeoConditional","Safety/Access","Attachments"))
            holder.binding.list.adapter = CaptureItemAdapter(context,subList)
        }
        else if(list[position]==type8){
            subList.clear()
            subList.addAll(listOf("Basic Info","Operational Info","GeoConditional","Safety/Access","Attachments"))
            holder.binding.list.adapter = CaptureItemAdapter(context,subList)
        }
        else if(list[position]==type9){
            subList.clear()
            subList.addAll(listOf("Basic Info","Operational Info","GeoConditional","Safety/Access","Attachments"))
            holder.binding.list.adapter = CaptureItemAdapter(context,subList)
        }
        else if(list[position]==type10){
            subList.clear()
            subList.addAll(listOf("Basic Info","Operational Info","GeoConditional","Safety/Access","Attachments"))
            holder.binding.list.adapter = CaptureItemAdapter(context,subList)
        }
        else {
            subList.clear()
            subList.addAll(listOf("Basic Info","Operational Info","GeoConditional","Safety/Access","Attachments"))
            holder.binding.list.adapter = CaptureItemAdapter(context,subList)
        }
    }

    override fun getItemCount(): Int {

        return list.size
    }
    var currentOpened = -1
    var recyclerView: RecyclerView?=null
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }
}

class CaptureSiteViewholder(itemView: View) : ViewHolder(itemView) {
    var binding = CaptureSiteDataItemLayoutBinding.bind(itemView)
}