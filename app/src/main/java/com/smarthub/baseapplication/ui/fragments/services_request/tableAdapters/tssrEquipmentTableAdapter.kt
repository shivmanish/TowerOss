package com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcotssrEquipmentTableItemBinding
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.OpcoTSSREquipmentTable
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.OpcoTssrAdapter

class tssrEquipmentTableAdapter (var context : Context, var listener : OpcoTssrAdapter.OpcoTssrLisListener,equipmentdata:List<OpcoTSSREquipmentTable>): RecyclerView.Adapter<tssrEquipmentTableAdapter.ViewHold>() {
    var list : ArrayList<OpcoTSSREquipmentTable>?
    init {
        list=equipmentdata as ArrayList<OpcoTSSREquipmentTable>
    }
    fun addItem(item:String){
        list?.add(
            OpcoTSSREquipmentTable(Equipment = "efgf", InputPower = "tgds", MaxPower = "trgf", OperatingTemp = "54",
            Size = "65", Technology = "4g", Type = "trhd", Voltage = "87", Weight = "73", created_at = "12-10-2022",
            id = "543", isActive = "true", modified_at = "22-12-2022")
        )
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= OpcotssrEquipmentTableItemBinding.bind(view)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.opcotssr_equipment_table_item,parent,false)
        return ViewHold(view)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it)
        }
        holder.binding.Technology.text=list?.get(position)?.Technology
        holder.binding.EquipmentInfo.text=list?.get(position)?.Equipment
        holder.binding.EquipmentType.text=list?.get(position)?.Type
        holder.binding.EquipmentSize.text=list?.get(position)?.Size
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
                        listener.editEquipmentClicked(position)
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
                        listener.viewEquipmentClicked(position)
                        return true
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
