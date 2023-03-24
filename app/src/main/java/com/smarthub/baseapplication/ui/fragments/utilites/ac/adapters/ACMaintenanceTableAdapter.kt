package com.smarthub.baseapplication.ui.fragments.utilites.ac.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PreventiveMentinanceTableItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityPreventiveMaintenance
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class ACMaintenanceTableAdapter (var context : Context, var listener : ACFragAdapter.ACListListener, var list:ArrayList<UtilityPreventiveMaintenance>?):
    RecyclerView.Adapter<ACMaintenanceTableAdapter.ViewHold>() {


    fun addItem(){
        val data=UtilityPreventiveMaintenance()
        list?.add(data)
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list!!.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= PreventiveMentinanceTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.preventive_mentinance_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item: UtilityPreventiveMaintenance =list!![position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        holder.binding.SrNo.text=position.plus(1).toString()
        holder.binding.ServiceType.text=item.ServiceType
        holder.binding.PmDate.text= Utils.getFormatedDate(item.PMDate,"dd-MMM-yyyy")
        if (item.VendorCompany?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(holder.binding.VendorName,DropDowns.VendorCompany.name, item.VendorCompany?.get(0).toString())
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int,view : View,data:UtilityPreventiveMaintenance) {
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
                        listener.editMaintenamceTableItem(position,data)
                        return true
                    }
                    // in the same way you can implement others
                    R.id.action_delete -> {
                        popupMenu.dismiss()
                        removeItem(position)
                        return true
                    }

                    R.id.action_view -> {
                        popupMenu.dismiss()
                        listener.viewMaintenanceTableItem(position,data)
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
