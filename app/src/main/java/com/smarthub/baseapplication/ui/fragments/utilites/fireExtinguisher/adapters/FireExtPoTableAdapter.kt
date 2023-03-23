package com.smarthub.baseapplication.ui.fragments.utilites.fireExtinguisher.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilitySmpsPoTableItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityPoDetails
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class FireExtPoTableAdapter (var context : Context, var listener : FireExtinguisherFragAdapter.FireExtListListener, var list:ArrayList<UtilityPoDetails>?): RecyclerView.Adapter<FireExtPoTableAdapter.ViewHold>() {



    fun addItem(){
        val data=UtilityPoDetails()
        list!!.add(data)
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list!!.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= UtilitySmpsPoTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.utility_smps_po_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item: UtilityPoDetails =list!![position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        if (item.VendorCompany?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(holder.binding.VendorName, DropDowns.VendorCompany.name,item.VendorCompany?.get(0).toString())
        holder.binding.PoNo.text=item.PONumber
        holder.binding.SrNo.text=position.plus(1).toString()
        holder.binding.PoDate.text= Utils.getFormatedDate(item.PODate,"dd-MMM-yyyy")
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int,view : View,data:UtilityPoDetails) {
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
                        listener.editPoClicked(position,data)

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
                        listener.viewPoClicked(position,data)
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
