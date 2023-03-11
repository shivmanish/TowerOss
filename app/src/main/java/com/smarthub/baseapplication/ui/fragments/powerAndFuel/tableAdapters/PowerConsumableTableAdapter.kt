package com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerConsumableTableItemBinding
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerConsumableMaterial
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilConsumableMaterial
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerModelConsumable
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter.PowerConnecFragAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.TowerInfoListAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class PowerConsumableTableAdapter (var context : Context, var listener : PowerConnecFragAdapter.PowerConnectionListListener, var list:ArrayList<PowerConsumableMaterial>?): RecyclerView.Adapter<PowerConsumableTableAdapter.ViewHold>() {


    fun addItem(item:String){

//        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= TowerConsumableTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tower_consumable_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item:PowerConsumableMaterial=list?.get(position)!!
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        try {
            holder.binding.SrNo.text=position.plus(1).toString()
            holder.binding.model.text=item.Model
            holder.binding.installationDate.text=Utils.getFormatedDate(item.InstallationDate.substring(0,10),"dd-MMM-yyyy")
            holder.binding.ItemName.text=item.ItemName
        }catch (e:java.lang.Exception){
            AppLogger.log("ToewerPoTableadapter error : ${e.localizedMessage}")
            Toast.makeText(context,"ToewerPoTableadapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int,view : View,data:PowerConsumableMaterial) {
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
//                        listener.editConsumableClicked(position)

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
                        listener.viewConsumableClicked(position,data)
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
