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
import com.smarthub.baseapplication.model.serviceRequest.AcquisitionSurvey.ASBoundryStructureDetail
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.SectorsOrCellDetail
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.OpcoTssrAdapter

class SecotrsCellsDetailsTableAdapter (var context : Context, var listener : OpcoTssrAdapter.OpcoTssrLisListener,sectorData:List<SectorsOrCellDetail>): RecyclerView.Adapter<SecotrsCellsDetailsTableAdapter.ViewHold>() {
    var list : ArrayList<SectorsOrCellDetail>?
    init {
        list = sectorData as ArrayList<SectorsOrCellDetail>
    }
    fun addItem(item:String){
        list?.add(
            SectorsOrCellDetail(AntennaHeight = "34", AzimuthOrOrientation = "dsf", Feasibility = "rgttr",
            Frequency = "ewr", Obstructions = "sfeg", SerialNo = "54", Shape = "shetrgf",
            Size = "43", TRXCount = "54", Technology = "4g", Type = "ercs", Weight = "45",
            created_at = "22-10-2022", id = 7, isActive = true, modified_at = "22-12-2022")
        )
        notifyItemInserted(list?.size!!.plus(1))
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
        holder.binding.Technology.text=list?.get(position)?.Technology
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
                        listener.editSectorCellsDetailsClicked(position)
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
