package com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TariffTableItemBinding
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo.PowerAndFuelEBConnectionTariffsDetail

class TarifftableAdapter (var context : Context,var table_list :ArrayList<PowerAndFuelEBConnectionTariffsDetail>, var listener : TrafilListiner): RecyclerView.Adapter<TarifftableAdapter.ViewHold>() {


    fun addValue(list :List<PowerAndFuelEBConnectionTariffsDetail>){
        table_list.addAll(list)
        notifyDataSetChanged()
    }

    fun removeItem(position:Int){
        table_list.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= TariffTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.tariff_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
//            show pop up menu
            performOptionsMenuClick(position,it)
        }
        holder.binding.columnOne.text = table_list.get(position).id
    }

    override fun getItemCount(): Int {
        return table_list.size
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
                        listener.trafil(position)

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
                        listener.trafil(position)
                        Toast.makeText(context , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }

    interface TrafilListiner{
        fun trafil(position:Int)
    }
}
