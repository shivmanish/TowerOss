package com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.EquipmentTableItemBinding
import com.smarthub.baseapplication.databinding.TowerPoTableItemBinding
import com.smarthub.baseapplication.model.serviceRequest.Equipment
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesRequestAdapter


class SREquipmentTableAdapter (var context : Context, var listener : ServicesRequestAdapter.ServicesRequestLisListener, equipmentData:List<Equipment> ): RecyclerView.Adapter<SREquipmentTableAdapter.ViewHold>() {

    var list : ArrayList<Equipment>?

    init {
        list=equipmentData as ArrayList<Equipment>
    }

    fun addItem(){
//        list?.add(Equipment(CabinetSize = "4", EquipmentWeight = "8",
//            InputPower = "5", MaxPowerRating = "76", OperatingTemp = "723", Technology = "empty",
//            Voltage ="43", created_at = "22-10-2022", id = 448,
//            isActive = true, modified_at = "22-12-2022" ))
//        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= EquipmentTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.equipment_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it)
        }
        holder.binding.Technology.text= list?.get(position)?.Technology
        holder.binding.EquipmentInfo.text=list?.get(position)?.EquipmentWeight
        holder.binding.EquipmentType.text=""
        holder.binding.EquipmentSize.text=""
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    // this method will handle the onclick options click
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
                        Toast.makeText(context , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
                        return true
                    }

                    R.id.action_view -> {
                        popupMenu.dismiss()
                        listener.viewEquipmentClicked(position)
                        Toast.makeText(context , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
