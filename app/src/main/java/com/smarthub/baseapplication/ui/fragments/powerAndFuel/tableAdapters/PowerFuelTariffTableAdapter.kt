package com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerFuelTariffTableItemsBinding
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelTariffDetails
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter.PowerConnecFragAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class PowerFuelTariffTableAdapter (var context : Context, var listener : PowerConnecFragAdapter.PowerConnectionListListener, var list:ArrayList<PowerFuelTariffDetails>?): RecyclerView.Adapter<PowerFuelTariffTableAdapter.ViewHold>() {


    fun addItem(){
        val data=PowerFuelTariffDetails()
        list?.add(data)
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= PowerFuelTariffTableItemsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.power_fuel_tariff_table_items,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item:PowerFuelTariffDetails=list!![position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        try {
            holder.binding.AverageConsUnit.text=item.AverageConsumableUnit
            holder.binding.ApplicableTarrifRate.text=item.TariffRate
            holder.binding.TarrifEffectiveDate.text=Utils.getFormatedDate(item.TarrifEffectiveDate,"dd-MMM-yyyy")
            holder.binding.SrNo.text=position.plus(1).toString()
        }catch (e:java.lang.Exception){
            AppLogger.log("ToewerPoTableadapter error : ${e.localizedMessage}")
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int,view : View,data:PowerFuelTariffDetails) {
        // create object of PopupMenu and pass context and view where we want
        // to show the popup menu
        val popupMenu = PopupMenu(context , view)
        // add the menu
        popupMenu.inflate(R.menu.options_menu2)
        // implement on menu item click Listener
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.action_edit -> {
                        popupMenu.dismiss()
                        listener.editTarrifClicked(position,data)
                        return true
                    }
                    // in the same way you can implement others
                    R.id.action_delete -> {
                        popupMenu.dismiss()
                        // define
                        removeItem(position)
                        return true
                    }
                }
                return false
            }
        })
        popupMenu.show()
    }
}
