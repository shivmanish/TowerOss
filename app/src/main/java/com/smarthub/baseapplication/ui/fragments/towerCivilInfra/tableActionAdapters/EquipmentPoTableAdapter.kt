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
import com.smarthub.baseapplication.databinding.PolePoTableItemBinding
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.EquipmentModelAuthorityPODetails
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.TowerEquipmentInfoAdapter
import com.smarthub.baseapplication.utils.AppLogger

class EquipmentPoTableAdapter (var context : Context, var listener : TowerEquipmentInfoAdapter.TowerPoleListListener,poData:List<EquipmentModelAuthorityPODetails>?): RecyclerView.Adapter<EquipmentPoTableAdapter.ViewHold>() {

    var list :ArrayList<EquipmentModelAuthorityPODetails>?

    init {
        list= poData as ArrayList<EquipmentModelAuthorityPODetails>
    }

    fun addItem(item:String){
        list?.add(
            EquipmentModelAuthorityPODetails(ItemDescription = "dhg", POAmount = "54", POCopy = "58",
                PODate = "22-12-2022", PONumber = "89", POQty = "56", PORate = "6", POlineNo = "43",
                VendorCode = "87", VendorName = "fdsh", created_at = "22-10-2022", id ="56", isActive = "true",
                modified_at = "22-12-2022")
        )
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= PolePoTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pole_po_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
//            show pop up menu
            performOptionsMenuClick(position,it)
        }
        try {
            holder.binding.VendorName.text=list?.get(position)?.VendorName
            holder.binding.PoNumber.text=list?.get(position)?.PONumber
            holder.binding.VendorCode.text=list?.get(position)?.VendorCode
        }catch (e:java.lang.Exception){
            AppLogger.log("EquipPoTableAdapter error : ${e.localizedMessage}")
            Toast.makeText(context,"EquipPoTableAdapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
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
                        listener.editPoClicked(position)

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
                        listener.viewPoClicked(position)
                        Toast.makeText(context , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
