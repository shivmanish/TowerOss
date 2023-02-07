package com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcotssrFiberTableItemBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.BackhaulFeasibility
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.Fiber
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.OpcoTssrAdapter

class BackhaulFiberTableAdapter(
    var context: Context, var listener: OpcoTssrAdapter.OpcoTssrLisListener,
    var backhaulFeasibility: BackhaulFeasibility,
    var serviceRequestAllData: ServiceRequestAllDataItem?
): RecyclerView.Adapter<BackhaulFiberTableAdapter.ViewHold>() {
    var list : ArrayList<Fiber>?
    init {
        list=backhaulFeasibility.Fiber as ArrayList<Fiber>
    }
    fun addItem(item:String){
//        list?.add(
//            Fiber(CableLength = "56", CableType = "dsf", Feasibility = "erf", FiberCore = "trhd",
//            LayingType = "trgtr", LmLength = "45", OpcoFarEndSite = "fgd", SerialNo = "56",
//                created_at = "22-10-2022", id = "54", isActive = "true", modified_at = "22-12-2022" )
//        )
//        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= OpcotssrFiberTableItemBinding.bind(view)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.opcotssr_fiber_table_item,parent,false)
        return ViewHold(view)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it)
        }
        holder.binding.SerialNo.text=list?.get(0)?.SerialNo
        holder.binding.OpcoForEndSite.text=list?.get(0)?.OpcoFarEndSite
        holder.binding.LmLenth.text=list?.get(0)?.LmLength
        holder.binding.CableLenth.text=list?.get(0)?.CableLength
    }
    override fun getItemCount(): Int {
        return list?.size!!
    }
    private fun performOptionsMenuClick(position: Int,view : View) {
        // create object of PopupMenu and pass context and view where we want
        // to show the popup menu
        val popupMenu = PopupMenu(context , view)
        // add the menu
        popupMenu.inflate(R.menu.options_menu)
        // implement on menu item click Listener
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.action_edit -> {
                        popupMenu.dismiss()
                        listener.editBackhaulFiberClicked(backhaulFeasibility,serviceRequestAllData)
                        return true
                    }
                    // in the same way you can implement others
                    R.id.action_delete -> {
                        popupMenu.dismiss()
                        // define
                        removeItem(position)
                        return true
                    }

                    R.id.action_view -> {
                        popupMenu.dismiss()
                        listener.viewBackhaulFiberClicked(position)
                        return true
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
