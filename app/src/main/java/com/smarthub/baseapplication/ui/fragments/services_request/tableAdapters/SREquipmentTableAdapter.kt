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
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.serviceRequest.Equipment
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesRequestAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns


class SREquipmentTableAdapter(
    var context: Context,
    var listener: ServicesRequestAdapter.ServicesRequestLisListener,
    var list: ArrayList<Equipment>,
    var serviceRequestAllData: ServiceRequestAllDataItem
) : RecyclerView.Adapter<SREquipmentTableAdapter.ViewHold>() {


    fun addItem() {
//        list?.add(Equipment(CabinetSize = "4", EquipmentWeight = "8",
//            InputPower = "5", MaxPowerRating = "76", OperatingTemp = "723", Technology = "empty",
//            Voltage ="43", created_at = "22-10-2022", id = 448,
//            isActive = true, modified_at = "22-12-2022" ))
//        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position: Int) {
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view) {
        var binding = EquipmentTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.equipment_table_item, parent, false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position, it)
        }
//        holder.binding.Technology.text = list?.get(position)?.Technology
        list[position].Technology?.get(0)?.let {
            AppPreferences.getInstance().getDropDown(DropDowns.Technology.name)[it]
        }

//        holder.binding.EquipmentInfo.text = list.get(position)?.EquipmentWeight
        try {
            list.get(position).Type?.toInt()?.let {
                AppPreferences.getInstance().getDropDown(DropDowns.SREquipmentsType.name)
                    .get(it)
            }
        }catch (e:Exception){
            AppLogger.log(e.localizedMessage)
        }


//        holder.binding.EquipmentSize.text = list[position]?.CabinetSize
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int, view: View) {
        // create object of PopupMenu and pass context and view where we want
        // to show the popup menu
        val popupMenu = PopupMenu(context, view)
        // add the menu
        popupMenu.inflate(R.menu.options_menu)
        // implement on menu item click Listener
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.action_edit -> {
                        popupMenu.dismiss()
                        listener.editEquipmentClicked(
                            list.get(position),
                            serviceRequestAllData,
                            "edit"
                        )
                        return true
                    }
                    // in the same way you can implement others
                    R.id.action_delete -> {
                        popupMenu.dismiss()
                        // define
                        removeItem(position)
                        Toast.makeText(context, "Item 2 clicked", Toast.LENGTH_SHORT).show()
                        return true
                    }

                    R.id.action_view -> {
                        popupMenu.dismiss()
                        listener.viewEquipmentClicked(position)
                        Toast.makeText(context, "Item 2 clicked", Toast.LENGTH_SHORT).show()
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
