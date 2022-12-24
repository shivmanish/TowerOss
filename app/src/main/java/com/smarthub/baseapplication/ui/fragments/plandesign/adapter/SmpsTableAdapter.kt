package com.smarthub.baseapplication.ui.fragments.plandesign.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocPoTableDataBinding
import com.smarthub.baseapplication.databinding.PoleTableItemBinding
import com.smarthub.baseapplication.databinding.SmpsPlannedTableItemBinding
import com.smarthub.baseapplication.databinding.SmpsRectifierTableItemBinding
import com.smarthub.baseapplication.ui.fragments.noc.NocListAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.TowerInfoListAdapter

class SmpsTableAdapter (var context : Context, var listener : TableCallback): RecyclerView.Adapter<SmpsTableAdapter.ViewHold>() {

    var list  = ArrayList<String>()

    init {
        list.add("item1")
        list.add("item1")
        list.add("item1")
        list.add("item1")
    }

    fun addItem(item:String){
        list.add(item)
        notifyItemInserted(list.size.plus(1))
    }

    fun removeItem(position:Int){
        list.removeAt(position)
        notifyItemRemoved(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.smps_rectifier_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
//            show pop up menu
            performOptionsMenuClick(position,it)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= SmpsRectifierTableItemBinding.bind(view)
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
                        listener.editItem(position)

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
                        listener.viewItem(position)
                        Toast.makeText(context , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
