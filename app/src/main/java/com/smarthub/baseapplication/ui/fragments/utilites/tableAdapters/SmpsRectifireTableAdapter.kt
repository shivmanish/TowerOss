package com.smarthub.baseapplication.ui.fragments.utilites.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilitySmpsRectifireTableItemBinding
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqOutsidePremise
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityRectifierModule
import com.smarthub.baseapplication.ui.fragments.utilites.adapter.SmpsUtilityFragAdapter
import com.smarthub.baseapplication.utils.Utils

class SmpsRectifireTableAdapter (var context : Context, var listener : SmpsUtilityFragAdapter.SmpsInfoListListener, var list:ArrayList<UtilityRectifierModule>): RecyclerView.Adapter<SmpsRectifireTableAdapter.ViewHold>() {



    fun addItem(){
        val data=UtilityRectifierModule()
        list.add(data)
        notifyItemInserted(list.size.plus(1))
    }

    fun removeItem(position:Int){
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= UtilitySmpsRectifireTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_rectifire_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item: UtilityRectifierModule =list[position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        holder.binding.SlotNumber.text=item.SlotNo.toString()
        holder.binding.SerialNo.text=item.SerialNumber
        holder.binding.RatingAmp.text=item.RatingAmp
        holder.binding.InstallationDate.text=Utils.getFormatedDate(item.InstallationDate,"dd-MMM-yyyy")

    }

    override fun getItemCount(): Int {
        return list.size
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int,view : View,data:UtilityRectifierModule) {
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
                        listener.editRectifireTableItem(position,data)

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
                        listener.viewRectifireTableItem(position,data)
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
