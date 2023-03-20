package com.smarthub.baseapplication.ui.fragments.utilites.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilitySmpsConnLoadsTableItemBinding
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityConnectedLoad
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityRectifierModule
import com.smarthub.baseapplication.ui.fragments.utilites.adapter.SmpsUtilityFragAdapter
import com.smarthub.baseapplication.utils.Utils

class SmpsConnLoadsTableAdapter (var context : Context, var listener : SmpsUtilityFragAdapter.SmpsInfoListListener, var list:ArrayList<UtilityConnectedLoad>?): RecyclerView.Adapter<SmpsConnLoadsTableAdapter.ViewHold>() {


    fun addItem(item:String){
        val data=UtilityConnectedLoad()
        list?.add(data)
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list!!.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= UtilitySmpsConnLoadsTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_conn_loads_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item: UtilityConnectedLoad =list!![position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        holder.binding.McbNo.text=item.MCBNumber.toString()
        holder.binding.ConnectedEquipment.text=item.ConnectedEquipment
        holder.binding.ActualReading.text=item.ActualReading
        holder.binding.InstallationDate.text= Utils.getFormatedDate(item.InstallationDate,"dd-MMM-yyyy")
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int,view : View,data:UtilityConnectedLoad) {
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
                        listener.editConnLoadsTableItem(position,data)
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
                        listener.viewConnLoadsTableItem(position,data)
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
