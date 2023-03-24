package com.smarthub.baseapplication.ui.fragments.utilites.dg.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilitySmpsConsumTableItemBinding
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityConsumableMaterial
import com.smarthub.baseapplication.utils.Utils

class DGConsumeMaterialTableAdapter (var context : Context, var listener : DGFragAdapter.DGListListener, var list:ArrayList<UtilityConsumableMaterial>?): RecyclerView.Adapter<DGConsumeMaterialTableAdapter.ViewHold>() {


    fun addItem(){
        val data=UtilityConsumableMaterial()
        list!!.add(data)
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list!!.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= UtilitySmpsConsumTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_consum_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item: UtilityConsumableMaterial =list!![position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        holder.binding.SrNo.text=position.plus(1).toString()
        holder.binding.ItemName.text=item.ItemName
        holder.binding.Type.text=item.ItemType
        holder.binding.InstallationDate.text= Utils.getFormatedDate(item.InstallationDate,"dd-MMM-yyyy")
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int,view : View,data:UtilityConsumableMaterial) {
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
                        listener.editConsumMaterialTableItem(position,data)

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
                        listener.viewConsumMaterialTableItem(position,data)
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
