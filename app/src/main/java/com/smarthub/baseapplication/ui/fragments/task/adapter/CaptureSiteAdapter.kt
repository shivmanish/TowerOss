package com.smarthub.baseapplication.ui.fragments.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CaptureSiteDataItemLayoutBinding
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel
import com.smarthub.baseapplication.utils.AppLogger

class CaptureSiteAdapter(val context: Context,var list : TaskDropDownModel, var listner:CapturedSite) : Adapter<CaptureSiteViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaptureSiteViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.capture_site_data_item_layout, parent, false)
        return CaptureSiteViewholder(view)
    }

    override fun onBindViewHolder(holder: CaptureSiteViewholder, position: Int) {
        val sublistAdapter=CaptureItemAdapter(context,list[position].tabs,currentSelected==position,
            if(currentSelected==position) sublistCheckedPos else ArrayList(),
            object : CaptureItemAdapterListener{
                override fun onCheckedCountChanged(count: Int,sublistStatus:ArrayList<Boolean>) {
                    holder.binding.titleCheckbox.tag = count > 0
                    if (holder.binding.titleCheckbox.tag as Boolean){
                        if (currentSelected!=currentOpened){
                            updateSelection(currentOpened,sublistStatus)
                        }
                        else
                            updateSelectedListItem(sublistStatus)
                    }
                    else
                        updateSelection(currentOpened,ArrayList())
                }

            })
        if(currentSelected==position){
            holder.binding.titleCheckbox.setImageResource(R.drawable.check_selected)
        }else
            holder.binding.titleCheckbox.setImageResource(R.drawable.check_unselected)


        if (currentOpened == position) {
            holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
            holder.binding.itemLine.visibility = View.GONE
            holder.binding.itemCollapse.visibility = View.VISIBLE
            holder.binding.list.adapter = sublistAdapter
        }
        else {
            holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
            holder.binding.itemLine.visibility = View.VISIBLE
            holder.binding.itemCollapse.visibility = View.GONE
        }
        holder.binding.collapsingLayout.setOnClickListener {
            updateList(position)
        }

        holder.binding.titleCheckbox.setOnClickListener {
            if (holder.binding.titleCheckbox.tag==null)
                holder.binding.titleCheckbox.tag = false
            if (holder.binding.titleCheckbox.tag is Boolean){
                holder.binding.titleCheckbox.tag = !(holder.binding.titleCheckbox.tag as Boolean)
            }
            if (holder.binding.titleCheckbox.tag as Boolean){
                holder.binding.titleCheckbox.setImageResource(R.drawable.check_selected)
                val checkedList:ArrayList<Boolean> =ArrayList()
                for (item in list[position].tabs){
                    checkedList.add(true)
                }
                updateSelection(position,checkedList)
            }
            else {
                holder.binding.titleCheckbox.setImageResource(R.drawable.check_unselected)
                updateSelection(position,ArrayList())
            }

        }
        holder.binding.titleStr.text = list[position].name
    }

    override fun getItemCount(): Int {
        return list.size
    }
    var currentOpened = -1
    var currentSelected = -1
    var sublistCheckedPos:ArrayList<Boolean> = ArrayList()

    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
    }

    fun updateSelection(position: Int,statusList:ArrayList<Boolean>){
        sublistCheckedPos=statusList
        currentSelected = if(currentSelected == position) -1 else position
        listner.selectedSites(generateSelectedIds(statusList))
        AppLogger.log("sulist in updateSelection===>: $statusList")
        notifyDataSetChanged()
    }
    fun updateSelectedListItem(statusList:ArrayList<Boolean>){
        sublistCheckedPos=statusList
        listner.selectedSites(generateSelectedIds(statusList))
        AppLogger.log("sulist in updateSelection===>: $statusList")
        notifyDataSetChanged()
    }

    fun generateSelectedIds(listItem:ArrayList<Boolean>):ArrayList<String>{
        val selectedIds=ArrayList<String>()
        AppLogger.log("current selected parent at captureSite Adapter ====> : $currentSelected")
        AppLogger.log("current selected child list at genrated function ====> : $sublistCheckedPos")
        if (currentSelected!=-1)
        {
            val subTabList=list[currentSelected].tabs
//            selectedIds.add("$currentSelected")
            for(i in 0..sublistCheckedPos.size.minus(1))
            {
                if (listItem[i])
                    selectedIds.add(subTabList[i].id.toString())
            }
        }

        return selectedIds
    }

}

class CaptureSiteViewholder(itemView: View) : ViewHolder(itemView) {
    var binding = CaptureSiteDataItemLayoutBinding.bind(itemView)
}

interface CapturedSite{
    fun selectedSites(selectedSites:ArrayList<String>)
}
