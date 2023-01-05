package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PoleConsumableTableItemBinding
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.PoleModelConsumable
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.PoleInfoFragAdapter
import com.smarthub.baseapplication.utils.AppLogger

class PoleconsumableTableAdapter (var context : Context, var listener : PoleInfoFragAdapter.PoleInfoListListener, consumdata:List<PoleModelConsumable>?): RecyclerView.Adapter<PoleconsumableTableAdapter.ViewHold>() {

    var list : ArrayList<PoleModelConsumable>?

    init {
        list=consumdata as ArrayList<PoleModelConsumable>
    }

    fun addItem(item:String){
        list?.add(
            PoleModelConsumable(Date = "22-12-2022", Description = "dsfg", ItemCode = "53", Qty = "654d2",
                created_at = "22-10-2022", id ="56", isActive = "true", modified_at = "22-12-2022")
        )
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= PoleConsumableTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pole_consumable_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it)
        }
        try {
            holder.binding.ItemName.text="Data Not Found"
            holder.binding.ItemCode.text=list?.get(position)?.ItemCode
            holder.binding.Description.text=list?.get(position)?.Description
            holder.binding.Qty.text=list?.get(position)?.Qty
        }catch (e:java.lang.Exception){
            AppLogger.log("ToewerPoTableadapter error : ${e.localizedMessage}")
            Toast.makeText(context,"ToewerPoTableadapter error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
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
                        listener.editConsumableClicked(position)

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
                        listener.viewConsumableClicked(position)
                        Toast.makeText(context , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}