package com.smarthub.baseapplication.ui.fragments.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CaptureSiteDataItemLayoutBinding
import com.smarthub.baseapplication.databinding.TaskQatTabDropdownBinding
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.model.taskModel.GeoGraohyLevelDropDownModel
import com.smarthub.baseapplication.model.taskModel.dropdown.CollectionItem
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModelItem
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.widgets.CustomSpinner

class CaptureSiteAdapter(val context: Context,var TaskTablist : TaskDropDownModel, var listner:CapturedSite,
                         var selectedTabIndex:Int,var selectedSubTabList:ArrayList<Boolean>,var QatId:String,
                         var qatDropDownList:ArrayList<DropDownItem>) : Adapter<ViewHold>() {

    var list:ArrayList<Any> =ArrayList()
    var currentOpened = -1
    var selctedQatId :String= ""
    var currentSelected = -1
    var sublistCheckedPos:ArrayList<Boolean> = ArrayList()
    init {
        if (selectedTabIndex!=-1){
            AppLogger.log("selected parent tab in init ===>$selectedTabIndex")
            currentSelected=selectedTabIndex
            selctedQatId=QatId
            if (selectedSubTabList.isNotEmpty())
                sublistCheckedPos=selectedSubTabList
        }
        list.clear()
        list.addAll(TaskTablist)
        list.add("QA List")
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position] is TaskDropDownModelItem)
            return 1
        else if (list[position] is String && list[position]=="QA List")
            return 2
        return 0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when(viewType){
            1->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.capture_site_data_item_layout, parent, false)
                CaptureSiteViewholder(view)
            }
            2->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.task_qat_tab_dropdown, parent, false)
                QAListViewholder(view)
            }
            else->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
                ViewHold(view)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {

        when(holder){
            is CaptureSiteViewholder->{
                val item = list[position] as TaskDropDownModelItem
                val sublistAdapter=CaptureItemAdapter(context,item.tabs,currentSelected==position,
                    if(currentSelected==position) sublistCheckedPos else ArrayList(),
                    object : CaptureItemAdapterListener{
                        override fun onCheckedCountChanged(count: Int,sublistStatus:ArrayList<Boolean>) {
                            holder.binding.titleCheckbox.tag = count > 0
                            if (holder.binding.titleCheckbox.tag as Boolean){
                                if (currentSelected!=currentOpened){
                                    updateSelection(currentOpened,sublistStatus, ArrayList())
                                }
                                else
                                    updateSelectedListItem(sublistStatus,ArrayList())
                            }
                            else
                                updateSelection(currentOpened,ArrayList(),ArrayList())
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
                        if (position==4)
                            AppLogger.log("siteAcq SubItem List ===>:${Gson().toJson(item.tabs)}")
                        for (SubItem in item.tabs){
                            checkedList.add(true)
                            if (position==4)
                                AppLogger.log("siteAcq Bool List ===>:$checkedList")
                        }
                        if (position==4)
                            AppLogger.log("siteAcq Bool List out of loop ===>:$checkedList")
                        updateSelection(position,checkedList,ArrayList())
                    }
                    else {
                        if (position==4)
                            AppLogger.log("siteAcq Bool List tag condition false")
                        holder.binding.titleCheckbox.setImageResource(R.drawable.check_unselected)
                        updateSelection(position,ArrayList(),ArrayList())
                    }

                }
                holder.binding.titleStr.text = item.name
            }
            is QAListViewholder->{
                if (selctedQatId!="")
                    holder.binding.QaList.setSpinnerData(qatDropDownList,selctedQatId)
                else{
                    holder.binding.QaList.setSpinnerData(qatDropDownList)
                }
                holder.binding.QaList.setOnItemSelectionListener(
                    object : CustomSpinner.ItemSelectedListener{
                        override fun itemSelected(selectedItem: DropDownItem) {
                            AppLogger.log("setOnItemSelectedListener :${selectedItem.name}")
                            selctedQatId=selectedItem.id
                            listner.selectedSites(arrayListOf("q_${selectedItem.id}"))

                        }
                    }
                )
                if(currentSelected==position){
                    holder.binding.titleCheckbox.setImageResource(R.drawable.check_selected)
                }else{
                    holder.binding.titleCheckbox.setImageResource(R.drawable.check_unselected)
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.itemLine.visibility = View.GONE
                    if(currentSelected==position)
                        holder.binding.itemCollapse.visibility = View.VISIBLE
                    else
                        holder.binding.itemCollapse.visibility = View.GONE
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
                        if (qatDropDownList.isNotEmpty())
                            updateSelection(position,ArrayList(), arrayListOf("q_${qatDropDownList[0].id}"))
                    }
                    else {
                        holder.binding.titleCheckbox.setImageResource(R.drawable.check_unselected)
                        updateSelection(position,ArrayList(), ArrayList())
                    }

                }
                holder.binding.titleStr.text = list[position] as String
            }
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
    }

    fun updateSelection(position: Int,statusList:ArrayList<Boolean>,QatstatusList:ArrayList<Any>){
        sublistCheckedPos=statusList
        currentSelected = if(currentSelected == position) -1 else position
        if (statusList.isNotEmpty())
            listner.selectedSites(generateSelectedIds(statusList))
        else
            listner.selectedSites(QatstatusList)
        AppLogger.log("sulist in updateSelection===>: $statusList")
        notifyDataSetChanged()
    }
    fun updateSelectedListItem(statusList:ArrayList<Boolean>,QatstatusList:ArrayList<Any>){
        sublistCheckedPos=statusList
        if (statusList.isNotEmpty())
            listner.selectedSites(generateSelectedIds(statusList))
        else
            listner.selectedSites(QatstatusList)
        AppLogger.log("sulist in updateSelection===>: $statusList")
        notifyDataSetChanged()
    }

    fun generateSelectedIds(listItem:ArrayList<Boolean>):ArrayList<Any>{
        val selectedIds=ArrayList<Any>()
        AppLogger.log("current selected parent at captureSite Adapter ====> : $currentSelected")
        AppLogger.log("current selected child list at genrated function ====> : $sublistCheckedPos")
        if (currentSelected!=-1)
        {
            var subTabList:ArrayList<CollectionItem> = ArrayList()
            if (list[currentSelected] is TaskDropDownModelItem){
                val item=list[currentSelected] as TaskDropDownModelItem
                subTabList=item.tabs
            }
//            selectedIds.add("$currentSelected")
            for(i in 0..sublistCheckedPos.size.minus(1))
            {
                if (listItem[i])
                    selectedIds.add(subTabList[i].id)
            }
        }

        return selectedIds
    }

}


open class ViewHold(itemView: View) : ViewHolder(itemView)
class CaptureSiteViewholder(itemView: View) : ViewHold(itemView) {
    var binding = CaptureSiteDataItemLayoutBinding.bind(itemView)
}
class QAListViewholder(itemView: View) : ViewHold(itemView) {
    var binding = TaskQatTabDropdownBinding.bind(itemView)
}

interface CapturedSite{
    fun selectedSites(selectedSites:ArrayList<Any>)
}
