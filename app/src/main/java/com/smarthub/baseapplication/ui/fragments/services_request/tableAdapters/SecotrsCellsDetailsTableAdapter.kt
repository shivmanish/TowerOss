package com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoTssrRfFeasibilityTableItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.RFFeasibility
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.SectorsOrCellDetail
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.OpcoTssrAdapter
import com.smarthub.baseapplication.utils.DropDowns

class SecotrsCellsDetailsTableAdapter(
    var context: Context,
    var listener: OpcoTssrAdapter.OpcoTssrLisListener,
    var rfFeasibility: RFFeasibility,
    var serviceRequestAllData: ServiceRequestAllDataItem?
): RecyclerView.Adapter<SecotrsCellsDetailsTableAdapter.ViewHold>() {
    var list : ArrayList<SectorsOrCellDetail>?
    init {
        list = rfFeasibility.SectorsOrCellDetails as ArrayList<SectorsOrCellDetail>
    }
    fun addItem(item:String){
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= OpcoTssrRfFeasibilityTableItemBinding.bind(view)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.opco_tssr_rf_feasibility_table_item,parent,false)
        return ViewHold(view)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it)
        }
        holder.binding.SerislNo.text=list?.get(position)?.SerialNo
        AppPreferences.getInstance().setDropDown(holder.binding.Technology,
            DropDowns.Rftechnology.name,list?.get(position)?.Technology)

//        holder.binding.Technology.text=list?.get(position)?.Technology
        holder.binding.TrxCount.text=list?.get(position)?.TRXCount
        holder.binding.AnteenaHeight.text=list?.get(position)?.AntennaHeight

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
//                        val data = rfFeasibility.clone()
//                        data.SectorsOrCellDetails!!.clear()
//                        data.SectorsOrCellDetails!!.add(list!!.get(position))
//                        listener.editSectorCellsDetailsClicked(data,serviceRequestAllData)
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
                        listener.viewSectorCellsDetailsClicked(position)
                        return true
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
